export default [
    {
        name: 'book',
        path: '/book',
        component: () => import('../view/book/list.vue')
        // component: resolve => require(['../view/phone/phone'], resolve),
    },
    {
        name: 'index',
        path: '/index',
        component: () => import('../view/index/index.vue')
        // component: resolve => require(['../view/phone/phone'], resolve),
    },
    {
        name: 'title',
        path: '/',
        component: () => import('../view/login.vue')
        // component: resolve => require(['../view/index/HelloWorld'], resolve),
    },
    {
        name: 'document',
        path: '/document',
        component: () => import('../view/document/list.vue')
        // component: resolve => require(['../view/index/HelloWorld'], resolve),
    },
    {
        name: 'statistics',
        path: '/statistics',
        component: () => import('../view/statistics/list.vue')
        // component: resolve => require(['../view/index/HelloWorld'], resolve),
    },

];


