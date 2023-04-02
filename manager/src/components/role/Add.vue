<template>
  <div>
    <el-form
        label-position="right"
        label-width="100px"
        :model="role"
        style="max-width: 460px"
    >
        <el-form-item label="角色名">
            <el-input v-model="role.name" />
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="confirm">确认</el-button>
        </el-form-item>
    </el-form>
  </div>  
</template> 

<script>

export default {
  name: "role-add-edit",
  emits: ["addOrEditRole"],
  setup(_,{ emit }) {
    const addOrEditRole = (role) => {
      emit("addOrEditRole", role)
    }
    return {addOrEditRole}
  },
  props: {
    role: {
      require: true,
      type: Object,
      default () {
        return {
            name: "",
            role: "",
        }
      }
    }
  },
  methods:{
    confirm(){
        this.addOrEditRole(this.role)
    }
  }
}
</script>