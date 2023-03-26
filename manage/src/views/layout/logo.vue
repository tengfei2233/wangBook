<template>
  <div class="sidebar-logo-container" :class="{'collapse':collapse}">
    <transition name="sidebarLogoFade">
      <router-link v-if="collapse" key="collapse" class="sidebar-logo-link" to="/">
        <img v-if="logo" :src="logo" class="sidebar-logo" />
        <h1 v-else class="sidebar-title">{{ title }}</h1>
      </router-link>
      <router-link v-else key="expand" class="sidebar-logo-link" to="/">
        <img v-if="logo" :src="logo" class="sidebar-logo" />
        <h1 class="sidebar-title">{{ title }}</h1>
      </router-link>
    </transition>
  </div>
</template>

<script>
import logoImg from "@/assets/logo/logo.png";

export default {
  name: "logo",
  props: {
    collapse: {
      type: Boolean,
      required: true
    }
  },
  computed: {},
  data() {
    return {
      title: "二手书交易后台",
      logo: logoImg
    };
  }
};
</script>

<style lang="less" scoped>
@import "@/assets/styles/sidebar.less";
</style>
