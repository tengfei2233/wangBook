<template>
  <div class="course-list flex-r-start flex-wrap" style="width: 100%">
    <div
      class="item"
      style="box-sizing: border-box; padding: 20px; flexbasis: 20%"
      v-for="book in bookList"
      :key="book.bookId"
      @click="toDetail(book.bookId)"
    >
      <el-card>
        <div class="img-container">
          <el-image style="width: 100%; height: 155px" :src="book.bookCover">
            <div slot="error" class="image-slot">
              <i class="el-icon-picture-outline"></i>
            </div>
          </el-image>
        </div>
        <div class="pl10 font-weight font20 mb5" style="color: #212121">
          {{ book.bookName }}
        </div>
        <div class="pl10 mb5 grey">作者：{{ book.bookAuthor }}</div>
        <div class="pl10 grey">单价：{{ book.bookPrice }}</div>
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
      this.$emit("click", id);
    },
  },
};
</script>
  
  <style>
.course-list .el-card {
  border-radius: 10px !important;
  /* margin-right: 40px;
    margin-top: 40px; */
  cursor: pointer;
}
.course-list .el-card .el-card__body {
  padding: 0 0 20px 0 !important;
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
</style>
  