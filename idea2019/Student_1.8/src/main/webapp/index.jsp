<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>学生后台管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href="jsUi/assets/css/dpl-min.css" rel="stylesheet" type="text/css"/>
    <link href="jsUi/assets/css/bui-min.css" rel="stylesheet" type="text/css"/>
    <link href="jsUi/assets/css/main-min.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="header">
    <div class="dl-log">
        <marquee onmouseover=this.stop() onmouseout=this.start()
                 scrollamount=2 scrolldelay=90 direction=left width="980" height=40>
            欢迎 当前用户：[${sessionScope.user_session.name}] 使用 SSM架构式学生管理系统！
        </marquee>
    </div>
</div>
<div class="content">

    <div class="dl-main-nav">
        <div class="dl-inform">
            <div class="dl-inform-title"><s class="dl-inform-icon dl-up"></s></div>
        </div>

        <ul id="J_Nav" class="nav-list ks-clear">
            &nbsp;&nbsp; <a href="#"
                            style="color:white;text-decoration: none;">当前用户：[${sessionScope.user_session.name}]</a><a
                href="${ctx}/logout.do" id="exit"
                style="margin-right:0px;color:white;height:30px;display: inline-block;line-height:23px;"
                class="nav-item-inner nav-home">注销退出</a>

            <li class="nav-item dl-selected">
                <div class="nav-item-inner nav-home">专业管理</div>
            </li>
            <li class="nav-item dl-selected">
                <div class="nav-item-inner nav-order">课程管理</div>
            </li>
            <li class="nav-item dl-selected">
                <div class="nav-item-inner nav-order">学生管理</div>
            </li>
            <li class="nav-item dl-selected">
                <div class="nav-item-inner nav-order">成绩管理</div>
            </li>
            <li class="nav-item dl-selected">
                <div class="nav-item-inner nav-order">用户管理</div>
            </li>
        </ul>
    </div>
    <ul id="J_NavContent" class="dl-tab-conten">

    </ul>


</div>
<script type="text/javascript" src="jsUi/assets/js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="jsUi/assets/js/bui-min.js"></script>
<script type="text/javascript" src="jsUi/assets/js/common/main-min.js"></script>
<script type="text/javascript" src="jsUi/assets/js/config-min.js"></script>
<script>
    BUI.use('common/main', function () {
        var config = [
            {
                id: '1',
                menu: [{
                    text: '专业管理',
                    items: [{id: '12', text: '添加专业', href: 'view/insertDepartment.do?flag=1'}, {
                        id: '3',
                        text: '浏览专业信息',
                        href: 'view/selectDepartment.do'
                    }]
                }]
            },
            {
                id: '5',
                homePage: '8',
                menu: [{
                    text: '课程管理',
                    items: [{id: '8', text: '添加课程信息', href: 'view/insertCourse.do?flag=1'}, {
                        id: '4',
                        text: '浏览课程信息',
                        href: 'view/selectCourse.do'
                    }]
                }]
            },
            {
                id: '6',
                homePage: '9',
                menu: [{
                    text: '学生管理',
                    items: [{id: '9', text: '添加学生', href: 'view/insertStudent.do?flag=1'}, {
                        id: '12',
                        text: '浏览学生信息',
                        href: 'view/selectStudent.do'
                    }]
                }]
            },
            {
                id: '7',
                homePage: '11',
                menu: [{
                    text: '成绩管理',
                    items: [{id: '11', text: '查询成绩', href: 'view/selectGrade.do'}, {
                        id: '12',
                        text: '添加成绩信息',
                        href: 'view/insertGrade.do?flag=1'
                    }]
                }]
            },

            {
                id: '8',
                homePage: '10',
                menu: [{
                    text: '用户个人管理',
                    items: [{id: '10', text: '用户查看', href: 'view/selectUser.do'}, {
                        id: '11',
                        text: '添加用户信息',
                        href: 'view/insertUser.do?flag=1'
                    }]
                }]
            },


        ];
        new PageUtil.MainPage({
            modulesConfig: config
        });
    });

</script>
</body>

</html>

