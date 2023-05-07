<template>
  <Question v-if="type === 1"/>
</template>

<script>
import Question from './components/Question/Question.vue'

export default {
  name: 'App',
  components: {
    Question
  },
  data(){
    return {
      type: 1,
    }
  },
  methods:{

  },
  created() {
    this.type = 1
    let pathname = window.location.pathname
    pathname = pathname.substring(1, pathname.length)
    let value = pathname.split("/")
    let id = value[0]
    console.log(id)
  }
}
</script>


