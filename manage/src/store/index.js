import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({

    state: {
        // 侧边栏是否关闭
        collapse: false
    },
    actions: {
        setCollapse({ state }) {
            state.collapse = !state.collapse;
        }
    }

})