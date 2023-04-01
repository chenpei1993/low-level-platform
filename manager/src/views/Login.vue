<template>
    <div class="login" style="height: 100vh; width: 100vw;">
        <div class="header">
            <div class="logo">低代码平台</div>
        </div>
        <div class="login-box">
            <div>
                <div class="login-box-title">登录账号</div>
                <div class="login-box-input">
                    <el-input v-model="username" placeholder="请输入用户名" size="large"></el-input>
                </div>
                <div >
                    <el-input v-model="password" placeholder="请输入密码" size="large" show-password></el-input>
                </div>
                <div class="login-tips-box">
                    <div class="login-tips">{{errorMessage}}</div>
                </div>
                <div class="login-box-button">
                    <div>
                        <el-button round style="width: 100%;" size="large" @click="login">登录</el-button>
                    </div>
                </div>
                <div style="text-align: center;">
                    <div style="display: inline-block;">
                        <el-checkbox v-model="rememberMe">
                            <span style="color:#C0C4CC;">30天内记住我</span>
                        </el-checkbox>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import {inject} from "vue";

export default{
    //TODO 加密
    //TODO 修改登录按钮样式
    //TODO 添加记住我功能
    //TODO 登录页面的报错信息是否要独立出来
    name: "Login",
    data (){
        return {
            username: "",
            password: "",
            errorMessage: "",
            rememberMe: false
        }
    },
    methods: {
        login(){
            let data = {
                username: this.username,
                password: this.password
            }
            this.http.post("user/login", data).then((data)=>{
                localStorage.setItem("jwt", data.jwt)
                //TODO returnValue 处理
                this.$router.push({ name: 'Home'})
            }).catch(e => {
                console.log(e)
            })

        }
    },
    created(){
        this.http = inject("$http")
    }
}
</script>

<style scoped>
.header{
    background: #333;
    width: 100%;
    height: 60px;
    color: white;
    display: flex;
    justify-content: space-between;
    align-items: center;
    position: relative;
}

.logo{
    font-size: 20px;
    margin-left: 10px;
    color: white;
    font-weight: bold;
}

.login-box-title{
    color: #303133;
    font-weight: bold;
    font-size: 25px;
    margin-bottom: 30px;
    margin-top: 25px;
}

.login-box-input{
    margin-bottom: 25px;
}

.login-tips-box{
    text-align: left;
    margin-top: 15px;
    height: 10px;
}

.login-tips{
    color:red;
    font-size: 12px;
}

.login-box-button{
    margin-top: 40px;
    margin-bottom: 25px;
}

.login{
    text-align: center;
}

.login-box{
    display: inline-block;
    margin-top: 150px;
    height: 400px;
    width: 400px;
    background: white;
    border-radius: 5px;
    box-shadow: 0 0 2px 1px rgba(0, 0, 0, 0.2);
    padding: 25px;
}

</style>