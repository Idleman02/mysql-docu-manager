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
          <el-sub-menu index="1" default-openeds="openeds">
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
      <el-main></el-main>
    </el-container>
  </el-container>


<!--    <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">-->
<!--      <el-tab-pane label="图书管理" name="first" >-->
<!--&lt;!&ndash;        使用book 组件&ndash;&gt;-->
<!--        <book/>-->
<!--      </el-tab-pane>-->
<!--      <el-tab-pane label="论文管理" name="second" ></el-tab-pane>-->
<!--      <document/>-->
<!--      <el-tab-pane label="统计管理" name="third" ></el-tab-pane>-->
<!--      &lt;!&ndash;    <el-tab-pane label="Task" name="fourth">Task</el-tab-pane>&ndash;&gt;-->
<!--    <statistics/>-->
<!--    </el-tabs>-->
</template>
<script lang="ts" setup>

import type {TabsPaneContext} from 'element-plus'
import {reactive, ref} from 'vue'

import axios from "axios";
// 导入book
import book from "../book/list.vue";
import document from "../document/list.vue";
import statistics from "../statistics/list.vue";
const activeName = ref('first')

const handleClick = (tab: TabsPaneContext, event: Event) => {
  console.log(tab, event)
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

.demo-tabs > .el-tabs__content {
  padding: 32px;
  color: #6b778c;
  font-size: 32px;
  font-weight: 600;
}

.sousuoqvyu {
  display: flex;

  align-items: center;
  margin-top: 20px;
  margin-left: 550px;
}

.pagination {
  margin-top: 20px;
  margin-left: 550px;
}
</style>
