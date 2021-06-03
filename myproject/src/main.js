import Vue from 'vue'
import Vuex from 'vuex'
import App from './App.vue'
import router from './router'
import store from './store'

import './plugin/element-ui'
import ElementUI from 'element-ui';
import 'font-awesome/css/font-awesome.min.css'

//引入让vue能使用axios不能携带cookie
axios.defaults.withCredentials = true;    

Vue.use(Vuex)  

// aiox需要的包
import axios from 'axios'
//aciox基础url
axios.defaults.baseURL = 'http://47.108.190.196/QAPlatform';
//将axios写成vue的原型属性
Vue.prototype.$axios = axios;
//为post请求设置默认请求头
axios.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8';

Vue.config.productionTip = false;

//JSON格式时间转换为标准格式时间
import moment from 'moment/moment';
Vue.prototype.$moment = moment;


new Vue({
  el: '#app',
  router: router,
  store,
  render: h => h(App), 
})
  