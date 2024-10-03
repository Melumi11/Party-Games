# Party Games

[![Discord](https://img.shields.io/discord/416652224505184276?color=%235865F2&label=LielAmar's%20Discord)](https://discord.gg/NzgBrqR)
[Melumi's Discord](https://discord.gg/MDt9ZWcb8Z)

## Information
Party Games is a Spigot plugin based off of Hypixel's original Party Games game.
<br>This plugin includes all 26 minigames, but they don't all fully work.

* This plugin was developed from scratch and is not related to Hypixel. However, using this plugin in your server is not allowed without clear permission from Hypixel.
For more information contact support at https://support.hypixel.net

## Features
* 26 different minigames (including the most famous Trampolinio, Chicken Rings, Workshop and many others!)
* Uses [Liel's PacketManager](https://github.com/LielAmar/PacketManager) & [Liel's Utils](https://github.com/LielAmar/LielsUtils) for Bossbar, Scoreboard and Custom Entity Support.
* Map rollback
* Admin commands

## Using Liel's Party Games
Using this plugin, taking its code or using it for anything other than learning is disallowed.
<br>However, if you want to test the plugin for yourself you can download a copy from [Releases](https://github.com/Melumi11/Party-Games/releases).
<br>* This plugin only works for Paper/Spigot version 1.8.8 (v1_8_R3)

* As been said before, this plugin is based off of Hypixel's Party Games and therefore can be used with Hypixel's permission only!

### Setup
* Place PartyGames-X.X.X.jar into your plugins folder
* Download worlds.zip and unzip it into your server folder ($ wget https://raw.githubusercontent.com/Melumi11/Party-Games/refs/heads/master/worlds.zip)
    * This folder contains the Party Games map
* Add the following at the top of your server run command to make sure that the map isn't changed often
```
rm -rf partygames
mkdir partygames
cp -r ./worlds/partygames-original/* ./partygames
```

### Commands
* Run /partygames (/pg) to view all commands
* /pg add <playername> (/pg addplayer, forceadd): Add player to game
    * Force commands are the same as the regular commands (Melumi doesn't know why)
* /pg start (/pg forcestart): Start game
* /pg endm (/pg endcurrentminigame): End current minigame
* /pg end (/pg endgame): End the game and restart the server (Melumi doesn't understand the restart)
* /pg remove <playername> (/pg removeplayer, kick): Remove player from game
* /pg next <gamename> (/pg setnextminigame): Set next minigame
    * gamename options: animal_slaughter, anvil_spleef, avalanche, bombardment, cannon_painters, chicken_rings, dive, fire_leapers, frozen_floor, high_ground, hoe_hoe_hoe, jigsaw_rush, jungle_jump, lab_escape, lawn_moower, minecart_racing, pig_fishing, pig_jousting, rpg_16, shooting_range, spider_maze, super_sheep, the_floor_is_lava, trampolinio, volcano, workshop

## Building
Install mvn and download the Party Games source code.

Install dependencies by opening a command prompt inside the project directory and running the following commands.

```
$ mvn install:install-file -Dfile=./LielsUtils-1.1.9.jar -DgroupId=com.lielamar -DartifactId=lielsutils -Dversion=1.1.9 -Dpackaging=jar

$ mvn install:install-file -Dfile=./PacketManager-1.0.8.jar -DgroupId=com.lielamar -DartifactId=PacketManager -Dversion=1.0.8 -Dpackaging=jar
```

Build Party Games using the following command.

```
$ mvn clean install
```

The built files will be found in the newly created target directory.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License
[GPL 3](https://choosealicense.com/licenses/agpl-3.0/)
