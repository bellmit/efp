dwr.engine.setAsync(false);//前后台调用同步

//设置异常处理
dwr.engine.setErrorHandler(eh);

var messageRegion = null;

var exception_sign = false;

var exception_msg;
var exception_info;

function showInfomation(msg){
	window.top.showMessage(msg);
}

function messageShow(title,msg,showType,callback){
	$.messager.show({
		title:title,
		msg:msg,
		showType:showType,
		showSpeed:200,
		width:250,
		height:130,
		timeout:0,
		style:{
			right:'',
			top:document.body.scrollTop+document.documentElement.scrollTop+100,
			bottom:''
		}
	});
}

function eh(msg,ex){
	exception_msg = msg;
	exception_info = ex;
	window.top.exceptionMsg = msg;
	window.top.exceptionInfo = ex;
	var s = common.util.createFrame("../../../js/exception/exception.html");
	window.top.getDialog().dialog({
	    title: '异常信息',
	    width: 700,
	    height:140,
	    top:150,
	    closed: false,
	    cache: false,
	    content:s,
	    modal: true
	});

	exception_sign = true;
}

function callCommFunc(func,args){
	var argsArray = Array.prototype.slice.call(arguments);
	//删除第一个元素
	argsArray.shift();
	var result = func.apply(this,argsArray);
//	var result = func(args,callback);
	if(exception_sign==true){
		exception_sign = false;
		throw "msg";
	}
	
	return result;
} 



function messageAlert(title,msg,icon,callback){
	$.messager.alert(title,msg,icon,callback);
}

if (typeof common == 'undefined') common = {};

if (!common.util) common.util = {};

function setMessageRegion(region){//设置统一消息区
	messageRegion = region;
};

function showMessage (text) {//在消息区统一显示消息
	messageRegion.removeClass("div_box");
	messageRegion.removeAttr("style");
	messageRegion.addClass("div_box");
	var htmlText = '<font color="red">'+text+'</font>';
	messageRegion.html(htmlText);
	
	
	messageRegion.animate({
                left: '600px'
            },"slow");
	
};

/**
 * 获取数据
 * @param data
 * @param options
 * @returns
 */
common.util.getFormValues = function(data, options) {
	  if (typeof data == "string" || dwr.util._isHTMLElement(data)) {
	    return dwr.util.getFormValues(data);
	  }else {
	    var prefix = "";
	    var depth = 100;
	    if (options != null && "prefix" in options) prefix = options.prefix;
	    if (options != null && "idPrefix" in options) prefix = options.idPrefix;
	    if (options != null && "depth" in options) depth = options.depth;
	    
	    common.util._getValuesRecursiveNew(data, prefix, depth, options);
	    return data;
	  }
};

common.util._getValuesRecursiveNew = function(data, idpath, depth, options) {
	  if (depth == 0) return;
	  // Array containing objects -> add "[n]" to idpath and make recursive call
	  // for each item object
	  if (dwr.util._isArray(data) && data.length > 0 && dwr.util._isObject(data[0])) {
	    for (var i = 0; i < data.length; i++) {
	    	common.util._getValuesRecursiveNew(data[i], idpath+"["+i+"]", depth-1, options);
	    }
	  }
	  // Object (not array) -> handle nested object properties
	  else if (dwr.util._isObject(data) && !dwr.util._isArray(data)) {
	    for (var prop in data) {
	      var subidpath = idpath ? idpath+"."+prop : prop;
	      
	    //日期类型组件
		  var dateObj = $("input[name='"+prop+"']");
		  var dateObjClass = dateObj.attr("class");
		  if(dateObjClass!=""&&dateObjClass!=null){
			  if(dateObjClass.indexOf("combo-value")>-1){
				  var parentObj = dateObj.parent();
				  var parentClass = parentObj.attr("class");
				  if(parentClass!=""&&parentClass!=null){
					  if(parentClass.indexOf("datebox")>-1){
						  var strTime = dateObj.val();
						  var riqi = null;
						  if(strTime!=""&&strTime!=null){
							  riqi= new Date(Date.parse(strTime.replace(/-/g,   "/"))); //转换成Data();
						  }
			       		  data[prop] = riqi;
			       		  
			       		  continue;
					  }
				  }
				  
			  }
		  }
		  
	      // Object, or array containing objects -> call ourselves recursively
	      if (dwr.util._isObject(data[prop]) && !dwr.util._isArray(data[prop])
	          || dwr.util._isArray(data[prop]) && data[prop].length > 0 && dwr.util._isObject(data[prop][0])) {
	    	
			common.util._getValuesRecursiveNew(data[prop], subidpath, depth-1, options);
	        
	      }
	      // Functions -> skip
	      else if (typeof data[prop] == "function") {
	        // NOP
	      }
	      // Only simple values left (or array of simple values, or empty array)
	      // -> call getValue()
	      else {
	        // Are there any elements with that id or name
	        if (dwr.util.byId(subidpath) != null || document.getElementsByName(subidpath).length >= 1) {
	        	  var dateObj = $("input[name='"+prop+"']");
				  var dateObjClass = dateObj.attr("class");
				  if(dateObjClass!=""&&dateObjClass!=null){
					  if(dateObjClass.indexOf("combo-value")>-1){
						  var str = dateObj.val();
			       		  data[prop] = str;
					  }else{
						  data[prop] = dwr.util.getValue(subidpath);
					  }
				  }else{
					  data[prop] = dwr.util.getValue(subidpath);
				  }
				  
	        }
	      }
	    }
	  }
};

/**
 * Given a map, or a recursive structure consisting of arrays and maps, call 
 * setValue() for all leaf entries and use intermediate levels to form nested
 * element ids.
 * 设置数据
 */
common.util.setFormValues = function(data, options) {
  var prefix = "";
  var depth = 100;
  if (options && "prefix" in options) prefix = options.prefix;
  if (options && "idPrefix" in options) prefix = options.idPrefix;
  if (options && "depth" in options) depth = options.depth;
  common.util._setValuesRecursive(data, prefix, depth, options);
};

/**
 * @private Recursive helper for setValues()
 */
common.util._setValuesRecursive = function(data, idpath, depth, options) {
  if (depth == 0) return;
  // Array containing objects -> add "[n]" to prefix and make recursive call
  // for each item object
  if (dwr.util._isArray(data) && data.length > 0 && dwr.util._isObject(data[0])) {
    for (var i = 0; i < data.length; i++) {
      dwr.util._setValuesRecursive(data[i], idpath+"["+i+"]", depth-1, options);
    }
  }
  // Object (not array) -> handle nested object properties
  else if (dwr.util._isObject(data) && !dwr.util._isArray(data)) {
    for (var prop in data) {
      var subidpath = idpath ? idpath+"."+prop : prop;
      // Object (not array), or array containing objects -> call ourselves recursively
      if (dwr.util._isObject(data[prop]) && !dwr.util._isArray(data[prop])
          || dwr.util._isArray(data[prop]) && data[prop].length > 0 && dwr.util._isObject(data[prop][0])) {
    	  
    	//判断是否是日期类型
		if(dwr.util._isDate(data[prop])){
			  var dateObj = $("input[name='"+prop+"']");
			  var dateObjClass = dateObj.attr("class");
			  if(dateObjClass!=null){
				  if(dateObjClass.indexOf("combo-value")>-1){
					  var riqi = data[prop];
						  var parentObj = dateObj.parent();
						  var parentPre = parentObj.prev();
						  var parentOptions = parentPre.attr("data-options");
						  if(parentOptions!=""&&parentOptions!=null){//required:true,formatter:common.util.formatDate
							  var optionsArray = parentOptions.split(",");
							  for(var i=0;i<optionsArray.length;i++){
								  var strOpt = optionsArray[i];
								  if(strOpt.indexOf("formatter")>-1){
									  var strOptFormatter = strOpt.split(":")[1];
									  var myformatters = eval(strOptFormatter);
									  var str = myformatters(riqi);
									  parentPre.datebox('setValue', str);
									  break;
								  }
							  }
							  
						  }
				  }
			  }
			  
			  
		}else{
			common.util._setValuesRecursive(data[prop], subidpath, depth-1, options);
		}
			
      }
      // Functions -> skip
      else if (typeof data[prop] == "function") {
        // NOP
      }
      // Only simple values left (or array of simple values, or empty array)
      // -> call setValue()
      else {
        // Are there any elements with that id or name
        if (dwr.util.byId(subidpath) != null || document.getElementsByName(subidpath).length >= 1) {
        	 var dateObj = $("input[name='"+prop+"']");
			  var dateObjClass = dateObj.attr("class");
			  if(dateObjClass!=""&&dateObjClass!=null){
				  if(dateObjClass.indexOf("combo-value")>-1){
		       		  dateObj.val(data[prop]);
				  }else{
					  dwr.util.setValue(subidpath, data[prop], options);
				  }
			  }else{
				  dwr.util.setValue(subidpath, data[prop], options);
			  }
			  
        }
      }
    }
  }
};
	
	
//创建一个iframe
common.util.createFrame = function(url) {
	var s = '<iframe name="dlgFrame" scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
	return s;
};

//替换(dialog)样式
common.util.changeStyle = function(){
	var parentThemes = window.top.styleThemes;
	changeStylesComm($("#easyuiTheme"),parentThemes);
//	$("#easyuiTheme").attr("href",parentThemes);
};

//替换(子窗口)样式
common.util.changeWinStyle = function(){
	var parentThemes = window.top.styleThemes;
	$("#easyuiTheme").attr("href",parentThemes);
};

common.util.deleteTableRows = function(table,ids){//表哥需要倒序删除，否则顺序删除行号有问题
	for(var i=ids.length-1;i>=0;i--){
		$(table).datagrid('deleteRow',ids[i]);
	}
};

common.util.formatDate = function(date){
	if (date==""||date==null)
		return "";
	if(isDate(date)){
		var y = date.getFullYear();
		var m = date.getMonth()+1;
		var d = date.getDate();
		if(parseInt(m)<10){
			m = "0"+m;
		}
		if(parseInt(d)<10){
			d = "0"+d;
		}
		return y+'-'+m+'-'+d;
	}else{
		return date;
	}
	
};

function isDate(data){
	  return (data && Object.prototype.toString.call(data)=="[object Date]");
}

var divDialog = null;
function getDialog(){
	if (divDialog == null)
		divDialog = $('<div id="common_dialog"></div>'); 
	return divDialog;
};


/**
 * 获取表格改变的数据
 */
common.util.getChangesData  = function(dgId){
	var add_rows = $("#"+dgId).datagrid('getChanges','inserted');
	var update_rows = $("#"+dgId).datagrid('getChanges','updated');
	var delete_rows = $("#"+dgId).datagrid('getChanges','deleted');
	var obj = new Object();
	obj["inserted"]=add_rows;
	obj["updated"]=update_rows;
	obj["deleted"]=delete_rows;
	
	return obj;
};

/**
 * 获取所有的行
 */
common.util.getAllRows  = function(dgId){
	var rows = $("#"+dgId).datagrid('getRows');
	return rows;
};


/**
 * 滚动到底部
 * @param dgId
 */
common.util.scrollToEndRow = function(dgId){
	var rows = $("#"+dgId).datagrid('getRows');
	var rowIndex = rows.length-1;
	$('#'+dgId).datagrid('scrollTo',rowIndex);
};

/**
 * 批量删除表格数据
 * @param dgId
 */
common.util.deleteDataGridRows = function(dgId){
	var check_rows = $("#"+dgId).datagrid('getChecked');
	
	var indexArray = new Array();
	$.each(check_rows,function(index){
		var rowObj = check_rows[index];
		var index = $('#'+dgId).datagrid('getRowIndex',rowObj);
		indexArray.push(index);
	});
	
	common.util.deleteTableRows($("#"+dgId),indexArray);
};

/**
 * 刷新datagrid页面(保存后)
 */
common.util.refreshGrid = function(obj,lstIds,dgId){
	var addArray =  obj.inserted;
	$.each(addArray,function(index){
		var addRow = addArray[index];
		var rowId = lstIds[index];
		var rowIndex = $('#'+dgId).datagrid('getRowIndex',addRow);
		$('#'+dgId).datagrid('updateRow',{
			index: rowIndex,
			row: {
				id:rowId
		}
	});
 });
	
	
  $('#'+dgId).datagrid("acceptChanges");
	
//	var result = getAllRows("single_table_dg");
//	$('#single_table_dg').datagrid('loadData',{total:0,rows:[]});
////	给datagrid填充值
//	$('#single_table_dg').datagrid('loadData',result);
};
/**
 * 日期转换
 * @param obj
 * @param property
 */
common.util.setRowDate = function(obj,property){
	var strTime = obj[property];
	if(typeof strTime=='string'){
		var riqi = $.fn.datebox.defaults.parser(strTime);
		obj[property] = riqi;
	}
};
/**
 * 日期格式化
 * @param obj
 * @param property
 */
common.util.setRowDateStr = function(obj,property){
	var strTime = obj[property];
	if(isDate(strTime)){
		var riqi = common.util.formatDate(strTime);
		obj[property] = riqi;
	}
};

/**
 * 弹出框关闭时触发
 * @returns {Boolean}
 */
function beforeCloseWindow(){
	$.messager.confirm("", "是否关闭窗口?",function (r) {
        if (r) {
           closeDialog();
        }
    });
    return false;  
}

/**
 * 关闭弹出框
 */
function closeDialog(){
	divDialog.dialog('close',true);
};


function changeStylesComm(easyuiTheme,styleThemes){
	if(easyuiTheme!=null){
		var oldTheme = easyuiTheme.attr("href");
		var endIndex = oldTheme.indexOf("css");
		if(endIndex>-1){
			endIndex = endIndex+3;
		}
		
		var oldThemesuffix = oldTheme.substring(0,endIndex);
		var themeChanges = oldThemesuffix+styleThemes;
		easyuiTheme.attr("href",themeChanges);
	}
}

/**
 * 密码验证
 */
$.extend($.fn.validatebox.defaults.rules, {    
    equals: {    
        validator: function(value,param){    
            return value == $(param[0]).val();    
        },    
        message: '两次输入的密码不一致！'   
    } 
});

/**
 * datagrid扩展密码输入框
 * @param {Object} container
 * @param {Object} options
 * @return {TypeName} 
 */
$.extend($.fn.datagrid.defaults.editors, {
    password: {
		init: function(container, options){
			var input = $('<input type="password" class="datagrid-editable-input">').appendTo(container);
//				$.extend($.fn.validatebox.defaults.rules, {
//					safepass: {
//						validator: function(value, param) {
//							return !(/^(([A-Z]*|[a-z]*|\d*|[-_\~!@#\$%\^&\*\.\(\)\[\]\{\}<>\?\\\/\'\"]*)|.{0,5})$|\s/.test(value));
//						},
//						message: '密码由字母和数字组成，至少6位'
//					}
//				});
			
				$.extend($.fn.validatebox.defaults.rules, {    
				    equal: {    
				        validator: function(value,param){
				        var rpwd = container.parents("td[field='userPwd']").prev().find("input[type='password']").val();
				        return value == rpwd;
				        },    
				        message: '两次输入的密码不一致！'   
				    }
				});
				
			input.validatebox(options);
			return input;
		},
		destroy: function(target){
			$(target).remove();
		},
		getValue: function(target){
			return $(target).val();
		},
		setValue: function(target, value){
			$(target).val(value);
		},
		resize: function(target, width){
			$(target)._outerWidth(width);
		}
    }
});

common.util.fillTemplate = function(result){
	$.each(result,function(index){
		var template = result[index];
		var num = index+1;
		
		fillTemplateTitle(num);
		fillTemplateContent(num,template);
	});
};

function fillTemplateTitle(num){
	var templateClass = "";
	if(num==1){
		templateClass = "on";
	}
	var liContent = $("<li>", {
					  "class": templateClass,
					  text: num
					});
	$("#banner ul").append(liContent);
}

function fillTemplateContent(num,template){
	var imgUrl = "../../../css/images_template/template_default_"+num+".jpg";
	var divContent = $("<a>", {
					  "href": "#",
					  "target": "_blank"
					});
	var img = $("<img>", {
					  "src": imgUrl,
					  "url": template.templateUrl,
					  "idAttr":template.id
					});
	divContent.append(img);
	$("#banner_list").append(divContent);
}

common.util.initialTemplate = function(){
	$("#banner_list a:not(:first-child)").hide();
	
	$("#banner li").click(function() {
		//获取Li元素内的值，即1，2，3，4
		var i = $(this).text()-1;
		$("#banner_list a").filter(":visible").fadeOut(500).parent().children().eq(i).fadeIn(1000);
		$("#banner").css({"background":''});
		$(this).toggleClass("on");
		$(this).siblings().removeAttr("class");
	});
};

common.util.getChangeTheme = function(tenantObj){
	var themComb = $('#themeStyle').combobox({
	    valueField:'id',
	    textField:'text',
	    data:themeData,
	    onSelect:function(record){
	    	var themeId = record.id;
	    	var theme = record.attributes.url;
	    	var themeColor = record.attributes.color;
	    	getTempOnChangeTheme(themeColor);
	    	
	    	styleThemes = theme;
	    }
	});
	
	var dataArray = themeData;
	if(tenantObj!=null){
		var themeStyle = tenantObj.themeStyle;
		if(themeStyle!=""&&themeStyle!=null){
			$.each(dataArray,function(index){
				var data = dataArray[index];
				var id = data.id;
				var text = data.text;
				var themeColor = data.attributes.color;
				var styleUrl = data.attributes.url;
				
				if(styleUrl==themeStyle){
					themComb.combobox('setValue',id);
	    			getTempOnChangeTheme(themeColor);
					return;
				}
			});
			return;
		}
	}
	
	$.each(dataArray,function(index){
		var data = dataArray[index];
		var id = data.id;
		var styleUrl = data.attributes.url;
		if(styleUrl=="/themes/default/easyui.css"){
			themComb.combobox('setValue',id);
			return;
		}
	});
};

function getTempOnChangeTheme(themeColor){
	var contentDiv = $("#banner_list a");
	$.each(contentDiv,function(index){
		var content = $(contentDiv[index]).find("img");
		var num = index+1;
		var imgUrl = "../../../css/images_template/template_"+themeColor+"_"+num+".jpg";
		content.attr("src",imgUrl);
	});
}