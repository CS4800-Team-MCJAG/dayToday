<template>
    <div class="d-flex flex-column">
        <h1>Events</h1>

        <div class="mb-4">
            <router-link to="/add" class="btn btn-success ml-2" exact>Create Event</router-link>
        </div>

        <div v-if="events && events.length > 0" class="d-flex flex-wrap justify-content-start">

            <div v-for="event in events" v-bind:key="event.startDayAndTime" >
                <div v-if="event.email == $store.state.auth.user.email" class="card mb-2 ml-2 text-dark bg-light" style="width: 18rem;">
                
                <div class="card-body">
                    <div class="d-flex justify-content-between">
                        <h5 class="card-title">{{ event.eventName }}</h5>
                        <span class="regular">{{ event.startDayAndTime | formatDate }}</span>
                    </div>

                    <div class="card-body">
                        <h6 class="card-subtitle mb-2 text-secondary">
                            Notification Time: {{ event.alert | formatDate }}
                        </h6>
                        <h7 class="card-subtitle mb-2 text-dark"> Event Link: </h7>
                        <a :href="'http://' + event.zoomLink" target="_blank" class="card-link"> {{ event.zoomLink }} </a>

                    </div>

                    <div class="d-flex justify-content-between">
                        <router-link type="button" tag="button" class="card-link btn btn-primary" :to="{ name: 'event-edit', params: {id: event.eventID} }"
                            exact>Edit</router-link>
                        <a v-on:click.prevent="currentEventID = event.eventID" class="card-link btn btn-danger" href="#" v-b-modal.modal1>Delete</a>
                    </div>
                </div>
                </div>
            </div>

            <div>
                <b-modal id="modal1" ref="modal" centered title="Delete Confirmation">
                    <p class="my-4">Are you sure you would like to delete this event?</p>
                    <div slot="modal-footer" class="w-100 text-right">
                        <b-btn slot="md" class="mr-1" variant="danger" @click="deleteEvent">Delete</b-btn>
                        <b-btn slot="md" variant="secondary" @click="cancelModal">Cancel</b-btn>
                    </div>
                </b-modal>
            </div>
        </div>

        <div v-if="events.length === 0" class="ml-2">
            <div class="alert alert-info">No events found.</div>
        </div>

    </div>

</template>

<script>

    import EventService from '../services/events.service';
    import moment from 'moment';

    export default {
        name: 'view-events',
        data: function() {
            return {
                events: [],
                currentEventID: null       
            };
        },
        
        methods: {
            fetchEvents() {
                EventService.getAll()
                    .then(res => {
                            this.events = res.data;
                        });
            },
            eventIsLate: function(date) {
                return moment(date).isBefore();
            },
            cancelModal: function() {
                this.$refs.modal.hide();
                this.currentEventID = null;
            },
            deleteEvent: async function() {
                this.$refs.modal.hide();
                await EventService.delete(this.currentEventID);
                const index = this.events.findIndex(event => event.eventID === this.currentEventID);
                this.events.splice(index, 1);
                this.currentEventID = null
            }
        },
        filters: {
            formatDate: function(value){
                return moment(value).add(8,'hours').format('MM/DD/YYYY hh:mm a');
            },
        },
        mounted(){
            this.fetchEvents();
        }
    };

</script>

<style scoped>

.custom-bg {
    color: #4968D8;
}

</style>