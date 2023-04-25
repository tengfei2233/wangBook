import request from "@/utils/request";

// 书籍列表
export function $bannerList(params) {
    return request({
        url: '/banner/banners',
        method: 'get',
        params
    })
}

// 新增列表
export function $addBanner(data) {
    return request({
        url: '/banner/add',
        method: 'post',
        data
    })
}


// 修改
export function $updateBanner(data) {
    return request({
        url: '/banner/update',
        method: 'post',
        data
    })
}


// 书籍列表
export function $delBanner(bnId) {
    return request({
        url: '/banner/delete',
        method: 'post',
        params: {
            bnId: bnId
        }
    })
}