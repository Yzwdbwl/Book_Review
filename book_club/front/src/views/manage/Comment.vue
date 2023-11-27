<script setup>
import {onMounted, ref} from 'vue'
import {ElMessageBox} from "element-plus"
import {Delete, Edit, Message} from "@element-plus/icons-vue"
import request from "@/utils/request"
import router from "@/router";

//评论表管理
const commentApi = {
  page(data) {
    return request.post('/comment/page', data)
  },
  listAll() {
    return request.get('/comment/listAll')
  },
  getById(id) {
    return request.get('/comment/getById/' + id)
  },
  add(data) {
    return request.post('/comment/add', data)
  },
  deleteById(id) {
    return request.delete('/comment/deleteById/' + id)
  },
  deleteByIds(ids) {
    return request.delete('/comment/deleteByIds', {
      data: ids
    })
  },
  updateById(data) {
    return request.put('/comment/updateById', data)
  }
}


const page = ref({
  pageSize: 10,
  pageNum: 1,
  search: '',
  total: 0
})

const tableData = ref([])
const dialog = ref({
  dialogFormVisible: false,
  optionName: '新增',
  formDisabled: true,
  optionValue: null
})

const form = ref({})

const search = () => {
  commentApi.page(page.value)
      .then(resp => {
        tableData.value = resp.data.data.records
        page.value.total = resp.data.data.total
      })
}

const handleAvatarSuccess = (response) => {
  form.value.img = response[0].url
}

const clickButton = (type, row) => {
  dialog.value.optionValue = type
  if (type === 'add') {
    dialog.value.dialogFormVisible = true
    dialog.value.optionName = '新增'
    dialog.value.formDisabled = false
  } else if (type === 'update') {
    commentApi.getById(row.id).then((resp) => {
      dialog.value.dialogFormVisible = true
      dialog.value.optionName = '修改'
      dialog.value.formDisabled = false
      form.value = resp.data.data
    })
  } else if (type === 'detail') {
    commentApi.getById(row.id).then((resp) => {
      dialog.value.dialogFormVisible = true
      dialog.value.optionName = '详情'
      dialog.value.formDisabled = true
      form.value = resp.data.data
    })
  } else if (type === 'delete') {
    ElMessageBox.confirm(
        '确定删除这条信息？',
        '警告', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
        }
    ).then(() => {
      commentApi.deleteById(row.id).then(() => {
        initTableData()
      })
    })
  }

}

const currentChange = (number) => {
  page.value.pageNum = number
  commentApi.page(page.value).then(resp => {
    tableData.value = resp.data.data.records
    page.value.total = resp.data.data.total
  })
}

const formSubmit = () => {
  dialog.value.dialogFormVisible = false
  if (dialog.value.optionValue === 'add') {
    commentApi.add(form.value)
        .then(() => {
          initTableData()
        })
  } else if (dialog.value.optionValue === 'update') {
    commentApi.updateById(form.value)
        .then(() => {
          initTableData()
        })
  }
}

const dialogClose = () => {
  form.value = {}
}

const initTableData = () => {
  commentApi.page(page.value)
      .then(resp => {
        tableData.value = resp.data.data.records
        page.value.total = resp.data.data.total
      })
}

const delArray = ref([])
const handleSelectionChange = (val) => {
  delArray.value = val.map(item => item.id)
}
const delBach = () => {
  ElMessageBox.confirm(
      '确定批量删除这些信息？',
      '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      }
  ).then(() => {
    commentApi.deleteByIds(delArray.value)
        .then(() => {
          initTableData()
        })
  })
}

const bookInfoHandle = (item) => {
  window.open('/front/BookInfo?id='+item.bookId, '_blank');
}

onMounted(() => {
  initTableData()
})


</script>

<template>
  <div class="p-div">
    <div style="background-color: white;height: 60px">
      <el-row style="line-height: 40px;padding: 10px">
        <el-col :span="3">
          <el-button type="danger" :disabled="delArray.length<1" @click="delBach">批量删除</el-button>
        </el-col>
        <el-col :span="5" :offset="1">
          <el-input v-model="page.search" placeholder="请输入搜索内容" clearable @clear="initTableData"/>
        </el-col>
        <el-col :span="1" :offset="1">
          <el-button type="success" @click="search">搜索</el-button>
        </el-col>
      </el-row>
    </div>
    <el-row>
      <el-col>
        <el-table :data="tableData" style="width: 100%" height="680px"
                  :header-cell-style="{padding:'15px',fontWeight:'bold'}"
                  :cell-style="{padding:'15px'}"
                  @selection-change="handleSelectionChange"
        >
          <el-table-column type="selection"/>
          <el-table-column prop="bookId" label="书籍ID">
            <template #default="scope">
              <el-link type="primary" @click="bookInfoHandle(scope.row)">
                {{scope.row.id}}
              </el-link>
            </template>
          </el-table-column>
          <el-table-column prop="score" label="评分"/>
          <el-table-column prop="description" label="评论内容"/>
          <el-table-column label="操作" width="300px">
            <template #default="scope">
              <el-button type="primary" :icon="Edit" circle @click="clickButton('update', scope.row)"/>
              <el-button type="info" :icon="Message" circle @click="clickButton('detail', scope.row)"/>
              <el-button type="danger" :icon="Delete" circle @click="clickButton('delete',scope.row)"/>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>


    <el-drawer v-model="dialog.dialogFormVisible" :title="dialog.optionName" @closed="dialogClose">
      <el-form :model="form" label-position="right" label-width="150px" :disabled="dialog.formDisabled">


        <el-form-item label="评分">
          <el-input v-model="form.score"/>
        </el-form-item>

        <el-form-item label="评论内容">
          <el-input type="textarea" :autosize="{minRows:3}" v-model="form.description"/>
        </el-form-item>


      </el-form>
      <template #footer>
<span class="dialog-footer" v-if="!dialog.formDisabled">
<el-button @click="dialog.dialogFormVisible = false">取消</el-button>
<el-button type="success" @click="formSubmit">确认</el-button>
</span>
      </template>
    </el-drawer>


    <!-- 分页 -->
    <el-affix position="bottom" :offset="20">
      <div class="paginationClass">
        <el-pagination
            small
            background
            :total="page.total"
            :page-size="page.pageSize"
            @current-change="currentChange"
            layout="total,prev, pager, next, jumper"
        />
      </div>
    </el-affix>
  </div>


</template>


<style lang="scss" scoped>
.el-row:nth-child(1) {
  margin-top: 0;
}

.el-row {
  margin-top: 30px;
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}

.paginationClass {
  position: fixed;
  bottom: 0;
  height: 60px;
  width: 100%;
  text-align: center;
}


.demo-image__error .image-slot {
  font-size: 30px;
}

.demo-image__error .image-slot .el-icon {
  font-size: 30px;
}

.demo-image__error .el-image {
  width: 100%;
  height: 200px;
}

</style>