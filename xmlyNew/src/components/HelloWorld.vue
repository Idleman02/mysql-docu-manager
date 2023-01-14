<template>
  <div class="app-container">
    <h3>总人数{{ total }}</h3>
    <el-row :gutter="10" class="mb8">

      <el-col :span="1.5">
        <el-button
            type="primary"
            plain
            @click="refresh"
        >首页
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="primary"
            plain
            @click="xiai(0)"
        >喜爱值降序
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="primary"
            plain
            @click="xiai(1)"
        >喜爱值升序
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="primary"
            plain
            @click="fensi(0)"
        >粉丝数降序
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="primary"
            plain
            @click="fensi(1)"
        >粉丝数升序
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="primary"
            plain
            @click="gender(1)"
        >男
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="primary"
            plain
            @click="gender(0)"
        >女
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="primary"
            plain
            @click="gender(2)"
        >其他
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-input style="width: 180px" v-model="userForm.guanjianci" placeholder="关键词过滤" clearable/>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="primary"
            plain
            @click="showdown"
        >下载表格
        </el-button>
      </el-col>
    </el-row>

    <h5>
      <el-button @click="upye">上一页</el-button>
      已开播
      <el-button @click="downye">下一页</el-button>
    </h5>
    <el-form
        label-width="100px"
        :model="search"
    >
      <el-row>
        <el-col>
          <el-form-item label="名称：" prop="roleName">
            <el-input style="width: 300px" v-model="search.name" placeholder="请输入"/>
            <el-button type="primary" @click="searckBT">搜索</el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <el-divider/>

    <el-table
        :data="user_info"
        stripe
        v-loading="loading"
    >
      <el-table-column label="序号" align="center" prop="id"/>
      <el-table-column label="uid" align="center" prop="uid"/>
      <el-table-column label="主播名称" align="center" prop="nickname"/>
      <el-table-column label="性别" align="center" prop="gender"/>
      <el-table-column label="个性签名" align="center" prop="description"/>
      <el-table-column label="喜爱值" align="center" prop="totalXiaiValue"/>
      <el-table-column label="粉丝数量" align="center" prop="followerCount"/>
      <el-table-column label="近七日喜爱值" align="center" prop="XiaiValue"/>
      <!--        <el-table-column label="任务类型" align="center" prop="taskType"/>-->
    </el-table>

    <!--    <el-divider/>-->
    <!--    <h5>未开播</h5>-->
    <!--    <el-divider/>-->
    <!--    <el-table-->
    <!--        :data="user_info_no"-->
    <!--        stripe-->
    <!--        v-loading="loading"-->
    <!--    >-->
    <!--      <el-table-column label="id" align="center" prop="uid"/>-->
    <!--      <el-table-column label="主播名称" align="center" prop="nickname"/>-->
    <!--      <el-table-column label="性别" align="center" prop="gender"/>-->
    <!--      <el-table-column label="个性签名" align="center" prop="description"/>-->
    <!--      <el-table-column label="喜爱值" align="center" prop="totalXiaiValue"/>-->
    <!--      <el-table-column label="粉丝数量" align="center" prop="followerCount"/>-->
    <!--      <el-table-column label="近七日喜爱值" align="center" prop="XiaiValue"/>-->
    <!--      &lt;!&ndash;        <el-table-column label="任务类型" align="center" prop="taskType"/>&ndash;&gt;-->
    <!--    </el-table>-->

    <!--分页-->
    <el-row :gutter="20">
      <el-col :offset="6">
        <el-pagination
            :page-size="userForm.pageSize"
            :page-sizes="[20,40,60]"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
        />
      </el-col>
    </el-row>
    <!--添加采集任务-->
    <el-dialog
        v-model="downForm"
        width="200px">
      <el-form
          :model="downexcel"
      >
        <el-row>
          <el-col :span="18">
            <el-form-item label="初始值：" prop="roleName">
              <el-input v-model="downexcel.start" placeholder="请输入" maxlength="50"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="18">
            <el-form-item label="末尾值：" prop="roleName">
              <el-input v-model="downexcel.end" placeholder="请输入" maxlength="50"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-button type="primary" @click="downloads">生成</el-button>
        <el-button @click="downForm = false">取消</el-button>


      </el-form>

    </el-dialog>

  </div>
</template>

<script setup>
import {reactive, ref} from 'vue'
import {Check} from '@element-plus/icons-vue'
import axios from "axios";

const user_info = ref([])
const user_info_no = ref([])
const loading = ref(true)
const downForm = ref(false)
const total = ref(-1);

let date = new Date();
const userForm = reactive({
  pageNum: 1,
  pageSize: 20,
  guanjianci: '',
  fensiType: -1,
  xiaiType: -1,
  gender: -1,
})

const downexcel = reactive({
  start: 0,
  end: 100,
})
const search = reactive({
  name: '',
})

function getuserlist() {
  clearform()
  loading.value = true
  axios.defaults.retryDelay = 10000;
  axios({
    method: 'get',
    url: 'http://150.158.83.145:6689',
    params: userForm
  }).then(res => {
    user_info.value = res.data.data.list
    user_info_no.value = res.data.data.list_no
    total.value = res.data.data.total
    userForm.fensiType = res.data.data.fensiType
    userForm.xiaiType = res.data.data.xiaiType
    userForm.guanjianci = res.data.data.guanjianci


    // 获取当前是几号
    var nowMonth = date.getMonth() + 1;

// 获取当前是几号
    var strDate = date.getDate();
// 对月份进行处理，1-9月在前面添加一个“0”
    if (nowMonth >= 1 && nowMonth <= 9) {
      nowMonth = "0" + nowMonth;
    }
// 对月份进行处理，1-9号在前面添加一个“0”
    if (strDate >= 0 && strDate <= 9) {
      strDate = "0" + strDate;
    }

    for (let i = 0; i < user_info.value.length; i++) {
      user_info.value[i].id = i + 1 + ((userForm.pageNum-1) * userForm.pageSize)
      if (user_info.value[i].gender === 0) {
        user_info.value[i].gender = '女'
      }
      if (user_info.value[i].gender === 1) {
        user_info.value[i].gender = '男'
      }
      if (user_info.value[i].gender === 2) {
        user_info.value[i].gender = '未知'
      }
    }
    for (let i = 0; i < user_info_no.value.length; i++) {
      if (user_info_no.value[i].gender === 0) {
        user_info_no.value[i].gender = '女'
      }
      if (user_info_no.value[i].gender === 1) {
        user_info_no.value[i].gender = '男'
      }
      if (user_info_no.value[i].gender === 2) {
        user_info_no.value[i].gender = '未知'
      }
    }
    loading.value = false
  })
}

function downloads() {
  window.location.href = 'http://150.158.83.145:6689/downloads?start=' + downexcel.start + '&end=' + downexcel.end
}


const handleSizeChange = (val) => {
  userForm.pageSize = val
  getuserlist()
}
const handleCurrentChange = (val) => {
  userForm.pageNum = val
  getuserlist()
}

function clearform() {
  user_info.value.length = 0
}

function xiai(type) {
  userForm.xiaiType = type
  getuserlist()
}

function fensi(type) {
  userForm.fensiType = type
  getuserlist()
}

function gender(type) {
  userForm.gender = type
  getuserlist()
}

function refresh() {
  userForm.guanjianci = ''
  userForm.fensiType = -1
  userForm.xiaiType = -1
  userForm.gender = -1
  getuserlist()
}

function showdown() {
  downexcel.start = 0
  downexcel.end = 100
  downForm.value = true
}


function upye() {
  if (userForm.pageNum !== 1) {
    userForm.pageNum--
    getuserlist()
  }
}

function searckBT(){
  user_info.value.length = 0
  axios({
    method: 'get',
    url: 'http://150.158.83.145:6689/serch',
    params: search
  }).then(res => {
    user_info.value.push(res.data.data.list.info)
    for (let i = 0; i < user_info.value.length; i++) {
      user_info.value[i].id = i + 1 + ((userForm.pageNum-1) * userForm.pageSize)
      if (user_info.value[i].gender === 0) {
        user_info.value[i].gender = '女'
      }
      if (user_info.value[i].gender === 1) {
        user_info.value[i].gender = '男'
      }
      if (user_info.value[i].gender === 2) {
        user_info.value[i].gender = '未知'
      }
    }
    loading.value = false
  })
}

function downye() {
  if (userForm.pageNum !== total.value) {
    userForm.pageNum++
    getuserlist()
  }
}

getuserlist()
</script>

