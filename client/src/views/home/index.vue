<template>
  <div>
    <Banner :bannerList="bannerList" />
    <div class="homeCard">
      <div class="cardHeader">
        <span>最新上架二手书籍</span>
        <el-button type="text" class="more" @click="more(1)"
          >更多<i class="el-icon-d-arrow-right el-icon--right"></i
        ></el-button>
      </div>
      <CardList :bookList="newBookList" />
    </div>
    <div class="homeCard">
      <div class="cardHeader">
        <span>热门二手书籍</span>
        <el-button type="text" class="more" @click="more(2)"
          >更多<i class="el-icon-d-arrow-right el-icon--right"></i
        ></el-button>
      </div>
      <CardList :bookList="hotBookList" />
    </div>
  </div>
</template>

<script>
import Banner from "@/components/Banner";
import CardList from "@/components/CardList";
import { $bannerList, $bookList } from "@/api/home";
export default {
  name: "home",
  components: {
    Banner,
    CardList,
  },
  data() {
    return {
      bannerList: [],
      hotBookList: [],
      newBookList: [],
    };
  },
  created() {
    this.getBannerList();
    this.getBookList();
  },
  methods: {
    getBannerList() {
      $bannerList().then((res) => {
        this.bannerList = res.data;
      });
    },
    getBookList() {
      $bookList().then((res) => {
        this.newBookList = res.data.new;
        this.hotBookList = res.data.hot;
      });
    },
    more(type) {
      this.$router.push({
        path: "/books",
        params: {
          type: type,
        },
      });
    },
  },
};
</script>

<style scoped>
.homeCard {
  margin: 40px 10%;
}
.homeCard .cardHeader span {
  font-size: 22px;
  font-weight: 400;
  color: #1f2f3d;
  border-left: solid 10px #2ce8b4;
  padding-left: 10px;
}
.homeCard .cardHeader .more {
  float: right;
  font-size: 20px;
  color: #525252;
}
</style>