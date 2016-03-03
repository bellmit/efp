/*---自定义插件---*/
(function($) {
//检测ie,ie6
$.isIE = $.browser.msie;
$.isIE6 = $.isIE && !window.XMLHttpRequest;
//ie6背景缓存
(function() {
	if ($.isIE6) try {
		document.execCommand("BackgroundImageCache", false, true)
	} catch(e) {}
})();
//ie6PNG Hack
(function() {
	if ($.isIE6) try {		
		$.getScript("../scripts/common/pngfix.js",
		window.onload = function()
             {
             DD_belatedPNG.fix(".pngfix,.pngfix:hover");
             })
	} catch(a) {}
})();
})(jQuery);
