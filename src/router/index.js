import Vue from 'vue'
import Router from 'vue-router'
import Error from '../components/Error.vue'
import Login  from '../components/Login.vue'
import Register from '../components/Register.vue'
import Main from '../components/Main.vue'
import Manager from '../components/Manager.vue'
import Admin_Main from '../components/Admin_Main.vue'
import Admin_User from '../components/Admin_User.vue'
import Admin_Bill from '../components/Admin_Bill.vue'
Vue.use(Router)
// 解决ElementUI导航栏中的vue-router在3.0版本以上重复点菜单报错问题
const originalPush = Router.prototype.push
Router.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}
export default new Router({
  routes: [
    {
      path: '/error',
      name: 'Error',
      component: Error
    },
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
    },
    {
      path: '/main',
      name: 'Main',
      component: Main
    },
    {
      path: '/manager',
      name: 'Manager',
      component: Manager,
      children: [
        {
             path: '/manager/am',
             name: 'Admin_Main',
             component: Admin_Main,
        },
        {
             path: '/manager/au',
             name: 'Admin_User',
             component: Admin_User,
        },
        {
          path: '/manager/ab',
          name: 'Admin_Bill',
          component: Admin_Bill,
        }
      ]
    }
  ]

})
