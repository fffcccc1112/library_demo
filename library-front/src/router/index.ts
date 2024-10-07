//创建一个路由器，并暴露出去
//引入createrrouter
import { createRouter,createWebHistory } from "vue-router";
import BookList from "@/page/BookList.vue";
import Login from "@/component/Login.vue";
import Register from "@/page/Register.vue";
import AddBooks from "@/page/AddBooks.vue";
//创建路由器
const router = createRouter({
    history:createWebHistory(),//路由器的工作模式
    routes:[
        // {
        //     path:'/news',
        //     component:News,
        //     children:[
        //         {   
        //             name:'xiang',
        //             path:'details/:title/:value',
        //             component:Details,
        //             props:true
        //         }
        //     ]
        // },
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
            redirect:'/login'
        }
    ]
})
//暴露出去
export default router