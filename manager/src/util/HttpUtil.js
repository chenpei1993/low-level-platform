import axios from "axios";
import { ElMessage } from 'element-plus';
import router from '../router';
import StringUtil from '@/util/StringUtil'

const headers = {
    Accept: "*/*",
    ContentType: "application/json"
}

const http = axios.create({
    baseURL: "http://127.0.0.1:8090/manage",
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
        router.push("/login?returnUrl=" + encodeURIComponent(router.currentRoute.fullPath));
        return Promise.reject(error);
    }
    ElMessage({
        type: 'error',
        message: 'Oooops，发生错误，请联系管理员！',
        showClose: true
    });
    return Promise.reject(error);
});


export default http;