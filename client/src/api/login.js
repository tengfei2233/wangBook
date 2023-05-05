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

// 手机号登录
export function $phoneLogin(data) {
    return request({
        url: '/login/phoneLogin',
        method: 'post',
        data
    })
}

// 注册
export function $register(data) {
    return request({
        url: '/login/register',
        method: 'post',
        data
    })
}

// 发送验证码
export function $getCode(phone) {
    return request({
        url: '/login/getCode',
        method: 'post',
        params: {
            phone: phone
        }
    })
}



// 登出
export function $logout() {
    return request({
        url: '/logout',
        method: 'get',
    })
}

