<template>
  <div>
    <el-form
        label-position="right"
        label-width="100px"
        :model="user"
        style="max-width: 460px"
    >
        <el-form-item label="用户名">
            <el-input v-model="user.username" />
        </el-form-item>
        <el-form-item label="颜色">
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="confirm">确认</el-button>
        </el-form-item>
    </el-form>
  </div>  
</template> 

<script>

export default {
  name: "user-add-edit",
  emits: ["addOrEditUser"],
  setup(_,{ emit }) {
    const addOrEditUser = (user) => {
      emit("addOrEditUser", user)
    }
    return {addOrEditUser}
  },
  props: {
    user: {
      require: true,
      type: Object,
      default () {
        return {
            name: "",
            group: "",
            color: "",
        }
      }
    }
  },
  methods:{
    confirm(){
        this.addOrEditUser(this.user)
    }
  }
}
</script>