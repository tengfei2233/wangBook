import Vue from 'vue'
import App from './App.vue'
import less from 'less';

import router from './router'
import store from './store'

// element-ui
import 'element-ui/lib/theme-chalk/index.css';
import Element from 'element-ui'

import './assets/styles/variables.less'
import './assets/styles/common.less'

// 常用工具
import { parseTime, resetForm, addDateRange, selectDictLabel, selectDictLabels, handleTree } from "@/utils/common";

// 分页组件
import Pagination from "@/components/Pagination";

Vue.config.productionTip = false
Vue.use(less);
Vue.use(Element);
// 全局挂载组件
Vue.component('Pagination', Pagination)
// 全局挂载方法
Vue.prototype.parseTime = parseTime
Vue.prototype.resetForm = resetForm
Vue.prototype.addDateRange = addDateRange
Vue.prototype.selectDictLabel = selectDictLabel
Vue.prototype.selectDictLabels = selectDictLabels
Vue.prototype.handleTree = handleTree

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
