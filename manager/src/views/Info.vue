<template>
  <div class="info">
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/' }">主页</el-breadcrumb-item>
      <el-breadcrumb-item>问卷</el-breadcrumb-item>
    </el-breadcrumb>
    <el-divider />
    <div>
        <el-button type="success" size="small" @click="add" v-auth="'activity:info:add'" plain>添加</el-button>
    </div>
    <div>
        <el-table :data="infos" stripe style="width: 100%" v-loading="loading">
            <el-table-column prop="name" label="名称" width="100"/>
            <el-table-column prop="title" label="网页标题"/>
            <el-table-column prop="startDateTime" label="活动开始时间" width="180" />
            <el-table-column prop="endDateTime" label="活动结束时间" width="180"/>
            <el-table-column prop="autoSend" label="定时推送" width="100">
              <template #default="scope">
                <span>{{scope.row.sendTasks.length ? "是" : "否"}}</span>
              </template>  
            </el-table-column>
            <el-table-column prop="" label="提醒定时器" width="100">
              <template #default="scope">
                  <el-button link type="primary" size="small" @click="showTipDetail(scope.row)">提醒定时器详情</el-button>
              </template>  
            </el-table-column>
            <el-table-column prop="" label="推送定时器" width="100">
              <template #default="scope">
                  <el-button link type="primary" size="small" @click="showSendDetail(scope.row)">推送定时器详情</el-button>
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
                    <el-button link type="primary" size="small" @click="editQuestions(scope.row)"
                               v-if="scope.row.status === 1" v-auth="'activity:info:edit_question'">编辑问题
                    </el-button>
                    <el-button link type="primary" size="small" @click="publish(scope.row)"
                               v-if="scope.row.status === 1" v-auth="'activity:info:publish'">发布
                    </el-button>
                    <el-button link type="primary" size="small" @click="preview(scope.row)"
                               v-auth="'activity:info:preview'">预览
                    </el-button>
                    <el-button link type="primary" size="small" @click="edit(scope.row)" v-if="scope.row.status === 1"
                               v-auth="'activity:info:edit'">编辑
                    </el-button>
                    <el-button link type="primary" size="small" @click="stop(scope.row)" v-if="scope.row.status === 2"
                               v-auth="'activity:info:stop'">停用
                    </el-button>
                    <el-button link type="primary" size="small" @click="del(scope.row)" v-if="scope.row.status === 1"
                               v-auth="'activity:info:del'">删除
                    </el-button>
                    <el-button link type="primary" size="small" @click="showResult(scope.row)"
                               v-auth="'activity:info:result'">收集结果
                    </el-button>
                    <el-button link type="primary" size="small" @click="showQRCode(scope.row)"
                               v-if="scope.row.status === 2" v-auth="'activity:info:show_addr'">显示地址
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
        <div class="pagination">
          <el-pagination
              v-model:currentPage="currentPage"
              v-model:page-size="pageSize"
              :page-sizes="[10, 15, 20, 25, 50]"
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
        v-model="isShowSendDetail"
        direction="ltr"
        title="定时器详情"
        :before-close="closeSendDetail"
        size="50%"
    >
      <SendDetail ref="SendDetail" :infoId="info.id"/>
    </el-drawer>
    <el-drawer
        v-model="isShowTipDetail"
        direction="ltr"
        title="提醒器详情"
        :before-close="closeTipDetail"
        size="80%"
    >
      <TipDetail ref="TipDetail" :infoId="info.id"/>
    </el-drawer>

    <el-drawer
        v-model="isShowPreview"
        direction="ltr"
        title="预览"
        :before-close="closePreview"
        size="50%"
    >
        <div style="display: flex; justify-content: center;">
          <Iphone :info="infoData" />
        </div>
    </el-drawer>

      <el-drawer
              v-model="isShowQRCode"
              direction="ltr"
              title="前端页面地址"
              :before-close="closeQRCode"
              size="50%"
      >
          <QRCode :info="info" />
      </el-drawer>
  </div>
</template>

<script>
import Add from '@/components/info/Add.vue'
import SendDetail from '@/components/info/SendDetail.vue'
import TipDetail from '@/components/info/TipDetail.vue'
import Iphone from '@/components/iphone/Iphone.vue'
import QRCode from '@/components/info/QRCode.vue'
import {ElMessage, ElMessageBox} from 'element-plus'
import {inject} from "vue"

export default {
  name: 'Info',
  components: {
    Add, SendDetail, TipDetail, Iphone, QRCode
  },
  data(){
    return {
      loading: false,
      currentPage: 1,
      pageSize: 15,
      total: 0,
      isShowSendDetail: false,
      isShowTipDetail: false,
      addOrEdit: "未知",
      isShowAddOrEditPanel: false,
      isShowPreview: false,
      isShowQRCode: false,
      info:{},
      infoData: {
          questions:[]
      },
      infos: [],
      infoDetails:[],
      tagOptions:[],
    }
  }, 
  methods: {
    closeAddOrEditPanel(){
      this.isShowAddOrEditPanel = false
    },
    addOrEditInfo(info){
        console.log(info)
        //TODO 检查参数
        if(this.lodash.isEmpty(info.name)){
            ElMessage.error("问卷名称不能为空")
            return
        }

        if(this.lodash.isEmpty(info.title)){
            ElMessage.error("问卷标题不能为空")
            return
        }

        if(this.lodash.isNil(info.startDateTime)){
            ElMessage.error("问卷开始日期不能为空")
            return
        }

        if(this.lodash.isNil(info.endDateTime)){
            ElMessage.error("问卷结束时间不能为空")
            return
        }

        if(info.startDateTime >= info.endDateTime){
          ElMessage.error("问卷开始日期应该小于问卷结束日期")
          return
        }

        if(info.sendDateTime > info.endDateTime && info.sendDateTime < info.endDateTime){
          ElMessage.error("问卷推送时间应该大于问卷开始时间，并且小于问卷结束时间")
        }
        
        // if(this.info.repeatValue !== null && typeof(this.info.repeatValue) !== "undefined"){
        //   this.info.repeatValue = this.info.repeatValue.split(",")
        // }

        if(this.lodash.isNil(info.id)){
          this.http.put("info", info)
              .then(()=>{
                ElMessage({
                  type: "success",
                  message: "添加成功",
                })
                this.closeAddOrEditPanel()
                this.refresh()
              })
        }else{
          this.http.post("info", info)
              .then(()=>{
                ElMessage({
                  type: "success",
                  message: "编辑成功",
                })
                this.closeAddOrEditPanel()
                this.refresh()
              })
        }

    },
    preview(info){
        console.log(info)
      this.http.get("question/getByInfoId/" + info.id)
          .then((data)=>{
              //todo 排序
              this.infoData.questions = data
              this.isShowPreview = true
          })
    },
    closePreview(){
        this.isShowPreview = false
    },
    showQRCode(info){
        this.info = info
        this.isShowQRCode = true;
    },
    closeQRCode(){
        this.isShowQRCode = false;
    },
    edit(info){
      this.addOrEdit = "编辑"
        this.info = JSON.parse(JSON.stringify(info))

        this.info.autoSend = info.sendTasks != null && info.sendTasks.length > 0
        let tipTimes = []
        let endTime = new Date(info.endDateTime).getTime() / 1000
        for(let i = 0; i < info.tipTasks.length; i++){
            let t = info.tipTasks[i]
            let time = new Date(t.executionDateTime).getTime() / 1000
            let d = endTime - time
            let timeUnit = 3
            let value = d / (3600 * 24)
            if( d >= 60 && d < 3600){
                value = d / 60
                timeUnit = 1
            }else if(d >= 3600 && d < 3600 * 24){
                value = d / 3600
                timeUnit = 2
            }
            tipTimes.push({message: t.sendMessage, tipType: t.sendType, timeUnit: timeUnit, value: value})
        }

        if( this.info.autoSend){
            if(info.repeatCollectType === 1){
                this.info.sendDateTime = info.sendTasks[0].executionDateTime
            }else {
                let time = new Date(info.sendTasks[0].executionDateTime);
                this.info.beginHours = time.getHours()
                this.info.beginMinutes = time.getMinutes()
            }
        }

        this.info.sendCustomerType= 1
        this.info.sendCustomers = info.sendCustomers
        this.info.delayTipTimers = tipTimes
      this.isShowAddOrEditPanel = true
    },
    publish(info){
        this.http.post("info/publish", { id: info.id})
            .then((data)=>{
                ElMessage.success("发布成功")
                this.refresh()
            })
    },
    stop(info){
        this.http.post("info/stop", { id: info.id})
            .then(()=>{
                ElMessage.success("停止成功")
                this.refresh()
            })
    },
    del(info){
      ElMessageBox.confirm(
          '是否删除问卷，会将问题也一并删除',
          '警告',
          {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning',
          })
          .then(() => {
              this.http.delete("info/" + info.id)
                  .then(()=>{
                      ElMessage.success("操作成功")
                      this.refresh()
                  })
          })
          .catch((e) => {
              console.log(e)
              ElMessage.info('取消删除')
          })
    },
    add(){
      this.addOrEdit = "添加"
      this.info = {repeatCollectType: 1}
      this.isShowAddOrEditPanel = true
    },
    showResult(info){
      this.$router.push({ name: 'InfoResult', query: {id: info.id}})
    },
    editQuestions(info){
      this.$router.push({ name: 'Question', query: {id: info.id}})
    },
    showSendDetail(info){
        this.info = info
      this.isShowSendDetail = true
        if(this.$refs.SendDetail != null){
            this.$refs.SendDetail.refresh()
        }
    },
    closeSendDetail(){
      this.isShowSendDetail = false
    },
    showTipDetail(info){
        this.info = info
      this.isShowTipDetail = true
        if(this.$refs.TipDetail != null){
            this.$refs.TipDetail.refresh()
        }
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
    this.lodash = inject("$lodash")
    this.refresh();
    this.refreshTagOptions();
  }
}
</script>
