<template>
  <div>
    <el-button type="success" plain @click="addUserBtn">添加用户</el-button>
    <div style="float: right;">
      <el-form
        :model="params"
        ref="queryForm"
        size="medium"
        :inline="true"
        label-width="68px"
      >
        <el-form-item prop="status">
          <el-select
            v-model="params.status"
            placeholder="用户状态"
            @change="selectQuery"
            :clearable="true"
          >
            <el-option label="正常" value="1"></el-option>
            <el-option label="禁用" value="0"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="searchKey">
          <el-input
            v-model="params.searchKey"
            placeholder="请输入用户名/手机号"
            clearable
            @keyup.enter.native="handleQuery"
          />
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

    <el-table v-loading="loading" :data="userList">
      <el-table-column
        label="用户id"
        align="center"
        key="userId"
        prop="userId"
      />
      <el-table-column
        label="用户名"
        align="center"
        key="username"
        prop="username"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="电话号码"
        align="center"
        key="phone"
        prop="phone"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="头像"
        align="center"
        key="avatar"
        prop="avatar"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="注册时间"
        align="center"
        key="addDate"
        prop="addDate"
        :show-overflow-tooltip="true"
      />
      <el-table-column label="状态" align="center" key="status" prop="status">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status == 1" type="success">正常</el-tag>
          <el-tag v-else type="danger">禁用</el-tag>
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
            v-if="scope.row.status == 0"
            icon="el-icon-circle-check"
            type="text"
            @click="lockUser(scope.row.userId)"
            >启用</el-button
          >
          <el-button
            v-else
            icon="el-icon-circle-close"
            type="text"
            @click="lockUser(scope.row.userId)"
            >禁用</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="params.pageNum"
      :limit.sync="params.pageSize"
      @pagination="getUserList"
    />
  </div>
</template>

<script>
import { $userList, $lock } from "@/api/user";
export default {
  name: "users",
  data() {
    return {
      params: {
        pageNum: 1,
        pageSize: 10,
        searchKey: undefined,
        status: undefined,
      },
      userList: [],
      total: 0,
      loading: false,
    };
  },
  created() {
    this.getUserList();
  },
  methods: {
    getUserList() {
      this.loading = true;
      $userList(this.params).then((res) => {
        console.log(res);
        this.total = res.data.total;
        this.userList = res.data.data;
        this.loading = false;
      });
    },
    lockUser(userId) {
      $lock(userId).then((res) => {
        this.getUserList();
      });
    },
    selectQuery() {
      this.getUserList();
    },
    handleQuery() {
      this.getUserList();
    },
    resetQuery() {
      this.resetForm("queryForm");
      this.getUserList();
    },
  },
};
</script>

<style scoped>
</style>