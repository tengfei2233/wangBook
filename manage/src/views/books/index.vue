<template>
  <div>
    <el-button type="success" plain @click="addBookBtn">添加书籍</el-button>
    <div style="float: right">
      <el-form
        :model="params"
        ref="queryForm"
        size="medium"
        :inline="true"
        label-width="68px"
      >
        <el-form-item prop="type">
          <el-select
            v-model="params.type"
            placeholder="书籍类型"
            @change="selectQuery"
            :clearable="true"
          >
            <el-option
              v-for="item in typeList"
              :label="item.typeName"
              :value="item.typeId"
              :key="item.typeId"
            ></el-option>
          </el-select>
        </el-form-item>
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
          <el-button icon="el-icon-refresh" size="medium" @click="resetQuery1"
            >重置</el-button
          >
        </el-form-item>
      </el-form>
    </div>
    <el-table v-loading="loading" :data="bookList">
      <el-table-column
        label="书籍名称"
        align="center"
        key="bookName"
        prop="bookName"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="作者名称"
        align="center"
        key="bookAuthor"
        prop="bookAuthor"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="封面"
        align="center"
        key="bookCover"
        prop="bookCover"
        :show-overflow-tooltip="true"
      >
        <template slot-scope="scope">
          <el-image
            class="courseImg"
            :src="scope.row.bookCover"
            :preview-src-list="[scope.row.bookCover]"
          ></el-image>
        </template>
      </el-table-column>
      <el-table-column
        label="ISBN号"
        align="center"
        key="bookIsbn"
        prop="bookIsbn"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="描述"
        align="center"
        key="bookDesc"
        prop="bookDesc"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="添加时间"
        align="center"
        key="bookAddDate"
        prop="bookAddDate"
        :show-overflow-tooltip="true"
      >
        <template slot-scope="scope">
          <span>{{
            parseTime(scope.row.bookAddDate, "{y}-{m}-{d} {h}:{i}")
          }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="书籍类型"
        align="center"
        key="typeName"
        prop="typeName"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="单价"
        align="center"
        key="bookPrice"
        prop="bookPrice"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="库存"
        align="center"
        key="bookStock"
        prop="bookStock"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="操作"
        align="center"
        width="160"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            icon="el-icon-edit-outline"
            type="text"
            @click="updateBook(scope.row)"
            >修改</el-button
          >
          <el-button
            v-if="scope.row.status == 0"
            icon="el-icon-check"
            type="text"
            @click="shelveBook(scope.row.bookId)"
            >上架</el-button
          >
          <el-button
            v-else
            icon="el-icon-close"
            type="text"
            @click="shelveBook(scope.row.bookId)"
            >下架</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="params.pageNum"
      :limit.sync="params.pageSize"
      @pagination="getBookList"
    />

    <el-dialog
      :title="addBtn.title"
      :visible.sync="addBtn.show"
      width="500px"
      append-to-body
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      :show-close="false"
    >
      <el-form
        ref="bookForm"
        :model="formData"
        :rules="rules"
        size="medium"
        label-width="100px"
        label-position="top"
      >
        <el-form-item label="书籍名称" prop="bookName">
          <el-input
            v-model="formData.bookName"
            placeholder="请输入书籍名称"
            show-word-limit
            clearable
            prefix-icon="el-icon-reading"
            :style="{ width: '100%' }"
          ></el-input>
        </el-form-item>
        <el-form-item label="作者名" prop="bookAuthor">
          <el-input
            v-model="formData.bookAuthor"
            placeholder="请输入作者名"
            show-word-limit
            clearable
            prefix-icon="el-icon-user"
            :style="{ width: '100%' }"
          ></el-input>
        </el-form-item>
        <el-form-item label="ISBN号" prop="bookIsbn">
          <el-input
            v-model="formData.bookIsbn"
            placeholder="请输入ISBN号"
            show-word-limit
            clearable
            prefix-icon="el-icon-s-check"
            :style="{ width: '100%' }"
          ></el-input>
        </el-form-item>
        <el-form-item label="书籍封面" prop="bookCover">
          <SingleImgUpload v-model="formData.bookCover" :fileSize="2" />
        </el-form-item>
        <el-form-item label="书籍描述" prop="bookDesc">
          <el-input
            v-model="formData.bookDesc"
            type="textarea"
            placeholder="请输入书籍描述"
            show-word-limit
            :autosize="{ minRows: 2, maxRows: 3 }"
            :style="{ width: '100%' }"
          ></el-input>
        </el-form-item>
        <el-form-item label="书籍类型" prop="typeId">
          <el-select
            v-model="formData.typeId"
            placeholder="请输入书籍类型"
            clearable
          >
            <el-option
              v-for="(item, index) in typeList"
              :key="index"
              :label="item.typeName"
              :value="item.typeId"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="书籍价格" prop="bookPrice">
          <el-input-number
            v-model="formData.bookPrice"
            placeholder="请输入书籍价格"
          >
          </el-input-number>
        </el-form-item>
        <el-form-item label="书籍库存" prop="bookStock">
          <el-input-number
            v-model="formData.bookStock"
            placeholder="请输入书籍库存"
            step-strictly
          ></el-input-number>
        </el-form-item>
        <el-form-item size="large">
          <el-button type="primary" @click="submitForm">提交</el-button>
          <el-button @click="resetQuery2">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { $typeListNoPage } from "@/api/type";
import { $bookList, $shelveBook, $addBook, $updateBook } from "@/api/book";
import SingleImgUpload from "@/components/Upload/singleImgUpload.vue";
export default {
  name: "books",
  components: {
    SingleImgUpload,
  },
  data() {
    return {
      params: {
        pageNum: 1,
        pageSize: 10,
        pattern: "1",
        type: undefined,
        key: undefined,
      },
      typeList: [],
      bookList: [],
      total: 0,
      loading: false,
      // 书籍信息
      bookInfo: {},
      addBtn: {
        title: "添加书籍",
        show: false,
      },
      // 添加书籍
      formData: {
        bookName: "",
        bookAuthor: "",
        bookIsbn: "",
        bookCover: "",
        bookDesc: "",
        typeId: "",
        bookPrice: 0.0,
        bookStock: 0,
      },
      rules: {
        bookName: [
          {
            required: true,
            message: "请输入书籍名称",
            trigger: "blur",
          },
        ],
        bookAuthor: [
          {
            required: true,
            message: "请输入作者名",
            trigger: "blur",
          },
        ],
        bookIsbn: [
          {
            required: true,
            message: "请输入ISBN号",
            trigger: "blur",
          },
        ],
        bookCover: [
          {
            required: true,
            message: "请输入书籍封面",
            trigger: "blur",
          },
        ],
        bookPrice: [
          {
            required: true,
            message: "请输入书籍价格",
            trigger: "blur",
          },
          {
            pattern: /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/,
            message: "请输入正确金额",
            trigger: "blur",
          },
        ],
        bookStock: [
          {
            required: true,
            message: "请输入书籍库存",
            trigger: "blur",
          },
          {
            pattern: /^[1-9]\d*$/,
            message: "请输入正确库存",
            trigger: "blur",
          },
        ],
      },
    };
  },
  created() {
    this.getTypeList();
    this.getBookList();
  },
  methods: {
    getTypeList() {
      $typeListNoPage().then((res) => {
        this.typeList = res.data;
      });
    },
    getBookList() {
      this.loading = true;
      $bookList(this.params).then((res) => {
        this.bookList = res.data.data;
        this.total = res.data.total;
        this.loading = false;
      });
    },
    selectQuery() {
      this.getBookList();
    },
    handleQuery() {
      this.getBookList();
    },
    resetQuery1() {
      this.resetForm("queryForm");
      this.getBookList();
    },
    shelveBook(bookId) {
      $shelveBook(bookId).then((res) => {
        this.$notify.success(res.msg);
        this.getBookList();
      });
    },
    updateBook(row) {
      this.formData = row;
      this.addBtn.title = "修改书籍";
      this.addBtn.show = true;
    },
    addBookBtn() {
      this.addBtn.title = "添加书籍";
      this.addBtn.show = true;
    },
    submitForm() {
      this.$refs["bookForm"].validate((valid) => {
        if (valid) {
          if (this.addBtn.title == "添加书籍") {
            $addBook(this.formData).then((res) => {
              this.resetQuery2();
              this.$notify.success(res.msg);
              this.getBookList();
            });
          } else {
            $updateBook(this.formData).then((res) => {
              this.resetQuery2();
              this.$notify.success(res.msg);
              this.getBookList();
            });
          }
        }
      });
    },
    resetQuery2() {
      this.resetForm("bookForm");
      this.addBtn.show = false;
    },
  },
};
</script>

<style scoped>
::v-deep .el-select .el-input {
  width: 130px;
}

::v-deep .input-with-select .el-input-group__prepend {
  background-color: #fff;
}

.courseImg {
  display: block;
  margin: auto;
  width: 60px;
  height: 120px;
}
</style>