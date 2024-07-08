<template>
  <div class="add-activity-form">
    <br>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="活动名称" required>
        <el-col :span="11">
          <el-form-item prop="name">
            <el-input v-model="ruleForm.name"></el-input>
          </el-form-item>
        </el-col>
        <el-col class="line" :span="2"></el-col>
        <el-col :span="11">
          <el-form-item label="组织者" prop="organizer" required>
            <el-input v-model="ruleForm.organizer"></el-input>
          </el-form-item>
        </el-col>
      </el-form-item>
      <el-form-item label="活动地点" prop="place">
        <el-input v-model="ruleForm.place"></el-input>
      </el-form-item>
      <el-form-item label="活动时间" required>
        <el-date-picker v-model="ruleForm.time" type="datetime" placeholder="选择日期时间">
        </el-date-picker>
      </el-form-item>
      <!-- <el-form-item label="活动时间" required>
        <el-col :span="11">
          <el-form-item prop="date1">
            <el-date-picker type="date" placeholder="选择日期" v-model="ruleForm.date1"
              style="width: 100%;"></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item prop="date2">
            <el-time-picker placeholder="选择时间" v-model="ruleForm.date2" style="width: 100%;"></el-time-picker>
          </el-form-item>
        </el-col>
      </el-form-item> -->
      <el-form-item label="活动安排" prop="arrange">
        <el-input v-model="ruleForm.arrange"></el-input>
      </el-form-item>
      <el-form-item label="活动描述" prop="desc">
        <el-input type="textarea" v-model="ruleForm.desc"></el-input>
      </el-form-item>
      <hr>
      <!-- <el-form-item label="上传附件">
        <el-upload class="upload-demo" drag :on-remove="handleRemove" :before-remove="beforeRemove"
          action="https://localhost:8081/activity/uploadZip/" :before-upload="beforeZipUpload">
          <i class="el-icon-upload"></i>
          <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
          <div class="el-upload__tip" slot="tip">只能上传zip文件, 且不超过2MB</div>
        </el-upload>
      </el-form-item>
      <el-form-item label="上传图片">
        <el-upload class="upload-demo" drag :on-remove="handleRemove" list-type="picture"
          action="https://localhost:8081/activity/uploadPhoto/">
          <i class="el-icon-upload"></i>
          <i class="el-icon-picture"></i>
          <div class="el-upload__text">将图片拖到此处，或<em>点击上传</em></div>
          <div slot="tip" class="el-upload__tip">只能上传jpg/png文件, 且不超过2MB</div>
        </el-upload>
      </el-form-item>
      <hr> -->
      <el-form-item>
        <el-button type="primary" @click="submitActivity('ruleForm')">立即申请</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>


<script>
import { mapGetters } from 'vuex'
import { addActivity } from '@/api/activities.js'

export default {
  name: 'AddActivity',
  computed: {
    ...mapGetters([
      'name'
    ])
  },
  data() {
    return {
      ruleForm: {
        name: '',
        organizer: '',
        type: 0,
        place: '',
        time: '',
        arrange: '',
        desc: '',
        groupName: this.$store.state.clubname
      },
      rules: {
        name: [
          { required: true, message: '请输入活动名称', trigger: 'blur' },
          { min: 1, max: 15, message: '长度在 1 到 15 个字符', trigger: 'blur' }
        ],
        organizer: [
          { required: true, message: '请输入组织者', trigger: 'blur' },
          { min: 1, max: 15, message: '长度在 1 到 15 个字符', trigger: 'blur' }
        ],
        place: [
          { required: true, message: '请输入活动地点', trigger: 'blur' },
          { min: 1, max: 31, message: '长度在 1 到 31 个字符', trigger: 'blur' }
        ],
        time: [
          { type: 'date', required: true, message: '请选择活动时间', trigger: 'change' }
        ],
        // arrange: [
        //   { required: true, message: '请填写活动安排', trigger: 'blur' },
        //   { min: 1, max: 31, message: '长度在 1 到 31 个字符', trigger: 'blur' }
        // ]
      }
    };
  },
  methods: {
    submitActivity(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const a = this.getActivity()
          console.log(a)
          addActivity(a).then(response => {
            console.log(response)
            this.$message({
              message: '活动申请成功!',
              type: 'success'
            })
            this.resetForm(formName)
            this.ruleForm.groupName = this.$store.state.clubname
          }).catch(error => console.log(error))
          return true
        } else {
          this.$message({
            message: '活动格式错误!',
            type: 'error'
          })
          return false;
        }
      });
    },
    getActivity() {
      // use URLSearchParams to avoid decoding error
      let data = new URLSearchParams();
      data.append("name", this.ruleForm.name)
      data.append("organizer", this.ruleForm.organizer,)
      data.append("type", this.ruleForm.type,)
      data.append("place", this.ruleForm.place,)
      data.append("time", this.ruleForm.time,)
      data.append("arrange", this.ruleForm.arrange,)
      data.append("desc", this.ruleForm.desc,)
      data.append("groupName", this.ruleForm.groupName)
      return data
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    beforeRemove(file) {
      return this.$confirm(`确定移除 ${file.name}?`);
    },
    beforeZipUpload(file) {
      const isZIP = file.type === 'zip';
      const isLt2M = file.size / 1024 / 1024 < 2;
      if (!isZIP)
        this.$message.error('上传只能是 ZIP 格式!')
      if (!isLt2M)
        this.$message.error('上传大小不能超过 2MB!')
      return isZIP && isLt2M;
    },
    beforePhotoUpload(file) {
      const isJPG = file.type === 'jpg/png/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 2;
      if (!isJPG)
        this.$message.error('上传图片只能是 JPG/PNG 格式!')
      if (!isLt2M)
        this.$message.error('上传图片大小不能超过 2MB!')
      return isJPG && isLt2M;
    }
  }
}
</script>