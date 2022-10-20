# CampGladiator Code Challenge

### Tech stack
* OpenJDK 11
* SpringBoot
* PostgreSQL
* Spock framework
* Swagger

### Build project and run tests:
Go to the project root folder and run:
```shell
./gradlew clean build
```

### Run the project
In order to run the project the easiest way you need to have [Docker](https://docs.docker.com/get-docker/) installed. <br> 
If you have docker installed, go to the project root folder and rename the `.env.example` file to `.env`:
```shell
mv .env.example .env
```
Then, make sure you built your project following the previous step and run the following command:
```shell
docker-compose up --build
```

You should see both the DB and the app starting up soon.

**if you encounter any issue running the `docker-compose` command above, make sure you have docker-compose installed. It should come by default with latest docker distributions but you might be running and older one.**

Once the project is running, you should be able to access the api portal through http://localhost:8080

## Few considerations regarding the implementation
* Since the requirements were very simple, I've decided to not create any layer between the DB access layer (Repository) and the web layer (RestController). If more complex requirements
were needed (e.g. calling and external service to get some data) we could introduce an intermediary layer. 
* Usually Mapping/Transformation (JSON <-> Entity) is done outside of the entity itself, but because the requirements were very simple I decided to not introduce another class to take care of the mapping. 
Should we have a more complex mapping scenario (e.g. Multiple different representations for the same entity) a new class could be introduced.
* I decided to keep the field validations very simple which might not reflect a real world scenario.
* To keep things simple I decided to not introduce integration tests. In a real production scenario we could've introduced Integration tests 
to make checks against a real DB.
