import request from "@/utils/request";

// 书籍类型列表
export function $typeList(params) {
    return request({
        url: '/book/types',
        method: 'get',
        params
    })
}

export function $typeListNoPage() {
    return request({
        url: '/book/types/noPage',
        method: 'get',
    })
}

// 删除书籍类型
export function $delType(typeId) {
    return request({
        url: '/book/delType',
        method: 'post',
        params: {
            typeId: typeId
        }
    })
}

// 添加书籍类型
export function $addType(typeName) {
    return request({
        url: '/book/addType',
        method: 'post',
        data: { typeName: typeName }
    })
}