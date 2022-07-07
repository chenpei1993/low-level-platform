<template>
  <div>
    <div>题目类型</div>
    <el-select v-model="question.type" class="m-2" placeholder="Select">
      <el-option
          v-for="item in typeOptions"
          :key="item.value"
          :label="item.label"
          :value="item.value"
      />
    </el-select>

    <div>问题描述</div>
    <el-input v-model="question.questionDesc"
              :rows="3"
              type="textarea"
              placeholder="Please input" />

    <div v-if="type === 2 || type === 3">
        <div class="option-panel row">
          <el-input v-model="option" placeholder="选项"></el-input>
          <el-button type="success" @click="addNewOptions">添加选项</el-button>
        </div>
        <div v-for="o in options">
          <el-input disabled >{{o}}</el-input>
        </div>
      </div>
    </div>

  <div>
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
  emits: ["addQuestion"], // <--- add this line
  setup(_,{ emit }) {
    const addQuestion = (question) => {
      emit("addQuestion", question)
    }
    return {addQuestion}
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
    addNewOptions(){
      this.question.options.push(this.option)
      this.option = ""
    },
    confirm(){
      this.addQuestion(this.question)
    }
  }
}

</script>