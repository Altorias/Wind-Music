import request, {post} from "./request";

export default {
    //登录
    signIn: (params) => {
        return request({
            url: '/admin/login',
            method: 'post',
            data: params,
        })
    },
    // 获取图片路径
    getImage: (par)
    // 获取所有歌手
    getArtistList: () => {
        return request({
            url: '/artist/getArtistList',
            method: 'get',
        })
    }
}