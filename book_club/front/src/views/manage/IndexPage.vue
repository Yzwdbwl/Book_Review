<script setup>
import * as echarts from 'echarts';
import {onMounted, ref} from "vue";
import request from "@/utils/request";

const indexApi = {
  statistics() {
    return request.get('/index/statistics')
  },
  chartsPieVo() {
    return request.get('/index/chartsPieVo')
  },
  chartsFoldLineVo() {
    return request.get('/index/chartsFoldLineVo')
  },
  top20VO() {
    return request.get('/index/top20VO?data='+(selectYearMonth.value==null?" ":selectYearMonth.value))
  },
}

const indexDate = ref({})

const initIndexDate = () => {
  indexApi.statistics()
      .then(resp => {
        indexDate.value = resp.data.data
      })
}



const chartPieData=ref({
  categories: [],
  salesData: []
})

const initChartsPieVo = () => {
  indexApi.chartsPieVo()
      .then(resp => {
        chartPieData.value.categories = resp.data.data.map((item)=>{return item.type})
        chartPieData.value.salesData = resp.data.data.map((item)=>{ return item.prop})
        createPieChart()
      })
}


const chartData = ref({
  xAxisData: [],
  salesData: [],
  profitData: [],
})


const initChartsFoldLineVo = () => {
  indexApi.chartsFoldLineVo()
      .then(resp => {
        chartData.value.xAxisData = resp.data.data.map((item)=>{return item.month})
        chartData.value.salesData = resp.data.data.map((item)=>{ return item.salesData.toFixed(2)})
        chartData.value.profitData = resp.data.data.map((item)=>{ return item.profitData.toFixed(2)})
        initEcharts()
      })
}



const s1Rf=ref(null)
const s2Rf=ref(null)

const initEcharts = () => {
  const chart = echarts.init(s1Rf.value);
  const option = {
    title: {
      text: '销售总额和利润',
    },
    tooltip: {
      trigger: 'axis',
    },
    legend: {
      data: ['销售总额', '利润'],
    },
    xAxis: {
      type: 'category',
      data: chartData.value.xAxisData,
    },
    yAxis: {
      type: 'value',
    },
    dataZoom: [
      {
        type: 'slider', // 滑动条类型
        show: true, // 显示滑动条
        start: 0, // 起始位置，百分比
        end: 1000, // 结束位置，百分比
      },
    ],
    series: [
      {
        name: '销售总额',
        type: 'line',
        data: chartData.value.salesData,
      },
      {
        name: '利润',
        type: 'line',
        data: chartData.value.profitData,
      },
    ],
  };

  chart.setOption(option);
}



const createPieChart = () => {
      const chart = echarts.init(s2Rf.value);
      const option = {
        title: {
          text: '商品类别销售百分比',
          left: 'center',
        },
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)',
        },
        legend: {
          orient: 'vertical',
          left: 10,
          data: chartPieData.value.categories,
        },
        series: [
          {
            name: '销售百分比',
            type: 'pie',
            radius: '55%',
            center: ['50%', '60%'],
            data: chartPieData.value.categories.map((category, index) => ({
              name: category,
              value: chartPieData.value.salesData[index],
            })),
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)',
              },
            },
          },
        ],
      };
      chart.setOption(option);
}


onMounted(() => {
  initIndexDate()
  initChartsPieVo()
  initChartsFoldLineVo()
  selectYearMonthHandle()

})



const tableData=ref([])


const selectYearMonth=ref("")

const selectYearMonthHandle = () => {
  indexApi.top20VO()
      .then((resp)=>{
       tableData.value=resp.data.data
      })
}

</script>

<template>
  <el-row :justify="'space-around'" :gutter="20" style="text-align: center">
    <el-col :span="6">
      <el-card><p style="color: #79b5ff">订单总数</p>
        <h3>{{ indexDate.dds }}</h3></el-card>
    </el-col>
    <el-col :span="6">
      <el-card><p style="color: #edccad">总销量</p>
        <h3>{{ indexDate.zxl }}</h3></el-card>
    </el-col>
    <el-col :span="6">
      <el-card><p style="color: #f6919c">销售总额</p>
        <h3>${{ indexDate.xse }}</h3></el-card>
    </el-col>
    <el-col :span="6">
      <el-card><p style="color: #8fcf6f">利润</p>
        <h3>${{ indexDate.liLun }}</h3></el-card>
    </el-col>
  </el-row>
  <div style="height: 30px;margin-top: 30px"></div>
  <el-card>
    <el-row :justify="'space-around'" :gutter="20">
      <el-col :span="10">
        <div ref="s1Rf" style="width: 95%;height: 500px"></div>
      </el-col>
      <el-col :span="10">
        <div ref="s2Rf" style="width: 95%;height: 500px"></div>
      </el-col>
    </el-row>
  </el-card>

  <div style="height: 30px;margin-top: 30px"></div>
  <el-card>
    <el-space direction="vertical"  size="30">
      <el-date-picker v-model="selectYearMonth" clea value-format="YYYY-MM-01" type="month" placeholder="请选择月份"></el-date-picker>

    </el-space>

    <el-button style="margin-left: 20px" type="primary" @click="selectYearMonthHandle">确定</el-button>

    <el-table :data="tableData" border style="width: 100%">
      <el-table-column type="index" label="销量排名" width="180" />
      <el-table-column prop="productName" label="商品名称" width="180" />
      <el-table-column prop="productCategory" label="商品类别" width="180" />
      <el-table-column prop="sumQuantity" label="销量" />
      <el-table-column prop="sumUnitPrice" label="销售额" />
      <el-table-column prop="sumEstimatedProfit" label="利润" />
    </el-table>
  </el-card>
</template>

<style scoped lang="scss">

</style>