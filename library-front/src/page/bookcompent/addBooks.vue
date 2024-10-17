<template>
    <el-button plain @click="dialogFormVisible = true">
    新增图书
    </el-button>
  <!-- 简单的表单验证 -->
    <el-dialog v-model="dialogFormVisible" title="图书基本信息" width="500">
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
        <el-form-item label="封面" :label-width="formLabelWidth">
          <el-input v-model="form.cover" autocomplete="off" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取消</el-button>
          <el-button @click="onClick">
            确认
          </el-button>
        </div>
      </template>
    </el-dialog>
  </template>
  
  <script lang="ts" setup>
  import { reactive, ref } from 'vue'
  import service from '@/utils/request';
  const dialogFormVisible = ref(false)
  const formLabelWidth = '140px'
  const form = reactive({
  bookid:'',
  title:'',
  author:'',
  cover:''
})
const onClick = () => {
    if(form.author=='' || form.bookid=='' || form.title==''){
        open("请填写完整信息")
    }
    else {
        service.post('http://localhost:5174/api/book/add',form)
        .then(response=>{
           console.log(response.data.data.message)
        })
        .catch(error=>{
            alert(error)
        })
    }

  dialogFormVisible.value = false
}
// 消息提示框
import { ElMessage } from 'element-plus'
const open = (mes:string) => {
  ElMessage(mes)
}

  </script>
  