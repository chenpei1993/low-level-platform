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
            <el-select v-model="custom.tags" multiple>
              <el-option
                v-for="item in tagOptions"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              />
            </el-select>
        </el-form-item>
        <el-form-item>
            <div style="display: flex; justify-content: flex-end; width: 100%;">
              <el-button type="primary" @click="confirm" size="small" plain>确认</el-button>
            </div>
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
    },
    tagOptions:{
      require: true,
      type: Object,
      default () {
        return []
      }
    }
  },
  methods:{
    confirm(){
        this.addOrEditCustom(this.custom)
    },
  },
}
</script>