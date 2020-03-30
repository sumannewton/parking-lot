# Parking lot
Implementation of parking lot in java language

## Requirements
 - JAVA 1.11
 - Maven 3

## Build Instructions from source code 

This project is built using [Apache Maven](http://maven.apache.org/).

Run the following command from the root of repository, to build the client JAR:
- Clone the source:
```
$ git clone https://github.com/sumannewton/parking-lot.git
```
- Build
```
$ mvn clean install
```

## How to setup and run
```
./setup.sh
```
#### To start an interactive command prompt 
```
./parking_lot.sh
```

Example:
<pre>
Parking lot service is starting....
|-------------------------------------------------------------|
|                     PARKING LOT MANUAL                      |
|-------------------------------------------------------------|
| help                                                        |
| exit                                                        |
| create_parking_lot {SLOT_SIZE}                              |
| park {VEHICLE_REG_NO} {VEHICLE_COLOUR}                      |
| leave {VEHICLE_REG_NO}                                      |
| status                                                      |
| registration_numbers_for_cars_with_color {VEHICLE_COLOUR}   |
| slot_numbers_for_cars_with_color {VEHICLE_COLOUR}           |
| slot_number_for_registration_number {VEHICLE_REG_NO}        |
|-------------------------------------------------------------|
cmdline> <b>create_parking_lot 3</b>
Created a parking lot with 3 slots
cmdline> <b>park KA-01-HH-1234 White</b>
Allocated slot number: 1
cmdline> <b>park KA-01-HH-9999 White</b>
Allocated slot number: 2
cmdline> <b>park KA-01-BB-0001 Black</b>
Allocated slot number: 3
cmdline> <b>park KA-01-HH-7777 Red</b>
Sorry, parking lot is full
cmdline> <b>leave 2</b>
Slot number 2 is free
cmdline> <b>status</b>
Slot No	| Registration No 	| Colour
---------------------------------------
  1	| KA-01-HH-1234		| White
  3	| KA-01-BB-0001		| Black

cmdline> <b>registration_numbers_for_cars_with_colour White</b>
[KA-01-HH-1234]
cmdline> <b>slot_numbers_for_cars_with_colour White</b>
[1]
cmdline> <b>slot_number_for_registration_number KA-01-HH-3141</b>
Not found
cmdline> <b>slot_number_for_registration_number KA-01-HH-1234</b>
1
cmdline> <b>exit</b>
Shutting down app....
</pre>

#### To run with input file having commands 
```
./parking_lot.sh <<INPUT_FILE_HERE>>
```
Sample input file is [here](src/main/resources/file_input.txt).

## Usage / Manual
Usage can be found [here](src/main/resources/usage.txt)

## Bugs

Bugs can be reported using Github issues.
