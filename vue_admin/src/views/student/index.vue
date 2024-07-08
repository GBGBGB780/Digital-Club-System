<template>
    <div class="student-container">
      <div>
        <el-input @keyup.enter.native="handleSearch" v-model="searchInfo" placeholder="搜索成员" clearable style="width: 10%;"/>
         <el-button icon="el-icon-search" @click="handleSearch" />
        <el-table 
          :data="studentlist"
          style="width: 100%"
          @row-click="handleRowClick">
          <el-table-column
            prop=user_name
            label="姓名"
            width="200">
            <template slot-scope="scope">
              <span class="text">{{ scope.row.userName }}</span>
            </template>
          </el-table-column>
          <el-table-column
            prop=user_id
            label="学号"
            width="230">
            <template slot-scope="scope">
              <span class="text">{{ scope.row.userId }}</span>
            </template>
          </el-table-column>
          <el-table-column
            prop=cteate_time
            label="申请时间"
            width="300">
            <template slot-scope="scope">
              <span class="text">{{ formatDate(scope.row.cteateTime) }}</span>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
  </template>
  
  <script>
  import { mapGetters } from 'vuex'
  import{ searchStudent } from "@/api/user.js"
  import { getStudentList } from "@/api/table.js";
  
  export default {
    name: 'StudentList',
    computed: {
      ...mapGetters([
        'name'
      ])},
    data() {
        return {
          studentlist: [],
          totalItems: 0,
          searchInfo: '',
        };
      },
    created: function () {
        getStudentList(this.$store.state.clubid).then((response) => {
        this.studentlist = response.data.items;
        console.log(this.studentlist);

      })
      .catch(error => {
        console.error(error);
      });
    },
    methods: {
      handleRowClick(row) {
      const id = row.userId; // 获取当前行的id
      this.$router.push({
        name: 'Studentdetail',
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
      handleSearch() {
        
        searchStudent(this.searchInfo)
        .then((response) => {
          this.studentlist = response.data.items;
          
        })
        .catch((error) => {
          console.error(error)
        })
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
  