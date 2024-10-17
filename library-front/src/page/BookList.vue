<script lang="ts" setup>
import axios from "axios";
import { ref,reactive } from "vue";
import { useRouter } from 'vue-router'; // 导入useRouter钩子
import { backtopEmits, componentSizeMap } from "element-plus";
import { ElDrawer, ElMessageBox } from 'element-plus'
import { reduceEachLeadingCommentRange } from "typescript";
import service from "@/utils/request";
import Cookies from "js-cookie";
import addBooks from "./bookcompent/addBooks.vue";
const router = useRouter();
const chose = ref('all')
const username =ref()
username.value = Cookies.get('username')
const timestampStr = Cookies.get('expiredTime');
let expired: number = timestampStr? parseInt(timestampStr, 10) : 0;
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
//实现根据id搜索的功能
const searchText = ref('')
const findbook = () => {
  service.get(`http://localhost:5174/api/book/find/${searchText.value}`)
  .then(response => {
    if (response.status==200) { // 检查响应是否成功
      tableData.value = [];
      tableData.value.push(response.data.data);
      console.log(tableData)
      console.log("Data fetched successfully");
      // console.log(response.data.data);
    } else {
      console.error('Failed to fetch data:', response.status);
    }
  })
  .catch(error => {
    console.error('Error fetching data:', error.message); 
  });
}
const deleteBook = (bookId: number) => {
  service.delete(`http://localhost:5174/api/book/delete/${bookId}`)
  .then(() => {
    // 删除成功后，从tableData中移除该书籍
    tableData.value = tableData.value.filter(book => book.bookid !== bookId);
  })
  .catch(error => {
    // 打印错误信息
    // console.error('Error:', error);
    alert("删除失败，请重试");
  });
};
const search = (sty: string) => {
  if (chose.value === "all") {
     service.get('http://localhost:5174/api/book/all')
     .then(response=>{
      // console.log(response)
      //怎么样将reaponce赋值给tabledata
      tableData.value=response.data
     })
     .catch(error=>{
      console.log("Error:", error);
    // 如果是网络错误等，可以更详细地打印错误信息
    if (error.response) {
        console.log(`Server responded with an error: ${error.response.status}`);
    } else if (error.request) {
        console.log('No response received from the server.');
    } else {
        console.log(`Error setting up the request: ${error.message}`);
    }
     })
  }
  else if(chose.value === "false"){
    service.get('http://localhost:5174/api/book/findfalse')
    .then(response=>{
      tableData.value=response.data.data
    })
    .catch(error=>{
      console.log("Error:", error);
    // 如果是网络错误等，可以更详细地打印错误信息
    if (error.response) {
        console.log(`Server responded with an error: ${error.response.status}`);
    } else if (error.request) {
        console.log('No response received from the server.');
    } else {
        console.log(`Error setting up the request: ${error.message}`);
    }
     })
  }
  else if(chose.value === "true"){
    service.get('http://localhost:5174/api/book/findtrue')
    .then(response=>{
      tableData.value=response.data.data
    })
    .catch(error=>{
      console.log("Error:", error);
    // 如果是网络错误等，可以更详细地打印错误信息
    if (error.response) {
        console.log(`Server responded with an error: ${error.response.status}`);
    } else if (error.request) {
        console.log('No response received from the server.');
    } else {
        console.log(`Error setting up the request: ${error.message}`);
    }
     })
  }
}
const options = [
  {
    chose: 'false',
    label: '已借阅',
  },
  {
    chose:'true',
    label: '未借阅',
  },
  {
    chose:'all',
    label:'全部'
  }
]
const borrowbook = (username: string,bookid: number)=>{
  const bookborrow = ref({
    username: username,
    bookid: bookid
  })
      //实现借阅的接口
      service.post('http://localhost:5174/api/book/borrow',bookborrow.value)
      .then(response => {
        console.log(response.data)
        alert(response.data.message)
    })
    .catch(error => {
      // 处理错误情况
      alert("借阅失败")
      console.error('Error borrowing book', error);
    });
}
const backbook =  (bookid: number) => {
  service.get(`http://localhost:5174/api/book/return/${bookid}`)
  .then(response=>{
    alert(response.data.message)
  })
}
// 详情的效果
const drawer = ref(false)
const currentBook: any = ref([])
function showDetails(this: any, scope:object){
  this.currentBook = scope;
  console.log(currentBook)
  drawer.value=true

}
// 实现分页的效果
const currentPage = ref(1);
const pageSize = ref(5);
function handleSizeChange(val:number){
  pageSize.value = val;
}
function handleCurrentChange(val:number){
    currentPage.value = val
}
</script> 
<template>
  <div class="head">
    <span>你好！{{ username }}</span><br>
    <span>请注意登录过期时间{{  expired }}</span><br>
  </div>
       <!-- 搜索框 -->
       <el-input
      type="text"
      prefix-icon="el-icon-search"
      v-model="searchText"
      placeholder="请输入搜索的图书号"
      class="secrchbut"
      :style="{ width: '241px' }"
    ></el-input>
    <el-button type="primary" class="but" @click="findbook">搜索</el-button>
  <!-- 筛选框 -->
  <div class="flex flex-wrap gap-4 items-center">
    <el-select v-model="chose" placeholder="筛选" style="width: 240px">
      <el-option
        v-for="item in options"
        :key="item.chose"
        :label="item.label"
        :value="item.chose"
      />
    </el-select>
    <el-button type="primary" @click="search(chose)">查找</el-button>

  </div>
  <!-- 表格 -->
  <el-table :data="tableData.slice((currentPage-1)*pageSize,currentPage*pageSize)"
   style="width: 100%" class="list">
    <el-table-column fixed prop="bookid" label="图书编号" width="150" />
    <el-table-column prop="bookname" label="书名" width="120" />
    <el-table-column prop="author" label="作者" width="120" />
    <el-table-column prop="cover" label="类型" width="120" />
    <el-table-column prop="exist" label="是否存在" width="120" />
    <el-table-column fixed="right" label="Operations" min-width="120">
         <template #default="scope">
        <el-button link type="primary" size="small"  @click="showDetails(scope.row)">
          详情
        </el-button>
            <el-button link type="primary"
              size="small"
              @click="deleteBook(scope.row.bookid)">
                删除
       </el-button>
  
        <el-button link type="primary" size="small" 
         @click="borrowbook(username,scope.row.bookid)">
         借阅
        </el-button>
        <el-button link type="primary" size="small" @click="backbook(scope.row.bookid)">
          还书
        </el-button>
      </template>
    </el-table-column>
  </el-table>
    <!-- 实现分页效果 -->
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-sizes="[5, 10, 20, 40]"
      :page-size="pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="tableData.length">
  </el-pagination>
    <!-- 详情弹窗 -->
    <el-drawer v-model="drawer" 
      title="这里是图书详情页面" 
      size="50%"
    >
      <div v-if="currentBook">
        <ul>
          <li><span>编号:</span>{{ currentBook.bookid}}</li>
          <li><span>书名:</span>{{ currentBook.title }}</li>
          <li><span>作者:</span>{{ currentBook.author }}</li>
          <li><span>是否存在:</span>{{currentBook.exist}}</li>
          <li><span>借阅时间:</span>{{currentBook.outtime}}</li>
          <li><span>读者:</span>{{currentBook.borrow}}</li>
          <li>
            <h3>封面</h3>
            <img :src="`http://localhost:8088/localimage/${currentBook.cover}`">
          </li>
        </ul>
      </div>
    </el-drawer>
  <addBooks/>
</template>

<style scoped>
.list {
  margin-top: 20px;
}
.example-pagination-block {
  display: flex;
  justify-content: end;
}
.head {
  text-align: right;
}
.but {
  margin-bottom: 5px;
}

</style>