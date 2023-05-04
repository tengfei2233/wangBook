import request from "@/utils/request";

// 评论列表
export function $commentList(params) {
    return request({
        url: '/comment/comments',
        method: 'get',
        params
    })
}

// 删除评论
export function $delComment(cmId) {
    return request({
        url: '/comment/del',
        method: 'post',
        params: {
            cmId: cmId
        }
    })
}