<template>
  <div>
    <br>
    <el-row :gutter="20">
      <el-col :span="6">
        <el-statistic :value="activities.length" title="活动数" />
      </el-col>
      <el-col :span="6">
        <el-statistic :value="toBeAccepted" title="已通过" />
      </el-col>
      <el-col :span="6">
        <el-statistic :value="toBeAccepted" title="已拒绝" />
      </el-col>
      <el-col :span="6">
        <el-statistic :value="toBeAccepted" title="待审批" />
      </el-col>
    </el-row>
    <div class="search-container" style="margin-left: 1%;">
      <el-input v-model="searchInfo" placeholder="搜索活动" clearable @keyup.enter.native="handleSearch" />
      <el-button icon="el-icon-search" @click="handleSearch" />
      <el-button @click="clearSearch">清空搜索</el-button>
    </div>
    <el-table :data="activities" style="width: 100%" @row-click="handleRowClick">
      <el-table-column prop="name" label="活动名" :span="8">
        <template slot-scope="scope">
          <span class="text">{{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="id" label="活动编号" :span="4">
        <template slot-scope="scope">
          <span class="text">{{ scope.row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="organizer" label="组织者" :span="2">
        <template slot-scope="scope">
          <span class="text">{{ scope.row.organizer }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="time" label="活动时间" span="4" sortable>
        <template slot-scope="scope">
          <span class="text">{{ formatDate(scope.row.time) }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="isAccepted" label="申请状态" :span="6" sortable>
        <template slot-scope="scope">
          <span v-if="scope.row.isAccepted === true" class="text success-text">已批准</span>
          <span v-else-if="scope.row.isAccepted === false" class="text fail-text">已拒绝</span>
          <span v-else class="text pending-text">待审核</span>
          <el-button v-show="scope.row.isAccepted !== true" type="primary" style="float: right;"
            @click="acceptActivity(scope.row.id)">批准</el-button>
          <el-button v-show="scope.row.isAccepted === true" type="primary" style="float: right;"
           disabled>批准</el-button>
          <el-button v-show="scope.row.isAccepted !== false" type="primary" style="float: right;"
            @click="rejectActivity(scope.row.id)">拒绝</el-button>
          <el-button v-show="scope.row.isAccepted === false" type="primary" style="float: right;"
            disabled>拒绝</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { searchActivities, getAllApps, acceptActivity, rejectActivity } from '@/api/activities.js'

export default {
  name: 'SeeAllActivity',
  data() {
    return {
      activities: [],
      searchInfo: ''
    }
  },
  computed: {
    ...mapGetters([
      'name'
    ]),
    toBeAccepted() {
      return this.activities.filter(a => a.isAccepted === null).length
    },
    accepted() {
      return this.activities.filter(a => a.isAccepted === true).length
    },
    rejected() {
      return this.activities.filter(a => a.isAccepted === false).length
    }
  },
  created: function () {
    getAllApps().then((response) => {
      this.activities = response.data.items
      console.log(this.activities)
    })
      .catch(error => {
        console.error(error)
      })
  },
  methods: {
    handleRowClick(row) {
      const aid = row.id // 获取当前行的id
      const activity = this.activities.filter(i => i.id === aid)[0]
      this.$router.push({
        name: 'Activitydetail',
        params: { 'aid': aid, 'activity': activity }
      })
    },
    formatDate(date) {
      // 格式化日期时间
      const d = new Date(date)
      return `${d.getFullYear()}年${d.getMonth() + 1}月${d.getDate()}日 ${String(d.getHours()).padStart(2, '0')}:${String(d.getMinutes()).padStart(2, '0')}:${String(d.getSeconds()).padStart(2, '0')}`
    },
    handleSearch() {
      searchActivities(this.searchInfo)
        .then((response) => {
          this.activities = response.data.items
        })
        .catch((error) => {
          console.error(error)
        })
    },
    clearSearch() {
      this.searchInfo = ''
      this.$router.go()
    },
    acceptActivity(id) {
      event.stopPropagation()
      setTimeout(() => {
        acceptActivity(id)
          .then((response) => {
            console.log(response.data)
            this.$message({
              message: '批准申请',
              type: 'success'
            })
            this.$router.go()
          })
          .catch((error) => {
            console.error(error)
          })
      }, 100) // 延迟
    },
    rejectActivity(id) {
      event.stopPropagation()
      setTimeout(() => {
        rejectActivity(id)
          .then((response) => {
            console.log(response.data)
            this.$message({
              message: '拒绝申请',
              type: 'success'
            })
            this.$router.go()
          })
          .catch((error) => {
            console.error(error)
          })
      }, 100)
    }
  }
}
</script>

<style>
.el-table th {
  font-weight: bold;
  font-size: 18px;
}

.text {
  font-size: 14px;
  font-weight: bold;
}

.el-table .fail-row {
  background: rgb(250, 231, 231);
}

.el-table .success-row {
  background: #e1fce8;
}

.edit-button {
  margin-left: 30px;
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

.search-container {
  right: 600px;
  display: flex;
  align-items: center;
  width: 35%;
}

.no-click {
  pointer-events: none;
}
</style>
