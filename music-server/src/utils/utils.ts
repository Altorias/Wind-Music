import {RouterName} from "../constant/constant";
import {getCurrentInstance, ref} from "vue";
import {LocationQueryRaw} from "vue-router";
import {getBaseURL} from "../api/request";

interface routerOptions {
    path?: string;
    query?: LocationQueryRaw;
}


export default function () {
    const {proxy} = getCurrentInstance()
    const uploadTypes = ref(["jpg", "jpeg", "png", "gif"]);

    function handleRouter(routerName: string | number, options: routerOptions) {
        switch (routerName) {
            case RouterName.MusicListDetail:
                proxy.$router.push({ path: options.path, query: options.query });
                break;
            case RouterName.Home:
            case RouterName.Error:
            case RouterName.SignIn:
            case RouterName.SignOut:
            case RouterName.Artist:
            case RouterName.Music:
            case RouterName.MusicList:
            default:
                proxy.$router.push({path: options.path});
                break;
        }
    }
    const handleGender = (gender) => {
        if (gender === 0){
            return "女";
        } else if (gender === 1) {
            return "男";
        } else if (gender === 2) {
            return "乐队";
        }
    }
    const handleDate = (value) =>{
        let times = new Date(value)
        let year = times.getFullYear()
        let month = times.getMonth() + 1
        let date = times.getDate()

        function add(m) {
            return m < 10 ? "0" + m : m
        }

        return add(year) + "-" + add(month) + "-" + add(date)
    }
    const handleStaticResources = (url) => {
        return `${getBaseURL()}${url}`
    }
    const checkUploadImg = (file) => {
        const ltCode = 2;
        const isLt2M = file.size / 1024 / 1024 < ltCode;
        const isExistFileType = uploadTypes.value.includes(file.type.replace(/image\//, ""));

        if (!isExistFileType) {
            (proxy as any).$message.error(`图片只支持 ${uploadTypes.value.join("、")} 格式!`);
        }
        if (!isLt2M) {
            (proxy as any).$message.error(`上传头像图片大小不能超过 ${ltCode}MB!`);
        }

        return isExistFileType && isLt2M;
    }
    const checkUploadMusic = (file) => {
        const ltCode = 10;
        const isLt10M = file.size / 1024 / 1024 < ltCode;
        const testmsg = file.name.substring(file.name.lastIndexOf(".") + 1);
        const extension = testmsg === "mp3";

        if (!extension) {
            (proxy as any).$message({
                message: "上传文件只能是mp3格式！",
                type: "error",
            });
        }
        if (!isLt10M) {
            (proxy as any).$message.error(`上传歌曲大小不能超过 ${ltCode}MB!`);
        }

        return extension && isLt10M;
    }
    const handleLyric = (text) => {
        const lines = text.split("\n");
        const pattern = /\[\d{2}:\d{2}.(\d{3}|\d{2})\]/g;
        const result = [];

        // 对于歌词格式不对的特殊处理
        if (!/\[.+\]/.test(text)) {
            return [text];
        }
        for (const item of lines) {
            if (pattern.test(item)) {
                const value = item.replace(pattern, ""); // 存歌词
                result.push(value);
            }
        }
        return result;
    }

    return{
        handleRouter,
        handleGender,
        handleDate,
        handleStaticResources,
        checkUploadImg,
        handleLyric,
    }
}
