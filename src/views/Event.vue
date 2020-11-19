<template>
    <div v-if="currentEvent" class="edit-form">
        <h4>Event</h4>
        <form>
            <div class="form-group">
                <label for="eventName">Event Name</label>
                <input class="form-control" id="eventName" type="text" v-model="currentEvent.eventName"/>
            </div>
            <div class="form-group">
                <label for="startDayAndTime">Start Day and Time</label>
                    <input type="datetime-local" class="form-control" id="startDayAndTime" v-model="currentEvent.startDate">
            </div>
            <div class="form-group">
                <label for="endDayAndTime">End Day and Time</label>
                    <input type="datetime-local" class="form-control" id="endDayAndTime" v-model="curentEvent.endDate">
            </div>
            <div class="form-group">
                <label for="alert">Notification Time</label>
                    <input type="datetime-local" class="form-control" id="alert" v-model="currentEvent.alert">
            </div>
            <div class="form-group">
                <label for="zoomLink">Link</label>
                <input class="form-control" id="zoomLink" type="text" v-model="currentEvent.zoomLink"/>
            </div>
        </form>
        <button class="badge badge-danger mr-2" @click="deleteEvent">
                Delete
        </button>

        <button type="submit" class="badge badge-success" @click="updateEvent">
                Update
        </button>
        <p>{{ message }}</p>


    </div>
    <div v-else>
        <br />
        <p>Please click on an Event...</p>
    </div>

</template>

<script>
import EventService from "../services/events.service";

export default {
    name:"event",
    data(){
        return {
            currentEvent: null,
            message: ''
        };
    },
    methods: {
        getEvent(id){
            EventService.get(id)
                .then(response => {
                    this.currentEvent = response.data;
                    console.log(response.data);
                })
                .catch(e => {
                    console.log(e);
                });
        },

        updateEvent(){
            EventService.update(this.currentEvent.eventID, this.currentEvent)
                .then(response => {
                    console.log(response.data);
                    this.message = "Updated event successfully!";
                })
                .catch(e => {
                    console.log(e);
                });
        },

        deleteEvent() {
            EventService.delete(this.currentEvent.eventID)
                .then(response => {
                    console.log(response.data);
                    this.$router.push({ name: "view-events" });
                })
                .catch(e => {
                    console.log(e);
                });
        }

    },
    mounted() {
        this.message='';
        this.getEvent(this.$route.params.id);
    }
};
</script>

<style>
.edit-form {
  max-width: 300px;
  margin: auto;
}
</style>