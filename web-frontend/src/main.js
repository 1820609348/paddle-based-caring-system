import Vue from 'vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue';
import router from './router';
import BaiduMap from 'vue-baidu-map';
//mock模拟拦截
import './api/mockServer/mockStatustics'
import './api/mockServer/mockTaskProgress'
import './api/mockServer/mockLogin'
import './api/mockServer/mockReceive'
import './api/mockServer/mockElderInfo'
import './api/mockServer/mockFamily'

Vue.config.productionTip = false
Vue.use(ElementUI);
Vue.use(BaiduMap, {ak: 'qtCQETPqGoOL4qmGENu5TPB380TjhL4c'})
new Vue({
    el: '#app',
    render: h => h(App),
    router
});