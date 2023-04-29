<template>
  <div>
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
            placeholder="订单状态"
            @change="selectQuery"
            :clearable="true"
          >
            <el-option label="未付款" value="0"></el-option>
            <el-option label="已付款" value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="val">
          <el-input
            placeholder="请输入内容"
            v-model="params.val"
            class="input-with-select"
            clearable
          >
            <el-select
              v-model="params.pattern"
              slot="prepend"
              placeholder="搜索类型"
            >
              <el-option label="订单id" value="1"></el-option>
              <el-option label="用户名/手机号" value="2"></el-option>
              <el-option label="书籍名" value="3"></el-option>
            </el-select>
          </el-input>
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
    <el-table v-loading="loading" :data="orderList">
      <el-table-column
        label="订单id"
        align="center"
        key="orderId"
        prop="orderId"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="书籍名称"
        align="center"
        key="bookName"
        prop="bookName"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="封面"
        align="center"
        key="bookCover"
        prop="bookCover"
        :show-overflow-tooltip="true"
      >
        <template slot-scope="scope">
          <el-image
            class="courseImg"
            :src="scope.row.bookCover"
            :preview-src-list="[scope.row.bookCover]"
          ></el-image>
        </template>
      </el-table-column>
      <el-table-column
        label="下单时间"
        align="center"
        key="orderDate"
        prop="orderDate"
        :show-overflow-tooltip="true"
      >
        <template slot-scope="scope">
          <span>{{
            parseTime(scope.row.orderDate, "{y}-{m}-{d} {h}:{i}")
          }}</span>
        </template></el-table-column
      >
      <el-table-column
        label="付款时间"
        align="center"
        key="buyDate"
        prop="buyDate"
        :show-overflow-tooltip="true"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.buyDate, "{y}-{m}-{d} {h}:{i}") }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="购买数量"
        align="center"
        key="orderNum"
        prop="orderNum"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="订单金额"
        align="center"
        key="orderPrice"
        prop="orderPrice"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="付款状态"
        align="center"
        key="status"
        prop="status"
        :show-overflow-tooltip="true"
      >
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status == 1" type="success">已付款</el-tag>
          <el-tag v-else type="danger">未付款</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        label="手机号"
        align="center"
        key="phone"
        prop="phone"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="用户名"
        align="center"
        key="username"
        prop="username"
        :show-overflow-tooltip="true"
      />
    </el-table>
    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="params.pageNum"
      :limit.sync="params.pageSize"
      @pagination="getOrderList"
    />
  </div>
</template>

<script>
import { $orderList, $orderInfo } from "@/api/book";
export default {
  name: "orders",
  data() {
    return {
      params: {
        pageNum: 1,
        pageSize: 10,
        pattern: "1",
        val: undefined,
        status: undefined,
      },
      total: 0,
      orderList: [],
      loading: false,
      orderInfo: {},
    };
  },
  created() {
    this.getOrderList();
  },
  methods: {
    getOrderList() {
      this.loading = true;
      $orderList(this.params).then((res) => {
        this.total = res.data.total;
        this.orderList = res.data.data;
        this.loading = false;
      });
    },
    selectQuery() {
      this.getOrderList();
    },
    handleQuery() {
      this.getOrderList();
    },
    resetQuery() {
      this.resetForm("queryForm");
      this.getOrderList();
    },
  },
};
</script>

<style scoped>
::v-deep .el-select .el-input {
  width: 130px;
}

::v-deep .input-with-select .el-input-group__prepend {
  background-color: #fff;
}

.courseImg {
  display: block;
  margin: auto;
  width: 60px;
  height: 120px;
}
</style>