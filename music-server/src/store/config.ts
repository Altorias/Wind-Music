import {createStore} from "vuex";

export default {
    state:{
        isCollapse: true,
        currentBreadcrumb: null,
        tabsList: [
            {
                path:'/Home',
                name:'home',
                label:'首页',
                icon:'home'
            }
        ],
    },
    getters: {
        isCollapse: (state) => state.isCollapse,
    },
    mutations:{
        updateIsCollapse(state){
            state.isCollapse = !state.isCollapse
        }
    }
}

