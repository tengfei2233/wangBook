<template>
  <el-dialog width="400px" title="用户注册" :visible.sync="isOpen" @close="close">
    <el-form ref="registerForm" :model="formData" :rules="rules" label-width="80px">
      <el-form-item label="用户名" prop="username">
        <el-input
          v-model="formData.username"
          placeholder="请输入用户名"
          :maxlength="10"
          show-word-limit
          clearable
          prefix-icon="el-icon-user"
        ></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input
          v-model="formData.password"
          placeholder="请输入密码"
          show-word-limit
          clearable
          prefix-icon="el-icon-lock"
          show-password
        ></el-input>
      </el-form-item>
      <el-form-item label="重复密码" prop="rePassword">
        <el-input
          v-model="formData.rePassword"
          placeholder="请再次输入密码"
          show-word-limit
          clearable
          prefix-icon="el-icon-lock"
          show-password
        ></el-input>
      </el-form-item>
      <el-form-item label="手机号" prop="phone">
        <el-input
          v-model="formData.phone"
          placeholder="请输入手机号"
          :maxlength="11"
          show-word-limit
          clearable
          prefix-icon="el-icon-mobile"
        ></el-input>
      </el-form-item>
      <el-form-item label="验证码" prop="phoneCode">
        <el-input
          v-model="formData.phoneCode"
          placeholder="请输入验证码"
          show-word-limit
          clearable
          prefix-icon="el-icon-picture-outline"
          style="width:50%"
        ></el-input>
        <div class="phone-code">
          <el-button
            type="primary"
            class="btn"
            :disabled="phoneCodeBtn.disabled"
            @click="getPhoneCode"
          >{{phoneCodeBtn.msg}}</el-button>
        </div>
      </el-form-item>
    </el-form>
    <div slot="footer">
      <el-button @click="close">取消</el-button>
      <el-button type="primary" @click="register">注册</el-button>
    </div>
  </el-dialog>
</template>

<script>
export default {
  name: "Register",
  props: {
    visible: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      formData: {
        username: "",
        password: "",
        rePassword: "",
        phone: "",
        phoneCode: ""
      },
      rules: {
        username: [
          {
            required: true,
            message: "请输入用户名",
            trigger: "blur"
          },
          {
            pattern: /^[a-zA-Z][0-9]{5,10}$/,
            message: "请输入5-10位字母开头的用户名",
            trigger: "blur"
          }
        ],
        password: [
          {
            required: true,
            message: "请输入密码",
            trigger: "blur"
          }
        ],
        rePassword: [
          {
            required: true,
            message: "请再次输入密码",
            trigger: "blur"
          }
        ],
        phone: [
          {
            required: true,
            message: "请输入手机号",
            trigger: "blur"
          },
          {
            pattern: /^1(3|4|5|7|8|9)\d{9}$/,
            message: "手机号格式错误",
            trigger: "blur"
          }
        ],
        phoneCode: [
          {
            required: true,
            message: "请输入验证码",
            trigger: "blur"
          }
        ]
      },
      phoneCodeBtn: {
        disabled: false,
        msg: "获取验证码"
      }
    };
  },
  computed: {
    /** 弹框是否打开 */
    isOpen: {
      get() {
        return this.visible;
      },
      set(val) {
        this.$emit("update:visible", val);
      }
    }
  },
  methods: {
    close() {
      this.$refs["registerForm"].resetFields();
      this.$emit("update:visible", false);
    },
    getPhoneCode() {
      const phone = this.formData.phone;
      const isPhone = phone != "" && /^1(3|4|5|7|8|9)\d{9}$/.test(phone);
      if (isPhone) {
        let time = 60;
        let interval = window.setInterval(() => {
          this.phoneCodeBtn.msg = "请" + time + "后再获取";
          this.phoneCodeBtn.disabled = true;
          time--;
          if (time <= 0) {
            this.phoneCodeBtn.msg = "获取验证码";
            this.phoneCodeBtn.disabled = false;
            window.clearInterval(interval);
          }
        }, 1000);
      } else {
        this.$message.error("请输入正确的手机号");
      }
    },
    register() {
      this.$refs["registerForm"].validate(valid => {
        if (!valid) return;
        this.close();
      });
    }
  }
};
</script>

<style scoped>
::v-deep .el-dialog__body {
  padding: 20px 10px;
}
.phone-code {
  width: 50%;
  height: 40px;
  float: right;
}

.phone-code > .btn {
  margin-left: 10px;
  width: 90%;
}
</style>