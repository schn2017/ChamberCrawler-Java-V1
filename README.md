# ChamberCrawler-Java-V1

This project was inspired by the adventure mode in Dwarf Fortress and other dungeon crawler roguelikes. I have always wanted to create my own ASCII roguelike dungeon crawler. I finally decided to try making one myself after I came across a list of programming projects. I decided to work on this project alongside a java course I am taking.

![Opening Screen](https://i.imgur.com/15ruLsC.png)

# Valid Player Commands
* no, so, ea, we, ne, nw, se, sw: moves the player character one block in the appropriate cardinal direction
* u <direction>: uses the potion indicated by the direction (e.g. no, so, ea
* u <direction> store: stores the potion indicated by the direction (e.g. no, so, ea) in the player's inventory
* u potion: uses a potion stored in the player's inventory
* a <direction>: attacks the enemy in the specified direction, if the monster is in the immediately specified block e.g. must be one block north of the @ 
* help: displays all possible commands
* map: displays all discovered tiles in the dungeon
* reset: restarts the game 
* quit: exits the game

# Map
There are ten floors in the dungeon. Each floor has a randomly spawned staircase that allows the player to descend to the next floor of the dungeon. Each floor randomly spawns, monsters, potions, and gold. Note: all floor levels share the same floor layout. Field of the view of the player is limited, so the player will need to explore on their own.

Example of Game Map below. <br>
![Game Map](https://i.imgur.com/PGTNKYx.png)

Example of field of view below.
![FOV](https://i.imgur.com/mmCUvKz.png)

Example of player using map below. The player's map only displays the tiles but not the location of gold, monsters or potions.
![MAP](https://i.imgur.com/GiV2BAt.png)

# Symbols/Character Representations
* @ - Player
* G - Gold
* P - Potion
* D - Dragon
* M - Merchant
* N - Goblin
* T - Troll
* V - Vampire
* W - Werewolf
* X - Phoenix
* / - Stairs

# Future Improvements
I plan to due some major refactoring to take advantage of inheritance. For example player class and monster class could both be subclasses of class called "Entity". This Entity class would contain all similar methods (e.g. findValidDirections and takeDamager) and member variables (e.g. monsterPositionX and playerPositionX). The only class that inherits is the dragon class which inherits from the monster class. If other monsters had unique abilities I would give them their own classes as well.

The lack of inheritance was due to not learning about inheritance until I accidentally stumbled accross it while browsing online. As of 3/01/2020, the course has not gotten to inheritance yet in the lessons.
