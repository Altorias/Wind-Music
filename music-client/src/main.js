import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import router from './router'
import store from "./store";
import './assets/css/index.scss'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import './utils/utils.ts'

const app = createApp(App)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}


app.use(router).use(store)
app.mount('#app')
