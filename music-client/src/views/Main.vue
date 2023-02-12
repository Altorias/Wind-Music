<template>
  <el-container>
    <el-header>
      <common-header></common-header>
    </el-header>
    <el-main>Main123213</el-main>
    <el-footer>Footer</el-footer>
  </el-container>
</template>

<script>
import CommonHeader from "../components/CommonHeader.vue";
import {getCurrentInstance} from "vue";





export default {
  name: "Main"
}
</script>

<style lang="less" scoped>
.el-container {
  min-height: calc(100% - 60px);
}

.el-header {
  padding: 0;
}

.el-main {
  padding-left: 0;
  padding-right: 0;
}
</style>