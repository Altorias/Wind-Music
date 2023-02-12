export default {
    state: {
        token: false, //用户登录
        searchWord: "", //搜索关键词
        activePage: ""
    },
    getters: {
        token: (state) => state.token,
        searchWord: (state) => state.searchWord,
        activePage: (state) => state.activePage,
    },
    mutations: {
        setToken: (state, token) => {
            state.token = token
        },
        setSearchWord: (state, searchWord) => {
            state.searchWord = searchWord
        },
        setActivePage: (state, activePage) => {
            state.activePage = activePage;
        },
    }
}