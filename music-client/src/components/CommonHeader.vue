<template>
  <div class="common-header">
    <div class="header-logo" @click="handleChangePage">
      <el-icon>
        <Headset/>
      </el-icon>
      <span>{{ appName }}</span>
    </div>
    <header-page class="header-page" :headerLeftList="headerLeftList" :activePage="activePage"
                 @click="handleChangePage"></header-page>
    <div class="header-search">
      <el-input placeholder="搜索" :prefix-icon="Search" v-model="keywords" @keyup.enter="handleSearch()"/>
    </div>
    <header-page :headerLeftList="headerRightList" :activePage="activePage"
                 @click="handleChangePage"></header-page>
    <el-dropdown class="user-wrap" v-if="token" trigger="click">
      <el-image class="user" fit="contain" :src="getImageSrc"/>
      <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item v-for="(item, index) in menuList" :key="index" @click.stop="handleMenuPage(item.path)">
            {{ item.name }}
          </el-dropdown-item>
        </el-dropdown-menu>
      </template>
    </el-dropdown>
  </div>
</template>

<script>
import {defineComponent, ref, getCurrentInstance, computed} from "vue";
import {
  APP_NAME,
  HEADER_LEFT_LIST,
  HEADER_RIGHT_LIST,
  HeaderPageName,
  RouterName,
  USER_MENU_LIST
} from "../constant/constant";
import HeaderPage from "./HeaderPage.vue";
import {useStore} from "vuex";
import {ElMessage} from 'element-plus'
import utils from "../utils/utils";
import {Search} from '@element-plus/icons-vue'



export default {
  name: "CommonHeader",
  components: {HeaderPage},
  setup() {
    const {proxy} = getCurrentInstance()
    const store = useStore()
    const appName = ref(APP_NAME)
    const headerLeftList = ref(HEADER_LEFT_LIST)
    const headerRightList = ref(HEADER_RIGHT_LIST)
    const activePage = computed(() => store.getters.activePage);
    const keywords = ref("");
    const {setActivePage, handleRouter} = utils()
    const token = computed(() => store.getters.token);
    const menuList = ref(USER_MENU_LIST)


    const handleChangePage = (pagePath, pageName) => {
      if (!pagePath && !pageName) {
        setActivePage(HeaderPageName.Home)
        handleRouter(RouterName.Home, {path: RouterName.Home})
      } else {
        setActivePage(pageName);
        handleRouter(pagePath, {pagePath})
      }
    }
    const handleSearch = () => {
      if (keywords.value !== "") {
        store.commit("setSearchWord", keywords.value);
        handleRouter(RouterName.Search, {path: RouterName.Search, query: {keywords: keywords.value}});
      } else {
        ElMessage({
          message: "搜索内容不能为空",
          type: "error",
        });
      }
    }
    const getImageSrc = () => {
      return new URL('../assets/images/user.png', import.meta.url).href;
    }
    const handleMenuPage = (path) => {
      if (path === RouterName.SignOut) {
        proxy.$store.commit("setToken", false);
        setActivePage(HeaderPageName.Home);
        handleRouter(RouterName.Home, {path: RouterName.Home});
      } else {
        handleRouter(path, {path});
      }
    }


    return {
      appName,
      headerLeftList,
      headerRightList,
      activePage,
      keywords,
      handleChangePage,
      handleSearch,
      token,
      getImageSrc,
      menuList,
      handleMenuPage,
      Search,
    }
  },
}

</script>

<style lang="scss" scoped>
@import "../assets/css/config.scss";
@import "../assets/css/global.scss";

@media screen and (min-width: $sm) {
  .header-logo {
    margin: 0 1rem;
  }
}

@media screen and (max-width: $sm) {
  .header-logo {
    margin: 0 1rem;

    span {
      display: none;
    }
  }
  .header-search {
    display: none;
  }
}

.common-header {
  position: fixed;
  width: 100%;
  height: $header-height;
  line-height: $header-height;
  padding: $header-padding;
  margin: $header-margin;
  background-color: $theme-header-color;
  box-shadow: $box-shadow;
  box-sizing: border-box;
  z-index: 100;
  display: flex;
  white-space: nowrap;
  flex-wrap: nowrap;
}

/* LOGO */
.header-logo {
  font-size: $font-size-logo;
  font-weight: bold;
  cursor: pointer;

  .icon {
    @include icon(3rem, $color-black);
    vertical-align: middle;
  }

  span {
    margin-left: 1rem;
  }
}

.yin-header-nav {
  flex: 1;
}

/*搜索输入框*/
.header-search {
  margin: 0 20px;
  width: 100%;

  :deep(.el-input__wrapper)  {
    margin-left: 0;
    text-indent: 5px;
    max-width: $header-search-max-width;
    min-width: $header-search-min-width;
    border-radius: $header-search-radius;
    box-shadow: none;
    background-color: $color-light-grey;
    color: $color-black;
  }
}

/*用户*/
.user-wrap {
  position: relative;
  display: flex;
  align-items: center;

  .user {
    width: $header-user-width;
    height: $header-user-width;
    border-radius: $header-user-radius;
    margin-right: $header-user-margin;
    cursor: pointer;
  }
}
</style>