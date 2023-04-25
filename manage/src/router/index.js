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
        redirect: '/dashboard',
        name:'dashboard',
        children: [
            {
                path: '/dashboard',
                component: () => import("@/views/dashboard"),
                meta: {
                    title: '仪表盘'
                }
            },
            {
                path: '/users',
                component: () => import("@/views/users"),
                meta: {
                    title: '用户管理'
                }
            },
            {
                path: '/books',
                component: () => import("@/views/books"),
                meta: {
                    title: '书籍管理'
                }
            },
            {
                path: '/orders',
                component: () => import("@/views/orders"),
                meta: {
                    title: '订单管理'
                }
            },
            {
                path: '/comments',
                component: () => import("@/views/comments"),
                meta: {
                    title: '评论管理'
                }
            },
            {
                path: '/me',
                component: () => import("@/views/me"),
                meta: {
                    title: '个人中心'
                }
            },
            {
                path: '/types',
                component: () => import("@/views/types"),
                meta: {
                    title: '书籍类型管理'
                }
            },
            {
                path: '/banners',
                component: () => import("@/views/banners"),
                meta: {
                    title: '轮播图管理'
                }
            }
        ]
    },
    {
        path: '/login',
        component: () => import("@/views/login"),
        name:'login',
        meta: {
            title: '登录'
        }
    }
]

export default new VueRouter({
    mode: "history",
    routes
})