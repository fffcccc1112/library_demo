import { defineStore } from 'pinia';
import { ref } from 'vue';

export const useUserStore = defineStore('user',()=>{
    const token = ref('');
    function set(newtoken: string){
        token.value=newtoken
    }
    return {token,set}
});