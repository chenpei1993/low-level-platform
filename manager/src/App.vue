<template>
  <div class="common-layout">
    <el-container>
      <el-header style="padding: 0">
        <Header />
      </el-header>
      <el-container>
        <el-aside width="200px">
          <Menu />
<!--          <router-link to="/">Home</router-link>-->
<!--          <router-link to="/about">About</router-link>-->
        </el-aside>
        <el-main>
          <router-view/>
        </el-main>
      </el-container>
    </el-container>
  </div>

</template>

<script>
import Header from '@/components/app/Header.vue'
import Menu from '@/components/app/Menu.vue'

export default {
  name: 'App',
  components: {
    Header, Menu
  }
}
</script>

<style>
html body{
  padding: 0;
  margin: 0;
}

</style>
