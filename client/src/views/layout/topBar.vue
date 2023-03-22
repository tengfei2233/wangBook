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
          
        </el-menu>
      </el-col>
      <el-col :span="8">
        <div id="right-menu"></div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "topBar"
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
  height: 60px;
}
#menu-btn .is-active {
  font-size: 22px;
}
</style>