<template>
  <div class="question">
    <div >
      <div style="display: flex; justify-content: flex-end;">
        <el-button type="success" @click="showAddPanel">添加题目</el-button>
      </div>
      <el-drawer
          v-model="isShowAddPanel"
          title="添加问题"
          direction="ltr"
          :before-close="closeAddPanel"
      >
        <Add :question="question" @addQuestion="addQuestion"/>
      </el-drawer>
      <div v-for="question in questions">
        <div>题目类型</div>
        <div>{{ question.type }}</div>
        <div>问题描述</div>
        <div>{{ question.questionDesc }}</div>
        <div v-if="question.type === 2 || question.type === 3">
          <div>选项</div>
          <div v-for="o in question.options">
            <el-input disabled >{{o}}</el-input>
          </div>
        </div>
      </div>
    </div>
    <div>
      <Iphone />
    </div>
  </div>
</template>

<script>
import Add from '@/components/question/Add.vue'
import Iphone from '@/components/question/Iphone.vue'

export default {
  name: 'Home',
  components: {
    Add, Iphone
  },
  data(){
    return {
      isShowAddPanel: false,
      question: {},
      questions:[]
    }
  },
  methods:{
    showAddPanel(){
      this.isShowAddPanel = !this.isShowAddPanel
    },
    closeAddPanel(){
      this.isShowAddPanel = false
    },
    addQuestion(question){
      this.questions.push(question)
      this.isShowAddPanel = false
      this.question = {}
    }
  }
}
</script>


<style scoped>
.question{
  display: flex;
  justify-content: space-between;
}
.left-panel{
  width: 50%;
}
.row{
  margin: 5px;
}
.option-panel{
  display: flex;
  justify-content: space-between;
}
</style>

