<template>
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
         @click="borrowbook(userstore.user,scope.row.bookid)">
         借阅
        </el-button>
        <el-button link type="primary" size="small" @click="backbook(scope.row.bookid)">
          还书
        </el-button>
      </template>
    </el-table-column>
  </el-table>
</template>
<script>
    
</script>