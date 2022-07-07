<template>
  <div class="question">
    <div >
      <div>
        网页标题
      </div>
      <div>
        <el-input v-model="info.pageTitle" placeholder="网页标题" />
      </div>
      <div>
        标题
      </div>
      <div>
        <el-input v-model="info.title" placeholder="标题" />
      </div>
      <div style="display: flex; justify-content: flex-end;">
        <el-button type="success" @click="showAddPanel">添加题目</el-button>
      </div>
      <el-drawer
          v-model="isShowAddPanel"
          :title="addOrEdit"
          direction="ltr"
          :before-close="closeAddPanel"
      >
        <Add :question="question"  @addOrEditQuestion="addOrEditQuestion"/>
      </el-drawer>
      <Show :questions="info.questions" @editQuestion="editQuestion" @delQuestion="delQuestion"/>
    </div>
    <div>
      <Iphone :info="info"/>
    </div>
  </div>
</template>

<script>
import Add from '@/components/question/Add.vue'
import Iphone from '@/components/question/Iphone.vue'
import Show from '@/components/question/Show.vue'

export default {
  name: 'Home',
  components: {
    Add, Iphone, Show
  },
  data(){
    return {
      addOrEdit:"",
      isShowAddPanel: false,
      question: {},
      info: {
        questions:[]
      }
    }
  },
  methods:{
    showAddPanel(){
      this.addOrEdit = "添加"
      this.isShowAddPanel = !this.isShowAddPanel
    },
    closeAddPanel(){
      this.isShowAddPanel = false
    },
    addOrEditQuestion(question){
      if(question.idx == null){
        question.idx = this.info.questions.length + 1
        this.info.questions.push(question)
      }
      this.question = {}
      this.isShowAddPanel = false
    },
    editQuestion(idx){
      this.addOrEdit = "编辑"
      this.question = idx - 1 >= 0 ? this.info.questions[idx - 1] : {}
      this.isShowAddPanel = true
    },

    delQuestion(idx){
      let questions = this.info.questions
      let _questions = []
      let n = 1;
      for(let i = 0; i < questions.length; i++){
        console.log(questions[i].idx)
        if(questions[i].idx !== idx){
          questions[i].idx = n
          _questions.push(questions[i])
          n++
        }
      }
      this.info.questions = _questions
    },

  }
}
</script>


<style scoped>
.question{
  display: flex;
  justify-content: space-between;
}
</style>

