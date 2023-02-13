import request, {getBaseURL, post} from "./request";

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
    getImageUrl: (url) => {
        return request({
            url: `${getBaseURL()}${url}`,
            method: 'get'
        })
    },
    // 获取所有歌手
    getArtistList: () => {
        return request({
            url: '/artist/getArtistList',
            method: 'get',
        })
    },
    // 添加歌手
    addArtist: (params) => {
        return request({
            url: '/artist/addArtist',
            method: 'post',
            data: params
        })
    },
    // 添加歌手
    updateArtist: (params) => {
        return request({
            url: '/artist/updateArtist',
            method: 'post',
            data: params
        })
    },
    // 删除歌手
    deleteArtist: (params) => {
        return request({
            url: '/artist/deleteArtist',
            method: 'get',
            data: params
        })
    }
}
