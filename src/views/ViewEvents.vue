<template>
    <div class="list row">
        <div class="col-md-6">
            <h4>Events List</h4>
            <ul class="list-group">
                <li class="list-group-item"
                    :class="{ active: index == currentIndex }"
                    v-for="(event, index) in events"
                    :key="index"
                    @click="setActiveEvent(event, index)" 
                >
                    {{ event.eventName }}
                   <p> {{ event.zoomLink }}</p>
                </li>
            </ul>
        </div>

        <div class="col-md-6">
            <div v-if="currentEvent">
            <h4>Event</h4>
            <div>
                <label><strong>Event:</strong></label> {{ currentEvent.eventName }}
            </div>
            <div>
                <label><strong>Start Day and Time:</strong></label> {{ currentEvent.startDayAndTime | formatDate }}
            </div>
            <div>
                <label><strong>End Day and Time:</strong></label> {{ currentEvent.endDayAndTime | formatDate}}
            </div>
            <div>
                <label><strong>Notification Time:</strong></label> {{ currentEvent.alert | formatDate}}
            </div>
            <div>
                <label><strong>Link:</strong></label> {{ currentEvent.zoomLink }}
            </div>

            <router-link class="badge badge-warning" :to="'/events/' + currentEvent.eventID">
                Edit
            </router-link>

            </div>
            <div v-else>
                <p>Click on Event to edit</p>
            </div>
        
        </div>
    </div>
</template>

<script>
import EventService from "../services/events.service";
import Moment from 'moment';

export default{
    name: 'view-events',
    data() {
        return{
            events: [],
            currentEvent: null,
            currentIndex: -1,
            
            event: {
                
            }
        };
    },

    methods: {
        fetchEvents() {
            EventService.getAll()
                .then(response => {
                    this.events = response.data;
                    console.log(response.data);
                })
                .catch(e => {
                    console.log(e);
                });
        },
        setActiveEvent(event, index) {
            this.currentEvent = event;
            this.currentIndex = index;
        }
        
    },

    filters: {
        formatDate: function(value){
           return Moment(value).format('MM/DD/YYYY hh:mm a');
        }
    },
    mounted(){
            this.fetchEvents();
    }
};

</script>

<style>

.list {
  text-align: center;
  max-width: 1000px;
  margin: auto;
}

</style>