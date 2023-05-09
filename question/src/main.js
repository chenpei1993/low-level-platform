import { createApp } from 'vue'
import App from './App.vue'
import http from './util/HttpUtil'
// import ElementPlus from 'element-plus'
// import 'element-plus/dist/index.css'

createApp(App)
    // .use(ElementPlus, {locale: zhCN})
    .provide("$http", http)
    .mount('#app')
