<template>
  <div>
    <el-table :data="infos" v-loading="loading">
      <el-table-column property="startDateTime" label="提醒时间" width="150" />
      <el-table-column property="sendType" label="推送方式" width="100" />
      <el-table-column property="sendMessage" label="文本模板" width="400" />
      <el-table-column property="status" label="状态" width="50" />
    </el-table>
  </div>
</template>

<script>

import {ElMessage} from "element-plus";

export default {
  name: "info-detail",
  props: {
    infos: {
      require: true,
      type: Array,
      default() {
        return []
      }
    }
  },
  data(){
    return {
      loading: false
    }
  },
  methods:{
    refresh(){
      this.loading = true
      this.http.post("/send_timer/page", {currentPage: this.currentPage, pageSize: this.pageSize})
          .then((res) => {
            ElMessage.success("更新成功")
            this.loading = false
            console.log(res.data)
            this.total = res.total
          }).catch(()=>{
        this.loading = false
      })
    }
  }
}

</script>