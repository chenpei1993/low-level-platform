<template>
  <div class="info">
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/' }">主页</el-breadcrumb-item>
      <el-breadcrumb-item>问卷</el-breadcrumb-item>
    </el-breadcrumb>
    <el-divider />
    <div>
        <el-button type="success" size="small" @click="add" plain>添加</el-button>
    </div>
    <div>
        <el-table :data="infos" stripe style="width: 100%" v-loading="loading">
            <el-table-column prop="name" label="名称" width="100"/>
            <el-table-column prop="title" label="网页标题"/>
            <el-table-column prop="" label="重复信息" width="100">
              <template #default="scope">
                <span> {{ getRepeatInfo(scope.row) }}</span>                  
              </template>  
            </el-table-column>
            <el-table-column prop="startDateTime" label="活动开始时间" width="180" />
            <el-table-column prop="endDateTime" label="推送定时器" width="180"/>
            <el-table-column prop="endDateTime" label="活动结束时间" width="180"/>
            <el-table-column prop="autoSend" label="定时推送" width="100">
              <template #default="scope">
                <span>{{scope.row.autoSend ? "是" : "否"}}</span>
              </template>  
            </el-table-column>
            <el-table-column prop="" label="提醒定时器" width="100">
              <template #default="scope">
                  <el-button link type="primary" size="small" @click="showInfoDetail(scope.row.id)">提醒定时器详情</el-button>
              </template>  
            </el-table-column>
            <el-table-column prop="" label="推送定时器" width="100">
              <template #default="scope">
                  <el-button link type="primary" size="small" @click="showTipDetail(scope.row.id)">推送定时器详情</el-button>
              </template>  
            </el-table-column>  
            <el-table-column prop="status" label="状态">
              <template #default="scope">
                <span>{{getStatus(scope.row.status)}}</span>
              </template>  
            </el-table-column>
            <el-table-column prop="createdAt" label="创建时间" width="180"/>
            <el-table-column prop="updatedAt" label="更新时间" width="180"/>
            <el-table-column fixed="right" label="操作" width="250">
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
        <Add :info="info" :tagOptions="tagOptions"
             :dateTimeRange="[info.startDateTime, info.endDateTime]" @addOrEditInfo="addOrEditInfo"/>
    </el-drawer>

    <el-drawer
        v-model="isShowInfoDetail"
        direction="ltr"
        title="定时器详情"
        :before-close="closeInfoDetail"
        size="50%"
    >
      <SendDetail :infoDetails="infoDetails"/>
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
import SendDetail from '@/components/info/SendDetail.vue'
import TipDetail from '@/components/info/TipDetail.vue'
import { ElMessage } from 'element-plus'
import { inject } from "vue"

export default {
  name: 'Info',
  components: {
    Add, SendDetail, TipDetail
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
      infoDetails:[],
      tagOptions:[]
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
        
        if(this.info.repeatValue !== null && typeof(this.info.repeatValue) !== "undefined"){
          this.info.repeatValue = this.info.repeatValue.split(",")
        }
        this.http.put("info", info)
            .then(()=>{
              ElMessage({
                type: "success",
                message: "添加成功",
              })
              this.closeAddOrEditPanel()
              this.refresh()
            })
    },
    preview(){

    },
    edit(info){
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
      this.info = {repeatCollectType: 1}
      this.isShowAddOrEditPanel = true
    },
    showResult(){
      this.$router.push({ name: 'InfoResult', params: {id: this.info.id}})   
    },
    editQuestions(){
      this.$router.push({ name: 'Question', params: {id: this.info.id}})
    },
    showInfoDetail(){
      this.isShowInfoDetail = true

    },
    closeInfoDetail(){
      this.isShowInfoDetail = false
    },
    showTipDetail(){
      this.isShowTipDetail = true
      this.http.get("/info/tip", {currentPage: this.currentPage, pageSize: this.pageSize})
          .then((res) => {
            ElMessage.success("更新成功")
            this.loading = false
            this.infos = res.data
            this.total = res.total
          }).catch(()=>{
        this.loading = false
      })
    },
    closeTipDetail(){
      this.isShowTipDetail = false
    },
    getStatus(status){
      if(status === 1){
        return "可编辑"
      }else if(status === 2){
        return "已发布"
      }else if(status === 3){
        return "已停用"
      }
      return "未知"
    },
    getRepeatInfo(row){
      if(row.repeatCollectType === 1){
        return "一次"
      }else if(row.repeatCollectType === 10){
        return "每日"
      }else if(row.repeatCollectType === 100){
        return "每周"
      }else if(row.repeatCollectType === 1000){
        return "每周"
      }
      return "每月"
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
    },
    refreshTagOptions(){
      this.http.get("/tag/all")
          .then((res) => {
            this.tagOptions = res
          }).catch(()=>{
      })
    }
  },
  created(){
    this.http = inject("$http")
    this.refresh();
    this.refreshTagOptions();
  }
}
</script>
