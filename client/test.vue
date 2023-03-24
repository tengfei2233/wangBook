<template>
  <div>
    <el-row :gutter="15">
      <el-form
        ref="loginForm"
        :model="formData"
        :rules="rules"
        size="medium"
        label-width="100px"
        label-position="top"
      >
        <el-col :span="24">
          <el-form-item label prop="phone">
            <el-input
              v-model="formData.phone"
              placeholder="请输入手机号"
              :maxlength="11"
              show-word-limit
              clearable
              prefix-icon="el-icon-mobile"
              :style="{width: '100%'}"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label prop="password">
            <el-input
              v-model="formData.password"
              placeholder="请输入密码"
              show-word-limit
              clearable
              prefix-icon="el-icon-lock"
              show-password
              :style="{width: '100%'}"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="15">
          <el-form-item label prop="captcha">
            <el-input
              v-model="formData.captcha"
              placeholder="请输入验证码"
              show-word-limit
              clearable
              prefix-icon="el-icon-picture-outline"
              :style="{width: '100%'}"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label prop>
            <el-button type="primary" icon="el-icon-search" size="medium">主要按钮</el-button>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item size="large">
            <el-button type="primary" @click="submitForm">提交</el-button>
            <el-button @click="resetForm">重置</el-button>
          </el-form-item>
        </el-col>
      </el-form>
    </el-row>
  </div>
</template>
  <script>
  export default {
    components: {},
    props: [],
    data() {
      return {
        formData: {
          phone: '',
          password: undefined,
          captcha: undefined,
        },
        rules: {
          phone: [{
            required: true,
            message: '请输入手机号',
            trigger: 'blur'
          }, {
            pattern: /^1(3|4|5|7|8|9)\d{9}$/,
            message: '手机号格式错误',
            trigger: 'blur'
          }],
          password: [{
            required: true,
            message: '请输入密码',
            trigger: 'blur'
          }],
          captcha: [{
            required: true,
            message: '请输入验证码',
            trigger: 'blur'
          }],
        },
      }
    },
    computed: {},
    watch: {},
    created() {},
    mounted() {},
    methods: {
      submitForm() {
        this.$refs['loginForm'].validate(valid => {
          if (!valid) return
          // TODO 提交表单
        })
      },
      resetForm() {
        this.$refs['loginForm'].resetFields()
      },
    }
  }
  
  </script>
  <style>
</style>
  