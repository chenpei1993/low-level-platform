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
    //TODO
    this.type = 1;
  }
}
</script>


