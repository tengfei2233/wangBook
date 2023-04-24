<template>
  <div>
    <el-button type="success" plain @click="addUserBtn">添加用户</el-button>
    <div style="float: right">
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
          <el-button icon="el-icon-refresh" size="medium" @click="resetQuery1"
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
      >
        <template slot-scope="scope">
          <el-image
            class="courseImg"
            :src="
              scope.row.avatar
                ? scope.row.avatar
                : require('@/assets/images/default-avatar.png')
            "
            :preview-src-list="[
              scope.row.avatar
                ? scope.row.avatar
                : require('@/assets/images/default-avatar.png'),
            ]"
          ></el-image>
        </template>
      </el-table-column>
      <el-table-column
        label="注册时间"
        align="center"
        key="addDate"
        prop="addDate"
        :show-overflow-tooltip="true"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.addDate, "{y}-{m}-{d} {h}:{i}") }}</span>
        </template>
      </el-table-column>
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

    <el-dialog
      title="添加用户"
      :visible.sync="openAddUser"
      width="500px"
      append-to-body
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      :show-close="false"
    >
      <el-form
        ref="addUserForm"
        :model="user"
        :rules="rules"
        size="medium"
        label-width="100px"
        label-position="top"
      >
        <el-form-item label="用户名(用户密码默认:123)" prop="username">
          <el-input
            v-model="user.username"
            placeholder="请输入用户名(用户密码默认:123)"
            show-word-limit
            clearable
            prefix-icon="el-icon-user"
            :style="{ width: '100%' }"
          ></el-input>
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input
            v-model="user.phone"
            placeholder="请输入手机号"
            :maxlength="11"
            show-word-limit
            clearable
            prefix-icon="el-icon-mobile"
            :style="{ width: '100%' }"
          ></el-input>
        </el-form-item>
        <el-form-item label="头像" prop="avatar">
          <SingleImgUpload v-model="user.avatar" :fileSize="2" />
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
import { $userList, $lock, $addUser } from "@/api/user";
import SingleImgUpload from "@/components/Upload/singleImgUpload.vue";
export default {
  name: "users",
  components: {
    SingleImgUpload,
  },
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
      openAddUser: false,
      user: {
        username: undefined,
        password: undefined,
        avatar: undefined,
      },
      rules: {
        username: [
          {
            required: true,
            message: "请输入用户名(用户密码默认:123)",
            trigger: "blur",
          },
        ],
        phone: [
          {
            required: true,
            message: "请输入手机号",
            trigger: "blur",
          },
          {
            pattern: /^1(3|4|5|7|8|9)\d{9}$/,
            message: "手机号格式错误",
            trigger: "blur",
          },
        ],
      },
      avatarAction: "头像上传地址",
      avatarfileList: [],
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
    resetQuery1() {
      this.resetForm("queryForm");
      this.getUserList();
    },
    addUserBtn() {
      this.openAddUser = true;
    },

    submitForm() {
      this.$refs["addUserForm"].validate((valid) => {
        if (valid) {
          $addUser(this.user).then((res) => {
            this.getUserList();
            this.$notify.success(res.msg);
            this.resetForm("addUserForm");
            this.openAddUser = false;
          });
        }
      });
    },
    resetQuery2() {
      this.resetForm("addUserForm");
      this.openAddUser = false;
    },
  },
};
</script>

<style scoped>
.courseImg {
  display: block;
  margin: auto;
  width: 120px;
  height: 60px;
}
</style>