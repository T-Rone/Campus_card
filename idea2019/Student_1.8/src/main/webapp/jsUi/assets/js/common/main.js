BUI.use(['bui/menu', 'bui/tab'], function (Menu, Tab) {
    //����ȫ�������ռ�
    var PageUtil = BUI.app('PageUtil');

    var CLS_SELECTE = 'dl-selected',//ѡ�е�ģ����ʽ
        CLS_HIDDEN = 'ks-hidden',//���ص�ģ����ʽ
        CLS_LAST = 'dl-last',//���һ��Ԫ��
        CLS_HOVER = 'dl-hover',
        CLS_ITEM = 'nav-item',
        CLS_LEFT_SLIB = 'dl-second-slib',
        CLS_TAB_ITEM = 'dl-tab-item',
        CLS_CALLAPSE = 'dl-collapse',
        CLS_HIDE_CURRENT = 'dl-hide-current',
        ATTTR_INDEX = 'data-index',
        WIDTH_ITERM = 145;

    function setTopManager(mainPageObj) {
        window.topManager = mainPageObj;
    }

    function addSearch(href, search) {
        if (href.indexOf('?') !== -1) {
            return href + '&' + search;
        } else {
            return href + '?' + search;
        }
    }

    //�����˵���Tab�����󶨹���,�Ƿ��������Ƿ�����ҳ
    function tabNav(moduleId, tabConfig, menuConfig, collapsed, homePage) {

        var _self = this,
            menu = new Menu.SideMenu(menuConfig),
            tab = new Tab.NavTab(tabConfig),
            menuContainerEl = $(menuConfig.render),
            slibEl = menuContainerEl.next('.' + CLS_LEFT_SLIB + '-con'),
            navContainerEl = menuContainerEl.parents('.' + CLS_TAB_ITEM);

        if (slibEl) {
            slibEl.on('click', function () {
                navContainerEl.toggleClass(CLS_CALLAPSE);
            });
            slibEl.parent().height(tabConfig.height);
        }
        if (collapsed) {
            navContainerEl.addClass(CLS_CALLAPSE);
        }

        //����˵����л�Tab����ˢ��
        menu.on('menuclick', function (ev) {
            var item = ev.item;
            if (item) {
                _self.tab.addTab({
                    id: item.get('id'),
                    title: item.get('text'),
                    href: item.get('href'),
                    closeable: item.get('closeable')
                }, true);
            }

        });

        //ѡ�еĲ˵������ı�󣬸��������ϵ�ҳ����
        menu.on('itemselected', function (ev) {
            var item = ev.item;
            if (item) {
                setNavPosition(moduleId, item.get('id'));
            }

        });

        //�л�Tab����˵�
        tab.on('activeChange', function (ev) {
            var item = ev.item;
            if (item) {
                _self.menu.setSelectedByField(item.get('id'));
            } else {
                _self.menu.clearSelection();
            }

        });

        _self.tab = tab;
        _self.menu = menu;
        _self.homePage = homePage;
        tab.render();
        menu.render();

    }

    //���ĵ�ַ������
    function setNavPosition(moduleId, pageId) {
        pageId = pageId || '';

        var str = '#' + moduleId;

        if (pageId) {
            str += '/' + pageId;
        }
        location.hash = str;
    }

    function getNavPositionSetting() {
        var pos = location.hash,
            moduleIndex = 0,
            pageId = '',
            splitIndex = pos.indexOf('/'),
            search = null;
        if (!pos) {
            return null;
        }

        if (splitIndex >= 0) {
            moduleIndex = pos.substring(1, splitIndex);
            pageId = pos.substring(splitIndex + 1);
            search = getParam(pageId);
            if (search) {
                pageId = pageId.replace('?' + search, '');
            }
        } else {
            moduleIndex = pos.substring(1);
        }

        return {moduleId: moduleIndex, pageId: pageId, search: search};
    }

    function getParam(pageId) {
        var index = pageId.indexOf('?');
        if (index >= 0) {
            return pageId.substring(index + 1);
        }
        return null;
    }

    //����Ȩ��ϵͳ������ ��,�������Bug
    function initModuleConfig(mconfig) {
        if (!$.isArray(mconfig)) {
            return;
        }
        var emptyIndex = findEmptyIndex(mconfig);
        while (emptyIndex !== -1) {
            mconfig.splice(emptyIndex, 1);
            emptyIndex = findEmptyIndex(mconfig);
        }
        return mconfig;
    }

    //����Ϊ�յļ�¼
    function findEmptyIndex(array) {
        var result = -1;
        $.each(array, function (index, item) {
            if (item === null || item === undefined) {
                result = index;
                return false;
            }
        });
        return result;
    }

    //��ȡ�û���������
    function getAutoHeight() {
        var height = BUI.viewportHeight(),
            subHeight = 70;
        return height - subHeight;
    }

    function findItem(element) {
        var el = $(element);
        if (el.hasClass(CLS_ITEM)) {
            return element;
        }
        return el.parent('.' + CLS_ITEM)[0];
    }

    var mainPage = function (config) {
        initModuleConfig(config);
        mainPage.superclass.constructor.call(this, config);
        this._init();
        setTopManager(this);
    };

    mainPage.ATTRS = {
        /**
         * ��ǰģ�������
         * @type {Number}
         */
        currentModelIndex: {},
        hideItmes: {
            value: []
        },
        //���ص������б�
        hideList: {},
        /**
         * ģ�鼯��
         * @type {Array}
         */
        modules: {
            value: []
        },
        /**
         * ģ���������
         * @type {Array}
         */
        modulesConfig: {},
        /**
         * һ������������
         * @type {jQuery}
         */
        navList: {
            valueFn: function () {
                return $('#J_Nav');// body...
            }
        },
        /**
         * �������ݵ�����
         * @type {jQuery}
         */
        navContent: {
            valueFn: function () {
                return $('#J_NavContent');
            }
        },
        /**
         * ������
         * @type {jQuery}
         */
        navItems: {
            valueFn: function () {
                return $('#J_Nav').children('.' + CLS_ITEM);// body...
            }
        },
        navTabs: {
            valueFn: function () {
                return this.get('navContent').children('.' + CLS_TAB_ITEM)
            }
        },
        /**
         * ҳ��ĺ�׺
         * @type {Object}
         */
        urlSuffix: {
            value: '.html'
        }
    };

    BUI.extend(mainPage, BUI.Base);

    BUI.augment(mainPage, {
        //��ҳ��
        openPage: function (pageInfo) {
            var _self = this,
                moduleId = pageInfo.moduleId || _self._getCurrentModuleId(),
                id = pageInfo.id,
                title = pageInfo.title || '�µı�ǩҳ',
                href = pageInfo.href,
                isClose = pageInfo.isClose,
                closeable = pageInfo.closeable,
                reload = pageInfo.reload,
                search = pageInfo.search;

            var module = _self._getModule(moduleId);
            if (module) {
                var tab = module.tab,
                    menu = module.menu,
                    menuItem = menu.getItem(id),
                    curTabPage = tab.getActivedItem(),
                    sourceId = curTabPage ? curTabPage.get('id') : null,
                    moduleIndex = _self._getModuleIndex(moduleId);
                if (moduleId != _self._getCurrentModuleId()) {
                    _self._setModuleSelected(moduleIndex);
                }
                if (menuItem) {
                    _self._setPageSelected(moduleIndex, id, reload, search);
                } else {
                    tab.addTab({id: id, title: title, href: href, sourceId: sourceId, closeable: closeable}, reload);
                }

                if (isClose) {
                    curTabPage.close();
                }
            }
        },
        //�ر�ҳ��
        closePage: function (id, moduleId) {
            this.operatePage(moduleId, id, 'close');
        },
        //ˢ��
        reloadPage: function (id, moduleId) {
            this.operatePage(moduleId, id, 'reload');
        },
        //���ı���
        setPageTitle: function (title, id, moduleId) {
            this.operatePage(moduleId, id, 'setTitle', [title]);
        },
        //����ҳ��
        operatePage: function (moduleId, id, action, args) {

            moduleId = moduleId || this._getCurrentModuleId();
            args = args || [];
            var _self = this,
                module = _self._getModule(moduleId);
            if (module) {
                var tab = module.tab,
                    item = id ? tab.getItemById(id) : tab.getActivedItem();
                if (item && item[action]) {
                    item[action].apply(item, args);
                }
            }
        },
        //����ģ��
        _createModule: function (id) {
            var _self = this,
                item = _self._getModuleConfig(id),
                modules = _self.get('modules');
            if (!item) {
                return null;
            }
            var id = item.id,
                tabId = '#J_' + id + 'Tab',
                treeId = '#J_' + id + 'Tree';
            module = new tabNav(id, {render: tabId, height: getAutoHeight() - 5}, {
                render: treeId,
                items: item.menu,
                height: getAutoHeight() - 5
            }, item.collapsed, item.homePage);
            modules[id] = module;
            return module;
        },
        //�����б�
        _hideHideList: function () {
            this.get('hideList').hide();
        },
        _init: function () {
            var _self = this;
            _self._initDom();
            _self._initNavItems();
            _self._initEvent();
        },
        //��������Ӧ����
        _initNavItems: function () {

            var _self = this,
                navItems = _self.get('navItems'),
                hideItmes = _self.get('hideItmes');
            //��������ڵ�������ý�������Ӧ����
            if (navItems.length === 0) {
                return;
            }

            $('<div class="nav-item-mask"></div>').appendTo($(navItems));

            var count = navItems.length,
                clientWidth = BUI.viewportWidth(),//��ȡ���ڿ��
                itemWidth = WIDTH_ITERM,
                totalWidth = itemWidth * count,
                showCount = 0;

            //����������ܿ��С���û��������򣬲��ý�������Ӧ����
            if (totalWidth <= clientWidth) {
                return;
            }

            //��ʼ��dataIndex
            $.each(navItems, function (index, item) {
                $(item).attr(ATTTR_INDEX, index);
                $(item).removeClass(CLS_LAST);

            });

            showCount = parseInt(clientWidth / itemWidth);
            var lastShowItem = navItems[showCount - 1];
            _self._setLastItem(lastShowItem);

            hideItmes.push($(lastShowItem).clone()[0]);
            for (var i = showCount; i < count; i++) {
                var itemEl = $(navItems[i]),
                    cloneItme = null;

                cloneItme = itemEl.clone()[0];
                hideItmes.push(cloneItme);
                itemEl.addClass(CLS_HIDDEN);

            }

            _self._initHideList();

        },
        _initHideList: function () {
            var _self = this,
                hideList = _self.get('hideList'),
                hideItmes = _self.get('hideItmes');

            if (hideList) {
                return;
            }

            var template = '<ul class="dl-hide-list ks-hidden"></ul>',
                hideListEl = $(template).appendTo('body');
            hideList = hideListEl;
            $.each(hideItmes, function (index, item) {
                $(item).appendTo(hideList);
            });
            _self.set('hideList', hideList);
            _self._initHideListEvent();
        },
        _initHideListEvent: function () {
            var _self = this,
                hideList = _self.get('hideList');

            if (hideList == null) {
                return;
            }

            hideList.on('mouseleave', function () {
                _self._hideHideList();
            });

            hideList.on('click', function (event) {
                var item = findItem(event.target),
                    el = null,
                    dataIndex = 0;
                if (item) {
                    el = $(item);
                    dataIndex = el.attr(ATTTR_INDEX);
                    _self._setModuleSelected(dataIndex);
                    _self._hideHideList();
                }
            });
        },
        _initContents: function () {
            var _self = this,
                modulesConfig = _self.get('modulesConfig'),
                navContent = _self.get('navContent');

            //���ģ������
            navContent.children().remove();

            //��ʼ�������˵�һ��Tab
            $.each(modulesConfig, function (index, module) {
                var id = module.id,
                    temp = ['<li class="dl-tab-item ks-hidden"><div class="dl-second-nav"><div class="dl-second-tree" id="J_', id, 'Tree"></div><div class="', CLS_LEFT_SLIB, '-con"><div class="', CLS_LEFT_SLIB, '"></div></div></div><div class="dl-inner-tab" id="J_', id, 'Tab"></div></li>'].join('');
                new $(temp).appendTo(navContent);
            });
        },
        _initDom: function () {
            var _self = this;

            _self._initContents();
            _self._initLocation();

        },
        _initEvent: function () {
            var _self = this,
                navItems = _self.get('navItems');
            navItems.each(function (index, item) {
                var item = $(item);
                item.on('click', function () {
                    var sender = $(this);
                    if (sender.hasClass(CLS_SELECTE)) {
                        return;
                    }
                    //sender.addClass(CLS_SELECTE);
                    _self._setModuleSelected(index, sender);
                }).on('mouseenter', function () {

                    $(this).addClass(CLS_HOVER);
                }).on('mouseleave', function () {
                    $(this).removeClass(CLS_HOVER);
                });
            });
            _self._initNavListEvent();
        },
        _initNavListEvent: function () {
            var _self = this,
                hideList = _self.get('hideList'),
                navList = _self.get('navList');

            navList.on('mouseover', function (event) {
                var item = findItem(event.target),
                    el = $(item),
                    offset = null;

                if (el && el.hasClass(CLS_LAST) && hideList) {
                    offset = el.offset();
                    offset.top += 37;

                    offset.left += 2;
                    _self._showHideList(offset);
                }
            }).on('mouseout', function (event) {
                var toElement = event.toElement;
                if (toElement && hideList && !$.contains(hideList[0], toElement) && toElement !== hideList[0]) {
                    _self._hideHideList();
                }

            });
        },
        //��ʼ��ѡ�е�ģ���ҳ��
        _initLocation: function () {

            //�������л�ȡ�û���λ����ģ�飬����ˢ�º�ת��ָ��ģ��ʹ��
            var _self = this,
                defaultSetting = getNavPositionSetting();
            if (defaultSetting) {
                var pageId = defaultSetting.pageId,   //ҳ����
                    search = defaultSetting.search,
                    index = _self._getModuleIndex(defaultSetting.moduleId);   //���Ӳ���

                _self._setModuleSelected(index);
                _self._setPageSelected(index, pageId, true, search);
            } else {
                var currentModelIndex = _self.get('currentModelIndex'),
                    moduleId = _self._getModuleId(currentModelIndex);
                if (currentModelIndex == null) {
                    _self._setModuleSelected(0);
                } else {
                    setNavPosition(moduleId);
                }
            }
        },
        //��ȡģ��,���δ��ʼ�����ʼ��ģ��
        _getModule: function (id) {
            var _self = this,
                module = _self.get('modules')[id];
            if (!module) {
                module = _self._createModule(id);
            }
            return module;
        },
        _getModuleIndex: function (id) {
            var _self = this,
                result = 0;

            $.each(_self.get('modulesConfig'), function (index, conf) {
                if (conf.id === id) {
                    result = index;
                    return false;
                }
            });
            return result;
        },
        _getModuleConfig: function (id) {
            var _self = this,
                result = null;
            $.each(_self.get('modulesConfig'), function (index, conf) {
                if (conf.id === id) {
                    result = conf;
                    return false;
                }
            });
            return result;
        },
        //��ȡģ����
        _getModuleId: function (index) {

            var modulesConfig = this.get('modulesConfig');
            if (modulesConfig[index]) {
                return modulesConfig[index].id;
            } else {
                return index;
            }
        },
        _getCurrentPageId: function () {
            var _self = this,
                moduleId = _self._getCurrentModuleId(),
                module = _self._getModule(moduleId),
                pageId = '';
            if (module) {
                var item = module.menu.getSelected();
                if (item) {
                    pageId = item.get('id');
                }
            }
            return pageId;
        },
        _getCurrentModuleId: function () {
            return this._getModuleId(this.get('currentModelIndex'));
        },
        //ģ���Ƿ��Ѿ���ʼ��
        _isModuleInitial: function (id) {
            return !!this.get('modules')[id];
        },
        //�������һ��
        _setLastItem: function (item) {
            var _self = this,
                lastShowItem = _self.get('lastShowItem');

            if (lastShowItem === item) {
                return;
            }

            var appendNode = null,
                lastShowItemEl = $(lastShowItem);
            itemEl = $(item);
            if (lastShowItem) {
                appendNode = lastShowItemEl.find('.' + CLS_HIDE_CURRENT);
                lastShowItemEl.removeClass(CLS_LAST);
                lastShowItemEl.addClass(CLS_HIDDEN);
            }
            itemEl.addClass(CLS_LAST);
            itemEl.removeClass(CLS_HIDDEN);
            if (!appendNode) {
                appendNode = $('<span class="icon icon-white  icon-caret-down ' + CLS_HIDE_CURRENT + '">&nbsp;&nbsp;</span>');
            }
            appendNode.appendTo(itemEl.children('.nav-item-inner'));
            _self.set('lastShowItem', item)
        },
        //����ѡ�е�ģ��
        _setModuleSelected: function (index, sender) {
            var _self = this,
                navItems = _self.get('navItems'),
                navTabs = _self.get('navTabs'),
                currentModelIndex = _self.get('currentModelIndex');

            if (currentModelIndex !== index) {
                var moduleId = _self._getModuleId(index),
                    module = null,
                    lastShowItem = _self.get('lastShowItem'),
                    isCreated = true;//ģ���Ƿ��Ѿ�����

                if (!_self._isModuleInitial(moduleId)) {
                    isCreated = false;
                }

                module = _self._getModule(moduleId);


                sender = sender || $(_self.get('navItems')[index]);
                //���ģ������
                if (sender.hasClass(CLS_HIDDEN) && lastShowItem) {
                    _self._setLastItem(sender[0]);
                    _self._setSelectHideItem(index);
                }/**/
                navItems.removeClass(CLS_SELECTE);
                sender.addClass(CLS_SELECTE);
                navTabs.addClass(CLS_HIDDEN);
                $(navTabs[index]).removeClass(CLS_HIDDEN);

                currentModelIndex = index;
                _self.set('currentModelIndex', currentModelIndex);
                curPageId = _self._getCurrentPageId();
                setNavPosition(moduleId, curPageId);

                if (!curPageId && module.homePage) {
                    _self._setPageSelected(index, module.homePage);
                }
            }
        },
        _setPageSelected: function (moduleIndex, pageId, isReload, search) {
            var _self = this,
                moduleId = _self._getModuleId(moduleIndex) || moduleIndex,
                module = _self._getModule(moduleId);
            if (module && pageId) {
                module.menu.setSelectedByField(pageId);
                var item = module.menu.getSelected(),
                    tab = module.tab,
                    href = '',
                    suffixIndex = -1;
                if (item && item.get('id') === pageId) {
                    href = item.get('href');
                    href = search ? (addSearch(href, search)) : href;
                    module.tab.addTab({
                        id: item.get('id'),
                        title: item.get('text'),
                        closeable: item.get('closeable'),
                        href: href
                    }, !!isReload);

                } else if (pageId) {

                    var subDir = pageId.replace('-', '/');
                    if (subDir.indexOf('/') === -1) {
                        subDir = moduleId + '/' + subDir;
                    }
                    if ((suffixIndex = pageId.indexOf('.')) === -1) {
                        subDir += _self.get('urlSuffix');
                    }
                    href = search ? (subDir + '?' + search) : subDir;
                    tab.addTab({id: pageId, title: '', href: href}, !!isReload);
                }
            }
        },
        _showHideList: function (offset) {
            var _self = this,
                hideList = _self.get('hideList');

            hideList.css('left', offset.left);
            hideList.css('top', offset.top);
            hideList.show();
        },
        _setSelectHideItem: function (index) {
            var _self = this,
                hideList = _self.get('hideList'),
                hideItmes = _self.get('hideItmes'),
                currentItem = null,
                selectItem = null,
                selectEl = null,
                appendNode = null;
            BUI.each(hideItmes, function (item) {
                var itemEl = $(item);
                if (itemEl.attr(ATTTR_INDEX) == index) {
                    selectItem = item;
                }

                if (itemEl.hasClass(CLS_LAST)) {
                    currentItem = item;
                }
            });

            if (currentItem !== selectItem) {
                if (currentItem) {
                    appendNode = $(currentItem).find('.dl-hide-current');
                    $(currentItem).removeClass(CLS_LAST);
                }
                $(selectItem).addClass(CLS_LAST);
                if (!appendNode) {
                    appendNode = new Node('<span class="dl-hide-current">&nbsp;&nbsp;</span>');
                }
                selectEl = $(selectItem);
                appendNode.appendTo(selectEl.children('.nav-item-inner'));
                selectEl.prependTo(hideList);
            }

        }

    });
    PageUtil.MainPage = mainPage;
});