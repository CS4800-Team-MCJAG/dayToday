<template>
    <div>
        <h1>Edit Event</h1>
        <form class="custom-form" v-on:submit.prevent="onSubmit">
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
                    email: this.$store.auth.user.email,
                    alert: '',
                    zoomLink: ''
                }
            }
        },
        methods: {
            onSubmit: async function() {
                const request = {
                    event: this.event
                }
                await EventService.update(request);
                this.$router.push({ name: 'view-events' });
            }
        },
        mounted(){
            EventService.get(`${this.$route.params.id}`)
                .then(response => {
                    if (!response) {
                        this.$router.push('/events');
                    } else {
                        (vm => {
                            const event = response.data;
                            event.startDayAndTime = moment(event.startDayAndTime).add(8,'hours').format('MM/DD/YYYY hh:mm a');
                            event.endDayAndTime = moment(event.endDayAndTime).add(8,'hours').format('MM/DD/YYYY hh:mm a');
                            event.alert = moment(event.alert).add(8,'hours').format('MM/DD/YYYY hh:mm a');
                            vm.event = event;
                        })
                    }
                });
        }
    };
</script>