<template>
    <div class="container">
        <header class ="jumbotron">
            <h3>
                <strong>{{currentUser.firstName}}</strong>'s Profile
            </h3>
        </header>
        <p>
            <strong>{{currentUser.firstName}} {{currentUser.lastName}}</strong>
        </p>
        <p>
            <strong>Token:</strong>
            {{ currentUser.accessToken.substring(0,20)}} ... {{currentUser.accessToken.substr(currentUser.accessToken.length - 20)}}
        </p>
        <p>
            <strong>Email:</strong> {{ currentUser.email }}
        </p>
    </div>
</template>

<script>
export default {
    name: 'Profile',
    computed: {
        currentUser() {
            return this.$store.state.auth.user;
        }
    },
    mounted() {
        if (!this.currentUser) {
            this.$router.push('/login');
        }
    }
};
</script>