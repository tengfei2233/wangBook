<template>
  <div id="container">
    <side-bar class="sidebar-container" />
    <router-view />
  </div>
</template>

<script>
import sideBar from "./sideBar";
export default {
  name: "layout",
  components: {
    sideBar
  }
};
</script>

<style lang="less" scoped>
@import "@/assets/styles/sidebar.less";

#container {
  position: relative;
  height: 100%;
  width: 100%;
  &:after {
    content: "";
    display: table;
    clear: both;
  }
}
</style>