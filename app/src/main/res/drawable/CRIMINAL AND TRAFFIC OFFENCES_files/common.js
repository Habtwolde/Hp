$(document).ready(function() {
	
	/* Force the search box to submit on "Enter" key event*/
	$("#header-cse").keypress(function(event){
	    if(event.keyCode == 13){
	    	event.preventDefault();
	        $("#search_submit").click();
	    }
	});
	
		
	/* Set the interval time of the large banner carousel scroll */
	$('#lscsaCarousel').carousel({
		interval : 8000
	});

	/* Set the interval time of the news carousel scroll */
	$('#newsCarousel').carousel({
		interval : 13000
	});
	
	/* Force the top level menu items to act as links */
	$('.menu1 li.top a').on('click mouseup', function(e) {
	      var el = $(this);
	      var link = el.attr('data-alt-url');
	      window.location = link;
	});	
	
	$('[data-toggle="offcanvas"]').click(function () {
		$('.row-offcanvas').toggleClass('active')
	});
	
	
	/* Law Handbook left hand side hover menu functions */
	$("li.expand-parent").hover(function(){
		$(this).find('.expand').css("display", "block");
	}, 
	function(){
		$(this).find('.expand').css("display", "none");
	});
	
	
	/* Performs a smooth page scroll to an anchor on the same page */
	$('#back_to_top a[href*=#]:not([href=#])').click(function() {
		if (location.pathname.replace(/^\//, '') == this.pathname
				.replace(/^\//, '')
				&& location.hostname == this.hostname) {
			var target = $(this.hash);
			target = target.length ? target : $('[name='
					+ this.hash.slice(1) + ']');
			if (target.length) {
				$('html,body').animate({
					scrollTop : target.offset().top
				}, 1000);
				return false;
			}
		}
	});
	
	/* Back to top functions */
	$(function(){
	    var backToTopTrigged = false;
	    $(window).scroll(function() {
	        if ($(this).scrollTop() >= 300 && !backToTopTrigged) {
	            $('#back_to_top').removeClass('hidden');
	            backToTopTrigged = true;
	        }
	        if ($(this).scrollTop() == 0) {
	        	$('#back_to_top').addClass('hidden');
	        	backToTopTrigged = false;
	        }	    			        
	    });
	});	
	
	
	$("em.firstterm").hover(function(){
		$(this).parent().css("text-decoration", "none");
	});

	/*
	*
	* This is a hack top open livechat window in a new window not new tab
	*
	*/
    $('.livechat_popup').click(function(e) {
        e.preventDefault();
        window.open(this.href, '_blank', 'width=500,height=600');
    });
	
	
});

/*
 * The below code when set up with the new GA "Universal Analytics tracking
 * code" will enable event tracking on all linking that have the class
 * "track-click" applied to them
 * 
 * $(document).ready(function() { initialise_external_links(); $('#v2maincontent
 * a.track-click').each(function() { $(this).click(function(e) {
 * e.preventDefault(); var _self = $(this); try { ga('send', 'event', 'link',
 * 'action', _self.text()); } catch (err) { } setTimeout(function() { //
 * document.location.href = _self.attr('href'); window.open(_self.attr('href')); },
 * 100); }); });
 * 
 * function initialise_external_links() {
 * $("a[rel='external']").click(function(e) { e.preventDefault();
 * window.open($(this).attr('href')); }); }
 * 
 * });
 */

function quickExit() {
    window.open('http://www.bom.gov.au', "_newtab");
    window.location.replace('http://google.com.au');
}

jQuery(function() {

    jQuery(".btn_exit").on("click", function(e) {
        quickExit();
    });

    jQuery(".btn_exit a").on("click", function(e) {
        e.stopPropagation();
    });

    jQuery(document).keyup(function(e) {
        if (e.keyCode == 27) { // escape key
            quickExit();
        }
    });

});
