<template>
  <div id="top">
    <el-row>
      <el-col :span="8">
        <div id="left-menu">
          <h2 style="color: #68bfd6">二手书售卖平台</h2>
        </div>
      </el-col>
      <el-col :span="8">
        <el-menu
          :default-active="$route.path"
          id="menu-btn"
          mode="horizontal"
          active-text-color="#68bfd6"
          :router="true"
        >
          <el-menu-item index="/home">首页</el-menu-item>
          <el-menu-item index="/books">图书列表</el-menu-item>
          <el-menu-item index="/cars">购物车</el-menu-item>
          <el-menu-item index="/orders">订单列表</el-menu-item>
        </el-menu>
      </el-col>
      <el-col :span="8">
        <div id="right-menu">
          <div v-if="!$store.state.userName">
            <!-- 已登录 -->
            <el-dropdown placement="bottom-start">
              <div id="avatar-wrapper">
                <el-avatar :src="avatar" id="avatar"></el-avatar>
                <i class="el-icon-caret-bottom" />
              </div>
              <el-dropdown-menu slot="dropdown">
                <router-link to="/user">
                  <el-dropdown-item>
                    <span style="display:block;" class="dropdown-select-info">个人中心</span>
                  </el-dropdown-item>
                </router-link>
                <el-dropdown-item divided @click.native="logout">
                  <span style="display:block;" class="dropdown-select-info">退出登录</span>
                </el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </div>
          <div v-else>
            <!-- 未登录 -->
            <i class="el-icon-user"></i>
            <el-button id="no-login-btn" type="text">注册/登录!</el-button>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "topBar",
  data() {
    return {
      avatar: ""
    };
  },
  created() {
    // TODO: 设置头像
    this.avatar = require("@/assets/images/avatar.jpg");
  },
  methods: {
    logout() {
      this.$confirm("是否确定退出当前系统？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {})
        .catch(() => {});
    }
  }
};
</script>

<style scoped>
.el-col {
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
#top {
  height: 100%;
  overflow-y: hidden;
}
#left-menu {
  display: flex;
  flex-direction: row;
  align-items: center;
  height: 100%;
}

/* 菜单按钮 */
.el-menu--horizontal > .el-menu-item {
  height: 50px;
}
.el-menu.el-menu--horizontal {
  border-bottom: none;
}
#menu-btn .el-menu-item {
  /*font-size: 18px;*/
  color: #909399;
  font-weight: 700;
  height: 65px;
  line-height: 70px;
}
#menu-btn .is-active {
  font-size: 22px;
}

#right-menu {
  display: flex;
  flex-direction: row;
  justify-content: left;
  align-items: center;
}
#right-menu > div {
  height: 70px;
}

#right-menu > div > i {
  font-size: 25px;
  margin-top: -5px;
  margin-right: 5px;
  /* 伪元素垂直居中 */
  display: inline-block;
  vertical-align: middle;
}

#right-menu > div > #no-login-btn {
  font-size: 16px;
  text-decoration: underline;
  line-height: 16px;
  margin-top: 16px;
}

#avatar-wrapper {
  margin-top: 8px;
  height: 55px;
}

#avatar-wrapper > #avatar {
  width: 55px;
  height: 55px;
}

.dropdown-select-info {
  font-size: 15px;
  text-decoration: none;
}
</style>