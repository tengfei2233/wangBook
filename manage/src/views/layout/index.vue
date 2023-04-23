<template>
  <div :class="classObj" id="container" style="--current-color:#409EFF;">
    <side-bar class="sidebar-container" />
    <div class="main-container">
      <div class="fixed-header">
        <nav-bar />
        <!-- <tags-view v-if="needTagsView" /> -->
      </div>
      <div style="padding:20px">
        <router-view />
      </div>
    </div>
  </div>
</template>

<script>
import sideBar from "./sideBar";
import navBar from "./navBar";
export default {
  name: "layout",
  components: {
    sideBar,
    navBar
  },
  computed: {
    classObj() {
      return this.$store.state.collapse ? "hideSidebar" : "openSidebar";
    }
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