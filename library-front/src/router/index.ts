//创建一个路由器，并暴露出去
//引入createrrouter
import { createRouter,createWebHistory } from "vue-router";
import BookList from "@/page/BookList.vue";
import Login from "@/page/Login.vue";
import Register from "@/page/Register.vue";
import AddBooks from "@/page/AddBooks.vue";
//创建路由器
const router = createRouter({
    history:createWebHistory(),//路由器的工作模式
    routes:[
        {
            path:'/register',
            component:Register,
            name:'Register'
        },
        {
            path:'/book',
            name: 'book',
            component:BookList,
            children:[
                {
                    path:"/update",
                    name:"update",
                    component:AddBooks
                }
            ]
        },
        {
            path:"/login",
            name:"Login",
            component:Login
        },
        {
            //重定向
            path:'/',
            redirect:'/book'
        }
    ]
})

// router.beforeEach((to, from, next) => {
//     if(to.name === "login"){
//         return;
//     }
//     // TODO: 当token 失效，logout，
//     next({
//         name: "login",
//         params: {
//             redirect: to.fullPath,
//         }
//     })
// })

//暴露出去
export default router