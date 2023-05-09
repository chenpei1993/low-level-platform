import { createRouter, createWebHistory } from 'vue-router'
import Question from '../views/Question.vue'
import Index from '../views/Index.vue'
import Home from '../views/Home.vue'
import Custom from '../views/Custom.vue'
import Info from '../views/Info.vue'
import InfoResult from '../views/InfoResult.vue'
import Tag from '../views/Tag.vue'
import Login from '../views/Login.vue'
import User from '../views/User.vue'
import Role from '../views/Role.vue'
import Permission from '../views/Permission.vue'


const routes = [
  {
    path: '/',
    name: 'Index',
    component: Index,
    children: [
      {
        path: '/home',
        name: 'Home',
        component: Home
      },
      {
        path: '/info',
        name: 'Info',
        component: Info
      },
      {
        path: '/info/InfoResult',
        name: 'InfoResult',
        component: InfoResult
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
        path: '/tag',
        name: 'Tag',
        component: Tag
      },
      {
        path: '/user',
        name: 'User',
        component: User
      },
      {
        path: '/role',
        name: 'Role',
        component: Role
      },
      {
        path: '/permission',
        name: 'Permission',
        component: Permission
      },
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
