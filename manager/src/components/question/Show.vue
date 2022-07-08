<template>
  <div class="wrapper">
    <div v-for="question in questions" class="a-question">
      <el-card >
        <div class="number">{{question.idx}}.</div>
        <div class="show-content">
          <div class="label-1"> 题目类型:</div>
          <div class="content">{{ toType(question.type) }}</div>
          <div class="label-1">问题描述:</div>
          <div class="content">{{ question.questionDesc }}</div>
          <div v-if="question.type === 2 || question.type === 3">
            <div class="label-1">选项: </div>
            <div v-for="o in question.options" class="content">
              <div >{{o}}</div>
            </div>
          </div>
          <div>
            <el-button size="small" type="danger" @click="delQuestion(question.idx)">删除</el-button>
            <el-button size="small" type="primary" @click="editQuestion(question.idx)">编辑</el-button>
          </div>
        </div>
      </el-card>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Show',
  props: {
    questions: {
      require: true,
      type: Object,
      default () {
        return {}
      }
    }
  },
  emits: ["editQuestion", "delQuestion"],
  setup(_,{ emit }) {
    const editQuestion = (idx) => {
      emit("editQuestion", idx)
    }
    const delQuestion = (idx) => {
      emit("delQuestion", idx)
    }
    return {editQuestion, delQuestion}
  },
  methods:{
    //todo
    toType(type){
      if(type === 1){
        return "简答题"
      }else if(type === 2){
        return "单选题"
      }else if(type === 3){
        return "多选题"
      }
      return "未知"
    },
    delQuestion(idx){
      console.log(idx)
      this.delQuestion(idx)
    },
    editQuestion(idx){
      this.editQuestion(idx)
    },
  }
}
</script>

<style scoped>
.a-question{
  margin: 5px;
}

.content{
  margin-left: 10px;
}

.wrapper{
  min-width: 500px;
  overflow-y: scroll;
}

.number{
  margin-right: 10px;
  display: inline-block;
  vertical-align: top;
}

.show-content{
  display: inline-block;
}
</style>
