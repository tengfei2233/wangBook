<template>
  <div class="course-list flex-r-start flex-wrap" style="width: 100%">
    <div
      class="item"
      v-for="book in bookList"
      :key="book.bookId"
      @click="toDetail(book.bookId)"
    >
      <el-card>
        <div class="img-container">
          <el-image style="width: 100%; height: 180px" :src="book.bookCover" v-if="book.bookCover !== undefined" lazy>
            <div slot="error" class="image-slot">
              <i class="el-icon-picture-outline"></i>
            </div>
          </el-image>
        </div>
        <div class="pl10 font-weight font20 mb5">
          <p class="name">{{ book.bookName }}</p>
        </div>
        <div class="pl10 mb5 grey">
          <p class="author">{{ book.bookAuthor }}</p>
        </div>
        <div class="pl10 grey">
          <p class="price">￥{{ book.bookPrice }}</p>
        </div>
      </el-card>
    </div>
  </div>
</template>
  
  <script>
export default {
  name: "CardList",
  props: {
    bookList: {
      type: Array,
      default: () => [],
    },
    showProgress: Boolean,
  },
  methods: {
    toDetail(id) {
      // 书籍详情
      this.$router.push({
        path: "/book/" + id + "?t=" + new Date().getTime().toString(),
      });
    },
  },
};
</script>
  
  <style scoped>
.item {
  box-sizing: border-box;
  padding: 10px 45px;
  flex-basis: 20%;
  /* 防止white-space与flex的不兼容 */
  min-width: 0;
}
.course-list .el-card {
  border-radius: 10px !important;
  cursor: pointer;
}
::v-deep .el-card__body {
  padding: 0 !important;
}

.course-list .el-card p {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  margin: 0;
}
.course-list .name {
  font-size: 18px;
  padding: 3px 10px 0 10px;
}

.course-list .author {
  font-size: 15px;
  color: gray;
  padding: 3px 10px 0 10px;
}

.course-list .price {
  font-size: 16px;
  color: #c30;
  padding: 3px 10px 3px 10px;
}

.img-container {
  position: relative;
}
.img-container .process {
  position: absolute;
  top: 135px;
  width: 100%;
  margin: 0 auto;
}
.process .el-progress-bar__outer {
  border-radius: 0 !important;
}
.process .el-progress-bar__outer,
.el-progress-bar__inner {
  border-radius: 0 !important;
}
.process .el-progress-bar__innerText {
  color: #212121 !important;
}

.image-slot {
  background-color: #f8f8f8;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 32px;
}

.flex-wrap {
  flex-wrap: wrap;
}

.flex-rb {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.flex-r-start {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
}
</style>
  