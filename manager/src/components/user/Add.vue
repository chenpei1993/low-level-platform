<template>
  <div>
    <el-form
        label-position="right"
        label-width="100px"
        :model="user"
        style="max-width: 460px"
    >
        <el-form-item label="用户名">
            <el-input v-model="user.username" :disabled="user.id != null"/>
        </el-form-item>
        <el-form-item label="角色">
            <el-select v-model="user.roleIds" placeholder="请选择" style="width: 100%;" multiple >
                <el-option
                        v-for="item in roles"
                        :key="item.id"
                        :label="item.name"
                        :value="item.id">
                </el-option>
            </el-select>
        </el-form-item>
        <el-form-item>
            <div style="display: flex; justify-content: flex-end; width: 100%;">
              <el-button type="primary" @click="confirm"  size="small" plain>确认</el-button>
            </div>
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
            id: "",
            name: "",
            role: "",
        }
      }
    },
    roles: {
        require: true,
        type: Array,
        default() {
            return [];
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