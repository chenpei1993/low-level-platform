<template>
  <div>
      <router-view/>
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
  height: 100%;
  width: 100%;
}
</style>
