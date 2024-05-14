<template>
  <div class="card-container">
    <div v-for="detail in applications" :key="detail.applicationid" class="card">
      <h2 class="card-title">申请详情信息</h2>
      <div class="table-wrapper">
        <table class="table-content">
          <tr>
            <td>申请编号:</td>
            <td>{{ detail.applicationid }}</td>
          </tr>
          <tr>
            <td>社团名称:</td>
            <td>{{ detail.groupname }}</td>
          </tr>
          <tr>
            <td>姓名:</td>
            <td>{{ detail.stuname }}</td>
          </tr>
          <tr>
            <td>学号:</td>
            <td>{{ detail.stunumber }}</td>
          </tr>
          <tr>
            <td>手机号:</td>
            <td>{{ detail.phone }}</td>
          </tr>
          <tr>
            <td>性别:</td>
            <td>{{ detail.gender }}</td>
          </tr>
          <tr>
            <td>专业:</td>
            <td>{{ detail.major }}</td>
          </tr>
          <tr>
            <td>自我介绍:</td>
            <td>{{ detail.selfintro }}</td>
          </tr>
          <tr>
            <td>申请时间:</td>
            <td>{{ detail.time | formatDate }}</td>
          </tr>
          <tr>
            <td>申请状态:</td>
            <td v-if="detail.isaccepted === true">已批准</td>
            <td v-else-if="detail.isaccepted === false">已拒绝</td>
            <td v-else>待审核</td>
          </tr>
        </table>
      </div>
      <div class="button-container">
        <el-form>
          <el-button
            v-if="detail.attachment !== null"
            type="primary"
            class="edit-button left-button"
            @click="openAttachment(detail)"
          >查看附件</el-button>

          <el-button
            v-if="detail.isaccepted === null"
            type="success"
            class="edit-button"
            @click="submitAccept(detail)"
          >
            同意申请
          </el-button>
          <el-button
            v-else
            class="edit-button disabled-button"
          >
            同意申请
          </el-button>
          <el-button
            v-if="detail.isaccepted === null"
            type="danger"
            class="edit-button"
            @click="submitReject(detail)"
          >
            拒绝申请
          </el-button>
          <el-button
            v-else
            class="edit-button disabled-button"
          >
            拒绝申请
          </el-button>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { getAppDetails, acceptApplication, rejectApplication } from '@/api/seeapplication.js'
import router from '@/router'

export default {
  name: 'Appdetail',
  computed: {
    ...mapGetters([
      'name'
    ])
  },
  filters: {
    formatDate: function(value) {
      const date = new Date(value)
      const year = date.getFullYear()
      const month = String(date.getMonth() + 1).padStart(2, '0')
      const day = String(date.getDate()).padStart(2, '0')
      const hours = String(date.getHours()).padStart(2, '0')
      const minutes = String(date.getMinutes()).padStart(2, '0')
      const seconds = String(date.getSeconds()).padStart(2, '0')
      return `${year}年${month}月${day}日 ${hours}:${minutes}:${seconds}`
    }
  },
  data() {
    return {
      applications: []
      // IsAccepted: '',
    }
  },
  created: function() {
    const id = this.$route.params.id // 获取路由参数id
    getAppDetails(id).then((response) => {
      this.applications = response.data.items
      // this.IsAccepted = response.data.isAccepted;
    })
      .catch(error => {
        console.error(error)
      })
  },
  methods: {
    openAttachment(detail) {
      window.open(detail.attachment)
    },
    submitAccept(detail) {
      this.$message({
        message: '批准申请',
        type: 'success'
      })
      setTimeout(() => {
        acceptApplication(detail.applicationid)
          .then((response) => {
            console.log(response.data)
            // detail.isaccepted = true;
            router.go() // 刷新当前页面
          })
          .catch((error) => {
            console.error(error)
          })
      }, 100) // 延迟
    },
    submitReject(detail) {
      this.$message({
        message: '拒绝申请',
        type: 'success'
      })
      setTimeout(() => {
        rejectApplication(detail.applicationid)
          .then((response) => {
            console.log(response.data)
            // detail.isaccepted = false;
            router.go() // 刷新当前页面
          })
          .catch((error) => {
            console.error(error)
          })
      }, 100) // 延迟
    }
  }
}
</script>

<style>
.card-container {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: -70px;
}

.card {
  border: 10px solid #333333;
  padding: 100px;
  margin: 80px;
  width: 60%;
  height: 200%;
  text-align: left;
  margin-bottom: -50px;
}

.card-title {
  margin-top: -30px;
  text-align: center;
  font-size: 40px;
  margin-bottom: 50px;
}

.table-wrapper {
  border: 1px solid #000;
  display: flex;
  justify-content: center;
  padding: 10px;
}

.table-content {
  font-size: 25px;
  border-collapse: separate;
  border-spacing: 15px; /* 调整间隔大小，根据需要进行调整 */
  border-collapse: collapse;
  width: 100%;
}

.table-content td {
  border: 1px solid #000;
  padding: 8px;
}

.table-content tr:nth-child(even) {
  background-color: #f2f2f2;
}

.table-content tr:hover {
  background-color: #ddd;
}

.button-container {
  text-align: center;
  margin-top: 50px;
}

.edit-button {
  margin-left: 30px;
}

.left-button {
  margin-right: 110px; /* Adjust the margin to create space between the buttons */
}

.disabled-button {
  background-color: #cccccc !important;
  cursor: not-allowed !important;
}
</style>
