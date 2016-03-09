package com.baiwang.einvoice.qz.resource;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.baiwang.einvoice.qz.mq.EInvoiceSenders;

@Controller
@Path("einvoice")
@Scope("prototype")
public class InvoiceResource {
	
	@Resource
	private EInvoiceSenders sender;
	
	@Autowired(required=true)
	@Qualifier("einvoiceMQ")
	private ActiveMQQueue einvoiceMQ;
	
	@Path("test")
	@GET
	public String test(){
		return "test";
	}
	
	@Path("send")
	@GET
	public String send(){
		
		sender.sendMessage(einvoiceMQ, "测试invoice");
		
		return "success";
	}
	
	
	
	
}
