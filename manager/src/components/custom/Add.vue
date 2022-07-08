<template>
  <div>
    <el-form
        label-position="right"
        label-width="100px"
        :model="custom"
        style="max-width: 460px"
    >
        <el-form-item label="姓名">
            <el-input v-model="custom.name" />
        </el-form-item>
        <el-form-item label="手机号">
            <el-input v-model="custom.phone" />
        </el-form-item>
        <el-form-item label="邮箱">
            <el-input v-model="custom.email" />
        </el-form-item>
        <el-form-item label="标签">
            <el-input v-model="custom.tag" />
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="confirm">确认</el-button>
        </el-form-item>
    </el-form>
  </div>  
</template>  

<script>

export default {
  name: "custom-add-edit",
  emits: ["addOrEditCustom"],
  setup(_,{ emit }) {
    const addOrEditCustom = (custom) => {
      emit("addOrEditCustom", custom)
    }
    return {addOrEditCustom}
  },
  props: {
    custom: {
      require: true,
      type: Object,
      default () {
        return {
            name: "",
            phone: "",
            email: "",
        }
      }
    }
  },
  methods:{
    confirm(){
        this.addOrEditCustom(this.custom)
    }
  }
}
</script>