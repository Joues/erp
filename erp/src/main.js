// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'
import {initMenu} from "./utils/menus"
import echarts from 'echarts'

import axios from 'axios'
import VueAxios from 'vue-axios'
// import Api from './utils/api'
import {getRequest} from './utils/api'
import {postRequest} from './utils/api'
import {deleteRequest} from './utils/api'
import {putRequest} from './utils/api'

// Vue.prototype.$api = Api;
Vue.prototype.getRequest = getRequest;
Vue.prototype.postRequest = postRequest;
Vue.prototype.deleteRequest = deleteRequest;
Vue.prototype.putRequest = putRequest;

//引入echarts组件
Vue.prototype.$echarts = echarts;

Vue.use(VueAxios, axios)
// 引入element组件
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
// 引入iconfont矢量图标库
import './assets/iconfont/iconfont.css'

// 引用公共element.css样式表
import './assets/css/element.css'


Vue.config.productionTip = false;

// 引入element组件
Vue.use(ElementUI);
/* eslint-disable no-new */

router.beforeEach((to, from, next) => {
  if (to.path == '/') {
    next();
  }else {
    if (window.sessionStorage.getItem("user")) {
      initMenu(router, store);
      next();
    }else{
      next('/?redirect='+to.path);
    }
  }
})

new Vue({
  el: '#app',
  router,
  store,
  components: {App},
  template: '<App/>',
  render: h => h(App)
})
