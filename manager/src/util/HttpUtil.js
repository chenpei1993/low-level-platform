import axios from "axios";
import { ElMessage } from 'element-plus';
import router from '../router';
import StringUtil from '@/util/StringUtil'

const headers = {
    Accept: "*/*",
    ContentType: "application/json"
}
const url = process.env.VUE_APP_SERVER_API + "/manage"
console.log(process.env)
console.log(process.env.VUE_APP_SERVER_API)

const http = axios.create({
    baseURL: url,
    timeout: 6000,
    headers: headers
})

// 数据请求拦截
http.interceptors.request.use((config) => {
    let jwt = sessionStorage.getItem("jwt")
    if(StringUtil.isEmpty(jwt)){
        return config;
    }
    config.headers["Authorization"] = "Bearer " + jwt
    return config;
}, (error) => {
    return Promise.reject(error);
});

// 返回响应数据拦截
http.interceptors.response.use((res) => {
    const data = res.data;
    // 状态码为 2xx 范围时都会调用该函数，处理响应数据
    if (res.status === 200) {
        if(data.code !== 200){
            ElMessage.error(data.msg)
            return Promise.reject(data)
        }
        return Promise.resolve(data.data)
    }
}, (error) => {
    if(error.response.status === 401){
        ElMessage({
            type: 'error',
            message: '登录过期，请重新登录！',
            showClose: true
        });
        router.push("/login")
        return Promise.reject(error)
    }else if(error.response.status === 404){
        router.push("/404")
        return Promise.reject(error)
    }
    ElMessage({
        type: 'error',
        message: 'Oooops，发生错误，请联系管理员！',
        showClose: true
    });
    router.push("/error")
    return Promise.reject(error);
});


export default http;