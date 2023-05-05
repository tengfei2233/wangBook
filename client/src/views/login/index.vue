<template>
  <div id="container">
    <div id="login-container">
      <p id="title">用户登录</p>
      <el-tabs v-model="activeName">
        <el-tab-pane label="账号密码登录" name="first">
          <el-form ref="loginForm" :model="loginForm" :rules="rules1">
            <el-form-item label prop="username">
              <el-input
                v-model="loginForm.username"
                placeholder="请输入用户名/手机号"
                :maxlength="11"
                show-word-limit
                clearable
                prefix-icon="el-icon-mobile"
              ></el-input>
            </el-form-item>
            <el-form-item label prop="password">
              <el-input
                v-model="loginForm.password"
                placeholder="请输入密码"
                clearable
                prefix-icon="el-icon-lock"
                show-password
              ></el-input>
            </el-form-item>
            <el-form-item label prop="captcha">
              <el-input
                v-model="loginForm.captcha"
                placeholder="请输入验证码"
                show-word-limit
                clearable
                prefix-icon="el-icon-picture-outline"
                style="width: 60%"
              ></el-input>
              <div class="captcha-code">
                <img
                  :src="captchaImg"
                  @click="getCaptcha"
                  class="captcha-code-img"
                />
              </div>
            </el-form-item>
            <el-form-item style="margin-bottom: 0px !important">
              <el-button type="primary" style="width: 100%" @click="login"
                >登录</el-button
              >
            </el-form-item>
          </el-form>
        </el-tab-pane>
        <el-tab-pane label="手机号登录" name="second">
          <el-form ref="phoneLoginForm" :model="phoneLoginForm" :rules="rules2">
            <el-form-item label prop="phone" style="margin-top: 31.4px">
              <el-input
                v-model="phoneLoginForm.phone"
                placeholder="请输入手机号"
                :maxlength="11"
                show-word-limit
                clearable
                prefix-icon="el-icon-mobile"
              ></el-input>
            </el-form-item>
            <el-form-item label prop="phoneCode">
              <el-input
                v-model="phoneLoginForm.phoneCode"
                placeholder="请输入验证码"
                show-word-limit
                clearable
                prefix-icon="el-icon-picture-outline"
                style="width: 50%"
              ></el-input>
              <div class="phone-code">
                <el-button
                  type="primary"
                  class="btn"
                  :disabled="phoneCodeBtn.disabled"
                  @click="getPhoneCode"
                  >{{ phoneCodeBtn.msg }}</el-button
                >
              </div>
            </el-form-item>
            <el-form-item style="margin-bottom: 0px !important">
              <el-button
                type="primary"
                style="width: 100%; margin-top: 33px"
                @click="phoneLogin"
                >登录</el-button
              >
            </el-form-item>
          </el-form>
        </el-tab-pane>
      </el-tabs>
      <el-button type="text" class="register-btn" @click="openRegister"
        >注册账号？</el-button
      >
    </div>
    <register :visible.sync="registerOpen" />
  </div>
</template>

<script>
import { $getCaptcha, $login, $getCode, $phoneLogin } from "@/api/login";
import Register from "@/components/Register";
import { setToken } from "@/utils/auth";
export default {
  name: "login",
  components: {
    Register,
  },
  data() {
    return {
      activeName: "first",
      captchaImg: "",
      loginForm: {
        username: "",
        password: "",
        captcha: "",
        uuid: "",
      },
      phoneLoginForm: {
        phone: "",
        phoneCode: "",
      },
      rules1: {
        username: [
          {
            required: true,
            message: "请输入用户名/手机号",
            trigger: "blur",
          },
        ],
        password: [
          {
            required: true,
            message: "请输入密码",
            trigger: "blur",
          },
        ],
        captcha: [
          {
            required: true,
            message: "请输入验证码",
            trigger: "blur",
          },
        ],
      },
      rules2: {
        phone: [
          {
            required: true,
            message: "请输入手机号",
            trigger: "blur",
          },
          {
            pattern: /^1(3|4|5|7|8|9)\d{9}$/,
            message: "手机号格式错误",
            trigger: "blur",
          },
        ],
        phoneCode: [
          {
            required: true,
            message: "请输入验证码",
            trigger: "blur",
          },
        ],
      },
      phoneCodeBtn: {
        disabled: false,
        msg: "获取验证码",
      },
      registerOpen: false,
    };
  },
  created() {
    this.getCaptcha();
  },
  methods: {
    login() {
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          $login(this.loginForm).then((res) => {
            setToken(res.data.token);
            this.$notify.success(res.msg);
            this.$router.push({ path: "/home" });
          });
        }
      });
    },
    phoneLogin() {
      this.$refs.phoneLoginForm.validate((valid) => {
        if (valid) {
          $phoneLogin(this.phoneLoginForm).then((res) => {
            setToken(res.data.token);
            this.$notify.success(res.msg);
            this.$router.push({ path: "/home" });
          });
        }
      });
    },
    getCaptcha() {
      $getCaptcha().then((res) => {
        this.loginForm.uuid = res.data.uuid;
        this.captchaImg = res.data.captcha;
      });
    },
    getPhoneCode() {
      const phone = this.phoneLoginForm.phone;
      const isPhone = phone != "" && /^1(3|4|5|7|8|9)\d{9}$/.test(phone);
      if (isPhone) {
        let time = 60;
        $getCode(phone).then((res) => {
          this.$message.success({
            message: "验证码为：" + res.data + "，测试使用，有效期十分钟",
            duration: 3000,
          });
        });
        let interval = window.setInterval(() => {
          this.phoneCodeBtn.msg = "请" + time + "后再获取";
          this.phoneCodeBtn.disabled = true;
          time--;
          if (time <= 0) {
            this.phoneCodeBtn.msg = "获取验证码";
            this.phoneCodeBtn.disabled = false;
            window.clearInterval(interval);
          }
        }, 1000);
      } else {
        this.$notify.error("请输入正确的手机号");
      }
    },
    openRegister() {
      this.registerOpen = true;
    },
  },
};
</script>

<style scoped>
#container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  background-image: url("https://blog-1306275892.cos.ap-shanghai.myqcloud.com/wang/user-login-cover.png");
  background-size: cover;
}
#login-container {
  width: 380px;
  height: 400px;
  padding: 0 20px;
  position: absolute;
  left: 50%;
  top: 35%;
  text-align: center;
  transform: translate(-50%, -50%);
  background-color: rgba(255, 255, 255, 0.95);
  border-radius: 5px;
}
#title {
  font-size: 20px;
  color: #676767;
  font-weight: 700;
}

::v-deep .el-tabs__nav-scroll {
  width: 55%;
  margin: 0 auto;
}

.captcha-code {
  width: 40%;
  height: 40px;
  float: right;
}
.captcha-code img {
  cursor: pointer;
  vertical-align: middle;
}
.captcha-code-img {
  height: 40px;
}

.phone-code {
  width: 50%;
  height: 40px;
  float: right;
}

.phone-code > .btn {
  margin-left: 10px;
  width: 90%;
}

.register-btn {
  position: absolute;
  right: 30px;
  text-decoration: underline;
}
</style>