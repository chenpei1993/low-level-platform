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
            <div style="margin-bottom: 10px;"><el-avatar> 用户 </el-avatar></div>
            <div style="margin-bottom: 10px;"><span>用户名：</span> <span>{{name}}</span></div>
            <div style="margin-bottom: 10px;"><span>角色名：</span> <span>{{role}}</span></div>
          </div>
        </div>
        <div style="width: 45%;">
          <div><span>问卷数: </span> <span>{{infoTotal}} / {{publishInfoTotal}}</span></div>
        </div>
      </div>
    </el-card>

    <el-card class="box-card" v-for="(d, id) in data" :key="id" style="margin-bottom: 15px;">
      <div><span>问卷：</span><span>{{d.name}}</span></div>
      <div :id="'chart_' + id" style="height: 200px;"></div>
    </el-card>

  </div>
</template>

<script>
import { inject } from "vue"

export default {
  name: 'Home',
  data(){
    return {
        data:[],
        publishInfoTotal: 0,
        infoTotal: 0,
        name:"",
        role:"",
    }
  },
  methods: {
      _drawLine(id, dates, values){
          let dom = document.getElementById(id)
          let chart = this.echarts.init(dom)

          let option = {
              xAxis: {
                  type: 'category',
                  data: dates
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
                      data: values,
                      type: 'line'
                  }
              ]
          };
          // 绘制图表
          chart.setOption(option)          // let chart = this.echarts.init(dom);

      },
    drawLine() {
      for(let i = 0; i < this.data.length; i++){
          let data = this.data[i]
          this._drawLine('chart_' + i, data.dates, data.values)
        }
    },
    refresh(){
      this.loading = true
      this.http.get("/home")
          .then((res) => {
            this.data = res.data
            this.publishInfoTotal = res.publishInfoTotal
            this.infoTotal = res.infoTotal
              this.role = res.role
              this.name = res.name
              this.$nextTick(()=>{
                  this.drawLine()
              })
          }).catch(()=>{
        this.loading = false
      })
    }
  },
  mounted() {
    this.refresh()
      console.log(document.getElementById("chart_0"))
  },
  created(){
    this.http = inject("$http")
    this.echarts = inject("$echarts")
  }
}
</script>

