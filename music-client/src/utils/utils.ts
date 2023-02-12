import {useStore} from "vuex";
import {computed, getCurrentInstance} from "vue";
import {RouterName} from "../constant/constant";
import {LocationQueryRaw} from "vue-router";

interface routerOptions {
    path?: string;
    query?: LocationQueryRaw;
}

export default function () {
    const { proxy } = getCurrentInstance();
    const store = useStore();
    const token = computed(() => store.getters.token);

    // 导航索引
    function setActivePage(value) {
        store.commit("setActivePage", value);
    }

    // 路由管理
    function handleRouter(routerName: string | number, options: routerOptions) {
        switch (routerName) {
            case RouterName.Search:
                proxy.$router.push({ path: options.path, query: options.query });
                break;
            case RouterName.Home:
            case RouterName.PlayList:
            case RouterName.Singer:
            case RouterName.Personal:
            case RouterName.Setting:
            case RouterName.SignIn:
            case RouterName.SignUp:
            case RouterName.Error:
            default:
                proxy.$router.push({ path: options.path });
                break;
        }
    }

    return {
        setActivePage,
        handleRouter
    }

}
