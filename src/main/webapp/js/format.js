/**
 * 是否含税价标志（通用类型）
 */
function formatKplx(value,row,index){
	if (value=="004"){
		return "增值税纸质专用发票";
	}else if (value=="007"){
		return "增值税纸质普通发票";
	}
}
/**
 * 格式化发票地区
 */
function formatfpdq(value,row,index){
	if (value=="00"){
		return "北京";
	}else if (value=="01"){
		return "上海";
	}
}
/*
 * 格式化发票客户类型
 */
function formatfpkhlx(value,row,index){
	if (value=="00"){
		return "B2B";
	}else if (value=="01"){
		return "B2C";
	}
}
/*
 * 格式化申请入口
 */
function formatsqrk(value,row,index){
	if (value=="00"){
		return "前台";
	}else if (value=="01"){
		return "后台";
	}
}

function formatArray(value,row,index){
	var arr = value.split(",");
	var result ="";
	if(arr.length>1){
		for(var i= 0;i<arr.length;i++){
			result += arr[i]+"</br>"
		}
		return result;
	}else{
		return value;
	}
}

function formatArrayTime(value,row,index){
	var arr = value.split(",");
	var result ="";
	if(arr.length>1){
		for(var i= 0;i<arr.length;i++){
			result = result + formatKprq(arr[i]) + "</br>";
		}
		return result;
	}else{
		return formatKprq(value);
	}
}



function formatKprq(value,row,index){
	return value.substr(0,10);
}

function formatKprq2(value){
	if(value==null || value =='undefined'){
		return '';
	}
	var dateValue = new Date(value);
	return dateValue.format("yyyy-mm-dd");
}

Date.prototype.format = function (fmt) { //author: meizz
    var o = {
        "m+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "H+": this.getHours(), //小时
        "M+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}


