import request from "@/utils/request";

// 获取轮播图列表
export function $bannerList(count) {
    return request({
        url: '/index/banner',
        method: 'get',
        params: {
            count: count
        }
    })
}

// 获取轮播图列表
export function $hotBookList(count) {
    return request({
        url: '/index/hotBooks',
        method: 'get',
        params: {
            count: count
        }
    })
}

// 获取轮播图列表
export function $newBookList(count) {
    return request({
        url: '/index/newBooks',
        method: 'get',
        params: {
            count: count
        }
    })
}
