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
        <el-table :data="customs" stripe style="width: 100%">
            <el-table-column prop="name" label="姓名"/>
            <!--TODO 不要明文 -->
            <el-table-column prop="phone" label="手机号" width="150" />
            <el-table-column prop="email" label="邮箱" width="150"/>
            <el-table-column prop="tags" label="标签"/>
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
        <Add :custom="custom" @addOrEditCustom="addOrEditCustom"/>
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
export default {
  name: 'Custom',
  components: {
    Add
  },
  data(){
    return {
      addOrEdit: "未知",
      isShowAddOrEditPanel: false,
      custom: {},
      customs: []
    }
  }, 
  methods: {
    closeAddOrEditPanel(){
      this.isShowAddOrEditPanel = false
    },
    addOrEditCustom(custom){
      if(custom.id == null){
        //TODO fetch
        this.customs.push(custom)
      }
      this.custom = {}
      this.isShowAddOrEditPanel = false
    },
    edit(){
      this.addOrEdit = "修改"
      this.isShowAddOrEditPanel = true
    },
    del(){

    },
    add(){
      this.addOrEdit = "添加"
      this.isShowAddOrEditPanel = true
    }
  },
  created(){
    this.customs = [
      {
        name: "张三",
        email: "",
        phone: "1212121",
        createdAt: "2022-08-08 10:00:00",
        updatedAt: "2022-09-08 10:00:00",
      },
    ]
  }
}
</script>