<template>
  <div style="margin-top: 10%;">
  <div
    class="echart"
    ref="mychart2"
    id="mychart2"
    :style="{ float: 'left', width: '45%', height: '500px'  }"
  ></div>
  <div></div>
  <div
    class="echart"
    ref="mychart1"
    id="mychart1"
    :style="{ float: 'right', width: '45%', height: '500px' }"
  ></div>
  </div>
</template>

<script>
import { findtop,getcount } from "@/api/user";
import * as echarts from "echarts";
import { ColorPicker } from "element-ui";

export default {
  data() {
    return {
      groups:[],
    };
  },
  mounted() {
    this.initEcharts1();
    this.initEcharts2();
  },
  created: function() {
    
  },
  methods: {
    initEcharts1() {
      getcount()
      .then((response)=>{
        this.groups = response.data.items
        // console.log(response)
        console.log(this.groups)
      const option = {
        title: {
          text: "社团人数前五展示"
        },
        tooltip: {},
        legend: {
          data: ["人数"]
        },
        xAxis: {
          data: [this.groups[0].groupName, this.groups[1].groupName, this.groups[2].groupName, this.groups[3].groupName, this.groups[4].groupName]
        },
        yAxis: {},
        series: [
          {
            name: "人数",
            type: "bar", //类型为柱状图
            data: [this.groups[0].count, this.groups[1].count, this.groups[2].count, this.groups[3].count, this.groups[4].count]
          },
        ]
      };
      const myChart = echarts.init(this.$refs.mychart1);// 图标初始化
      myChart.setOption(option);// 渲染页面
      //随着屏幕大小调节图表
      window.addEventListener("resize", () => {
        myChart.resize();
      });})
    },
    initEcharts2() {
      findtop()
      .then((response)=>{
        this.groups = response.data.item
        console.error(response)
const option = {
    legend: {
        // 图例
        data: [this.groups[0].name, this.groups[1].name, this.groups[2].name, this.groups[3].name, this.groups[4].name],
        right: "10%",
        top: "30%",
        orient: "vertical"
    },
    title: {
        // 设置饼图标题，位置设为顶部居中
        text: "热度前五社团展示",
        top: "48%",
        left: "center"
    },

    series: [
        {
            type: "pie",
            label: {
                show: true,
                formatter: "{b} : {c} ({d}%)" // b代表名称，c代表对应值，d代表百分比
            },
            radius: ["40%", "70%"], //圆环内半径40%，外半径70%
            data: [
                {
                    value: this.groups[0].hot,
                    name: this.groups[0].name
                },
                {
                    value: this.groups[1].hot,
                    name: this.groups[1].name
                },
                {
                    value: this.groups[2].hot,
                    name: this.groups[2].name
                },
                {
                    value: this.groups[3].hot,
                    name: this.groups[3].name
                },
                {
                    value: this.groups[4].hot,
                    name: this.groups[4].name
                }
            ]
        }
    ]
};
      const myChart = echarts.init(this.$refs.mychart2);// 图标初始化
      myChart.on("click", clickFunc);
      function clickFunc(param) {
        alert(param.data.name); // 当前点击对象的name
      }
      myChart.setOption(option);// 渲染页面
      //随着屏幕大小调节图表
      window.addEventListener("resize", () => {
        myChart.resize();
      });
      })
        .catch(error => {
          console.error(error)
        })

    }
  }
};
</script>
