  import axios from "axios";

  export default axios.create({
      baseURL: `http://daytodayplanner.com:8080`,
      headers: {
          "Content-type": "application/json"
      }
  });