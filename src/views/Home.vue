<template>
    <div class="container">
        <header class="jumbotron">
        <h1 class="display-4">dayToday</h1>
        <p class="lead">Use this application to get your events in order! Never miss a class or special event again.</p>
        <hr class="my-4">
        <p>Begin by registering and adding events.</p>
        <router-link class="btn btn-success btn-lg" to="/events">
            View Events
        </router-link>
        </header>
    </div>
</template>

<script>
//import UserService from '../services/user.service';

export default {
    name: 'Home'/*,
    data(){
        return{
            content: ' '
        };
    },
    mounted(){
        UserService.getPublicContent().then(
            response => {
                this.content=response.data;
            },
            error => {
                this.content = 
                (error.response && error.response.data) ||
                error.message || error.toString();
            }
        );
    }
    */
};
</script>