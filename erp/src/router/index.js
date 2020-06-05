import Vue from 'vue'
import Router from 'vue-router'

import Login from '@/view/Login'
import Home from '@/view/Home'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login,
      // hidden: true
    },
    {
      path: '/home',
      name: '控制台',
      component: Home,
      // hidden: true
    }, {
      path: '*',
      redirect: '/',
    }
  ]
})
