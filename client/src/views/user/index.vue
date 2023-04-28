<template>
  <div style="padding: 0 20%">
    <el-row :gutter="20">
      <el-col :span="8" :xs="24">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>个人信息</span>
          </div>
          <div>
            <div style="text-align: center">
              <UserAvatar />
            </div>
            <ul class="list-group list-group-striped">
              <li class="list-group-item">
                <i class="el-icon-user"></i><span class="item">用户名称</span>
                <div class="pull-right">{{ user.username }}</div>
              </li>
              <li class="list-group-item">
                <i class="el-icon-mobile-phone"></i
                ><span class="item">手机号码</span>
                <div class="pull-right">{{ user.phone }}</div>
              </li>
              <li class="list-group-item">
                <i class="el-icon-date"></i><span class="item">创建时间</span>
                <div class="pull-right">
                  {{ parseTime(user.addDate, "{y}-{m}-{d} {h}:{i}") }}
                </div>
              </li>
            </ul>
          </div>
        </el-card>
      </el-col>
      <el-col :span="16" :xs="24">
        <el-card>
          <div slot="header" class="clearfix">
            <span>修改信息</span>
          </div>
          <el-tabs v-model="activeTab">
            <el-tab-pane label="修改手机号" name="updatePhone">
              <el-form ref="phoneForm" :model="phoneForm" :rules="rules1">
                <el-form-item label prop="phone">
                  <el-input
                    v-model="phoneForm.phone"
                    placeholder="请输入手机号"
                    :maxlength="11"
                    show-word-limit
                    clearable
                    prefix-icon="el-icon-mobile"
                  ></el-input>
                </el-form-item>
                <el-form-item label prop="code">
                  <el-input
                    v-model="phoneForm.code"
                    placeholder="请输入验证码"
                    show-word-limit
                    clearable
                    prefix-icon="el-icon-picture-outline"
                    style="width: 50%"
                  ></el-input>
                  <div class="phone-code">
                    <el-button
                      type="primary"
                      class="btn"
                      :disabled="phoneCodeBtn.disabled"
                      @click="getPhoneCode"
                      >{{ phoneCodeBtn.msg }}</el-button
                    >
                  </div>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="updatePhone"
                    >修改</el-button
                  >
                </el-form-item>
              </el-form>
            </el-tab-pane>
            <el-tab-pane label="修改密码" name="updatePwd">
              <el-form ref="pwdForm" :model="pwdForm" :rules="rules2">
                <el-form-item label prop="oldPwd">
                  <el-input
                    v-model="pwdForm.oldPwd"
                    placeholder="请输入旧密码"
                    clearable
                    prefix-icon="el-icon-lock"
                    type="password"
                  ></el-input>
                </el-form-item>
                <el-form-item label prop="newPwd">
                  <el-input
                    v-model="pwdForm.newPwd"
                    placeholder="请输入新密码"
                    clearable
                    type="password"
                    prefix-icon="el-icon-lock"
                  ></el-input>
                </el-form-item>
                <el-form-item label prop="pwd">
                  <el-input
                    v-model="pwdForm.pwd"
                    placeholder="请再次输入新密码"
                    clearable
                    type="password"
                    prefix-icon="el-icon-lock"
                  ></el-input>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="updatePwd">修改</el-button>
                </el-form-item>
              </el-form>
            </el-tab-pane>
          </el-tabs>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { $userInfo, $updatePwd, $updatePhone, $getCode } from "@/api/me";
import UserAvatar from "@/components/UserAvatar";
export default {
  name: "user",
  components: {
    UserAvatar,
  },
  data() {
    return {
      activeTab: "updatePhone",
      user: {},
      phoneForm: {
        phone: "",
        code: "",
      },
      pwdForm: {
        oldPwd: "",
        pwd: "",
        newPwd: "",
      },
      rules1: {
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
        code: [
          {
            required: true,
            message: "请输入验证码",
            trigger: "blur",
          },
        ],
      },
      rules2: {
        oldPwd: [
          {
            required: true,
            message: "请输入旧密码",
            trigger: "blur",
          },
        ],
        newPwd: [
          {
            required: true,
            message: "请输入新密码",
            trigger: "blur",
          },
        ],
        pwd: [
          {
            required: true,
            message: "请再次输入新密码",
            trigger: "blur",
          },
        ],
      },
      phoneCodeBtn: {
        disabled: false,
        msg: "获取验证码",
      },
    };
  },
  created() {
    this.getUserInfo();
  },
  methods: {
    getUserInfo() {
      $userInfo().then((res) => {
        this.user = res.data;
      });
    },
    getPhoneCode() {
      const phone = this.phoneForm.phone;
      const isPhone = phone != "" && /^1(3|4|5|7|8|9)\d{9}$/.test(phone);
      if (isPhone) {
        let time = 60;
        // TODO: 发送验证码
        $getCode(phone).then((res) => {
          this.$message.success({
            message: "验证码为：" + res.data + "，测试使用，有效期十分钟",
            duration: 3000,
          });
        });
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
        this.$notify.error("请输入正确的手机号");
      }
    },
    updatePhone() {
      this.$refs.phoneForm.validate((valid) => {
        if (valid) {
          $updatePhone(this.phoneForm).then((res) => {
            this.$notify.success(res.msg);
            this.resetForm("phoneForm");
            this.getUserInfo();
          });
        }
      });
    },
    updatePwd() {
      this.$refs.pwdForm.validate((valid) => {
        if (valid) {
          if (this.pwdForm.newPwd != this.pwdForm.pwd) {
            this.$notify.error("两次密码输入不一致");
          } else {
            $updatePwd(this.pwdForm).then((res) => {
              this.$notify.success(res.msg);
              this.resetForm("pwdForm");
            });
          }
        }
      });
    },
  },
};
</script>

<style scoped>


.item {
  margin-left: 5px;
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