<template>
  <div>
    <el-table :data="infos">
      <el-table-column property="startDateTime" label="开始时间" width="150" />
      <el-table-column property="endDateTime" label="结束时间" width="150" />
      <el-table-column property="sendDateTime" label="推送时间" width="150" />
      <el-table-column property="sendType" label="推送方式" width="100" />
      <el-table-column property="sendMessage" label="文本模板" width="400" />
    </el-table>
  </div>
</template>

<script>

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
  }
}

</script>