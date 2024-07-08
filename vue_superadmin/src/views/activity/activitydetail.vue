<template>
  <div>
    <el-header>
      <h1>活动详情信息</h1>
    </el-header>
    <el-row :gutter="20">
      <el-col :span="4">
        <div class="grid-content bg-purple"><el-main class="el-icon-wind-power"> 活动名</el-main></div>
      </el-col>
      <el-col :span="4">
        <div class="grid-content bg-purple-light"><el-main>{{ activity.name }}</el-main></div>
      </el-col>
      <el-col :span="4">
        <div class="grid-content bg-purple"><el-main class="el-icon-location"> 地点</el-main></div>
      </el-col>
      <el-col :span="4">
        <div class="grid-content bg-purple-light"><el-main>{{ activity.place }}</el-main></div>
      </el-col>
      <el-col :span="4">
        <div class="grid-content bg-purple"><el-main class="el-icon-s-custom"> 组织者</el-main></div>
      </el-col>
      <el-col :span="4">
        <div class="grid-content bg-purple-light"><el-main>{{ activity.organizer }}</el-main></div>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="4">
        <div class="grid-content bg-purple"><el-main class="el-icon-time"> 申请时间</el-main></div>
      </el-col>
      <el-col :span="4">
        <div class="grid-content bg-purple-light"><el-main>{{ activity.time | formatDate }}</el-main></div>
      </el-col>
      <el-col :span="4">
        <div class="grid-content bg-purple"><el-main class="el-icon-set-up"> 事项安排</el-main></div>
      </el-col>
      <el-col :span="4">
        <div class="grid-content bg-purple-light"><el-main>{{ activity.arrange }}</el-main></div>
      </el-col>
      <el-col :span="4">
        <div class="grid-content bg-purple"><el-main class="el-icon-open"> 活动状态</el-main></div>
      </el-col>
      <el-col :span="4">
        <div v-if="activity.isAccepted === true" class="grid-content bg-purple-light success-text">
          <el-main>已批准</el-main>
        </div>
        <div v-else-if="activity.isAccepted === false" class="grid-content bg-purple-light fail-text">
          <el-main>已拒绝</el-main>
        </div>
        <div v-else class="grid-content bg-purple-light pending-text"><el-main>待审核</el-main></div>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="4">
        <div class="grid-content bg-purple"><el-main class="el-icon-notebook-2
"> 描述</el-main></div>
      </el-col>
      <el-col :span="20">
        <div class="grid-content bg-purple-light"><el-main>{{ activity.description }}</el-main></div>
      </el-col>
    </el-row>

    <div class="button-container">
      <el-form>
        <el-button v-if="activity.attachment !== null" type="primary" class="el-icon-folder edit-button left-button"
          @click="openAttachment()">查看附件</el-button>
        <el-button v-else type="primary" class="el-icon-folder-delete left-button" disabled> 查看附件</el-button>
        <el-button v-if="activity.image !== null" type="primary" class="el-icon-picture edit-button left-button"
          @click="openImage()">查看图片</el-button>
        <el-button v-else type="primary" class="el-icon-picture-outline left-button" disabled> 查看图片</el-button>
      </el-form>
    </div>

    <br>
    <div class="button-container">
      <el-form>
        <el-button v-show="activity.isAccepted !== true" type="primary" class="edit-button left-button"
          @click="acceptActivity">批准申请</el-button>
        <el-button v-show="activity.isAccepted === true" type="primary" class="edit-button left-button" disabled
          @click="acceptActivity">批准申请</el-button>
        <el-button v-show="activity.isAccepted !== false" type="primary" class="edit-button left-button"
          @click="rejectActivity">拒绝申请</el-button>
        <el-button v-show="activity.isAccepted === false" type="primary" class="edit-button left-button" disabled
          @click="rejectActivity">拒绝申请</el-button>
      </el-form>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { acceptActivity, rejectActivity } from '@/api/activities.js'

export default {
  name: 'ActivityList',
  filters: {
    formatDate: function (value) {
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
      activity: {}
    }
  },
  computed: {
    ...mapGetters([
      'name'
    ])
  },
  created: function () {
    this.activity = this.$route.params.activity // 获取路由参数activity
  },
  methods: {
    openAttachment() {
      window.open(this.activity.attachment)
    },
    openImage() {
      window.open(this.activity.image)
    },
    acceptActivity() {
      this.$message({
        message: '批准申请',
        type: 'success'
      })
      setTimeout(() => {
        acceptActivity(this.activity.id)
          .then((response) => {
            console.log(response.data)
            this.activity.isAccepted = true
          })
          .catch((error) => {
            console.error(error)
          })
      }, 100) // 延迟
    },
    rejectActivity() {
      this.$message({
        message: '拒绝申请',
        type: 'success'
      })
      setTimeout(() => {
        rejectActivity(this.activity.id)
          .then((response) => {
            console.log(response.data)
            this.activity.isAccepted = false
          })
          .catch((error) => {
            console.error(error)
          })
      }, 100)
    }
  }
}
</script>

<style scoped>
.el-row {
  margin-bottom: 20px;

  &:last-child {
    margin-bottom: 0;
  }
}

.el-col {
  border-radius: 4px;
}

.bg-purple-dark {
  background: #99a9bf;
}

.bg-purple {
  background: #d3dce6;
}

.bg-purple-light {
  background: #e5e9f2;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}

.button-container {
  text-align: center;
  margin-top: 50px;
}

.edit-button {
  margin-left: 30px;
}

.left-button {
  margin-right: 120px;
}

.success-text {
  color: #4caf50;
}

.fail-text {
  color: #f44336;
}

.pending-text {
  color: #ff9800;
}
</style>