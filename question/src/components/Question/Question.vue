<template>
  <div style="margin: 0 auto; max-width: 800px;">
      <div class="wrapper">
          <div class="question-title">
              <span>{{info.title}}</span>
          </div>
          <hr style="width: 40%;" v-if="info.title != null && info.title"/>
          <div id="mainFrame">
          </div>
          <div class="question-submit-wrapper">
              <button class="question-submit-button"
                      @click="submit"
                      v-if="info.questions != null && info.questions.length > 0">提交</button>
          </div>
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
      mainFrame: null,
      questionMap: new Map(),
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
          this.questionMap.set(question.idx, question)
          let q = QuestionFactory.getByType(question)
          q.generate(this.mainFrame)
      }
    },
    loadStyles(){
        let link = document.createElement("link");
        link.rel = "stylesheet";
        link.type = "text/css";
        link.href = this.info.styleUrl;
        let head = document.getElementsByTagName("head")[0];
        head.appendChild(link);
    },
    submit(){
        let questions = this.info.questions;
        if(questions == null){
            return
        }
        let inputs = document.getElementsByTagName("input")
        let map = new Map()
        for(let i = 0; i < inputs.length; i++){
            let e = inputs[i]
            let arr = map.get(e.idx)
            if(arr == null){
                arr = []
            }
            arr.push(e);
            map.set(e.idx, arr)
        }

        let answer = []
        for(let i = 0; i < questions.length; i++){
            let q = questions[i]
            let arr = map.get(q.idx)
            let value = QuestionFactory.getValueByType(q, arr)
            answer.push({id: q.id, value: value})
        }

        let data = {
            infoId: this.info.id,
            answers: answer
        }
        console.log(data)
    }
  },
  mounted(){
    this.mainFrame = document.getElementById("mainFrame")
      // this.loadStyles()
    this.createFrame()
  }
}
</script>

<style scoped>
@import url("./css/default.css");
</style>

