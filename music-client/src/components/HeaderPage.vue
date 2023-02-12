<template>
  <ul class="header_page">
    <li :class="{ active: item.name === activePage }"
        v-for="item in headerLeftList" :key="item.path" @click="handleChangeView(item)">
      {{ item.name }}
    </li>
  </ul>
</template>

<script>
import { defineComponent, getCurrentInstance } from "vue";

export default defineComponent({
  props: {
    headerLeftList: Array,
    activePage: String,
  },
  emits: ["click"],
  setup() {
    const { proxy } = getCurrentInstance();

    function handleChangeView(item) {
      proxy.$emit("click", item.path, item.name);
    }
    return {
      handleChangeView,
    };
  },
});
</script>

<style lang="scss" scoped>
@import "../assets/css/config.scss";

li {
  margin: $header-nav-margin;
  padding: $header-nav-padding;
  line-height: 3.3rem;
  color: $color-grey;
  border-bottom: none;
  cursor: pointer;
}

li.active {
  color: $color-black;
  font-weight: 600;
  border-bottom: 5px solid $color-black;
}
</style>