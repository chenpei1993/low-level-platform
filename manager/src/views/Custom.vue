<template>
  <div class="custom">
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/' }">主页</el-breadcrumb-item>
      <el-breadcrumb-item>客户</el-breadcrumb-item>
    </el-breadcrumb>
    <el-divider />
    <div>
        <el-button link type="primary" size="small" @click="add">添加</el-button>
    </div>
    <div>
        <el-table :data="customs" stripe style="width: 100%" v-loading="loading">
            <el-table-column prop="name" label="姓名"/>
            <!--TODO 不要明文 -->
            <el-table-column prop="phone" label="手机号" width="150">
              <template #default="scope">
                <el-tooltip
                      effect="light"
                      :content="scope.row.phone"
                      placement="top"
                    >
                    <span style="margin-right: 10px">{{ scope.row.phone.substr(0,3) + "****" + scope.row.phone.substr(7,scope.row.phone.length)}}</span>
                </el-tooltip>
              </template>
            </el-table-column>  
            <el-table-column prop="email" label="邮箱" width="150"/>
            <el-table-column prop="tags" label="标签"/>
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
            :currentPage="currentPage"
            :page-size="pageSize"
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
      >
        <Add :custom="custom" :tagOptions="tagOptions" @addOrEditCustom="addOrEditCustom"/>
    </el-drawer>
  </div>
</template>

<script>
/**
 * 1 使用中
 * 2 已发布
 * 3 停用
 */
import Add from '@/components/custom/Add.vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { inject } from "vue"

export default {
  name: 'Custom',
  components: {
    Add
  },
  data(){
    return {
      loading: false,
      currentPage: 1,
      pageSize: 50,
      total: 0,
      addOrEdit: "未知",
      isShowAddOrEditPanel: false,
      custom: {},
      customs: [],
      tagOptions:[]
    }
  }, 
  methods: {
    closeAddOrEditPanel(){
      this.isShowAddOrEditPanel = false
    },
    addOrEditCustom(custom){
      if(custom.id == null){
        //添加
        this.http.put("customer", custom)
            .then(()=>{
              ElMessage.success("操作成功")
              this.custom = {}
              this.isShowAddOrEditPanel = false
              this.refresh()
            })
      }else{
        //修改
        this.http.post("customer", custom)
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
      this.custom = row
      this.isShowAddOrEditPanel = true
    },
    del(row){
      ElMessageBox.confirm(
            '是否删除客户',
            'Warning',
        { 
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
        })
        .then(() => {
            this.http.delete("customer/" + row.id)
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
      this.custom = {}
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
      this.http.post("/customer/page", {currentPage: this.currentPage, pageSize: this.pageSize})
        .then((res) => {
          ElMessage.success("更新成功")
          this.loading = false
          this.customs = res.data
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
            this.loading = false
          })
    }
  },
  created(){
    this.http = inject("$http")
    this.refresh()
    this.refreshTagOptions()
  }
}
</script>