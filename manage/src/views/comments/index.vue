<template>
  <div>
    <div style="float: right">
      <el-form
        :model="params"
        ref="queryForm"
        size="medium"
        :inline="true"
        label-width="68px"
      >
        <el-form-item prop="val">
          <el-input
            placeholder="请输入内容"
            v-model="params.val"
            class="input-with-select"
            clearable
          >
            <el-select
              v-model="params.pattern"
              slot="prepend"
              placeholder="搜索类型"
            >
              <el-option label="书名" value="1"></el-option>
              <el-option label="ISBN号" value="2"></el-option>
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
    <el-table
      :data="comments"
      row-key="cmId"
      border
      :tree-props="{ children: 'childComment' }"
    >
      <el-table-column
        prop="username"
        align="center"
        label="评论人"
        :show-overflow-tooltip="true"
        width="180"
      >
      </el-table-column>
      <el-table-column
        prop="bookName"
        align="center"
        label="书名"
        :show-overflow-tooltip="true"
        width="180"
      >
      </el-table-column>
      <el-table-column
        prop="cmContent"
        align="center"
        label="评论内容"
        :show-overflow-tooltip="true"
      >
      </el-table-column>
      <el-table-column
        label="添加时间"
        align="center"
        prop="bookAddDate"
        width="180"
        :show-overflow-tooltip="true"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.cmDate, "{y}-{m}-{d} {h}:{i}") }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        align="center"
        width="160"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            icon="el-icon-delete"
            type="text"
            @click="delComment(scope.row.cmId)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="params.pageNum"
      :limit.sync="params.pageSize"
      @pagination="getCommentList"
    />
  </div>
</template>

<script>
import { $commentList, $delComment } from "@/api/comment";
export default {
  name: "comments",
  data() {
    return {
      params: {
        pageNum: 1,
        pageSize: 10,
        pattern: "1",
        val: undefined,
      },
      total: 0,
      comments: [],
    };
  },
  created() {
    this.getCommentList();
  },
  methods: {
    getCommentList() {
      $commentList(this.params).then((res) => {
        this.total = res.data.total;
        this.comments = res.data.data;
      });
    },
    handleQuery() {
      this.getCommentList();
    },
    resetQuery() {
      this.resetForm("queryForm");
      this.getCommentList();
    },
    delComment(cmId) {
      $delComment(cmId).then((res) => {
        this.$notify.success(res.msg);
        this.getCommentList();
      });
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
</style>