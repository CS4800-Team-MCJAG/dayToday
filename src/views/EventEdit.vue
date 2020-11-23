<template>
    <div>
        <form class="custom-form" v-on:submit.prevent="onSubmit">
            <h3>Edit Event</h3>

            <div class="form-group">
                <label for="eventName">Event Name</label>
                <input v-model="event.eventName" type="text" class="form-control" id="eventName" name="eventName" placeholder="Event Name" />
            </div>
            <div class="form-group">
                <label for="startDayAndTime">Start Day and Time</label>
                <input v-model="event.startDayAndTime" type="datetime-local" class="form-control" id="startDayAndTime" name="startDayAndTime" />
            </div>
            <div class="form-group">
                <label for="endDayAndTime">End Day and Time</label>
                <input v-model="event.endDayAndTime" type="datetime-local" class="form-control" id="endDayAndTime" name="endDayAndTime" />
            </div>
            <div class="form-group">
                <label for="alert">Notification Time</label>
                <input v-model="event.alert" type="datetime-local" class="form-control" id="alert" name="alert" />
            </div>
            <div class="form-group">
                <label for="zoomLink">Link</label>
                <input v-model="event.zoomLink" type="text" class="form-control" id="zoomLink" name="zoomLink" placeholder="Link" />
            </div>
            <div class="form-group">
                <button type="submit" @click="onSubmit" class="btn btn-secondary">Save Changes</button>
            </div>
        </form>
    </div>
</template>

<script>
    import EventService from '../services/events.service';
    import moment from 'moment';

    export default {
        name: 'event-edit',
        data: function() {
            return {
                event: {
                    eventName: '',
                    startDayAndTime: '',
                    endDayAndTime: '',
                    alert: '',
                    zoomLink: ''
                }
            }
        },
        methods: {
            onSubmit: async function() {
                await EventService.update(this.event.eventID, this.event);
                this.$router.push({ name: 'view-events' });
            }
        },
        mounted(){
            EventService.get(`${this.$route.params.id}`)
                .then(response => {
                    if (!response) {
                        this.$router.push('/events');
                    } else {
                        
                            const event = response.data;
                            event.startDayAndTime = moment(event.startDayAndTime).add(8,'hours').format('MM/DD/YYYY hh:mm a');
                            event.endDayAndTime = moment(event.endDayAndTime).add(8,'hours').format('MM/DD/YYYY hh:mm a');
                            event.alert = moment(event.alert).add(8,'hours').format('MM/DD/YYYY hh:mm a');
                            this.event = event;
                    
                    }
                });
        }
    };
</script>

<style scoped>
form.custom-form {
    max-width: 40rem;
    display: flex;
    flex-direction: column;
    margin-left: auto;
    margin-right: auto;
}
</style>