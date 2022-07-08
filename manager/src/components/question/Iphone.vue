<template>
  <div class="wrapper">
    <div class="iphone">
      <div class="screen-wrapper">
        <div style="  display: flex;justify-content: space-around; align-items: center;">
          <div class="indicator time">{{time}}</div>
          <div class="notch"></div>
          <div class="indicator time none-color">16:22</div>
        </div>
        <div class="screen">
          <Content :info="info"/>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Content from '@/components/question/Content.vue'
export default {
  name: 'Iphone',
  components: {
    Content
  },
  props: {
    info: {
      require: true,
      type: Object,
      default () {
        return {}
      }
    }
  },
  data(){
    return {
      time: "",
      timer: null
    }
  },
  methods: {
    getTime(){
      let now = new Date()
      let hours = now.getHours()
      if( hours < 10){
        hours = '0' + hours
      }
      let minutes = now.getMinutes()
      if( minutes < 10){
        minutes = '0' + minutes
      }
      return hours + ":" + minutes
    }
  },
  mounted() {
    this.time = this.getTime();
    this.timer = setInterval(()=>{
      this.time = this.getTime()
    }, 6000)
  }
}
</script>
<style scoped>
*, *:before, *:after {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}

.iphone {
  position: relative;
  width: 300px;
  height: 600px;
  background-color: black;
  border-radius: 25px;
  display: flex;
  justify-content: center;
  padding: 10px;
}

.screen-wrapper {
  position: relative;
  overflow: hidden;
  border-radius: 25px;
  border-color: black;
  /* background: linear-gradient(#4a3972, #5e4a9d); */
  background: white;
  width: 100%;
}

.notch{
  height: 25px;
  width: 140px;
  background-color: black;
  border-radius: 0 0 15px 15px;
}

.screen {
  height: calc(600px - 45px);
}

.indicator {
  font-size: 10px;
  color: black;
}

.time{
  padding-top: 5px;
}

.none-color{
  color: rgba(0,0,0,0);
}

.wrapper {
  display: flex;
  flex-direction: column;
  justify-content: center;
  font-family: 'Open Sans', sans-serif;
}



</style>