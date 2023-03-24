

const port = process.env.APP_PORT || 80 // 端口

module.exports = {
  transpileDependencies: true,
  // 对应应用的子路径，例如，www.wang.com/client，部署在client子路径中，则publicPath就是client
  publicPath: process.env.VUE_APP_CONTEXT_PATH,
  // 打包输出路径
  outputDir: 'dist',
  // 打包静态文件输出路径
  assetsDir: 'static',
  // 是否开启eslint保存检测，有效值：ture | false | 'error'
  lintOnSave: false,
  // 如果你不需要生产环境的 source map，可以将其设置为 false 以加速生产环境构建。
  productionSourceMap: false,
  devServer: {
    host: '0.0.0.0',
    port: port,
    open: false,
    proxy: {
      // detail: https://cli.vuejs.org/config/#devserver-proxy
      [process.env.VUE_APP_BASE_API]: {
        target: `http://127.0.0.1:8888`,
        changeOrigin: true,
        pathRewrite: {
          // TODO: 路径重写
          ['^' + process.env.VUE_APP_BASE_API]: 'user'
        },
        // 用不到ws的时候就关闭
        ws: false,
        changeOrigin: true,
      }
    },
    historyApiFallback: true,
    allowedHosts: "all",
    // 以下配置在webpack5中被禁用
    // disableHostCheck: true
  },
}
