<template>
  <div class="full-screen-container">
    <vue-particles
      class="particles-bg"
      color="#39AFFD"
      :particle-opacity="0.7"
      :particles-number="100"
      shape-type="circle"
      :particle-size="4"
      lines-color="#8DD1FE"
      :lines-width="1"
      :line-linked="true"
      :line-opacity="0.4"
      :lines-distance="150"
      :move-speed="3"
      :hover-effect="true"
      hover-mode="grab"
      :click-effect="true"
      click-mode="push"
    />
    <div class="login-container">
      <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form" auto-complete="on" label-position="left">
        <div class="title-container">
          <h3 class="title">社团招新申请者登录</h3>
        </div>

        <el-form-item prop="userName">
          <span class="svg-container">
            <svg-icon icon-class="user" />
          </span>
          <el-input
            ref="userName"
            v-model="loginForm.userName"
            placeholder="请输入你的学号"
            name="userName"
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
        <div class="button-container">
        <el-button :loading="loading" type="primary" style="width:100%;margin-bottom:30px;" @click.native.prevent="handleLogin">Login</el-button>
        <el-button type="primary" style="width:100%;margin-bottom:30px;" @click="showRegister">Register</el-button>
        <div class="tips" style="float:right;"><el-link type="white" @click="retrievePWD">忘记密码</el-link></div>
        </div> 
      </el-form>
    </div>

    <el-dialog :visible.sync="senddialogVisible" title="找回密码" width="30%" :append-to-body="true" @close="retrievePWDDialog=false,reset()" >
      <div style="font-size: 20px;">{{ this.tips }}</div>
      <div style="display: flex;margin-top: 10px;">
        <el-input style="width: 300px;margin-right: 10px;" v-model="stunum"></el-input>
        <el-button @click="verify" :loading="retrievepwdloading">{{ this.sendbtn }}</el-button>
      </div>
    </el-dialog>

    <el-dialog :visible.sync="verifydialogVisible" title="找回密码" width="30%" :append-to-body="true" @close="retrievePWDDialog=false,reset()" >
      <div style="font-size: 20px;">{{ this.tips }}</div>
      <div style="display: flex;margin-top: 10px;">
        <el-input style="width: 300px;margin-right: 10px;" v-model="vcode"></el-input>
        <el-button @click="verify" :loading="retrievepwdloading">{{ this.sendbtn }}</el-button>
        <el-button @click="vercode">验证</el-button>
      </div>
    </el-dialog>

    <el-dialog :visible.sync="resetdialogVisible" title="找回密码" width="30%" :append-to-body="true" @close="resetPWDDialog=false,reset()" >
      <div style="font-size: 20px;">{{ this.tips }}</div>
      <div>请输入新密码
        <el-input style="width: 300px;margin-top: 10px;" v-model="pas1"></el-input>
      </div>
      <div>请重复新密码
        <el-input style="width: 300px;margin-top: 10px;margin-bottom: 10px;" v-model="pas2"></el-input>
      </div>
      <div>
        <el-button @click="modifyPassword">修改密码</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { validuserName } from '@/utils/validate'
import { modifyPassword, verifycode, verifyemail,modifypas } from '@/api/user.js'
import { MessageBox } from 'element-ui'

export default {
  name: 'Login',
  data() {
    const validateuserName = (rule, value, callback) => {
      if (!validuserName(value)) {
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
      loginForm: {
        userName: '',
        password: ''
      },
      loginRules: {
        userName: [{ required: true, trigger: 'blur', validator: validateuserName }],
        password: [{ required: true, trigger: 'blur', validator: validatePassword }]
      },
      loading: false,
      passwordType: 'password',
      redirect: undefined,
      tips: '请输入您的学号',
      senddialogVisible: false,
      resetdialogVisible:false,
      verifydialogVisible:false,
      sendbtn:'发送验证码',
      retrievepwdloading:false,
      stunum:'',
      vcode:'',
      pas1:'',
      pas2:'',
      mail:''
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
    showRegister() {
      console.info('showRegister() called')
      this.$router.push({ path: '/register' })
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
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true
          // vuex
          this.$store.dispatch('user/login', this.loginForm).then(() => {
            console.info(this.redirect || '/')
            this.$router.push({ path: this.redirect || '/' })
            this.loading = false
          }).catch(() => {
            this.loading = false
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    retrievePWD() {
      this.senddialogVisible=true;
    },
    verify() {      
      if(this.stunum!='')
      {
          verifyemail(this.stunum).then((response) => {
          // console.log(response.data.email)
          const _this =this;//setInterval中的this指向问题
          this.retrievepwdloading=true; //按钮不可重复点击
          var time = 60;//定义时间变量 60s
          var timer = null;//定义定时器
          timer = setInterval(function(){
            if(time==0){
              _this.sendbtn="重新获取验证码";                          
              _this.retrievepwdloading=false;            
              clearInterval(timer);//清除定时器
            }else{
              _this.sendbtn=time+"秒后重新获取";                     
              time--;
            }     
          },1000)
          if(response.code=200)
          {
            this.retrievepwdloading=false
            this.mail=response.data.email
            this.resetdialogVisible=false
            this.tips='请前往您的企业邮箱并在此输入您的验证码'
            this.retrievepwdloading=true
            this.verifydialogVisible=true
          }
        })
        .catch(error => {
          console.error(error)
        })
      }
      else
      {
        this.$message({type: 'info',message: '请输入您的学号'});
      }
    },
    vercode() {
      verifycode(this.mail,this.vcode).then((res) => {
              // console.log(res)
              this.verifydialogVisible=false
              this.tips='请修改您的密码'
              this.resetdialogVisible=true
      }).catch(err => {console.error(err)})
    },
    modifyPassword() {
      if(this.pas1==''||this.pas2=='')
      this.$message({type: 'info',message: '请输入新密码'});
      else if(this.pas1!=this.pas2)
      this.$message({type: 'info',message: '两次密码不一致'});
      else
      modifypas(this.stunum,pas1).then((res) => {
              // console.log(res)
              this.$message({type: 'success',message: '新密码修改成功'});
      }).catch(err => {console.error(err)})
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

.button-container {
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
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
