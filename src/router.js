import Vue from 'vue';
import Router from 'vue-router';
import Home from './views/Home.vue';
import Login from './views/Login.vue';
import Register from './views/Register.vue';

Vue.use(Router);

export const router = new Router({
    routes: [{
            path: '/',
            name: 'home',
            component: Home
        },
        {
            path: '/home',
            component: Home
        },
        {
            path: '/login',
            component: Login
        },
        {
            path: '/register',
            component: Register
        },
        {
            path: '/profile',
            name: 'profile',
            component: () =>
                import ('./views/Profile.vue')
        },
        {
            path: '/events',
            name: 'view-events',
            component: () =>
                import ("./views/ViewEvents.vue")
        },
        {
            path: '/events/:id',
            name: 'event',
            component: () =>
                import ("./views/Event.vue")
        },
        {
            path: "/add",
            name: "add-event",
            component: () =>
                import ("./views/AddEvent.vue")
        }
    ]
});

router.beforeEach((to, from, next) => {
    const publicPages = ['/login', '/register', '/home'];
    const authRequired = !publicPages.includes(to.path);
    const loggedIn = localStorage.getItem('user');

    if (authRequired && !loggedIn) {
        next('/login');
    } else {
        next();
    }
});