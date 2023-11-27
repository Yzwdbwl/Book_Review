<script setup>
import {onMounted, ref} from 'vue'
import {ElMessageBox} from "element-plus"
import {Delete, Edit, Message, Plus} from "@element-plus/icons-vue"
import request from "@/utils/request"

//书籍表管理
const bookApi = {
  page(data) {
    return request.post('/book/page', data)
  },
  listAll() {
    return request.get('/book/listAll')
  },
  getById(id) {
    return request.get('/book/getById/' + id)
  },
  add(data) {
    return request.post('/book/add', data)
  },
  deleteById(id) {
    return request.delete('/book/deleteById/' + id)
  },
  deleteByIds(ids) {
    return request.delete('/book/deleteByIds', {
      data: ids
    })
  },
  updateById(data) {
    return request.put('/book/updateById', data)
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
  bookApi.page(page.value)
      .then(resp => {
        tableData.value = resp.data.data.records
        page.value.total = resp.data.data.total
      })
}

const handleEndSuccess = (response) => {
  form.value.img = response[0].url
}



const clickButton = (type, row) => {
  dialog.value.optionValue = type
  if (type === 'add') {
    dialog.value.dialogFormVisible = true
    dialog.value.optionName = '新增'
    dialog.value.formDisabled = false
  } else if (type === 'update') {
    bookApi.getById(row.id).then((resp) => {
      dialog.value.dialogFormVisible = true
      dialog.value.optionName = '修改'
      dialog.value.formDisabled = false
      form.value = resp.data.data
    })
  } else if (type === 'detail') {
    bookApi.getById(row.id).then((resp) => {
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
      bookApi.deleteById(row.id).then(() => {
        initTableData()
      })
    })
  }

}

const currentChange = (number) => {
  page.value.pageNum = number
  bookApi.page(page.value).then(resp => {
    tableData.value = resp.data.data.records
    page.value.total = resp.data.data.total
  })
}

const formSubmit = () => {
  dialog.value.dialogFormVisible = false
  if (dialog.value.optionValue === 'add') {
    bookApi.add(form.value)
        .then(() => {
          initTableData()
        })
  } else if (dialog.value.optionValue === 'update') {
    bookApi.updateById(form.value)
        .then(() => {
          initTableData()
        })
  }
}

const dialogClose = () => {
  form.value = {}
}

const initTableData = () => {
  bookApi.page(page.value)
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
    bookApi.deleteByIds(delArray.value)
        .then(() => {
          initTableData()
        })
  })
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
          <el-button type="primary" @click="clickButton('add',null)">新增</el-button>
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
          <el-table-column prop="name" label="书籍名称" width="110"  show-overflow-tooltip/>
          <el-table-column prop="img" label="封面" width="180" show-overflow-tooltip>
            <template #default="scope">
              <img :src="scope.row.img" width="150" height="180">
            </template>
          </el-table-column>
          <el-table-column prop="author" label="作者"  show-overflow-tooltip/>
          <el-table-column prop="publishers" label="出版社"  show-overflow-tooltip/>
          <el-table-column prop="tributary" label="流派"  show-overflow-tooltip/>
          <el-table-column prop="subheading" label="副标题"  show-overflow-tooltip/>
          <el-table-column prop="translator" label="译者"  show-overflow-tooltip/>
          <el-table-column prop="originalTitle" label="原作名"  show-overflow-tooltip/>
          <el-table-column prop="year" label="出版年"  show-overflow-tooltip/>
          <el-table-column prop="pagination" label="页数"  show-overflow-tooltip/>
          <el-table-column prop="isbn" label="isbn"  show-overflow-tooltip/>
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

        <el-form-item label="书籍名称">
          <el-input v-model="form.name"/>
        </el-form-item>

        <el-form-item label="封面">
          <el-upload
              action="/api/file/upload"
              :data="{fileTypeEnum:'FILE'}"
              :show-file-list="false"
              :on-success="handleEndSuccess"
              name="files"
              list-type="picture-card"
          >
            <img v-if="form.img" :src="form.img" width="100"/>
            <el-icon v-else class="avatar-uploader-icon">
              <Plus/>
            </el-icon>
          </el-upload>
        </el-form-item>

        <el-form-item label="作者">
          <el-input v-model="form.author"/>
        </el-form-item>

        <el-form-item label="出版社">
          <el-input v-model="form.publishers"/>
        </el-form-item>

        <el-form-item label="流派">
          <el-input v-model="form.tributary"/>
        </el-form-item>

        <el-form-item label="副标题">
          <el-input v-model="form.subheading"/>
        </el-form-item>

        <el-form-item label="译者">
          <el-input v-model="form.translator"/>
        </el-form-item>

        <el-form-item label="原作名">
          <el-input type="textarea" :autosize="{minRows:3}" v-model="form.originalTitle"/>
        </el-form-item>

        <el-form-item label="出版年">
          <el-input v-model="form.year"/>
        </el-form-item>

        <el-form-item label="页数">
          <el-input v-model="form.pagination"/>
        </el-form-item>

        <el-form-item label="isbn">
          <el-input v-model="form.isbn"/>
        </el-form-item>
        <el-form-item label="内容简介">
          <el-input type="textarea" :autosize="{minRows:5}" v-model="form.description"/>
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