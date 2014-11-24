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


####Know issues / todos:####
- more color rules :)
- register key presses and animate
- group keys
- add key rules like ALL_KEY - "WASD"
- remove fixed frame rate of 30 frames / second.
- only linux supported in c++ driver.
- color mixing rules for multiple color settings per key
- alpha channel support


####Greetings####
Special thanks to:
- CalcProgrammer1 (https://github.com/CalcProgrammer1) for his excellent work with the linux driver
- The corsair forum community for inspiration and discussions


### Howto write a json program ###
To write a new program in json you just need some meta information and some color-change rules. The meta-information are stored in the base object:

### Basic program attributes ###

##### colorMixingRule #####
Value: <OVERRIDE>, <ADDITION>, <SUBTRACT>, <AVERAGE>, <ADDITION_WITH_ALPHA>, <SUBTRACT_WITH_ALPHA>
Only one global color mixing rule can be set. It describe the behavior of a key if multiple colors are set. Currently only the override Method is supported.

##### startAction #####
Value: String (name)
Define the first rule which starts after loading the program.

##### rules #####
Value: Array of objects
Contains a list of different rules which specified for the current program


### General Color Rule attributes  ###
Every role need some basic settings. These settings are:

##### delay #####
Value: int (milliseconds)
Time value in milliseconds until the "doAfter" rules will start

##### alias #####
Value: String (name)
Contains the name of the rule. A rule-name has to be unique.

##### keys #####
Value: Array of String
A list of key names who are effected by this rule.

##### doAfter #####
Value: Array of String
Contains a list of rule names (alias) to execute after the delay.



### SetColor ###
The SetColor -rule is the a standard rule. You can set a color for specified keys.

##### type #####
Value: String (SetColor)
the type attribute contains the name of the current rule

##### color #####
Value: String - hexadecimal color code
Contains the color to set. the values are given in hexadecimal values in the format RRGGBBAA where RR=red, GG=green, BB=blue and AA=alpha. The alpha channel is optional, the default value is FF(255). So colors like FFAA00 are valid.



### LinearColorChange ###
A linear color change is a transformation between to colors. The transformation is a simple difference calculation between to colors. The difference is calculated for each color-channel.
Example: A transformation from FFFF00 to 0000FF will decrease the red- and the green color channel and at the same time increase the blue channel. At the middle of the transformation the color 808080 will be reached.

##### type #####
Value: String (LinearColorChange)
the type attribute contains the name of the current rule

##### startColor #####
Value: String - hexadecimal color code
Contains the start color of the fade. the values are given in hexadecimal values in the format RRGGBBAA where RR=red, GG=green, BB=blue and AA=alpha. The alpha channel is optional, the default value is FF(255). So colors like FFAA00 are valid.

##### startColor #####
Value: String - hexadecimal color code
Contains the end color.

##### duration #####
Value: int (milliseconds)
The duration of this animation in milliseconds.


### HSVColorChange ###
The HSV-color-change rule is a transformation between to colors. The transformation is a walk on the HSV color circle. This means, a transformation from yellow (FFFF00) to blue (0000FF) will be calculated by a degree walk from 60° to 240°. The middle of the transformation is the color with 150°. In the yellow-blue example it would be light-green (#00FF80).
Example: A transformation from FFFF00 to 0000FF will decrease the red- and the green color channel and at the same time increase the blue channel. At the middle of the transformation the color 808080 will be reached.

##### type #####
Value: String (LinearColorChange)
the type attribute contains the name of the current rule

##### startColor #####
Value: String - hexadecimal color code
Contains the start color of the fade. the values are given in hexadecimal values in the format RRGGBBAA where RR=red, GG=green, BB=blue and AA=alpha. The alpha channel is optional, the default value is FF(255). So colors like FFAA00 are valid.

##### startColor #####
Value: String - hexadecimal color code
Contains the end color.

##### duration #####
Value: int (milliseconds)
The duration of this animation in milliseconds.
