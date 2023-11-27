import {createRouter, createWebHistory} from 'vue-router'
import {useUserStore} from "@/stores/userInfo";


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {path: '/', component: ()=>import('../views/user/LoginPage.vue')},
    {path: '/login', component: ()=>import('../views/user/LoginPage.vue')},
    {path: '/Register', component: ()=>import('../views/user/Register.vue')},
    {
      path: '/front', component: ()=>import('../views/layout/FrontPage.vue'),
      redirect:'/front/IndexPage',
      children:[
        {path: '/front/IndexPage', component: ()=>import('../views/front/IndexPage.vue')},
        {path: '/front/About', component: ()=>import('../views/front/About.vue')},
        {path: '/front/BookInfo', component: ()=>import('../views/front/BookInfo.vue')}
      ]
    },
    {
      path: '/end', component: ()=>import('../views/layout/EndPage.vue'),
      redirect:'/end/Comment',
      children:[
        {path: '/end/sysUser', component: ()=>import('../views/manage/SysUser.vue')},
        {path: '/end/IndexPage', component: ()=>import('../views/manage/IndexPage.vue')},
        {path: '/end/Book', component: ()=>import('../views/manage/Book.vue')},
        {path: '/end/Comment', component: ()=>import('../views/manage/Comment.vue')},
      ]
    },
  ]
})


router.beforeEach((to, from, next) => {
  if (to.href === '/login' || to.href === '/Register') {
    next()
  } else {
    if (to.href !== '/login' && useUserStore().user === null) {
      next('/login')
    } else {
      next()
    }
  }
})


export default router
