<template>
  <div id="container">
    <div id="login-container">
      <p id="title">用户登录</p>
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="账号密码登录" name="first">

        </el-tab-pane>
        <el-tab-pane label="手机号登录" name="second">
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script>
export default {
  name: "login",
  data() {
    return {
      activeName: "first"
    };
  },
  methods:{
    handleClick(){

    }
  }
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
  height: 380px;
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
</style>