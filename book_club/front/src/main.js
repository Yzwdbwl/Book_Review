import '@/assets/main.scss'
import 'element-plus/dist/index.css'
import { createApp } from 'vue'
import pinia from "@/stores";
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
const app = createApp(App)

app.use(router)
app.use(pinia)
app.use(ElementPlus, {
    locale: zhCn,
})
app.mount('#app')
