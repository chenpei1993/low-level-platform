<template>
  <div class="menu">
    <el-scrollbar>
      <el-menu router>
        <el-menu-item index="/Home">主页</el-menu-item>
        <el-menu-item-group title="活动配置" v-auth="'activity'">
          <el-menu-item index="/Info" v-auth="'activity:info'">问卷</el-menu-item>
        </el-menu-item-group>
        <el-menu-item-group title="客户关系" v-auth="'relation'">
          <el-menu-item index="/Custom" v-auth="'relation:custom'">客户</el-menu-item>
          <el-menu-item index="/Tag" v-auth="'relation:tag'">标签</el-menu-item>
        </el-menu-item-group>
        <el-menu-item-group title="系统设置" v-auth="'setting'">
          <el-menu-item index="/User" v-auth="'setting:user'">用户</el-menu-item>
          <el-menu-item index="/Role" v-auth="'setting:role'">角色</el-menu-item>
          <el-menu-item index="/Permission" v-auth="'setting:permission'">接口权限</el-menu-item>
          <el-menu-item index="/DataPermission" v-auth="'setting:data-permission'">数据权限</el-menu-item>
        </el-menu-item-group>
      </el-menu>
    </el-scrollbar>
  </div>
</template>

