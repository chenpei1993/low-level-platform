import { createRouter, createWebHistory } from 'vue-router'
import Question from '../views/Question.vue'
import Home from '../views/Home.vue'
import Custom from '../views/Custom.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/question',
    name: 'Question',
    component: Question
  },
  {
    path: '/custom',
    name: 'Custom',
    component: Custom
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
