<template>
  <div>
    <el-card shadow="naver">
      <div ref="myChart" style="width: 100%; height: 650px"></div>
    </el-card>
  </div>
</template>

<script>
import * as echarts from "echarts";
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
          data: ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun","1","2","3"],
        },
        yAxis: {
          type: "value",
        },
        series: [
          {
            data: [820, 932, 901, 934, 1290, 1330, 1320,432,534,256],
            name: "销售量",
            type: "line",
            itemStyle: {
              normal: {
                color: "#fac858"
              },
            },
          },
          {
            name: "销售额",
            type: "bar",
            barWidth: "50%",
            data: [10, 52, 200, 334, 390, 330, 220,123,234,345],
            itemStyle: {
              normal: {
                color: "#91cc75",
              },
            },
          },
        ],
      },
    };
  },
  mounted() {
    this.initChart();

    setTimeout((res) => {
      console.log(this.myChart);
      this.myChart.setOption(this.config);
    }, 1000);
  },
  methods: {
    initChart() {
      this.myChart = echarts.init(this.$refs.myChart);
    },
  },
};
</script>

<style scoped>
</style>