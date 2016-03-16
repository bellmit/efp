var index = 0;

var styleThemes = "/themes/default/easyui.css";

var exceptionMsg;

var exceptionInfo;

$(function(){
	//获得菜单项
//	getMenuDemo();
	$.getJSON("../../menu",function(data){
		var json = convert(data);
		$.each(json[0].children, function(i, n) 
			{
		        var menulist = ""; 
		        $.each(n.children, function(j, o) 
					{
						var text = "'"+o.text+"'";
						var url = "'"+o.url+"'";
						menulist += '&nbsp;&nbsp;<li><div onclick="addTabs('+text+','+url+')"><a target="mainFrame" class="ahover" onclick="addTabs('+text+',' + url + ')" >' + o.text + '</a></div></li>\n';
					})
			
				$('#menu_div').accordion('add',
					{
						title:n.text,
						content:'<div>'+menulist+'</div>'
					});
			});
	});
	
	$('.cs-navigate-tab').live('click', function() {
		var $this = $(this);
		var href = $this.attr('src');
		var title = $this.text();
		
		addTabs(title, href);
	});
	
	$("#tt").tree({
		onClick: function(node){
			var text = node.text;
			var url = node.attributes.url;
			addTabs(text,url);
		}
	});
	
	
	//改变背景色
	getBackGroundOnChange();
	
	//改变按钮位置
	changeBtnPosition();
	
	changeWinBtnPosition();
	
	//改变布局
	getLayoutOnChange();
	
	tabCloseEven();
	
	/*//初始化查询组件
	initialSearchComponent();*/
});

//修改密码
function changePassword(){
	var s = common.util.createFrame("../../portal/user/usermanage/password_change.html");
	window.top.getDialog().dialog({
	    title: '密码修改',
	    width: 500,
	    height: 400,
	    closed: false,
	    cache: false,
	    content:s,
	    modal: true
//	    onBeforeClose:beforeCloseWindow,
	});
}

//退出系统
function exitSystem(){
	
	$.messager.confirm("","确定退出?",function(r){
		if(r){
			$.ajax({
				type:"POST",
		        url: basePath + '/logout',
		        async:false,
		        success: function (data) {
		        	if(data== '0'){
		        		window.location.href= basePath + '/login/login.html';
		        	}
		        },
		        error:function(e) {
		        	window.top.location.href = basePath + '/login/login.html';
		        }
			});
			
//			window.location.href= basePath + '/logout';
		}
	});
}

//显示当前登录用户
/*function currentLoginUser(){
	currentUser = callCommFunc(com.bwplat.portal.user.service.UserService.findCurrentUserAccount);
	$("#currentUser").html('欢迎'+currentUser+'登录电子发票平台系统！');
}*/

//在右边center区域打开菜单，新增tab
function addTabs(text,url){
   var num = $('#tabs').tabs();
   var s = createFrame(url);
   if ($('#tabs').tabs('exists', text)) {
       $('#tabs').tabs('select', text);
   } else {
       $('#tabs').tabs('add', {
           title : text,
           closable : true,
           content : s
           //fit:true
       });
       
   }
   
   tabClose();
}

//创建一个iframe
function createFrame(url) {
	var s = "<iframe id=\"mainFrame\" name=\"mainFrame\" scrolling=\"auto\" frameborder=\"0\"  src=\"" + url + "\" style=\"width:100%;height:100%;\"></iframe>";
	return s;
}

//绑定tabs的右键菜单
$("#tabs").tabs({
    onContextMenu : function (e, title) {
        e.preventDefault();
        $('#tabsMenu').menu('show', {
            left : e.pageX,
            top : e.pageY
        }).data("tabTitle", title);
    }
});

//实例化menu的onClick事件
$("#tabsMenu").menu({
    onClick : function (item) {
        CloseTab(this, item.name);
    }
});

//几个关闭事件的实现
function CloseTab(menu, type) {
    var curTabTitle = $(menu).data("tabTitle");
    var tabs = $("#tabs");
    
    if (type === "close") {
        tabs.tabs("close", curTabTitle);
        return;
    }
    
    var allTabs = tabs.tabs("tabs");
    var closeTabsTitle = [];
    
    $.each(allTabs, function () {
        var opt = $(this).panel("options");
        if (opt.closable && opt.title != curTabTitle && type === "Other") {
            closeTabsTitle.push(opt.title);
        } else if (opt.closable && type === "All") {
            closeTabsTitle.push(opt.title);
        }
    });
    
    for (var i = 0; i < closeTabsTitle.length; i++) {
        tabs.tabs("close", closeTabsTitle[i]);
    }
}

function tabClose() {
	/*双击关闭TAB选项卡*/
	$(".tabs-inner").dblclick(function(){
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close',subtitle);
	})
	/*为选项卡绑定右键*/
	$(".tabs-inner").bind('contextmenu',function(e){
		$('#tabs_menu').menu('show', {
			left: e.pageX,
			top: e.pageY
		});

		var subtitle =$(this).children(".tabs-closable").text();

		$('#tabs_menu').data("currtab",subtitle);
		$('#tabs').tabs('select',subtitle);
		return false;
	});
}

//绑定右键菜单事件
function tabCloseEven() {
	/*setMessageRegion($('#messageDiv'));*/
	//刷新
	$('#refresh').click(function(){
		var currTab = $('#tabs').tabs('getSelected');
		var url = $(currTab.panel('options').content).attr('src');
		if(url != undefined && currTab.panel('options').title != 'Home') {
			$('#tabs').tabs('update',{
				tab:currTab,
				options:{
					content:createFrame(url)
				}
			})
		}
	})
	
	//关闭当前
	$('#close').click(function(){
		var currtab_title = $('#tabs_menu').data("currtab");
		$('#tabs').tabs('close',currtab_title);
	})
	//全部关闭
	$('#all').click(function(){
		$('.tabs-inner span').each(function(i,n){
			var t = $(n).text();
			if(t != 'Home') {
				$('#tabs').tabs('close',t);
			}
		});
	});
	//关闭除当前之外的TAB
	$('#other').click(function(){
		var prevall = $('.tabs-selected').prevAll();
		var nextall = $('.tabs-selected').nextAll();		
		if(prevall.length>0){
			prevall.each(function(i,n){
				var t=$('a:eq(0) span',$(n)).text();
				if(t != 'Home') {
					$('#tabs').tabs('close',t);
				}
			});
		}
		if(nextall.length>0) {
			nextall.each(function(i,n){
				var t=$('a:eq(0) span',$(n)).text();
				if(t != 'Home') {
					$('#tabs').tabs('close',t);
				}
			});
		}
		return false;
	});
}

function getBackGroundOnChange(){
	$('#them_id').combobox({
	    url:'themes_data.json',
	    valueField:'id',
	    textField:'text',
	    onSelect:function(record){
	    	var winFrames = window.frames;
	    	
	    	var themeId = record.id;
	    	var theme = record.attributes.url;
	    	styleThemes = theme;
	    	
	    	changeStylesComm($("#easyuiTheme"),styleThemes);
			
			//改变背景颜色
			changeBackgroundColor(winFrames,styleThemes);
	    }
	});
}

function getLayoutOnChange(){
	$('#template_id').combobox({
	    url:'template_data.json',
	    valueField:'id',
	    textField:'text',
	    onSelect:function(record){
	    	var id = record.id;
	    	switch(id){
		    	case 1:
		    		var divNorth = $(".layout-panel-north");
		    		var divSouth = $(".layout-panel-south");
		    		var divNorthBody = divNorth.find(".panel-body");
		    		var divSouthBody = divSouth.find(".panel-body");
		    		var northContent = divNorthBody.html();
		    		var southContent = divSouthBody.html();
		    		
		    		var divNorthClass = $(divNorthBody).attr("class");
		    		var divSouthClass = $(divSouthBody).attr("class");
		    		var northStyle = "north_div";
		    		var southStyle = "south_div";
		    		var northHeight = 80;
		    		var southHeight = 25;
		    		var divNorthContent;
		    		var divSouthContent;
		    		if(divNorthClass.indexOf("north_div")>-1){
		    			northStyle = "south_div";
		    			southStyle = "north_div";
		    			northHeight = 25;
		    			southHeight = 80;
		    			var spanArray = $(northContent).children();
		    			divSouthContent = $(northContent).empty();
		    			getSpanContent(spanArray,divSouthContent);
		    			
		    			divNorthContent = southContent;
		    		}else if(divNorthClass.indexOf("south_div")>-1){
		    			var spanArray = $(southContent).children();
		    			divNorthContent = $(southContent).empty();
		    			getSpanContent(spanArray,divNorthContent);
		    			divSouthContent = northContent;
		    		}
		    		
		    		removePanel("north");
		    		removePanel("south");
		    		
		    		var southOptions = {
						region: 'south',
						content: divSouthContent,
						bodyCls:southStyle,
						height:southHeight
					};
		    		var northOptions = {
						region: 'north',
						content: divNorthContent,
						bodyCls: northStyle,
						height: northHeight
					};
		    		
		    		addPanel(southOptions);
		    		addPanel(northOptions);
		    		
		    		//改变背景色
					getBackGroundOnChange();
					//改变布局
					getLayoutOnChange();
		    		break;
		    	case 2:
		    		var divWest = $(".layout-panel-west");
		    		var divEast = $(".layout-panel-east");
		    		var divWestBody = divWest.find(".panel-body");
		    		var divEastBody = divEast.find(".panel-body");
		    		var westContent = divWestBody.html();
		    		var eastContent = divEastBody.html();
		    		
		    		var accordingDiv;
		    		removePanel("west");
		    		removePanel("east");
		    		var divWestClass = $(divWestBody).attr("class");
		    		var divEastClass = $(divEastBody).attr("class");
		    		var divAccording = null;
		    		var childMenu = null;
		    		if(divWestClass!=""&&divWestClass!=null){
		    			if(divWestClass.indexOf("west-div")>-1){
			    			childMenu = $(westContent).find(".panel");
			    			divAccording = $(westContent).empty();
			    			var eastOptions = {
								region: 'east',
								content: divAccording,
								bodyCls:'west-div',
								split:true,
								title:'功能导航',
								width:200
							};
			    			
			    			addPanel(eastOptions);
			    		}
		    		}else{
		    			if(divEastClass.indexOf("west-div")>-1){
		    				childMenu = $(eastContent).find(".panel");
			    			divAccording = $(eastContent).empty();
			    			var westOptions = {
								region: 'west',
								content: divAccording,
								bodyCls:'west-div',
								split:true,
								title:'功能导航',
								width:200
								
							};
			    			addPanel(westOptions);
		    			}
		    		}
		    		
		    		
		    		$.each(childMenu,function(index){
		    			var child = childMenu[index];
		    			var panelHeader = $(child).find(".panel-header");
		    			var panelBody = $(child).find(".panel-body");
		    			var title = panelHeader.find(".panel-title").text();
		    			var content = panelBody.html();
		    			if(index==0){
		    				panelBody.empty();
//		    				var childTree = content.attr(".easyui-tree");
		    				var childTree = $(content);
		    				$.each(childTree,function(index){
		    					var menuTree = childTree[index];
		    					var childId = $(menuTree).attr("id");
		    					var child = $("<ul>", {
					    			id:childId,
								  	"class": "easyui-tree"
								}).appendTo(panelBody);
		    				});
		    				
		    				
		    				content = panelBody.html();
		    				
		    			}
		    			
		    			$(divAccording).accordion("add",{
							title: title,
							content: content
						});
		    			$(divAccording).accordion("select",0);
		    		});
		    		
					//获得菜单项
					getMenuDemo();
    				//获取功能菜单
					getFunctionMenu();
		    		break;
	    	}
	    }
	});
}

/**
 * 改变背景色
 * @param winFrames
 * @param styleThemes
 */
function changeBackgroundColor(winFrames,styleThemes){
	for(var i=0;i<winFrames.length;i++){
		var win = winFrames[i];
		var childWinFrames = win.frames;
		var easyuiTheme = win.document.getElementById("easyuiTheme");
		
		changeBackgroundColor(childWinFrames,styleThemes);
		
		
		if(easyuiTheme!=null){
			changeStylesComm($(easyuiTheme),styleThemes);
		}
	}
}

function changePosition(winFrames,position){
	for(var i=0;i<winFrames.length;i++){
		var win = winFrames[i];
		var divWin = win.document.getElementById("toolbar_div");
		var divBody = win.document.body;
		
		if(divWin!=null){
			if(position==1){//上面
				appendTop(divWin,divBody);
			}else if(position==2){//下面
				appendBottom(divWin,divBody);
			}
		}
	}
}

function changeWinPosition(winArray,position){
	for(var i=0;i<winArray.length;i++){
		var win = winArray[i];
		var childWinFrames = win.frames;
		var divWin = win.document.getElementById("win_toolbar_div");
		var divBody = win.document;
		
		changeWinPosition(childWinFrames,position);
		if(divWin!=null){
			if(position==1){//上面
				appendWinTop(divWin,divBody);
			}else if(position==2){//下面
				appendWinBottom(divWin,divBody);
			}
		}
	}
}

function appendTop(divWin,divBody){
	var toolbarDiv = $(divWin);
	$(divBody).prepend(toolbarDiv);
	
}

function appendWinTop(divWin,divBody){
	var divBody = divBody.getElementById("north_div");
	$(divBody).prepend(divWin);
}

function appendBottom(divWin,divBody){
	var toolbarDiv = $(divWin);
	$(divBody).append(toolbarDiv);
}

function appendWinBottom(divWin,divBody){
	var divBody = divBody.getElementById("south_div");
	$(divBody).append(divWin);
}

function getMenuDemo(){
	$('#tt').tree({
		url:'tree_data1.json',
		method:'get',
		animate:true,
		dnd:true,
		onClick: function(node){
			var text = node.text;
			var url = node.attributes.url;
			addTabs(text,url);
		}
	});
}

/**
 * 获取span内容
 * @param {Object} spanArray
 * @param {Object} divSouthContent
 */
function getSpanContent(spanArray,divSouthContent){
	$.each(spanArray,function(index){
		var spanContent = spanArray[index];
		var combArray = $(spanContent).find('input[class="combobox-f combo-f"]');
		if(combArray.length>0){
			var divSpanContent = $("<span>", {
			  	"class": "selectTheme"
			});
			$.each(combArray,function(index){
				var comb = combArray[index];
				var combId = $(comb).attr("id");
				$("<input>", {
				  id: combId
				}).appendTo(divSpanContent);
			});
			
			divSpanContent.appendTo(divSouthContent);
		}else{
			divSouthContent.append(spanContent);
		}
		
	});
}

function initialSearchComponent(){
	var htmlContent = '<input id="searchText" type="text" style="width:80px;height:20px;" onkeypress="enterPress();" onkeydown="enterPress();"/>';
	var alink = htmlContent+ '<a href="javascript:void(0);" class="icon-search" style="text-decoration:none;" onclick="searchTree();" plain="true">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>';
	
	var westDiv = $("body").layout("panel","west");
	westDiv.panel('setTitle',alink);
}



function changeBtnPosition(){
	$('#position_id').combobox({
	    url:'position_data.json',
	    valueField:'id',
	    textField:'text',
	    onSelect:function(record){
	    	var winFrames = window.frames;
	    	
	    	var id = record.id;
	    	switch(id){
		    	case 1:
		    		changePosition(winFrames,id);
		    		break;
		    	case 2:
		    		changePosition(winFrames,id);
		    		break;
	    	}
	    }
	});
}

function changeWinBtnPosition(){
	$('#win_position_id').combobox({
	    url:'position_data.json',
	    valueField:'id',
	    textField:'text',
	    onSelect:function(record){
	    	var winFrames = window.frames;
	    	var winArray = new Array();
	    	for(var i=0;i<winFrames.length;i++){
				var win = winFrames[i];
				var childWinFrames = win.frames;
				if(childWinFrames.length>0){
					winArray.push(childWinFrames);
				}
			}
	    	
	    	var id = record.id;
	    	switch(id){
		    	case 1:
		    		changeWinPosition(winArray,id);
		    		break;
		    	case 2:
		    		changeWinPosition(winArray,id);
		    		break;
	    	}
	    }
	});
}

function initialSearchComponent(){
	var htmlContent = '<input id="searchText" type="text" style="width:80px;height:20px;" onkeypress="enterPress();" onkeydown="enterPress();"/>';
	var alink = htmlContent+ '<a href="javascript:void(0);" class="search_btn" onclick="searchTree();" plain="true">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>';
	
	var westDiv = $("body").layout("panel","west");
	westDiv.panel('setTitle',alink);
}

function searchTree(){
//	$('#menu_div').accordion('select', 0);
	var text = $("#searchText").val();
//	$("#business_tree").tree("search",text);
	
	var children = $("#business_tree").tree("getChildren");
	$.each(children,function(index){
		var node = children[index];
		var childText = node.text;
		if(childText.indexOf(text)>-1){
			var target = node.target;
			$("#business_tree").tree("scrollTo",target);
			$("#business_tree").tree("select",target);
			return;
		}
	});	
	
}

function enterPress(){
	var e = e || window.event; 
	if(e.keyCode == 13){ 
		searchTree();
	} 
}

function convert(rows){
	function exists(rows, parentId){
		for(var i=0; i<rows.length; i++){
			if (rows[i].id == parentId) return true;
		}
		return false;
	}
	
	var nodes = [];
	// get the top level nodes
	for(var i=0; i<rows.length; i++){
		var row = rows[i];
		if (!exists(rows, row.parentId)){
			nodes.push({
				id:row.id,
				text:row.functionName,
				url:row.functionUrl
			});
		}
	}
	
	var toDo = [];
	for(var i=0; i<nodes.length; i++){
		toDo.push(nodes[i]);
	}
	while(toDo.length){
		var node = toDo.shift();	// the parent node
		// get the children nodes
		for(var i=0; i<rows.length; i++){
			var row = rows[i];
			if (row.parentId == node.id){
				var child = {id:row.id,text:row.functionName,url:row.functionUrl};
				if (node.children){
					node.children.push(child);
				} else {
					node.children = [child];
				}
				toDo.push(child);
			}
		}
	}
	return nodes;
}
