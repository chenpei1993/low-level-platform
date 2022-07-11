<template>
  <div>
    <el-form
        label-position="right"
        label-width="100px"
        :model="info"
        style="width: 100%"
    >
        <el-form-item label="开始时间">
          <el-date-picker
            v-model="info.startDateTime"
            type="datetime"
            placeholder="Select date and time"
          />
        </el-form-item>
        <el-form-item label="结束时间">
          <el-date-picker
            v-model="info.endDateTime"
            type="datetime"
            placeholder="Select date and time"
          />
        </el-form-item>
        <el-form-item label="是否定时推送">
            <el-select v-model="info.isFixedTimeSend">
              <el-option
                v-for="item in isFixedTimeSendOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
        </el-form-item>
        
        <el-form-item label="定时推送时间" v-if="info.isFixedTimeSend">
            <el-date-picker
              v-model="info.fixedSendDateTime"
              type="datetime"
              placeholder="推送时间"
            />
        </el-form-item>
        <el-form-item label="推送方式" v-if="info.isFixedTimeSend">
          <el-select v-model="info.sendType">
            <el-option
              v-for="item in sendTypeOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="发送人群类型" v-if="info.isFixedTimeSend">
          <el-select v-model="info.sendCustomerType">
            <el-option
              v-for="item in sendCustomerTypeOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="标签" v-if="info.isFixedTimeSend && info.sendCustomerType == 1">
            <el-input v-model="info.sendCustomers" />
        </el-form-item>
        <el-form-item label="自定义" v-if="info.isFixedTimeSend && info.sendCustomerType == 2">
            <el-input v-model="info.sendCustomers" />
        </el-form-item>
        <el-form-item
          v-for="(delayTipTimer, index) in info.delayTipTimers"
          :key="delayTipTimer.key"
          :label="'延时提醒-' + index"
          :prop="'domains.' + index + '.value'"
        >
          <div style="display: flex; justify-content: space-between; width: 100%;">
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
                <span>提醒方式</span>
                <el-select v-model="delayTipTimer.tipType" size="small" style="width: 60px; margin-right:3px;">
                  <el-option
                    v-for="item in sendTypeOptions"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
                
            </div>
            <div>
              <el-button class="mt-2" @click="delDelayTipTimer(index)" type="danger" size="small" style="margin-left:3px;">删除</el-button>
            </div>
          </div>        
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="addDelayTipTimer" v-if="info.isFixedTimeSend">添加定时提醒</el-button>
            <el-button type="primary" @click="confirm">确认</el-button>
        </el-form-item>
    </el-form>
  </div>  
</template> 

<script>

export default {
  name: "info-add-edit",
  emits: ["addOrEditinfo"],
  setup(_,{ emit }) {
    const addOrEditInfo = (info) => {
      emit("addOrEditinfo", info)
    }
    return {addOrEditInfo}
  },
  data(){
    return {
      timeUnitOptions: [
        {label: "分", value: 1},
        {label: "小时", value: 2},
        {label: "天", value: 3},
        {label: "周", value: 4},
        {label: "月", value: 5}
      ],
      isFixedTimeSendOptions:[
        {label: "是", value: true},
        {label: "否", value: false},
      ],
      sendTypeOptions:[
        {label: "企业微信", value: 1},
        {label: "手机号", value: 2},
        {label: "邮箱", value: 3},
      ],
      sendCustomerTypeOptions:[
        {label: "标签", value: 1},
        {label: "自定义", value: 2},
      ]
    }
  },
  props: {
    info: {
      require: true,
      type: Object,
      default () {
        return {
            name: "",
            phone: "",
            email: "",
            isFixedTimeSend: true
        }
      }
    }
  },
  methods:{
    confirm(){
        this.addOrEditInfo(this.info)
    },
    delDelayTipTimer(idx){

    },
    addDelayTipTimer(){
      if(this.info.delayTipTimers == null){
        this.info.delayTipTimers = []
      }
      this.info.delayTipTimers.push({timeUnit: 1})
    }
  }
}
</script>