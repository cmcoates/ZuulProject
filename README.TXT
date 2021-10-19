Project: zuul
Authors: Michael Kölling and David J. Barnes

This project is part of the material for the book

   Objects First with Java - A Practical Introduction using BlueJ
   Sixth edition
   David J. Barnes and Michael Kölling
   Pearson Education, 2016

This project is a simple framework for an adventure game. In this version,
it has a few rooms and the ability for a player to walk between these rooms.
That's all.

To start this application, create an instance of class "Game" and call its
"play" method.

From Zuul-bad, we used the idea of coupling and cohesion to interconnect the classes better.
This was important to fix because the more connected the classes, the less code duplication we will have.
Also with coupling, we can now make changes to one class that won't affect another.
We used encapsulation to change public fields to private fields, which led to a better design.
Classes are very specific and only do what they should, which emphasizes the idea
of a responsibility-driven design. We also refactored the program, which restructures a 
existing design to maintain a good design when expanding the program's capabilities. We did
this by moving the field currentRoom from the Game class to the Player class.
Rooms can now contain multiple items using a collection of a hashmap.
The back command was implemented into the game class to go to the previous room.
A player class was implemented to allow a virtual player to walk around the rooms.
The player needed to be a class because it is used as an object in other classes.
Take and drop commands were added to the game so the player could pick up items or drop items. 
Player contains an arraylist to allow for the single player to carry multiple items.
Players can now be increased with a method that increases weight a player can carry
after eating a certain food, but a player can only carry so much. A conditional statement was used 
if a player ate a certain item, their weight they could carry would increase.
Adding locked exits was done by adding a field to the Room class. This field 
was a boolean that specified if a room is available to enter or not. If the player
had the correct item needed to unlock the room, the room would unlock and the 
player could proceed with the game. Helpers were added by introducing a new 
class. With a new class, a Helper could be placed within a Room and offer advice 
to the player if certain items were given to the helper. In order to do this,
I added a hint command, which asks the helper for a hint, and a give command,
which is used to give the helper the currency needed to provide you with 
a hint.

******************************TEST SCRIPT**************************
Map:

Ocean(key)-(west)-----Beach(has sandals)
  |                     |
  |(down)               |
  |                     |
Ocean cave(treasure)    |(north)      
                        |
                        |
                        |
Restaurant---(west)----Outside(player start, has helper)----(east)--------house(has cake, helmet)
 (helper)                |
    |                    |
    |                    |
    |(down)              |(south)
    |                    |
    |                    gym (protein)
dungeon(locked, has sword)

***************Example commands to type in***************
MOVEMENT
go north
go down
back

DESCRIPTIONS BASED ON WHAT YOU HAVE/WHAT'S IN ROOM
items
look

TAKE/DROP/EAT
take sword
eat protein
drop sandals

INTERACTING WITH HELPER
hint
give cake

TO QUIT
quit

GET HELP/QUIT
help
quit



