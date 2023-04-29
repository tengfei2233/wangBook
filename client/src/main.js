import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import './permission'

// element-ui
import 'element-ui/lib/theme-chalk/index.css';
import Element from 'element-ui'

import './assets/styles/common.css'

import { parseTime, resetForm, addDateRange, selectDictLabel, selectDictLabels, handleTree } from "@/utils/common";

// 分页组件
import Pagination from "@/components/Pagination";

Vue.config.productionTip = false

// 全局挂载组件
Vue.component('Pagination', Pagination)
// 全局挂载方法
Vue.prototype.parseTime = parseTime
Vue.prototype.resetForm = resetForm
Vue.prototype.addDateRange = addDateRange
Vue.prototype.selectDictLabel = selectDictLabel
Vue.prototype.selectDictLabels = selectDictLabels
Vue.prototype.handleTree = handleTree


Vue.use(Element);
new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
