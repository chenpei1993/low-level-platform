<template>
  <div class="info">
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/' }">主页</el-breadcrumb-item>
      <el-breadcrumb-item>问卷</el-breadcrumb-item>
    </el-breadcrumb>
    <el-divider />
    <div>
        <el-button link type="primary" size="small" @click="add">添加</el-button>
    </div>
    <div>
        <el-table :data="infos" stripe style="width: 100%" v-loading="loading">
            <el-table-column prop="name" label="名称"/>
            <el-table-column prop="title" label="网页标题"/>
            <el-table-column prop="startDateTime" label="重复次数"/>
            <el-table-column prop="startDateTime" label="活动开始时间" width="150" />
            <el-table-column prop="endDateTime" label="活动结束时间" width="150"/>
            <el-table-column prop="endDateTime" label="是否定时推送" width="150"/>
            <el-table-column prop="status" label="状态"/>
            <el-table-column prop="createdAt" label="创建时间" />
            <el-table-column prop="updatedAt" label="更新时间" />
            <el-table-column fixed="right" label="操作" style="width: 200px">
                <template #default="scope">
                    <el-button link type="primary" size="small" @click="showInfoDetail">问卷详情</el-button>
                    <el-button link type="primary" size="small" @click="showTipDetail">提醒器详情</el-button>
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
        <div class="pagination">
          <el-pagination
              v-model:currentPage="currentPage"
              v-model:page-size="pageSize"
              :page-sizes="[20, 50, 100, 200, 300, 400]"
              layout="total, sizes, prev, pager, next, jumper"
              :total="total"
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
          />
        </div>
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
    <el-drawer
        v-model="isShowInfoDetail"
        direction="ltr"
        title="问卷详情"
        :before-close="closeInfoDetail"
        size="50%"
    >
      <InfoDetail :infoDetails="infoDetails"/>
    </el-drawer>
    <el-drawer
        v-model="isShowTipDetail"
        direction="ltr"
        title="提醒器详情"
        :before-close="closeTipDetail"
        size="50%"
    >
      <TipDetail :infos="infos"/>
    </el-drawer>
  </div>
</template>

<script>
import Add from '@/components/info/Add.vue'
import InfoDetail from '@/components/info/InfoDetail.vue'
import TipDetail from '@/components/info/TipDetail.vue'
import { ElMessage } from 'element-plus'
import { inject } from "vue";

export default {
  name: 'Info',
  components: {
    Add, InfoDetail, TipDetail
  },
  data(){
    return {
      loading: false,
      currentPage: 1,
      pageSize: 50,
      total: 0,
      isShowInfoDetail: false,
      isShowTipDetail: false,
      addOrEdit: "未知",
      isShowAddOrEditPanel: false,
      info: {},
      infos: [],
      infoDetails:[]
    }
  }, 
  methods: {
    closeAddOrEditPanel(){
      this.isShowAddOrEditPanel = false
    },
    addOrEditInfo(info){
        console.log(info)
        if(info.startDateTime >= info.endDateTime){
          ElMessage.error("问卷开始日期应该小于问卷结束日期")
          return
        }

        if(info.sendDateTime > info.endDateTime && info.sendDateTime < info.endDateTime){
          ElMessage.error("问卷推送时间应该大于问卷开始时间，并且小于问卷结束时间")
        }
        this.info.repeatValue = info.repeatValue.split(",")
        this.http.put("info", info)
            .then(()=>{
              ElMessage({
                type: "success",
                message: "ok",
              })
              this.refresh()
            })
    },
    preview(){

    },
    edit(info){
      console.log(info)
      this.addOrEdit = "编辑"
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
        this.addOrEdit = "添加"
        this.info = {}
        this.isShowAddOrEditPanel = true
    },
    showResult(){
      this.$router.push({ name: 'InfoResult', params: {id: this.info.id}})   
    },
    editQuestions(){
      this.$router.push({ name: 'Question', params: {id: this.info.id}})
    },
    showInfoDetail(){

    },
    showTipDetail(){

    },
    closeInfoDetail(){

    },
    closeTipDetail(){

    },
    handleSizeChange(size){
      this.pageSize = size
      this.refresh()
    },
    handleCurrentChange(page){
      this.currentPage = page
    },
    refresh(){
      this.loading = true
      this.http.post("/info/page", {currentPage: this.currentPage, pageSize: this.pageSize})
          .then((res) => {
            ElMessage.success("更新成功")
            this.loading = false
            this.infos = res.data
            this.total = res.total
          }).catch(()=>{
        this.loading = false
      })
    }
  },
  created(){
    this.http = inject("$http")
    this.refresh();
  }
}
</script>
