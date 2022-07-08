<template>
  <div class="question">
    <div >
      <div class="item">
        <div class="item">网页标题</div>
        <div class="item">
          <el-input v-model="info.pageTitle" placeholder="网页标题" />
        </div>
      </div>
      <div class="item">  
        <div class="item">标题</div>
        <div class="item">
          <el-input v-model="info.title" placeholder="标题" />
        </div>
      </div>  
      <div class="item item-bottom">
        <el-button type="success" @click="showAddPanel">添加题目</el-button>
        <el-button type="info" @click="saveInfo">保存问卷</el-button>
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
      this.question = {}
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
        if(questions[i].idx !== idx){
          questions[i].idx = n
          _questions.push(questions[i])
          n++
        }
      }
      this.info.questions = _questions
    },
    saveInfo(){
      //todo
    }

  }
}
</script>


<style scoped>
.question{
  display: flex;
  justify-content: space-between;
}

.item{
  margin-top: 5px; margin-bottom: 5px
}

.item-bottom{
  display: flex; 
  justify-content: flex-end;
}
</style>

