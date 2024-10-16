import './assets/main.css'
import { createApp } from 'vue'
import { createPinia } from 'pinia'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus' 
import 'element-plus/dist/index.css';
import service from './utils/request'
const app = createApp(App)
const pinia = createPinia()
app.config.globalProperties.$axios =service
app.use(pinia)
app.use(router)
app.use(ElementPlus)
app.mount('#app')
