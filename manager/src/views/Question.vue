<template>
  <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/' }">主页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/Info' }">问卷</el-breadcrumb-item>
      <el-breadcrumb-item>问题</el-breadcrumb-item>
  </el-breadcrumb>
  <el-divider />
  <div class="question">
    <div >
      <div class="item">  
        <div class="item" style="display: flex; justify-content: space-between;">
            <div style="display: flex; align-content: center;">
                <div>标题</div>
            </div>
            <div class="item item-bottom">
                <el-button type="primary" @click="returnToInfo" size="small" plain>返回</el-button>
                <el-button type="success" @click="showAddPanel" size="small" plain>添加题目</el-button>
                <el-button type="info" @click="saveInfo" size="small" plain>保存问卷</el-button>
            </div>
        </div>
        <div class="item">
          <el-input v-model="info.title" placeholder="标题" />
        </div>
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
      <Iphone :info="info" />
    </div>
  </div>
</template>

<script>
import Add from '@/components/question/Add.vue'
import Iphone from '@/components/iphone/Iphone.vue'
import Show from '@/components/question/Show.vue'
import {ElMessage} from "element-plus";
import {inject} from "vue";

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
    returnToInfo(){
      this.$router.push({name: "Info"})
    },
    showAddPanel(){
      this.addOrEdit = "添加"
      this.question = {}
      this.isShowAddPanel = !this.isShowAddPanel
    },
    closeAddPanel(){
      this.isShowAddPanel = false
    },
    addOrEditQuestion(question){
        if(this.lodash.isEmpty(question)){
            this.isShowAddPanel = false
            return
        }

        if(this.lodash.isNil(question.type)){
            ElMessage.warning("题目类型，未选择")
            return
        }
        if(this.lodash.isNil(question.required)){
            ElMessage.warning("题目是否必选，未选择")
            return
        }
        if(this.lodash.isNil(question.questionDesc)){
            ElMessage.warning("题目描述，未填写")
            return
        }
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
        // 限制问题描述的字数
      let data = {}
      data.infoId = this.infoId
      data.title = this.info.title
      data.questionInfoList = this.info.questions
      this.http.put("question", data)
          .then(()=>{
              ElMessage({
                  type: "success",
                  message: "保存成功",
              })
          })
    },
    getInfoQuestion(){
        this.http.get("question/getByInfoId/" + this.infoId)
            .then((data)=>{
                //todo 排序
                this.info.questions = data
            })
    }
  },
  created(){
      this.infoId = this.$route.query.id
      this.http = inject("$http")
      this.lodash = inject("$lodash")
      this.getInfoQuestion()
  }
}
</script>


<style scoped>
.question{
  display: flex;
  justify-content: space-around;
}

.item{
  margin-top: 5px; margin-bottom: 5px
}

.item-bottom{
  display: flex; 
  justify-content: flex-end;
}
</style>

