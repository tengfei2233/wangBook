<template>
  <div>
    <logo :collapse="collapse" />
    <el-scrollbar class="theme-dark" wrap-class="scrollbar-wrapper">
      <el-menu
        class="side-bar-menu"
        :default-active="activeMenu"
        :collapse="collapse"
        background-color="#304156"
        text-color="#bfcbd9"
        :unique-opened="true"
        active-text-color="#409EFF"
        :collapse-transition="true"
        mode="vertical"
        router
      >
        <template v-for="route in routeArr">
          <el-submenu v-if="route.subs" :index="route.index" :key="route.index">
            <template slot="title">
              <i :class="route.icon"></i>
              <span>{{ route.title }}</span>
            </template>
            <el-menu-item-group>
              <el-menu-item
                v-for="sub in route.subs"
                :index="sub.index"
                :key="sub.index"
              >
                <i :class="sub.icon"></i>
                <span slot="title">{{ sub.title }}</span>
              </el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <el-menu-item v-else :index="route.index" :key="route.index">
            <i :class="route.icon"></i>
            <span slot="title">{{ route.title }}</span>
          </el-menu-item>
        </template>
      </el-menu>
    </el-scrollbar>
  </div>
</template>

<script>
import logo from "./logo";
import routes from "./menu.js";
export default {
  name: "sideBar",
  components: {
    logo,
  },
  data() {
    return {
      routeArr: [],
    };
  },
  created() {
    this.routeArr = routes;
  },
  computed: {
    activeMenu() {
      return this.$route.path;
    },
    collapse() {
      return this.$store.state.collapse;
    },
  },
};
</script>

<style lang="less" scoped>
@import "@/assets/styles/sidebar.less";
/deep/ .theme-dark .el-submenu.is-active .el-submenu__title {
  color: #fff !important;
}
</style>