<template>
  <el-aside :width="$store.getters.isCollapse? '12rem' : '5rem'">
    <el-menu class="common-aside-menu"
             default-active="1"
             text-color="#BBE1FA"
             active-text-color="#EEE9DA"
             router
             :collapse="!$store.getters.isCollapse"
             :collapse-transition="false">
        <h3> {{ $store.getters.isCollapse ? '后台管理' : '后台' }} </h3>
      <el-menu-item @click="clickMenu" index="info">
        <el-icon><pie-chart /></el-icon>
        <span>系统首页</span>
      </el-menu-item>
      <el-menu-item index="music">
        <el-icon><mic /></el-icon>
        <span>歌曲管理</span>
      </el-menu-item>
      <el-menu-item index="artist">
        <el-icon><mic /></el-icon>
        <span>歌手管理</span>
      </el-menu-item>
      <el-menu-item index="musicList">
        <el-icon><Document /></el-icon>
        <span>歌单管理</span>
      </el-menu-item>
      <el-menu-item index="consumer">
        <el-icon><User /></el-icon>
        <span>用户管理</span>
      </el-menu-item>
    </el-menu>
  </el-aside>
</template>

<script>

import { PieChart, Mic, Document, User } from "@element-plus/icons-vue";
import {APP_NAME} from "../constant/constant";
import {useStore} from "vuex";
import {useRouter} from "vue-router";


export default {
  name: "CommonAside",
  setup(){
    let store = useStore()
    const router = useRouter()
    const musicName = APP_NAME
    return{
      musicName,
      store,
      router
    }
  }

}
</script>

<style scoped>
el-aside {
  left: 0;
  bottom: 0;
  overflow-y: scroll;
  height: 100%;
  color: #EEE9DA;
  background: #205295;
}
h3 {
  height: 40px;
  color: #EEE9DA;
  padding-top: 20px;
  text-align: center;
  margin: 0;
}
.common-aside-menu{
  border: none;
  width: 100%;
  background: #205295;
  height: 100%;
  text-align: center;
}
.el-menu-item{
  text-align: center;
  font-size: 18px;
}
.el-menu-item .el-icon{
  size: 18px;
}

</style>
