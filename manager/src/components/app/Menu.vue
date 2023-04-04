<template>
  <div class="menu">
    <el-scrollbar>
      <el-menu router>
        <el-menu-item index="/Home" >主页</el-menu-item>
        <el-menu-item-group title="活动配置">
          <el-menu-item index="/Info" >问卷</el-menu-item>
        </el-menu-item-group>
        <el-menu-item-group title="客户关系">
          <el-menu-item index="/Custom">客户</el-menu-item>
          <el-menu-item index="/Tag">标签</el-menu-item>
        </el-menu-item-group>
        <el-menu-item-group title="系统设置">
          <el-menu-item index="/User">用户</el-menu-item>
          <el-menu-item index="/Role">角色</el-menu-item>
          <el-menu-item index="/Permission">权限</el-menu-item>
        </el-menu-item-group>
      </el-menu>
    </el-scrollbar>
  </div>
</template>


<script setup>
</script>