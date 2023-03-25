<template>
  <div id="container">
    <div id="login-container">
      <p id="title">后台管理登录</p>
      <el-form ref="loginForm" :model="loginForm" :rules="rules">
        <el-form-item label prop="phone">
          <el-input
            v-model="loginForm.username"
            placeholder="请输入用户名"
            :maxlength="11"
            show-word-limit
            clearable
            prefix-icon="el-icon-user"
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
        <el-form-item>
          <el-button type="primary" style="width: 100%;margin-top:35px" @click="login">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "login",
  data() {
    return {
      loginForm: {
        username: "",
        password: ""
      },
      rules: {
        username: [
          {
            required: true,
            message: "请输入用户名",
            trigger: "blur"
          }
        ],
        password: [
          {
            required: true,
            message: "请输入密码",
            trigger: "blur"
          }
        ]
      }
    };
  },
  methods: {
    login() {}
  }
};
</script>

<style scoped>
#container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  background-image: url("https://blog-1306275892.cos.ap-shanghai.myqcloud.com/wang/manage-login-cover.png");
  background-size: cover;
}
#login-container {
  width: 380px;
  height: 300px;
  padding: 0 20px;
  position: absolute;
  left: 50%;
  top: 40%;
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
</style>