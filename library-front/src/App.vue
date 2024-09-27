<template>
  <Header/>
  <div>
    <RouterView></RouterView>
  </div>
</template>
<script setup lang="ts">
import { RouterView,RouterLink } from 'vue-router';
import Header from './component/Header.vue';
</script>
<style scoped>
 *{
  padding: 0;
  margin: 0;
 }
  </style>
