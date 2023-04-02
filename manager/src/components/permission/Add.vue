<template>
  <div>
    <el-form
        label-position="right"
        label-width="100px"
        :model="permission"
        style="max-width: 460px"
    >
        <el-form-item label="权限名">
            <el-input v-model="permission.name" />
        </el-form-item>
        <el-form-item label="权限代码">
            <el-input v-model="permission.permission" />
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="confirm">确认</el-button>
        </el-form-item>
    </el-form>
  </div>  
</template> 

<script>

export default {
  name: "permission-add-edit",
  emits: ["addOrEditPermission"],
  setup(_,{ emit }) {
    const addOrEditPermission = (permission) => {
      emit("addOrEditPermission", permission)
    }
    return {addOrEditPermission}
  },
  props: {
    permission: {
      require: true,
      type: Object,
      default () {
        return {
            name: "",
            permission: "",
        }
      }
    }
  },
  methods:{
    confirm(){
        this.addOrEditPermission(this.permission)
    }
  }
}
</script>