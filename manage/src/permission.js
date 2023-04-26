import router from './router'
// 进度条
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
// 获取用户token
import { getToken } from '@/utils/auth'

NProgress.configure({ showSpinner: false })

router.beforeEach(async (to, from, next) => {
    // 进度条开始
    NProgress.start()
    const token = getToken()
    // 含有标题，设置标题
    if (to.meta.title) {
        document.title = '二手书售卖管理平台-' + to.meta.title
    }
    if (token) {
        if (to.path === '/login') {
            // 如果已经登录，则不能再访问登录页面
            next({ path: from.path })
        } else {
            next()
        }
    } else {
        // 没有token
        if (to.path == "/login") {
            // 如果是访问白名单，放行
            next()
        } else {
            // 否者跳转到登录页面
            next('/login')
        }
    }
})

router.afterEach((to, from) => {
    // 刷新url
    if (from.path != '/login' && from.path !== '/' && to.path == '/login') {
        window.location.reload()
    }
    NProgress.done()
})
