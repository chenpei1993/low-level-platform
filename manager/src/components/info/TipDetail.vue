<template>
  <div>
    <el-table :data="tips">
      <el-table-column property="startDateTime" label="提醒时间" width="150" />
      <el-table-column property="sendType" label="推送方式" width="100" />
      <el-table-column property="sendMessage" label="文本模板" width="400" />
    </el-table>
  </div>
</template>

<script>

export default {
  name: "info-tips",
  props: {
    tips: {
      require: true,
      type: Array,
      default() {
        return []
      }
    }
  }
}
</script>