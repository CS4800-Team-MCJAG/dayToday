import axios from "axios";

export default axios.create({
  baseURL: "http://localhost:8080/api", //change and add base url??
  headers: {
    "Content-type": "application/json" //change header??
  }
});