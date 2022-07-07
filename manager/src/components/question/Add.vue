<template>
  <div>
    <div class="row">题目类型：</div>
    <el-select v-model="question.type" class="m-2" placeholder="Select">
      <el-option
          v-for="item in typeOptions"
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
              placeholder="Please input" />

    <div class="row">选项：</div>
    <div v-if="question.type === 2 || question.type === 3">
        <div class="option-panel">
          <el-input v-model="option" placeholder="选项" class="option-item"></el-input>
          <el-button type="success" @click="addNewOptions">添加选项</el-button>
        </div>
        <div v-for="(o, index) in question.options">
          <div class="option-show">
            <div>选项 {{index + 1}}</div>
            <div>{{o}}</div>
            <el-button size="small" type="danger" @click="delOption(index)">删除</el-button>
          </div>
        </div>
      </div>
    </div>

  <div class="row bottom-panel">
    <el-button type="success" @click="confirm">确定</el-button>
  </div>
</template>

<script>

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
      console.log(this.question.options)
      this.option = ""
      console.log(this.question.options)
    },
    confirm(){
      this.addOrEditQuestion(this.question)
    },
    delOption(){

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
}

.bottom-panel{
  display: flex;
  justify-content: flex-end;
}

.option-panel{
  margin-top: 5px;
  margin-bottom: 5px;
}

.option-item{
  margin-right: 5px;
}

.option-show{
  display: flex;
  justify-content: space-between;
  margin: 5px;
}
</style>