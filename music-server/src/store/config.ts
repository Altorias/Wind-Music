import {createStore} from "vuex";

export default {
    state:{
        isCollapse: true,
        currentBreadcrumb: null,
        musicPlay: false,
        musicUrl: '',
        tabsList: [
            {
                path: '/Home',
                name: 'home',
                label: '首页',
                icon: 'home'
            }
        ],
    },
    getters: {
        isCollapse: (state) => state.isCollapse,
        musicPlay: (state) => state.musicPlay,
        musicUrl: (state) => state.musicUrl,
    },
    mutations: {
        updateIsCollapse(state) {
            state.isCollapse = !state.isCollapse
        },
        setMusicPlay: (state) => {
            state.musicPlay = !state.musicPlay
        },
        setMusicUrl: (state, musicUrl) => {
            state.musicUrl = musicUrl
        },
    }
}

