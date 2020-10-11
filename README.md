# dayToday 

A Calendar and Planner web service 


## Vue setup
### download files before installing Vue on command line:
```
npm install -g @vue/cli
```
#### Once installed, you should have node_modules/ folder to run Vue
### Compiles and minifies for production
```
npm run build
```

### Run code on localhost

npm run serve
```
### app should be now be running on http://localhost:8080/


### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).


## To run backend server:
For mac users: 
`./mvnw spring-boot:run`

For windows users:
`mvnw spring-boot:run`

### app should now be running on http://localhost:8080/

## To set up the database:
- In the root folder: `mysql -uroot -p < src/main/java/com/mcjag/daytoday/databaseScript.sql`

#####Check the database has been created by 
- `mysql -uroot -p`
- `show databases;` 