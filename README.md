# Mars Rover

## Description

A simple program modelling robots moving on a grid.

## Getting Started

### Executing program

* This program is in Java 17 and built with Gradle. Make sure you have both of these to build and run the program.
* To run the program, run the following commands on your console
```
./gradlew clean build
java -jar {pathToJar} {file}
```
where `{pathToJar}` is the path to the jar file built in the first command, `{file}` is the file containing the inputs to the program.
An example of execution looks like the following
```
java -jar build/libs/MarsRover-1.0-SNAPSHOT.jar samples/test1.txt
```
```
(4, 4, E)
(0, 4, W) LOST
```
