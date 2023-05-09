<template>
    <div>
        <el-table :data="sends">
            <el-table-column property="executionDateTime" label="提醒时间" width="200" />
            <el-table-column property="sendType" label="推送方式" width="100">
                <template #default="scope">
                    <span>{{ getSendTypeOptionByValue(scope.row.sendType) }}</span>
                </template>
            </el-table-column>
            <el-table-column property="sendMessage" label="文本模板" width="400" />
            <el-table-column property="status" label="状态" >
                <template #default="scope">
                    <span>{{ getStatusOptionByValue(scope.row.status) }}</span>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>

import {ElMessage} from "element-plus";
import {inject} from "vue";
import TaskUtil from '@/util/TaskUtil'

export default {
    name: "info-tips",
    data(){
        return {
            loading: false,
            currentPage: 1,
            pageSize: 15,
            total: 0,
            sends: [],
        }
    },
    props: {
        infoId: {
            require: true,
            type: Number,
        },
    },
    methods:{
        getSendTypeOptionByValue(value){
            return TaskUtil.getSendTypeOptionByValue(value)
        },

        getStatusOptionByValue(value){
            return TaskUtil.getStatusOptionByValue(value);
        },
        refresh(){
            console.log(this.infoId)
            this.http.post("/send_timer/page", {currentPage: this.currentPage, pageSize: this.pageSize, infoId: this.infoId})
                .then((res) => {
                    ElMessage.success("更新成功")

                    this.sends = res.data
                    this.total = res.total
                }).catch(()=>{
                this.loading = false
            })
        }
    },
    mounted() {
        this.refresh()
    },
    created() {
        this.http = inject("$http")
    }
}
</script>