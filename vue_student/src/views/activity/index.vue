<template>
    <div class="top-container">
      <div class="top-text">活动列表</div>
      <el-input @keyup.enter.native="handleSearch" v-model="searchInfo" placeholder="搜索活动" clearable style="width: 10%;"/>
        <el-button icon="el-icon-search" @click="handleSearch" />
      <el-row :gutter="30">
        <el-col v-for="(activity, index) in activites" :key="activity.id" :span="6" :offset="0">
          <el-card class="activity-card" :body-style="{ padding: '20px', height: '200px' }">
            <div class="activity-content">
              <div class="image-wrapper">
                <img :src="activity.image" class="activity-image">
              </div>
              <div class="activity-details">
                <!-- <div>index</div> -->
                <div class="activity-rank">{{ activity.organizer }}</div>
                <div class="activity-name">{{ activity.name }}</div>
                <div class="activity-buttons">
                  <el-button type="primary" class="activity-button" size="mini" @click="redirectToApplication(activity.id)">申请加入</el-button>
                  <el-button type="primary" class="activity-button" size="mini" @click="showDescription(activity)">查看详情</el-button>
                </div>
              </div>
            </div>
          </el-card>
        </el-col>
      </el-row>
      <el-dialog :visible.sync="dialogVisible" title="活动详情" width="30%">
        <div style="font-weight: bold; font-size: 20px;">活动详情：{{ currentActivity.description }}</div>
        <div style="font-weight: bold; font-size: 20px;">举办社团：{{ currentActivity.groupName }}</div>
        <span slot="footer" class="dialog-footer">
          <el-button type="primary" @click="dialogVisible = false">关闭</el-button>
        </span>
      </el-dialog>
    </div>
  </template>
  
  <script>
  import { mapGetters } from 'vuex'
  import { getActivites } from '@/api/activity.js'
  
  export default {
    name: 'activity',
    computed: {
      ...mapGetters([
        'userName'
      ]) },
    data() {
      return {
        activites: [],
        dialogVisible: false,
        currentActivity: {
          organizer: ''
        },
        totalItems: 0,
        pageSize: 32,
        currentPage: 1,
        searchInfo: ''
      }
    },
    created: function() {
      getActivites(null).then((response) => {
        this.activites = response.data.items
        console.error(this.activites)
      })
        .catch(error => {
          console.error(error)
        })
    },
    methods: {
      showDescription(activity) {
        this.currentActivity = activity
        this.currentActivity.organizer = activity.organizer
        this.dialogVisible = true
      },
      redirectToApplication(activityid) {
      this.$router.push({
        name: 'ActivityApplication',
        params: {
          activityid: activityid
        }
      })
    },
    handleSearch() {
      getActivites(this.searchInfo)
        .then((response) => {
          this.totalItems = response.data.items.length
          this.activites = response.data.items.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)
        })
        .catch((error) => {
          console.error(error)
        })
    },
      redirectToShowactivitydetails(activityId) {
        this.$router.push({
          name: 'Showactivitydetails',
          params: {
            activityId: activityId
          }
        })
      }
    }
  }
  </script>
  
  <style lang="scss" scoped>
  .top-container {
    padding: 20px;
  }
  
  .top-text {
    font-size: 18px;
    font-weight: bold;
    margin-bottom: 20px;
  }
  
  .activity-card {
    height: 350px; /* 设置卡片高度 */
    margin-bottom: 20px; /* 设置卡片间的垂直间距 */
    width: 100%;
    border-radius: 8px;
    box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
  }
  
  .activity-content {
    display: flex;
    flex-direction: column;
    height: 100%;
  }
  
  .image-wrapper {
    width: 100%;
    min-height: 210px;
    max-height: 210px;
  }
  
  .activity-image {
    width: 100%;
    height: 100%;
    object-fit: cover;
    border-radius: 8px 8px 8px 8px;
  }
  
  .activity-rank {
    font-size: 20px;
    font-weight: bold;
    text-align: center;
    margin-bottom: 3px;
  }
  .activity-name {
    font-size: 15px;
    font-weight: bold;
    text-align: center;
    margin-bottom: 3px;
  }
  .activity-buttons {
    display: flex;
    justify-content: flex-end;
    margin-top: 2px
  }
  
  .activity-button {
    flex-grow: 1;
    margin: 0 20px;
  }
  
  .activity-details {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    height: 100%;
    flex-grow: 1;
    padding-top: 10px; /* 设置卡片顶部内边距 */
  }
  
  </style>
  