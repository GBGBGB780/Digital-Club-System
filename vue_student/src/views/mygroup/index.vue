<template>
    <div>
    <div class="top-container">
      <div class="top-text">我的社团</div>
      <el-row :gutter="30">
        <el-col v-for="(group, index) in groups" :key="group.id" :span="6" :offset="0">
          <el-card class="group-card" :body-style="{ padding: '20px', height: '200px' }">
            <div class="group-content">
              <div class="image-wrapper">
                <img :src="group.image" class="group-image">
              </div>
              <div class="group-details">
                <!-- <div>index</div> -->
                <div class="group-name">{{ group.name }}</div>
                <div class="group-buttons">
                  <el-button type="primary" class="group-button" size="mini" @click="showDescription(group)">查看详情</el-button>
                </div>
              </div>
            </div>
          </el-card>
        </el-col>
      </el-row>
      <el-dialog :visible.sync="dialogVisible" title="社团详情" width="30%">
        <div style="font-weight: bold; font-size: 20px;">{{ currentGroup.description }}</div>
        <span slot="footer" class="dialog-footer">
          <el-button type="primary" @click="dialogVisible = false">关闭</el-button>
        </span>
      </el-dialog>
    </div>
    </div>
  </template>
  
  <script>
  import { mapGetters } from 'vuex'
  import { getMyGroups } from '@/api/group.js'
  import { getProfile } from '@/api/user.js'
  
  export default {
    name: 'Profile',
    computed: {
      ...mapGetters([
        'name'
      ]) },
    data() {
      return {
        groups: [],
        userID:'',
        dialogVisible: false,
        searchInfo: '',
        totalItems: 0,
        currentPage: 1,
        pageSize: 32,
        currentGroup: {
          description: ''
        }
      }
    },
    created: function() {
    getProfile(this.name)
      .then((res) => {
        this.userID = res.data.student.userId
      getMyGroups(this.userID).then((response) => {
        this.groups = response.data.groupItems
        console.log(response)
        console.log(this.groups)
      })
        .catch(error => {
          console.error(error)
        })})
    },
    methods: {
      showDescription(group) {
        this.currentGroup = group
        this.currentGroup.description = group.description
        this.dialogVisible = true
      },
      redirectToApplication(groupName) {
        this.$router.push({
          name: 'Application',
          params: {
            groupName: groupName
          }
        })
      },
      redirectToShowgroupdetails(groupId) {
        this.$router.push({
          name: 'Showgroupdetails',
          params: {
            groupId: groupId
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
  
  .group-card {
    height: 350px; /* 设置卡片高度 */
    margin-bottom: 20px; /* 设置卡片间的垂直间距 */
    width: 100%;
    border-radius: 8px;
    box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
  }
  
  .group-content {
    display: flex;
    flex-direction: column;
    height: 100%;
  }
  
  .image-wrapper {
    width: 100%;
    min-height: 210px;
    max-height: 210px;
  }
  
  .group-image {
    width: 100%;
    height: 100%;
    object-fit: cover;
    border-radius: 8px 8px 8px 8px;
  }
  
  .group-name {
    font-size: 20px;
    font-weight: bold;
    text-align: center;
    margin-bottom: 3px;
  }
  .group-buttons {
    display: flex;
    justify-content: flex-end;
    margin-top: 2px
  }
  
  .group-button {
    flex-grow: 1;
    margin: 0 20px;
  }
  
  .group-details {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    height: 100%;
    flex-grow: 1;
    padding-top: 10px; /* 设置卡片顶部内边距 */
  }
  
  .search-container {
    right: 600px;
    display: flex;
    align-items: center;
    width: 35%;
  }
  
  </style>
  