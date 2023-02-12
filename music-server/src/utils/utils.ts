import {RouterName} from "../constant/constant";
import {getCurrentInstance} from "vue";
import {LocationQueryRaw} from "vue-router";

interface routerOptions {
    path?: string;
    query?: LocationQueryRaw;
}


export default function () {
    const {proxy} = getCurrentInstance()

    function handleRouter(routerName: string | number, options: routerOptions) {
        switch (routerName) {
            case RouterName.Home:
            case RouterName.Error:
            case RouterName.SignIn:
            case RouterName.SignOut:
            default:
                proxy.$router.push({path: options.path});
                break;
        }
    }

    return{
        handleRouter,
    }
}