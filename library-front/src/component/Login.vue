<template>
   <div class="main">
    <form @submit.prevent="handleLogin">
    <h3>用户登录</h3>
    <span>账号: </span><input type="text" placeholder="请输入你的账号" v-model="user.username" required><br><br>
    <span>密码: </span><input type="password" placeholder="请输入你的密码" v-model="user.password" required><br>
    <span>{{ message }}</span><br>
    <button @click="register">注册</button>
    <button type="submit" >登录</button>
   </form>
   </div>

</template>
<script setup lang="ts">
import axios from 'axios';
import { ref } from 'vue';
import { useRouter } from 'vue-router'; // 导入useRouter钩子
const router = useRouter()
const user = ref({"username":'',"password":''})
const message = ref('')
const handleLogin = async (event: { preventDefault: () => void; }) => {
  event.preventDefault(); // 阻止表单默认提交行为
  if (user.value.username === '' || user.value.password === '') {
    message.value = "用户名或密码不能为空！";
  } else {
    message.value = '';
    try {
      const response = await axios.post('/api/login', user.value);
      console.log(response.data);
      router.push('/home');
      // 处理登录成功，例如跳转到主页或其他操作
    } catch (error: any) {
      if (error.response && error.response.status === 401) {
        message.value = '账号或者密码错误!';
      } else {
        message.value = '登录出错!';
      }
    }
  }
};
function register(){
  router.push('/register')
}
</script>
<style>
.main {
  margin-top: 100px;
  text-align: center;
}
</style>