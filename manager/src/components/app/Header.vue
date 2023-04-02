<template>
  <div class="header">
    <div class="title">低代码平台</div>
    <div class="user">
        <el-avatar> 用户 </el-avatar>
        <span class="logout" @click="logout">登出</span>
    </div>
  </div>
</template>
<script>
export default {
    name: "Header",
    methods: {
        logout(){
          //todo
        }
    }
}
</script>
<style scoped>
.header{
  background: #333;
  width: 100%;
  height: 100%;
  color: white;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.title{
  font-size: 20px;
  margin-left: 10px;
}

.user{
    margin-right: 15px;
    cursor: pointer;
}

.logout{
    font-size: 14px;
    margin-left: 10px;
}
</style>