/**
 * 依赖js包
 *
 * easing 缓动函数插件
 * select 插件
 * scrollBar 插件
 */
(function($){

     // 动画缓动函数插件{{{
    jQuery.easing['jswing'] = jQuery.easing['swing'];
    jQuery.extend( jQuery.easing, {
        def: 'easeOutQuad',
        swing: function (x, t, b, c, d) {
            //alert(jQuery.easing.default);
            return jQuery.easing[jQuery.easing.def](x, t, b, c, d);
        },
        easeInQuad: function (x, t, b, c, d) {
            return c*(t/=d)*t + b;
        },
        easeOutQuad: function (x, t, b, c, d) {
            return -c *(t/=d)*(t-2) + b;
        },
        easeInOutQuad: function (x, t, b, c, d) {
            if ((t/=d/2) < 1) return c/2*t*t + b;
            return -c/2 * ((--t)*(t-2) - 1) + b;
        },
        easeInCubic: function (x, t, b, c, d) {
            return c*(t/=d)*t*t + b;
        },
        easeOutCubic: function (x, t, b, c, d) {
            return c*((t=t/d-1)*t*t + 1) + b;
        },
        easeInOutCubic: function (x, t, b, c, d) {
            if ((t/=d/2) < 1) return c/2*t*t*t + b;
            return c/2*((t-=2)*t*t + 2) + b;
        },
        easeInQuart: function (x, t, b, c, d) {
            return c*(t/=d)*t*t*t + b;
        },
        easeOutQuart: function (x, t, b, c, d) {
            return -c * ((t=t/d-1)*t*t*t - 1) + b;
        },
        easeInOutQuart: function (x, t, b, c, d) {
            if ((t/=d/2) < 1) return c/2*t*t*t*t + b;
            return -c/2 * ((t-=2)*t*t*t - 2) + b;
        },
        easeInQuint: function (x, t, b, c, d) {
            return c*(t/=d)*t*t*t*t + b;
        },
        easeOutQuint: function (x, t, b, c, d) {
            return c*((t=t/d-1)*t*t*t*t + 1) + b;
        },
        easeInOutQuint: function (x, t, b, c, d) {
            if ((t/=d/2) < 1) return c/2*t*t*t*t*t + b;
            return c/2*((t-=2)*t*t*t*t + 2) + b;
        },
        easeInSine: function (x, t, b, c, d) {
            return -c * Math.cos(t/d * (Math.PI/2)) + c + b;
        },
        easeOutSine: function (x, t, b, c, d) {
            return c * Math.sin(t/d * (Math.PI/2)) + b;
        },
        easeInOutSine: function (x, t, b, c, d) {
            return -c/2 * (Math.cos(Math.PI*t/d) - 1) + b;
        },
        easeInExpo: function (x, t, b, c, d) {
            return (t==0) ? b : c * Math.pow(2, 10 * (t/d - 1)) + b;
        },
        easeOutExpo: function (x, t, b, c, d) {
            return (t==d) ? b+c : c * (-Math.pow(2, -10 * t/d) + 1) + b;
        },
        easeInOutExpo: function (x, t, b, c, d) {
            if (t==0) return b;
            if (t==d) return b+c;
            if ((t/=d/2) < 1) return c/2 * Math.pow(2, 10 * (t - 1)) + b;
            return c/2 * (-Math.pow(2, -10 * --t) + 2) + b;
        },
        easeInCirc: function (x, t, b, c, d) {
            return -c * (Math.sqrt(1 - (t/=d)*t) - 1) + b;
        },
        easeOutCirc: function (x, t, b, c, d) {
            return c * Math.sqrt(1 - (t=t/d-1)*t) + b;
        },
        easeInOutCirc: function (x, t, b, c, d) {
            if ((t/=d/2) < 1) return -c/2 * (Math.sqrt(1 - t*t) - 1) + b;
            return c/2 * (Math.sqrt(1 - (t-=2)*t) + 1) + b;
        },
        easeInElastic: function (x, t, b, c, d) {
            var s=1.70158;var p=0;var a=c;
            if (t==0) return b;  if ((t/=d)==1) return b+c;  if (!p) p=d*.3;
            if (a < Math.abs(c)) { a=c; var s=p/4; }
            else var s = p/(2*Math.PI) * Math.asin (c/a);
            return -(a*Math.pow(2,10*(t-=1)) * Math.sin( (t*d-s)*(2*Math.PI)/p )) + b;
        },
        easeOutElastic: function (x, t, b, c, d) {
            var s=1.70158;var p=0;var a=c;
            if (t==0) return b;  if ((t/=d)==1) return b+c;  if (!p) p=d*.3;
            if (a < Math.abs(c)) { a=c; var s=p/4; }
            else var s = p/(2*Math.PI) * Math.asin (c/a);
            return a*Math.pow(2,-10*t) * Math.sin( (t*d-s)*(2*Math.PI)/p ) + c + b;
        },
        easeInOutElastic: function (x, t, b, c, d) {
            var s=1.70158;var p=0;var a=c;
            if (t==0) return b;  if ((t/=d/2)==2) return b+c;  if (!p) p=d*(.3*1.5);
            if (a < Math.abs(c)) { a=c; var s=p/4; }
            else var s = p/(2*Math.PI) * Math.asin (c/a);
            if (t < 1) return -.5*(a*Math.pow(2,10*(t-=1)) * Math.sin( (t*d-s)*(2*Math.PI)/p )) + b;
            return a*Math.pow(2,-10*(t-=1)) * Math.sin( (t*d-s)*(2*Math.PI)/p )*.5 + c + b;
        },
        easeInBack: function (x, t, b, c, d, s) {
            if (s == undefined) s = 1.70158;
            return c*(t/=d)*t*((s+1)*t - s) + b;
        },
        easeOutBack: function (x, t, b, c, d, s) {
            if (s == undefined) s = 1.70158;
            return c*((t=t/d-1)*t*((s+1)*t + s) + 1) + b;
        },
        easeInOutBack: function (x, t, b, c, d, s) {
            if (s == undefined) s = 1.70158; 
            if ((t/=d/2) < 1) return c/2*(t*t*(((s*=(1.525))+1)*t - s)) + b;
            return c/2*((t-=2)*t*(((s*=(1.525))+1)*t + s) + 2) + b;
        },
        easeInBounce: function (x, t, b, c, d) {
            return c - jQuery.easing.easeOutBounce (x, d-t, 0, c, d) + b;
        },
        easeOutBounce: function (x, t, b, c, d) {
            if ((t/=d) < (1/2.75)) {
                return c*(7.5625*t*t) + b;
            } else if (t < (2/2.75)) {
                return c*(7.5625*(t-=(1.5/2.75))*t + .75) + b;
            } else if (t < (2.5/2.75)) {
                return c*(7.5625*(t-=(2.25/2.75))*t + .9375) + b;
            } else {
                return c*(7.5625*(t-=(2.625/2.75))*t + .984375) + b;
            }
        },
        easeInOutBounce: function (x, t, b, c, d) {
            if (t < d/2) return jQuery.easing.easeInBounce (x, t*2, 0, c, d) * .5 + b;
            return jQuery.easing.easeOutBounce (x, t*2-d, 0, c, d) * .5 + c*.5 + b;
        }
    });
    //}}}


    // 滚轮事件插件{{{
    (function() {
        var types = ['DOMMouseScroll', 'mousewheel'];

        if ($.event.fixHooks) {
            for ( var i=types.length; i; ) {
                $.event.fixHooks[ types[--i] ] = $.event.mouseHooks;
            }
        }

        $.event.special.mousewheel = {
            setup: function() {
                if ( this.addEventListener ) {
                    for ( var i=types.length; i; ) {
                        this.addEventListener( types[--i], handler, false );
                    }
                } else {
                    this.onmousewheel = handler;
                }
            },

            teardown: function() {
                if ( this.removeEventListener ) {
                    for ( var i=types.length; i; ) {
                        this.removeEventListener( types[--i], handler, false );
                    }
                } else {
                    this.onmousewheel = null;
                }
            }
        };

        $.fn.extend({
            mousewheel: function(fn) {
                return fn ? this.bind("mousewheel", fn) : this.trigger("mousewheel");
            },

            unmousewheel: function(fn) {
                return this.unbind("mousewheel", fn);
            }
        });

        function handler(event) {
            var orgEvent = event || window.event, args = [].slice.call( arguments, 1 ), delta = 0, returnValue = true, deltaX = 0, deltaY = 0;
            event = $.event.fix(orgEvent);
            event.type = "mousewheel";

            // Old school scrollwheel delta
            if ( orgEvent.wheelDelta ) { delta = orgEvent.wheelDelta/120; }
            if ( orgEvent.detail     ) { delta = -orgEvent.detail/3; }

            // New school multidimensional scroll (touchpads) deltas
            deltaY = delta;

            // Gecko
            if ( orgEvent.axis !== undefined && orgEvent.axis === orgEvent.HORIZONTAL_AXIS ) {
                deltaY = 0;
                deltaX = -1*delta;
            }

            // Webkit
            if ( orgEvent.wheelDeltaY !== undefined ) { deltaY = orgEvent.wheelDeltaY/120; }
            if ( orgEvent.wheelDeltaX !== undefined ) { deltaX = -1*orgEvent.wheelDeltaX/120; }

            // Add event and delta to the front of the arguments
            args.unshift(event, delta, deltaX, deltaY);

            return ($.event.dispatch || $.event.handle).apply(this, args);
        }

    })();
    //}}}


    var scrollBarIdx = 0;  // 用于惟一标识每个scrollbar
    var selectIdx = 0;    // 用于惟一标识每个select
    $.fn.extend({

        // html scrollbar 自定义控件 {{{
        scrollBarPlugin: function(options){
            var defaults = {
                scrollCon: 'data-scrollCon',
                scrollWrap: 'data-scrollWrap',
                scrollDrag: 'data-scrollDrag',
                scrollUp: 'data-scrollUp',
                scrollDown: 'data-scrollDown',
                scrollConCls: 'scrollBarCon',   // 滚动内容主体
                scrollWrapCls: 'scrollBarWrap', // 滚动块主体
                scrollDragCls: 'scrollBarDrag', // 滚动块拖动体
                scrollUpCls: 'scrollBarUp',     // 向上移动
                scrollDownCls: 'scrollBarDown', // 向下移动
                scrollStep: 30,  // 滚动步长
                scrollMaxHeight: 'data-scrollMaxHeight'  // 超过高度，即滚动
            };
            options = $.extend(defaults, options);
            $(this).each(function(){
                var $this = $(this);
                // 计算idx，防止重新解绑/绑定事件冲突，如：多个绑定document
                var thisIdx = parseInt($this.data('scrollBarIdx')) || 0;
                if (thisIdx < 1) {
                    ++scrollBarIdx;
                    thisIdx = scrollBarIdx;
                    $this.attr('scrollBarIdx', thisIdx);
                }
                var $bindLabel = '.scrollBarPlugin_' + thisIdx;

                var scrollConCls = $this.attr(options.scrollCon) || options.scrollConCls;
                var scrollWrapCls = $this.attr(options.scrollWrap) || options.scrollWrapCls;
                var scrollDragCls = $this.attr(options.scrollDrag) || options.scrollDragCls;
                var scrollUpCls   = $this.attr(options.scrollUp) || options.scrollUpCls;
                var scrollDownCls = $this.attr(options.scrollDown) || options.scrollDownCls;
                var $scrollCon = $this.find('.' + scrollConCls);
                var $scrollWrap = $this.find('.' + scrollWrapCls);
                var $scrollDrag = $this.find('.' + scrollDragCls);
                var $scrollUp   = $this.find('.' + scrollUpCls);
                var $scrollDown = $this.find('.' + scrollDownCls);
                var scrollMaxHeight = $this.attr(options.scrollMaxHeight);

                // 关键节点检测 开始
                var kItemArr = [
                    [scrollConCls, $scrollCon],
                    [scrollWrapCls, $scrollWrap],
                    [scrollDragCls, $scrollDrag]
                ];
                for (var k=0,klen=kItemArr.length; k<klen; k++) {
                    if (!kItemArr[k][1].length) throw '[.' + kItemArr[k][0] + '] no exist';
                }
                // 关键节点检测 结束

                var cHeight = $scrollCon.outerHeight(true);
                // 超过高度自动显示滚动条处理
                if (typeof scrollMaxHeight != 'undefined') {
                    scrollMaxHeight = parseInt(scrollMaxHeight) || 0;
                    if (scrollMaxHeight > 0 && (cHeight > scrollMaxHeight)) {
                        $scrollCon.parent().css({height:scrollMaxHeight+'px', overflow:'hidden'});
                        $scrollWrap.css({height:scrollMaxHeight+'px'});
                        $scrollWrap.find('.selectScrollTrack').css({height:scrollMaxHeight-5+'px'});
                    } else {
                        $scrollCon.parent().css({height:cHeight+'px'});
                        $scrollWrap.css({height:cHeight+'px'});
                        $scrollWrap.find('.selectScrollTrack').css({height:cHeight-5+'px'});
                    }
                }

                var cMaxHeight = cHeight - $this.height();
                if (cMaxHeight > 0) {
                    // 有滚动条
                    var dMaxHeight = $scrollDrag.parent().height() - $scrollDrag.height();
                    var dRate      = dMaxHeight / cMaxHeight;
                    var dragArr = {
                        hasDrag: false,
                        oldX: 0,
                        oldY: 0,
                        nTop: 0,
                        dragDown: function(evt, label){
                            dragArr.hasDrag = true;
                            dragArr.oldX = evt.type == 'touchstart' ? evt.originalEvent.targetTouches[0].pageX : evt.pageX;
                            dragArr.oldY = evt.type == 'touchstart' ? evt.originalEvent.targetTouches[0].pageY : evt.pageY;
                            dragArr.nTop = label && label == 'touchpane' ? (parseInt($scrollCon.css('top')) || 0) : (parseInt($(this).css('top')) || 0);
                        },
                        dragMove: function(evt, label){
                            var currPageY = evt.type == 'touchmove' ? evt.originalEvent.targetTouches[0].pageY : evt.pageY;
                            if (dragArr.hasDrag) {
                                if (label && label == 'touchpane') {
                                    // 拖动内容
                                    var diffY = dragArr.nTop + currPageY - dragArr.oldY;
                                    var newTop = diffY;
                                    if (diffY > 0) {
                                        newTop = 0;
                                    } else {
                                        if (Math.abs(diffY) > cMaxHeight) {
                                            newTop = '-' + cMaxHeight;
                                        }
                                    }
                                    $scrollDrag.css({top:Math.abs(newTop)*dRate+'px'});
                                    $scrollCon.css({top:newTop+'px'}).trigger('scroll');
                                } else {
                                    // 拖动条
                                    var diffY = dragArr.nTop + currPageY - dragArr.oldY;
                                    var newTop = diffY;
                                    if (diffY > 0) {
                                        // 向下拖，要向上滚
                                        if (diffY >= dMaxHeight) {
                                            newTop = dMaxHeight;
                                        }
                                    } else {
                                        // 向上拖，要向下滚
                                        newTop = 0;
                                    }
                                    $scrollDrag.css({top:newTop+'px'});
                                    $scrollCon.css({top:'-'+newTop/dRate+'px'}).trigger('scroll');
                                }
                            }
                        },
                        dragUp: function(evt){
                            dragArr.hasDrag = false;
                            $(this).unbind('mousemove'+$bindLabel+' touchmove'+$bindLabel);
                        }
                    };

                    $scrollCon.css({top:0}).trigger('scroll');
                    $this.unbind('mousewheel.scrollBarPlugin').bind('mousewheel.scrollBarPlugin', function(evt, delta){
                        var nTop = parseInt($scrollCon.css('top')) || 0;
                        var diffTop = nTop + delta * options.scrollStep;
                        var newTop  = diffTop;
                        if (delta < 0) {
                            // 向下滚
                            if (Math.abs(diffTop) >= cMaxHeight) {
                                newTop = 0 - cMaxHeight;
                            }
                        } else {
                            // 向上滚
                            if (diffTop >= 0) {
                                newTop = 0;
                            }
                        }
                        $scrollCon.css({top:newTop+'px'}).trigger('scroll');
                        $scrollDrag.css({top:(0-dRate*newTop)+'px'});

                        return false;
                    }).unbind('touchstart.scrollBarPlugin').bind('touchstart.scrollBarPlugin', function(evt){
                        // 鼠标按下
                        dragArr.dragDown.call(this, evt, 'touchpane');
                        $(document).unbind('mousemove'+$bindLabel+' touchmove'+$bindLabel).bind('mousemove'+$bindLabel+' touchmove'+$bindLabel, function(evt){
                            // 鼠标拖动
                            dragArr.dragMove.call(this, evt, 'touchpane');
                            return false;
                        });
                    });

                    // 向上滚动
                    if ($scrollUp.length) {
                        $scrollUp.bind('click.scrollBarPlugin', function(){
                            var nTop = parseInt($scrollCon.css('top')) || 0;
                            var diffTop = nTop + 10;
                            var newTop  = diffTop;
                            if (diffTop >= 0) {
                                newTop = 0;
                            }
                            $scrollCon.css({top:newTop+'px'}).trigger('scroll');
                            $scrollDrag.css({top:(0-dRate*newTop)+'px'});
                            return false;
                        });
                    }
                    // 向下滚动
                    if ($scrollDown.length) {
                        $scrollDown.bind('click.scrollBarPlugin', function(){
                            var nTop = parseInt($scrollCon.css('top')) || 0;
                            var diffTop = nTop - 10;
                            var newTop  = diffTop;
                            if (Math.abs(diffTop) >= cMaxHeight) {
                                newTop = 0 - cMaxHeight;
                            }
                            $scrollCon.css({top:newTop+'px'}).trigger('scroll');
                            $scrollDrag.css({top:(0-dRate*newTop)+'px'});
                            return false;
                        });
                    }

                    // 拖动滚动处理
                    $scrollDrag.css({top:0});
                    $scrollDrag.unbind('mousedown.scrollBarPlugin touchstart.scrollBarPlugin').bind('mousedown.scrollBarPlugin touchstart.scrollBarPlugin', function(evt){
                        // 鼠标按下
                        dragArr.dragDown.call(this, evt);
                        $(document).unbind('mousemove'+$bindLabel+' touchmove'+$bindLabel).bind('mousemove'+$bindLabel+' touchmove'+$bindLabel, function(evt){
                            // 鼠标拖动
                            dragArr.dragMove.call(this, evt);
                            return false;
                        });
                        return false;
                    });
                    // 鼠标放开
                    $(document).unbind('mouseup'+$bindLabel+' touchend'+$bindLabel).bind('mouseup'+$bindLabel+' touchend'+$bindLabel, function(evt){
                        dragArr.dragUp.call(this, evt);
                    });

                    $scrollWrap.show().unbind('click.scrollBarPlugin').bind('click.scrollBarPlugin', function(evt){
                        return false;
                    });
                } else {
                    // 没有滚动条
                    $scrollWrap.hide();
                    $scrollCon.css({top:0}).trigger('scroll');
                    $this.unbind('mousewheel.scrollBarPlugin');
                    $scrollDrag.unbind('mousedown.scrollBarPlugin').unbind('touchstart.scrollBarPlugin');
                    $scrollWrap.unbind('click.scrollBarPlugin');
                    $(document).unbind('mouseup'+$bindLabel).unbind('touchend'+$bindLabel);
                }
            });
        },
        //}}}

        // html select 自定义控件 {{{
        selectPlugin: function(options){
            var defaults = {
                dataId: 'data-id',                // 节点id所在属性名
                dataName: 'data-name',            // 节点节点所在属性名
                dataValue: 'data-value',          // 节点节点所在值
                valueName: 'data-value',               // 下拉项中值的属性名
                triggerType: 'data-triggerType',  // 列表下拉触发方式属性值 mouse:鼠标经过 click:鼠标点击
                showSelected: 'data-showSelected',// 是否显示选中的值
                valueCls: 'selectMain',           // 节点值所在节点类
                listWrapCls: 'selectWrap',        // 列表下拉包裹类
                listItemSelectedCls: 'selected',  // 列表下拉项选中类
                scrollWrapCls: 'selectScrollWrap',// 列表下拉滚动条wrap
                scrollDragCls: 'selectScrollDrag', // 列表下拉滚动条 拖动类
                focusCls: 'selectShow'             // 打开下拉时，添加的类
            };
            options = $.extend(defaults, options);
            $(this).each(function(){
                var $this = $(this);
                // 计算idx，防止重新解绑/绑定事件冲突，如：多个绑定document
                var thisIdx = parseInt($this.data('selectIdx')) || 0;
                if (thisIdx < 1) {
                    ++selectIdx;
                    thisIdx = selectIdx;
                    $this.attr('selectIdx', thisIdx);
                }
                var $bindLabel = '.selectPlugin_' + thisIdx;

                var openState = false;
                var $selectWrap = $this.find('.' + options.listWrapCls);
                var $selectWrapUl = $selectWrap.find('ul');
                var $valueItem  = $this.find('.' + options.valueCls);
                var $scrollWrap = $this.find('.' + options.scrollWrapCls);
                var $scrollDragItem = $this.find('.' + options.scrollDragCls);

                // 关键节点检测 开始
                var kItemArr = [
                    [options.listWrapCls, $selectWrap],
                    [options.listWrapCls+' ul', $selectWrapUl],
                    [options.valueCls, $valueItem],
                    [options.scrollWrapCls, $scrollWrap],
                    [options.scrollDragCls, $scrollDragItem]
                ];
                for (var k=0,klen=kItemArr.length; k<klen; k++) {
                    if (!kItemArr[k][1].length) throw '[.' + kItemArr[k][0] + '] no exist';
                }
                // 关键节点检测 结束

                var fn_close = function(){
                    $this.removeClass(options.focusCls);
                    //$valueItem.removeClass('selOn');
                    $selectWrap.hide();
                    openState = false;
                };

                var zIndex = parseInt($this.css('zIndex')) || 100;

                var fn_open = function(){
                    $this.addClass(options.focusCls).css('zIndex', zIndex);
                    //$valueItem.addClass('selOn');

                    var $liWrap = $selectWrap.find('li').length;
                    if ($liWrap > 1) {
                        $selectWrap.show();
                        openState = true;

                        // 下位滚动处理
                        $selectWrap.scrollBarPlugin({scrollConCls:'selectCon ul', scrollWrapCls:'selectScrollWrap', scrollDragCls:'selectScrollDrag'});
                    }
                };

                var fn_init = function(){
                    var name = $this.attr(options.dataName);
                    var value = $this.attr(options.dataValue);
                    if (typeof(name) != 'undefined') {
                        if (typeof(value) == 'undefined') {
                            $this.attr(options.dataValue, '');
                        }

                        var $item = $this.find('li a['+options.valueName+'='+value+']');
                        if ($item.length) {
                            $item.trigger('click');
                        } else {
                            $this.find('li a:eq(0)').trigger('click');
                        }
                    }

                };

                var triggerType = $this.attr(options.triggerType);
                switch (triggerType) {
                    case 'click':
                        $this.unbind('click.selectPlugin').bind('click.selectPlugin', function(){
                            // 已打开，则关闭
                            if (openState) {
                                fn_close();
                            } else {
                                fn_open();
                            }
                        });
                        break;
                    default:
                        var mouseHandle = null;
                        $this.unbind('mouseover.selectPlugin').bind('mouseover.selectPlugin', function(){
                            // 打开
                            if (mouseHandle) {clearTimeout(mouseHandle, 0);mouseHandle=null;}
                            fn_open();
                        }).unbind('mouseout.selectPlugin').bind('mouseout.selectPlugin', function(){
                            // 关闭
                            if (mouseHandle) {clearTimeout(mouseHandle, 0);mouseHandle=null;}
                            mouseHandle = setTimeout(fn_close, 100);
                        });
                }

                var showSelected = parseInt($this.attr(options.showSelected)) || 0;

                $this.undelegate('ul li a', 'click.selectPlugin').delegate('ul li a', 'click.selectPlugin', function(e){
                    var id    = $this.attr(options.dataId);
                    var name  = $this.attr(options.dataName);
                    var value = $(this).attr(options.valueName);
                    var txt   = $(this).text();
                    var $val  = $this.find('input[name="'+name+'"]');
                    var defval= $val.val();

                    value = typeof value == 'undefined' ? '' : value;
                    // 生成隐藏控件
                    if ($val.length) {
                        $val.val(value);
                    } else if (name) {
                        if (id) {
                            $val = $('<input type="hidden" value="'+value+'" id="'+id+'" name="'+name+'" />').appendTo($this);
                        } else {
                            $val = $('<input type="hidden" value="'+value+'" name="'+name+'" />').appendTo($this);
                        }
                    }

                    // 显示选择的值
                    $valueItem.attr(options.valueName, value).text(txt);
                    $this.find('li').removeClass(options.listItemSelectedCls).show();
                    if (showSelected) {
                        $(this).parent().addClass(options.listItemSelectedCls).show();
                    } else {
                        $(this).parent().addClass(options.listItemSelectedCls).hide();
                    }
                    $selectWrap.hide();

                    // 是否改变值
                    if (value != defval) {
                        if ($val.length) {
                            $val.trigger('change', value);
                        }
                        $this.trigger('change', value);
                    }

                    fn_close();
                    return false;
                }).unbind('replaceAllOption.selectPlugin').bind('replaceAllOption.selectPlugin', function(e, dataArr){
                    var def_value  = $(this).attr(options.dataValue);
                    var selectedVal = (typeof(def_value) == 'undefined') ? '' : def_value;
                    if (dataArr) {
                        var htmlTpl = [];
                        for (var i in dataArr) {
                            if (i == selectedVal) {
                                if (showSelected) {
                                    htmlTpl.push('<li class="selected"><a '+options.valueName+'="' + i + '" href="javascript:;">' + dataArr[i] + '</a></li>');
                                } else {
                                    htmlTpl.push('<li class="selected" style="display:none;"><a '+options.valueName+'="' + i + '" href="javascript:;">' + dataArr[i] + '</a></li>');
                                }
                                htmlTplVal = [i,dataArr[i]];
                            } else {
                                htmlTpl.push('<li><a '+options.valueName+'="' + i + '" href="javascript:;">' + dataArr[i] + '</a></li>');
                            }
                        }
                        $selectWrap.find('ul').html(htmlTpl.join(''));
                        var $firstItem = $selectWrap.find('.selected a');
                        if (!$firstItem.length) {
                            $firstItem = $selectWrap.find('li a:eq(0)');
                        }
                        $firstItem.trigger('click');

                        return false;
                    }
                }).unbind('replaceAllOption2.selectPlugin').bind('replaceAllOption2.selectPlugin', function(e, dataArr){
                    var def_value  = $(this).attr(options.dataValue);
                    var selectedVal = (typeof(def_value) == 'undefined') ? '' : def_value;
                    if (dataArr.length > 0) {
                        var htmlTpl = [];
                        for (var i=0,len=dataArr.length; i<len; i++) {
                            if (dataArr[i][0] == selectedVal) {
                                if (showSelected) {
                                    htmlTpl.push('<li class="selected"><a '+options.valueName+'="' + dataArr[i][0] + '" href="javascript:;">' + dataArr[i][1] + '</a></li>');
                                } else {
                                    htmlTpl.push('<li class="selected" style="display:none;"><a '+options.valueName+'="' + dataArr[i][0] + '" href="javascript:;">' + dataArr[i][1] + '</a></li>');
                                }
                                htmlTplVal = [i,dataArr[i][1]];
                            } else {
                                htmlTpl.push('<li><a '+options.valueName+'="' + i + '" href="javascript:;">' + dataArr[i][1] + '</a></li>');
                            }
                        }
                        $selectWrap.find('ul').html(htmlTpl.join(''));
                        var $firstItem = $selectWrap.find('.selected a');
                        if (!$firstItem.length) {
                            $firstItem = $selectWrap.find('li a:eq(0)');
                        }
                        $firstItem.trigger('click');

                        return false;
                    }
                }).unbind('selectOption.selectPlugin').bind('selectOption.selectPlugin', function(e, val){
                    $(this).find('li a[' + options.valueName + '="' + val + '"]').trigger('click');
                    return false;
                }).unbind('selectOptionIndex.selectPlugin').bind('selectOptionIndex.selectPlugin', function(e, idx){
                    $(this).find('li:eq('+idx+') a').trigger('click');
                    return false;
                });

                // 初始化默认值
                fn_init();

                // 绑定点击隐藏下拉事件
                $(document).unbind('click'+$bindLabel).bind('click'+$bindLabel, function(e){
                    if (e.target != $valueItem.get(0)) {
                        fn_close();
                    }
                });
            });
        },
        //}}}

        // 图片延时加载 lazyload{{{
        lazyLoadPlugin: function(options){
            var defaults = {
                threshold   : 0,               // 额外显示长度
                failureLimit: 0,               // 失败限制
                triggerType : 'scroll',        // 触发检测类型
                effect      : 'show',          // 显示效果
                effectSpeed : 0,               // 显示动画时长
                container   : window,          // 容器视窗（触发事件绑定在该节点上）
                skipHidden  : true,            // 隐藏图片不检测，待显示时检测(检测需手动触发)
                placeholder : '',              // 图片占位符
                original    : 'data-original'  // 图片真正的路径
            };
            options = $.extend(defaults, options||{});

            var $container = $(options.container);
            var $elements = $(this);  // 储存待显示图片的节点列表
            // 检测对象相对窗口位置
            var checkBorder = function(direction, $element, $container){
                var elemP = $element.offset();
                var contP = $container.offset();
                var elemT = 0, contT = 0;
                switch (direction) {
                    case 'top': // 是否在窗口上面
                        elemT = (elemP && elemP.top ? elemP.top : 0) + $element.outerHeight();
                        contT = (contP && contP.top ? contP.top : 0) + (parseInt($container.scrollTop()) || 0) - options.threshold;
                        //console.log('top', elemT, contT, contT >= elemT);
                        return contT >= elemT;
                        break;
                    case 'bottom': // 是否在窗口下面
                        elemT = elemP && elemP.top ? elemP.top : 0;
                        contT = (contP && contP.top ? contP.top : 0) + (parseInt($container.scrollTop()) || 0) + $container.outerHeight() + options.threshold;
                        //console.log('bottom', elemT, contT, contT <= elemT);
                        return contT <= elemT;
                        break;
                    case 'left': // 是否在窗口左边
                        elemT = (elemP && elemP.left ? elemP.left : 0) + $element.outerWidth();
                        contT = (contP && contP.left ? contP.left : 0) + (parseInt($container.scrollLeft()) || 0) - options.threshold;
                        //console.log('left', elemT, contT, contT >= elemT);
                        return contT >= elemT;
                        break;
                    case 'right': // 是否在窗口右边
                        elemT = (elemP && elemP.left ? elemP.left : 0);
                        contT = (contP && contP.left ? contP.left : 0) + (parseInt($container.scrollLeft()) || 0) + $container.outerWidth() + options.threshold;
                        //console.log('right', elemT, contT, contT <= elemT);
                        return contT <= elemT;
                        break;
                }

                return false;
            };

            // 检测需要延时加载的图片
            $(this).each(function(){
                var that = this;
                var $that = $(this);
                that.loaded = false;

                // img src默认值
                if ($that.is('img') && $that.attr("src") == undefined) {
                     $self.attr("src", settings.placeholder);
                }

                // 加载原始图片
                $that.unbind('appear.lazyLoadPlugin').one('appear.lazyLoadPlugin', function(){
                    if (this.loaded) return false;

                    // 原始图片url
                    var original = $that.attr(options.original);
                    $('<img />').bind('load', function(){
                        $that.hide();
                        if ($that.is('img')) {
                            // 替换图片
                            $that.attr('src', original);
                        } else {
                            // 替换背景
                            $that.css('background-image', 'url(' + original + ')');
                        }
                        $that[options.effect](options.effectSpeed);
                        that.loaded = true;

                        // 过滤加载完的图片对象
                        var temp = $.grep($elements, function(element) {
                            return !element.loaded
                        });
                        $elements = $(temp);
                    }).attr("src", original);

                    return false;
                });
            });

            // 触发检测需要显示的图片
            var triggerHandle = null;
            $container.bind(options.triggerType+'.lazyLoadPlugin', function(evt){
                var $that = $(this);
                if (triggerHandle) {
                    clearTimeout(triggerHandle);
                    triggerHandle = null;
                }
                triggerHandle = setTimeout(function(){$that.trigger(options.triggerType+'_omz');}, 50);
            }).bind(options.triggerType+'_omz.lazyLoadPlugin', function(evt){
                //console.log(evt, $elements.length);   // 打印剩余未初始化数量
                var noAppearNum = 0;  // 未达到显示条件的数量
                $elements.each(function(){
                    var $that = $(this);
                    // 检测是否跳过隐藏的图片
                    if (options.skipHidden && !$that.is(':visible')) return true;

                    // 检测图片是否可见区域内
                    if (checkBorder('top', $that, $container) || checkBorder('left', $that, $container)) {
                        // 上边，左边均不管
                    } else if ((!checkBorder('bottom', $that, $container)) && !checkBorder('right', $that, $container)) {
                        // 在窗口区域内，触发显示
                        $that.trigger("appear");
                        noAppearNum = 0;
                    } else {
                        // 未显示数量
                        if (noAppearNum++ > options.failureLimit) {
                            return false;
                        }
                    }
                });
                // 过滤加载完的图片对象
                var temp = $.grep($elements, function(element) {
                    return !element.loaded
                });
                $elements = $(temp);

                return false;
            }).trigger(options.triggerType);

            // window窗口大小重绘
            $(window).bind('resize.lazyLoadPlugin', function(){
                $container.trigger(options.triggerType);
            });

        }
        //}}}

    });


})(jQuery);


window.Pay = window.Pay || {};
Pay.$ = jQuery;
Pay.debug = false;

// debug显示日志函数{{{
Pay.log = (function() {
    return function(){
        if (Pay.debug && window.console && window.console.log && !Pay.$.browser.webkit) {
            console.log.apply(this, arguments);
        }
    };
})();
//}}}

// pay.getClass {{{
Pay.getClass = function(){

    //构造函数
    var thisClass = function(config) {
        config = config || {};
        this.config = this.config || {};
        this.events = this.events || [];
        $.extend(this.config, config);

        if (this.moduleName) {
            var moduleNameArr = this.moduleName.split('.');
            var module = window;
            for (var i=0,len=moduleNameArr.length; i<len; i++) {
                module = module[moduleNameArr[i]];
            }
            module['instance'] = this;
        }
        this._init(config);
        this.init(config);
    };

    //初始化
    thisClass.prototype._init = function() {
        var that = this;
        if (this.events.length) {
            var $item = null, events = this.events, bindTypeArr=[], triggerArr=[];
            for (var i=0,len=events.length; i<len; i++) {
                $item = $(events[i][0]);
                if ($item.length) {
                    bindTypeArr = events[i][1].split('|');
                    if (bindTypeArr[0] == 'delegate') {
                        // delegate绑定方式
                        $item.delegate(bindTypeArr[2], bindTypeArr[1], that[events[i][2]]);
                    } else if (bindTypeArr[0] == 'trigger') {
                        // trigger方式
                        triggerArr.push([$item, bindTypeArr[1], bindTypeArr[2]||null]);
                    } else {
                        $item.bind(events[i][1], that[events[i][2]]);
                    }
                }
            }

            // trigger处理
            if (triggerArr.length) {
                setTimeout(function(){
                    for (var i=0,len=triggerArr.length; i<len; i++) {
                        triggerArr[i][0].trigger(triggerArr[i][1], triggerArr[i][2]);
                    }
                }, 0);
            }
        };
    };

    return thisClass;
};
//}}}

// 弹窗window类{{{
Pay.windowList = {};
Pay.defaultOpt = {'closeCls': '.closePop'};
Pay.window = function(id, config) {
    if (!Pay.windowList[id]) {
        config = config || {};
        $.extend(config, Pay.defaultOpt);
        Pay.windowList[id] = new Pay.window.fn.init(id, config);
    }
    return Pay.windowList[id];
};
Pay.window.fn = Pay.window.prototype = {
    init: function(id, config) {
        var that = this;
        this.config = config;
        this.openState = false;
        this.lock = true;
        this.id = id;
        this.dom = $(id);
        config.closeCls && this.dom.delegate(config.closeCls, 'click', function(e){
            that.pre_close && that.pre_close.call(that);
            that.close();
            return false;
        });
    },
    open: function() {
        // 显示
        this.openState = true;
        this.dom.show();
        this.isShowMaskLayer();
    },
    close: function() {
        // 隐藏
        this.openState = false;
        this.dom.hide();
        this.isShowMaskLayer();
        this.dom.trigger('close');
    },
    resize: function(){
        var that = this;
        if (that.dom.css('display') != 'none') {
            var nScrollTop = $(window).scrollTop();
            $('body').attr('data', nScrollTop);
            ((!that.config.overflow) ||  (that.config.overflow != 'auto')) && $('html,body').css('overflow', 'hidden');
            var nW = that.dom.width() / 2;
            var nTop = nScrollTop + ($(window).height() - that.dom.height())/2 - 20;
            nTop = nTop < nScrollTop ? nScrollTop : nTop;
            that.dom.css('top', nTop+'px');
        }
    },
    isShowMaskLayer: function(){
        var that = this;
        var isShow = false;
        $.each(Pay.windowList, function(key, thisWindow){
            if (thisWindow.openState && thisWindow.lock) {
                isShow = true;
            }
        });
        if (isShow) {
            var nScrollTop = $(window).scrollTop();
            $('body').attr('data', nScrollTop);
            ((!that.config.overflow) ||  (that.config.overflow != 'auto')) && $('html,body').css('overflow', 'hidden');
            var nW = this.dom.width() / 2;
            var nTop = nScrollTop + ($(window).height() - this.dom.height())/2 - 20;
            nTop = nTop < nScrollTop ? nScrollTop : nTop;
            this.dom.css('top', nTop+'px');
            var nLeft = ($(window).width() - this.dom.outerWidth()) / 2;
            this.dom.css({'left': nLeft +'px'});

            var alp=$("#alpha");
            //var alpH=(document.documentElement.clientHeight || document.body.clientHeight) + (document.documentElement.scrollTop || document.body.scrollTop) + 50;
            //alp.css({"opacity":0.8,"height":alpH+"px"});
            alp.css({"opacity":0.8,"height":"2220px"});
            $('#alpha').show();
        } else {
            $('html,body').attr('style', '');
            var bodyScrolltop = $('body').attr('data');
            if (bodyScrolltop && bodyScrolltop !='') {
                $(window).scrollTop(bodyScrolltop);
            }
            $('#alpha').hide();
        }
        $(window).bind('scroll', function(){
            $('.popscroll').each(function(){
                if ($(this).css('display') != 'none') {
                    var nScrollTop = $(window).scrollTop();
                    $('body').attr('data', nScrollTop);
                    ((!that.config.overflow) ||  (that.config.overflow != 'auto')) && $('html,body').css('overflow', 'hidden');
                    var nW = that.dom.width() / 2;
                    var nTop = nScrollTop + ($(window).height() - that.dom.height())/2 - 20;
                    nTop = nTop < nScrollTop ? nScrollTop : nTop;
                    $(this).css('top', nTop+'px');
                }
            });
        });
    }
};
Pay.window.fn.init.prototype = Pay.window.fn;
//}}}

// 工具函数库{{{
Pay.Ulib = Pay.Ulib || {};
Pay.$.extend(Pay.Ulib, {
    isArray: function(obj){
        return Object.prototype.toString.call(obj).toLowerCase() == '[object array]';
    },
    isObject: function(obj){
        return Object.prototype.toString.call(obj).toLowerCase() == '[object object]';
    },
    // 设置cookie
    setCookie: function(name, value, time) {
        var nameString = name + '=' + escape(value);
        var expiryString = "";
        if(time !== 0) {
            var expdate = new Date();
            if(time == null || isNaN(time)) time = 60*60*1000;
            expdate.setTime(expdate.getTime() +  time);
            expiryString = ' ;expires = '+ expdate.toGMTString();
        }
        var path = " ;path =/";
        document.cookie = nameString + expiryString + path;
    },
    // 获取cookie
    getCookie: function(sName) {
        var aCookie = document.cookie.split('; ');
        for (var i=0; i < aCookie.length; i++) {
            var aCrumb = aCookie[i].split('=');
            if (sName == aCrumb[0])
                return unescape(aCrumb[1]);
        }
        return '';
    },
    // 剪切板函数
    copyToClipboard: function(txt) {
        if (!txt) return false;
        if (window.clipboardData) {
            window.clipboardData.clearData();
            window.clipboardData.setData("Text",txt);
            alert("复制成功。");
        } else if(navigator.userAgent.indexOf("Opera")!= -1) {
            window.location = txt;
        } else if (window.netscape) {
            try {
                netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
            } catch (e) {
                alert("您的firefox安全限制限制您进行剪贴板操作，请打开'about:config'将signed.applets.codebase_principal_support'设置为true'之后重试");
                return false;
            }
            var clip = Components.classes["@mozilla.org/widget/clipboard;1"].createInstance(Components.interfaces.nsIClipboard);
            if (!clip) return false;
            var trans = Components.classes["@mozilla.org/widget/transferable;1"].createInstance(Components.interfaces.nsITransferable);
            if (!trans) return false;
            trans.addDataFlavor('text/unicode');
            var str = new Object();
            var len = new Object();
            var str = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);
            var copytext = txt;
            str.data = copytext;
            trans.setTransferData("text/unicode",str,copytext.length*2);
            var clipid = Components.interfaces.nsIClipboard;
            if (!clip) return false;
            clip.setData(trans,null,clipid.kGlobalClipboard);
        }
    },
    // 截取字符串
    cutLen: function(str, width) {
        var realLength = 0, len = str.length, charCode = -1;
        for (var i = 0; i < len; i++) {
            charCode = str.charCodeAt(i);
            if (charCode >= 0 && charCode <= 128) {
                realLength += 1;
            } else {
                realLength += 2;
            }
            if (width <= realLength) {
                return str.substr(0, i+1) + '...';
            }
        }
        return str;
    },
    //时间格式化
    //
    //和PHP一样的时间戳格式化函数
    //@param  {string} format    格式
    //@param  {int}    timestamp 要格式化的时间 默认为当前时间
    //@return {string}           格式化的时间字符串
    //
    dateFormat: function(format, timestamp) {
        var a, jsdate=((timestamp) ? new Date(timestamp*1000) : new Date());
        var pad = function(n, c){
            if((n = n + "").length < c){
                return new Array(++c - n.length).join("0") + n;
            } else {
                return n;
            }
        };
        var txt_weekdays = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
        var txt_ordin = {1:"st", 2:"nd", 3:"rd", 21:"st", 22:"nd", 23:"rd", 31:"st"};
        var txt_months = ["", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"]; 

        var f = {
            // Day
            d: function(){return pad(f.j(), 2)},
            D: function(){return f.l().substr(0,3)},
            j: function(){return jsdate.getDate()},
            l: function(){return txt_weekdays[f.w()]},
            N: function(){return f.w() + 1},
            S: function(){return txt_ordin[f.j()] ? txt_ordin[f.j()] : 'th'},
            w: function(){return jsdate.getDay()},
            z: function(){return (jsdate - new Date(jsdate.getFullYear() + "/1/1")) / 864e5 >> 0},

            // Week
            W: function(){
                var a = f.z(), b = 364 + f.L() - a;
                var nd2, nd = (new Date(jsdate.getFullYear() + "/1/1").getDay() || 7) - 1;
                if(b <= 2 && ((jsdate.getDay() || 7) - 1) <= 2 - b){
                    return 1;
                } else{
                    if(a <= 2 && nd >= 4 && a >= (6 - nd)){
                        nd2 = new Date(jsdate.getFullYear() - 1 + "/12/31");
                        return date("W", Math.round(nd2.getTime()/1000));
                    } else{
                        return (1 + (nd <= 3 ? ((a + nd) / 7) : (a - (7 - nd)) / 7) >> 0);
                    }
                }
            },

            // Month
            F: function(){return txt_months[f.n()]},
            m: function(){return pad(f.n(), 2)},
            M: function(){return f.F().substr(0,3)},
            n: function(){return jsdate.getMonth() + 1},
            t: function(){
                var n;
                if( (n = jsdate.getMonth() + 1) == 2 ){
                    return 28 + f.L();
                } else{
                    if( n & 1 && n < 8 || !(n & 1) && n > 7 ){
                        return 31;
                    } else{
                        return 30;
                    }
                }
            },

            // Year
            L: function(){var y = f.Y();return (!(y & 3) && (y % 1e2 || !(y % 4e2))) ? 1 : 0},
            //o not supported yet
            Y: function(){return jsdate.getFullYear()},
            y: function(){return (jsdate.getFullYear() + "").slice(2)},

            // Time
            a: function(){return jsdate.getHours() > 11 ? "pm" : "am"},
            A: function(){return f.a().toUpperCase()},
            B: function(){
                // peter paul koch:
                var off = (jsdate.getTimezoneOffset() + 60)*60;
                var theSeconds = (jsdate.getHours() * 3600) + (jsdate.getMinutes() * 60) + jsdate.getSeconds() + off;
                var beat = Math.floor(theSeconds/86.4);
                if (beat > 1000) beat -= 1000;
                if (beat < 0) beat += 1000;
                if ((String(beat)).length == 1) beat = "00"+beat;
                if ((String(beat)).length == 2) beat = "0"+beat;
                return beat;
            },
            g: function(){return jsdate.getHours() % 12 || 12},
            G: function(){return jsdate.getHours()},
            h: function(){return pad(f.g(), 2)},
            H: function(){return pad(jsdate.getHours(), 2)},
            i: function(){return pad(jsdate.getMinutes(), 2)},
            s: function(){return pad(jsdate.getSeconds(), 2)},
            //u not supported yet

            // Timezone
            //e not supported yet
            //I not supported yet
            O: function(){
                var t = pad(Math.abs(jsdate.getTimezoneOffset()/60*100), 4);
                if (jsdate.getTimezoneOffset() > 0) t = "-" + t; else t = "+" + t;
                return t;
            },
            P: function(){var O = f.O();return (O.substr(0, 3) + ":" + O.substr(3, 2))},
            //T not supported yet
            //Z not supported yet

            // Full Date/Time
            c: function(){return f.Y() + "-" + f.m() + "-" + f.d() + "T" + f.h() + ":" + f.i() + ":" + f.s() + f.P()},
            //r not supported yet
            U: function(){return Math.round(jsdate.getTime()/1000)}
        };

        return format.replace(/[\\]?([a-zA-Z])/g, function(t, s){
            if( t!=s ){
                // escaped
                ret = s;
            } else if( f[s] ){
                // a date function exists
                ret = f[s]();
            } else{
                // nothing special
                ret = s;
            }
            return ret;
        });
    },
    getUrl: function(view, args, url) {
        url = typeof url == 'undefined' ? '/' : url;
        if (view) {
            url += view + '/';
            if (args) {
                url += args + '/';
            }
        }
        return url;
    },
    encodeUnicode: function(str) {
        // 把字符转16进制，编码
        var returnStr = '';
        var cTemp = '';
        for (var i=0,len=str.length; i<len; i++) {
            cTemp = str.charCodeAt(i).toString(16);
            returnStr += "\\u" + new Array(5-String(cTemp).length).join("0") + cTemp;
        }
        return returnStr;
    },
    encodeHtml: function(str) {
        return str.replace(/&/g,'&amp;')
                  .replace(/</g,'&lt;')
                  .replace(/>/g,'&gt;')
                  .replace(/"/g, "&quot;")
                  .replace(/'/g, "&#39;");
    },
    cursorMoveEnd: function(obj) {
        if (!obj) return false;
        obj.focus();
        var len = obj.value.length;
        if (document.selection) {
            var sel = obj.createTextRange();
            sel.moveStart('character', len);
            sel.collapse();
            sel.select();
        } else if (typeof obj.selectionStart == 'number' && typeof obj.selectionEnd == 'number') {
            obj.selectionStart = obj.selectionEnd = len;
        }
    },
    // 转serializeArray为name为值
    parseSerializeArr: function(arr) {
        var returnArr = {};
        var temp = null;
        for (var i=0, len=arr.length; i<len; i++) {
            // 数组处理
            if (/[^\[]+\[\]$/.test(arr[i].name)) {
                // 数组
                if (Pay.Ulib.isArray(returnArr[arr[i].name])) {
                    returnArr[arr[i].name].push(arr[i].value);
                } else {
                    returnArr[arr[i].name] = [arr[i].value];
                }
            } else {
                returnArr[arr[i].name] = arr[i].value;
            }
        }
        return returnArr;
    },
    // 转serializeArray为name为值
    getFormData: function(obj) {
        var $obj = $(obj);
        return Pay.Ulib.parseSerializeArr($obj.serializeArray());
    },
    getDomain: function(host) {
        var returnStr = host;
        if (host) {
            var replaceStr = '|com|cn|mobi|gov|so|co|net|org|cc|biz|info|in|la|tk|name|me|tel|tv|hk|asia|cm|xunlong|';
            var siteArr = host.split('.');
            var tempArr = [];
            for (var i=siteArr.length-1; i>=0; i--) {
                if (replaceStr.indexOf('|' + siteArr[i] + '|') == -1) {
                    tempArr.push(siteArr[i]);
                    break;
                } else {
                    tempArr.push(siteArr[i]);
                }
            }
            returnStr = '';
            for (var i=tempArr.length-1; i>=0; i--) {
                if (i == 0) {
                    returnStr += '.' + tempArr[i];
                } else {
                    returnStr += tempArr[i];
                }
            }
        }

        return returnStr;
    }
});
//}}}

