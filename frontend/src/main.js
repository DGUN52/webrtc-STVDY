import './assets/main.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'
import axios from 'axios'

axios.defaults.baseURL = 'http://localhost:8080'

import { fakeBackend } from './helpers'
fakeBackend()

const app = createApp(App)

// app.config.globalProperties.axios = axios
app.use(createPinia()).use(router).mount('#app')
