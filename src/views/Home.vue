<template>
    <div class="container">
        <header class="jumbotron">
        <h1 class="display-4">dayToday</h1>
        <p class="lead">This is a sample agenda application.</p>
        <hr class="my-4">
        <p>Begin by adding events below.</p>
        </header>
    </div>
</template>

<script>
import UserService from '../services/user.service';

export default {
    name: 'Home',
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
};
</script>