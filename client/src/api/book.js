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

// 获取书籍详情
export function $bookInfo(bookId) {
    return request({
        url: '/book/bookInfo/' + bookId,
        method: 'get'
    })
}

// 加入购物车
export function $addCar(data) {
    return request({
        url: '/book/addCar',
        method: 'post',
        data
    })
}

// 购买
export function $buyBook(params) {
    return request({
        url: '/book/buyBook',
        method: 'get',
        params
    })
}

// 购物车列表
export function $carList(params) {
    return request({
        url: '/book/cars',
        method: 'get',
        params
    })
}

// 删除购物车
export function $delCar(id) {
    return request({
        url: '/book/delCar',
        method: 'post',
        params: {
            id: id
        }
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

// 删除订单
export function $delOrder(orderId) {
    return request({
        url: '/book/delOrder',
        method: 'post',
        params: {
            orderId: orderId
        }
    })
}

// 评论列表
export function $commonList(params) {
    return request({
        url: '/book/comments',
        method: 'get',
        params
    })
}

// 发送评论
export function $toComment(data) {
    return request({
        url: '/book/setComment',
        method: 'post',
        data
    })
}


