<template>
  <div class="register">
    <h2>请注册你的账号</h2>
   <form action="">
    <span>账号:</span><input type="text"><br><br>
    <span>密码:</span><input type="password"><br><br>
    <span>手机:</span><input type="text"><br><br>
    <button>注册</button> 
    <button @click="back">已有账号?点击返回</button>
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
.register {
    text-align: center;
    margin-top: 50px;
}

</style>