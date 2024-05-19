<template>
  <div class="application-container">
    <div>
      <el-table 
        :data="applications"
        style="width: 100%"
        @row-click="handleRowClick">
        <el-table-column
          prop=stuname
          label="姓名"
          width="200">
          <template slot-scope="scope">
            <span class="text">{{ scope.row.stuname }}</span>
          </template>
        </el-table-column>
        <el-table-column
          prop=stunumber
          label="学号"
          width="230">
          <template slot-scope="scope">
            <span class="text">{{ scope.row.stunumber }}</span>
          </template>
        </el-table-column>
        <el-table-column
          prop=time
          label="申请时间"
          width="300">
          <template slot-scope="scope">
            <span class="text">{{ formatDate(scope.row.time) }}</span>
          </template>
        </el-table-column>
        <el-table-column
        prop="isaccepted"
        label="申请状态">
        <template slot-scope="scope">
          <span v-if="scope.row.isaccepted === true" class="text success-text">已批准</span>
          <span v-else-if="scope.row.isaccepted === false" class="text fail-text">已拒绝</span>
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
    getSeeApplications(this.name).then((response) => {
      this.applications = response.data.items;
    })
    .catch(error => {
      console.error(error);
    });
  },
  methods: {
    handleRowClick(row) {
    const id = row.applicationid; // 获取当前行的id
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
