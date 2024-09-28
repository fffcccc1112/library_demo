<template>
  <div class="text">
    <h1>图书列表</h1>
    <ol>
      <li v-for="book in booklist" :key="book.id">
        <span>书名:《{{ book.bookname }}》</span>
        <span>作者:{{ book.author }}</span>
        <div class="but">
          <el-button @click="deletebook(book.id)">删除</el-button>
          <el-button>修改</el-button>
        </div>
      </li>
    </ol>
  </div>
</template>

<script setup lang="ts">
import axios from 'axios';
import { ref } from 'vue';

// 初始化 booklist 为一个空数组
let booklist = ref<{ id: number; bookname: string; author: string }[]>([]);

async function fetchBooks() {
  try {
    const response = await axios.get('/api/books');
    // 确保响应数据是数组类型
    if (Array.isArray(response.data)) {
      booklist.value = response.data;
    } else {
      console.error('API response is not an array');
    }
    console.log(booklist.value); // 打印 booklist 的响应式数据
  } catch (error) {
    console.error(error); // 处理错误
  }
}
fetchBooks();

async function deleteBookApi(id: number) {
  const response = await axios.delete(`/api/bookdel/${id}`);
  return response.data; // 返回响应数据
}

// 异步函数，调用 deleteBookApi 并处理响应或错误
async function deletebook(id: number) {
  try {
    await deleteBookApi(id);
    // 从 booklist 中移除被删除的图书
    booklist.value = booklist.value.filter((book) => book.id !== id);
  } catch (error) {
    console.error(error); // 处理错误
  }
}
</script>
<style scoped>
.text {
  text-align: left;
}
span {
  margin-right: 10px;
}
.but {
  display: flex;
  margin-right: 100px;
  margin-top: 10px;
  margin-bottom: 10px;

}

</style>