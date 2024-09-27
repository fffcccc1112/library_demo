<template>
    <h1>这里是用户列表！</h1>
   <ul>
    <li v-for="x in items" :key="x.id">
        <span>用户名：{{ x.username }} </span>
        <span> 密码：{{ x.password }}</span>
    </li>
   </ul>
  </template>
  
  <script setup lang="ts">
  import axios from "axios";
  import { onMounted, ref } from "vue";
  
  // 创建一个响应式变量来存储数据
  const items:any = ref([]);
  
  // 定义异步函数来获取数据
  const quarryAll = async () => {
    try {
      const response = await axios.get('/api/all');
      // 将响应数据保存到响应式变量中
      items.value = response.data;
    } catch (error) {
      console.error(error);
    }
  };
  
  // 在组件挂载后调用数据获取函数
  onMounted(quarryAll);
  </script>

<style>

</style>