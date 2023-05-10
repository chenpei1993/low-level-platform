<template>
  <Question :info="info" v-if="type === 1"/>
  <ShowFullMessage :message="message" v-if="type === -1"/>
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
      type: -1,
      info: {},
      message:""
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
            console.log(data)
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


