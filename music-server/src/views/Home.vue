<template>
  <div class="common-layout">
    <el-container>
      <common-aside></common-aside>
      <el-container>
        <common-header :userName="userName"></common-header>
        <el-main>
          <router-view/>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import CommonHeader from "../components/CommonHeader.vue";
import CommonAside from "../components/CommonAside.vue";

export default {
  components: {
    CommonHeader,
    CommonAside
  },
  setup() {
    const userName = "admin"
    return {
      userName
    }
  }
}
</script>

<style lang="less" scoped>
.el-container {
  flex-wrap: wrap;
  align-items: flex-start;
}

.common-layout {
  height: 100%;

  .lay-container {
    flex-wrap: nowrap;
  }

  & > .el-container {
    height: 100%;

    & > .el-aside {
      height: 100%;
      background-color: #545c64;
    }
  }
}
</style>