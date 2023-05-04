<template>
  <div>
    <el-card shadow="naver">
      <div ref="myChart" style="width: 100%; height: 650px"></div>
    </el-card>
  </div>
</template>

<script>
import * as echarts from "echarts";
import { $chartData } from "@/api/dashboard";
export default {
  name: "dashboard",
  data() {
    return {
      myChart: null,
      config: {
        tooltip: {
          trigger: "axis",
        },
        legend: {},
        title: {
          text: "过去十天书本销售量与销售额统计图",
        },
        grid: {
          left: "0%",
          right: "0%",
          bottom: "3%",
          containLabel: true,
        },
        xAxis: {
          type: "category",
          data: [],
        },
        yAxis: [
          {
            type: "value",
            name: "销售量",
            axisLine: {
              show: true,
              lineStyle: {
                color: "#fac858",
              },
            },
            axisLabel: {
              formatter: "{value} 本",
            },
          },
          {
            type: "value",
            name: "销售额",
            axisLine: {
              show: true,
              lineStyle: {
                color: "#91cc75",
              },
            },
            axisLabel: {
              formatter: "{value} 元",
            },
          },
        ],
        series: [
          {
            data: [],
            name: "销售量",
            type: "line",
            yAxisIndex: 0,
            itemStyle: {
              normal: {
                color: "#fac858",
              },
            },
          },
          {
            data: [],
            name: "销售额",
            type: "bar",
            barWidth: "50%",
            yAxisIndex: 1,
            itemStyle: {
              normal: {
                color: "#91cc75",
              },
            },
          },
        ],
      },
      date: [],
      numData: [],
      priceData: [],
    };
  },
  mounted() {
    this.initChart();
    this.getChartData();
  },
  methods: {
    initChart() {
      this.myChart = echarts.init(this.$refs.myChart);
    },
    getChartData() {
      $chartData().then((res) => {
        const data = res.data;
        data.num.forEach((item) => {
          this.date.push(item.date);
          this.numData.push(item.num);
        });
        data.price.forEach((item) => {
          this.priceData.push(item.price);
        });
        // chart设置数据
        this.config.xAxis.data = this.date;
        this.config.series[0].data = this.numData;
        this.config.series[1].data = this.priceData;
        this.myChart.setOption(this.config);
      });
    },
  },
};
</script>

<style scoped>
</style>