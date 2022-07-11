import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import http from './util/HttpUtil'
import zhCN from 'element-plus/lib/locale/lang/zh-cn'

createApp(App)
    .use(router)
    .use(ElementPlus, {locale: zhCN})
    .provide("$http", http)
    .mount('#app')
