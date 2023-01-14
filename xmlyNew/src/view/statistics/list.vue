<template>
  <el-container class="home-container">
    <!-- 头部区域 -->
    <el-header>
      <div>
        <img src="/src/assets/logo3.png" alt="">
        <span>图书论文管理系统</span>
      </div>
      <el-button type="primary" @click="logout">退出登录</el-button>
    </el-header>
    <!-- 页面主体区域 -->
    <el-container>
      <!-- 侧边栏区域 -->
      <el-aside width="200px">
        <!-- 侧边栏菜单区域 -->
        <el-menu
            active-text-color="#ffd04b"
            background-color="#333744"
            default-active="2"
            text-color="#fff"
        >
          <!-- 一级菜单 -->
          <el-sub-menu index="1">
            <template #title>
              <!-- 图标 -->
              <el-icon><Menu /></el-icon>
              <!-- 文本 -->
              <span>系统导航</span>
            </template>
            <!-- 二级菜单 -->
            <el-menu-item index="1-4-1" @click="bookManager">
              <template #title>
                <!-- 图标 -->
                <el-icon><Notebook /></el-icon>
                <!-- 文本 -->
                <span>图书管理</span>
              </template>
            </el-menu-item>
            <el-menu-item index="1-4-1" @click="documentManager">
              <template #title>
                <!-- 图标 -->
                <el-icon><Document /></el-icon>
                <!-- 文本 -->
                <span>论文管理</span>
              </template>
            </el-menu-item>
            <el-menu-item index="1-4-1" @click="statisticManager">
              <template #title>
                <!-- 图标 -->
                <el-icon><List /></el-icon>
                <!-- 文本 -->
                <span>数据统计</span>
              </template>
            </el-menu-item>
          </el-sub-menu>
        </el-menu>
      </el-aside>
      <!-- 右侧内容主体区域 -->
      <el-main>
        当前用户共上传了 <span style="color: red;font-size: 15px">{{ documentNum }}</span> 篇论文
        当前用户共上传了 <span style="color:red;font-size: 15px"> {{ bookNum }}</span> 图书
        <div style="display: flex">
          <!--      上传的图书列表-->
          <el-table :data="bookList2" style="width: 50%">
            <el-table-column prop="bookName" label="图书名字" width="500"/>
            <el-table-column prop="author" label="图书作者" width="500"/>
            <!--      <el-table-column prop="price" label="价格" width="220"/>-->
            <!--      <el-table-column prop="press" label="出版社" width="220"/>-->
            <!--      <el-table-column prop="gmtCreate" label="上架日期" width="220"/>-->
          </el-table>

          <!--      上传的论文列表-->
          <el-table :data="documentList2" style="width: 50%">
            <el-table-column prop="title" label="论文题目" width="500"/>
            <el-table-column prop="digest" label="摘要" width="500"/>
            <el-table-column prop="author" label="作者" width="500"/>
            <!--      <el-table-column prop="gmtCreate" label="发表时间" width="240"/>-->
          </el-table>
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup>
import {reactive, ref} from 'vue'
import {Check} from '@element-plus/icons-vue'
import axios from "axios";


const documentNum = ref(0)
const bookNum = ref(0)
const bookList2 = ref([])
const documentList2 = ref([])

function getBookNum() {
  axios({
    method: 'get',
    url: 'http://localhost:8888/book/getBookByUserId',

  }).then(res => {
    console.log(res.data)
    bookList2.value = res.data.data.data
    bookNum.value = res.data.data.total
    console.log(res)
  })
}

function getDocumentNum() {
  axios({
    method: 'get',
    url: 'http://localhost:8888/document/getDocumentByUserId',

  }).then(res => {
    console.log(res.data)
    documentList2.value = res.data.data.data
    documentNum.value = res.data.data.total
    console.log(res)
  })
}

function logout() {
  window.location.href = "/";
}
function bookManager() {
  window.location.href = "/book";
}
function documentManager() {
  window.location.href = "/document";
}
function statisticManager() {
  window.location.href = "/statistics";
}
getDocumentNum()
getBookNum()

</script>


<style lang="less" scoped>
.home-container{
  height: 100%;
}

.el-header{
  background-color: #373d41;
  display: flex;
  justify-content: space-between;
  padding-left: 0;
  align-items: center;
  color: #fff;
  font-size: 20px;
  > div{
    display: flex;
    align-items: center;
    span{
      margin-left: 15px;
    }
  }
}

.el-aside{
  background-color: #333744;
}

.el-main{
  background-color: #EAEDF1;
}
</style>
