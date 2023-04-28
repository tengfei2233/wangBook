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



// 获取用户信息
export function $userInfo() {
    return request({
        url: '/user/info',
        method: 'get',
    })
}

// 修改密码
export function $updatePwd(data) {
    return request({
        url: '/user/updatePwd',
        method: 'post',
        data
    })
}


// 修改手机号
export function $updatePhone(data) {
    return request({
        url: '/user/updatePhone',
        method: 'post',
        data
    })
}

// 修改头像
export function $updateAvatar(avatar) {
    return request({
        url: '/user/updateAvatar',
        method: 'post',
        params: {
            avatar: avatar
        }
    })
}

// 发送验证码
export function $getCode(phone) {
    return request({
        url: '/user/getCode',
        method: 'post',
        params: {
            phone: phone
        }
    })
}