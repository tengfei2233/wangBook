import request from "@/utils/request";

// 书籍列表
export function $bookList(params) {
    return request({
        url: '/book/books',
        method: 'get',
        params
    })
}

// 添加书籍
export function $addBook(data) {
    return request({
        url: '/book/add',
        method: 'post',
        data
    })
}

// 上架/下架列表
export function $shelveBook(bookId) {
    return request({
        url: '/book/shelve',
        method: 'post',
        params: {
            bookId: bookId
        }
    })
}

// 修改列表
export function $updateBook(data) {
    return request({
        url: '/book/update',
        method: 'post',
        data
    })
}

// 订单列表
export function $orderList(params) {
    return request({
        url: '/book/orders',
        method: 'get',
        params
    })
}

// 订单详情
export function $orderInfo(orderId) {
    return request({
        url: '/book/order/' + orderId,
        method: 'get',
    })
}