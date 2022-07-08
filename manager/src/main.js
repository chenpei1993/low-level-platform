import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import http from './util/HttpUtil'

createApp(App)
    .use(router)
    .use(ElementPlus)
    .provide("$http", http)
    .mount('#app')
