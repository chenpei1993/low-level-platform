<template>
  <div>
    <el-form
        label-position="right"
        label-width="100px"
        :model="tag"
        style="max-width: 460px"
    >
        <el-form-item label="名称">
            <el-input v-model="tag.name" />
        </el-form-item>
        <el-form-item label="组">
            <el-input v-model="tag.group" />
        </el-form-item>
        <el-form-item label="颜色">
            <el-color-picker v-model="tag.color" />
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="confirm">确认</el-button>
        </el-form-item>
    </el-form>
  </div>  
</template> 

<script>

export default {
  name: "tag-add-edit",
  emits: ["addOrEditTag"],
  setup(_,{ emit }) {
    const addOrEditTag = (tag) => {
      emit("addOrEditTag", tag)
    }
    return {addOrEditTag}
  },
  props: {
    tag: {
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
        this.addOrEditTag(this.tag)
    }
  }
}
</script>