// module.exports = {
//   devServer: {
//     open: true,
//     host: 'localhost',
//     port: 8080,
//     https: false,
//     //以上的ip和端口是我们本机的;下面为需要跨域的
//     proxy: {//配置跨域
//       '/': {
//         target: 'http://localhost:8081',//这里后台的地址模拟的;应该填写你们真实的后台接口
//         ws: true,
//         changOrigin: true,//允许跨域
//         pathRewrite: {
//           '^/': ''//请求的时候使用这个api就可以
//         }
//       }
//
//     }
//   }
// }
let proxyObj = {};

proxyObj['/'] = {
    ws: false,
    target: 'http://localhost:8081',
    changeOrigin: true,
    pathRewrite: {
        '^/': ''
    }
}

module.exports = {
    devServer: {
        host: 'localhost',
        port: 8080,
        proxy: proxyObj
    }
}
