import VueRouter from 'vue-router'
import Vue from 'vue'

Vue.use(VueRouter);
// 防止页面刷新报错
const VueRouterPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(to) {
    return VueRouterPush.call(this, to).catch(err => err)
}

const routes = [
    // TODO 添加你想要的路由
    {
        path: '/login',
        comments: () => import("@/views/login")
    }
]


export default new VueRouter({
    mode:"history",
    routes
})