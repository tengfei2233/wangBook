import request from "@/utils/request";

// 获取书籍类型列表
export function $typeList() {
    return request({
        url: '/book/types',
        method: 'get'
    })
}


// 获取书籍列表
export function $bookList(params) {
    return request({
        url: '/book/books',
        method: 'get',
        params
    })
}