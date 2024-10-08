<script lang="ts" setup>
import axios from "axios";
import { ref } from "vue";
import { useRouter } from 'vue-router'; // 导入useRouter钩子
import { useUserStore } from "@/stores/useUserStore";
const router = useRouter();
const userstore = useUserStore();
// 定义书籍类型
type book = {
  bookid: number;
  bookname: string;
  author: string;
  borrow: string;
  cover: string;
  exist: boolean;
  outtime: Date;
};

// 创建书籍数据的响应式引用
const tableData = ref<book[]>([
  {
    bookid: 0,
    bookname: "",
    author: "",
    borrow: "",
    cover: "",
    exist: false,
    outtime: new Date()
  }
]);
  // 打印响应数据
//   console.log("=====");
//   console.log(userstore.token)
// // 点击事件处理函数
const handleClick = () => {
  console.log('click');
};

// 发送 GET 请求获取书籍数据
axios.get('/api/book/all', {
  headers: {
    'Content-Type': 'application/json',
    'Authorization':userstore.token
  }
})
.then(response => {
  // 更新表格数据
  tableData.value = response.data;

})
.catch(error => {
  // 打印错误信息
  console.error('Error:', error);
  alert("登录已过期，请重新登陆")
  router.push('/login')
});
</script>
<template>
  <el-table :data="tableData" style="width: 100%" class="list">
    <el-table-column fixed prop="bookid" label="图书编号" width="150" />
    <el-table-column prop="bookname" label="书名" width="120" />
    <el-table-column prop="author" label="作者" width="120" />
    <el-table-column prop="cover" label="类型" width="120" />
    <el-table-column prop="exist" label="是否存在" width="120" />
    <el-table-column fixed="right" label="Operations" min-width="120">
         <template #default>
        <el-button link type="primary" size="small" @click="handleClick">
          详情
        </el-button>
        <el-button link type="primary" size="small">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
  <!-- 分页条 -->
  <div class="example-pagination-block">
    <el-pagination layout="prev, pager, next" :total="50" />
  </div>
</template>

<style scoped>
.list {
  margin-top: 20px;
}
.example-pagination-block {
  display: flex;
  justify-content: end;
}
</style>