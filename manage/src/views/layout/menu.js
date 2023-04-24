// sidebar侧边栏按钮
// TODO: 需要和router对应

export default [
    {
        icon: "el-icon-monitor",
        index: "/dashboard",
        title: "仪表盘"
    },
    {
        icon: "el-icon-user",
        index: "user-manage",
        title: "用户管理",
        subs: [
            {
                icon: "el-icon-s-custom",
                index: "/users",
                title: "用户列表"
            },
            {
                icon: "el-icon-s-check",
                index: "/managers",
                title: "管理员列表"
            }
        ]
    },
    {
        icon: "el-icon-reading",
        index: "book-manage",
        title: "书籍管理",
        subs: [
            {
                icon: 'el-icon-collection',
                index: "/books",
                title: "书籍列表"
            },
            {
                icon: 'el-icon-shopping-bag-2',
                index: "/orders",
                title: "订单列表"
            },
            {
                icon: 'el-icon-c-scale-to-original',
                index: "/types",
                title: "书籍类型列表"
            }
        ]
    },
    {
        icon: "el-icon-chat-dot-round",
        index: "/comments",
        title: "评论列表"
    },
]