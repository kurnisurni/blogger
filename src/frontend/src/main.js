import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import store from './store'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import NewArticle from './views/NewArticle'
import EditArticle from './views/EditArticle'
import SortArticle from './views/SortArticle'
import Home from './views/Home'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.config.productionTip = false

Vue.use(VueRouter)

// Install BootstrapVue
Vue.use(BootstrapVue)
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin)

const router = new VueRouter({
  routes: [
    { path: '/', name: 'home', component: Home},
    { path: '/edit/:id', name: 'editArticle', component: EditArticle, props: true},
    { path: '/new', name: 'newArticle', component: NewArticle},
    { path: '/sort', name: 'sortArticle', component: SortArticle},
  ],
  mode: "history"
})

new Vue({
  render: h => h(App),
  store,
  router
}).$mount('#app')
