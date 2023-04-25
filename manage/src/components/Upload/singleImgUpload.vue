<template>
  <div class="component-upload-image">
    <el-upload
      class="avatar-uploader"
      :action="uploadImgUrl"
      :show-file-list="false"
      :on-success="handleUploadSuccess"
      :before-upload="handleBeforeUpload"
      :on-error="handleUploadError"
      name="img"
      :headers="headers"
    >
      <img v-if="imageUrl" :src="imageUrl" class="avatar" />
      <i v-else class="el-icon-plus avatar-uploader-icon"></i>
    </el-upload>

    <!-- 上传提示 -->
    <div class="el-upload__tip" slot="tip" v-if="showTip">
      请上传
      <template v-if="fileSize">
        大小不超过
        <b style="color: #f56c6c">{{ fileSize }}MB</b>
      </template>
      <template v-if="fileType">
        格式为
        <b style="color: #f56c6c">{{ fileType.join("/") }}</b>
      </template>
      的文件
    </div>
  </div>
</template>

<script>
import { getToken } from "@/utils/auth";

export default {
  props: {
    value: [String, Object, Array],
    // 大小限制(MB)
    fileSize: {
      type: Number,
      default: 2,
    },
    // 文件类型, 例如['png', 'jpg', 'jpeg']
    fileType: {
      type: Array,
      default: () => ["png", "jpg", "jpeg"],
    },
    // 是否显示提示
    isShowTip: {
      type: Boolean,
      default: true,
    },
  },
  data() {
    return {
      uploadImgUrl: process.env.VUE_APP_BASE_API + "/common/uploadImg", // 上传的图片服务器地址
      headers: {
        token: getToken(),
      },
      imageUrl: "",
    };
  },
  watch: {
    value: {
      handler(val) {
        if (val) {
          this.imageUrl = val;
          return val;
        } else {
          this.imageUrl = "";
          return "";
        }
      },
      deep: true,
      immediate: true,
    },
  },
  computed: {
    // 是否显示提示
    showTip() {
      return this.isShowTip && (this.fileType || this.fileSize);
    },
  },
  methods: {
    // 上传成功回调
    handleUploadSuccess(res) {
      if (res.code == 200) {
        this.imageUrl = res.data;
        this.$emit("input", this.imageUrl);
        this.$loading().close();
      } else {
        this.$notify.error(res.msg);
        this.$loading().close();
      }
    },
    // 上传前loading加载
    handleBeforeUpload(file) {
      let isImg = false;
      if (this.fileType.length) {
        let fileExtension = "";
        if (file.name.lastIndexOf(".") > -1) {
          fileExtension = file.name.slice(file.name.lastIndexOf(".") + 1);
        }
        isImg = this.fileType.some((type) => {
          if (file.type.indexOf(type) > -1) return true;
          if (fileExtension && fileExtension.indexOf(type) > -1) return true;
          return false;
        });
      } else {
        isImg = file.type.indexOf("image") > -1;
      }

      if (!isImg) {
        this.$notify.error(
          `文件格式不正确, 请上传${this.fileType.join("/")}图片格式文件!`
        );
        return false;
      }
      if (this.fileSize) {
        const isLt = file.size / 1024 / 1024 < this.fileSize;
        if (!isLt) {
          this.$notify.warning(`上传图片大小不能超过 ${this.fileSize} MB!`);
          return false;
        }
      }
      this.$loading({ text: "正在上传图片，请稍候..." });
    },
    // 上传失败
    handleUploadError(res) {
      this.$notify.error("上传图片失败，请重试");
      this.$loading().close();
    },
  },
};
</script>
<style scoped>
::v-deep .avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
::v-deep .avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>

