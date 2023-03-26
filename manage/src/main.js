import Vue from 'vue'
import App from './App.vue'
import less from 'less';

import router from './router'

import './assets/styles/variables.less'

// element-ui
import 'element-ui/lib/theme-chalk/index.css';
import Element from 'element-ui'

Vue.config.productionTip = false
Vue.use(less);
Vue.use(Element);
new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
