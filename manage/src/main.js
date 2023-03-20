import Vue from 'vue'
import App from './App.vue'

// element-ui
import 'element-ui/lib/theme-chalk/index.css';
import Element from 'element-ui'

Vue.config.productionTip = false
Vue.use(Element);
new Vue({
  render: h => h(App),
}).$mount('#app')
