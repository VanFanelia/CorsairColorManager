## Corsair Color Manager ##

### Description ###

####What is this program about?####
This program includes a driver for the corsair k70rgb keyboard and a toolbox to write your own programs for the background lights.

The base of the program is the linux driver of CalcProgrammer1 (https://github.com/CalcProgrammer1).
His work is used to controll the LED Lights by an java program which interprets costum programs written in a json-syntax.
So its now easier for non developer to write color effects without deep knowledge of programming.

#### What does i need to execute?####
Dependencies:
- You have to install a java jre (see google how to get JAVA JRE if you haven't install it yet)
- start the java program by executing the "CorsairColorManager.jar" or by console with: "java -jar CorsairColorManager.jar"
- sometimes the keyboard has issues with access to the device. try ls-usb and set CHMOD on the right /dev/<devicebus> to fix it temporally

####Know issues / todos:####
- only tested with linux. windows still have issues.
- sometimes problems with device access

####Greetings####
Special thanks to:
- CalcProgrammer1 (https://github.com/CalcProgrammer1) for his excellent work with the linux driver
- The corsair forum community for inspiration and discussions


### write your own program ###
To write a new program in json you just need some meta information and some color-change rules. 
A program for the RGB-keyboard is a json object including a list of rules. Eg. this simple program sets the color of the keys: "W","A","S" and "D":<br/></p>
```json
{
    "colorMixingRule": "OVERRIDE",
    "startAction" : "setRed",
    "rules": [
        {
            "type": "SetColor",
            "delay": "1000",
            "color": "ff0000ff",
            "alias": "setRed",
            "keys": ["W","A","S","D"],
            "doAfter" :  []
        }
    ]
}
```

A program can have the following parameters:

|Key | Values |
|----|--------|
|startAction <name> | The name of the first rule to execute after program starts (program entry point) |
|groups <Array of json objects> | You can define a group as a set of keys. You can later use the groups in your rules to handle your keys |
|rules <Array of json objects> | Contains a list of available rules (json objects) |
|colorMixingRule (default OVERRIDE) | Describe howto handle if 2 colors are set for one key. Currently only OVERRIDE is implemented. |


#### Colors ####
Every rule has one or more colors specified. A color is definied by an 6 or 8 Character long string representing the color in a RGB-scheme. Eg. the color orange can be defined by:

| red | green | blue | alpha |
|-----|-------|------|-------| 
| FF  |  AA   |  00  | FF    | 

The alpha value is optional. Default is FF (=255 = 100% visible).
If you have 2 colors on different layers the alpha value can used to create half transparent layers.
      

### Rules ###
Every rule can have the following parameters:

| Key | Values |
|-----|--------|
|alias	<name> | The name of the rule (for references) |
|keys	[] Strings | A list of keys or groups who are effected by this rule |
|doAfter [] Strings | List of rule names executed after this current rule has finished |
|delay	int | milliseconds until the doAfter-rules are executed |
|layer	int (1-5) | defines the layer of this rule (1-5). Each key can have up to 5 layers (1-5). Every layer can have a color. Layer 1 is in the background, Layer 5 in the foreground |

#### SetColor ####

This rule simply set a color of some keys;

| Key | Values |
|-----|--------|
| color	<Color> | The color to set in hexadezimal RedGreenBlueAlpha |


#### KeyLine ####

This rule create a line. Every effected key will be displayed for a short time and all given keys will be handled sequenced.

| Key | Values |
|-----|--------|
|lineColor	<Color> |The color of the current key |
|backgroundColor	<Color> |The to set when the key disappear |
|keyShowDuration	int (milliseconds) |The time in milliseconds a single key will be shown |
|keyInterval	int (milliseconds) |time (ms) until the next key in line will be shown |

#### LinearColorChange ####

Animate a color transfer between to colors (linear change by rgb values)

| Key | Values |
|-----|--------|
|startColor	<Color> |The start color |
|endColor	<Color> |The color after the animated fade |
|duration	int (milliseconds) |the duration of the color transformation |


#### HSVColorChange ####

Animate a color transfer between to colors based on the HSV Color wheel model. The color transfer animated by degree change.

| Key | Values |
|-----|--------|
|startColor	<Color> |The start color |
|endColor	<Color> |The color after the animated fade |
|duration	int (milliseconds) |the duration of the color transformation |

#### OnKeyPress ####

This rule react on a key input. It executes the doAfterRule if one of the keys defined in the key list is pressed.
You can use "$PRESSED" as a variable in the doAfterKeyRules. This variable will be filled with pressed key. (currently only one the rules after the OnKeyPress-rule will replace the variable).

| Key | Values |
|-----|--------|
|cancelOld <boolean> | if true, the old thread will be stopped if it has not finished yet | 
