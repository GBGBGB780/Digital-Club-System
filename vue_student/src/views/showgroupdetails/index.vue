<template>
  <div>
    <el-button
      v-if="group.attachment !== null"
      type="primary"
      style="margin-left: 50px; margin-top: 15px;"
      @click="downloadAttachment(group.id)"
    >下载附件</el-button>
  </div>
</template>

<script>
import { getAttachmentUrl } from '@/api/group.js'

export default {
  data() {
    return {
      group: {
        id: '',
        attachment: '',
        description: ''
      }
    }
  },
  mounted() {
    this.group.id = this.$route.params.groupid
    this.group.description = this.$route.params.description
  },
  methods: {
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

  }
}
</script>

<style scoped>
.description {
  font-size: 24px;
  font-weight: bold;
  color: #333;
  margin-top: 10px;
  /* 可根据需要调整其他样式属性 */
}
</style>

