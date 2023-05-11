import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import http from '@/util/HttpUtil'
import * as echarts from 'echarts'
import zhCN from 'element-plus/lib/locale/lang/zh-cn'
import lodash from  'lodash'
import permission from "@/directive/permission"

let Vue = createApp(App)
    .use(router)
    .use(ElementPlus, {locale: zhCN})
    .provide("$http", http)
    .provide("$lodash", lodash)
    .provide("$echarts", echarts)


Vue.directive('auth', permission)
Vue.mount('#app')
