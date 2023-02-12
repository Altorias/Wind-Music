import {createRouter, createWebHashHistory,RouteRecordRaw } from "vue-router";

const routes: Array<RouteRecordRaw> = [
    {
        path: "/:pathMatch(.*)*",
        redirect: "/404",
    },
    {
        path: "/404",
        component: () => import("../views/error/404.vue"),
    },
    {
        path: '/',
        component: () => import('../views/Main.vue'),
        name: 'main'
    },

]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})

export default router