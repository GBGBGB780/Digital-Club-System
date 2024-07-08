<template>
  <div>
    <div class="search-container" style="margin-left: 1%;">
      <el-input v-model="searchInfo" placeholder="搜索活动" clearable @keyup.enter.native="handleSearch" />
      <el-button icon="el-icon-search" @click="handleSearch" />
      <el-button @click="clearSearch">清空搜索</el-button>
    </div>
    <el-table :data="searcchRes">
      <el-table-column prop=name label="活动名" :span="6">
        <template slot-scope="scope">
          <span class="text">{{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column prop=id label="活动编号" :span="3">
        <template slot-scope="scope">
          <span class="text">{{ scope.row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column prop=organizer label="组织者" :span="6">
        <template slot-scope="scope">
          <span class="text">{{ scope.row.organizer }}</span>
        </template>
      </el-table-column>
      <el-table-column prop=time label="申请时间" :span="6" sortable>
        <template slot-scope="scope">
          <span class="text">{{ formatDate(scope.row.time) }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="isAccepted" label="申请状态" :span="3" sortable>
        <template slot-scope="scope">
          <span class="text pending-text">待审核</span>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { getMyApps } from '@/api/activities.js'

export default {
  name: 'Activityapplication',
  computed: {
    ...mapGetters([
      'name'
    ])
  },
  data() {
    return {
      searchInfo: '',
      activities: [],
      searcchRes: []
    };
  },
  created: function () {
    getMyApps('', this.$store.state.clubname).then((response) => {
      this.searcchRes = response.data.items
      this.activities = response.data.items
      console.log(this.searcchRes)
    })
      .catch(error => {
        console.error(error)
      })
  },
  methods: {
    // handleRowClick(row) {
    //   const aid = row.id // 获取当前行的id
    //   const activity = this.activities.filter(i => i.id === aid)[0]
    //   this.$router.push({
    //     name: 'Activitydetail',
    //     params: { 'aid': aid, 'activity': activity }
    //   });
    // },
    formatDate(date) {
      // 格式化日期时间
      const d = new Date(date);
      return `${d.getFullYear()}年${d.getMonth() + 1
        }月${d.getDate()}日 ${String(d.getHours()).padStart(2, '0')}:${String(d.getMinutes()).padStart(2, '0')}:${String(d.getSeconds()).padStart(2, '0')}`;
    },
    handleSearch() {
      getMyApps(this.searchInfo, this.$store.state.clubname)
        .then((response) => {
          this.searcchRes = response.data.items
        })
        .catch((error) => {
          console.error(error)
        })
    },
    clearSearch() {
      this.searchInfo = ''
      this.searcchRes = this.activities
      // this.$router.go()
    }
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