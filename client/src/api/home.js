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

// 获取书籍列表{最新,热门}
export function $bookList(count) {
    return request({
        url: '/index/book',
        method: 'get',
        params: {
            count: count
        }
    })
}
