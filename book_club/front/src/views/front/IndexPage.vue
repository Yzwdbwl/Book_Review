<script setup>

import {Search} from "@element-plus/icons-vue";
import {onMounted, ref} from "vue";
import request from "@/utils/request";
import router from "@/router";

//书籍表管理
const bookFrontApi = {
  page(data) {
    return request.post('/book/pageFront', data)
  },
  getById(id) {
    return request.get('/book/getById/' + id)
  },
}


const searchValue=ref('')

const searchHandle = () => {
  initTableData()
}

const page = ref({
  pageSize: 8,
  pageNum: 1,
  search: '',
  total: 0
})

const tableData=ref([])

const currentChange = (number) => {
  page.value.pageNum = number
  bookFrontApi.page(page.value).then(resp => {
    tableData.value = resp.data.data.records
    page.value.total = resp.data.data.total
  })
}


const initTableData = () => {
  bookFrontApi.page(page.value)
      .then(resp => {
        tableData.value = resp.data.data.records
        page.value.total = resp.data.data.total
      })
}

const bookInfoHandle = (item) => {
  router.push({path: '/front/BookInfo',query:{id:item.id}})
}

onMounted(()=>{
  initTableData()
})

</script>

<template>
<div style="width: 60%;margin: 30px auto">
    <el-row >
      <el-col :span="5">
        <div style="font-size: 30px;font-weight: bold">书评俱乐部</div>
      </el-col>
      <el-col :span="12">
        <el-input
            size="large"
            v-model="page.search"
            placeholder="书名，作者"
            clearable
            @clear="initTableData"
        >
          <template #append>
            <el-button :icon="Search" @click="searchHandle" />
          </template>
        </el-input>
      </el-col>
    </el-row>

  <div style="margin-top: 30px"></div>
    <el-row >
      <el-col :span="6" v-for="item in tableData" style="margin-top: 50px">
        <el-card style="width: 190px">
            <img  :src="item.img" width="150" height="180">
          <div><el-link type="primary" @click="bookInfoHandle(item)">{{ item.name }}</el-link></div>
          <div><el-text truncated >{{item.author}}</el-text></div>
        </el-card>
      </el-col>
    </el-row>

  <div style="margin-top: 30px"></div>
  <!-- 分页 -->
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
</div>
</template>

<style scoped lang="scss">

</style>