<template>
  <div>
    <div class="user-info-head" @click="editCropper()">
      <img v-bind:src="avatar" title="点击上传头像" class="img-circle img-lg" />
    </div>

    <el-dialog
      title="修改头像"
      :visible.sync="open"
      width="300px"
      append-to-body
    >
      <div>
        <SingleImgUpload v-model="avatar" @uploaded="uploaded" :fileSize="2" />
      </div>
    </el-dialog>
  </div>
</template>

<script>
import SingleImgUpload from "../Upload/singleImgUpload.vue";
import { $updateAvatar } from "@/api/me";
export default {
  name: "UserAvatar",
  components: {
    SingleImgUpload,
  },
  data() {
    return {
      avatar: sessionStorage.getItem("avatar")
        ? sessionStorage.getItem("avatar")
        : require("@/assets/images/avatar.jpg"),
      open: false,
    };
  },
  methods: {
    editCropper() {
      this.open = true;
    },
    uploaded() {
      // 修改完成
      this.open = false;
      sessionStorage.setItem("avatar", this.avatar);
      $updateAvatar(this.avatar).then((res) => {
        this.$notify.success(res.msg);
      });
    },
  },
};
</script>

<style scoped>
.img-circle {
  border-radius: 50%;
}

.img-lg {
  width: 120px;
  height: 120px;
}
.user-info-head {
  position: relative;
  display: inline-block;
  /* text-align: center; */
  height: 120px;
}

.user-info-head:hover:after {
  content: "+";
  position: absolute;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
  color: #eee;
  background: rgba(0, 0, 0, 0.5);
  font-size: 24px;
  font-style: normal;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  cursor: pointer;
  line-height: 110px;
  border-radius: 50%;
}
</style>