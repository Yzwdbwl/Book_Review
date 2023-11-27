<script setup>

import {ref} from "vue"
import {login, logout, register} from "@/api/api"
import router from "@/router"
import {useUserStore} from "@/stores/userInfo"
const  userStore=useUserStore()
const loginDTO = ref({
  username: '',
  password: '',
  nickname:'',
  role:''
})



const registerHandle = () => {

  register(loginDTO.value)
      .then(()=>{
        router.push({path: '/login'})
      })


}


</script>

<template>

  <div class="login-container">
    <div class="login-box">
      <el-row justify="center">
        <h1>
          注册
        </h1>
      </el-row>
      <el-row justify="center">
        <el-col :span="22">
          <el-form size="large" label-width="100">
            <el-form-item label="昵称：">
              <el-input v-model="loginDTO.nickname"></el-input>
            </el-form-item>
            <el-form-item label="账号：">
              <el-input v-model="loginDTO.username"></el-input>
            </el-form-item>
            <el-form-item label="密码：">
              <el-input type="password" show-password v-model="loginDTO.password"></el-input>
            </el-form-item>
            <el-form-item label="角色：">
              <el-select v-model="loginDTO.role">
                <el-option  :label="'ADMIN'" :value="'ADMIN'"></el-option>
                <el-option  :label="'USER'" :value="'USER'"></el-option>
              </el-select>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
      <el-row :justify="'center'">
        <el-col :span="7">
          <el-button type="success"   @click="registerHandle">
            确认注册
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
  height: 350px;
}

</style>