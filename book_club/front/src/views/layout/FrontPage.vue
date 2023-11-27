<script setup>

import router from "@/router";
import {useUserStore} from "@/stores/userInfo";
import {onMounted, ref, inject, provide} from 'vue'
import request from "@/utils/request";

const userStore = useUserStore()
const endPage = () => {
  router.push({path: '/end'})
}

const logout = () => {
  router.push({path: '/login'})
  userStore.removeUser()
}




</script>
<template>
  <el-container>
    <el-header>
      <el-row :justify="'center'">
        <el-col :span="23">
          <el-menu
              class="my-menu"
              mode="horizontal"
              :ellipsis="false"
              router
              :default-active="$route.path"
          >
            <el-menu-item index="/front/IndexPage">首页</el-menu-item>
            <el-menu-item index="/front/About">关于我们</el-menu-item>
          </el-menu>
        </el-col>
        <el-col :span="1" class="cls">
          <el-dropdown>
            <el-avatar :size="50"
                       src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item @click="logout">退出登录</el-dropdown-item>
                <el-dropdown-item @click="endPage">后台</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </el-col>
      </el-row>
    </el-header>
    <el-main>
      <router-view></router-view>
    </el-main>


  </el-container>
</template>

<style scoped lang="scss">
.el-menu-vertical {
  //height: 100%;
}

.cls {
  background-color: white;
  border-bottom: #dcdfe6 solid 1px;
}

.log {
  background: #ffffff url("@/assets/23b82ca2532479a2c532738650b2386d.png") no-repeat 20px 0;
  height: 58px;
  background-size: 150px;

  border-bottom: #dcdfe6 solid 1px;
}

.flex-grow {
  flex-grow: 1;
}

.my-menu {
  border-right: none;
}

.el-main{
  padding: 0;
}

.el-header {
  padding: 0;
  background-color: rgba(255, 255, 255, 0.35);
}

.el-dropdown {
  line-height: 60px;
}
</style>