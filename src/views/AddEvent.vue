<template>
  <div class="custom-form">
    <div v-if="!submitted">
      <h3>Add Event</h3>
      <div class="form-group">
          <div class="form-group">
            <label for="eventName">Event Name</label>
              <input class="form-control" type="text" id="eventName" placeholder="Event Name" v-model="event.name" required>
          </div>

          <div class="form-group">
            <label for="startDayAndTime">Start Day and Time</label>
                <input type="datetime-local" class="form-control" id="startDayAndTime" placeholder="Start Date" v-model="event.startDate" required>
          </div>

          <div class="form-group">
            <label for="endDayAndTime">End Day and Time</label>
            <input type="datetime-local" class="form-control" id="endDayAndTime" placeholder="End Date" v-model="event.endDate" required>
          </div>

          <div class="form-group">
            <label for="alert">Notification Time</label>
              <input type="datetime-local" class="form-control" id="alert" placeholder="Notification Time" v-model="event.alert" required>
          </div>

          <div class="form-group">
            <label for="zoomLink">Link</label>
              <input class="form-control" type="text" id="zoomLink" placeholder="Link to event" v-model="event.link" required>
          </div>

          <button class="btn btn-success" @click="addEvent">Submit</button>

      </div>
    </div>
    <div v-else>
      <h4>You submitted successfully!</h4>
      <button class="btn btn-success" @click="newEvent">Add</button>
    </div>
  </div>
</template>

<script>
import EventService from '../services/events.service';

export default {
  name: 'add-event',
  data() {
    return{
      event: {
        id: null,
        eventName: "",
        startDayAndTime: "",
        endDayAndTime: "",
        alert:"",
        email: this.$store.state.auth.user.email,
        link:""
      },
      submitted: false
    };
	},

	methods: {

		addEvent() {
      var data = {
        eventName: this.event.name,
        startDayAndTime: this.event.startDate,
        endDayAndTime: this.event.endDate,
        alert: this.event.alert,
        email: this.$store.state.auth.user.email,
        zoomLink: this.event.link
      };

      EventService.create(data)
        .then(response => {
          this.event.eventID = response.data.eventID;
          console.log(response.data);
          this.submitted=true;
        })
        .catch(e=>{
          console.log(e)
        });
    },
    
		newEvent() {
      this.submitted = false;
      this.event = {};
    }
  }
};
</script>

<style scoped>
  .custom-form {
    max-width: 40rem;
    display: flex;
    flex-direction: column;
    margin-left: auto;
    margin-right: auto;
}
</style>