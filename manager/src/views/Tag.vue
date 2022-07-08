<template>
  <div class="tag">
    <h1>This is an tag page</h1>
    <div>
        <el-button link type="primary" size="small" @click="add">添加</el-button>
    </div>
    <div>
        <el-table :data="tags" stripe style="width: 100%">
            <el-table-column prop="name" label="标签名"/>
            <el-table-column prop="group" label="标签组" width="150" />
            <el-table-column prop="color" label="颜色" width="150"/>
            <el-table-column prop="createdAt" label="创建时间" />
            <el-table-column prop="updatedAt" label="更新时间" />
            <el-table-column fixed="right" label="操作" width="120">
                <template #default>
                    <el-button link type="primary" size="small" @click="edit">编辑</el-button>
                    <el-button link type="primary" size="small" @click="del">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
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
import { inject } from "vue";

export default {
  name: 'Tag',
  components: {
    Add
  },
  data(){
    return {
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
        this.http.put("tag", tag)
            .then(()=>{
              ElMessage({
                type: "success",
                message: "ok",
              })
              this.tags.push(tag)
            })
      }
      this.tag = {}
      this.isShowAddOrEditPanel = false
    },
    edit(){
      this.addOrEdit = "修改"
      this.isShowAddOrEditPanel = true
    },
    del(){
        ElMessageBox.confirm(
            '是否删除标签',
            'Warning',
        { 
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
        })
        .then(() => {
            ElMessage({
                type: 'success',
                message: '成功删除',
            })
        })
        .catch(() => {
            ElMessage({
                type: 'info',
                message: '取消删除',
            })
        })
    },
    add(){
      this.addOrEdit = "添加"
      this.isShowAddOrEditPanel = true
    }
  },
  created(){
    this.http = inject("$http")
    this.tags = [
      {
        name: "组1",
        group: "",
        color: "#333"
      },
    ]
  }
}
</script>
