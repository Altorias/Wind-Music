<template>
  <div class="login-bg">
    <div class="login-title">{{ titleName }}</div>
    <el-form ref="formLogin" class="login-form" :model="loginForm" :rules="rules">
      <el-form-item prop="username">
        <el-input v-model="loginForm.username" placeholder="username"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input v-model="loginForm.password" placeholder="password" @keyup.enter="submitForm"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button size="large" class="login-btn" type="primary" @click="submitForm">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import {defineComponent, getCurrentInstance, reactive} from "vue";
import utils from "../utils/utils";
import {APP_NAME, RouterName} from "../constant/constant";

export default defineComponent({
  setup() {
    const {proxy} = getCurrentInstance()
    const {handleRouter} = utils()
    const titleName = APP_NAME
    const loginForm = reactive({
      //todo
      username: "admin",
      password: "123456"
    })
    const rules = reactive({
      username: [{required: true, message: "请输入用户名", trigger: "blur"}],
      password: [{required: true, message: "请输入密码", trigger: "blur"}],
    })
    const submitForm = () => {
      proxy.$refs.formLogin.validate(async (valid) => {
        if (valid) {
          let res = await proxy.$api.signIn(loginForm)
          if (res.success) {
            handleRouter(RouterName.Info, {path: RouterName.Info})
          }
        }else{
          ElMessage({
            showClose: true,
            message: "请输入正确的内容",
            type: "error",
          });
        }
      })
    }
    return {
      titleName,
      loginForm,
      rules,
      submitForm
    }
  }
})
</script>


<style lang="less" scoped>
.login-bg{
  position: relative;
  background: url("../assets/images/background.png") fixed center;
  background-size: cover;
  width: 100%;
  height: 100%;
}

.login-title{
  font-family: "Lucida Calligraphy", cursive, serif, sans-serif;
  position: absolute;
  top: 50%;
  width: 100%;
  margin-top: -230px;
  text-align: center;
  font-size: 60px;
  font-weight: 600;
  color: #fff;
}

.login-form {
  position: absolute;
  left: 50%;
  top: 50%;
  width: 300px;
  margin: -150px 0 0 -190px;
  padding: 40px;
  border-radius: 5px;
  background: #fff;
}

.login-btn {
  width: 100%;
}
</style>