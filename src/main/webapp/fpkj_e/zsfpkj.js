var cachedInfo,sfxmCount,szsmStr="",fskj=false;
var widthMap = {};
$(function() {
	//����ռλ��
	for (var index = 1; index <= 8; index++) {
		 $("#fyxms").append(newPlaceHolder());
	}
	// ���ɷ�����Ŀ
	for (var index = 1; index <= 0; index++) {
		addRow();
	}
	//�ж��Ƿ���Ͽ�Ʊ����
	var initMsgVal = $("#initMsg").val();
	if(initMsgVal){
		$(".fp-main :input[id!='javax.faces.ViewState']").attr('disabled','disabled');
		setTimeout(function(){
			alert(initMsgVal);
		},200);
		return;
	}
	//˰��˰Ŀ
	$.each($.parseJSON($("#szsmArrInput").val()),function(i,n){
		szsmStr += ("<option value='"+n+"'>"+n*100+"%</option>");
	});
	//��һ�л�ȡ����
	reFocus($("#ghdwmc")[0]);
	//�л���
	$("#fyxms td :text,#fyxms td select").live("focus",function(){
		var index = $(this).parents("tr:first").children("td:first");
		if(index.attr("rowselect")!="y"){
			var other = $(this).parents("table").find("tr:not([zkh])").find("td:first");
			other.attr("rowselect","n").text(function(){return $(this).attr("rowIndex");});
			index.attr("rowselect","y").text("��");
			$("#nowSelectRow").val(index.attr("rowIndex"));
			if(index.attr("haszk")!="y"){
				toggleZK(false);
			}else{
				toggleZK(true);
			}
		}
	});
	//�������뻺��
	$("form").attr("autocomplete","off");
	//��ťEnterʱ��Ӧ
	$("button").keyup(function(event){
		var key = event.which||event.keyCode;
		var keyCode = $.ui.keyCode;
		if(key==keyCode.ENTER||key==keyCode.NUMPAD_ENTER){
			$(this).trigger('click');
		}
	});
	//����ʱ��Enter
	var focusEL = ":text:not(:disabled),select:not(:disabled)";
	$(".fp-content "+focusEL).live("keyup",function(event){
		var key = event.which||event.keyCode;
		var keyCode = $.ui.keyCode;
		if(key==keyCode.ENTER||key==keyCode.NUMPAD_ENTER){
			var now = $(this);
			var toBeFocus = now.nextAll(focusEL).first();
			while(toBeFocus.length==0&&!now.hasClass("fp-content")){
				now = now.parent();
				toBeFocus = now.nextAll().find(focusEL+",textarea").first();
			}
			reFocus(toBeFocus[0]);
		}
	});
	
	//����������,������
	$("#fyxms :text[id^='spsl'],#fyxms :text[id^='spdj']").live("change",function(){
		var index = getIndex(this.id);
		var temp = new Number(this.value.replace(/-/,"")).toFixed(6);
		temp = delRight(new String(temp));
		var isdj = this.id.indexOf("dj")!=-1;
		this.value = temp = isdj?temp:fskj?(-temp):temp;
		var otherValue = isdj?$("#spsl_"+index).val():$("#spdj_"+index).val();
		if(temp!=""||otherValue!=""){
			var result=(temp*otherValue).toFixed(2);
			$("#je_"+index).val(result==0?"":result);
		}
		if(isdj){
			$(this).removeAttr("optDj");
			$("#hsbz_"+index).val($("#sfhs").val());
		}
		reCountRow(index);
	});
	//���㵥�ۻ�����
	$("#fyxms :text[id^='je']").live("change",function(){
		this.value = (this.value.replace(/-/,"")*(fskj?-1:1)).toFixed(2);
		if(this.value==0){
			this.value="";
		}
		var index = getIndex(this.id);
		var spsl = $("#spsl_"+index);
		var spdj = $("#spdj_"+index);
		if(spsl.val()!=""){
			spdj.val(delRight((this.value/spsl.val()).toFixed(6)));
			spdj.removeAttr("optDj");
			$("#hsbz_"+index).val($("#sfhs").val());
		}else if(spdj.val()!=""){
			spsl.val(delRight((this.value/spdj.val()).toFixed(6)));
		}
		reCountRow(index);
	});
	//˰�ʱ仯����
	$("#fyxms select[id^='sl']").live("change",function(){
		var index = getIndex(this.id);
		$("#spdj_"+index).removeAttr("optDj");
		if($("#je_"+index).val()==""){
			return;
		}
		reCountRow(index);
	});
	//�ۿۼ���
	$("#zkl").keyup(function(){
		$("#zkje").val(($("#spyje").text()*this.value/100).toFixed(2));
	}).each(function(i){
		limit_money_input(this);
	});
	$("#zkje").keyup(function(){
		$("#zkl").val((100*this.value/$("#spyje").text()).toFixed(3));
	}).each(function(i){
		limit_money_input(this);
	});
	//�ۿۼ���
	$("#zkhs").change(function(){
		var nowSelectRow = $("#nowSelectRow").val();
		var zkhs = this.value;
		var zje = 0;
		var zse = 0;
		for(var i=0;i<zkhs;i++){
			zje += $("#je_"+(nowSelectRow-i)).val()*1;
			zse += $("#se_"+(nowSelectRow-i)).val()*1;
		}
		$("#spyje").text(zje.toFixed(2));
		$("#spyse").val(zse.toFixed(2));
		if($("#zkl")!=""){
			$("#zkl").trigger("keyup");
		}
	});
	//��Ʊ���ƿ��ߵ��ѡȡ�¼�
	$("#fpfzdt_data td:not(.no_meaning)").live("click",function(){
		$(this).parent().find("button").trigger("click");
	});
	//ѡȡ��˰��
	$("#ghdwmc,#ghdwdm,#ghdwyhzh,#ghdwdzdh").dblclick(selectNsr);
	$("#nsrinfodt_data tr").live("click",function(){
		var div = $(this).find("td:last div");
		$("#ghdwmc,#ghdwdm,#ghdwdzdh,#ghdwyhzh").val(function(){
			return div.children("[data='"+this.id+"']").text();
		});
		nsrinfoDlg.hide();
	});
	//ѡȡ������Ŀ
	$("#fyxminfodt_data tr").live("click",function(){
		var div = $(this).find("td:last div");
		var index = $("#nowSelectRow").val();
		$("#spmc_"+index+",#ggxh_"+index+",#dw_"+index+",#hsbz_"+index+",#sl_"+index).val(function(){
			var id = this.id.substring(0,this.id.indexOf("_"));
			return div.children("[data='"+id+"']").text();
		});
		var sfhs = $("#sfhs").val()=="y";
		var hsbz = div.children("[data='hsbz']").text()=="y";
		var sl = div.children("[data='sl']").text();
		var dj = div.children("[data='spdj']").text();
		if(sfhs&&!hsbz){
			$("#spdj_"+index).attr("optDj",dj).val(delRight((dj*(1+sl*1)).toFixed(6)));
		}else if(!sfhs&&hsbz){
			$("#spdj_"+index).attr("optDj",dj).val(delRight((dj/(1+sl*1)).toFixed(6)));
		}else{
			$("#spdj_"+index).val(delRight(dj));
			$("#spdj_"+index).removeAttr("optDj");
		}
		$("#je_"+index+",#se_"+index+",#spsl_"+index).val("");
		fyxminfoDlg.hide();
	});
	/*
	//���ز�ѯ
	$("#yfpcx").hide();
	$("#fswz").hide();*/

});

//����У��
function validateZsfp(){
	$(".fp-content :text:not(:disabled),textarea").val(function(){return $.trim(this.value);});
	if($("#maxRow").val()==0){
		alert("����ӷ�����Ŀ");
		addRow();
		return false;
	}
	var ghdwmc = $("#ghdwmc")[0];
	if(ghdwmc.value==""){
		alert("������λ���Ʋ���Ϊ��");
		reFocus(ghdwmc);
		return false;
	}else if(countStrLength(ghdwmc.value)>100){
		alert("������λ�������Ϊ100���ַ���50������");
		reFocus(ghdwmc);
		return false;
	}
	var ghdwdm = $("#ghdwdm")[0];
	var regex = /^[a-zA-Z0-9]{15,20}$/;
	/*if(ghdwdm.value==""){
		alert("������λʶ��Ų���Ϊ��");
		reFocus(ghdwdm)
		return false;
	}else */
	if(ghdwdm.value!=""&&!regex.test(ghdwdm.value)){
		alert("������λʶ���Ϊ15-20λ���ֻ��д��ĸ");
		reFocus(ghdwdm);
		return false;
	}
	var ghdwdzdh = $("#ghdwdzdh")[0];
	/*if(ghdwdzdh.value==""){
		alert("������λ��ַ�绰����Ϊ��");
		reFocus(ghdwdzdh);
		return false;
	}else */
	if(ghdwdzdh.value!=""&&countStrLength(ghdwdzdh.value)>100){
		alert("������λ��ַ�绰���Ϊ100���ַ���50������");
		reFocus(ghdwdzdh);
		return false;
	}
	var ghdwyhzh = $("#ghdwyhzh")[0];
	/*if(ghdwyhzh.value==""){
		alert("������λ�����˺Ų���Ϊ��");
		reFocus(ghdwyhzh);
		return false;
	}else */
	if(ghdwyhzh.value!=""&&countStrLength(ghdwyhzh.value)>100){
		alert("������λ�����˺����Ϊ100���ַ���50������");
		reFocus(ghdwyhzh);
		return false;
	}
	var bz = $("#bz")[0];
	if(bz.value.indexOf("?")!=-1){
		alert("��ע�зǷ��ַ�?��ֹ����");
		reFocus(bz);
		return false;
	}else if(countStrLength(bz.value)>138){
		alert("��ע���Ϊ138���ַ���69������");
		reFocus(bz);
		return false;
	}
	var enterNum=0;
	for(var i=0;i<bz.value.length;i++){
		if(bz.value.indexOf("\n",i)<0){
			break;
		}else{
			enterNum++;
			i = bz.value.indexOf("\n",i);
		}
	}
	if(enterNum>1){
		if(!confirm("��ע��Ϣ���д������࣬�����޷�������ʾ����ȷ��\n�Ƿ�����Щ���У���ȷ�ϱ���������ȷ��������")){
			return false;
		}
	}
	var skr = $("#skr")[0];
	if(countStrLength(skr.value)>16){
		alert("�տ������Ϊ16���ַ���8������");
		reFocus(skr);
		return false;
	}
	var fhr = $("#fhr")[0];
	if(countStrLength(fhr.value)>16){
		alert("���������Ϊ16���ַ���8������");
		reFocus(fhr);
		return false;
	}
	var kpr = $("#kpr")[0];
	if(countStrLength(kpr.value)>16){
		alert("��Ʊ�����Ϊ16���ַ���8������");
		reFocus(kpr);
		return false;
	}
	if(countStrLength(kpr.value)==0){
		alert("��Ʊ�˲�����Ϊ�գ�");
		reFocus(kpr);
		return false;
	}
	var spmcs = $("#fyxms :text[id^='spmc']"); 
	for(var i=0;i<spmcs.length;i++){
		var spmc = spmcs[i];
		var index = getIndex(spmc.id);
		var ggxh = $("#ggxh_"+index)[0];
		var dw = $("#dw_"+index)[0];
		var je = $("#je_"+index)[0];
		if(spmc.value==""){
			alert("�����Ӧ˰�������Ʋ���Ϊ��");
			reFocus(spmc);
			return false;
		}else if(countStrLength(spmc.value)>72){
			alert("�����Ӧ˰�����������Ϊ72���ַ���36������");
			reFocus(spmc);
			return false;
		}
		if(countStrLength(ggxh.value)>36){
			alert("����ͺ����Ϊ36���ַ���18������");
			reFocus(ggxh);
			return false;
		}
		if(countStrLength(dw.value)>14){
			alert("��λ���Ϊ14���ַ���7������");
			reFocus(dw);
			return false;
		}
		if(je.value==""){
			alert("����Ϊ��");
			reFocus(je);
			return false;
		}
	}
	return true;
}
//������
function reCountRow(index){
	var sfhs = $("#sfhs").val()=="y";
	var sl = $("#sl_"+index).val()*1;
	var dj = $("#spdj_"+index).val();
	var spsl = $("#spsl_"+index).val();
	var je = $("#je_"+index).val();
	if(sfhs){
		if(sl!=""&&spsl!=""){
			$("#se_"+index).val((dj*spsl*sl/(1+sl)).toFixed(2));
		}else{
			$("#se_"+index).val((je*sl/(1+sl)).toFixed(2));
		}
	}else{
		if(sl!=""&&spsl!=""){
			$("#se_"+index).val((dj*spsl*sl).toFixed(2));
		}else{
			$("#se_"+index).val((je*sl).toFixed(2));
		}
	}
	if($("#se_"+index).val()==0){
		$("#se_"+index).val("0.00");
	}
	reCountHjje();
}
//����ϼƽ��
function reCountHjje(){
	var hjje=0,se=0,jshj=0;
	var sfhs = $("#sfhs").val()=="y";
	$("#fyxms :text[id^='je']").each(function(){
		hjje += $(this).val()*1;
	});
	$("#fyxms :text[id^='se']").each(function(){
		se += $(this).val()*1;
	});
	hjje += $("#hjzkje").text()*1;
	se += $("#hjzkse").text()*1;
	if(sfhs){
		jshj = hjje.toFixed(2);
		hjje = jshj-se;
	}else{
		jshj = (hjje+se).toFixed(2);
	}
	$("#jshj").text("��"+jshj);
	$("#hjje").text("��"+hjje.toFixed(2));
	$("#hjse").text("��"+se.toFixed(2));
	$("#jshjdx").text(je2Upper(jshj));
}
//����ϼ��ۿ۽��
function reCountZkje(){
	var zkzje = 0;
	var zkzse = 0;
	$("#fyxms tr[zkh] td[id^='zkje']").each(function(){
		zkzje += $(this).text()*1;
	});
	$("#fyxms tr[zkh] td[id^='zkse']").each(function(){
		zkzse += $(this).text()*1;
	});
	$("#hjzkje").text(zkzje.toFixed(2));
	$("#hjzkse").text(zkzse.toFixed(2));
	reCountHjje();
}
function validate(n, max, name,xs) {
	if(n==""||n=="0")return true;
	var num = new Number(n);
	var numStr = new String(num);
	if (numStr == "NaN"||n.indexOf("e")!=-1||n.indexOf("E")!=-1
		||num<0||n.charAt(0)=="."||(n.charAt(0)=="0"&&n.charAt(1)!=".")) {
		alert(name + "����������ȷ����");
		return false;
	} else if (num > max) {
		alert(name + "��ֵ�������" + max);
		return false;
	} else if (n.indexOf(".") != -1
			&& n.substring(n.indexOf(".") + 1).length > xs) {
		alert(name + "���ֻ����"+xs+"λС��");
		return false;
	}
	return true;
}
//���»�ȡ����
function reFocus(a,b) {
	if(a==undefined){
		return;
	}
	if(b){
		a.value = "";
	}
	if ($.browser.msie) {
		a.focus();
	} else {
		window.setTimeout(function() {
					a.focus();
				}, 0);
	}
	if(a.value!=""){
		a.value=a.value;
	}
}
//����д
function je2Upper(num) {
	var strOutput = "";
	var strUnit = 'Ǫ��ʰ��Ǫ��ʰ��Ǫ��ʰԲ�Ƿ�';
	num += "00";
	if(num.charAt(0)=="-")num = num.substring(1);
	var intPos = num.indexOf('.');
	if (intPos >= 0)
		num = num.substring(0, intPos) + num.substr(intPos + 1, 2);
	strUnit = strUnit.substr(strUnit.length - num.length);
	for (var i = 0; i < num.length; i++)
		strOutput += '��Ҽ��������½��ƾ�'.substr(num.substr(i, 1), 1)
				+ strUnit.substr(i, 1);
	return strOutput.replace(/������$/, '��').replace(/��[Ǫ��ʰ]/g, '��').replace(
			/��{2,}/g, '��').replace(/��([��|��])/g, '$1').replace(/��+Բ/, 'Բ')
			.replace(/����{0,3}��/, '��').replace(/^Բ/, "��Բ").replace(/���$/, '��');
}
//�ַ������ȣ�һ������Ϊ����Ӣ��
function countStrLength(str){
	if(!str){
		return 0;
	}
	var count = 0;
	for(var i=0;i<str.length;i++){
		var c = str.charCodeAt(i);
		if((c>=0x0001&&c<=0x007e)||c>=0xff9f&&c<=0xff60){
			count++;
		}else{
			count += 2;
		}
	}
	return count;
}
//������ۿ۵�����
function addzk(){
	var index = $("#nowSelectRow").val();
	var td = $("#row_"+index);
	//ɾ���ۿ�
	if("y"==td.attr("haszk")){
		if(confirm("�Ƿ�ɾ����ǰ "+td.attr("zkhs")+" �е��ۿ���Ϣ��")){
			var tr = td.parent().next();
			while(tr.attr("zkh")!="y"){
				tr = tr.next();
			}
			var lastRow = tr.prev();
			tr.remove();
			var zkhs = td.attr("zkhs");
			for(var i=0;i<zkhs;i++){
				lastRow.children("td:first").removeAttr("zkhs").css("background-color","").attr("haszk","n");
				lastRow.find("td :text").removeAttr("readonly");
				lastRow.find("td select").removeAttr("disabled");
				var dblIndex = getIndex(lastRow.find(":text:first").attr("id"));
				addRowDbclick(dblIndex);
				lastRow = lastRow.prev();
			}
			reCountZkje();
			reFocus(td.next().children()[0]);
			toggleZK(false);
			var maxZkRow = $("#maxZkRow");
			checkNowRowCount(false);
			maxZkRow.val(maxZkRow.val()-1);
		}
		return;
	}
	//��������ۿۼ���
	var selectRowSl = $("#sl_"+index).val();
	var canZk = 0;
	for(var i=index;i>0;i--){
		if($("#row_"+i).attr("haszk")!="y"&&$("#sl_"+i).val()==selectRowSl){
			var spmc = $("#spmc_"+i);
			var je = $("#je_"+i);
			if(spmc.val()==""||je.val()==""){
				alert("��д�ۿ�ǰ��������д��Ʒ���ƺͽ�");
				reFocus(spmc.val()==""?spmc[0]:je[0]);
				return;
			}
			canZk++;
		}else{
			break;
		}
	}
	$("#zkhs").empty();
	for(var i=1;i<=canZk;i++){
		$("#zkhs").append("<option value='"+i+"'>"+i+"</option>");
	}
	$("#spyje").text($("#je_"+index).val());
	$("#spyse").val($("#se_"+index).val());
	$("#zkl,#zkje").val("");
	zkDlg.show();
	$("#zkl").focus();
}
//����ۿ�
function checkAddZK(){
	var zkl = $("#zkl");
	var zkje = $("#zkje");
	var zkjeval = new Number(zkje.val()*-1).toFixed(2);
	var maxRowIndex = $("#maxRow").val();
	if(zkje.val()==""||zkl.val()==""||zkjeval==0||maxRowIndex>1?(Math.abs(zkjeval)>$("#spyje").text()):(Math.abs(zkjeval)>=$("#spyje").text())){
		alert("�ۿ۽����ۿ�����д����");
		reFocus($("#zkl")[0]);
	}else{
		var index = $("#nowSelectRow").val();
		var zkhs = $("#zkhs").val();
		var td = $("#row_"+index);
		var zkslval = $("#sl_"+index).val();
		var zkseval = (-$("#spyse").val()*$("#zkl").val()/100).toFixed(2);
		if(zkseval==0){
			zkseval="0.00";
		}
		td.parent().after("<tr zkh='y' index='"+index+"' style='color:red;text-align:right'>" +
				"<td width='14' style='border-right:none;'/>" +
				"<td width='151' style='text-align:left;' id='zknr_"+index+"'>�ۿ�����"+zkhs+
				"��"+(zkl.val()*1).toFixed(3)+"%��</td>" +
				"<td width='84'/><td width='30'/><td width='56'/><td width='56'/>" +
				"<td width='98' style='text-align:right;padding-right:5px;' id='zkje_"+index+"'>"+
				zkjeval +"</td>" +
				"<td width='52' style='text-align:right;' id='zksl_"+index+"'>"+zkslval*100+
				"<span style='margin-right:5px;'>%</span></td>" +
				"<td style='border-right:none;text-align:right;' id='zkse_"+index+"'>"+zkseval + "</td></tr>");
		td.attr("haszk","y");
		for(var i=0;i<zkhs;i++){
			var tr = $("#spmc_"+(index-i)).parents("tr:first");
			tr.find("td :text").attr("readonly","readonly");
			tr.find("td select").attr("disabled","disabled");
			tr.children("td:first").attr("zkhs",zkhs).css("background-color","orange").attr("haszk","y");
			delRowDbclick(index-i);
		}
		//���»�ȡ����
		reFocus($("#spmc_"+index)[0]);
		//ȥ��˫���¼�
		toggleZK(true);
		reCountZkje();
		zkDlg.hide();
		var maxZkRow = $("#maxZkRow");
		maxZkRow.val(maxZkRow.val()*1+1);
		checkNowRowCount(true);
	}
}
//�л��ۿ�״̬
function toggleZK(a){
	if(a){
		$('#zk').children().text('ɾ���ۿ�');
	}else{
		$('#zk').children().text('�����ۿ�');
	}
}
//�л��嵥״̬
function toggleSyqd(){
	var syqd = $("#syqd");
	if(syqd.children().text()=="ȡ���嵥"){
		syqd.children().text('ʹ���嵥');
		$("#qdbz").val("n");
		$("#spmctitle").text("�����Ӧ˰��������");
	}else{
		syqd.children().text('ȡ���嵥');
		$("#qdbz").val("y");
		$("#spmctitle").html("<span style='color:red'>�嵥)</span>�����Ӧ˰��������");
	}
}
//�л��Ƿ�˰
function toggleSfhs(){
	var sfhs_input = $("#sfhs");
	if(sfhs_input.val()=="n"){
		sfhs_input.val("y");
		$("#sfhsstr").text("���(��˰)");
		$("#djsfhsstr").text("����(��)");
		$("#fyxms :text[id^='je']").each(function(){
			if(this.value!=""){
				var index = getIndex(this.id);
				this.value = (this.value*1+$("#se_"+index).val()*1).toFixed(2);
			}
		});
		$("#fyxms :text[id^='spdj']").each(function(){
			if(this.value!=""){
				var optDj = $(this).attr("optDj");
				if(optDj){
					$(this).attr("optDj",this.value);
					this.value=optDj;
				}else{
					$(this).attr("optDj",this.value);
					var index = getIndex(this.id);
					this.value = delRight((this.value*(1+$("#sl_"+index).val()*1)).toFixed(6));
				}
			}
		});
		$("#fyxms tr[zkh] td[id^=zkje]").each(function(){
			var index = getIndex(this.id);
			$(this).text(($(this).text()*1+$("#zkse_"+index).text()*1).toFixed(2));
		});
	}else{
		sfhs_input.val("n");
		$("#sfhsstr").text("���(����˰)");
		$("#djsfhsstr").text("����(��)");
		$("#fyxms :text[id^='je']").each(function(){
			if(this.value!=""){
				var index = getIndex(this.id);
				this.value = (this.value-$("#se_"+index).val()).toFixed(2);
			}
		});
		$("#fyxms :text[id^='spdj']").each(function(){
			if(this.value!=""){
				var optDj = $(this).attr("optDj");
				if(optDj){
					$(this).attr("optDj",this.value);
					this.value=optDj;
				}else{
					$(this).attr("optDj",this.value);
					var index = getIndex(this.id);
					this.value = delRight((this.value/(1+$("#sl_"+index).val()*1)).toFixed(6));
				}
			}
		});
		$("#fyxms tr[zkh] td[id^=zkje]").each(function(){
			var index = getIndex(this.id);
			$(this).text(($(this).text()-$("#zkse_"+index).text()).toFixed(2));
		});
	}
	reCountZkje();
}
//�����
function addRow(p){
	var maxRowIndex = $("#maxRow").val();
	if(maxRowIndex==8&&fskj){
		alert("�ѵ�����������޷���������������Ŀ��");
		return;
	}
	$("#maxRow").val(++maxRowIndex);
	var place = $("#fyxms tr[placeholder]:first");
	if(place[0]){
		place.before(newLine(maxRowIndex));
	}else{
		$("#fyxms tr:not([placeholder]):last").after(newLine(maxRowIndex));
	}
	$("#fyxms tr[placeholder]:last").remove();
	limit_money_input($("#spsl_"+maxRowIndex+",#spdj_"+maxRowIndex+",#je_"+maxRowIndex));
	if(p!="not_focus"){
		reFocus($("#spmc_"+maxRowIndex)[0]);
	}
	//��˫���¼�
	addRowDbclick(maxRowIndex);
	if(maxRowIndex==1){
		delwv.enable();
		zkwv.enable();		
	}
	if(!fskj){
		checkNowRowCount(true);
	}
}
//ɾ����
function delRow(){
	var maxRowIndex = $("#maxRow").val();
	var td = $("#row_"+maxRowIndex);
	if(td.attr("haszk")=="y"){
		alert("����������ۿ���Ϣ������ɾ�����е��ۿ���Ϣ��");
		reFocus($("#spmc_"+maxRowIndex)[0]);
		return;
	}
	if(maxRowIndex<9){
		$("#fyxms").append(newPlaceHolder());
		if(maxRowIndex==1){
			delwv.disable();
			zkwv.disable();
		}
	}
	$(td.parent().remove());
	reCountHjje();
	checkNowRowCount(false);
	$("#maxRow").val(--maxRowIndex);
	reFocus($("#spmc_"+maxRowIndex)[0]);
}
//��ӷ�����Ŀ��˫������ѡ��
function addRowDbclick(index){
	$("#spmc_"+index+",#ggxh_"+index+",#dw_"+index).dblclick(selectFyxm);
}
//����ۿۺ�ɾ���¼�
function delRowDbclick(index){
	$("#spmc_"+index+",#ggxh_"+index+",#dw_"+index).unbind("dblclick");
}
//����Ƿ񳬹�8��
function checkNowRowCount(add){
	if($("#maxRow").val()*1+$("#maxZkRow").val()*1==9){
		if(!add){
			syqdwv.enable();
			return;
		}
		if($("#qdbz").val()=="n"){
			toggleSyqd();
		}
		syqdwv.disable();
		PrimeFaces.cw('Growl', 'widget_j_info', {
						id : 'j_info',
						sticky : false,
						life : 6000,
						msgs : [ {
							summary : 'ע��',
							detail : '����8����ϸ������ʹ���嵥��ӡ��Ʊ�潫ֻ��ʾ����������嵥�����ۿۡ�',
							severity : 'warn'
						} ]
					});
	}
}
//���ռλ��
function newPlaceHolder(){
	return "<tr placeholder='y'><td width='16' style='border-right:none;'></td>" +
		 		"<td class='dynamicinput' width='151'></td><td class='dynamicinput' width='84'></td>" +
		 		"<td class='dynamicinput' width='30'></td><td class='dynamicinput' width='56'></td>" +
		 		"<td class='dynamicinput' width='56'></td><td class='dynamicinput' width='98'></td>" +
		 		"<td width='54'></td><td style='border-right: none;'>" +
		 		"<input disabled='disabled' style='visibility: hidden;'/></td></tr>";
}
//���һ��
function newLine(index){
	return "<tr><td width='16' id='row_"
			+ index + "' style='border-right:none;'" + " rowIndex='" + index+"'>" + index
			+ "</td><td class='dynamicinput' width='151'><input id='spmc_"
			+ index + "' name='spmc_" + index 
			+ "' maxlength='72'/></td><td class='dynamicinput' width='84'><input id='ggxh_" + index
			+ "' name='ggxh_" + index
			+ "' maxlength='36'/></td><td class='dynamicinput' width='30'><input id='dw_" + index
			+ "' name='dw_" + index + "' maxlength='14'/></td><td class='dynamicinput' width='56'><input id='spsl_"
			+ index + "' name='spsl_" + index
			+ "' style='text-align:right;' maxlength='13'/></td><td class='dynamicinput' width='56'><input id='spdj_"
			+ index + "' name='spdj_" + index
			+ "' style='text-align:right;' maxlength='13'/></td><td class='dynamicinput' width='98'><input id='je_"
			+ index + "' name='je_" + index
			+ "' style='text-align:right;' maxlength='16'/></td><td width='54'><select id='sl_"
			+ index + "' name='sl_" + index
			+ "' style='height:22px;width:98%;'>"+szsmStr+"</select></td>"
			+ "<td style='border-right: none;'><input id='se_" + index
			+ "' style='text-align:right;' disabled='disabled'/>" +
			"<input id='hsbz_"+index+"' name='hsbz_"+index+"' " +
			"value='"+$("#sfhs").val()+"' type='hidden'/></td></tr>";
}
//����ǰ������������������
function addExtraInfo(){
	$("#extraDiv").remove();
	var extraDiv = $("<div id='extraDiv'/>").hide();
	$("#zsfpform").append(extraDiv);
	extraDiv.append("<input name='hjzkje' value='"+$("#hjzkje").text()+"'/>");
	extraDiv.append("<input name='hjzkse' value='"+$("#hjzkse").text()+"'/>");
	extraDiv.append("<input name='hjje' value='"+delLeftMoney($("#hjje").text())+"'/>");
	extraDiv.append("<input name='hjse' value='"+delLeftMoney($("#hjse").text())+"'/>");
	extraDiv.append("<input name='jshj' value='"+delLeftMoney($("#jshj").text())+"'/>");
	//�ж��Ƿ�ͳһ˰��
	var slArray = $("#fyxms select[id^=sl]").get();
	var isAll = true;
	for(var i=1;i<slArray.length;i++){
		var index = getIndex(slArray[i].id);
		if(slArray[i].value!=slArray[0].value){
			isAll=false;
			break;
		}
	}
	if(isAll){
		extraDiv.append("<input name='allSL' value='"+slArray[0].value+"'/>");
	}
	//׷�ӽ��õ�˰��select
	$("#fyxms select:disabled[id^=sl]").each(function(){
		extraDiv.append("<input name='"+this.name+"' value='"+this.value+"'/>");
	});
	//׷��˰��
	$("#fyxms :text[id^='se']").each(function(){
		//׷���ۿ۱�־
		var index = getIndex(this.id);
		var td = $("#row_"+index);
		extraDiv.append("<input name='zkbz_"+index+"' value='"+(td.attr("haszk")=="y"?"y":"n")+"'/>");
		extraDiv.append("<input name='"+this.id+"' value='"+this.value+"'/>");
	});
	if(!fskj){
		//׷���ۿ���
		var bhshjzkje = 0,sfhs = $("#sfhs").val()=="y";
		$("#fyxms tr[zkh]").each(function(){
			var index = $(this).attr("index");
			var zknr = $("#zknr_"+index).text();
			var zkje = $("#zkje_"+index).text();
			var zksl = delRightPercent($("#zksl_"+index).text())/100;
			var zkse = $("#zkse_"+index).text();
			//��˰ʱ������˰�ۿ۽��
			if(sfhs){
				zkje = (zkje-zkse).toFixed(2);
				bhshjzkje += zkje*1;
			}
			extraDiv.append("<input name='zknr_"+index+"' value='"+zknr+"'/>")
			.append("<input name='zkje_"+index+"' value='"+zkje+"'/>")
			.append("<input name='zksl_"+index+"' value='"+zksl+"'/>")
			.append("<input name='zkse_"+index+"' value='"+zkse+"'/>");
		});
		//׷�Ӻ�˰ʱ�Ĳ���˰�ۿ��ܽ��
		if(sfhs){
			extraDiv.append("<input name='bhshjzkje' value='"+bhshjzkje.toFixed(2)+"'/>");
		}
	}else{
		//��Ӹ�������״̬
		extraDiv.append("<input name='fskj' id='fskj' value='1'/>");
	}
	//ת����˰���
	if(sfhs){
		$("#fyxms :text[id^='je']").each(function(){
			var index = getIndex(this.id);
			var bhsje = $("#je_"+index).val()-$("#se_"+index).val();
			extraDiv.append("<input name='bhsje_"+index+"' value='"+bhsje.toFixed(2)+"'/>");
		});
		$("#fyxms :text[id^=spdj]").each(function(){
			var realDj=this.value,index = getIndex(this.id);
			if(realDj!=""){
				if($(this).attr("optDj")){
					realDj = $(this).attr("optDj");
				}else{
					realDj = delRight((this.value/(1+$("#sl_"+index).val()*1)).toFixed(2))
				}
			}
			extraDiv.append("<input name='realdj_"+index+"' value='"+realDj+"'/>");
		})
	}
	//�������庬˰���к�˰��־��һ��ʱ��ʵ�ʵ���
	//	$("#fyxms :text[id^=spdj]").each(function(){
	//		var realDj=this.value,index = getIndex(this.id);
	//		if(realDj!=""&&sfhs!=($("#hsbz_"+index).val()=="y")){
	//			if($(this).attr("optDj")){
	//				realDj = $(this).attr("optDj");
	//			}else if(sfhs){
	//				realDj = delRight((this.value/(1+$("#sl_"+index).val()*1)).toFixed(2));
	//			}else{
	//				realDj = delRight((this.value*(1+$("#sl_"+index).val()*1)).toFixed(2));
	//			}
	//		}
	//		extraDiv.append("<input name='realdj_"+index+"' value='"+realDj+"'/>");
	//	});
}
//ɾ�����һ���ַ�
function delLeftMoney(src){
	src = $.trim(src);
	return src.substring(1,src.length);
}
//ɾ�������ұߵ���
function delRight(src){
	var temp = src;
	while((temp.charAt(temp.length-1)=="0"&&temp.indexOf(".")!=-1)||
			temp=="0"||temp.charAt(temp.length-1)=='.'){
		temp = delRightPercent(temp);
	}
	return temp;
}
//ɾ���ұ�һ���ַ�
function delRightPercent(src){
	src = $.trim(src);
	return src.substring(0,src.length-1);
}
//��ȡ���
function getIndex(src){
	return src.substring(src.indexOf("_")+1,src.length);
}
//���߳ɹ�
function kjcg(args){
	if(args.success==true){
		$.fpewm=args.ewm;
		$('#dy,#xyz,#yl').show();
		$('.fp-content-center :input').attr('disabled','disabled');
		addwv.disable();
		delwv.disable();
		zkwv.disable();
		sfhswv.disable();
		syqdwv.disable();
		fzkjwv.disable();
	}else{
		kjbt.enable();
	}
}
/*
//�����߸�����Ҫ��״̬
function preFskj(dr){
	//fskjDlg1.hide();
	$("#fswz").show();
//	var fyxm_table = $("#fyxms");
//	fyxm_table.empty();
//	for (var index = 1; index <= 8; index++) {
//		fyxm_table.append(newPlaceHolder());
//	}
	var maxRow = $("#maxRow").val();
	for(var i=1;i<=maxRow;i++){
		//delRow();
		var spsl = $("#spsl_"+i).val();
		spsl = "-" + spsl ;
		$("#spsl_"+i).val(spsl);
		
		var je = $("#je_"+i).val();
		je = "-" + je ;
		$("#je_"+i).val(je);
		
		var se = $("#se_"+i).val();
		se = "-" + se ;
		$("#se_"+i).val(se);
	}
	reCountHjje();
	$("#maxZkRow,#nowSelectRow").val(0);
	$("#jshjdx").before("") ;
	$("#spmctitle").text("�����Ӧ˰��������");
	$("#qdbz").val("n");
	//$("#bz").before("<span style='color:red'>���ַ�Ʊ��Ϣ���ţ�1111111111111111</span>").attr("rows","3");
	fskj = true;
}
*/
//���ƿ���ѡȡ
function selectFzkj(args){
	if(args){
		syqdwv.enable();
		var fpmx = $.parseJSON(args.fpmx);
		$("#ghdwmc,#ghdwdm,#ghdwdzdh,#ghdwyhzh,#bz,#skr,#fhr,#kpr").val(function(){return fpmx[this.id];});
		var fyxm_table = $("#fyxms");
		fyxm_table.empty();
		for (var index = 1; index <= 8; index++) {
			fyxm_table.append(newPlaceHolder());
		}
		$("#maxRow,#maxZkRow,#nowSelectRow").val(0);
		var mxzb_old = fpmx.mxzb;
		for(var i = 0;i<mxzb_old.length;i++){
			var fyxm = mxzb_old[i];
			var sfhs = $("#sfhs").val()=="y";
			if(fyxm.fphxz==0){
				addRow("not_focus");
				fillValue(fyxm);
			}else if(fyxm.fphxz==1){
				var index = $("#maxRow").val();
				$("#row_"+index).parent().after("<tr zkh='y' index='"+index+"' style='color:red;text-align:right'>" +
				"<td width='14' style='border-right:none;'/>" +
				"<td width='151' style='text-align:left;' id='zknr_"+index+"'>"+fyxm.spmc+"</td>" +
				"<td width='84'/><td width='30'/><td width='56'/><td width='56'/>" +
				"<td width='98' style='text-align:right;padding-right:5px;' id='zkje_"+index+"'>"+
				(sfhs?(fyxm.je*(1+fyxm.sl)).toFixed(2):fyxm.je) +"</td>" +
				"<td width='52' style='text-align:right;' id='zksl_"+index+"'>"+fyxm.sl*100+
				"<span style='margin-right:5px;'>%</span></td>" +
				"<td style='border-right:none;text-align:right;' id='zkse_"+index+"'>"+fyxm.se + "</td></tr>");
				var zkhs;
				if(fyxm.spmc.indexOf("��")==-1){
					zkhs = fyxm.spmc.substring(4,fyxm.spmc.indexOf("("))*1;
				}else{
					zkhs = fyxm.spmc.substring(4,fyxm.spmc.indexOf("��"))*1;
				}
				for(var j=0;j<zkhs;j++){
					var tr = $("#spmc_"+(index-j)).parents("tr:first");
					tr.find("td :text").attr("readonly","readonly");
					tr.find("td select").attr("disabled","disabled");
					tr.children("td:first").attr("zkhs",zkhs).css("background-color","orange").attr("haszk","y");
					delRowDbclick(index-j);
				}
				var maxZkRow = $("#maxZkRow");
				maxZkRow.val(maxZkRow.val()*1+1);
			}else if(fyxm.fphxz==2){
				addRow("not_focus");
				fillValue(fyxm);
			}
		}
		reCountZkje();
		var qdbzPlusQdbz = fpmx.qdbz+$("#qdbz").val();
		if("0n"!=qdbzPlusQdbz&&"1y"!=qdbzPlusQdbz){
			toggleSyqd();
		}
		fpfzDlg.hide();
		reFocus($("#spmc_"+$("#maxRow").val())[0]);
	}
}
function fillValue(f){
	var index = $("#maxRow").val();
	var sfhs = $("#sfhs").val()=="y";
	$.each(['spmc','ggxh','dw','sl'],function(i,n){
		$("#"+n+"_"+index).val(f[n]);
	});
	$("#spsl_"+index).val(delRight(f.spsl+""));
	$("#se_"+index).val(f.se.toFixed(2));
	$("#hsbz_"+index).val(f.hsbz?"1":"0");
	var spdj = $("#spdj_"+index);
	if(sfhs){
		$("#je_"+index).val((f.je+f.se).toFixed(2));
//		if(f.hsbz){
//			spdj.val(f.spdj).attr("optDj",f.spdj/(1+f.sl));
//		}else{
		spdj.val(delRight((f.spdj*(1+f.sl)).toFixed(6)));
//		}
	}else{
		$("#je_"+index).val(f.je.toFixed(2));
		spdj.val(delRight(f.spdj.toFixed(6)));
//		if(f.hsbz){
//			spdj.val((f.spdj/(1+f.sl)).toFixed(6)).attr("optDj",f.spdj);
//		}else{
//			spdj.val(f.spdj).attr("optDj",f.spdj/(1+f.sl));
//		}
	}
//	spdj.val(delRight(spdj.val())).attr("optDj",delRight(spdj.attr("optDj")));
}
//��ӡ
var defaultZbj = 6,defaultYbj = -6;

function initbjFromCookie(){
	var zbj = $.cookie("zbj_zzsp");
	if(!zbj){
		zbj = defaultZbj;
	}
	var ybj = $.cookie("ybj_zzsp");
	if(!ybj){
		ybj = defaultYbj;
	}
	$("#zbj").val(zbj);
	$("#ybj").val(ybj);
}
function printfp(){
	if($("#zbj").val()==defaultZbj||$("#ybj")==defaultYbj){
		if(!confirm("��ǰ��ӡ�߾�ΪĬ�����ã���ȷ���Ƿ���Ҫ�������ã�\n��ȷ�ϴ����ã�����ȷ��������ӡ��")){
			return false;
		}
	}
	dyDlg.hide();
	dywv.disable();
	ylwv.disable();
	initPrintData();
	LODOP.PRINT();
}
function printfpA(){
	if($("#zbj").val()==defaultZbj&&$("#ybj")==defaultYbj){
		if(!confirm("��ǰ��ӡ�߾�ΪĬ�����ã���ȷ���Ƿ���Ҫ�������ã�\n��ȷ�ϴ����ã�����ȷ��������ӡ��")){
			return false;
		}
	}
	dyDlg.hide();
	dywv.disable();
	ylwv.disable();
	initPrintData();
	LODOP.PRINTA();
}
function initYLData(){
	initPrintData(true);
	LODOP.SET_PREVIEW_WINDOW(1,0,0,890,700,"");
	LODOP.SET_SHOW_MODE("HIDE_PBUTTIN_PREVIEW",1);
	LODOP.PREVIEW();
}
function initPrintData(yl){
	LODOP.PRINT_INITA((yl?defaultZbj:$("#zbj").val())+"mm",(yl?defaultYbj:$("#ybj").val())+"mm","230mm","159mm","��ֵ˰��ͨ��Ʊ");
	LODOP.SET_PRINT_PAGESIZE(1,2300,1590,"CreateCustomPage");
	if(!yl){
		$.cookie("zbj_zzsp",$("#zbj").val(),{path:"/",expires:100000});
		$.cookie("ybj_zzsp",$("#ybj").val(),{path:"/",expires:100000});
	}
	LODOP.ADD_PRINT_TEXT("6.9mm","98mm","58.2mm","5mm",$("#province").text()+"��ֵ˰��ͨ��Ʊ");
	LODOP.SET_PRINT_STYLEA(0,"FontName","���Ŀ���");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",16);
	LODOP.SET_PRINT_STYLEA(0,"FontColor","#008000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.SET_PRINT_STYLEA(0,"LetterSpacing",2);
	LODOP.ADD_PRINT_TEXT(42,159,132,15,$("#fpdm").text());
	LODOP.SET_PRINT_STYLEA(0,"FontName","����");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",15);
	LODOP.SET_PRINT_STYLEA(0,"FontColor","#FF0000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.SET_PRINT_STYLEA(0,"LetterSpacing",2);
	LODOP.ADD_PRINT_SHAPE(1,"16.9mm","86.5mm","65.1mm","1",0,1,"#008000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.ADD_PRINT_SHAPE(0,"18mm","86.5mm","65.1mm","1",0,1,"#008000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.ADD_PRINT_TEXT(42,634,113,20,$("#fphm").text());
	LODOP.SET_PRINT_STYLEA(0,"FontName","����");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",15);
	LODOP.SET_PRINT_STYLEA(0,"FontColor","#FF0000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.ADD_PRINT_TEXT(52,778,78,15,$("#fpdm").text());
	LODOP.ADD_PRINT_TEXT(66,762,87,15,$("#fphm").text());
	LODOP.SET_PRINT_STYLEA(0,"FontName","Courier New");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	LODOP.ADD_PRINT_IMAGE(39,595,26,24,"data:image/jpeg;base64,/9j/" +
			"4AAQSkZJRgABAAEAYABgAAD//gAfTEVBRCBUZWNobm9sb2dpZXMgSW5jLiBWMS4wMQD/2wCEAAgFB" +
			"gcGBQgHBgcJCAgJDBQNDAsLDBgREg4UHRkeHhwZHBsgJC4nICIrIhscKDYoKy8xMzQzHyY4PDgyPC" +
			"4yMzEBCAkJDAoMFw0NFzEhHCExMTExMTExMTExMTExMTExMTExMTExMTExMTExMTExMTExMTExMTE" +
			"xMTExMTExMTExMf/EAaIAAAEFAQEBAQEBAAAAAAAAAAABAgMEBQYHCAkKCwEAAwEBAQEBAQEBAQAAA" +
			"AAAAAECAwQFBgcICQoLEAACAQMDAgQDBQUEBAAAAX0BAgMABBEFEiExQQYTUWEHInEUMoGRoQgjQrH" +
			"BFVLR8CQzYnKCCQoWFxgZGiUmJygpKjQ1Njc4OTpDREVGR0hJSlNUVVZXWFlaY2RlZmdoaWpzdHV2d3h" +
			"5eoOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4eLj5OX" +
			"m5+jp6vHy8/T19vf4+foRAAIBAgQEAwQHBQQEAAECdwABAgMRBAUhMQYSQVEHYXETIjKBCBRCkaGxwQ" +
			"kjM1LwFWJy0QoWJDThJfEXGBkaJicoKSo1Njc4OTpDREVGR0hJSlNUVVZXWFlaY2RlZmdoaWpzdHV2d3" +
			"h5eoKDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uLj5OXm" +
			"5+jp6vLz9PX29/j5+v/AABEIABoAGwMBEQACEQEDEQH/2gAMAwEAAhEDEQA/APQvil421rwNBHqUOjWt" +
			"9pG5I5JTcFZUY5/hxjHHXNAEnjfx+2j+CbLXvD9qmpSalJFHaRsThi4yM457Yx60AbvgnV9R1rw1Z3+s6" +
			"c2mX0ynzbZgRtIOM4PIz1waANygDz748WsV94Iis5yVjuNRtoiw6gM+CfyJoA8dstRvtBurH4f6vvabS/" +
			"ENvNbNjhoixzj25DD/AHqAOhu/GHiWbw3q3jmDWbiB7HVvs8engj7P5A4Klcck+vWgD3uwn+02NvcYx5s" +
			"Svj0yAaAPJ/iPqHifXPHdt4QttEJ0pbq0ukvgrYAQh3JPTHUY9vegC78WfA0mp+JfDniXSoDJd2V7Cl0qd" +
			"Wi3ghv+A/yPtQBx2qaeTq15aJ4W8U/2JNqH2ybTkjj8uWUHqG6hT1xQB79bY+zRYjMQ2DCH+HjpQBLQAUAFABQB/9k=");
	LODOP.SET_PRINT_STYLEA(0,"Stretch",2);
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.ADD_PRINT_TEXT(74,148,162,15,"������ţ�");
	LODOP.SET_PRINT_STYLEA(0,"LetterSpacing",1);
	LODOP.ADD_PRINT_TEXT(92,145,147,15,$("#jqbh").text());
	LODOP.SET_PRINT_STYLEA(0,"FontName","Courier New");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	LODOP.ADD_PRINT_TEXT("23.3mm","168mm","19.6mm","4.5mm","��Ʊ���ڣ�");
	LODOP.SET_PRINT_STYLEA(0,"FontName","���Ŀ���");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"FontColor","#008000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.ADD_PRINT_TEXT("23.3mm",714,121,15,$("#kprq").text());
	LODOP.SET_PRINT_STYLEA(0,"FontName","Courier New");
	LODOP.ADD_PRINT_SHAPE(2,"29.9mm","20.1mm","201.1mm","95mm",0,1,"#008000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.ADD_PRINT_SHAPE(2,"29.9mm","20.1mm","201.1mm","22mm",0,1,"#008000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.ADD_PRINT_SHAPE(2,"29.9mm","20.1mm","7.4mm","22mm",0,1,"#008000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.ADD_PRINT_SHAPE(2,"29.9mm","136mm","5mm","22mm",0,1,"#008000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.ADD_PRINT_SHAPE(2,"51.6mm","20.1mm","201.1mm","54mm",0,1,"#008000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.ADD_PRINT_SHAPE(1,196,276,1,202,0,1,"#008000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.ADD_PRINT_SHAPE(1,195,372,1,169,0,1,"#008000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.ADD_PRINT_SHAPE(1,195,419,1,169,0,1,"#008000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.ADD_PRINT_SHAPE(1,196,495,1,169,0,1,"#008000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.ADD_PRINT_SHAPE(1,195,571,1,169,0,1,"#008000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.ADD_PRINT_SHAPE(1,195,684,1,169,0,1,"#008000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.ADD_PRINT_SHAPE(1,196,726,1,169,0,1,"#008000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.ADD_PRINT_SHAPE(2,"105.3mm","20.1mm","7.4mm","19.6mm",0,1,"#008000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.ADD_PRINT_SHAPE(2,"105.3mm","136mm","5mm","19.6mm",0,1,"#008000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.ADD_PRINT_TEXT(120,84,18,76,"������λ");
	LODOP.SET_PRINT_STYLEA(0,"FontName","����");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"FontColor","#008000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.SET_PRINT_STYLEA(0,"LineSpacing",1);
	LODOP.ADD_PRINT_TEXT(118,107,114,20,"��        �ƣ�");
	LODOP.SET_PRINT_STYLEA(0,"FontName","����");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"FontColor","#008000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.ADD_PRINT_TEXT(136,107,114,20,"��˰��ʶ��ţ�");
	LODOP.SET_PRINT_STYLEA(0,"FontName","����");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"FontColor","#008000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.ADD_PRINT_TEXT(155,107,58,20,"��ַ��");
	LODOP.SET_PRINT_STYLEA(0,"FontName","����");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"FontColor","#008000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.SET_PRINT_STYLEA(0,"LetterSpacing",3);
	LODOP.ADD_PRINT_TEXT(155,157,62,20,"�绰��");
	LODOP.SET_PRINT_STYLEA(0,"FontName","����");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"FontColor","#008000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.SET_PRINT_STYLEA(0,"LetterSpacing",3);
	LODOP.ADD_PRINT_TEXT(175,107,111,20,"�����м��˺ţ�");
	LODOP.SET_PRINT_STYLEA(0,"FontName","����");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"FontColor","#008000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.ADD_PRINT_TEXT(123,517,18,71,"������");
	LODOP.SET_PRINT_STYLEA(0,"FontName","����");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"FontColor","#008000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.SET_PRINT_STYLEA(0,"LineSpacing",6);
	LODOP.ADD_PRINT_TEXT(200,108,155,17,"�����Ӧ˰��������");
	LODOP.SET_PRINT_STYLEA(0,"FontName","����");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"FontColor","#008000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.SET_PRINT_STYLEA(0,"LetterSpacing",2);
	LODOP.ADD_PRINT_TEXT(200,295,86,17,"����ͺ�");
	LODOP.SET_PRINT_STYLEA(0,"FontName","����");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"FontColor","#008000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.SET_PRINT_STYLEA(0,"LetterSpacing",2);
	LODOP.ADD_PRINT_TEXT(200,382,60,17,"��λ");
	LODOP.SET_PRINT_STYLEA(0,"FontName","����");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"FontColor","#008000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.SET_PRINT_STYLEA(0,"LetterSpacing",2);
	LODOP.ADD_PRINT_TEXT(200,438,61,17,"����");
	LODOP.SET_PRINT_STYLEA(0,"FontName","����");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"FontColor","#008000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.SET_PRINT_STYLEA(0,"LetterSpacing",12);
	LODOP.ADD_PRINT_TEXT(200,512,80,17,"����");
	LODOP.SET_PRINT_STYLEA(0,"FontName","����");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"FontColor","#008000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.SET_PRINT_STYLEA(0,"LetterSpacing",12);
	LODOP.ADD_PRINT_TEXT(200,602,90,17,"���");
	LODOP.SET_PRINT_STYLEA(0,"FontName","����");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"FontColor","#008000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.SET_PRINT_STYLEA(0,"LetterSpacing",24);
	LODOP.ADD_PRINT_TEXT(200,691,60,17,"˰��");
	LODOP.SET_PRINT_STYLEA(0,"FontName","����");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"FontColor","#008000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.SET_PRINT_STYLEA(0,"LetterSpacing",2);
	LODOP.ADD_PRINT_TEXT(200,755,90,17,"˰��");
	LODOP.SET_PRINT_STYLEA(0,"FontName","����");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"FontColor","#008000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.SET_PRINT_STYLEA(0,"LetterSpacing",24);
	LODOP.ADD_PRINT_SHAPE(0,364,77,758,1,0,1,"#008000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.ADD_PRINT_TEXT(348,127,103,17,"��         ��");
	LODOP.SET_PRINT_STYLEA(0,"FontName","����");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"FontColor","#008000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.ADD_PRINT_TEXT(375,115,146,17,"��˰�ϼƣ���д��");
	LODOP.SET_PRINT_STYLEA(0,"FontName","����");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"FontColor","#008000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.SET_PRINT_STYLEA(0,"LetterSpacing",2);

	LODOP.ADD_PRINT_TEXT(374,288,15,15,"��");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	LODOP.ADD_PRINT_TEXT(374,288,15,15,"��");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	
	LODOP.ADD_PRINT_TEXT(375,620,65,17,"��Сд��");
	LODOP.SET_PRINT_STYLEA(0,"FontName","����");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"FontColor","#008000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.ADD_PRINT_TEXT(405,84,18,71,"������λ");
	LODOP.SET_PRINT_STYLEA(0,"FontName","����");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"FontColor","#008000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.SET_PRINT_STYLEA(0,"LineSpacing",-2);
	LODOP.ADD_PRINT_TEXT(404,107,109,20,"��        �ƣ�");
	LODOP.SET_PRINT_STYLEA(0,"FontName","����");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"FontColor","#008000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.ADD_PRINT_TEXT(420,107,108,20,"��˰��ʶ��ţ�");
	LODOP.SET_PRINT_STYLEA(0,"FontName","����");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"FontColor","#008000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.ADD_PRINT_TEXT(439,107,58,20,"��ַ��");
	LODOP.SET_PRINT_STYLEA(0,"FontName","����");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"FontColor","#008000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.SET_PRINT_STYLEA(0,"LetterSpacing",3);
	LODOP.ADD_PRINT_TEXT(439,157,61,20,"�绰��");
	LODOP.SET_PRINT_STYLEA(0,"FontName","����");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"FontColor","#008000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.SET_PRINT_STYLEA(0,"LetterSpacing",3);
	LODOP.ADD_PRINT_TEXT(454,107,109,20,"�����м��˺ţ�");
	LODOP.SET_PRINT_STYLEA(0,"FontName","����");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"FontColor","#008000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.ADD_PRINT_TEXT(411,518,18,59,"��\n\nע");
	LODOP.SET_PRINT_STYLEA(0,"FontName","����");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"FontColor","#008000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.ADD_PRINT_TEXT(476,90,68,19,"�տ��ˣ�");
	LODOP.SET_PRINT_STYLEA(0,"FontName","����");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"FontColor","#008000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.ADD_PRINT_TEXT(476,306,63,19,"���ˣ�");
	LODOP.SET_PRINT_STYLEA(0,"FontName","����");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"FontColor","#008000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.ADD_PRINT_TEXT(476,474,68,19,"��Ʊ�ˣ�");
	LODOP.SET_PRINT_STYLEA(0,"FontName","����");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"FontColor","#008000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	LODOP.ADD_PRINT_TEXT(476,626,130,19,"������λ�����£�");
	LODOP.SET_PRINT_STYLEA(0,"FontName","����");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"FontColor","#008000");
	LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);
	if($.ewmbz){
	LODOP.ADD_PRINT_IMAGE(33,78,66,66,"data:image/png;base64,"+$.fpewm);
	LODOP.SET_PRINT_STYLEA(0,"Stretch",2);
	}
	var ghdwmc = $("#ghdwmc").val();
	var ghdwmcLen = countStrLength(ghdwmc);
	if(ghdwmcLen<=50){
		LODOP.ADD_PRINT_TEXT(119,208,315,16,ghdwmc);
	}else if(ghdwmcLen<=56){
		LODOP.ADD_PRINT_TEXT(115,208,315,16,ghdwmc);
		LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
		LODOP.SET_PRINT_STYLEA(0,"LineSpacing",-5);
	}else if(ghdwmcLen<=64){
		if(ghdwmcLen<=60){
			LODOP.ADD_PRINT_TEXT(119,208,315,16,ghdwmc);
		}else{
			LODOP.ADD_PRINT_TEXT(116,208,315,16,ghdwmc);
			LODOP.SET_PRINT_STYLEA(0,"LineSpacing",-4);
		}
		LODOP.SET_PRINT_STYLEA(0,"FontSize",7);
	}else{
		if(ghdwmcLen<=75){
			LODOP.ADD_PRINT_TEXT(119,208,315,16,ghdwmc);
		}else{
			LODOP.ADD_PRINT_TEXT(117,208,315,16,ghdwmc);
			LODOP.SET_PRINT_STYLEA(0,"LineSpacing",-3);
		}
		LODOP.SET_PRINT_STYLEA(0,"FontSize",6);
	}
	LODOP.ADD_PRINT_TEXT(135,225,290,15,$("#ghdwdm").val());
	LODOP.SET_PRINT_STYLEA(0,"FontName","Courier New");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
	var ghdwdzdh = $("#ghdwdzdh").val();
	var ghdwdzdhLen = countStrLength(ghdwdzdh);
	if(ghdwdzdhLen<=50){
		LODOP.ADD_PRINT_TEXT(156,208,315,16,ghdwdzdh);
	}else if(ghdwdzdhLen<=56){
		LODOP.ADD_PRINT_TEXT(151,208,315,16,ghdwdzdh);
		LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
		LODOP.SET_PRINT_STYLEA(0,"LineSpacing",-5);
	}else if(ghdwdzdhLen<=64){
		if(ghdwdzdhLen<=60){
			LODOP.ADD_PRINT_TEXT(156,208,315,16,ghdwdzdh);
		}else{
			LODOP.ADD_PRINT_TEXT(152,208,315,16,ghdwdzdh);
			LODOP.SET_PRINT_STYLEA(0,"LineSpacing",-4);
		}
		LODOP.SET_PRINT_STYLEA(0,"FontSize",7);
	}else{
		if(ghdwdzdhLen<=75){
			LODOP.ADD_PRINT_TEXT(156,208,315,16,ghdwdzdh);
		}else{
			LODOP.ADD_PRINT_TEXT(153,208,315,16,ghdwdzdh);
			LODOP.SET_PRINT_STYLEA(0,"LineSpacing",-3);
		}
		LODOP.SET_PRINT_STYLEA(0,"FontSize",6);
	}
	var ghdwyhzh = $("#ghdwyhzh").val();
	var ghdwyhzhLen = countStrLength(ghdwyhzh);
	if(ghdwyhzhLen<=50){
		LODOP.ADD_PRINT_TEXT(175,208,315,16,ghdwyhzh);
	}else if(ghdwyhzhLen<=56){
		LODOP.ADD_PRINT_TEXT(170,208,315,16,ghdwyhzh);
		LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
		LODOP.SET_PRINT_STYLEA(0,"LineSpacing",-5);
	}else if(ghdwyhzhLen<=64){
		if(ghdwyhzhLen<=60){
			LODOP.ADD_PRINT_TEXT(175,208,315,16,ghdwyhzh);
		}else{
			LODOP.ADD_PRINT_TEXT(171,208,315,16,ghdwyhzh);
			LODOP.SET_PRINT_STYLEA(0,"LineSpacing",-4);
		}
		LODOP.SET_PRINT_STYLEA(0,"FontSize",7);
	}else{
		if(ghdwyhzhLen<=75){
			LODOP.ADD_PRINT_TEXT(175,208,315,16,ghdwyhzh);
		}else{
			LODOP.ADD_PRINT_TEXT(172,208,315,16,ghdwyhzh);
			LODOP.SET_PRINT_STYLEA(0,"LineSpacing",-3);
		}
		LODOP.SET_PRINT_STYLEA(0,"FontSize",6);
	}
	LODOP.ADD_PRINT_TEXT(120,544,300,15,$("#skm1").text());
	LODOP.SET_PRINT_STYLEA(0,"FontName","Courier New");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
	LODOP.ADD_PRINT_TEXT(137,544,300,15,$("#skm2").text());
	LODOP.SET_PRINT_STYLEA(0,"FontName","Courier New");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
	LODOP.ADD_PRINT_TEXT(154,544,300,15,$("#skm3").text());
	LODOP.SET_PRINT_STYLEA(0,"FontName","Courier New");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
	LODOP.ADD_PRINT_TEXT(171,544,300,15,$("#skm4").text());
	LODOP.SET_PRINT_STYLEA(0,"FontName","Courier New");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
	var qdbz = $("#qdbz").val()=="y";
	var slFlag = true;
	if(qdbz){
		var mc,gg,dw,spsl,dj,je,sl,se,bhszkzje;
		mc = "����������嵥��";
		var sfhs = $("#sfhs").val()=="y";
		if(sfhs){
			bhszkzje = $("#extraDiv input[name='bhshjzkje']").val();
		}else{
			bhszkzje = $("#extraDiv input[name='hjzkje']").val();
		}
		je = $("#extraDiv input[name='hjje']").val()-bhszkzje;
		var allSL = $("#extraDiv input[name='allSL']");
		if(allSL[0]){
			sl = allSL.val()*100+"%";
		}
		if(sl!="0%"){
			slFlag=false;
		}
		var hjzkse = $("#extraDiv input[name='hjzkse']").val();
		se = $("#extraDiv input[name='hjse']").val()-hjzkse;
		addPrintLine(0,mc,gg,dw,spsl,dj,je.toFixed(2),sl,se.toFixed(2));
		var zkbz = bhszkzje*1!=0;
		if(zkbz){
			mc = "�ۿ�";
			je = bhszkzje;
			se = hjzkse;
			addPrintLine(1,mc,gg,dw,spsl,dj,je,sl,se);
		}
	}else{
		$("#fyxms tr:not([placeholder])").each(function(i,n){
			var iszkh = $(n).attr("zkh")=="y";
			var mc,gg,dw,spsl,dj,je,sl,se;
			if(iszkh){
				var index = getIndex($(n).attr("index"));
				mc = $(n).children("td[id^=zknr]").text();
				je = $("#extraDiv input[name=zkje_"+index+"]").val();
				sl = $(n).children("td[id^=zksl]").text();
				se = $(n).children("td[id^=zkse]").text();
			}else{
				mc = $(n).find(":text[id^=spmc]").val();
				gg = $(n).find(":text[id^=ggxh]").val();
				dw = $(n).find(":text[id^=dw]").val();
				spsl = $(n).find(":text[id^=spsl]").val();
				sl = $(n).find("select[id^=sl]").val()*100+"%";
				se = $(n).find(":text[id^=se]").val();
				dj = $(n).find(":text[id^=spdj]").val();
				var index = getIndex($(n).find(":text[id^=spmc]").attr("id"));
				var sfhs = $("#sfhs").val()=="y";
				if(sfhs){
					dj = $("#extraDiv input[name=realdj_"+index+"]").val();
					je = $("#extraDiv input[name=bhsje_"+index+"]").val();
				}else{
					je = $(n).find(":text[id^=je]").val();
				}
			}
			if(sl!="0%"){
				slFlag=false;
			}
			addPrintLine(i,mc,gg,dw,spsl,dj,je,sl,se);
		});
	}
	var hjje = $("#extraDiv input[name='hjje']").val();
	var hjjeLen = countStrLength(hjje);
	LODOP.ADD_PRINT_TEXT(345,551,129,20,hjje);
	LODOP.SET_PRINT_STYLEA(0,"FontName","Courier New");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	addMoney(346,551+129-hjjeLen*9.2-12);
	LODOP.SET_PRINT_STYLEA(0,"Stretch",2);
	var hjse = $("#extraDiv input[name='hjse']").val();
	var hjseLen = countStrLength(hjse);
	if(slFlag){
		hjse="***";
	}else{
		addMoney(346,696+135-hjseLen*9.2-12);
		LODOP.SET_PRINT_STYLEA(0,"Stretch",2);
	}
	LODOP.ADD_PRINT_TEXT(345,696,135,20,hjse);
	LODOP.SET_PRINT_STYLEA(0,"FontName","Courier New");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.ADD_PRINT_TEXT(376,307,311,20,$("#jshjdx").text());
	addMoney(374,680);
	LODOP.SET_PRINT_STYLEA(0,"Stretch",2);
	LODOP.ADD_PRINT_TEXT(373,695,135,20,$("#extraDiv input[name='jshj']").val());
	LODOP.SET_PRINT_STYLEA(0,"FontName","Courier New");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
	var xhdwmc = $("#xhdwmc").attr("title");
	var xhdwmcLen = countStrLength(xhdwmc);
	if(xhdwmcLen<=50){
		LODOP.ADD_PRINT_TEXT(405,208,315,16,xhdwmc);
	}else if(xhdwmcLen<=56){
		LODOP.ADD_PRINT_TEXT(401,208,315,16,xhdwmc);
		LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
		LODOP.SET_PRINT_STYLEA(0,"LineSpacing",-5);
	}else if(xhdwmcLen<=64){
		if(xhdwmcLen<=60){
			LODOP.ADD_PRINT_TEXT(405,208,315,16,xhdwmc);
		}else{
			LODOP.ADD_PRINT_TEXT(402,208,315,16,xhdwmc);
			LODOP.SET_PRINT_STYLEA(0,"LineSpacing",-4);
		}
		LODOP.SET_PRINT_STYLEA(0,"FontSize",7);
	}else{
		if(xhdwmcLen<=75){
			LODOP.ADD_PRINT_TEXT(405,208,315,16,xhdwmc);
		}else{
			LODOP.ADD_PRINT_TEXT(403,208,315,16,xhdwmc);
			LODOP.SET_PRINT_STYLEA(0,"LineSpacing",-3);
		}
		LODOP.SET_PRINT_STYLEA(0,"FontSize",6);
	}
	LODOP.ADD_PRINT_TEXT(419,225,276,15,$("#xhdwdm").text());
	LODOP.SET_PRINT_STYLEA(0,"FontName","Courier New");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
	var xhdwdzdh = $("#xhdwdzdh").attr("title");
	var xhdwdzdhLen = countStrLength(xhdwdzdh);
	if(xhdwdzdhLen<=50){
		LODOP.ADD_PRINT_TEXT(438,208,315,16,xhdwdzdh);
	}else if(xhdwdzdhLen<=56){
		LODOP.ADD_PRINT_TEXT(434,208,315,16,xhdwdzdh);
		LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
		LODOP.SET_PRINT_STYLEA(0,"LineSpacing",-5);
	}else if(xhdwdzdhLen<=64){
		if(xhdwdzdhLen<=60){
			LODOP.ADD_PRINT_TEXT(438,208,315,16,xhdwdzdh);
		}else{
			LODOP.ADD_PRINT_TEXT(435,208,315,16,xhdwdzdh);
			LODOP.SET_PRINT_STYLEA(0,"LineSpacing",-4);
		}
		LODOP.SET_PRINT_STYLEA(0,"FontSize",7);
	}else{
		if(xhdwdzdhLen<=75){
			LODOP.ADD_PRINT_TEXT(438,208,315,16,xhdwdzdh);
		}else{
			LODOP.ADD_PRINT_TEXT(436,208,315,16,xhdwdzdh);
			LODOP.SET_PRINT_STYLEA(0,"LineSpacing",-3);
		}
		LODOP.SET_PRINT_STYLEA(0,"FontSize",6);
	}
	var xhdwyhzh = $("#xhdwyhzh").attr("title");
	var xhdwyhzhLen = countStrLength(xhdwyhzh);
	if(xhdwyhzhLen<=50){
		LODOP.ADD_PRINT_TEXT(455,208,315,16,xhdwyhzh);
	}else if(xhdwyhzhLen<=56){
		LODOP.ADD_PRINT_TEXT(451,208,315,16,xhdwyhzh);
		LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
		LODOP.SET_PRINT_STYLEA(0,"LineSpacing",-5);
	}else if(xhdwyhzhLen<=64){
		if(xhdwyhzhLen<=60){
			LODOP.ADD_PRINT_TEXT(455,208,315,16,xhdwyhzh);
		}else{
			LODOP.ADD_PRINT_TEXT(452,208,315,16,xhdwyhzh);
			LODOP.SET_PRINT_STYLEA(0,"LineSpacing",-4);
		}
		LODOP.SET_PRINT_STYLEA(0,"FontSize",7);
	}else{
		if(xhdwyhzhLen<=75){
			LODOP.ADD_PRINT_TEXT(455,208,315,16,xhdwyhzh);
		}else{
			LODOP.ADD_PRINT_TEXT(453,208,315,16,xhdwyhzh);
			LODOP.SET_PRINT_STYLEA(0,"LineSpacing",-3);
		}
		LODOP.SET_PRINT_STYLEA(0,"FontSize",6);
	}

	var jym = ""+$("#jym").text();
	jym = jym.substring(0, 5)+" "+jym.substring(5, 10)+" "+jym.substring(10, 15)+" "+jym.substring(15, jym.length);
	LODOP.ADD_PRINT_TEXT(405,539,307,61,"У���� "+jym+"\n"+$("#bz").val());
	LODOP.SET_PRINT_STYLEA(0,"TextNeatRow",true);
	LODOP.ADD_PRINT_TEXT(477,152,152,15,$("#skr").val());
	LODOP.ADD_PRINT_TEXT(477,359,115,15,$("#fhr").val());
	var kpr = $("#kpr").val();
	var kprLen = countStrLength(kpr);
	if(kprLen<=14){
		LODOP.ADD_PRINT_TEXT(476,526,121,15,kpr);
		LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	}else{
		LODOP.ADD_PRINT_TEXT(477,526,121,15,kpr);
	}
}
function addPrintLine(i,mc,gg,dw,spsl,dj,je,sl,se){
	if(mc){
		var mcLen = countStrLength(mc);
		if(mcLen<=30){
			LODOP.ADD_PRINT_TEXT(217+i*17,82,197,20,mc);
		}else if(mcLen<=34){
			LODOP.ADD_PRINT_TEXT(217+i*17,82,197,20,mc);
			LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
			LODOP.SET_PRINT_STYLEA(0,"LineSpacing",-6);
		}else if(mcLen<=40){
			LODOP.ADD_PRINT_TEXT(217+i*17,82,197,20,mc);
			LODOP.SET_PRINT_STYLEA(0,"FontSize",7);
			LODOP.SET_PRINT_STYLEA(0,"LineSpacing",-4);
		}else{
			LODOP.ADD_PRINT_TEXT(214+i*17,82,197,20,mc);
			LODOP.SET_PRINT_STYLEA(0,"FontSize",6);
			LODOP.SET_PRINT_STYLEA(0,"LineSpacing",-3);
		}
	}
	if(gg){
		var ggLen = countStrLength(gg);
		if(ggLen<=14){
			LODOP.ADD_PRINT_TEXT(217+i*17,281,95,20,gg);
		}else if(ggLen<=16){
			LODOP.ADD_PRINT_TEXT(217+i*17,281,95,20,gg);
			LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
			LODOP.SET_PRINT_STYLEA(0,"LineSpacing",-6);
		}else if(ggLen<=18){
			LODOP.ADD_PRINT_TEXT(217+i*17,281,95,20,gg);
			LODOP.SET_PRINT_STYLEA(0,"FontSize",7);
			LODOP.SET_PRINT_STYLEA(0,"LineSpacing",-4);
		}else{
			LODOP.ADD_PRINT_TEXT(214+i*17,281,95,20,gg);
			LODOP.SET_PRINT_STYLEA(0,"FontSize",6);
			LODOP.SET_PRINT_STYLEA(0,"LineSpacing",-3);
		}
	}
	if(dw){
		var dwLen = countStrLength(dw);
		if(dwLen<=6){
			LODOP.ADD_PRINT_TEXT(217+i*17,378,43,20,dw);
		}else if(dwLen<=8){
			LODOP.ADD_PRINT_TEXT(217+i*17,378,43,20,dw);
			LODOP.SET_PRINT_STYLEA(0,"FontSize",7);
			LODOP.SET_PRINT_STYLEA(0,"LineSpacing",-6);
		}else{
			LODOP.ADD_PRINT_TEXT(214+i*17,378,43,20,dw);
			LODOP.SET_PRINT_STYLEA(0,"FontSize",6);
			LODOP.SET_PRINT_STYLEA(0,"LineSpacing",-3);
		}
	}
	if(spsl){
		var spslLen = countStrLength(spsl);
		LODOP.ADD_PRINT_TEXT(217+i*17,405,87,20,spsl);
		if(spslLen>11){
			LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
		}
		LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	}
	if(dj){
		var djLen = countStrLength(dj);
		LODOP.ADD_PRINT_TEXT(217+i*17,496,72,20,dj);
		if(djLen>11){
			LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
		}
		LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	}
	if(je){
		LODOP.ADD_PRINT_TEXT(217+i*17,569,110,20,je);
		LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	}
	if(sl){
		if(sl=="0%"){
			sl="***";
			LODOP.ADD_PRINT_TEXT(217+i*17,684,40,20,sl);
			LODOP.SET_PRINT_STYLEA(0,"FontName","Courier New");
		}else{
			LODOP.ADD_PRINT_TEXT(217+i*17,684,40,20,sl);
		}
		LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	}
	if(se){
		if(sl=="***"){
			se="***";
			LODOP.ADD_PRINT_TEXT(217+i*17,726,104,20,se);
			LODOP.SET_PRINT_STYLEA(0,"FontName","Courier New");
		}else{
			LODOP.ADD_PRINT_TEXT(217+i*17,726,104,20,se);
		}
		LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	}
}

function initForm(){
	
	addwv.enable();
	sfhswv.enable();
	fzkjwv.enable();
	syqdwv.enable();
	
	var maxRow = $("#maxRow").val();
	for(var i=1;i<=maxRow;i++){
		//delRow();
		var spsl = $("#spsl_"+i).val();
		if(spsl.indexOf("-") == 0){
			spsl = spsl.substr(1) ;
		}
		$("#spsl_"+i).val(spsl);
		
		var je = $("#je_"+i).val();
		if(je.indexOf("-") == 0){
			je = je.substr(1) ;
		}
		$("#je_"+i).val(je);
		
		var se = $("#se_"+i).val();
		if(se.indexOf("-") == 0){
			se = se.substr(1) ;
		}
		$("#se_"+i).val(se);
	}
	
	fskj = false;
	
	$("#yfpcx,#fswz").hide();
	
	reCountHjje();
}