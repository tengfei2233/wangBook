import router from './router'
import store from './store'
import { Message } from 'element-ui'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
import { getToken } from '@/utils/auth' // get token from cookie

NProgress.configure({ showSpinner: false }) // NProgress Configuration

const whiteList = ['/login'] // no redirect whitelist

router.beforeEach(async (to, from, next) => {
    // start progress bar
    NProgress.start()
    // determine whether the user has logged in
    const hasToken = getToken()
    // 含有标题，设置标题
    if (to.meta.title) {
        document.title = '二手书售卖平台-' + to.meta.title
    }
    if (hasToken) {
        if (to.path === '/login') {
            // if is logged in, redirect to the home page
            next({ path: '/index' })
            NProgress.done()
        } else {
            const hasGetUserInfo = store.getters.name
            if (hasGetUserInfo) {
                next()
            } else {
                try {
                    // get user info
                    await store.dispatch('getInfo')
                    next()
                } catch (error) {
                    // remove token and go to login page to re-login
                    await store.dispatch('resetToken')
                    Message.error(error || 'Has Error')
                    next('/')
                    NProgress.done()
                }
            }
        }
    } else {
        /* has no token*/

        if (whiteList.indexOf(to.path) !== -1) {
            // in the free login whitelist, go directly
            next()
        } else {
            // other pages that do not have permission to access are redirected to the login page.
            next('/')
            NProgress.done()
        }
    }
})

router.afterEach((to, from) => {
    // finish progress bar
    if (from.path != '/login' && from.path !== '/' && to.path == '/login') {
        window.location.reload()
    }
    NProgress.done()
})
