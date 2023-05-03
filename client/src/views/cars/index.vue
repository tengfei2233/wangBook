<template>
  <div style="margin: 0 30%">
    <el-card shadow="never">
      <div slot="header" class="clearfix">
        <span>我的购物车</span>
      </div>
      <div v-for="car in cars" :key="car.bookId">
        <el-row :gutter="20">
          <el-col :span="4">
            <el-image
              style="width: 80px; height: 128px"
              v-if="car.bookCover !== undefined"
              lazy
              :src="car.bookCover"
              :preview-src-list="[car.bookCover]"
              fit="fit"
            >
            </el-image>
          </el-col>
          <el-col :span="20">
            <el-row :gutter="20">
              <el-col :span="8">
                <p class="name">{{ car.bookName }}</p>
                <p class="author">{{ car.bookAuthor }}</p>
                <p class="isbn">{{ car.bookIsbn }}</p>
              </el-col>
              <el-col :span="8">
                <p class="num">{{ car.orderNum }}本</p>
                <p class="price">{{ car.orderPrice }}元</p>
              </el-col>
              <el-col :span="8">
                <dir style="margin-top: 45px">
                  <el-button
                    type="text"
                    icon="el-icon-warning-outline"
                    @click="info(car)"
                    >详情</el-button
                  >
                  <el-button
                    type="text"
                    icon="el-icon-delete"
                    @click="delCar(car.id)"
                    >删除</el-button
                  >
                </dir>
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
        @pagination="getCarList"
      />
    </el-card>
  </div>
</template>

<script>
import { $carList, $delCar } from "@/api/book";
export default {
  name: "cars",
  data() {
    return {
      params: {
        pageNum: 1,
        pageSize: 10,
      },
      cars: [],
      total: 0,
    };
  },
  created() {
    this.getCarList();
  },
  methods: {
    getCarList() {
      $carList(this.params).then((res) => {
        this.cars = res.data.data;
        this.total = res.data.total;
      });
    },
    delCar(id) {
      $delCar(id).then((res) => {
        this.$notify.success(res.msg);
        this.getCarList();
      });
    },
    info(car) {
      this.$router.push({
        path: "/book/" + car.bookId + "?t=" + new Date().getTime().toString(),
        query: {
          orderNum: car.orderNum,
        },
      });
    },
  },
};
</script>

<style scoped>
.el-divider--horizontal {
  margin: 10px 0;
}

.name {
}
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
  margin-top: 24px;
  text-align: center;
}
.num {
  margin: 0;
  margin-top: 24px;
  text-align: center;
}
</style>