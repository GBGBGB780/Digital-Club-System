<template>
  <div>
    <div class="dashboard-container">
      <div class="typed-js-container">
        <vue-typed-js style="margin-left: 20px;" class="mao" :strings="['丰富活动等你参与', '海量社团等你选择','欢迎你登录中山大学社团招新系统']">
          <h3 class="typing" />
        </vue-typed-js>
      </div>
      <div class="search-container" style="margin-left: 50px;">
        <el-input v-model="searchinfo" placeholder="搜索社团" clearable />
        <el-button icon="el-icon-search" @click="handleSearch" />
        <el-button style="margin-left: 50px;" @click="showVideoDialog">播放社团宣传视频</el-button>
        <div class="audio-container" style="margin-left: 50px; display: flex; align-items: center;">
          <span style="white-space: nowrap;">
            《山高水长》
          </span>
          <audio ref="audio" :src="music_path" controls />
        </div>
        <el-popover
          placement="bottom"
          width="200"
          trigger="click"
          :content="`天气:${weather.weather}   温度:${weather.temperature}℃     ${weather.remark}`"
        >
          <el-button slot="reference" style="margin-left: 50px;">查看天气</el-button>
        </el-popover>
        <i class="el-icon-arrow-down" style="margin-left: 50px; cursor: pointer;" @click="carouselVisible = !carouselVisible"></i>
      </div>
    </div>
    <div class="block" v-if="carouselVisible">
      <el-carousel height="800px">
        <el-carousel-item v-for="item in imagebox" :key="item.id">
          <a :href="getWebsiteURL(item.id)">
            <img :src="item.idView" class="image">
          </a>
        </el-carousel-item>
      </el-carousel>
    </div>
    <el-dialog :visible="videoDialogVisible" @close="handleVideoDialogClose">
      <div class="video-container">
        <video ref="videoPlayer" controls style="width: 100%; height: 100%;">
          <source :src="videoUrl" type="video/mp4">
          Your browser does not support the video tag.
        </video>
      </div>
    </el-dialog>
    <el-row :gutter="30">
      <el-col v-for="group in groups" :key="group.id" :span="6" :offset="0">
        <el-card class="group-card" :body-style="{ padding: '20px', height: '200px' }">
          <div class="group-content">
            <div class="image-wrapper">
              <img :src="group.image" class="group-image">
            </div>
            <div class="group-details">
              <div class="group-name">{{ group.username }}</div>
              <div class="group-buttons">
                <el-button type="primary" class="group-button" size="mini" @click="redirectToApplication(group.username)">申请加入</el-button>
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
        <el-button
            v-if="currentGroup.attachment !== null"
          type="primary"
          style="margin-left: 50px; margin-top: 15px;"
          @click="downloadAttachment(currentGroup.id)"
        >下载社团详情附件</el-button>
        <el-button type="primary" @click="dialogVisible = false">关闭</el-button>
      </span>
    </el-dialog>
    <div style="bottom: 0; left: 0; right: 0;">
    <el-pagination
      :current-page="currentPage"
      :page-sizes="[ 4,8, 16,32]"
      :page-size="pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="totalItems"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    /></div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { getGroups, getVideo, getWeather, getAttachmentUrl } from '@/api/group.js'

export default {
  name: 'Dashboard',
  computed: {
    ...mapGetters([
      'name'
    ]) },

  data() {
    return {
      // 音频文件路径
      music_path: '/山高水长.mp3',
      totalItems: 0,
      // 是否播放音频
      pageSize: 32,
      currentPage: 1,
      is_play: false,
      groups: [],
      weather: '',
      dialogVisible: false,
      currentGroup: {
        description: ''
      },
      searchinfo: '',
      videoDialogVisible: false,
      videoUrl: '',
      imagebox: [
        { id: 0, idView: require('../../assets/caraousel/走马灯_05.png') },
        { id: 1, idView: require('../../assets/caraousel/走马灯_06.png') },
        { id: 2, idView: require('../../assets/caraousel/走马灯_07.png') },
        { id: 3, idView: require('../../assets/caraousel/走马灯_08.png') }
      ],
      carouselVisible: true,
      linkbox: [
        { id: 0, idView: ('https://p0.itc.cn/q_70/images03/20220802/252a97dd74244b92a06da8cd978bb0fb.jpeg') },
        { id: 1, idView: ('https://huacheng.gz-cmc.com/upload/news/image/2020/07/15/f9ba78f5c0f94f4a9c09b57046aa0ad9.jpeg') },
        { id: 2, idView: ('https://p3.itc.cn/q_70/images03/20210703/c577b8b0385d480f9ad325b6b9a7f51d.jpeg') },
        { id: 3, idView: ('https://zt.gaoxiaojob.com/zsdxrjgcxy2103.jpg') }
      ]
    }
  },
  created: function() {
    this.searchinfo = this.$route.query.searchinfo
    this.handleSearch()
    this.getWeather()
  },
  methods: {
    getWebsiteURL(id) {
    if (id === 0) {
      return 'https://ztjy.sysu.edu.cn/'; // 第一个item的id为1，跳转到example.com
    }
    else if (id === 1) {
      return 'https://www.sysu.edu.cn/news/ztjj2/gdzt/zt2023ncjgzhy.htm'; // 第二个item的id为2，跳转到example2.com
    }
    else if (id === 2) {
      return 'https://www.sysu.edu.cn/xxg/zdjj1.htm'; // 第二个item的id为2，跳转到example2.com
    }
    else {
      return 'https://ddhzt.sysu.edu.cn/'; // 其他情况下跳转到默认网站
    }
  },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`)
      this.pageSize = val
      this.handleSearch()
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`)
      this.currentPage = val
      this.handleSearch()
    },
    showDescription(group) {
      this.currentGroup = group
      this.currentGroup.description = group.description
      this.dialogVisible = true
    },
    showVideoDialog() {
      getVideo()
        .then(response => {
          this.videoUrl = response.data.url
          // 打开弹窗
          this.videoDialogVisible = true
          // 获取video标签的引用
          const videoElement = this.$refs.videoPlayer
          // 播放视频
          videoElement.play()
        })
        .catch(error => {
          console.error('获取视频失败:', error)
        })
    },
    handleVideoDialogClose() {
      const videoElement = this.$refs.videoPlayer
      videoElement.pause()
      videoElement.currentTime = 0
      this.videoDialogVisible = false
    },
    redirectToApplication(groupname) {
      this.$router.push({
        name: 'Application',
        params: {
          groupname: groupname
        }
      })
    },
    async downloadAttachment(groupId) {
      try {
        const response = await getAttachmentUrl(groupId)
        if (response.attachment !== '') {
          const url = response.attachment
          window.open(url, '_blank')
        } else {
          this.$message.error('无法获取附件 URL')
        }
      } catch (error) {
        console.error(error)
        this.$message.error('下载附件时发生错误')
      }
    },
    // redirectToShowgroupdetails(groupid,description) {
    //   this.$router.push({
    //     name: 'Showgroupdetails',
    //     params: {
    //       groupid: groupid,
    //       description: description
    //     }
    //   })
    // },
    handleSearch() {
      getGroups(this.searchinfo)
        .then((response) => {
          this.totalItems = response.data.items.length
          this.groups = response.data.items.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)
        })
        .catch((error) => {
          console.error(error)
        })
    },
    getWeather() {
      getWeather()
        .then((response) => {
          this.weather = response.data
        })
        .catch((error) => {
          console.error(error)
        })
    }
  }
}
</script>

<style lang="scss" scoped>
.text-center {
  text-align: center;
}
.audio-container{
  margin-right: 10px;
  font-size: 18px;
  line-height: 24px;
}
.mao {
  position: relative;
  -webkit-background-clip: text;
  background-clip: text;
  background-image: linear-gradient(rgb(74, 14, 240), rgb(67, 248, 248));
  -webkit-text-fill-color: transparent;
}
.video-container {
  width: 100%;
  height: 100%;
}

audio {
  display: block;
  margin: 0 auto;
}

.dashboard-container {
  display: flex;
  align-items: center;
}

.dashboard-text {
  font-size: 18px;
  font-weight: bold;
  margin-right: 30px;
}

.search-container {
  right: 600px;
  display: flex;
  align-items: center;
}

.video-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
}

.video-container video {
  max-width: 100%;
  max-height: 100%;
}

.group-card {
  height: 300px; /* 设置卡片高度 */
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

.block {
  position: relative; /* 设置父容器为相对定位，为绝对定位的图片提供参考 */
}

.block .image {
  position: absolute; /* 将图片设置为绝对定位 */
  top: 0;
  left: 0;
  width: 100%; /* 图片宽度占满父容器 */
  height: 100%; /* 图片高度占满父容器 */
  object-fit: cover; /* 按比例缩放图片，以覆盖整个容器 */
}

.el-pagination {
  text-align: center;
}
</style>
