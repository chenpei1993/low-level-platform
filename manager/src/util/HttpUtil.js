import axios from "axios";
import { ElMessage } from 'element-plus';

const http = axios.create({
    baseURL: 'http://127.0.0.1:8090/manage',
    timeout: 6000
})

// 数据请求拦截
http.interceptors.request.use((config) => {
    return config;
}, (error) => {
    return Promise.reject(error);
});

// 返回响应数据拦截
http.interceptors.response.use((res) => {
    const data = res.data;
    // 状态码为 2xx 范围时都会调用该函数，处理响应数据
    if (res.status === 200) {
        return Promise.resolve(data);
    }else{
        ElMessage({
            type: 'error',
            message: data.msg,
            showClose: true
        });
    }
}, (error) => {
    ElMessage({
        type: 'error',
        message: '请联系管理员！',
        showClose: true
    });
    return Promise.reject(error);
});


export default http;