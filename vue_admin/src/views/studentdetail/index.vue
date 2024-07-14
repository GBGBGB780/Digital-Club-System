<template>
    <div class="card-container">
      <div  :key="student.userId" class="card">
        <h2 class="card-title">成员详情信息</h2>
        <div class="table-wrapper">
          <table class="table-content">
            <tr>
              <td>学号:</td>
              <td>{{ student.userId }}</td>
            </tr>
            <tr>
              <td>姓名:</td>
              <td>{{ student.userName }}</td>
            </tr>
            <tr>
              <td>邮箱:</td>
              <td>{{ student.email }}</td>
            </tr>
            <tr>
              <td>手机号:</td>
              <td>{{ student.phone }}</td>
            </tr>
            <tr>
              <td>校区:</td>
              <td>{{ student.campus }}</td>
            </tr>
            <tr>
              <td>专业:</td>
              <td>{{ student.major }}</td>
            </tr>
            <tr>
              <td>自我介绍:</td>
              <td>{{ student.description }}</td>
            </tr>
          </table>
        </div>
        
      </div>
    </div>
  </template>
  
  <script>
  import { mapGetters } from 'vuex'
  import { getStudentDetail } from '@/api/user.js'
  
  
  export default {
    name: 'Studentdetail',
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
        student: '',
        // isAccepted: '',
      }
    },
    created: function() {
      const id = this.$route.params.id // 获取路由参数id
      getStudentDetail(id).then((response) => {
        this.student = response.data.user
        // this.isAccepted = response.data.isAccepted;
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
    async reload() {
      await this.$store.dispatch('user/logout')
      this.$router.push(`/login?redirect=${this.$route.fullPath}`)
    },
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
  