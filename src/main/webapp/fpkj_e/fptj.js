$(function() {
	// ��������
	$.datepicker.regional['zh-CN'] = {
		closeText : '�ر�',
		prevText : '&#x3C;����',
		nextText : '����&#x3E;',
		currentText : '����',
		monthNames : ['һ��', '����', '����', '����', '����', '����', '����', '����',
				'����', 'ʮ��', 'ʮһ��', 'ʮ����'],
		monthNamesShort : ['һ��', '����', '����', '����', '����', '����', '����',
				'����', '����', 'ʮ��', 'ʮһ��', 'ʮ����'],
		dayNames : ['������', '����һ', '���ڶ�', '������', '������', '������', '������'],
		dayNamesShort : ['����', '��һ', '�ܶ�', '����', '����', '����', '����'],
		dayNamesMin : ['��', 'һ', '��', '��', '��', '��', '��'],
		weekHeader : '��',
		dateFormat : 'yy-mm-dd',
		firstDay : 1,
		isRTL : false,
		showMonthAfterYear : true,
		yearSuffix : ''
	};
	$.datepicker.setDefaults($.datepicker.regional['zh-CN']);
	$.timepicker.regional['zh-CN'] = {
		timeOnlyTitle : 'ѡ�� ʱ��',
		timeText : 'ʱ��',
		hourText : 'Сʱ',
		minuteText : '����',
		secondText : '��',
		millisecText : '����',
		timezoneText : 'ʱ��',
		currentText : '����',
		closeText : '�ر�',
		timeFormat : 'HH:mm:ss',
		amNames : ['����', '����'],
		pmNames : ['����', '����'],
		isRTL : false
	};
	$.timepicker.setDefaults($.timepicker.regional['zh-CN']);
});