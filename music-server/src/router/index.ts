import {createRouter, createWebHistory, RouteRecordRaw} from "vue-router";

const routes: Array<RouterRecordRaw> = [
    {
        path: "/:pathMatch(.*)*",
        redirect: "/404",
    },
    {
        path: "/404",
        component: () => import("../views/error/404.vue"),
    },
    {
        path:'/',
        component: () => import("../views/SignIn.vue")
    },
    {
        path:"/Home",
        component: () => import("../views/Home.vue"),
        children:[
            {
                path: '/Info',
                component: () => import('../views/homePage/InfoPage.vue'),
                meta: { title: 'Info' }
            },
            {
                path: '/Music',
                component: () => import('../views/homePage/MusicPage.vue'),
                meta: { title: 'Music' }
            },
            {
                path: '/Artist',
                component: () => import('../views/homePage/ArtistPage.vue'),
                meta: { title: 'Singer' }
            },
        ]
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router
