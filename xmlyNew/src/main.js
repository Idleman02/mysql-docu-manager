import { createApp } from 'vue'
// element-plush

// 使用中文语言包

import ElementPlus from 'element-plus'
import '/@modules/element-plus/dist/index.css'
import App from './App.vue'
import axios from "axios";
import router from './router/index'
// 导入字体图标
import './assets/fonts/iconfont.css'
// 导入全局样式表
import './assets/css/global.css'
// 导入element-plus的所有图标
// import * as ElementPlusIconsVue from '@element-plus/icons-vue'

//注意use要在mount之前
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
const app = createApp(App)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}





app.use(router).mount('#app')
app.use(ElementPlus)
// app.mount('#app')

