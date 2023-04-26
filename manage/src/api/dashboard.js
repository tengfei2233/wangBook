import request from "@/utils/request";

export function $chartData() {
    return request({
        url: "/dashboard/chartData",
        method: 'get'
    })
}