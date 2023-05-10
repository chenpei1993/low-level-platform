<template>
  <div>
    <div class="row">题目类型：</div>
    <el-select v-model="question.type" class="m-2" placeholder="请选择问题类型">
      <el-option
          v-for="item in typeOptions"
          :key="item.value"
          :label="item.label"
          :value="item.value"
          onchange="changeType"
      />
    </el-select>

    <div class="row">是否为必选</div>
    <el-select v-model="question.required" class="m-2" placeholder="请选择问题是否必选">
      <el-option
          v-for="item in isRequiredOptions"
          :key="item.value"
          :label="item.label"
          :value="item.value"
          onchange="changeType"
      />
    </el-select>

    <div class="row">问题描述：</div>
    <el-input v-model="question.questionDesc"
              :rows="3"
              type="textarea"
              placeholder="请输入问题描述" />

    <div v-if="question.type === 2 || question.type === 3">
        <div class="row">选项：</div>
        <div class="option-panel">
          <el-input v-model="option" placeholder="选项" class="option-item"></el-input>
          <el-button type="primary" @click="addNewOptions" size="small" plain>添加选项</el-button>
        </div>
        <div v-for="(o, index) in question.options">
          <div class="option-show">
            <div>选项 {{index + 1}}</div>
            <div>{{o}}</div>
            <el-button size="small" type="danger" @click="delOption(index)" plain>删除</el-button>
          </div>
        </div>
      </div>
    </div>

  <div class="row bottom-panel">
    <el-button type="success" @click="confirm" size="small" plain>确定</el-button>
  </div>
</template>

<script>
import { ElMessage } from 'element-plus'
export default {
  name: "question-add",
  props: {
    question: {
      require: true,
      type: Object,
      default () {
        return {}
      }
    }
  },
  emits: ["addOrEditQuestion"],
  setup(_,{ emit }) {
    const addOrEditQuestion = (question) => {
      emit("addOrEditQuestion", question)
    }
    return {addOrEditQuestion}
  },
  model: {
    prop: 'question',
    event: 'addQuestion'
  },
  data(){
    return {
      isShowAddPanel: false,
      type: "",
      typeOptions:[
        { value: 1, label: "简答题"},
        { value: 2, label: "单选题"},
        { value: 3, label: "多选题"}
      ],
      isRequiredOptions:[
        { value: false, label: "否"},
        { value: true, label: "是"},
      ],
      questionDesc: "",
      option: "",
      options: []
    }
  },
  methods:{
    changeType(){
      this.question = {}
    },
    addNewOptions(){
      if(this.question.options == null){
        this.question.options = []
      }
      this.question.options.push(this.option)
      this.option = ""
    },
    confirm(){
        this.addOrEditQuestion(this.question)
    },
    delOption(idx){
      let options = this.question.options
      let _options = []
      for(let i = 0; i < options.length; i++){
        if(i !== idx){
          _options.push(options[i])
        }
      }
      this.question.options = _options
    }
  }
}

</script>

<style scoped>
.row{
  margin: 5px;
}
.option-panel{
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-top: 5px;
    margin-bottom: 5px;
}

.bottom-panel{
    display: flex;
    justify-content: flex-end;
    margin-top: 20px;
}

.option-item{
  margin-right: 5px;
}

.option-show{
    display: flex;
    justify-content: space-between;
    margin: 5px;
    font-size: 14px;
}
</style>