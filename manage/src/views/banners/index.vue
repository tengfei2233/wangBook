<template>
  <div>
    <el-button type="success" plain @click="addBannerBtn">添加轮播图</el-button>
    <el-table v-loading="loading" :data="bannerList" style="margin-top: 20px">
      <el-table-column label="轮播图Id" align="center" key="bnId" prop="bnId" />
      <el-table-column
        label="图片"
        align="center"
        key="bnUrl"
        prop="bnUrl"
        :show-overflow-tooltip="true"
      >
        <template slot-scope="scope">
          <el-image
            class="courseImg"
            :src="scope.row.bnUrl"
            :preview-src-list="[scope.row.bnUrl]"
          ></el-image>
        </template>
      </el-table-column>
      <el-table-column
        label="内容"
        align="center"
        key="bnContent"
        prop="bnContent"
      />
      <el-table-column
        label="添加时间"
        align="center"
        key="bnDate"
        prop="bnDate"
        :show-overflow-tooltip="true"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.bnDate, "{y}-{m}-{d} {h}:{i}") }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="是否展示"
        align="center"
        key="bnIsShow"
        prop="bnIsShow"
      >
        <template slot-scope="scope">
          <el-tag v-if="scope.row.bnIsShow == 1" type="success">展示</el-tag>
          <el-tag v-else type="warning">不展示</el-tag>
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
            icon="el-icon-circle-check"
            type="text"
            @click="updateBanner(scope.row)"
            >修改</el-button
          >
          <el-button
            icon="el-icon-circle-check"
            type="text"
            @click="delBanner(scope.row.bnId)"
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
      @pagination="getBannerList"
    />

    <el-dialog
      :title="addBtn.title"
      :visible.sync="addBtn.show"
      width="500px"
      append-to-body
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      :show-close="false"
    >
      <el-form
        ref="bannerForm"
        :model="formData"
        :rules="rules"
        size="medium"
        label-width="100px"
      >
        <el-form-item label="轮播图内容" prop="bnContent">
          <el-input
            v-model="formData.bnContent"
            placeholder="请输入轮播图内容"
            show-word-limit
            clearable
            prefix-icon="el-icon-edit"
            :style="{ width: '100%' }"
          ></el-input>
        </el-form-item>
        <el-form-item label="轮播图图片" prop="bnUrl">
          <SingleImgUpload v-model="formData.bnUrl" :fileSize="2" />
        </el-form-item>
        <el-form-item label="是否展示" prop="bnIsShow" required>
          <el-switch
            v-model="formData.bnIsShow"
            :active-value="1"
            :inactive-value="0"
          ></el-switch>
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
import {
  $bannerList,
  $addBanner,
  $delBanner,
  $updateBanner,
} from "@/api/banner";
import SingleImgUpload from "@/components/Upload/singleImgUpload.vue";
export default {
  name: "banners",
  components: {
    SingleImgUpload,
  },
  data() {
    return {
      params: {
        pageNum: 1,
        pageSize: 10,
      },
      bannerList: [],
      total: 0,
      loading: false,
      addBtn: {
        title: "添加轮播图",
        show: false,
      },
      formData: {
        bnId: undefined,
        bnContent: "",
        bnUrl: "",
        bnIsShow: 1,
      },
      rules: {
        bnContent: [
          {
            required: true,
            message: "请输入轮播图内容",
            trigger: "blur",
          },
        ],
        bnUrl: [
          {
            required: true,
            message: "请输入轮播图图片",
            trigger: "blur",
          },
        ],
      },
    };
  },
  created() {
    this.getBannerList();
  },
  methods: {
    getBannerList() {
      this.loading = true;
      $bannerList(this.params).then((res) => {
        this.total = res.data.total;
        this.bannerList = res.data.data;
        this.loading = false;
      });
    },
    addBannerBtn() {
      this.addBtn.title = "添加轮播图";
      this.addBtn.show = true;
    },
    updateBanner(row) {
      this.formData = row;
      this.addBtn.title = "修改轮播图";
      this.addBtn.show = true;
    },
    delBanner(bnId) {
      $delBanner(bnId).then((res) => {
        this.$notify.success(res.msg);
        this.getBannerList();
      });
    },
    submitForm() {
      this.$refs["bannerForm"].validate((valid) => {
        if (valid) {
          if (this.addBtn.title == "添加轮播图") {
            $addBanner(this.formData).then((res) => {
              this.$notify.success(res.msg);
              this.resetForm("bannerForm");
              this.addBtn.show = false;
              this.getBannerList();
            });
          } else {
            $updateBanner(this.formData).then((res) => {
              this.$notify.success(res.msg);
              this.resetForm("bannerForm");
              this.addBtn.show = false;
              this.getBannerList();
            });
          }
        }
      });
    },
    resetQuery() {
      this.resetForm("bannerForm");
      this.addBtn.show = false;
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