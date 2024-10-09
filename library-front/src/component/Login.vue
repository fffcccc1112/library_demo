<template>
  <div class="login-container">
    <h2>登录</h2>
    <form @submit.prevent="handleLogin">
      <div>
        <label for="username">账号:</label>
        <input type="text" id="username" v-model="username" required />
      </div>
      <div>
        <label for="password">密码:</label>
        <input type="password" id="password" v-model="password" required />
      </div>
      <button type="submit">登录</button>
      <button @click="register">注册</button>
    </form>
  </div>
</template>

<script setup lang="ts">
import axios from 'axios';
import { ref } from 'vue';
import { useRouter } from 'vue-router'; // 导入useRouter钩子
import  {useUserStore}  from '@/stores/useUserStore';
import { walkUpBindingElementsAndPatterns } from 'typescript';
const userstore = useUserStore();
const router = useRouter()
const username = ref('')
const password = ref('')
const pass = ref('')
// const token = ref('')
  const handleLogin = async (event:any) => {
    // 阻止表单的默认提交行为
    event.preventDefault();
    const formData = new FormData();
    formData.append('username', username.value);
    formData.append('password', password.value);
      // 这里可以添加登录逻辑，例如调用 API
    try {
      const response = await axios.post('/api/user/login',formData)
      if(response.data.code==0){
        pass.value=response.data.data
        userstore.set(pass.value);
        userstore.setname(username.value);
        alert("登录成功!")
        router.push({ name: 'book'});
      }
      else {
        alert(response.data.message)
      }
    } catch (error) {
      console.log("操作失败")
    }
  };
function register(){
  router.push('/register');
}
</script>

<style scoped>
.login-container {
  max-width: 300px;
  margin: 50px auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.login-container div {
  margin-bottom: 15px;
}

label {
  display: block;
  margin-bottom: 5px;
}

input[type="text"],
input[type="password"] {
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

button {
  width: 100%;
  padding: 10px;
  background-color: #007bff;
  border: none;
  color: white;
  cursor: pointer;
  margin-bottom: 5px;
}

button:hover {
  background-color: #0056b3;
}

p {
  color: red;
}
</style>