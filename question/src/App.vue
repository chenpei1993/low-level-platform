<template>
  <Question :info="info" v-if="type === 1"/>
</template>

<script>
import Question from './components/Question/Question.vue'
import { inject } from "vue"

export default {
  name: 'App',
  components: {
    Question
  },
  data(){
    return {
      type: -1,
      info: {},
    }
  },
  methods:{

  },
  created() {
    this.http = inject("$http")
    let pathname = window.location.pathname
    pathname = pathname.substring(1, pathname.length)
    let value = pathname.split("/")
    let id = value[0]
    this.http.get( "activity/info/" + id)
        .then((data) => {
          this.info = data
          this.type = 1
        })
  }
}
</script>


