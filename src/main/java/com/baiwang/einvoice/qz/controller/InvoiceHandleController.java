package com.baiwang.einvoice.qz.controller;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baiwang.einvoice.qz.mq.handleRequest.HandleResponse;
import com.baiwang.einvoice.qz.utils.XmlUtil;
import com.baiwang.einvoice.qz.vo.Returnmsg;
import com.baiwang.einvoice.util.InvoiceUtil;

@RequestMapping("handle")
//@Scope("prototype")
@Controller
public class InvoiceHandleController {

	private static final Log logger = LogFactory.getLog(InvoiceHandleController.class);
	
	@Autowired
    private JmsTemplate jmsTemplate2;
	
	@Resource
	private JmsTemplate jmsTemplateDzfpHandle;//电子发票Sender
	@Resource
	private Destination dzfpHandleQuene;//电子发票队列
	
	@Resource
	private JmsTemplate jmsTemplateZzfpHandle;//纸质发票Sender
	@Resource
	private Destination zzfpHandleQuene;
	
	@RequestMapping(value="save",method=RequestMethod.POST)
	@ResponseBody
	public Returnmsg SaveKpInfo(final String xml, HttpServletRequest request) throws UnsupportedEncodingException, JMSException{
		logger.info("接收xml报文：" + xml);
		
		Returnmsg returnmsg = new Returnmsg();
		
		final String fpqqlsh = XmlUtil.random();
		logger.info("发票请求流水号：" + fpqqlsh);
		
		String fplx = InvoiceUtil.getIntervalValue(xml, "<FPLX>", "</FPLX>");
		try{
			if(fplx.equals("026")){
				jmsTemplateDzfpHandle.send(dzfpHandleQuene, new MessageCreator() {
		        	
		            public Message createMessage(Session session) throws JMSException {
		            	TextMessage message = session.createTextMessage(xml);
		            	message.setJMSCorrelationID(fpqqlsh);
		                return message;
		            }
		        });
			}else{
				jmsTemplateZzfpHandle.send(zzfpHandleQuene, new MessageCreator() {
		        	
		            public Message createMessage(Session session) throws JMSException {
		            	TextMessage message = session.createTextMessage(xml);
		            	message.setJMSCorrelationID(fpqqlsh);
		                return message;
		            }
		        });
			}
		}catch(Exception e){
			logger.error("发票请求流水号：" + fpqqlsh + "发生异常...");
			e.printStackTrace();
			returnmsg.setReturncode("400").setReturnmsg("error: 队列异常");
			return returnmsg;
		}
		
		
		//从响应队列检索响应消息
		ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Returnmsg> future = executor.submit(new HandleResponse(fpqqlsh, jmsTemplate2));
        try{
        	returnmsg = future.get(20, TimeUnit.SECONDS);
        	logger.info("响应队列检索响应消息:"+ returnmsg.getReturncode() + "," + returnmsg.getReturnmsg());
        }catch (InterruptedException e) {
        	future.cancel(true);
        	e.printStackTrace();
        	returnmsg.setReturncode("400").setReturnmsg("warn:InterruptedException");
        } catch (ExecutionException e) {
        	future.cancel(true);
        	e.printStackTrace();
        	returnmsg.setReturncode("400").setReturnmsg("warn:ExecutionException");
        } catch (TimeoutException e) {
        	e.printStackTrace();
        	returnmsg.setReturncode("2000").setReturnmsg("正在处理中,请稍后查询");
        } finally {
            executor.shutdown();
        }
		
	
		return returnmsg;
	}	
	
}
