<template>
  <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/' }">主页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/Info' }">问卷</el-breadcrumb-item>
      <el-breadcrumb-item>结果</el-breadcrumb-item>
  </el-breadcrumb>
    <el-divider />
    <div>
        <el-button type="success" size="small" @click="exportExcel" plain>导出</el-button>
    </div>
    <div>
        <el-table :data="answers" stripe style="width: 100%" v-loading="loading">
            <el-table-column
                    v-for="col in cols"
                    :key="col.id"
                    :prop="col.id + ''"
                    :label="'第' + col.idx + '题'">
                <template #default="scope">
                    {{ scope.row[col.id] }}
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
</template>
<script>
import {ElMessage, ElMessageBox} from 'element-plus'
import { inject } from "vue"

export default {
    name: 'Answer',
    data(){
        return {
            loading: false,
            currentPage: 1,
            pageSize: 15,
            total: 0,
            answers:[],
            cols: []
        }
    },
    methods: {
        exportExcel(){

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
            let data = {infoId: this.infoId, currentPage: this.currentPage, pageSize: this.pageSize}
            this.http.post("/info/answer/page", data)
                .then((res) => {
                    ElMessage.success("更新成功")
                    this.loading = false

                    this.total = res.total
                    this.cols = res.questionsInfo
                    let answers = res.data
                    let arr = []
                    answers.forEach(e => {
                        let createdAt = e.createdAt
                        let updatedAt = e.updatedAt
                        let a = JSON.parse(e.answer)
                        let o = {}
                        o['createdAt'] = createdAt
                        o['updatedAt'] = updatedAt
                        a.forEach(e1 => {
                            o[e1.questionId] = e1.answer
                        })
                        arr.push(o)
                    })
                    console.log(arr)
                    this.answers = arr
                }).catch(()=>{
                this.loading = false
            })
        },
    },
    created(){
        this.infoId = this.$route.query.id
        this.http = inject("$http")
        this.lodash = inject("$lodash")
        this.refresh();
    }
}
</script>
