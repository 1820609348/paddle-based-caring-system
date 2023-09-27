<template>
  <div class="main">
    <!--登陆框-->
    <div class="container">
      <div class="form-box" :style="boxAction">
        <!-- 注册 -->
        <div :class="`register-box ${regState}`">
          <h1>register</h1>
          <input type="text" placeholder="用户名" v-model="regForm.username">
          <input type="email" placeholder="邮箱" v-model="regForm.email">
          <input type="password" placeholder="密码" v-model="regForm.secret">
          <input type="password" placeholder="确认密码" v-model="regForm.secretAgain">
          <button @click="register">注册</button>
        </div>
        <!-- 登录 -->
        <div :class="`login-box ${loginState}`">
          <h1>login</h1>
          <input type="text" placeholder="用户名" v-model="loginForm.username">
          <input type="password" placeholder="密码" v-model="loginForm.secret">
          <button @click="login">登录</button>
        </div>
      </div>
      <!--侧页-->
      <div class="con-box left">
        <h2>急救中心<span>管理系统</span></h2>
        <p>系统<span>正在开发</span>中</p>
        <p>已有账号</p>
        <button id="login" @click="gotoLogin">去登录</button>
      </div>
      <div class="con-box right">
        <h2>急救中心<span>管理系统</span></h2>
        <p>系统<span>正在开发</span>中</p>
        <p>没有账号？</p>
        <button id="register" @click="gotoRegister">去注册</button>
      </div>
    </div>
  </div>
</template>
<script>
import {postLogin} from '@/api'
import {backgroundLin} from "@/utils/geometricConnection";

export default {
  data() {
    return {
      regState: 'hidden',
      loginState: '',
      boxAction: '',
      loginForm: {
        username: "",
        secret: ""
      },
      regForm: {
        username: "",
        email: "",
        secret: "",
        secretAgain: ""
      },
    }
  },
  mounted() {
    //启动粒子特效
    backgroundLin();
  },
  methods: {
    login() {
      postLogin(this.loginForm, 'login').then(value => {
        let status = value.data
        if (status) {
          this.$message.success("登录成功！")
          sessionStorage.setItem("status", "true")//一次登录则设立缓存
          this.$router.push("/main/realtime-statistics")
        } else {
          this.$message.error("用户名或密码错误！")
        }
      }).catch(error => {
        console.log(error)
      })
    },
    register() {
      let data = {
        "username": this.regForm.username,
        "email": this.regForm.email,
        "secret": this.regForm.secret
      }
      postLogin(data, 'reg').then(value => {
        console.log(value)
        let status = value.data
        if (status) {
          this.$message.success("注册成功！")
          this.clearRegForm()
          this.gotoLogin()
        } else {
          this.$message.error("用户名已存在！")
        }
      }).catch(error => {
        console.log(error)
      })
    },
    gotoRegister() {
      this.boxAction = 'transform:translateX(80%)';
      this.loginState = 'hidden';
      this.regState = '';
    },
    gotoLogin() {
      this.boxAction = 'transform:translateX(0%)';
      this.loginState = '';
      this.regState = 'hidden';
    },
    //清除regform的内容
    clearRegForm() {
      for (let key in this.regForm) {
        this.regForm[key] = ""
      }
    }
  }
}
</script>
<style scoped lang="less">
* {
  /* 初始化 */
  margin: 0;
  padding: 0;
}

.main {
  /* 100%窗口高度 */
  height: 100vh;
  /* 弹性布局 水平+垂直居中 */
  display: flex;
  justify-content: center;
  align-items: center;
  /* 渐变背景 */
  //background: linear-gradient(200deg, #f3e7e9, #e3eeff);//在粒子特效处已经使用
}

.container {
  background-color: #fff;
  width: 650px;
  height: 415px;
  border-radius: 5px;
  /* 阴影 */
  box-shadow: 5px 5px 5px rgba(0, 0, 0, 0.1);
  /* 相对定位 */
  position: relative;
}

.form-box {
  /* 绝对定位 */
  position: absolute;
  top: -10%;
  left: 5%;
  background-color: #d3b7d8;
  width: 320px;
  height: 500px;
  border-radius: 5px;
  box-shadow: 2px 0 10px rgba(0, 0, 0, 0.1);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 2;
  /* 动画过渡 加速后减速 */
  transition: 0.5s ease-in-out;
}

.register-box, .login-box {
  /* 弹性布局 垂直排列 */
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
}

.hidden {
  display: none;
  transition: 0.5s;
}

h1 {
  text-align: center;
  margin-bottom: 25px;
  /* 大写 */
  text-transform: uppercase;
  color: #fff;
  /* 字间距 */
  letter-spacing: 5px;
}

input {
  background-color: transparent;
  width: 70%;
  color: #fff;
  border: none;
  /* 下边框样式 */
  border-bottom: 1px solid rgba(255, 255, 255, 0.4);
  padding: 10px 0;
  text-indent: 10px;
  margin: 8px 0;
  font-size: 14px;
  letter-spacing: 2px;
}

input::placeholder {
  color: #fff;
}

input:focus {
  color: #a262ad;
  outline: none;
  border-bottom: 1px solid #a262ad80;
  transition: 0.5s;
}

input:focus::placeholder {
  opacity: 0;
}

.form-box button {
  width: 70%;
  margin-top: 35px;
  background-color: #f6f6f6;
  outline: none;
  border-radius: 8px;
  padding: 13px;
  color: #a262ad;
  letter-spacing: 2px;
  border: none;
  cursor: pointer;
}

.form-box button:hover {
  background-color: #a262ad;
  color: #f6f6f6;
  transition: background-color 0.5s ease;
}

.con-box {
  width: 50%;
  /* 弹性布局 垂直排列 居中 */
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  /* 绝对定位 居中 */
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
}

.con-box.left {
  left: -2%;
}

.con-box.right {
  right: -2%;
}

.con-box h2 {
  color: #8e9aaf;
  font-size: 25px;
  font-weight: bold;
  letter-spacing: 3px;
  text-align: center;
  margin-bottom: 4px;
}

.con-box p {
  font-size: 12px;
  letter-spacing: 2px;
  color: #8e9aaf;
  text-align: center;
}

.con-box span {
  color: #d3b7d8;
}

.con-box img {
  width: 150px;
  height: 150px;
  opacity: 0.9;
  margin: 40px 0;
}

.con-box button {
  margin-top: 3%;
  background-color: #fff;
  color: #a262ad;
  border: 1px solid #d3b7d8;
  padding: 6px 10px;
  border-radius: 5px;
  letter-spacing: 1px;
  outline: none;
  cursor: pointer;
}

.con-box button:hover {
  background-color: #d3b7d8;
  color: #fff;
}
</style>