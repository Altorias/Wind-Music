<template>
  <audio controls="controls" :src="handleStaticResources(musicUrl)" preload="true" :ref="player"
         @canplay="startPlay" @ended="ended">
  </audio>
</template>

<script lang="ts">
import {defineComponent, getCurrentInstance, computed, watch, ref, onMounted} from "vue";
import {useStore} from "vuex";
import utils from "../utils/utils";

export default defineComponent({
  setup() {
    const {handleStaticResources} = utils()
    const {proxy} = getCurrentInstance();
    const store = useStore();
    const divRef = ref<HTMLAudioElement>();
    const musicUrl = computed(() => store.getters.musicUrl);
    const player = (el) => {
      divRef.value = el;
    };

    const musicPlay = computed(() => store.getters.musicPlay); // 播放状态
    // 监听播放还是暂停
    watch(musicPlay, () => {
      handlePlay();
    });

    // 开始/暂停
    function handlePlay() {
      if (store.getters.musicPlay) {
        divRef.value.play()
      } else {
        divRef.value.pause()
      }
    }

    // 获取歌曲链接后准备播放
    function startPlay() {
      console.log('playasd')
      divRef.value.play();
    }

    // 音乐播放结束时触发
    function ended() {
      proxy.$store.commit("setIsPlay", false);
    }

    return {
      musicUrl,
      musicPlay,
      player,
      startPlay,
      ended,
      handleStaticResources
    };
  },
});
</script>

<style scoped>
audio{
  display: none;
}
</style>
