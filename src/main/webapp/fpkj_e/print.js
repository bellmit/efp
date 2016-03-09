//����ؼ�
document.write("<object id=\"LODOP_OB\" "
		+ "classid=\"clsid:2105C259-1E0C-4534-8141-A753534CB4CA\" "
		+ "width=\"0\" height=\"0\">"
		+ "<embed id=\"LODOP_EM\" type=\"application/x-print-lodop\" "
		+ "width=0 height=0></embed></object>");
//��ӡ�ؼ�
var	LODOP;
//�ͻ��������ļ�·��
var cfg_dir = "C:\\Program Files\\MountTaiSoftware\\Lodop\\";
//��ȡ�ؼ�ʵ��
if (navigator.appVersion.indexOf("MSIE")>=0){
	LODOP=document.getElementById('LODOP_OB');
}else{
	LODOP=document.getElementById('LODOP_EM');
}
if (LODOP==null||typeof(LODOP.VERSION)=="undefined"||LODOP.VERSION!='6.1.9.8'){
	location.href="";
}else{
	LODOP.SET_LICENSES("ZCSB�������з�����","545C44BD459DBBAFB79F18A0F8160A12","","");
}
//���ƽ��������
function limit_money_input(a) {
	var ja = $(a);
	ja.bind("contextmenu", function() {
		return false;
	});
	ja.css('ime-mode', 'disabled');
	ja.bind("keydown", function(e) {
		var key = window.event ? e.keyCode : e.which;
		if (isFullStop(key)) {
			return $(this).val().indexOf('.') < 0;
		}
		return (isSpecialKey(key)) || ((isNumber(key) && !e.shiftKey))
	});
}

function isNumber(key) {
	return (key >= 48 && key <= 57) || (key >= 96 && key <= 105);
}
function isSpecialKey(key) {
	//8:backspace; 46:delete; 37-40:arrows; 36:home; 35:end; 9:tab; 13:enter  116:F5
	return key == 8 || key == 46 || (key >= 37 && key <= 40) || key == 35
			|| key == 36 || key == 9 || key == 13 || key==116;
}
function isFullStop(key) {
	return key == 190 || key == 110;
}
function addMoney(a,b){
	LODOP.ADD_PRINT_TEXT(a-1,b,20,20,"Y");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
	LODOP.ADD_PRINT_TEXT(a-9,b,20,20,"_");
	LODOP.SET_PRINT_STYLEA(0,"FontName","����");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
	LODOP.ADD_PRINT_TEXT(a-7,b,20,20,"_");
	LODOP.SET_PRINT_STYLEA(0,"FontName","����");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
}