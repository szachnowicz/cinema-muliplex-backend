Ticket booking app
Application allows to book ticket in  multiplex cinema, with given movie offert there is no admin panel jet, which will allow to CRUD movie offer.

Made in ports and adapters architecture
contains 4 maven modules :

*app* - contains all spring configuration, dll scripts and AppStarter

*domain* -  contains package-private domain classes, public dto and primary ports and adapters.

*jpa-adapter* contains database entities and logic, implementations of secondary ports

*rest-adapter* - contains controllers, rest handlers and logic

using liquibase to create database schema and maven to manage dependencies.

running scripts:
cinema/mvn clean install
java -jar .cinema/app/target/app-0.0.1-SNAPSHOT.jar

However the best way is just to clone and import maven project in IntelJ

By default app work on port 8080

API - covers 4 endpoint

GET /cinema/api/confirmations?code=XXX confirms reservation
POST /cinema/api/reservations - creating a reservation
GET /cinema/api/movies?beginOfScreening=XXX&endOfScreening=XXX - shows cinema movie offer between dates
GET /cinema/api/movies/id=XXX - returns movie details with available seats

Api has a graphical representation using swagger at endpoint http://localhost:8080/swagger-ui.html#/

