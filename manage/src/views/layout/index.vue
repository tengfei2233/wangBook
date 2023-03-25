<template>
  <div id="container">
    <side-bar/>
    <router-view />
  </div>
</template>

<script>
import SideBar from "./SideBar.vue";
export default {
  name: "layout",
  comments: {
    SideBar
  }
};
</script>

<style scoped>
#container {
  position: relative;
  height: 100%;
  width: 100%;
}
</style>