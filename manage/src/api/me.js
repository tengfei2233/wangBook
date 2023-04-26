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

// 获取用户信息
export function $userInfo() {
    return request({
        url: '/login/info',
        method: 'get',
    })
}

// 修改密码
export function $updatePwd(data) {
    return request({
        url: '/login/updatePwd',
        method: 'post',
        data
    })
}


// 修改手机号
export function $updatePhone(data) {
    return request({
        url: '/login/updatePhone',
        method: 'post',
        data
    })
}

// 修改头像
export function $updateAvatar(avatar) {
    return request({
        url: '/login/updateAvatar',
        method: 'post',
        params: {
            avatar: avatar
        }
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