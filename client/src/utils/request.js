import axios from 'axios'
import { MessageBox, Message } from 'element-ui'
import { getToken,removeToken } from '@/utils/auth'
import NProgress from 'nprogress'
// 创建axios实例
const baseURL = process.env.VUE_APP_BASE_API
const service = axios.create({
    baseURL, // url = base url + request url
    // 超时时间
    timeout: 5000
})

// 请求拦截器
service.interceptors.request.use(
    config => {
        // 进度条开始
        NProgress.start();
        // 获取cookie
        const token = getToken();
        if (token) {
            // 设置请求头，携带token
            config.headers['token'] =  token
        }
        return config
    },
    error => {
        NProgress.start();
        // 抛出异常
        return Promise.reject(error)
    }
)

// 相应拦截器
service.interceptors.response.use(

    response => {
        // 获取相应信息体
        const res = response.data
        const code = res.code || 200;
        // 相应状态码
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
                    // TODO: 删除token并刷新页面
                    removeToken();
                    location.reload();
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
