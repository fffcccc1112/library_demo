import axios from "axios";
import router from "@/router";
import { server } from "typescript";
import Cookies from "js-cookie";
const service = axios.create({
    baseURL: 'localhost:8088/'
})
//请求拦截器
service.interceptors.request.use(
    config => {
        //同一注入token
        if(Cookies.get('token')){
            config.headers['Authorization'] = `Bearer ${Cookies.get('token')}`
            config.headers['Content-Type'] = 'application/json'
        }
        return config
    },
    error =>{
        return Promise.reject(error);
    }
)
//响应拦截器
service.interceptors.response.use(
    response => {
        const { status, data } = response;
        const currentTimestamp = Date.now();
        const timestampStr = Cookies.get('expiredTime');
        let expired: number = timestampStr? parseInt(timestampStr, 10) : 0;
        if(currentTimestamp+1000*60*45>=expired){
            // prom: 没有成功加上响应头 Cookies.get('token')
           axios.post('/api/user/refresh',{},{
            headers: {
                Authorization: 'Bearer '+Cookies.get('token')
            }
           })
           .then(response=>{
            console.log(response)
            Cookies.set("token",response.data.token)
            Cookies.set("expiredTime",response.data.expiredTime)
           })
        }
        if(status==200){
            return response;
        }
        else {
            // console.log(response)
            return Promise.reject(response.data);
        }
       
    },
    error => {
        console.error('响应拦截器错误：', error);
        if (error.response && error.response.status === 401) {
            // 处理未授权错误，例如跳转到登录页面
            router.push('/login');
        } else {
            return Promise.reject(error);
        }
    }
);

export default service