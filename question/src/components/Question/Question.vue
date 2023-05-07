<template>
  <div class="wrapper">
    <div class="question-title">
      <span>{{info.title}}</span>
    </div>
    <hr style="width: 40%;" v-if="info.title != null && info.title"/>
    <div id="mainFrame">
    </div>
    <div class="question-submit-wrapper">
      <button class="question-submit-button"
              v-if="info.questions != null && info.questions.length > 0">提交</button>
    </div>   
  </div>  
</template>

<script>
import {QuestionFactory} from "./js/QuestionFactory"

export default {
  name: "question-content",
  props: {
    info: {
      require: true,
      type: Object,
      default() {
        return {}
      }
    }
  },
  data(){
    return {
      mainFrame: null
    }
  },
  watch: {
    // whenever question changes, this function will run
    info: {
      handler(newInfo){
        this.createFrame()
      },
      deep: true
    }
  },
  methods:{
    createFrame(){

      while(this.mainFrame.children.length > 0){
        this.mainFrame.removeChild(this.mainFrame.lastChild)
      }

      let questions = this.info.questions;
      if(questions == null){
        return
      }

      for(let i = 0; i < questions.length; i++){
          let question = questions[i]
          let q = QuestionFactory.getByType(question)
          q.generate(this.mainFrame)
      }
    }
  },
  mounted(){
    this.mainFrame = document.getElementById("mainFrame")
    this.createFrame()
  }
}
</script>

<style scoped>
@import url("./css/default.css");
</style>

