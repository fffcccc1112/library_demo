<template>
  <div class="login-container">
    <h2>注册</h2>
    <form @submit.prevent="">
      <div>
        <label for="username">账号:</label>
        <input type="text" id="username"/>
      </div>
      <div>
        <label for="password">密码:</label>
        <input type="password" id="password"/>
      </div>
      <div>
        <label for="phone">手机号:</label>
        <input type="text" id="phone"/>
      </div>
      <button type="submit">注册</button>
      <button @click="back">返回</button>
    </form>
  </div>
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router'; // 导入useRouter钩子
const router = useRouter()
function back(){
    router.push('/login')
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