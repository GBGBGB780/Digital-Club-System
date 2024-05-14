<template>
  <div class="register-container">
    <el-form ref="registerForm" :model="registerForm" :rules="registerRules" class="register-form" auto-complete="on" label-position="left">

      <div class="title-container">
        <h3 class="title">Register Form</h3>
      </div>

      <el-form-item prop="username">
        <span class="svg-container">
          <svg-icon icon-class="user" />
        </span>
        <el-input
          ref="username"
          v-model="registerForm.username"
          placeholder="请输入你的学号"
          name="username"
          type="text"
          tabindex="1"
          auto-complete="off"
        />
      </el-form-item>

      <el-form-item prop="password">
        <span class="svg-container">
          <svg-icon icon-class="password" />
        </span>
        <el-input
          :key="passwordType"
          ref="password"
          v-model="registerForm.password"
          :type="passwordType"
          placeholder="请输入你的密码"
          name="password"
          tabindex="2"
          auto-complete="off"
        />
        <!-- @keyup.enter.native="handleLogin" -->
        <span class="show-pwd" @click="showPwd">
          <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
        </span>
      </el-form-item>

      <el-form-item prop="email">
        <!-- <span class="svg-container">
          <svg-icon icon-class="email" />
        </span> -->
        <el-input
          ref="email"
          v-model="registerForm.email"
          placeholder="请输入你的邮箱"
          name="email"
          type="text"
          tabindex="3"
          auto-complete="off"
        />
      </el-form-item>

      <el-form-item prop="valicode">
        <!-- <span class="svg-container">
          <svg-icon icon-class="valicode" />
        </span> -->
        <el-input
          ref="valicode"
          v-model="registerForm.valicode"
          placeholder="请输入验证码"
          name="valicode"
          type="text"
          tabindex="4"
          auto-complete="off"
        />
      </el-form-item>

      <el-row>
        <el-button
          type="primary"
          style="width:100%;margin-bottom:30px;"
          @click="returnLogin"
        >Back To Login</el-button>

        <el-button
          type="primary"
          style="width:100%;margin-bottom:30px;"
          @click="EmailConfirm"
        >Validate Email</el-button>

        <el-button
          type="primary"
          style="width:100%;margin-bottom:30px;"
          @click="handleRegister"
        >Register</el-button>

      </el-row>
    </el-form>
  </div>
</template>

<script>
import { validUsername } from '@/utils/validate'
import { validateEmail, register } from '@/api/user.js'

export default {
  name: 'Login',
  data() {
    const validateUsername = (rule, value, callback) => {
      if (!validUsername(value)) {
        callback(new Error('你的学号必须为8位数字'))
      } else {
        callback()
      }
    }
    const validatePassword = (rule, value, callback) => {
      if (value.length < 6) {
        callback(new Error('密码长度不能少于6位'))
      } else {
        callback()
      }
    }
    return {
      registerForm: {
        username: '',
        password: '',
        email: '',
        valicode: ''
      },
      registerRules: {
        username: [{ required: true, trigger: 'blur', validator: validateUsername }],
        password: [{ required: true, trigger: 'blur', validator: validatePassword }],
        email: [{ required: true, trigger: 'blur' }]
      },
      loading: false,
      passwordType: 'password',
      redirect: undefined
    }
  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect
      },
      immediate: true
    }
  },
  methods: {
    returnLogin() {
      this.redirect = '/login'
      this.$router.push({ path: this.redirect || '/' })
    },
    showPwd() {
      if (this.passwordType === 'password') {
        this.passwordType = ''
      } else {
        this.passwordType = 'password'
      }
      this.$nextTick(() => {
        this.$refs.password.focus()
      })
    },
    EmailConfirm() {
      this.$refs.registerForm.validate(valid => {
        if (valid) {
          // vuex
          console.error(this.registerForm.email)
          validateEmail(this.registerForm.email).then(response => {
            this.$message({
              message: '成功将验证码发送到邮箱!',
              type: 'success'
            })
            console.log(response.data)
          }).catch(error => {
            console.error(error)
          })
        } else {
          console.log('error comfirm!!')
          return false
        }
      })
    },

    handleRegister() {
      this.$refs.registerForm.validate(valid => {
        if (valid) {
          // vuex
          console.error(this.registerForm)
          register(this.registerForm).then(response => {
            this.$message({
              message: '注册成功',
              type: 'success'
            })
            console.log(response.data)
            this.$router.push('/login')
          }).catch(error => {
            console.log(error)
          })
        } else {
          console.log('error register!!')
          return false
        }
      })
    }
  }
}
</script>

<style lang="scss">
/* 修复input 背景不协调 和光标变色 */
/* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

$bg:#283443;
$light_gray:#fff;
$cursor: #fff;

@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .register-container .el-input input {
    color: $cursor;
  }
}

/* reset element-ui css */
.register-container {
  .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;

    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      height: 47px;
      caret-color: $cursor;

      &:-webkit-autofill {
        box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: $cursor !important;
      }
    }
  }

  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    background: rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    color: #454545;
  }
}
</style>

<style lang="scss" scoped>
$bg:#2d3a4b;
$dark_gray:#889aa4;
$light_gray:#eee;

.register-container {
  min-height: 100%;
  width: 100%;
  background-color: $bg;
  overflow: hidden;

  .register-form {
    position: relative;
    width: 520px;
    max-width: 100%;
    padding: 160px 35px 0;
    margin: 0 auto;
    overflow: hidden;
  }

  .tips {
    font-size: 14px;
    color: #fff;
    margin-bottom: 10px;

    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }

  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }

  .title-container {
    position: relative;

    .title {
      font-size: 26px;
      color: $light_gray;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
    }
  }

  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }
}
</style>
