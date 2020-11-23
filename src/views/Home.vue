<template>
    <div class="container">
        <header class="jumbotron">
        <h1 class="display-4">dayToday</h1>
        <p class="lead">Use this application to get your events in order! Never miss a class or special event again.</p>
        <hr class="my-4">
        <p>Begin by registering and adding events below.</p>
        <router-link class="btn btn-success btn-lg" to="/add">
            Add Events
        </router-link>
        </header>
    </div>
</template>

<script>

export default {
    name: 'Home'
};
</script>