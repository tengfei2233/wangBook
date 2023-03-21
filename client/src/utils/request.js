import axios from 'axios'
import { MessageBox, Message } from 'element-ui'
import store from '@/store'
import { getToken } from '@/utils/auth'
import NProgress from 'nprogress'
// 创建axios实例
const baseURL = process.env.VUE_APP_REMOTE_HOST
const service = axios.create({
    baseURL, // url = base url + request url
    // 超时时间
    timeout: 5000
})

// request interceptor
service.interceptors.request.use(
    config => {
        // do something before request is sent
        NProgress.start();
        // 获取cookie
        const token = getToken();
        if (token) {
            // let each request carry token
            // ['X-Token'] is a custom headers key
            // please modify it according to the actual situation
            config.headers['Authorization'] = 'Bearer ' + token
        }
        return config
    },
    error => {
        NProgress.start();
        // do something with request error
        return Promise.reject(error)
    }
)

// response interceptor
service.interceptors.response.use(
    /**
     * If you want to get http information such as headers or status
     * Please return  response => response
     */

    /**
     * Determine the request status by custom code
     * Here is just an example
     * You can also judge the status by HTTP Status Code
     */
    response => {
        const res = response.data
        const code = res.code || 200;

        // if the custom code is not 20000, it is judged as an error.
        if (code !== 200) {
            Message({
                message: res.msg || '请求出错',
                type: 'error',
                duration: 3 * 1000
            })

            if (code === 401 || code === 403) {
                // to re-login
                MessageBox.confirm('你的登录已过期，请重新登录', '提示', {
                    confirmButtonText: '重新登录',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    // TODO: 
                    store.dispatch('resetToken').then(() => {
                        location.reload()
                    })
                })
            }
            NProgress.done();
            return Promise.reject(new Error(res.msg || 'Error'))
        } else {
            NProgress.done();
            return res
        }
    },
    error => {
        let { message } = error;
        if (message == "Network Error") {
            message = "后端接口连接异常";
        }
        else if (message.includes("timeout")) {
            message = "系统接口请求超时";
        }
        else if (message.includes("Request failed with status code")) {
            message = "系统接口" + message.substr(message.length - 3) + "异常";
        }
        Message({
            message: message,
            type: 'error',
            duration: 3 * 1000
        })
        NProgress.done();
        return Promise.reject(error)
    }
)

export default service
