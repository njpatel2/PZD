 (function($) {
     $.fn.linkesDrillDown = function(q) {
    	 debugger
         var a = $.extend({
             easeHeight: true
         }, q);
        
         $.fn.linkesDrillDown.sdd = function(o, f) {
        	 console.log(q);
             var g = o.hasClass('l_drillDown');
             if (!g) {
                 o.addClass('active');
                 $.fn.linkesDrillDown.sdd(o.parent().closest('ul'), false)
             } else {
                 var h = o.find('.active');
                 var i = (o.width() * h.length * -1) + 'px';
                 o.animate({
                     left: i
                 }, 'fast')
             } if (f) {
                 var t = o.siblings('a');
                 var j = o.parent();
                 var k = t.closest('.l_drillDownWrapper').find('.l_ddbc');
                 if (!o.parent().parent().hasClass('l_drillDown')) {
                     var l = '&laquo ' + t.parent().parent().siblings('a').text();
                     var m = "goUp"
                 } else {
                	 debugger
                     //var l = '&laquo Results123 list';
                	 var l = '&laquo' +i18Map("label.search.resultlist");
                     var m = "Back to results"
                 }
                 var n = function() {
                     var w = t.closest('.l_drillDownWrapper');
                     var a = w.find('.displayed');
                     var b = w.find('.l_drillDown').outerHeight();
                     if (a.length > 0) b = (w.find('.displayed').outerHeight() + w.find('.l_ddbc').outerHeight());
                     w.animate({
                         height: b
                     }, 'fast', function() {
                         $(window).trigger('resize')
                     })
                 };
                 var p = $(document.createElement('a')).html(l).addClass(m).attr('href', 'javascript:;').click(function() {
                     var a = $(this).closest('.l_drillDownWrapper');
                     var b = a.find('.displayed');
                     var c = a.find('active');
                     var d = b.parent().parent();
                     var e = d.siblings('a');
                     console.log(e);
                     if (!d.hasClass('l_drillDown')) {
                         e.trigger('click')
                     } else {
                         b.removeClass('displayed');
                         c.removeClass('active');
                         a.find('.l_drillDown').animate({
                             left: 0
                         }, 'fast');
                         $(this).parent().empty().slideUp('fast');
                         n()
                     }
                 });
                 k.empty().append(p).append(' &raquo; ' + t.text()).slideDown(50, function() {
                     n()
                 })
             }
         };
         var r = this;
         r.each(function() {
             var d = $(this);
             d.addClass('l_drillDown');
             d.wrap('<div class="l_drillDownWrapper" />');
             d.before('<div class="l_ddbc" />');
             d.find('a[href="#"]').attr('href', 'javascript:;');
             d.find('a').each(function() {
                 if (($(this).attr('href') == 'javascript:;') && ($(this).siblings('ul').length > 0)) $(this).parent().addClass('hasSubs')
             });
             d.find('a').click(function() {
            	 debugger	
            	 
                 if ($(this).attr('href') == 'javascript:;') {
                     var t = $(this);
                     var c = t.closest('.l_drillDown');
                     var a = (t.closest('ul').position().left - c.position().left) * -1;
                     var b = t.closest('.l_drillDownWrapper').find('.l_ddbc');
                     c.find('ul').removeClass('active').removeClass('displayed');
                     var u = t.siblings('ul');
                     u.addClass('displayed');
                     
                     $.fn.linkesDrillDown.sdd(u, true)
                 }
             })
         })
     }
 })(jQuery);
