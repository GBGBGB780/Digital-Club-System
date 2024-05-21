<template>
  <div class="full-screen-container">
    <vue-particles
      class="particles-bg"
      color="#39AFFD"
      :particle-opacity="0.7"
      :particles-number="100"
      shape-type="star"
      :particle-size="4"
      lines-color="#FFFFFF"
      :lines-width="1"
      :line-linked="true"
      :line-opacity="0.4"
      :lines-distance="150"
      :move-speed="3"
      :hover-effect="true"
      hover-mode="grab"
      :click-effect="true"
      click-mode="repulse"
    />
    <div class="login-container">
      <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form" auto-complete="on" label-position="left">

        <div class="title-container">
          <h3 class="title">社团管理员登录</h3>
        </div>

        <el-form-item prop="username">
          <span class="svg-container">
            <svg-icon icon-class="user" />
          </span>
          <el-input
            ref="username"
            v-model="loginForm.username"
            placeholder="请输入你的社团名"
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
            v-model="loginForm.password"
            :type="passwordType"
            placeholder="请输入你的密码"
            name="password"
            tabindex="2"
            auto-complete="off"
            @keyup.enter.native="handleLogin"
          />
          <span class="show-pwd" @click="showPwd">
            <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
          </span>
        </el-form-item>

        <el-button :loading="loading" type="primary" style="width:100%;margin-bottom:30px;" @click.native.prevent="handleLogin">Login</el-button>

      </el-form>
    </div>
    
    <el-dialog :visible.sync="dialogVisible" title="请选择您要管理的社团" width="30%" :append-to-body="true">
      <div id="choose" style="text-align:center;margin-top: 30px;margin-bottom: 30px;">
        <!-- 循环按钮 -->
        <el-button v-for="(item,index) in choosebtn" type="primary" :key="'choosebtn'+index" style="width: 80%;height: 60px;margin:4px;" @click="chooseclub(index)" :title="item">
          {{ item }}
        </el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import { managerlogin,managedgroup } from '@/api/user.js'

export default {
  name: 'Login',
  data() {
    const validateUsername = (rule, value, callback) => {
      callback()
    }
    const validatePassword = (rule, value, callback) => {
      if (value.length < 6) {
        callback(new Error('密码长度不能少于6位'))
      } else {
        callback()
      }
    }
    return {
      loginForm: {
        username: '',
        password: ''
      },
      loginRules: {
        username: [{ required: true, trigger: 'blur', validator: validateUsername }],
        password: [{ required: true, trigger: 'blur', validator: validatePassword }]
      },
      loading: false,
      passwordType: 'password',
      redirect: undefined,
      dialogVisible: false,
      choosebtn: [],   
      token:0,
      cid:[],
      // club:[{cid,cname}],
    }
  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect
      },
      immediate: true,
      dialogVisible: function(newVal,oldVal){
        if(newVal = false)
        {
          this.choosebtn=[];
          console.log("test")
        }
      }
    }
  },
  methods: {
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
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true
          managerlogin(this.loginForm.username.trim(), this.loginForm.password).then(response => {
            this.loading = false
            const { data } = response
            this.token=data.token
            // vuex
            managedgroup(this.loginForm.username.trim()).then(res =>{
              // console.log(res.data.items[0].username)
              for(var i=0; i<res.data.items.length; i++)
              {
                this.addButton(i,res.data.items[i].username,res.data.items[i].name)
              }
            })
            // this.dialogVisible = true 如果放在这里的话就会导致打开时还未渲染，因为Vue是异步进行
          }
        ).catch(() => {
            this.loading = false
        })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    chooseclub(index){
      var clubid=this.cid[index];
      // vuex
      this.$store.commit('chooseclub',clubid);
      this.$store.dispatch('user/managerlogin', this.token)
      this.$router.push({ path: this.redirect || '/' })
    },
    
    addButton(i,cid,cname){
      var display = "社团id："+cid+"社团名: "+cname;
      this.cid[i]=cid; 
      this.choosebtn[i]=display;
      // console.log(this.choosebtn[i])
      this.dialogVisible = true
    },
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
  .login-container .el-input input {
    color: $cursor;
  }
}
.full-screen-container {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
}

.particles-bg {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
}
/* reset element-ui css */
.login-container {
  .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;

    input {
      background: transparent;
      border: 0px;
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

.login-container {
  min-height: 100%;
  width: 100%;
  background-color: $bg;
  overflow: hidden;

  .login-form {
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
