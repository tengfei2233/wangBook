<template>
  <el-carousel
    :interval="4000"
    indicator-position="none"
    arrow="hover"
    height="400px"
  >
    <el-carousel-item v-for="(banner, index) in bannerList" :key="index">
      <img :src="banner.bnUrl" class="carousel-img" />
    </el-carousel-item>
  </el-carousel>
</template>

<script>
export default {
  name: "Banner",
  props: {
    bannerList: {
      type: Array,
      default: () => [],
    },
  },
};
</script>

<style scoped>
.el-carousel {
  padding: 0 20px;
}
img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
</style>
