import request from "@/utils/request";

// 登录
export function $login(params) {
    return request({
        url: '/login/login',
        method: 'post',
        params
    })
}
// 登出
export function $logout() {
    return request({
        url: '/logout',
        method: 'get',
    })
}
