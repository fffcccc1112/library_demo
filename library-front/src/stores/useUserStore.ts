import { defineStore } from 'pinia';
import { ref } from 'vue';

export const useUserStore = defineStore('user',()=>{
    const token = ref('');
    const user = ref('')
    function set(newtoken: string){
        token.value=newtoken
    }
    function setname(username: string){
        user.value = username
    }
    return {token,set,user,setname}
});