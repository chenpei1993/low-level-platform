<template>
  <div class="role">
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/' }">主页</el-breadcrumb-item>
      <el-breadcrumb-item>角色</el-breadcrumb-item>
    </el-breadcrumb>
    <el-divider />
    <div>
        <el-button link type="primary" size="small" @click="add">添加</el-button>
    </div>
    <div style="display: flex; justify-content: space-between;">
        <div style="width: 50%; padding: 20px;">
          <el-table :data="roles" stripe style="width: 100%" v-loading="loading" @row-click="rowClick" highlight-current-row>
              <el-table-column prop="name" label="角色名"/>
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
              :page-sizes="[20, 50, 100, 200, 300, 400]"
              layout="total, sizes, prev, pager, next, jumper"
              :total="total"
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
            />
          </div>
        </div>
        <div style="width: 50%; padding: 20px;">
            <div v-for="[key, value] in allPermissions" :key="key" style="margin-bottom: 10px;">
                <div>
                    {{value[0].name}}
                    <el-checkbox v-model="value[0].checked"></el-checkbox>
                </div>

                <span v-for="(v, i) in value" :key="i" style="margin-right: 10px;">
                    <el-checkbox v-if="i !== 0" v-model="v.checked">{{v.name}}</el-checkbox>
                </span>
            </div>
            <el-button @click="savePermission">保存</el-button>
        </div>
    </div>
    <el-drawer
          v-model="isShowAddOrEditPanel"
          :title="addOrEdit"
          direction="ltr"
          :before-close="closeAddOrEditPanel"
      >
        <Add :role="role" @addOrEditRole="addOrEditRole"/>
    </el-drawer>
  </div>
</template>

<script>

import Add from '@/components/role/Add.vue'
//TODO 全局变量
import { ElMessage, ElMessageBox } from 'element-plus'
import { inject } from "vue"

export default {
  name: 'Role',
  components: {
    Add
  },
  data(){
    return {
        isCheck: true,
      loading: false,
      currentPage: 1,
      pageSize: 50,
      total: 0,
      addOrEdit: "未知",
      isShowAddOrEditPanel: false,
      role: {},
      roles:[],
      allPermissions: null,
      allPermissionsMap: null,
      currentPermissions: null,
      currentRoleId: null
    }
  }, 
  methods: {
    closeAddOrEditPanel(){
      this.isShowAddOrEditPanel = false
    },
    addOrEditRole(role){
      if(role.id == null){
        //添加
        this.http.put("role", role)
            .then(()=>{
              ElMessage.success("操作成功")
              this.role = {}
              this.isShowAddOrEditPanel = false
              this.refresh()
            })
      }else{
        //修改
        this.http.post("role", role)
            .then(()=>{
              ElMessage.success("操作成功")
              this.role = {}
              this.isShowAddOrEditPanel = false
              this.refresh()
            })
      }
      
    },
    edit(row){
      this.addOrEdit = "修改"
      this.role = this.lodash.cloneDeep(row)
      this.isShowAddOrEditPanel = true
    },
    del(row){
        ElMessageBox.confirm(
            '是否删除该角色?',
            'Warning',
        { 
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
        })
        .then(() => {
            this.http.delete("role/" + row.id)
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
      this.role = {}
      this.isShowAddOrEditPanel = true
    },
    handleSizeChange(size){
      this.pageSize = size
      this.refresh()
    },
    handleCurrentChange(page){
      this.currentPage = page
    },
    savePermission(){
        if(this.currentRoleId == null){
            ElMessage.error("请先选择角色！")
            return
        }
        let permissionIds = []
        for(let [key, value] of this.allPermissions){
            value.forEach(v => {
                if(v.checked){
                    permissionIds.push(v.id)
                }
            })
        }
        let data = {
            roleId: this.currentRoleId,
            permissionIds: permissionIds
        }
        this.http.post("/role/permission", data).then(res => {
            ElMessage.success("保存成功")
        })
    },
    rowClick(row, column, event){
        this.currentRoleId = row.id
        this.http.get("/permission/" + row.id)
            .then((res) => {
                this.currentPermissions = new Set()
                this.allPermissionsMap.forEach(e => {
                    e.checked = false
                })
                res.forEach(e => {
                    this.currentPermissions.add(e.permission)
                    if(this.allPermissionsMap.has(e.permission)){
                        let p = this.allPermissionsMap.get(e.permission)
                        p.checked = true
                    }
                })
                this.$forceUpdate()
            }).catch(()=>{
        })
    },
    handlePermissions(permissions){
        this.allPermissions = new Map()
        this.allPermissionsMap = new Map()
        permissions.forEach(e => {
            this.allPermissionsMap.set(e.permission, e)
            let t = e.permission.split(":")
            let arr = this.allPermissions.get(t[0])
            if(arr === undefined){
                arr = []
            }

            if(t.length === 1){
                arr.unshift(e)
            }else if(t.length > 1){
                arr.push(e)
            }
            this.allPermissions.set(t[0], arr)
        })
    },
    refresh(){
      this.loading = true
        this.http.get("/permission/all")
            .then((res) => {
                this.handlePermissions(res)
            }).catch(()=>{
        })

      this.http.post("/role/page", {currentPage: this.currentPage, pageSize: this.pageSize})
        .then((res) => {
          ElMessage.success("更新成功")
          this.loading = false
          this.roles = res.data
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
