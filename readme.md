## Corsair Color Manager ##

### Description ###

####What is this program about?####
This program includes a driver for the corsair k70rgb keyboard and a toolbox to write colorprograms for the background lights.


####What the structure of this tool?####
The base of the program is the linux driver of CalcProgrammer1 (https://github.com/CalcProgrammer1). To control the colors of the keyboard it continuously reads color codes from a memcached "server". This allows the programmer to manipulate colors dynamically over a common standard.
The next layer of this project is the java program. To give everyone the ability to write cool color effects without knowledge about memcached, the java program convert simple json rules to shiny color effects.

hierarchy 
- C++ program for keyboard control
- Memcached "Server" for color exchange support
- Java program to execute color rules
- JSON program in human readable form


#### What does i need to execute?####
Dependencies
- You have to install memcached server on your machine
- you need libusb (libusb-0.1)
- java jre

Execute: (compiled source in CorsairColorManager/lab)
- start the linux driver by execute: ./keyboardColorChanger
- start the java program: java -jar CorsairColorManager.jar <pathToYourJSONProgram>


####Know issiues / todos:####
- Currently the program is designed for german keyboard Layout.
- HowTo and description of the color rules
- remove fixed frame rate of 30 Frames / Second.
- only linux supported in c++ driver.
- register key presses and animate
- color mixing rules for multiple color settings per key
- alpha channel support


####Greetings####
Special thanks to:
- CalcProgrammer1 (https://github.com/CalcProgrammer1) for his excellent work with the linux driver
- The corsair forum community for inspiration and discussions
