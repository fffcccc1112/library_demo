<script lang="ts" setup>
import axios from "axios";
import { ref,reactive } from "vue";
import { useRouter } from 'vue-router'; // 导入useRouter钩子
import { useUserStore } from "@/stores/useUserStore";
import { backtopEmits } from "element-plus";
import { ElDrawer, ElMessageBox } from 'element-plus'
import { error } from "console";
const router = useRouter();
const userstore = useUserStore();
const chose = ref('all')

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
//实现详细信息弹窗

const handleClick = () => {
  console.log('click');
};
//实现根据id搜索的功能
const searchText = ref('')
const findbook = () => {
  axios.get(`/api/book/find/${searchText.value}`, {
    headers: {
      'Content-Type': 'application/json',
      'Authorization': userstore.token 
    }
  })
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
   // console.log(response.data)

 })
 .catch(error => {
   // 打印错误信息
   console.error('Error:', error);
   alert("登录已过期，请重新登陆")
   router.push('/login')
 });
const deleteBook = (bookId: number) => {
  axios.delete(`/api/book/delete/${bookId}`, {
    headers: {
      'Authorization': userstore.token
    }
  })
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
    axios.get('/api/book/all', {
      headers: {
        'Authorization': userstore.token
      }
    })
    .then(response => {
      if (response.status === 200) {
        tableData.value = response.data
      } else {
        console.error('Failed to fetch data:', response.status);
      }
    })
    .catch(error => {
      console.error('Error fetching all books:', error);
      alert("请求失败，请重试");
    });
  } else if (chose.value === 'false') {
    axios.get('/api/book/find/false', {
      headers: {
        'Authorization': userstore.token
      }
    })
    .then(response => {
      if (response.status === 200) {
        tableData.value = response.data
      } else {
        console.error('Failed to fetch false books:', response.status);
      }
    })
    .catch(error => {
      console.error('Error fetching false books:', error);
      alert("请求失败，请重试");
    });
  } else if (chose.value === 'true') {
    axios.get('/api/book/find/true', {
      headers: {
        'Authorization': userstore.token
      }
    })
    .then(response => {
      if (response.status === 200) {
        tableData.value = response.data
      } else {
        console.error('Failed to fetch true books:', response.status);
      }
    })
    .catch(error => {
      console.error('Error fetching true books:', error);
      alert("请求失败，请重试");
    });
  }
};
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
      axios.post('/api/book/borrow',bookborrow.value,{
        headers: {
        'Authorization': userstore.token
      }
      })
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
const backbook = (bookid: number)=>{
  axios.get(`/api/book/return/${bookid}`,
    {
      headers: {
        'Authorization': userstore.token
      }
    })
    .then(response => {
    // 处理成功的响应
     alert(response.data.message)
    })
    .catch(error=>{
      alert("还书失败")
    })

}
//设置弹窗
const formLabelWidth = '80px'
let timer:any
const dialog = ref(false)
const loading = ref(false)
const form = reactive({
  bookid:'',
  title:'',
  author:'',
  cover:''
})


const onClick = () => {
  axios.post('/api/book/add',form,{
    headers: {
        'Authorization': userstore.token
      }
  })
  .then(response=>{
    alert(response.data.message)
  })
  .catch(error=>{
    alert("添加失败")
  })
  loading.value = true
  setTimeout(() => {
    loading.value = false
    dialog.value = false
  }, 400)
}
const cancelForm = () => {
  loading.value = false
  dialog.value = false
  clearTimeout(timer)
}
</script> 
<template>
  <div class="head"><span>你好！{{userstore.user }}</span></div>
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
  <el-table :data="tableData" style="width: 100%" class="list">
    <el-table-column fixed prop="bookid" label="图书编号" width="150" />
    <el-table-column prop="bookname" label="书名" width="120" />
    <el-table-column prop="author" label="作者" width="120" />
    <el-table-column prop="cover" label="类型" width="120" />
    <el-table-column prop="exist" label="是否存在" width="120" />
    <el-table-column fixed="right" label="Operations" min-width="120">
         <template #default="scope">
        <el-button link type="primary" size="small" @click="handleClick">
          详情
        </el-button>
        <el-button link type="primary" size="small" @click="deleteBook(scope.row.bookid)">
          删除
        </el-button>
        <el-button link type="primary" size="small" 
         @click="borrowbook(scope.row.username,scope.row.bookid)">
         借阅
        </el-button>
        <el-button link type="primary" size="small" @click="backbook(scope.row.bookid)">
          还书
        </el-button>
      </template>
    </el-table-column>
  </el-table>
    <!-- 详情弹窗 -->
  <el-button text @click="dialog = true"
    >新增图书</el-button
  >
  <el-drawer
    v-model="dialog"
    title="请输入图书的基本信息"
    direction="ltr"
    class="demo-drawer"
  >
    <div class="demo-drawer__content">
      <el-form :model="form">
        <el-form-item label="图书编号" :label-width="formLabelWidth">
          <el-input v-model="form.bookid" autocomplete="off" />
        </el-form-item>
        <el-form-item label="书名" :label-width="formLabelWidth">
          <el-input v-model="form.title" autocomplete="off" />
        </el-form-item>
        <el-form-item label="作者" :label-width="formLabelWidth">
          <el-input v-model="form.author" autocomplete="off" />
        </el-form-item>
        <el-form-item label="篇幅" :label-width="formLabelWidth">
          <el-input v-model="form.cover" autocomplete="off" />
        </el-form-item>
      </el-form>
      <div class="demo-drawer__footer">
        <el-button @click="cancelForm">取消</el-button>
        <el-button type="primary" :loading="loading" @click="onClick">
          {{ loading ? 'Submitting ...' : '提交' }}
        </el-button>
      </div>
    </div>
  </el-drawer>
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
.head {
  text-align: right;
}
.but {
  margin-bottom: 5px;
}
</style>