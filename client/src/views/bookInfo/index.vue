<template>
  <div style="margin: 0 32%">
    <el-card shadow="never">
      <el-row :gutter="20" style="margin-top: 30px">
        <el-col :span="10" :xs="24">
          <el-image
            v-if="book.bookCover !== undefined"
            style="width: 100%; height: 320px"
            lazy
            :src="book.bookCover"
            :preview-src-list="[book.bookCover]"
            fit="fit"
          ></el-image>
        </el-col>
        <el-col :span="14" :xs="24">
          <el-descriptions
            class="margin-top"
            :title="book.bookName"
            :column="1"
            style="margin-top: 0px"
          >
            <el-descriptions-item label="作者">{{
              book.bookAuthor
            }}</el-descriptions-item>
            <el-descriptions-item label="类型">{{
              book.typeName
            }}</el-descriptions-item>
            <el-descriptions-item label="ISBN号">{{
              book.bookIsbn
            }}</el-descriptions-item>
            <el-descriptions-item label="上架时间">
              {{ parseTime(book.bookAddDate, "{y}-{m}-{d} {h}:{i}") }}
            </el-descriptions-item>
            <el-descriptions-item label="库存量">{{
              book.bookStock
            }}</el-descriptions-item>
            <el-descriptions-item label="价格"
              ><span class="price"
                >￥{{ book.bookPrice * order.orderNum }}</span
              ></el-descriptions-item
            >
            <el-descriptions-item
              label="购买数量"
              :labelStyle="{ 'line-height': '40px' }"
              ><el-input-number
                style="width: 60%"
                v-model="order.orderNum"
                :min="1"
                :max="book.bookStock"
                type="number"
              ></el-input-number
            ></el-descriptions-item>
          </el-descriptions>
          <el-row style="margin-top: 10px">
            <el-button
              icon="el-icon-shopping-cart-2"
              @click="addCar(book.bookId)"
              class="addCar"
              type="primary"
              >加入购物车</el-button
            >
            <el-button
              icon="el-icon-shopping-bag-2"
              class="toBuy"
              @click="toBuy(book.bookId)"
              >立即购买</el-button
            >
          </el-row>
        </el-col>
      </el-row>
      <div style="margin-top: 40px">
        <el-divider content-position="left"
          ><span style="font-size: 20px">书籍简介</span></el-divider
        >
        <div>
          {{ book.bookDesc }}
        </div>
      </div>
      <div style="margin-top: 40px">
        <el-divider content-position="left"
          ><span style="font-size: 20px">评论</span></el-divider
        >
        <div>
          <el-input
            :placeholder="tipTag.placeholder"
            class="input-with-select"
            v-model="cmParam.cmContent"
          >
            <el-button
              type="primary"
              slot="append"
              icon="el-icon-s-promotion"
              @click="toComment"
              >评论</el-button
            >
          </el-input>
          <el-tag
            v-show="tipTag.show"
            closable
            style="margin-top: 10px"
            @close="closeTag"
            >{{ tipTag.title }}</el-tag
          >
          <el-collapse accordion style="margin-top: 20px">
            <div
              v-for="common in commons"
              :key="common.cmId"
              style="margin-top: 1px"
            >
              <el-collapse-item v-if="common.childComment.length > 0">
                <template slot="title">
                  <div class="common">
                    <el-row :gutter="22">
                      <el-col :span="4" class="header">
                        <div @click.stop.prevent="reComment(common)">
                          <el-avatar
                            :src="common.avatar"
                            :size="60"
                            style="cursor: pointer"
                          ></el-avatar>
                        </div>
                      </el-col>
                      <el-col :span="20" class="body">
                        <p
                          class="username"
                          @click.stop.prevent="reComment(common)"
                        >
                          {{ common.username }}
                        </p>
                        <p class="content">{{ common.cmContent }}</p>
                        <p class="date">
                          {{ parseTime(common.cmDate, "{y}-{m}-{d} {h}:{i}") }}
                        </p>
                      </el-col>
                    </el-row>
                  </div>
                </template>
                <div>
                  <div
                    class="common"
                    style="margin-left: 20px"
                    v-for="cmChild in common.childComment"
                  >
                    <el-row :gutter="22">
                      <el-col :span="4" class="header">
                        <el-avatar :src="cmChild.avatar" :size="60"></el-avatar>
                      </el-col>
                      <el-col :span="20" class="body">
                        <p class="username">{{ cmChild.username }}</p>
                        <p class="content">{{ cmChild.cmContent }}</p>
                        <p class="date">
                          {{ parseTime(cmChild.cmDate, "{y}-{m}-{d} {h}:{i}") }}
                        </p>
                      </el-col>
                    </el-row>
                  </div>
                </div>
              </el-collapse-item>
              <div v-else>
                <div class="common">
                  <el-row :gutter="22">
                    <el-col :span="4" class="header">
                      <div @click.stop.prevent="reComment(common)">
                        <el-avatar
                          :src="common.avatar"
                          :size="60"
                          style="cursor: pointer"
                          @click="reComment(common)"
                        ></el-avatar>
                      </div>
                    </el-col>
                    <el-col :span="20" class="body">
                      <p class="username" @click="reComment(common)">
                        {{ common.username }}
                      </p>
                      <p class="content">{{ common.cmContent }}</p>
                      <p class="date">
                        {{ parseTime(common.cmDate, "{y}-{m}-{d} {h}:{i}") }}
                      </p>
                    </el-col>
                  </el-row>
                  <div style="border-bottom: 1px solid #ebeef5"></div>
                </div>
              </div>
            </div>
          </el-collapse>
          <el-pagination
            :hide-on-single-page="total <= 1"
            layout="prev, pager, next"
            :total="total"
            :page-size.sync="params.pageSize"
            :current-page.sync="params.pageNum"
            style="float: right"
            @current-change="getCommonList"
            @prev-click="getCommonList"
            @next-click="getCommonList"
          >
          </el-pagination>
        </div>
      </div>
    </el-card>

    <el-dialog title="提示" :visible.sync="payType" width="20%">
      <span>采用扫码支付还是网页支付</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="scanPay">扫码支付</el-button>
        <el-button type="primary" @click="webPay">网页支付</el-button>
      </span>
    </el-dialog>

    <el-dialog
      title="请使用支付宝扫码支付"
      :visible.sync="qrCode"
      width="300px"
      @close="scanClose"
    >
      <div ref="qrcodeCanvas"></div>
    </el-dialog>
  </div>
</template>

<script>
import {
  $bookInfo,
  $addCar,
  $commonList,
  $toComment,
  $buyBook,
  $scanBuyBook,
  $queryOrder,
  $updateOrder,
} from "@/api/book";
import QRCode from "qrcodejs2";
export default {
  name: "bookInfo",
  data() {
    return {
      bookId: undefined,
      book: [],
      orderId: undefined,
      order: {
        orderNum: 1,
        bookId: undefined,
      },
      params: {
        pageNum: 1,
        pageSize: 10,
        bookId: undefined,
      },
      commons: [],
      total: 0,
      cmParam: {
        bookId: undefined,
        cmContent: undefined,
        cmParentId: undefined,
      },
      tipTag: {
        title: undefined,
        show: false,
        placeholder: "输入您的评论",
      },
      payType: false,
      qrCode: false,
      timer: null,
    };
  },
  created() {
    this.bookId = this.$route.params.bookId;
    let orderNum = this.$route.query.orderNum;
    this.order.orderNum = orderNum ? orderNum : 1;
    if (this.bookId != null) {
      this.getBookInfo();
      this.params.bookId = this.bookId;
      this.getCommonList();
    }
  },
  methods: {
    getBookInfo() {
      $bookInfo(this.bookId).then((res) => {
        if (res.data == null) {
          this.$alert("书籍不存在", "提示", {
            confirmButtonText: "退出",
            type: "warning",
            callback: (action) => {
              this.$router.go(-1);
            },
          });
        } else {
          this.book = res.data;
        }
      });
    },
    addCar(bookId) {
      if (
        this.order.orderNum < 0 ||
        this.order.orderNum > this.book.bookStock
      ) {
        this.$notify.warning("库存不足");
      } else {
        this.order.bookId = bookId;
        $addCar(this.order).then((res) => {
          this.$notify.success(res.msg);
        });
      }
    },
    toBuy(bookId) {
      this.order.bookId = bookId;
      this.payType = true;
    },
    // 网页支付
    async webPay() {
      if (
        this.order.orderNum < 0 ||
        this.order.orderNum > this.book.bookStock
      ) {
        this.$notify.warning("库存不足");
      } else {
        await $buyBook({
          bookId: this.order.bookId,
          orderNum: this.order.orderNum,
        }).then((res) => {
          let newWindow = window.open("", "_blank");
          newWindow.document.open();
          newWindow.document.write(res);
          newWindow.document.close();
        });
        this.payType = false;
      }
    },
    // 扫码支付
    scanPay() {
      $scanBuyBook({
        bookId: this.order.bookId,
        orderNum: this.order.orderNum,
      }).then((res) => {
        res=res.data;
        this.qrCode = true;
        this.orderId=res.orderId;
        this.$nextTick(() => {
          const canvas = this.$refs.qrcodeCanvas;
          new QRCode(canvas, {
            text: res.qrcode, // 需要转换为二维码的内容
            width: 150,
            height: 150,
            colorDark: "#000000",
            colorLight: "#ffffff",
            correctLevel: QRCode.CorrectLevel.H,
          });
          this.timerQuery();
        });
      });
      this.payType = false;
    },
    timerQuery() {
      // 轮询查询订单状态
      this.timer = setInterval(() => {
        $queryOrder(this.orderId).then((res) => {
          console.log("状态：", res);
        });
      }, 2000);
    },
    scanClose() {
      console.log("关闭扫码框");
      clearInterval(this.timer);
      this.$refs.qrcodeCanvas.innerHTML = "";
      // 跟新订单列表
      $updateOrder(this.orderId);
    },
    getCommonList() {
      $commonList(this.params).then((res) => {
        this.commons = res.data.data;
        this.total = res.data.total;
      });
    },
    // 发送评论
    toComment() {
      this.cmParam.bookId = this.bookId;
      if (this.cmParam.cmContent == null || this.cmParam.cmContent == "") {
        this.$notify.error("评论内容为空");
      } else {
        $toComment(this.cmParam).then((res) => {
          this.getCommonList();
          this.$notify.success(res.msg);
          this.cmParam.cmContent = undefined;
          this.cmParam.cmParentId = undefined;
        });
      }
    },
    reComment(comment) {
      this.tipTag.title = "回复给：" + comment.username;
      this.tipTag.placeholder = "回复给：" + comment.username;
      this.tipTag.show = true;
      this.cmParam.cmParentId = comment.cmId;
    },
    closeTag() {
      this.tipTag.show = false;
      this.tipTag.placeholder = "输入您的评论";
      this.cmParam.cmParentId = undefined;
    },
  },
};
</script>

<style scope>
.el-descriptions-item__label,
.el-descriptions-item__content {
  color: #000;
  font-size: 16px;
}
.price {
  color: #c30;
  font-size: 20px;
  line-height: 24px;
}

.addCar {
  background-color: #ff2832;
  color: #fff;
}

.toBuy {
  background-color: #fefefe;
  color: gray;
}

.addCar:hover,
.addCar:focus {
  background-color: #f00000;
  color: #ffc4c4;
  border-color: #f00000;
}

.addCar.is-active,
.addCar.active {
  background-color: #f00000;
  color: #ffc4c4;
  border-color: #f00000;
}

.toBuy:hover,
.toBuy:focus {
  background-color: #ffb8b8;
  color: #ff5d5d;
  border-color: #ffb8b8;
}

.toBuy.is-active,
.toBuy.active {
  background-color: #ffb8b8;
  color: #ff5d5d;
  border-color: #ffb8b8;
}

/* common */
.el-collapse-item__header {
  min-height: 100px;
}

.common {
  width: 100%;
}

.common .header {
  height: 80px;
}

.common .body p {
  margin: 0;
}

.common .body .username {
  color: #61666d;
  font-size: 14px;
  line-height: 22px;
  display: inline-block;
  cursor: pointer;
}

.common .body .content {
  color: #18191c;
  font-size: 16px;
  line-height: 20px;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 2;
  overflow: hidden;
}

.common .body .date {
  color: #9499a0;
  font-size: 13px;
  line-height: 22px;
}
</style>