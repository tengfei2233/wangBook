import request from "@/utils/request";

// 查询验证码
export function $getCaptcha() {
    return request({
        url: '/login/getCaptcha',
        method: 'get'
    })
}

// 登录
export function $login(data) {
    return request({
        url: '/login/login',
        method: 'post',
        data
    })
}
// 登出
export function $logout() {
    return request({
        url: '/logout',
        method: 'get',
    })
}