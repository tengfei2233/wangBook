import request from "@/utils/request";

// 查询验证码
export function $getCaptcha() {
    return request({ url: '/login/getCaptcha' })
}

