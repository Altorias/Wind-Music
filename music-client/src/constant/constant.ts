// 网站名称
export const APP_NAME = "Lyric-Music";

// 路由名称
export const enum RouterName {
    Home = "/",
    PlayList = "/playlist",
    Artist = "/artist",
    AudioBook = "/audiobook",
    Personal = "/personal",
    MusicCenter = "/musicCenter",
    Setting = "/setting",
    SignIn = "/signin",
    SignUp = "/signup",
    Search = "/search",
    Error = "/404",
    SignOut = "0"
}

//Header 导航栏
export const enum HeaderPageName {
    Home = "首页",
    PlayList = "歌单",
    Artist = "歌手",
    AudioBook = "有声书",
    Personal = "个人主页",
    MusicCenter = "我的音乐",
    Setting = "设置",
    SignIn = "登录",
    SignUp = "注册",
    SignOut = "退出",
}

// 左侧导航栏
export const HEADER_LEFT_LIST = [
    {
        name: HeaderPageName.Home,
        path: RouterName.Home
    },
    {
        name: HeaderPageName.PlayList,
        path: RouterName.PlayList
    },
    {
        name: HeaderPageName.Artist,
        path: RouterName.Artist
    },
    {
        name: HeaderPageName.AudioBook,
        path: RouterName.AudioBook
    },
    {
        name: HeaderPageName.MusicCenter,
        path: RouterName.MusicCenter
    }
]

// 右侧导航栏
export const HEADER_RIGHT_LIST = [
    {
        name: HeaderPageName.SignIn,
        path: RouterName.SignIn,
    },
    {
        name: HeaderPageName.SignUp,
        path: RouterName.SignUp,
    },
]

//用户下拉菜单栏
export const USER_MENU_LIST = [
    {
        name: HeaderPageName.Personal,
        path: RouterName.Personal
    },
    {
        name: HeaderPageName.Setting,
        path: RouterName.Setting
    },
    {
        name: HeaderPageName.SignOut,
        path: RouterName.SignOut
    }
]