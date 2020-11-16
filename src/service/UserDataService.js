import http from "../http-common";

class UserDataService
{
   displayGraph(email) 
    {
        return http.get("/graph/{email}");
    }

    sendEmail(eventID) 
    {
        return http.get("/email/{eventID}");
    }

    createUser(user) 
    {
        return http.post("/user");
    }

    showAllUsers() 
    {
        return http.get("/users");
    }

    deleteUser(email) 
    {
        return http.delete("/user/{email}");
    }

    updateUser(user) {
        return http.put("/user");
    }
}

export default new UserDataService();