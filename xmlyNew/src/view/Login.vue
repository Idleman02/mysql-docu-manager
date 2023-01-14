<template>

  <!--  登录页面 -->
  <div class="login_container">
    <div class="login_box">
      <!-- 头像区域 -->
      <div class="avatar_box">
        <img src="../assets/logo2.png" alt="">
        <!-- 删去的部分 -->
      </div>
      <!-- 登陆表单区域 -->
      <div class="login-form-title">图书论文管理系统</div>
      <el-form ref="loginFormRef" :model="loginForm" label-width="0px" class="login_form" :rules="rules">
        <!-- 用户名 -->
        <el-form-item prop="username">
          <el-input v-model="loginForm.username"  :prefix-icon="User" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <!-- 密码 -->
        <el-form-item prop="password">
          <el-input  v-model="loginForm.password" :prefix-icon="Lock" placeholder="请输入密码" type="password"></el-input>
        </el-form-item>
        <!-- 按钮区域 -->
        <el-form-item  class="login-buttons">
          <div style="text-align: right; margin: 0">
            <el-button type="primary" @click="login">登录</el-button>
            <el-button type="info" @click="resetLoginForm">重置</el-button>
          </div>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script setup>

import {reactive, ref} from 'vue'
import {Check} from '@element-plus/icons-vue'
import axios from "axios";
import {useRouter} from "vue-router";
import { Edit, Share, Delete, Search, Upload ,User ,Lock} from '@element-plus/icons-vue'

const loginForm = reactive({
  username: '',
  password: ''
})

const rules = reactive({
  username: [
    {required: true, message: '请输入用户名', trigger: 'blur'},
    {min:3 ,max: 10 ,message:"长度在 3 到 10 个字符", trigger: 'blur'}
  ],
  password: [
    {required: true, message: '请输入密码', trigger: 'blur'},
    {min:3 ,max: 15 ,message:"长度在 3 到 15 个字符", trigger: 'blur'}
  ]
})

const router = useRouter()

const loginFormRef = ref(null);

// 点击重置按钮，重置登录表单
const resetLoginForm = () => {
  loginFormRef.value.resetFields();
};

//登录方法
function login() {
  axios({
    method: 'post',
    url: 'http://localhost:8888/user/login',
    data: loginForm
  }).then(res => {
    console.log(res)
    console.log("res")
    console.log('res.data.code')
    console.log(res.data.code)
    if (res.data.code === 0) {
      router.push({path: '/index'})
    } else {
      alert(res.data.msg)
    }
  })
}

</script>

<style lang="less" scoped>

.login_container {
  background-color: #2b4b6b;
  width: 100%;
  height: 100%;
}

.login_box{
  width:450px;
  height:300px;
  background-color: #fff;
  border-radius: 3px;
  position:absolute;
  left:50%;
  top:50%;
  transform:translate(-50%,-50%);
}

.avatar_box{
  height:130px;
  width:130px;
  border: 1px solid #eee;
  border-radius: 50%;
  padding: 10px;
  box-shadow: 0 0 10px #ddd;
  position:absolute;
  left:50%;
  transform:translate(-50%,-50%);
  background-color: #fff;
  img {
    width: 100%;
    height: 100%;
    border-radius: 50%;
    background-color: #eee;
  }
}

.login-buttons{
  display:flex;
  justify-content: flex-end;
}

.login_form{
  position: absolute;
  bottom: 0;
  width: 100%;
  padding: 0 20px;
  box-sizing: border-box;
}

.login-form {
  background-color: #fff;
  width: 400px;
  height: 300px;
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 20px;
}

.login-form-title {
  font-size: 20px;
  display: flex;
  flex-direction: column;
  margin: 0 !important;
  position: absolute;
  top: 30%;
  left: 50%;
  transform: translate(-50%, -50%);
}

.login-btn {
  /*  居中*/
  display: block;
  margin: 0 auto;
}

</style>
