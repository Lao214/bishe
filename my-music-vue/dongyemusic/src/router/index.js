import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/views/Home'
import Song from '@/views/Song'
import Stock from '@/views/Stock'
import StockAudit from '@/views/StockAudit'
import Login from '@/views/Login'
import Layout from '@/Layout/Layout'
import Register from '@/views/Register'
import Article from '@/views/Article'
import Count from '@/views/Count'
import CangMap from '@/views/CangMap'
import Warehouse from '@/views/Warehouse'
import MemberApply from '@/views/MemberApply'
import Index from '@/views/Index'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/index',
      name: 'Index',
      component: Index
    },
    {
      path: '/',         //当访问/时会进入框架LayOut界面
      name: 'Layout',
      component: Layout,
      redirect: "/index", //重定向 当你访问“/”的时候 会重定向到"/home"
      //嵌套路由
      children :[
        {
          path: '/home',
          name: 'Home',
          component: Home
        },
        {
          path: '/song',
          name: 'Song',
          component: Song
        },
        {
          path: '/stock',
          name: 'Stock',
          component: Stock
        },
        {
          path: '/cangMap',
          name: 'CangMap',
          component: CangMap
        },
        {
          path: '/stockAudit',
          name: 'StockAudit',
          component: StockAudit
        },
        {
          path: '/warehouse',
          name: 'Warehouse',
          component: Warehouse
        },
        {
          path: '/article',
          name: 'Article',
          component: Article
        },
        {
          path: '/count',
          name: 'Count',
          component: Count
        },
        {
          path: '/memberApply',
          name: 'MemberApply',
          component: MemberApply
        }
      ]
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
    }
  ]
})
