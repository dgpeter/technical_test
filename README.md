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
The application offers the possibility to isolate the results for every provider. You can either supply arguments in the edit configurations tab of IntelliJ or run the command (make sure you ran mvnw clean install before for this):
```
java -jar target/spring-0.0.1-SNAPSHOT.jar {pickup} {dropoff} provider {provider_name} {number_of_passengers}
```

### Part 2 

## Built With
* [Maven](https://maven.apache.org/) - Dependency Management

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
