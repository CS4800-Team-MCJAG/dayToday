<template>
    <div class="d-flex flex-column">
        <h1>Events</h1>

        <div class="mb-4">
            <router-link to="/add" class="btn btn-success ml-2" exact>Create Event</router-link>
        </div>

        <div v-if="events && events.length > 0" class="d-flex flex-wrap justify-content-start">

            <div v-for="event in events" v-bind:key="event.startDayAndTime" >
                <div v-if="event.email == $store.state.auth.user.email" class="card mb-2 ml-2 text-dark bg-info" style="width: 18rem;">
                
                <div class="card-body">
                    <div class="d-flex justify-content-between">
                        <h5 class="card-title">{{ event.eventName }}</h5>
                        <span class="regular">{{ event.startDayAndTime | formatDate }}</span>
                    </div>

                    <h6 class="card-subtitle mb-2 text-secondary">
                         {{ event.zoomLink }}
                    </h6>

                    <div class="d-flex justify-content-between">
                        <router-link type="button" tag="button" class="card-link btn btn-primary" :to="{ name: 'event-edit', params: {id: event.eventID} }"
                            exact>Edit</router-link>
                        <a v-on:click.prevent="currentEvent = event.eventID" class="card-link btn btn-danger" href="#" v-b-modal.modal1>Delete</a>
                    </div>
                </div>
                </div>

            </div>

            <div>
                <b-modal id="modal1" ref="modal" centered title="Delete Confirmation">
                    <p class="my-4">Are you sure you would like to delete this event?</p>
                    <div slot="modal-footer" class="w-100 text-right">
                        <b-btn slot="md" class="mr-1" variant="danger" @click="deleteTask">Delete</b-btn>
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
                currentEvent: null       
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
                this.currentEvent = null;
            },
            deleteTask: async function() {
                this.$refs.modal.hide();
                await EventService.delete(this.currentEvent.eventID);
                const index = this.events.findIndex(event => event.eventID === this.currentEvent);
                this.events.splice(index, 1);
                this.currentEvent = null
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