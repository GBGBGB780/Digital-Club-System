<template>
  <div>
    <el-table
      :data="myapps"
      style="width: 100%"
      :row-class-name="tableRowClassName"
    >
      <el-table-column
        prop="time"
        label="申请时间"
        width="300"
      >
        <template slot-scope="scope">
          <span class="text">{{ formatDate(scope.row.time) }}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="groupName"
        label="申请社团"
        width="300"
      >
        <template slot-scope="scope">
          <span class="text">{{ scope.row.groupName }}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="isAccepted"
        label="申请状态"
      >
        <template slot-scope="scope">
          <span v-if="scope.row.isAccepted === true" class="text success-text">已通过</span>
          <span v-else-if="scope.row.isAccepted === false" class="text fail-text">未通过</span>
          <span v-else class="text pending-text">待审核</span>
        </template>
      </el-table-column>
    </el-table>
    <el-col :span="10">
      <el-card shadow="hover" class="text-center" style="width: 100%;margin-left: 70% ">
        <div slot="header" class="clearfix">
          <span>钱福《明日歌》</span>
        </div>
        <div style="font-size: 18px;">明日复明日</div>
        <div style="font-size: 18px;">明日何其多</div>
        <div style="font-size: 18px;">我生待明日</div>
        <div style="font-size: 18px;">万事成蹉跎</div>
        <div style="margin-top: 40px;" />
        <h3>距离明天还有: {{ hours }}小时 {{ minutes }}分钟 {{ seconds }}秒</h3>
        <h1>就在今天，加入你喜欢的社团吧！</h1>
      </el-card>
    </el-col>
  </div>
</template>

<style>
  .el-table th {
    font-weight: bold;
    font-size: 20px;
  }
  .text-center {
    text-align: center;
  }
  .text {
    font-size: 17px;
    font-weight: bold;
  }

  .el-table .fail-row {
    background: rgb(250, 231, 231);
  }

  .el-table .success-row {
    background: #e1fce8;
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

<script>
import { mapGetters } from 'vuex'
import { getApplications } from '@/api/group.js'
export default {
  name: 'Myapplication',
  computed: {
    ...mapGetters([
      'name'
    ]) },
  mounted() {
    this.startCountdown()
  },
  beforeDestroy() {
    clearInterval(this.timer)
  },
  created: function() {
    getApplications(this.name)
      .then((response) => {
        this.myapps = response.data.items
        this.myapps.sort((a, b) => {
          const timeA = new Date(a.time)
          const timeB = new Date(b.time)
          return timeB - timeA
        })
      })
      .catch(error => {
        console.error(error)
      })
  },
  methods: {
    tableRowClassName({ row }) {
      if (row.isAccepted === true) {
        return 'success-row'
      } else if (row.isAccepted === false) {
        return 'fail-row'
      }
      return ''
    },
    startCountdown() {
      this.timer = setInterval(() => {
        const now = new Date()
        const tomorrow = new Date(now.getFullYear(), now.getMonth(), now.getDate() + 1)
        const distance = tomorrow - now

        this.hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60))
        this.minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60))
        this.seconds = Math.floor((distance % (1000 * 60)) / 1000)
      }, 1000)
    },
    formatDate(date) {
      // 格式化日期时间
      const d = new Date(date)
      return `${d.getFullYear()}年${
        d.getMonth() + 1
      }月${d.getDate()}日 ${String(d.getHours()).padStart(2, '0')}:${String(d.getMinutes()).padStart(2, '0')}:${String(d.getSeconds()).padStart(2, '0')}`
    }
  },
  data() {
    return {
      myapps: [],
      hours: 0,
      minutes: 0,
      seconds: 0,
      timer: null,
      isPageLoaded: false
    }
  }
}
</script>
