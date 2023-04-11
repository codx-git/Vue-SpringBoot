import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './CSS/global.css'
import request from './utils/request';

Vue.config.productionTip = false

Vue.use(ElementUI,{ size: "mini" }) //配置ElementUI

Vue.prototype.request = request //配置http请求

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')