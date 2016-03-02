var enter_addRow = undefined;
$.extend($.fn.datagrid.methods, { 
	keyCtr : function (jq) { 
	return jq.each(function () { 
	var grid = $(this); 
	grid.datagrid('getPanel').panel('panel').attr('tabindex', 1).bind('keydown', function (e) { 
	switch (e.keyCode) { 
	case 38: // up 
	var selected = grid.datagrid('getSelected'); 
	grid.datagrid('clearSelections');
	if (selected) { 
	var index = grid.datagrid('getRowIndex', selected); 
	grid.datagrid('selectRow', index - 1); 
	} else { 
	var rows = grid.datagrid('getRows'); 
	grid.datagrid('selectRow', rows.length - 1); 
	} 
	break; 
	case 40: // down 
	var selected = grid.datagrid('getSelections'); 
	grid.datagrid('clearSelections');
	if (selected.length>0) { 
	var index = grid.datagrid('getRowIndex',selected[selected.length-1]);
	grid.datagrid('selectRow', index + 1); 
	} else { 
	grid.datagrid('selectRow', 0); 
	} 
	break; 
	case 13://enter
	var rows = $("#single_table_dg").datagrid('getRows');
	var rowIndex = rows.length-1;
	var select_row = $("#single_table_dg").datagrid('getSelected');
	var select_rowIndex = $("#single_table_dg").datagrid('getRowIndex',select_row);
	if(select_rowIndex==rowIndex){
		if ($('#single_table_dg').datagrid('validateRow', rowIndex)){
			$('#single_table_dg').datagrid('endEdit', rowIndex);
					$("#single_table_dg").datagrid('appendRow',{});
					$("#single_table_dg").datagrid('selectRow',rowIndex+1).datagrid('beginEdit', rowIndex+1);
					$("#single_table_dg").datagrid('getEditors', rowIndex+1);
					enter_addRow = rowIndex+1;
								}
	}else{
		if ($('#single_table_dg').datagrid('validateRow', select_rowIndex)){
			$('#single_table_dg').datagrid('endEdit', select_rowIndex);
		}
	}
	 
	} 
	}); 
	}); 
	} 
}); 



