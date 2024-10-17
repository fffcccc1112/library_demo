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
</template>

<script lang="ts" setup>


</script>

<style scoped>

</style>