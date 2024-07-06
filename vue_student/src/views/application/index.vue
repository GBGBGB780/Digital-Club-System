<template>
  <el-form ref="ruleForm" :model="ruleForm" :rules="rules" label-width="100px" class="demo-ruleForm">
    <el-form-item label="姓名" prop="stuName">
      <el-input v-model="stuName" :disabled="true"/>
    </el-form-item>
    <el-form-item label="学号" prop="stuNumber">
      <el-input v-model="stuNumber" :disabled="true"/>
    </el-form-item>
    <el-form-item label="电话" prop="phone">
      <el-input v-model="ruleForm.phone" :disabled="true"/>
    </el-form-item>
    <el-form-item label="专业" prop="major">
      <el-input v-model="ruleForm.major" :disabled="true"/>
    </el-form-item>
    <el-form-item label="自我陈述" prop="selfIntro">
      <el-input v-model="ruleForm.selfIntro" type="textarea" :rows="8" />
    </el-form-item>
    <el-upload
      class="upload-demo"
      style="margin-left: 100px;"
      action="http://localhost:8081/application/uploadZip"
      :on-remove="handleRemove"
      :before-remove="beforeRemove"
      :on-success="handleUploadSuccess"
      :on-error="handleUploadError"
      :before-upload="beforeUpload"
      multiple
      :limit="1"
      :on-exceed="handleExceed"
      :file-list="fileList"
      accept=".zip"
    >
      <el-button size="small" type="primary">上传附件</el-button>
      <div slot="tip" class="el-upload__tip">只能上传zip文件,且不超过10MB</div>
    </el-upload>
    <div style="padding-bottom: 15px;" />
    <el-form-item>
      <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
      <el-button @click="resetForm('ruleForm')">重置</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import { mapGetters } from 'vuex'
import { postform } from '@/api/group.js'
import { getProfile } from '@/api/user.js'
export default {
  name: 'Application',
  computed: {
    ...mapGetters([
      'name'
    ]) },
  data() {
    return {
      fileList: [],
      ruleForm: {
        groupName: '',
        phone: '',
        gender: '',
        major: '',
        stuName: '',
        stuNumber: '',
        selfIntro: '',
        attachment: ''
      },
      stuName: '',
      stuNumber: '',
      majors: [],
      // dialogVisible2: false,
      // applicationSubmitted: false,
      // uploadAction: 'your_upload_endpoint', // 替换为你的后端文件上传接口
      rules: {
        // stuName: [
        //   { required: true, message: '请输入你的名字', trigger: 'blur' }
        // ],
        // stuNumber: [
        //   { required: true, message: '请输入你的学号', trigger: 'change' },
        //   { min: 8, max: 8, message: '学号长度必须为8位', trigger: 'blur' }
        // ],
        phone: [
          { required: true, message: '请输入你的手机号码', trigger: 'change' },
          { pattern: /^1[0-9]{10}$/, message: '请输入有效的手机号码', trigger: 'blur' }
        ],
        gender: [
          { required: true, message: '请选择性别', trigger: 'change' }
        ],
        selfIntro: [
          { required: true, message: '请填写自我陈述', trigger: 'blur' }
        ]
      }
    }
  },
  mounted() {
    this.ruleForm.groupName = this.$route.params.groupName;
    console.log(this.ruleForm.groupName)
    this.majors = this.loadAll();
  },
  created: function() {
    this.stuNumber = this.name
    getProfile(this.name)
      .then((response) => {
        this.stuName = response.data.student.userName
        this.ruleForm.major = response.data.student.major
        this.ruleForm.phone = response.data.student.phone
        console.log(response.data.student)
      })
      .catch((error) => {
        console.error(error)
      })
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.showSuccessMessage();
          this.ruleForm.stuName = this.stuName;
          this.ruleForm.stuNumber = this.stuNumber;
          postform(this.ruleForm).then(response => {
            console.log(response.data.student)
            this.$router.push({ name: 'Dashboard' })
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
    querySearch(queryString, cb) {
        var majors = this.majors;
        var results = queryString ? majors.filter(this.createFilter(queryString)) : majors;
        // 调用 callback 返回建议列表的数据
        cb(results);
    },
    createFilter(queryString) {
      return (major) => {
        return (major.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
      };
    },
    loadAll() {
        return [
          { "value": "软件工程学院" },
          { "value": "人工智能学院" },
          { "value": "微电子科学与技术学院" },
          { "value": "测绘与遥感技术学院" },
          { "value": "土木工程学院" },
          { "value": "大气科学学院" },
          { "value": "岭南学院" },
          { "value": "中国语言文学学院" },
          { "value": "历史学院" },
          { "value": "哲学学院" },
          { "value": "政治与公共事物管理学院" },
          { "value": "管理学院" },
          { "value": "外国语学院" },
          { "value": "化学学院" },
          { "value": "数学学院" },
          { "value": "马克思主义学院" },
          { "value": "药学院" },
          { "value": "护理学院" },
          { "value": "计算机学院" },
          { "value": "物理学院" },
          { "value": "电子与信息工程学院" },
          { "value": "国际金融学院" },
          { "value": "海洋科学学院" },
          { "value": "物理与天文学院" },
          { "value": "国际关系学院" },
          { "value": "商学院" },
          { "value": "理学院" },
          { "value": "材料学院" },
          { "value": "生态学院" },
          { "value": "农学院" },
          { "value": "智能工程学院" },
        ];
    },
    handleSelect(item) {
      console.log(item);
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
    },
    showSuccessMessage() {
      this.$message({
        message: '申请表提交成功!',
        type: 'success'
      })
    },
    handleRemove(file, fileList) {
      console.log(file, fileList)
    },
    handleExceed(files, fileList) {
      this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`)
    },
    beforeRemove(file, fileList) {
      console.log(file, fileList)
      return this.$confirm(`确定移除 ${file.name}？`)
    },
    handleUploadSuccess(response, file, fileList) {
      console.log(file, fileList)
      this.ruleForm.attachment = response.fileUrl
    },
    handleUploadError() {
      // 处理文件上传失败的情况
      this.applicationSubmitted = true // 文件上传失败后将标志位设置为 true，表示申请书已递交
      this.dialogVisible = true // 显示提示信息的对话框
    },
    beforeUpload(file) {
      if (file.size / 1024 / 1024 > 10) {
        this.$message.error('文件大小不能超10MB')
        return false
      }
      return true
    }
  }
}
</script>

