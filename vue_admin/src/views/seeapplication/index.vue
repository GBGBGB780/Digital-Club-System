<template>
  <div class="application-container">
    <div>
      <el-table 
        :data="applications"
        style="width: 100%"
        @row-click="handleRowClick">
        <el-table-column
          prop=stuName
          label="姓名"
          width="200">
          <template slot-scope="scope">
            <span class="text">{{ scope.row.stuName }}</span>
          </template>
        </el-table-column>
        <el-table-column
          prop=stuNumber
          label="学号"
          width="230">
          <template slot-scope="scope">
            <span class="text">{{ scope.row.stuNumber }}</span>
          </template>
        </el-table-column>
        <el-table-column
          prop=time
          label="申请时间"
          width="300">
          <template slot-scope="scope">
            <span class="text">{{ formatDate(scope.row.createTime) }}</span>
          </template>
        </el-table-column>
        <el-table-column
        prop="isAccepted"
        label="申请状态">
        <template slot-scope="scope">
          <span v-if="scope.row.isAccepted === true" class="text success-text">已批准</span>
          <span v-else-if="scope.row.isAccepted === false" class="text fail-text">已拒绝</span>
          <span v-else class="text pending-text">待审核</span>
        </template>
      </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { getSeeApplications } from "@/api/seeapplication.js";

export default {
  name: 'Seeapplication',
  computed: {
    ...mapGetters([
      'name'
    ])},
  data() {
      return {
        applications: [],
      };
    },
  created: function () {
    getSeeApplications(this.$store.state.clubname).then((response) => {
      // console.log(this.$store.state.clubname)
      this.applications = response.data.items;
      console.log(this.applications)
    })
    .catch(error => {
      console.error(error);
    });
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
    const id = row.applicationId; // 获取当前行的id
    this.$router.push({
      name: 'Appdetail',
      params: { 'id': id }
      });
    },
    formatDate(date) {
      // 格式化日期时间
      const d = new Date(date);
      return `${d.getFullYear()}年${
        d.getMonth() + 1
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
