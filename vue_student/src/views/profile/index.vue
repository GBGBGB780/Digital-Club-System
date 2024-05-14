<template>
  <div class="page-container">
    <div class="content">
      <div style="margin: 20px;" />
      <el-form :label-position="right" label-width="80px" :model="profile">
        <el-form-item label="学号">
          <el-input v-model="profile.stunumber" :disabled="true" class="custom-data" />
        </el-form-item>
        <el-form-item label="姓名">
          <el-input v-model="profile.stuname" :disabled="true" class="custom-data" />
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="profile.email" :disabled="true" class="custom-data" />
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="profile.phone" :disabled="true" class="custom-data" />
        </el-form-item>
      </el-form>
      <el-form ref="newForm" :model="newForm" status-icon :rules="rules" label-width="100px" class="demo-ruleForm">
        <el-form-item label="新手机号" prop="phone">
          <el-input v-model="newForm.phone" type="phone" maxlength="11" class="custom-input" />
          <span style="margin-right: 20px;" /> <!-- 添加空白元素 -->
          <el-button type="primary" @click="submitPhone('newForm')">更新</el-button>
        </el-form-item>
      </el-form>
      <el-form ref="ruleForm" :model="ruleForm" status-icon :rules="rules" label-width="100px" class="demo-ruleForm">
        <el-form-item label="修改密码" label-width="180px" />
        <el-form-item label="新密码" prop="pass">
          <el-input v-model="ruleForm.pass" type="password" autocomplete="off" class="custom-data" />
        </el-form-item>
        <el-form-item label="确认密码" prop="checkPass">
          <el-input v-model="ruleForm.checkPass" type="password" autocomplete="off" class="custom-data" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitPassword('ruleForm')">提交</el-button>
          <el-button @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { modifypassword, getProfile, modifyphone } from '@/api/user.js'
import router from '@/router'
export default {
  name: 'Profile',
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
      ruleForm: {
        pass: '',
        checkPass: ''
      },
      rules: {
        pass: [
          { required: true, validator: validatePass, trigger: 'blur' }
        ],
        checkPass: [
          { required: true, validator: validatePass2, trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '请输入新的手机号', trigger: 'change' },
          { pattern: /^1[0-9]{10}$/, message: '请输入有效的手机号码', trigger: 'blur' }
        ]
      },
      profile: {
        stunumber: '',
        stuname: '',
        email: '',
        phone: ''
      },
      newForm: {
        phone: ''
      }
    }
  },
  created: function() {
    this.profile.stunumber = this.name
    getProfile(this.name)
      .then((response) => {
        this.profile.stuname = response.data.student.stuname
        this.profile.email = response.data.student.email
        this.profile.phone = response.data.student.phone
      })
      .catch((error) => {
        console.error(error)
      })
  },
  methods: {
    submitPassword(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.showSuccessUpdatePassword()
          modifypassword(this.name, this.ruleForm.pass).then(response => {
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
      await this.$store.dispatch('user/logout')
      this.$router.push(`/login?redirect=${this.$route.fullPath}`)
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
    },
    submitPhone(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$message({
            message: '手机号修改成功',
            type: 'success'
          })
          setTimeout(() => {
            modifyphone(this.name, this.newForm.phone)
              .then((response) => {
                console.log(response.data)
                router.go() // 刷新当前页面
              })
              .catch((error) => {
                console.error(error)
              })
          }, 500) // 1秒钟延迟
        } else {
          console.log('error submit!!')
          return false
        }
      })
    }
  }
}
</script>

<style>
.custom-input {
  width: 200px;
}
.custom-data {
  width: 300px;
}
.if-page-container {  /* 如果要居中把if去掉，然后把content给删了*/
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh; /* 设置最小高度为视口高度，以确保页面在内容较少时也能居中 */
}
.page-container {
  position: relative;
  height: 100vh;
}
.content {
  position: absolute;
  top: 20px;
  left: 100px;
}
</style>
