/**
 * 添加panel
 */
function addPanel(options){
//	var region = $('#region').val();
//	var options = {
//		region: direction
//	};
//	if (region=='north' || region=='south'){
//		options.height = 50;
//	} else {
//		options.width = 100;
//		options.split = true;
//		options.title = $('#region option:selected').text();
//	}
	$("body").layout('add', options);
}

/**
 * 移除panel
 */
function removePanel(direction){
	$("body").layout('remove', direction);
}