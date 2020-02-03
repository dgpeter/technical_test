# Technical Test Rideways Booking.com


## Getting Started

The project is built using IntelliJ IDEA, but can be run from other Java IDEs as well.
To fetch it from git and run it from the terminal you can follow the commands:

```
git clone git@github.com:dgpeter/technical_test.git
-> enter the directory
./mvnw clean install
```

./mvnw clean install - will return a JAR file -- spring-0.0.1-SNAPSHOT.jar and will also run the tests


### Prerequisites

```
JDK 1.8 minimum
```

### Part 1
## 1.a Dave's API result 
The application offers the possibility to isolate the results for every provider. You can either supply arguments in the edit configurations tab of IntelliJ or run the command (make sure you ran mvnw clean install before for this). The format printed in the std will be {car type} - {price}.
```
Order of arguments:
{pickup} {dropoff} provider {provider_name} {number_of_passengers}(optional)
```
For Dave's Api specifically:
```
Console Example:
java -jar target/spring-0.0.1-SNAPSHOT.jar 51.470020,-0.454295 52.470020,-0.454295 provider dave
For IntelliJ, supply args in run configuration
```
## 1.b Passenger constraint
```
Console Example:
java -jar target/spring-0.0.1-SNAPSHOT.jar 51.470020,-0.454295 52.470020,-0.454295 provider dave 5 <- Number of passengers 
For IntelliJ, supply args in run configuration
```
## For all providers and cheapest car type for each
Do not supply the provider's name. This will print a list of rides in descending order and if there are multiple cars with the same type, it will choose the cheapest option and print - {car type} - {supplier} - {price}
```
Order of arguments:
{pickup} {dropoff} {number_of_passengers}(optional)
```
```
Console Example:
java -jar target/spring-0.0.1-SNAPSHOT.jar 51.470020,-0.454295 52.470020,-0.454295 5 <- Number of passengers 
For IntelliJ, supply args in run configuration
```
### Part 2
Start the tomcat server with the command (same as above, but without arguments):
```
java -jar target/spring-0.0.1-SNAPSHOT.jar
```
Then in your preffered browser enter the urls:
* (http://localhost:8080/options/?pickup=3.410632,-2.157533&dropoff=3.410632,-2.157533)
* (http://localhost:8080/options/?pickup=3.410632,-2.157533&dropoff=3.410632,-2.157533&numberOfPassengers=5)
* (http://localhost:8080/options/jeff?pickup=3.410632,-2.157533&dropoff=3.410632,-2.157533)
* (http://localhost:8080/options/dave?pickup=3.410632,-2.157533&dropoff=3.410632,-2.157533)
* (http://localhost:8080/options/dave?pickup=3.410632,-2.157533&dropoff=3.410632,-2.157533&numberOfPassengers=5)

If no path variable is provided, a list of rides in JSON format will be provided (the minumum price/car_type rule applies here too)
All will be printed in descending order. Errors will be thrown, the console will only display if there are problems with the request. (Internal Server Error, Bad Request, Timeout (set for 2 sec)) etc.
## Built With
* [Maven](https://maven.apache.org/) - Dependency Management


