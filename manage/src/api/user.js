import request from "@/utils/request";

// 用户列表
export function $userList(params) {
    return request({
        url: '/user/users',
        method: 'get',
        params
    })
}

// 启用/封禁用户
export function $lock(userId) {
    return request({
        url: '/user/lock',
        method: 'post',
        params: {
            userId: userId
        }
    })
}

// 添加用户
export function $addUser(data) {
    return request({
        url: '/user/add',
        method: 'post',
        data
    })
}