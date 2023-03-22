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
        path: '/',
        component: () => import("@/views/layout"),
        redirect: '/home',
        children: [
            {
                path: '/home',
                component: () => import("@/views/home"),
                meta: {
                    title: '首页'
                }
            },
            {
                path: '/books',
                component: () => import("@/views/books"),
                meta: {
                    title: '图书列表'
                }
            },
            {
                path: '/cars',
                component: () => import("@/views/cars"),
                meta: {
                    title: '购物车'
                }
            }
        ]
    },
    {
        path: '/login',
        component: () => import("@/views/login"),
        meta: {
            title: '登录'
        }
    }
]


export default new VueRouter({
    mode: "history",
    routes
})