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
        <div class="sousuoqvyu">
          <el-form
              :model="lunwen_form"
              style="max-width: 460px;display: flex;justify-content: space-between;"
          >
            <el-form-item label="论文题目">
              <el-input v-model="lunwen_form.title"/>
            </el-form-item>
            <el-button style="margin-left: 15px" type="primary" @click="search">搜索</el-button>
            <!-- 添加论文按钮-->
            <el-button style="margin-left: 15px" type="primary" @click="addDocumentBtn">添加论文</el-button>
          </el-form>
        </div>

        <!--  表格 -->
        <div style="margin-left: 15%">
          <el-table :data="tableData" style="width: 70%">
            <el-table-column prop="title" label="论文题目" width="240"/>
            <el-table-column prop="digest" label="摘要" width="240"/>
            <el-table-column prop="author" label="作者" width="240"/>
            <el-table-column prop="gmtCreate" label="发表时间" width="240"/>
            <el-table-column label="操作" width="300">
              <template #default="scope">
                <el-button type="primary" size="mini" @click="handleEdit2(scope.row)">编辑</el-button>
                <el-button type="danger" size="mini" @click="handleDelete2(scope.row)">删除</el-button>
                <el-button type="success" size="mini" @click="handleInfo(scope.row)">详情</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>


        <el-pagination
            class="pagination"
            :page-size="lunwen_form.pageNum"
            :page-sizes="[20,40,60]"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
        />

        <el-dialog v-model="dialogTableVisible2" title="编辑论文">
          <el-form :model="document_form">
            <el-form-item label="论文题目" :label-width="formLabelWidth">
              <el-input v-model="document_form.title"/>
            </el-form-item>
            <el-form-item label="作者" :label-width="formLabelWidth">
              <el-input v-model="document_form.author"/>
            </el-form-item>
            <el-form-item label="摘要" :label-width="formLabelWidth">
              <el-input v-model="document_form.digest"/>
            </el-form-item>

            <el-form-item label="论文内容" :label-width="formLabelWidth">
              <el-input
                  v-model="document_form.content"
                  :autosize="{ minRows: 5, maxRows: 100 }"
                  type="textarea"
                  placeholder="请输入论文内容"
              />
            </el-form-item>
          </el-form>
          <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogTableVisible2 = false">取消</el-button>
        <el-button type="primary" @click="updateDocument">
          确定
        </el-button>
      </span>
          </template>
        </el-dialog>


        <el-dialog v-model="dialogTableVisible2add" title="添加论文">
          <el-form :model="document_form">
            <el-form-item label="论文题目" :label-width="formLabelWidth">
              <el-input v-model="document_form.title"/>
            </el-form-item>
            <el-form-item label="作者" :label-width="formLabelWidth">
              <el-input v-model="document_form.author"/>
            </el-form-item>
            <el-form-item label="摘要" :label-width="formLabelWidth">
              <el-input v-model="document_form.digest"/>
            </el-form-item>

            <el-form-item label="论文内容" :label-width="formLabelWidth">
              <el-input
                  v-model="document_form.content"
                  :autosize="{ minRows: 5, maxRows: 100 }"
                  type="textarea"
                  placeholder="请输入论文内容"
              />
            </el-form-item>
          </el-form>
          <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogTableVisible2add = false">取消</el-button>
        <el-button type="primary" @click="addDocument">
          确定
        </el-button>
      </span>
          </template>
        </el-dialog>



        <el-dialog v-model="dialogTableVisible3add" title="论文内容">
          <el-form :model="document_form">

            <el-form-item label="论文内容" :label-width="formLabelWidth">
              <el-input
                  v-model="document_form.content"
                  :autosize="{ minRows: 5, maxRows: 100 }"
                  type="textarea"
                  placeholder="请输入论文内容"
              />
            </el-form-item>
          </el-form>
          <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogTableVisible3add = false">取消</el-button>

      </span>
          </template>
        </el-dialog>
      </el-main>
    </el-container>
  </el-container>





</template>

<script setup>
import {reactive, ref} from 'vue'
import {Check} from '@element-plus/icons-vue'
import axios from "axios";


// 定义一个响应式的对象
const dialogTableVisible2 = ref(false)
const dialogTableVisible2add = ref(false)
const dialogTableVisible3add = ref(false)
const lunwen_form = reactive({
  name: '',
  page: 1,
  limit: 10
})


const input = (emit) => {
  richValue.value = emit;
}
const formLabelWidth = '140px'
const document_form = reactive({
  id: '',
  author: '',
  title: '',
  digest: '',
  gmt_create: '',
  content: '',
})

const tableData = ref([])

function getDocumentList() {
  axios({
    method: 'get',
    url: 'http://localhost:8888/document/list?name=' + lunwen_form.name + '&page=' + lunwen_form.page + '&limit=' + lunwen_form.limit,

  }).then(res => {
    console.log('res.data')
    console.log(res.data)
    tableData.value = res.data.data.data
    total.value = res.data.data.total
    console.log(res)
  })
}

const total = ref(0)

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

function search() {
  getDocumentList()
}


const handleSizeChange = (val) => {
  lunwen_form.limit = val
  getDocumentList()
}
const handleCurrentChange = (val) => {
  lunwen_form.page = val
  getDocumentList()
}

function handleEdit2(row) {
  console.log(row)
  dialogTableVisible2.value = true
  document_form.author = row.author
  document_form.title = row.title
  document_form.digest = row.digest
  document_form.content = row.content
  document_form.gmtCreate = row.gmtCreate
  document_form.id = row.id
}

//清空表单数据的方法
function clearFormDocument() {
  document_form.author = ''
  document_form.title = ''
  document_form.digest = ''
  document_form.content = ''
  document_form.gmtCreate = ''
  document_form.id = ''
}

function updateDocument() {
//  发送请求更新数据
  axios({
    method: 'post',
    url: 'http://localhost:8888/document/update',
    data: document_form
  }).then(res => {
    console.log(res.data)
    tableData.value = res.data.data.data
    total.value = res.data.data.total
    console.log(res)
  })
//  关闭弹窗
  dialogTableVisible2.value = false

  getDocumentList()
}

import {ElMessage, ElTable, ElMessageBox} from 'element-plus'

function handleDelete2(row) {
  console.log(row)
  //提示用户是否删除
  ElMessageBox.confirm('此操作将永久删除该文件, 是否继续?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    //发送请求删除数据
    axios({
      method: 'get',
      url: 'http://localhost:8888/document/delete?id=' + row.id,

    }).then(res => {
      console.log(res.data)
      tableData.value = res.data.data.data
      total.value = res.data.data.total
      console.log(res)
    })
    ElMessage({
      type: 'success',
      message: '删除成功!'
    });
  }).catch(() => {
    ElMessage({
      type: 'info',
      message: '已取消删除'
    });
  });

  getDocumentList()

}

function addDocumentBtn() {
  dialogTableVisible2add.value = true
  clearFormDocument()
}

function addDocument() {
  axios({
    method: 'post',
    url: 'http://localhost:8888/document/add',
    data: document_form
  }).then(res => {
    console.log(res.data)
    if (res.data.code == 0) {
      ElMessage({
        type: 'success',
        message: '添加成功!'
      });
    } else {
      ElMessage({
        type: 'error',
        message: '添加失败!'
      });
    }
    console.log(res)
  })
  dialogTableVisible2add.value = false
  getDocumentList()
}

function handleInfo(row) {
  dialogTableVisible3add.value = true
  console.log(row)
  document_form.content = row.content
}

getDocumentList()
</script>


<style lang="less" scoped>
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
