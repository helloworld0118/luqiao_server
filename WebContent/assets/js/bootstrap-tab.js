      
      function Dictionary() {
            var items = {};

            this.has = function (key) {
                return key in items;
            };

            this.set = function (key, value) {
                items[key] = value;
            };

            this.remove = function (key) {
                if (this.has(key)) {
                    delete items[key];
                    return true;
                }
                return false;
            };

            this.get = function (key) {
                return this.has(key) ? items[key] : undefined;
            };

            this.values = function () {
                var values = [];
                for (var k in items) {
                    if (this.has(k)) {
                        values.push(items[k]);
                    }
                }
                return values;
            };

            this.clear = function () {
                items = {};
            };

            this.size = function () {
                var count = 0;
                for (var prop in items) {
                    if (items.hasOwnProperty(prop)) {
                        ++count;
                    }
                }
                return count;
            };

            this.getItems = function () {
                return items;
            };
        }
        var  allmanger_obj = {
                    id: 'allmanager',
                    text: '工作台',
                    closeable: false,
                    url:"allmanager"
                }
         var tab_dictionary = new Dictionary();
         tab_dictionary.set("allmanager",allmanger_obj);
/**
 * Bootstrap tab组件封装
 * @author billjiang  qq:475572229
 * @created 2017/7/24
 *
 */
(function ($, window, document, undefined) {
    'use strict';

    var pluginName = 'tabs';

    //入口方法
    $.fn[pluginName] = function (options) {
        var self = $(this);
        if (this == null)
            return null;
        var data = this.data(pluginName);
        if (!data) {
            data = new BaseTab(this, options);
            self.data(pluginName, data);
        }
        return data;
    };


    var BaseTab = function (element, options) {
        this.$element = $(element);
        this.options = $.extend(true, {}, this.default, options);
        this.init();
    }

    //默认配置
    BaseTab.prototype.default = {
        showIndex: 0, //默认显示页索引
        loadAll: true,//true=一次全部加在页面,false=只加在showIndex指定的页面，其他点击时加载，提高响应速度

    }

    //结构模板
    BaseTab.prototype.template = {
        ul_nav: '<ul id="myTab"  class="nav nav-tabs"></ul>',
        ul_li: '<li><a href="#{0}" data-toggle="tab"><span>{1}</span></a></li>',
        ul_li_close: '<i class="icon-remove fa fa-remove closeable" title="关闭"></i>',
        div_content: '<div id="tab-content" class="tab-content"></div>',
        div_content_panel: '<div class="tab-pane fade" id="{0}"></div>'
    }

    //初始化
    BaseTab.prototype.init = function () {
        if (!this.options.data || this.options.data.length == 0) {
            console.error("请指定tab页数据");
            return;
        }
        //当前显示的显示的页面是否超出索引
        if (this.options.showIndex < 0 || this.options.showIndex > this.options.data.length - 1) {
            console.error("showIndex超出了范围");
            //指定为默认值
            this.options.showIndex = this.default.showIndex;
        }
        //清除原来的tab页
        this.$element.html("");
        this.builder(this.options.data);
    }

    //使用模板搭建页面结构
    BaseTab.prototype.builder = function (data) {
        var ul_nav = $(this.template.ul_nav);
        var div_content = $(this.template.div_content);

        for (var i = 0; i < data.length; i++) {
            //nav-tab
            var ul_li = $(this.template.ul_li.format(data[i].id, data[i].text));
            //如果可关闭,插入关闭图标，并绑定关闭事件
            $(ul_li).attr("tab-id",data[i].id);
            if (data[i].closeable) {
                var ul_li_close = $(this.template.ul_li_close);

                ul_li.find("a").append(ul_li_close);
                ul_li.find("a").append("&nbsp;");
            }

            ul_nav.append(ul_li);

            //div-content
            var div_content_panel = $(this.template.div_content_panel.format(data[i].id));


            div_content.append(div_content_panel);
        }

        this.$element.append(ul_nav);
        this.$element.append(div_content);
        this.loadData();

        this.$element.find(".nav-tabs li:eq(" + this.options.showIndex + ") a").tab("show");

    }

    BaseTab.prototype.loadData = function () {
        var self = this;

        //tab点击即加载事件
        //设置一个值，记录每个tab页是否加载过
        this.stateObj = {};
        var data = this.options.data;
        //如果是当前页或者配置了一次性全部加载，否则点击tab页时加载
        for (var i = 0; i < data.length; i++) {
            if (this.options.loadAll || this.options.showIndex == i) {

                if (data[i].url) {
                    //$("#" + data[i].id).load(data[i].url,data[i].param);
                    var html = '<iframe src="'+data[i].url+'" width="100%" height="100%" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="yes" allowtransparency="yes" class="tab_iframe" style="height: '+parseInt($(window).height()-80)+'px; width: 100%; display: inline-block;"></iframe>'
                    $("#" + data[i].id).html(html);
                    this.stateObj[data[i].id] = true;
                } else {
                    console.error("id=" + data[i].id + "的tab页未指定url");
                    this.stateObj[data[i].id] = false;
                }
            } else {
                this.stateObj[data[i].id] = false;
                (function (id, url,paramter) {
                    self.$element.find(".nav-tabs a[href='#" + id + "']").on('show.bs.tab', function () {
                        if (!self.stateObj[id]) {
                            $("#" + id).load(url,paramter);
                            self.stateObj[id] = true;
                        }
                    });
                }(data[i].id, data[i].url, data[i].paramter))
            }
        }

        //关闭tab事件
        this.$element.find(".nav-tabs li a i.closeable").each(function (index, item) {
            $(item).click(function () {
                var href = $(this).parents("a").attr("href").substring(1);
                if(self.getCurrentTabId()==href){
                    self.$element.find(".nav-tabs li:eq(0) a").tab("show");
                }
                
                $(this).parents("li").remove();
                $("#" + href).remove();
            })
        });

    }
   
    //新增一个tab页
    BaseTab.prototype.addTab=function (obj) {
        var self=this;
        //nav-tab
        var ul_li = $(this.template.ul_li.format(obj.id, obj.text));
        //如果可关闭,插入关闭图标，并绑定关闭事件
        if (obj.closeable) {
            var ul_li_close = $(this.template.ul_li_close);

            ul_li.find("a").append(ul_li_close);
            ul_li.find("a").append("&nbsp;");
        }
        $(ul_li).attr("tab-id",obj.id);
        this.$element.find(".nav-tabs:eq(0)").append(ul_li);
        //div-content
        var div_content_panel = $(this.template.div_content_panel.format(obj.id));
        this.$element.find(".tab-content:eq(0)").append(div_content_panel);
        var html = '<iframe src="'+obj.url+'" width="100%" height="100%" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="yes" allowtransparency="yes" class="tab_iframe" style="height: '+parseInt($(window).height()-80)+'px; width: 100%; display: inline-block;"></iframe>'
        $("#" + obj.id).html(html);
        this.stateObj[obj.id] = true;

        if(obj.closeable){
            this.$element.find(".nav-tabs li a[href='#" + obj.id + "'] i.closeable").click(function () {
                var href = $(this).parents("a").attr("href").substring(1);
                if(self.getCurrentTabId()==href){
                    self.$element.find(".nav-tabs li:eq(0) a").tab("show");
                    $.each($("#sidebar").find("li"),function(index,item){
                        $(item).attr('class','tabmenu'); 
                    });
                    $('li[menu-id=allmanger]').attr("class","tabmenu active");
                }
                tab_dictionary.remove(href+".html");
                $(this).parents("li").remove();
                $("#" + href).remove();
                
            })
        }
        var tab = this.$element.find(".nav-tabs a[href='#" + obj.id + "']");
        $(tab).contextmenu({
                  target:'#context-menu', 
                  before: function(e,context) {
                    if($(e.target).parent().parent().attr("tab-id")=="allmanger"){
                        $("#context-menu-remove").css("display","none");
                    }else{
                        $("#context-menu-remove").css("display","block");
                    }
                    $("#context-menu-reload").attr("tab-id",$(e.target).parent().parent().attr("tab-id"));
                    $("#context-menu-remove").attr("tab-id",$(e.target).parent().parent().attr("tab-id"));
                    
                  },
                  onItem: function(context,e) {
                    //console.info($(e.target).attr("opt"));
                    if ($(e.target).attr("opt")=="reload"){
                        var objname = $(e.target).parent().attr("tab-id")+".html";
                        $("#tabContainer").data("tabs").reload(tab_dictionary.get(objname));
                    }else if($(e.target).attr("opt")=="close"){
                        var objname = $(e.target).parent().attr("tab-id")+".html";
                        var obj = tab_dictionary.get(objname);
                        // tab_dictionary.remove(obj.id +".html");
                        $("#tabContainer").find(".nav-tabs li a[href='#" + obj.id + "'] i.closeable").click();
                    }
                  }
                });

        $(tab).tab("show");
        
    }

    //根据id获取活动也标签名
    BaseTab.prototype.find=function (tabId) {
        return this.$element.find(".nav-tabs li a[href='#" + tabId + "']").text();
    }
    
    // 删除活动页
    BaseTab.prototype.remove=function (tabId) {
    	var self=this;
        $("#" + tabId).remove();
        this.$element.find(".nav-tabs li a[href='#" + tabId + "']").parents("li").remove();
    }
    
    // 重新加载页面
    BaseTab.prototype.reload=function (obj) {
    	var self=this;
    	if(self.find(obj.id)!=null){
    		// $("#" + obj.id).remove();
      //       $(this).parents("li").remove();
    		// this.$element.find(".nav-tabs li a[href='#" + obj.id + "']").parents("li").remove();
    		//console.info($("#" + obj.id));
           //$("#" + obj.id).load(obj.url,obj.paramter);

           var html = '<iframe src="'+obj.url+'" width="100%" height="100%" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="yes" allowtransparency="yes" class="tab_iframe" style="height: '+parseInt($(window).height()-80)+'px; width: 100%; display: inline-block;"></iframe>'
           $("#" + obj.id).html(html);
            //self.addTab(obj);
    	}else{
    		self.addTab(obj);
    	}
    }

    //根据id设置活动tab页
    BaseTab.prototype.showTab=function (tabId) {
    	
        this.$element.find(".nav-tabs li a[href='#" + tabId + "']").tab("show");
    }

    //获取当前活动tab页的ID
    BaseTab.prototype.getCurrentTabId=function () {
        var href=this.$element.find(".nav-tabs li.active a").attr("href");
        href=href.substring(1);
        return href;
    }


    String.prototype.format = function () {
        if (arguments.length == 0) return this;
        for (var s = this, i = 0; i < arguments.length; i++)
            s = s.replace(new RegExp("\\{" + i + "\\}", "g"), arguments[i]);
        return s;
    };
})(jQuery, window, document)