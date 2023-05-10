import axios from "axios";

const headers = {
    Accept: "*/*",
    ContentType: "application/json"
}
const url = process.env.VUE_APP_SERVER_API


const http = axios.create({
    baseURL: url,
    timeout: 6000,
    headers: headers
})


// 返回响应数据拦截
http.interceptors.response.use((res) => {
    const data = res.data;
    // 状态码为 2xx 范围时都会调用该函数，处理响应数据
    if (res.status === 200) {
        return Promise.resolve(data)
    }
}, (error) => {
    return Promise.reject(error);
});

export default http;