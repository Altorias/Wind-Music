<template>
  <el-header class="header">
    <div class="collapse-btn" @click="handleCollapse">
      <el-icon v-if="!$store.getters.isCollapse">
        <Expand/>
      </el-icon>
      <el-icon v-else>
        <Fold/>
      </el-icon>
    </div>
    <span> {{ musicName }} </span>
    <div class="header-right">
      <div class="header-user-con">
        <el-dropdown class="user-name" trigger="click" @command="handleCommand">
          <div class="user-avator">
            <img src="../assets/images/user.png"/>
          </div>
          <!--          <span class="el-dropdown-link">-->
          <!--            {{ userName }}-->
          <!--            <i class="el-icon-caret-bottom"></i>-->
          <!--          </span>-->
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item command="logout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
    </div>
  </el-header>
</template>

<script>
import {APP_NAME, RouterName} from "../constant/constant";
import {useStore} from "vuex";
import utils from "../utils/utils";
import {defineComponent, ref} from "vue";
import {Expand, Fold} from "@element-plus/icons-vue";


export default defineComponent({
  props: {
    userName: String
  },
  setup() {
    const musicName = APP_NAME
    let store = useStore()
    const handleRouter = utils()
    const handleCollapse = () => {
      store.commit("updateIsCollapse")
    }
    const getImageSrc = () => {
      return null
    }

    // 用户名下拉菜单选择事件
    function handleCommand(command) {
      if (command === "logOut") {
        handleRouter(RouterName.SignIn, {path: RouterName.SignIn});
      }
    }

    return {
      musicName,
      getImageSrc,
      handleCommand,
      handleCollapse,
      store
    }
  }
})
</script>

<style scoped>
header {
  width: 100%;
  height: 60px;
  display: flex;
  align-items: center;
  font-size: 20px;
  color: #EEE9DA;
  background: #205295;
  box-shadow: 0px 0px 8px 2px rgba(0, 0, 0, 0.3);
  margin-bottom: 0;
}

.collapse-btn {
  display: flex;
  padding: 0 21px;
  cursor: pointer;
}


.header-right {
  position: absolute;
  right: 0;
  padding-right: 30px;
}

.header-user-con {
  display: flex;
  align-items: center;
}

.user-name {
  margin-left: 10px;
  font-size: 20px;
}

.user-avator img {
  cursor: pointer;
  display: block;
  width: 50px;
  height: 50px;
  border-radius: 50%;
}

.el-dropdown-link {
  cursor: pointer;
}

.el-dropdown-menu__item {
  text-align: center;
}
</style>
