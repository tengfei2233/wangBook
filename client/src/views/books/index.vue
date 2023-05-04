<template>
  <div style="margin: 0 10%">
    <div class="book_type">
      <span>书籍类型：</span>
      <el-button
        v-for="item in types"
        :key="item.typeId"
        type="text"
        @click="checkType(item.typeId)"
        :class="params.type == item.typeId ? 'check_btn' : ''"
        >{{ item.typeName }}</el-button
      >
    </div>
    <div class="book_sort_type">
      <el-button
        type="text"
        @click="checkSortType(1)"
        :class="params.sortType == 1 ? 'check_btn' : ''"
        >默认</el-button
      >
      <el-divider direction="vertical"></el-divider>
      <el-button
        type="text"
        @click="checkSortType(2)"
        :class="params.sortType == 2 ? 'check_btn' : ''"
        >最新</el-button
      >
      <!-- <el-divider direction="vertical"></el-divider>
      <el-button
        type="text"
        @click="checkSortType(3)"
        :class="params.sortType == 3 ? 'check_btn' : ''"
        >最热</el-button
      > -->
    </div>
    <div class="book_search">
      <el-form
        :model="params"
        ref="queryForm"
        size="medium"
        :inline="true"
        label-width="68px"
      >
        <el-form-item prop="key">
          <el-input
            placeholder="请输入内容"
            v-model="params.key"
            class="input-with-select"
            clearable
          >
            <el-select
              v-model="params.pattern"
              slot="prepend"
              placeholder="搜索类型"
            >
              <el-option label="书名" value="1"></el-option>
              <el-option label="作者名" value="2"></el-option>
              <el-option label="ISBN号" value="3"></el-option>
            </el-select>
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button
            type="primary"
            icon="el-icon-search"
            size="medium"
            @click="handleQuery"
            >搜索</el-button
          >
          <el-button icon="el-icon-refresh" size="medium" @click="resetQuery"
            >重置</el-button
          >
        </el-form-item>
      </el-form>
    </div>

    <CardList v-if="books" :bookList="books" v-loading="loading" />
    <el-empty v-show="!books.length" />

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="params.pageNum"
      :limit.sync="params.pageSize"
      @pagination="getBookList"
    />
  </div>
</template>

<script>
import CardList from "@/components/CardList";
import { $bookList, $typeList } from "@/api/book";
export default {
  name: "books",
  components: {
    CardList,
  },
  data() {
    return {
      params: {
        pageNum: 1,
        pageSize: 10,
        sortType: 1,
        type: undefined,
        pattern: "1",
        key: undefined,
      },
      types: [],
      books: [],
      total: 0,
      loading: false,
    };
  },
  created() {
    this.getTypeList();
    this.getBookList();
  },
  methods: {
    checkSortType(sortType) {
      this.params.sortType = sortType;
      this.getBookList();
    },
    checkType(typeId) {
      this.params.type = typeId;
      this.getBookList();
    },
    getTypeList() {
      $typeList().then((res) => {
        this.types = res.data;
      });
    },
    getBookList() {
      this.loading = true;
      $bookList(this.params).then((res) => {
        this.books = res.data.data;
        this.total = res.data.total;
        this.loading = false;
      });
    },
    handleQuery() {
      this.getBookList();
    },
    resetQuery() {
      this.resetForm("queryForm");
      this.params.type = undefined;
      this.params.sortType = "1";
      this.getBookList();
    },
  },
};
</script>

<style scoped>
.book_type {
  border-left: solid 10px #2ce8b4;
  margin-left: 40px;
  padding-left: 10px;

}
.book_type span {
  font-size: 18px;
}

.book_type .check_btn {
  color: #409eff;
}

.book_type button {
  font-size: 18px;
  color: gray;
}

.book_sort_type {
  padding: 30px 0 0 60px;
  display: inline-block;
}
.book_sort_type .check_btn {
  color: #409eff;
}

.book_sort_type button {
  font-size: 18px;
  color: gray;
}

.book_search {
  float: right;
  padding-top: 30px;
}

::v-deep .el-select .el-input {
  width: 130px;
}

::v-deep .input-with-select .el-input-group__prepend {
  background-color: #fff;
}
</style>