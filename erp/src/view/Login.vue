<template>
  <div class="login">
    <el-form
      :rules="rules"
      class="login-container"
      label-position="left"
      ref="LoginForm"
      :model="loginForm"
      label-width="0px"
      v-loading="loading"
    >
      <h3 class="login_title">物料生产管理erp系统 V1.0</h3>
      <el-form-item prop="username">
        <el-input type="text" v-model="loginForm.username" auto-complete="off" placeholder="账号"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password" v-model="loginForm.password" auto-complete="off" placeholder="密码"></el-input>
      </el-form-item>
      <el-checkbox class="login_remember" v-model="checked" label-position="left">记住密码</el-checkbox>
      <el-form-item style="width: 100%">
        <el-button type="primary" style="width: 100%" @click="submitClick">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
  import {postKeyValueRequest} from "../utils/api";

  export default {
    data() {
      return {
        rules: {
          username: [{required: true, message: "哎呀，请先输入用户名！", trigger: "blur"}],
          password: [{required: true, message: "密码还未输入呢~", trigger: "blur"}]
        },
        checked: true,
        loginForm: {
          username: "admin",
          password: "123456"
        },
        loading: false
      };
    },
    methods: {
      submitClick() {
        this.$refs.LoginForm.validate((valid) => {
          if (valid) {
            this.loading = true;
            postKeyValueRequest('/doLogin', this.loginForm).then(resp => {
              this.loading = false;
              if (resp) {
                window.sessionStorage.setItem("user", JSON.stringify(resp.obj));
                this.$router.replace('/home');
              }
            })
          } else {
            this.$message.error('您好像还遗漏了什么呢？！请补充完整哦！');
            return false;
          }
        });
      }
    }
  };
</script>
<style>
  .login {
    margin: -8px;
    width: 100%;
    height: 100%;
    /* 设置overflow: hidden;可以防止div溢出 */
    overflow: hidden;
    /* 设置position: fixed;可以铺满屏幕 */
    position: fixed;
    background: url(../assets/images/background.jpg);
    background-repeat: no-repeat;
    background-size: cover;
  }

  .login-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 180px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }

  .login_title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }

  .login_remember {
    margin: 0px 0px 35px 0px;
    text-align: left;
  }
</style>
