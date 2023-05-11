<template>
  <Question :info="info" v-if="type === 1"/>
  <ShowFullMessage :message="message" v-if="type === -1"/>
  <div class="wrapper" v-if="loading">
      <div style="display: flex; flex-direction: column; align-items: center;">
          <div class="loading" ></div>
          <div style="margin-top: 10px;">问卷初始化中...</div>
      </div>
  </div>
</template>

<script>
import Question from './components/Question/Question.vue'
import ShowFullMessage from './components/ShowFullMessage.vue'
import { inject } from "vue"

export default {
  name: 'App',
  components: {
    Question, ShowFullMessage
  },
  data(){
    return {
      type: -2,
      info: {},
      message:"",
      loading: true
    }
  },
  methods:{

  },
  created() {
    this.http = inject("$http")
    let pathname = window.location.pathname
    pathname = pathname.substring(1, pathname.length)
    let value = pathname.split("/")
    let code = value[0]
    this.http.get( "activity/info/" + code)
        .then((data) => {
            this.loading = false
            if(data.code === 200){
                this.info = data.data
                this.type = 1
            }else{
                this.type = -1
                this.message = data.msg
            }

        })
  }
}
</script>

<style scoped>
.loading {
    width: 30px;
    height: 30px;
    border: 2px solid #000;
    border-top-color: transparent;
    border-radius: 100%;

    animation: circle infinite 0.75s linear;
}

.wrapper{
    width: 100%;
    height: 100%;
    top: 0;
    z-index: 100;
    position: fixed;
    display: flex;
    align-items: center;
    justify-content: center;
    background-color: #ffffff;
}


@keyframes circle {
    0% {
        transform: rotate(0);
    }
    100% {
        transform: rotate(360deg);
    }
}
</style>


