/**
 环境配置文件
 三个环境：
 开发环境
 测试环境
 正式环境
 */
//当前环境
const env =  'development'
const BASE_URL = 'http://127.0.0.1:8080/'

const envConfig = {
    development: {
        baseApi: BASE_URL,
        // mockApi: 'http://127.0.0.1:5173'
        mockApi: 'https://www.fastmock.site/mock/04bb28c376ae9402355ada7d1e4c6adb/api'
    },
    test: {
        baseApi: BASE_URL,
        mockApi: 'https://www.fastmock.site/mock/b88524d11a3c59d6821add68f74fbdfd/api/home/getTableData'
    },
    prod: {
        baseApi: BASE_URL,
        mockApi: 'https://www.fastmock.site/mock/b88524d11a3c59d6821add68f74fbdfd/api/home/getTableData'
    }
}

export default {
    env,
    // mock开关
    mock: false,
    ...envConfig[env]
}
