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
              :model="book_list"
              style="max-width: 460px;display: flex;justify-content: space-between;"
          >
            <el-form-item label="图书名字">
              <el-input v-model="book_list.name"/>
            </el-form-item>
            <el-button style="margin-left: 15px" type="primary" @click="search">搜索</el-button>
            <!-- 添加图书按钮-->
            <el-button style="margin-left: 15px" type="primary" @click="addBookBtn">添加图书</el-button>
          </el-form>
        </div>

        <!--  表格 -->
        <div style="margin-left: 15%">
          <el-table :data="tableData" style="width: 80%">
            <el-table-column prop="bookName" label="图书名字" width="220"/>
            <el-table-column prop="author" label="图书作者" width="220"/>
            <el-table-column prop="price" label="价格" width="220"/>
            <el-table-column prop="press" label="出版社" width="220"/>
            <el-table-column prop="gmtCreate" label="上架日期" width="220"/>
            <el-table-column prop="gmtCreate" label="操作" width="220">
              <template #default="scope">
                <el-button type="primary" size="mini" @click="handleEdit(scope.row)">编辑</el-button>
                <el-button type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>


        <el-pagination
            class="pagination"
            :page-size="book_list.pageNum"
            :page-sizes="[20,40,60]"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
        />

        <el-dialog v-model="dialogTableVisible" title="编辑图书">
          <el-form :model="book_form">
            <el-form-item label="图书名字" :label-width="formLabelWidth">
              <el-input v-model="book_form.bookName"/>
            </el-form-item>
            <el-form-item label="作者" :label-width="formLabelWidth">
              <el-input v-model="book_form.author"/>
            </el-form-item>
            <el-form-item label="价格" :label-width="formLabelWidth">
              <el-input v-model="book_form.price"/>
            </el-form-item>
            <el-form-item label="出版者" :label-width="formLabelWidth">
              <el-input v-model="book_form.press"/>
            </el-form-item>

          </el-form>
          <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogTableVisible = false">取消</el-button>
        <el-button type="primary" @click="updateBook">
          确定
        </el-button>
      </span>
          </template>
        </el-dialog>


        <el-dialog v-model="dialogTableVisibleadd" title="添加图书">
          <el-form :model="book_form">
            <el-form-item label="图书名字" :label-width="formLabelWidth">
              <el-input v-model="book_form.bookName"/>
            </el-form-item>
            <el-form-item label="作者" :label-width="formLabelWidth">
              <el-input v-model="book_form.author"/>
            </el-form-item>
            <el-form-item label="价格" :label-width="formLabelWidth">
              <el-input v-model="book_form.price"/>
            </el-form-item>
            <el-form-item label="出版者" :label-width="formLabelWidth">
              <el-input v-model="book_form.press"/>
            </el-form-item>

          </el-form>
          <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogTableVisibleadd = false">取消</el-button>
        <el-button type="primary" @click="addBook">
          确定
        </el-button>
      </span>
          </template>
        </el-dialog>
      </el-main>
    </el-container>
  </el-container>

  <!-- 原来的代码-->



</template>

<script setup>
import {reactive, ref} from 'vue'
import {Check} from '@element-plus/icons-vue'
import axios from "axios";

// 定义一个响应式的对象
const dialogTableVisible = ref(false)
const dialogTableVisibleadd = ref(false)
const book_list = reactive({
  name: '',
  page: 1,
  limit: 10
})
const formLabelWidth = '140px'
const book_form = reactive({
  id: '',
  author: '',
  book_name: '',
  price: '',
  press: '',
  gmt_create: ''
})

const tableData = ref([])

function getBookList() {
  axios({
    method: 'get',
    url: 'http://localhost:8888/book/list?name=' + book_list.name + '&page=' + book_list.page + '&limit=' + book_list.limit,

  }).then(res => {
    console.log(res.data)
    tableData.value = res.data.data.data
    total.value = res.data.data.total

    console.log(res)
  })
}

const total = ref(0)


function search() {
  getBookList()
}


const handleSizeChange = (val) => {
  book_list.limit = val
  getBookList()
}
const handleCurrentChange = (val) => {
  book_list.page = val
  getBookList()
}

function handleEdit(row) {
  console.log(row)
  dialogTableVisible.value = true
  book_form.author = row.author
  book_form.bookName = row.bookName
  book_form.price = row.price
  book_form.press = row.press
  book_form.gmtCreate = row.gmtCreate
  book_form.id = row.id


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
function updateBook() {
//  发送请求更新数据
  axios({
    method: 'post',
    url: 'http://localhost:8888/book/update',
    data: book_form
  }).then(res => {
    console.log(res.data)
    tableData.value = res.data.data.data
    total.value = res.data.data.total
    console.log(res)
  })
//  关闭弹窗
  dialogTableVisible.value = false

  getBookList()
}

import {ElMessage, ElTable, ElMessageBox} from 'element-plus'

function handleDelete(row) {
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
      url: 'http://localhost:8888/book/delete?id=' + row.id,

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

  getBookList()

}

function addBookBtn() {
  dialogTableVisibleadd.value = true
  clearFormBook()
}

//清空表单
function clearFormBook() {
  book_form.author = ''
  book_form.bookName = ''
  book_form.price = ''
  book_form.press = ''
  book_form.gmtCreate = ''
  book_form.id = ''
}

function addBook() {

  axios({
    method: 'post',
    url: 'http://localhost:8888/book/add',
    data: book_form
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
  dialogTableVisibleadd.value = false
  getBookList()
}

getBookList()
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
