<template>
  <el-table :data="activities" style="width: 100%" @row-click="handleRowClick">
    <el-table-column prop=name label="活动名" width="200">
      <template slot-scope="scope">
        <span class="text">{{ scope.row.name }}</span>
      </template>
    </el-table-column>
    <el-table-column prop=id label="活动编号" width="230">
      <template slot-scope="scope">
        <span class="text">{{ scope.row.id }}</span>
      </template>
    </el-table-column>
    <el-table-column prop=organizer label="组织者" width="120">
      <template slot-scope="scope">
        <span class="text">{{ scope.row.organizer }}</span>
      </template>
    </el-table-column>
    <el-table-column prop=time label="申请时间" width="300" sortable>
      <template slot-scope="scope">
        <span class="text">{{ formatDate(scope.row.time) }}</span>
      </template>
    </el-table-column>
    <el-table-column prop="isAccepted" label="申请状态" sortable>
      <template slot-scope="scope">
        <span v-if="scope.row.isAccepted === true" class="text success-text">已批准</span>
        <span v-else-if="scope.row.isAccepted === false" class="text fail-text">已拒绝</span>
        <span v-else class="text pending-text">待审核</span>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import { mapGetters } from 'vuex'
import { getMyActivity } from '@/api/activities.js'

export default {
  name: 'SeeActivity',
  computed: {
    ...mapGetters([
      'name'
    ])
  },
  data() {
    return {
      activities: [],
    };
  },
  created: function () {
    getMyActivity(this.$store.state.clubname).then((response) => {
      this.activities = response.data.items
      console.log(this.activities)
    })
      .catch(error => {
        console.error(error)
      })
      if(this.$store.state.clubid==0) {
      if(confirm("登陆身份失效，请重新登陆"))
      {
        this.reload()
      }
      else
      {
        window.alert("取消也得重新登陆")
        this.reload()
      }
    }
  },
  methods: {
    handleRowClick(row) {
      const aid = row.id // 获取当前行的id
      const activity = this.activities.filter(i => i.id === aid)[0]
      this.$router.push({
        name: 'Activitydetail',
        params: { 'aid': aid, 'activity': activity }
      });
    },
    formatDate(date) {
      // 格式化日期时间
      const d = new Date(date);
      return `${d.getFullYear()}年${d.getMonth() + 1
        }月${d.getDate()}日 ${String(d.getHours()).padStart(2, '0')}:${String(d.getMinutes()).padStart(2, '0')}:${String(d.getSeconds()).padStart(2, '0')}`;
    },
    async reload() {
      await this.$store.dispatch('user/logout')
      this.$router.push(`/login?redirect=${this.$route.fullPath}`)
    },
  },
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
</style>