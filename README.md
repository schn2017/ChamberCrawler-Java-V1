# ChamberCrawler-Java-V1

This project was inspired by the adventure mode in Dwarf Fortress and other dungeon crawler roguelikes. I have always wanted to create my own ASCII roguelike dungeon crawler. I finally decided to try making one myself after I came across a list of programming projects. I decided to work on this project alongside a java course I am taking.

# Valid Player Commands
* no, so, ea, we, ne, nw, se, sw: moves the player character one block in the appropriate cardinal direction
* u <direction>: uses the potion indicated by the direction (e.g. no, so, ea
* u <direction> store: stores the potion indicated by the direction (e.g. no, so, ea) in the player's inventory
* u potion: uses a potion stored in the player's inventory
* a <direction>: attacks the enemy in the specified direction, if the monster is in the immediately specified block e.g. must be one block north of the @ 
* help: displays all possible commands
* reset: restarts the game 
* quit: exits the game

# Map
![Game Map](https://i.imgur.com/PGTNKYx.png)

# Symbols/Character Representations
* @ - Player
* G - Gold
* P - Potion
* D - (Dragon)
* M - (Merchant)
* N - (Goblin)
* T - (Troll)
* V - (Vampire)
* W - (Werewolf)
* X - (Phoenix)

# Future Improvements
I plan to due some major refactoring to take advantage of inheritance. For example player class and monster class could both be subclasses of class called "Entity". This Entity class would contain all similar methods (e.g. findValidDirections and takeDamager) and member variables (e.g. monsterPositionX and playerPositionX). The only class that inherits is the dragon class which inherits from the monster class. If other monsters had unique abilities I would give them their own classes as well.

The lack of inheritance was due to not learning about inheritance until I accidentally stumbled accross it while browsing online. As of 3/01/2020, the course has not gotten to inheritance yet in the lessons.
