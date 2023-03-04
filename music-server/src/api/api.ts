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
    getAllArtist: () => {
        return request({
            url: '/artist/getAllArtist',
            method: 'get',
        })
    },
    // 添加歌手
    insertArtist: (params) => {
        return request({
            url: '/artist/insertArtist',
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
    },
    // 获取所有歌曲
    getAllMusic: () => {
        return request({
            url: '/music/getAllMusic',
            method: 'get',
        })
    },
    // 添加歌曲
    insertMusic: (params) => {
        return request({
            url: '/music/insertMusic',
            method: 'post',
            data: params
        })
    },
    // 修改歌曲信息
    updateMusicInfo: (params) => {
        return request({
            url: '/music/updateMusicInfo',
            method: 'post',
            data: params
        })
    },
    // 删除歌曲
    deleteMusic: (params) => {
        return request({
            url: '/music/deleteMusic',
            method: 'get',
            data: params
        })
    },
    // 获取所有歌单
    getAllMusicList: () => {
        return request({
            url: '/musicList/getAllMusicList',
            method: 'get',
        })
    },
    // 添加歌单
    insertMusicList: (params) => {
        return request({
            url: '/musicList/insertMusicList',
            method: 'post',
            data: params
        })
    },
    // 修改歌单信息
    updateMusicListInfo: (params) => {
        return request({
            url: '/musicList/updateMusicListInfo',
            method: 'post',
            data: params
        })
    },
    // 删除歌单
    deleteMusicList: (params) => {
        return request({
            url: '/musicList/deleteMusicList',
            method: 'get',
            data: params
        })
    },
    // 获取歌单中的歌曲
    selectMusicByListId: (params) => {
        return request({
            url: '/musicListDetail/selectMusicByListId',
            method: 'get',
            data: params
        })
    },
    // 从歌单中删除歌曲
    deleteMusicFromList: (params) => {
        return request({
            url: '/musicListDetail/deleteMusicFromList',
            method: 'get',
            data: params
        })
    },
    // 将歌曲添加到歌单中
    insertMusicToList: (params) => {
        return request({
            url: '/musicListDetail/insertMusicToList',
            method: 'post',
            data: params
        })
    },
    // 选择不在歌单中的歌曲
    getMusicNotInList: (params) => {
        return request({
            url: '/musicListDetail/getMusicNotInList',
            method: 'get',
            data: params
        })
    },


}
