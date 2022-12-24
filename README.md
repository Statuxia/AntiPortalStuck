# AntiPortalStuck
A simple plugin to avoid situations of getting stuck in portals

This plugin allows players to get out of the portal when they log into the game.

This is useful when using plugins such as AuthMe, which do not allow the player to move until he is logged in.


This plugin works in two stages:

1) Search for the block closest to the portal to which a player can be teleported within a certain radius (from 1 to 5 inclusive).

2) teleporting a player to the highest block by player position (disabled by default)

 

The following commands are present in the plugin:

> /antiportalstuck (or aps) force (true/false) - enables an emergency teleport of the player to the upper block of the portal

> /antiportalstuck range (1-5) - changes the radius by which a suitable block is being searched

> /antiportalstuck reload - if you change the settings manually in the configuration file
