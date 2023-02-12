import axios from 'axios'
import config from './config'
import {ElMessage} from "element-plus";


const NETWORK_ERROR = "网络请求异常 请稍后重试"
axios.defaults.timeout = 5000; // 超时时间设置
axios.defaults.withCredentials = true; // true允许跨域
// Content-Type 响应头
axios.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded;charset=UTF-8";

// 创建一个axios实例对象
const service = axios.create({
    baseURL: config.baseApi
})
// 在请求之前做一些事情
service.interceptors.request.use((req) => {
    // 自定义header
    // jwt-token认证式可以用到
    return req
})

//在请求之后做一点事
service.interceptors.response.use((res) => {
    // status是为了照顾js
    if (res.status === 200) {
        if (res.data.code === 400){
            ElMessage.error(res.data.message || NETWORK_ERROR)
        }
        else{
            return res.data
        }
    }

    else {
        //网络请求错误
        ElMessage.error(res.data.message || NETWORK_ERROR)
        // @ts-ignore
        return Promise.reject(res.data.message || NETWORK_ERROR)
    }

})

// 封装核心函数
function request(options) {

    options.method = options.method || 'get'
    if (options.method.toLowerCase() === 'get') {
        options.params = options.data
    }
    //对mock处理
    let isMock = config.mock
    if (typeof options.mock !== 'undefined') {
        isMock = options.mock
    }
    //对正式环境做处理
    if (config.env === 'prod') {
        // 不给使用Mock的机会
        service.defaults.baseURL = config.baseApi
    } else {
        service.defaults.baseURL = isMock ? config.mockApi : config.baseApi
    }
    console.log(service.defaults.baseURL)

    return service(options)
}

/**
 * 封装post请求
 * @param url
 * @param data
 * @returns {Promise}
 */
export function post(url, data = {}) {
    // @ts-ignore
    return new Promise((resolve, reject) => {
        axios.post(url, data).then(
            (response) => resolve(response.data),
            (error) => reject(error)
        );
    });
}

/**
 * 封装delete请求
 * @param url
 * @param data
 * @returns {Promise}
 */
export function deletes(url, data = {}) {
    // @ts-ignore
    return new Promise((resolve, reject) => {
        axios.delete(url, data).then(
            response => resolve(response.data),
            error => reject(error)
        );
    });
}

/**
 * 封装put请求
 * @param url
 * @param data
 * @returns {Promise}
 */
export function put(url, data = {}) {
    // @ts-ignore
    return new Promise((resolve, reject) => {
        axios.put(url, data).then(
            response => resolve(response.data),
            error => reject(error)
        );
    });
}

export default request