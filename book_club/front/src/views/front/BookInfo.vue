<script setup>

import {onMounted, ref} from "vue";
import request from "@/utils/request";
import {useRouter} from "vue-router";

//书籍表管理
const bookInfoApi = {
  getById(id) {
    return request.get('/book/getById/' + id)
  },
  addComment(data) {
    return request.post('/comment/add', data)
  },
  listCommentAll(bookId) {
    return request.get(`/comment/listAllByBookId/${bookId}`)
  },
}

const bookId = useRouter().currentRoute.value.query.id

const bookInfo = ref({})
const initBookInfo = () => {
  bookInfoApi.getById(bookId)
      .then((resp) => {
        bookInfo.value = resp.data.data
      })
}

const commentData=ref([])
const scoreInfo=ref({
  score:0,
  times:0
})
const initCommentInfo=()=>{
  bookInfoApi.listCommentAll(bookId)
      .then((resp)=>{
        commentData.value=resp.data.data.data
        scoreInfo.value.score=resp.data.data.score
        scoreInfo.value.times=resp.data.data.times
      })
}

const score = ref(2.9)
const commentForm = ref({
  bookId: bookId,
  score: null,
  description: null,
})

const addCommentInfo = () => {
  bookInfoApi.addComment(commentForm.value)
      .then(()=>{
        commentForm.value.description=null
        initCommentInfo()
      })
}

onMounted(() => {
  initCommentInfo()
  initBookInfo()
})


</script>

<template>
  <div style="width: 60%;margin: 30px auto">
    <el-row>
      <el-col>
        <h1>{{ bookInfo.name }}</h1>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="4">
        <img :src="bookInfo.img" width="180" height="260">
      </el-col>
      <el-col :span="10">
        <div class="textdiv" v-show="bookInfo.author">
          <el-text>作者：{{ bookInfo.author }}</el-text>
        </div>
        <div class="textdiv" v-show="bookInfo.publishers">
          <el-text>出版社：{{ bookInfo.publishers }}</el-text>
        </div>
        <div class="textdiv" v-show="bookInfo.subheading">
          <el-text>副标题：{{ bookInfo.subheading }}</el-text>
        </div>
        <div class="textdiv" v-show="bookInfo.tributary">
          <el-text>流派：{{ bookInfo.tributary }}</el-text>
        </div>
        <div class="textdiv" v-show="bookInfo.originalTitle">
          <el-text>原作名：{{ bookInfo.originalTitle }}</el-text>
        </div>
        <div class="textdiv" v-show="bookInfo.translator">
          <el-text>译者：{{ bookInfo.translator }}</el-text>
        </div>
        <div class="textdiv" v-show="bookInfo.year">
          <el-text>出版年：{{ bookInfo.year }}</el-text>
        </div>
        <div class="textdiv" v-show="bookInfo.pagination">
          <el-text>页数：{{ bookInfo.pagination }}</el-text>
        </div>
        <div class="textdiv" v-show="bookInfo.isbn">
          <el-text>ISBN：{{ bookInfo.isbn }}</el-text>
        </div>
      </el-col>

      <el-col :span="10">
        <div style="min-height: 60px;border-left:1px solid #dbd3d3;padding-left: 10px">
          <div>
            <el-text>评分</el-text>
          </div>
          <div>
            <el-row>
              <el-col :span="4">
                <h2>{{scoreInfo.score.toFixed(1)}}</h2>
              </el-col>
              <el-col :span="10">
                <el-rate disabled v-model="score" allow-half :max="5"/>
                <br/>
                {{scoreInfo.times}}人评价
              </el-col>
            </el-row>
          </div>
        </div>
      </el-col>
    </el-row>

    <div style="margin-top: 30px"></div>
    <el-row>
      <el-col>
        <div style="color: #2e508f">
          内容简介 · · · · · ·
        </div>
        <p style="width: 70%;font-size: 13px;color: #111;line-height: 1.62;text-indent: 2em">
          {{ bookInfo.description }}
        </p>
      </el-col>
    </el-row>

    <div style="margin-top: 30px"></div>
    <el-row style="min-height: 100px;max-height:500px;overflow: auto" v-if="commentData.length>0">
      <el-col>
        <div style="color: #2e508f">
          大众评论 · · · · · ·
        </div>
        <div style="width: 70%;" v-for="item in commentData">
          <div style="margin-top: 10px;">
            <el-text>{{ item.createBy }} {{item.createTime}}
              <el-rate disabled v-model="item.score" allow-half :max="5"/>
            </el-text>
          </div>
          <el-text>
            {{item.description}}
          </el-text>
        </div>
      </el-col>
    </el-row>

    <div style="margin-top: 30px"></div>
    <el-row>
      <el-col>
        <div style="color: #2e508f">
          发表评论
        </div>
        <div style="width: 70%;">
          <el-rate v-model="commentForm.score" allow-half :max="5"/>
          <el-input type="textarea" :autosize="{minRows:8}" v-model="commentForm.description"></el-input>
          <div style="margin-top: 10px"></div>
          <el-button type="primary" @click="addCommentInfo">发表</el-button>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<style scoped lang="scss">
.textdiv {
  margin-top: 5px;
}
</style>