(function ($) {

    var baseUrl = '',         //��վ�ĸ�Ŀ¼��ַ������������ʱʹ��
        jsBase = '/assets/js';

    function isRelative(url) {
        if (!/:/.test(url)) {
            return true;
        }
    }

    function formatUrl(url) {
        if (isRelative(url)) {
            var path = getCurrentPath();
            return path + '/' + url;
        }
        return url;
    }

    function getCurrentPath() {
        var url = location.href,
            lastIndex;
        url = url.replace(/\?.*$/, '').replace(/\#.*$/, '');
        lastIndex = url.lastIndexOf('/');
        return url.substring(0, lastIndex);
    }

    function getBaseUrl() {    //����config.js��·��ȡbaseUrl
        var scripts = $('script'),
            rst = '';
        $.each(scripts, function (index, script) {
            var src = script.src,
                lastIndex = src.indexOf(jsBase + '/config');
            if (lastIndex !== -1) {
                rst = src.substring(0, lastIndex);
                return false;
            }
        });
        return formatUrl(rst);
    }

    if (!baseUrl) {//���δָ����Ŀ·��������ƥ�䡣
        baseUrl = getBaseUrl();
    }

    BUI.config({
        alias: {
            'common': baseUrl + jsBase + '/common',
            'module': baseUrl + jsBase + '/module'
        },/**/
        map: [ //���Ի�����ʹ��'*.js'�������������ǰ��ʹ��*-min.jsȥ�������map
            //[/common\/(.*)-min.js/,'common/$1.js'],
            [/module\/(.*)-min.js/, 'module/$1.js']
        ]

    });

})(jQuery);