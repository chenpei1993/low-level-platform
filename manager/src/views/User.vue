<template>
  <div class="user">
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/' }">主页</el-breadcrumb-item>
      <el-breadcrumb-item>用户</el-breadcrumb-item>
    </el-breadcrumb>
    <el-divider />
    <div>
        <el-button type="success" size="small" @click="add" plain>添加</el-button>
    </div>
    <div>
        <el-table :data="users" stripe style="width: 100%" v-loading="loading">
            <el-table-column prop="username" label="用户名"/>
            <el-table-column prop="roleIds" label="用户角色">
                <template #default="scope">
                    <el-tag style="margin-right: 5px;" v-for="role in scope.row.roleIds"
                            :key="role.id"
                            type="info"
                            effect="plain">
                        {{ role.name }}
                    </el-tag>
                </template>
            </el-table-column>
            <el-table-column prop="createdAt" label="创建时间" />
            <el-table-column prop="updatedAt" label="更新时间" />
            <el-table-column fixed="right" label="操作" width="120">
                <template #default="scope">
                    <el-button link type="primary" size="small" @click="edit(scope.row)" v-if="scope.row.id !== 1">编辑</el-button>
                    <el-button link type="primary" size="small" @click="del(scope.row)" v-if="scope.row.id !== 1">删除</el-button>
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
        <Add :user="user" :roles="roles" @addOrEditUser="addOrEditUser"/>
    </el-drawer>
  </div>
</template>

<script>
/**
 * 1 使用中
 * 2 已发布
 * 3 停用
 */
import Add from '@/components/user/Add.vue'
//TODO 全局变量
import { ElMessage, ElMessageBox } from 'element-plus'
import { inject } from "vue"

export default {
  name: 'User',
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
      user: {},
      users:[],
      roles:[]
    }
  }, 
  methods: {
    closeAddOrEditPanel(){
      this.isShowAddOrEditPanel = false
    },
    addOrEditUser(user){
      if(user.id == null){
        //添加
        this.http.put("user", user)
            .then(()=>{
              ElMessage.success("操作成功")
              this.user = {}
              this.isShowAddOrEditPanel = false
              this.refresh()
            })
      }else{
        //修改
        this.http.post("user", user)
            .then(()=>{
              ElMessage.success("操作成功")
              this.user = {}
              this.isShowAddOrEditPanel = false
              this.refresh()
            })
      }
      
    },
    edit(row){
      this.addOrEdit = "修改"
      this.user = this.lodash.cloneDeep(row)
      this.isShowAddOrEditPanel = true
    },
    del(row){
        ElMessageBox.confirm(
            '是否删除该用户',
            '警告',
        { 
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
        })
        .then(() => {
            this.http.delete("user/" + row.id)
              .then(()=>{
                ElMessage.success("操作成功")
                this.refresh()
              })
        })
        .catch((e) => {
            ElMessage.info('取消删除')
        })
    },
    add(){
      this.addOrEdit = "添加"
      this.user = {}
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
      this.http.post("/user/page", {currentPage: this.currentPage, pageSize: this.pageSize})
        .then((res) => {
          ElMessage.success("更新成功")
          this.loading = false
          this.users = res.data
          this.total = res.total
        }).catch(()=>{
          this.loading = false
        })
    },
    getRoles(){
      this.http.get("/role/all").then((res) => {
          this.roles = res
      })
    }
  },
  created(){
    this.http = inject("$http")
    this.lodash = inject("$lodash")
    this.refresh()
    this.getRoles()
  }
}
</script>
