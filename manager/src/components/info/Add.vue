<template>
  <div>
    <el-form
        label-position="right"
        label-width="150px"
        :model="info"
        style="width: 100%"
    >
        <el-form-item label="名称">
          <el-input v-model="info.name" sytle="width:220px" />
        </el-form-item>
        <el-form-item label="网页标题">
            <el-input v-model="info.title" sytle="width:220px" />
        </el-form-item>
      <el-form-item label="重复次数">
        <el-select v-model="info.repeatCollectType">
          <el-option
              v-for="item in repeatCollectionTypeOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="每周几" v-if="info.repeatCollectType === 100">
        <el-input v-model="info.repeatValue" sytle="width:200px" />
      </el-form-item>
      <el-form-item label="每月几号" v-if="info.repeatCollectType === 1000">
        <el-input v-model="info.repeatValue" sytle="width:200px" />
      </el-form-item>

      <el-form-item label="活动开始结束时间">
        <el-date-picker
          v-model="dateTimeRange"
          type="datetimerange"
          format="YYYY-MM-DD HH:mm"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          @change="changeDatetimeRange"
        />
      </el-form-item>
      <el-form-item label="开始时间" v-if="info.repeatCollectType !== 1 ">
        <el-input v-model="info.beginHours"  style="width: 50px; margin-right: 3px"/><span class="gap">时</span>
        <el-input v-model="info.beginMinutes"  style="width: 50px; margin-right: 3px" /><span class="gap">分</span>
      </el-form-item>
      <el-form-item label="时长" v-if="info.repeatCollectType !== 1 ">
        <el-input v-model="total.days" style="width: 50px; margin-right: 3px" /><span class="gap">天</span>
        <el-input v-model="total.hours"  style="width: 50px; margin-right: 3px"/><span class="gap">时</span>
        <el-input v-model="total.minutes"  style="width: 50px; margin-right: 3px" /><span class="gap">分</span>
      </el-form-item>
        <el-form-item label="是否定时推送">
            <el-select v-model="info.autoSend">
              <el-option
                v-for="item in isFixedTimeSendOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
        </el-form-item>
      <el-form-item label="推送的文本模板" v-if="info.autoSend">
        <el-input v-model="info.sendMessage" type="textarea" sytle="width:200px" />
      </el-form-item>

        <el-form-item label="定时推送时间" v-if="info.autoSend" >
            <el-date-picker
              v-model="info.sendDateTime"
              type="datetime"
              format="YYYY-MM-DD HH:mm"
              placeholder="选择推送日期时间"
            />
        </el-form-item>
        <el-form-item label="推送方式" v-if="info.autoSend">
          <el-select v-model="info.sendType">
            <el-option
              v-for="item in sendTypeOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="发送人群类型" v-if="info.autoSend">
          <el-select v-model="info.sendCustomerType">
            <el-option
              v-for="item in sendCustomerTypeOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="自定义" v-if="info.autoSend && info.sendCustomerType === 1">
          <el-input type="textarea" v-model="info.sendCustomers" />
        </el-form-item>
        <el-form-item label="标签" v-if="info.autoSend && info.sendCustomerType === 2">
          <el-select v-model="info.sendCustomers" multiple>
            <el-option
                v-for="item in tagOptions"
                :key="item.id"
                :label="item.name"
                :value="item.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item
          v-for="(delayTipTimer, index) in info.delayTipTimers"
          :key="delayTipTimer.key"
          :label="'延时提醒-' + index"
          :prop="'domains.' + index + '.value'"
        >
          <div class="delayTimer">
            <div>
              <span>距离结束时间前
              <el-input v-model="delayTipTimer.value" size="small" style="width: 40px; margin-right:3px;"/> 
              <el-select v-model="delayTipTimer.timeUnit" size="small" style="width: 60px; margin-right:3px;">
                <el-option
                  v-for="item in timeUnitOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                />
                </el-select>提醒,</span>
                <span style="margin-right:3px;">提醒方式</span>
                <el-select v-model="delayTipTimer.tipType" size="small" style="width: 100px; margin-right:3px;">
                  <el-option
                    v-for="item in sendTypeOptions"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
                <div>
                  <span >提醒文本模板</span>
                  <el-input size="small"  type="textarea" v-model="delayTipTimer.message" />
                </div>  
            </div>
            <div>
              <el-button class="mt-2" @click="delDelayTipTimer(index)" type="danger" size="small" style="margin-left:3px;">删除</el-button>
            </div>
          </div>        
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="addDelayTipTimer">添加延时提醒</el-button>
            <el-button type="primary" @click="confirm">确认</el-button>
        </el-form-item>
    </el-form>
  </div>  
</template> 

<script>
import ArrayUtil from '@/util/ArrayUtil'

export default {
  name: "info-add-edit",
  emits: ["addOrEditInfo"],
  setup(_,{ emit }) {
    const addOrEditInfo = (info) => {
      emit("addOrEditInfo", info)
    }
    return {addOrEditInfo}
  },
  data(){
    return {
      dateTimeRange:[],
      total:{
        days: 0,
        minutes: 0,
        hours: 0
      },
      timeUnitOptions: [
        {label: "分", value: 1},
        {label: "小时", value: 2},
        {label: "天", value: 3},
      ],
      isFixedTimeSendOptions:[
        {label: "是", value: 1},
        {label: "否", value: 0},
      ],
      sendTypeOptions:[
        {label: "企业微信", value: 1},
        {label: "手机号", value: 2},
        {label: "邮箱", value: 3},
      ],
      sendCustomerTypeOptions:[
        {label: "自定义", value: 1},
        {label: "标签", value: 2},
      ],
      repeatCollectionTypeOptions:[
        {label: "一次", value: 1},
        {label: "每天", value: 10},
        {label: "每周", value: 100},
        {label: "每月", value: 1000},
      ]
    }
  },
  props: {
    info: {
      require: true,
      type: Object,
      default () {
        return {
            autoSend: false,
            repeatCollectType: -1,
            repeatValue: "",
            isFixedTimeSend: true,
            sendDateTime: "",
            beginHours: 0,
            beginMinutes: 0,
            time: 0
        }
      }
    },
    tagOptions:{
      require: true,
      type: Object,
      default () {
        return []
      }
    }
  },
  methods:{
    confirm(){
      this.time = this.total.days * 24 * 60 + this.total.hours * 60 + this.total.minutes
      this.addOrEditInfo(this.info)
    },
    delDelayTipTimer(idx){
      this.info.delayTipTimers = ArrayUtil.delItem(this.info.delayTipTimers, idx)
    },
    addDelayTipTimer(){
      if(this.info.delayTipTimers == null){
        this.info.delayTipTimers = []
      }
      this.info.delayTipTimers.push({timeUnit: 1})
    },
    changeDatetimeRange(){
      console.log(this.dateTimeRange)
      this.info.startDateTime = this.dateTimeRange[0];
      this.info.endDateTime = this.dateTimeRange[1];
      this.info.beginHours = this.info.startDateTime.getHours()
      this.info.beginMinutes = this.info.startDateTime.getMinutes()
    }
  }
}
</script>

<style scoped>

.delayTimer{
  display: flex; 
  justify-content: space-between; 
  width: 100%; 
  align-items: flex-end;
}

.gap{
  margin-right: 3px;
}
</style>