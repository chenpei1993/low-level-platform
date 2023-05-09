<template>
  <div class="home">
    <el-breadcrumb separator="/">
      <el-breadcrumb-item>主页</el-breadcrumb-item>
    </el-breadcrumb>
    <el-divider />

    <el-card class="box-card" style="margin-bottom: 15px;">
      <div style="display: flex;justify-content: space-between;">
        <div style="width: 45%;">
          <div style="display: flex; flex-direction: column; align-items: center;">
            <div><el-avatar> 用户 </el-avatar></div>
            <div><span>Admin</span></div>
          </div>
        </div>
        <div style="width: 45%;">
          <div><span>问卷数: </span> <span>2</span></div>
        </div>
      </div>
    </el-card>

    <el-card class="box-card">
      <div><span>问卷：</span><span>测试1</span></div>
      <div id="myChart" style="height: 200px;"></div>
    </el-card>

  </div>
</template>

<script>
import { inject } from "vue"

export default {
  name: 'Home',
  data(){
    return {
    }
  },
  methods: {
    drawLine() {
      // var i=0;
      // 基于准备好的dom，初始化echarts实例
      let myChart = this.echarts.init(document.getElementById("myChart"));

      let option = {
        xAxis: {
          type: 'category',
          data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
        },
        yAxis: {
          type: 'value'
        },
        grid:{
          left: 40,
          right: 20,
          top:20,
          bottom: 20
        },
        series: [
          {
            data: [150, 230, 224, 218, 135, 147, 260],
            type: 'line'
          }
        ]
      };
      // 绘制图表
      myChart.setOption(option)
    },
    refresh(){
      this.loading = true
      this.drawLine();

      // this.http.post("/tag/page", {currentPage: this.currentPage, pageSize: this.pageSize})
      //     .then((res) => {
      //       ElMessage.success("更新成功")
      //       this.loading = false
      //       this.tags = res.data
      //       this.total = res.total
      //     }).catch(()=>{
      //   this.loading = false
      // })
    }
  },
  mounted() {
    this.refresh()
  },
  created(){
    this.http = inject("$http")
    this.echarts = inject("$echarts")
  }
}
</script>

