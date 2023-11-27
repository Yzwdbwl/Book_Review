<script setup>
import {HomeFilled, Menu, Setting} from "@element-plus/icons-vue";
import {useUserStore} from "@/stores/userInfo";
import router from "@/router";
import {logout} from "@/api/api";
import {authShow} from "@/utils/auth";


const userStore = useUserStore()
const logoutHandle = () => {
  logout().then(()=>{
    userStore.removeUser()
    window.location="/"
  })
}
const frontHandle = () => {
  router.push({path: '/front'})
}
</script>
<template>
  <el-container>
    <el-aside width="250px">
      <div class="log">
        <span style="font-size: 30px;margin-left: 20px;line-height: 60px;color: white;font-weight: bold">书评俱乐部</span>
      </div>
      <el-menu
          class="my-menu el-menu-vertical"
          active-text-color="#ffffff"
          background-color="#242426"
          text-color="#ffffff"
          router
          :default-active="$route.path"
      >


        <el-sub-menu v-if="authShow(['ADMIN'])">
          <template #title>
            <el-icon><setting /></el-icon>
            <span>系统管理</span>
          </template>
          <el-menu-item  index="/end/sysUser" ><el-icon><Menu/></el-icon><span>用户管理</span></el-menu-item>
<!--          <el-menu-item index="/end/SysAuthority" ><el-icon><Menu/></el-icon><span>权限管理</span></el-menu-item>-->
<!--          <el-menu-item  index="/end/SysRole" ><el-icon><Menu/></el-icon><span>权限分配管理</span></el-menu-item>-->
        </el-sub-menu>

        <el-menu-item v-if="authShow(['ADMIN'])" index="/end/Book" ><el-icon><Menu/></el-icon><span>书籍管理</span></el-menu-item>
        <el-menu-item index="/end/Comment" ><el-icon><Menu/></el-icon><span>评论管理</span></el-menu-item>




      </el-menu>
    </el-aside>
    <el-container>
      <el-header>
        <el-row>
          <el-col :span="4">
            <div style="line-height: 60px;padding-left: 20px;color: white">{{
                userStore.user.authentication.name
              }}：<strong>{{ userStore.user.authentication.principal.role }}</strong>
            </div>
          </el-col>
          <el-col :offset="19" :span="1">
            <el-dropdown>
              <el-avatar :size="50"
                         src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
              <template #dropdown>
                <el-dropdown-menu>
                  <el-dropdown-item @click="logoutHandle">退出登录</el-dropdown-item>
                  <el-dropdown-item @click="frontHandle">前台</el-dropdown-item>
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
  </el-container>
</template>

<style scoped lang="scss">
.el-menu-vertical {
  //height: 100%;
}

//.log {
//  background-image: url("@/assets/23b82ca2532479a2c532738650b2386d.png");
//  background-repeat: no-repeat;
//  background-color: #ffffff;
//  width: 250px;
//  height: 118px;
//  background-size: 250px;
//}

.log {
  width: 250px;
  height: 60px;
  background-color: #3e4454;
}


.my-menu {
  border-right: none;
  height: 100vh;
  background-color: #242426;
}

.my-menu .el-menu-item:hover {
  //background-color: #4497ff;
  //color: #ed0707;
}

.el-menu-item.is-active{
  background-color: #4497ff;
}


.el-header {
  padding: 0;
  background-color: #3e4454;
}

.el-dropdown {
  line-height: 60px;
}
</style>