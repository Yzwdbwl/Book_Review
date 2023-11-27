<script setup>

import {ref} from "vue"
import {login, logout} from "@/api/api"
import router from "@/router"
import {useUserStore} from "@/stores/userInfo"
const  userStore=useUserStore()
const loginDTO = ref({
  username: '',
  password: ''
})


const rep = ref(null)

const loginHandle = () => {
  login(loginDTO.value)
      .then((resp)=>{
        if (resp.data.code===200){
          userStore.setUser(resp.data.data)
          router.push({path: '/end'})
        }
      })
}

const registerHandle = () => {
  router.push({path: '/Register'})
}


</script>

<template>

  <div class="login-container">
    <div class="login-box">
      <el-row justify="center">
        <h1>
          登录
        </h1>
      </el-row>
      <el-row justify="center">
        <el-col :span="22">
          <el-form size="large" label-width="100">
            <el-form-item label="账号：">
              <el-input v-model="loginDTO.username"></el-input>
            </el-form-item>
            <el-form-item label="密码：">
              <el-input type="password" show-password v-model="loginDTO.password"></el-input>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
      <el-row :justify="'center'">
        <el-col :span="7">
          <el-button type="primary" @click="loginHandle">
            登录
          </el-button>
          <el-button type="success"   @click="registerHandle">
            注册
          </el-button>
        </el-col>



      </el-row>
    </div>
  </div>


</template>

<style scoped lang="scss">





.login-container {
  height: 100vh; /* 100% of the viewport height */
  display: flex;
  align-items: center;
  justify-content: center;
  background: url('@/assets/pexels-photo-243975.webp') center center / cover no-repeat; /* Replace with your image path */
}

.login-box {
  background-color: rgba(255, 255, 255, 0.8); /* Semi-transparent white background */
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
  width: 500px;
  height: 300px;
}

</style>