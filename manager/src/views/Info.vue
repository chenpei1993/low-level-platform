<template>
  <div class="info">
    <h1>This is an info page</h1>
    <div>
        <el-button link type="primary" size="small" @click="add">添加</el-button>
    </div>
    <div>
        <el-table :data="tableData" stripe style="width: 100%">
            <el-table-column prop="name" label="名称"/>
            <el-table-column prop="startDateTime" label="开始时间" width="150" />
            <el-table-column prop="endDateTime" label="结束时间" width="150"/>
            <el-table-column prop="timer" label="定时任务"/>
            <el-table-column prop="delayTipTimer" label="延时提醒任务"/>
            <el-table-column prop="status" label="状态"/>
            <el-table-column prop="createdAt" label="创建时间" />
            <el-table-column prop="updatedAt" label="更新时间" />
            <el-table-column fixed="right" label="操作" style="width: 200px">
                <template #default="scope">
                    <el-button link type="primary" size="small" @click="editQuestions">编辑问题</el-button>
                    <el-button link type="primary" size="small" @click="publish">发布</el-button>
                    <el-button link type="primary" size="small" @click="preview">预览</el-button>
                    <el-button link type="primary" size="small" @click="edit(scope.row)">编辑</el-button>
                    <el-button link type="primary" size="small" @click="stop">停用</el-button>
                    <el-button link type="primary" size="small" @click="del">删除</el-button>
                    <el-button link type="primary" size="small" @click="showResult">收集结果</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
    <el-drawer
          v-model="isShowAddOrEditPanel"
          :title="addOrEdit"
          direction="ltr"
          :before-close="closeAddOrEditPanel"
          size="50%"
      >
        <Add :info="info" @addOrEditInfo="addOrEditInfo"/>
    </el-drawer>    
  </div>
</template>

<script setup>
const tableData = [
  {
    name: "问卷一",
    startDateTime: "2022-08-08 10:00:00",
    endDateTime: "2022-09-08 10:00:00",
    timer: "",
    delayTipTimer: "",
    createdAt: "2022-08-08 10:00:00",
    updatedAt: "2022-09-08 10:00:00",
  },
]
</script>


<script>
/**
 * 1 使用中
 * 2 已发布
 * 3 停用
 */
import Add from '@/components/info/Add.vue'
export default {
  name: 'Info',
  components: {
    Add
  },
  data(){
    return {
      addOrEdit: "未知",
      isShowAddOrEditPanel: false,
      info: {},
      infos: []
    }
  }, 
  methods: {
    closeAddOrEditPanel(){
      this.isShowAddOrEditPanel = false
    },
    addOrEditCustom(custom){
      if(custom.id == null){
        //TODO fetch
        this.customs.push(custom)
      }
      this.custom = {}
      this.isShowAddOrEditPanel = false
    },
    addOrEditInfo(info){
        console.log(info)
    },
    preview(){

    },
    edit(info){
      console.log(info)
      this.addOrEdit = "编辑",
      this.info = info
      this.isShowAddOrEditPanel = true
    },
    publish(){
        //判断时间
    },
    stop(){

    },
    del(){

    },
    add(){
        this.addOrEdit = "添加",
        this.info = {}
        this.isShowAddOrEditPanel = true
    },
    showResult(){
        
    },
    editQuestions(){

    }
  }
}
</script>
