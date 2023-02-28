<template>
  <div class="common-layout">
    <el-container class="lay-container">
      <common-aside></common-aside>
      <el-container class="main-container">
        <common-header :userName="userName"></common-header>
        <el-main>
          <router-view/>
        </el-main>
      </el-container>
    </el-container>
  </div>
  <music-play></music-play>
</template>

<script>
import CommonHeader from "../components/CommonHeader.vue";
import CommonAside from "../components/CommonAside.vue";
import MusicPlay from "../components/MusicPlay.vue";

export default {
  components: {
    CommonHeader,
    CommonAside,
    MusicPlay
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
  flex-wrap: nowrap;
  align-items: flex-start;
  height: 100%;

  .main-container {
    flex-wrap: wrap;
    height: 100%;
    padding: 0;

    .el-main {
      margin-top: -8rem;
    }
  }
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
