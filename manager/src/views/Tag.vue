<template>
  <div class="tag">
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/' }">主页</el-breadcrumb-item>
      <el-breadcrumb-item>标签</el-breadcrumb-item>
    </el-breadcrumb>
    <el-divider />
    <div>
        <el-button type="success" size="small" @click="add" plain>添加</el-button>
    </div>
    <div>
        <el-table :data="tags" stripe style="width: 100%" v-loading="loading">
            <el-table-column prop="name" label="标签名"/>
            <el-table-column prop="color" label="颜色">
              <template #default="scope">
                <el-tag :color="scope.row.color" effect="light" style="width:50px"></el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="createdAt" label="创建时间" />
            <el-table-column prop="updatedAt" label="更新时间" />
            <el-table-column fixed="right" label="操作" width="120">
                <template #default="scope">
                    <el-button link type="primary" size="small" @click="edit(scope.row)">编辑</el-button>
                    <el-button link type="primary" size="small" @click="del(scope.row)">删除</el-button>
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
      >
        <Add :tag="tag" @addOrEditTag="addOrEditTag"/>
    </el-drawer>
  </div>
</template>

<script>
/**
 * 1 使用中
 * 2 已发布
 * 3 停用
 */
import Add from '@/components/tag/Add.vue'
//TODO 全局变量
import { ElMessage, ElMessageBox } from 'element-plus'
import { inject } from "vue"

export default {
  name: 'Tag',
  components: {
    Add
  },
  data(){
    return {
      loading: false,
      currentPage: 1,
      pageSize: 15,
      total: 0,
      addOrEdit: "未知",
      isShowAddOrEditPanel: false,
      tag: {},
      tags:[]
    }
  }, 
  methods: {
    closeAddOrEditPanel(){
      this.isShowAddOrEditPanel = false
    },
    addOrEditTag(tag){
      if(tag.id == null){
        //添加
        this.http.put("tag", tag)
            .then(()=>{
              ElMessage.success("操作成功")
              this.tag = {}
              this.isShowAddOrEditPanel = false
              this.refresh()
            })
      }else{
        //修改
        this.http.post("tag", tag)
            .then(()=>{
              ElMessage.success("操作成功")
              this.tag = {}
              this.isShowAddOrEditPanel = false
              this.refresh()
            })
      }
      
    },
    edit(row){
      this.addOrEdit = "修改"
      this.tag = this.lodash.cloneDeep(row)
      this.isShowAddOrEditPanel = true
    },
    del(row){
        ElMessageBox.confirm(
            '是否删除标签，会将客户的标签也一并删除',
            '警告',
        { 
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
        })
        .then(() => {
            this.http.delete("tag/" + row.id)
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
      this.tag = {}
      this.isShowAddOrEditPanel = true
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
      this.http.post("/tag/page", {currentPage: this.currentPage, pageSize: this.pageSize})
        .then((res) => {
          ElMessage.success("更新成功")
          this.loading = false
          this.tags = res.data
          this.total = res.total
        }).catch(()=>{
          this.loading = false
        })
    }
  },
  created(){
    this.http = inject("$http")
    this.lodash = inject("$lodash")
    this.refresh()
  }
}
</script>
