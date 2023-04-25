<template>
  <div>
    <el-button type="success" plain @click="addTypeBtn">添加书籍类型</el-button>
    <div></div>
    <el-table v-loading="loading" :data="typeList" style="margin-top: 20px">
      <el-table-column
        label="书籍类型id"
        align="center"
        key="typeId"
        prop="typeId"
      />
      <el-table-column
        label="类型名称"
        align="center"
        key="typeName"
        prop="typeName"
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
            icon="el-icon-circle-check"
            type="text"
            @click="delType(scope.row.typeId)"
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
      @pagination="getTypeList"
    />

    <el-dialog
      title="添加图书类型"
      :visible.sync="openAddType"
      width="500px"
      append-to-body
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      :show-close="false"
    >
      <el-form
        ref="addTypeForm"
        :model="typeForm"
        :rules="rules"
        size="medium"
        label-width="100px"
        label-position="top"
      >
        <el-form-item label="类型名称" prop="typeName">
          <el-input
            v-model="typeForm.typeName"
            placeholder="请输入书籍类型名称"
            show-word-limit
            clearable
            prefix-icon="el-icon-c-scale-to-original"
            :style="{ width: '100%' }"
          ></el-input>
        </el-form-item>
        <el-form-item size="large">
          <el-button type="primary" @click="submitForm">提交</el-button>
          <el-button @click="resetQuery">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { $typeList, $delType, $addType } from "@/api/type";
export default {
  name: "types",
  data() {
    return {
      typeList: [],
      params: {
        pageNum: 1,
        pageSize: 10,
      },
      total: 0,
      loading: false,
      openAddType: false,
      typeForm: {
        typeName: undefined,
      },
      rules: {
        typeName: [
          {
            required: true,
            message: "请输入书籍类型名称",
            trigger: "blur",
          },
        ],
      },
    };
  },
  created() {
    this.getTypeList();
  },
  methods: {
    getTypeList() {
      this.loading = true;
      $typeList(this.params).then((res) => {
        console.log(res);
        this.typeList = res.data.data;
        this.total = res.data.total;
        this.loading = false;
      });
    },
    addTypeBtn() {
      this.openAddType = true;
    },
    delType(typeId) {
      $delType(typeId).then((res) => {
        this.getTypeList();
        this.$notify.success(res.msg);
      });
    },
    submitForm() {
      this.$refs["addTypeForm"].validate((valid) => {
        if (valid) {
          $addType(this.typeForm.typeName).then((res) => {
            this.getTypeList();
            this.$notify.success(res.msg);
            this.resetForm("addTypeForm");
            this.openAddType = false;
          });
        }
      });
    },
    resetQuery() {
      this.resetForm("addTypeForm");
      this.openAddType = false;
    },
  },
};
</script>

<style>
</style>