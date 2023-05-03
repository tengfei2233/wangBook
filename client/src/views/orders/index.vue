<template>
  <div style="margin: 0 25%">
    <el-card shadow="never">
      <div slot="header" class="clearfix">
        <span>我的订单</span>
      </div>
      <div v-for="(order, index) in orders" :key="index">
        <el-row :gutter="24">
          <el-col :span="4">
            <el-image
              style="width: 80px; height: 128px"
              v-if="order.bookCover !== undefined"
              lazy
              :src="order.bookCover"
              :preview-src-list="[order.bookCover]"
              fit="fit"
            >
            </el-image>
          </el-col>
          <el-col :span="20">
            <el-row :gutter="22">
              <el-col :span="6">
                <p class="name">{{ order.bookName }}</p>
                <p class="author">{{ order.bookAuthor }}</p>
                <p class="isbn">{{ order.bookIsbn }}</p>
              </el-col>
              <el-col :span="7">
                <p class="num">{{ order.orderNum }}本</p>
                <p class="price">{{ order.orderPrice }}元</p>
                <p class="orderId">{{ order.orderId }}</p>
              </el-col>
              <el-col :span="8">
                <p class="orderDate">
                  下单时间：{{
                    parseTime(order.orderDate, "{y}-{m}-{d} {h}:{i}")
                  }}
                </p>
                <p class="buyDate">
                  下单时间：{{
                    parseTime(order.buyDate, "{y}-{m}-{d} {h}:{i}")
                  }}
                </p>
                <p class="isBuy">
                  <el-tag v-if="order.status == 0" type="danger">未付款</el-tag>
                  <el-tag v-else type="success">已付款</el-tag>
                </p>
              </el-col>
              <el-col :span="3">
                <el-button
                  style="margin-top: 55px"
                  type="text"
                  icon="el-icon-warning-outline"
                  @click="delOrder(order.orderId)"
                  >删除</el-button
                >
              </el-col>
            </el-row>
          </el-col>
        </el-row>
        <el-divider />
      </div>
      <pagination
        v-show="total > 0"
        :total="total"
        :page.sync="params.pageNum"
        :limit.sync="params.pageSize"
        @pagination="getOrderList"
      />
    </el-card>
  </div>
</template>

<script>
import { $orderList, $delOrder } from "@/api/book";
export default {
  name: "orders",
  data() {
    return {
      params: {
        pageNum: 1,
        pageSize: 10,
      },
      orders: [],
      total: 0,
    };
  },
  created() {
    this.getOrderList();
  },
  methods: {
    getOrderList() {
      $orderList(this.params).then((res) => {
        this.orders = res.data.data;
        this.total = res.data.total;
      });
    },
    delOrder(orderId) {
      this.$confirm("是否确认删除该订单?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        $delOrder(orderId).then((res) => {
          this.$notify.success(res.msg);
          this.getOrderList();
        });
      });
    },
  },
};
</script>

<style scoped>
.author {
  font-size: 14px;
  color: gray;
}
.isbn {
  font-size: 14px;
  color: gray;
}
.price {
  margin: 0;
  color: #c30;
  font-size: 18px;
  margin-top: 10px;
}
.num {
  margin: 0;
  margin-top: 15px;
}
.orderId {
  margin: 0;
  margin-top: 15px;
  font-size: 14px;
  color: gray;
}
.orderDate,
.buyDate {
  margin: 0;
  margin-top: 15px;
  font-size: 14px;
  color: gray;
}
</style>