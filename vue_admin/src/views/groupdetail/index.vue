<template>
  <div>
    <el-steps :active="3" style="margin-left: 200px; margin-right: 80px; margin-top: 20px">
      <el-step title="社团描述（必填）" icon="el-icon-edit"></el-step>
      <el-step title="上传附件（可选）" icon="el-icon-upload"></el-step>
      <el-step title="上传图片（可选）" icon="el-icon-picture"></el-step>
      <el-step title="确认修改（必点）" icon="el-icon-check"></el-step>
    </el-steps>
    <div class="groupcard">
    <div style="margin: 30px;" />
    <el-form :model="staticForm" label-width="80px" style="margin-left: 20px;">
      <el-form-item label="社团名称" prop="name">
        <el-input v-model="staticForm.name" :disabled="true" class="custom-data" />
      </el-form-item>
      <el-form-item label="社长" prop="leader">
        <el-input v-model="staticForm.leader" :disabled="true" class="custom-data" />
      </el-form-item>
    </el-form>
    <el-form ref="newForm" :model="newForm" status-icon :rules="rules" label-width="100px" class="demo-ruleForm">
      <el-form-item label="详细介绍" prop="description">
        <el-input v-model="newForm.description" type="decription" class="custom-data" />
        <el-upload
          :before-upload="beforeUpload"
          class="upload-demo"
          style="margin-top: 10px;"
          action="http://localhost:8081/group/uploadZip"
          :on-remove="handleRemove"
          :before-remove="beforeRemove"
          :on-success="handleUploadSuccess"
          :on-error="handleUploadError"
          multiple
          :limit="1"
          :on-exceed="handleExceed"
          :file-list="zipList"
          accept=".zip"
        >
          <el-button size="small" type="primary">上传附件</el-button>
          <div slot="tip" class="el-upload__tip" style="margin-top: -10px;">只能上传zip文件,且不超过10MB</div>
        </el-upload>
        <el-upload
          class="upload-demo"
          drag
          :on-success="handlePhotoUploadSuccess"
          :on-preview="handlePictureCardPreview"
          :on-remove="handleRemovePhoto"
          accept=".jpg,.png"
          :limit="1"
          action="http://localhost:8081/group/uploadPhoto"
          multiple
          :on-exceed="handlePhotoExceed"
        >
          <i class="el-icon-upload" />
          <div class="el-upload__text">将社团图片拖到此处，或<em>点击上传</em></div>
          <div slot="tip" class="el-upload__tip">只能上传jpg/png文件</div>
        </el-upload>
        <el-button type="primary" @click="updateDescription('newForm')">确认修改</el-button>
      </el-form-item>
    </el-form>

  </div>
</div>
</template>

<script>
import { mapGetters } from 'vuex'
import { getDetails, updateForm, updatePassword } from '@/api/manage.js'
export default {
  name: 'Groupdetail',
  computed: {
    ...mapGetters([
      'name'
    ]) },
  data() {
    const validatePass = (rule, value, callback) => {
      if (value.length < 6) {
        callback(new Error('密码长度不能少于6位'))
      } else {
        callback()
      }
    }
    const validatePass2 = (rule, value, callback) => {
      if (value !== this.ruleForm.pass) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      zipList: [],
      fileList: [],
      dialogImageUrl: '',
      dialogVisible: false,
      staticForm: {
        name: '',
        leader: ''
      },
      newForm: {
        description: '',
        attachment: '',
        image: ''
      },
      ruleForm: {
        pass: '',
        checkPass: ''
      },
      rules: {
        description: [
          { required: true, message: '内容不能为空', trigger: 'change' }
        ],
        pass: [
          { required: true, validator: validatePass, trigger: 'blur' }
        ],
        checkPass: [
          { required: true, validator: validatePass2, trigger: 'blur' }
        ]
      }
    }
  },
  created: function() {
    this.staticForm.name = this.name

    getDetails(this.$store.state.clubname)
      .then((response) => {
        this.staticForm.name = response.data.group.name
        this.staticForm.leader = response.data.group.leader
        this.newForm.description = response.data.group.description
        this.newForm.image = response.data.group.image
        this.newForm.attachment = response.data.group.attachment
      })
      .catch((error) => {
        console.error(error)
      })
  },
  methods: {
    handlePhotoExceed(files, fileList) {
      this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`)
    },
    handlePhotoUploadSuccess(response, file, fileList) {
      console.log(file, fileList)
      this.newForm.image = response.fileUrl
    },
    handleRemovePhoto(file, fileList) {
      this.fileList = this.fileList.filter((item) => item.name !== file.name)
      console.log(file, fileList)
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url
      this.dialogVisible = true
    },
    handleRemove(file, zipList) {
      console.log(file, zipList)
    },
    handleExceed(files, zipList) {
      this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + zipList.length} 个文件`)
    },
    // beforeRemove(file, zipList) {
    //   console.log(file, zipList)
    //   return this.$confirm(`确定移除 ${file.name}？`)
    // },
    handleUploadSuccess(response, file, zipList) {
      console.log(file, zipList)
      this.newForm.attachment = response.fileUrl
    },
    handleUploadError() {
      // 处理文件上传失败的情况
      this.dialogVisible = true // 显示提示信息的对话框
    },
    beforeUpload(file) {
      if (file.size / 1024 / 1024 > 10) {
        this.$message.error('文件大小不能超10MB')
        return false
      }
      return true
    },
    updateDescription(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$message({
            message: '详情介绍更新成功',
            type: 'success'
          })
          updateForm(this.staticForm.name, this.newForm.description, this.newForm.attachment, this.newForm.image)
            .then(response => {
              console.log(response.data)
              this.$router.push({ name: 'Groupdetail' })
            })
            .catch(error => {
              console.error(error)
            })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    submitPassword(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.showSuccessUpdatePassword()
          updatePassword(this.name, this.ruleForm.pass).then(response => {
            console.log(response.data)
            this.logout()
          })
            .catch(error => {
              console.error(error)
            })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    showSuccessUpdatePassword() {
      this.$message({
        message: '密码修改成功',
        type: 'success'
      })
    },
    async logout() {
      await this.$store.dispatch('group/logout')
      this.$router.push(`/login?redirect=${this.$route.fullPath}`)
    },
    resetForm(formName) {
      console.log( this.$store.state.clubid)
      console.log( this.$store.state.clubname)
      this.$refs[formName].resetFields()
    }
  }
}
</script>

<style>
.custom-data {
  width: 300px;
}
.groupcard {
  width: 600px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  text-align: center;
}
</style>

