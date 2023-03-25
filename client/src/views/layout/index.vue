<template>
  <div style="height: 100%">
    <el-container style="height: 100%;">
      <el-header id="header" height="70px">
        <top-bar />
      </el-header>
      <el-main>
        <router-view />
      </el-main>
      <el-footer id="footer">
        <p>~欢迎来到王清泽的二手图书售卖王~</p>
      </el-footer>
    </el-container>
  </div>
</template>

<script>
import TopBar from "@/views/layout/topBar.vue";
export default {
  name: "layout",
  components: {
    TopBar
  }
};
</script>

<style scoped>
#header {
  padding: 0;
  overflow: hidden;
  box-shadow: 0 4px 3px #e6e6e6;
  position: relative;
  top: 0;
  left: 0;
}
#footer {
  height: 50px !important;
  padding: 0;
  background-image: linear-gradient(to bottom right, #dddcde, #fcfbfc);
  overflow: hidden;
  position: relative;
  bottom: 0;
  left: 0;
}
#footer > p {
  margin: 0;
  height: 100%;
  width: 100%;
  line-height: 50px;
  text-align: center;
}
</style>