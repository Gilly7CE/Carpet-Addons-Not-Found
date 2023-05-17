# Gilly7CE-Carpet-Addons

![Curseforge downloads](https://cf.way2muchnoise.eu/full_862771_downloads.svg) ![GitHub all releases](https://img.shields.io/github/downloads/Gilly7CE/Gilly7CE-Carpet-Addons/total?style=social) ![Curseforge available for](https://cf.way2muchnoise.eu/versions/862771.svg) ![GitHub](https://img.shields.io/github/license/Gilly7CE/Gilly7CE-Carpet-Addons) ![GitHub release (latest by date)](https://img.shields.io/github/v/release/Gilly7CE/gilly7ce-carpet-addons?label=latest%20release) ![GitHub Workflow Status](https://img.shields.io/github/actions/workflow/status/Gilly7CE/Gilly7CE-Carpet-Addons/mainbuild.yml?label=production%20build) ![GitHub Workflow Status](https://img.shields.io/github/actions/workflow/status/Gilly7CE/Gilly7CE-Carpet-Addons/prbuild.yml?label=dev%20build) ![GitHub Workflow Status](https://img.shields.io/github/actions/workflow/status/Gilly7CE/Gilly7CE-Carpet-Addons/publish.yml?label=publish%20build)

This mod extends the [carpet mod](https://github.com/gnembon/fabric-carpet) and adds useful features to the game which I
personally would like to be in vanilla.

## Installation

Download the `.jar` which corresponds to the minecraft version you are using and place it inside the 'mods' directory
within your minecraft installation folder.

You also need to download the [carpet mod](https://github.com/gnembon/fabric-carpet)
and [fabric loader](https://fabricmc.net/).

## Features

### creativePlayerOneHitKill

Allows players in Creative mode to kill entities in one hit.

- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `FEATURE`, `CREATIVE`, `GILLY7CE-CARPET-ADDON`
- Additional notes:
  - This only works on non-player entities.

### disablePhantomSpawningForCreativePlayers

Phantoms will no longer spawn for creative players.

- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `CREATIVE`, `FEATURE`, `GILLY7CE-CARPET-ADDON`

### disablePhantomSpawningInMushroomFields

Phantoms will no longer spawn in a mushroom fields biome.

- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `FEATURE`, `GILLY7CE-CARPET-ADDON`

### dispensersPlaceEyesOfEnder

Dispensers can place eyes of ender into end portal frames.

- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `FEATURE`, `DISPENSER`, `GILLY7CE-CARPET-ADDON`

### dispensersRemoveEyesOfEnder

Dispensers can remove eyes of ender from full end portal frames. Any connecting end portals will break.

- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `FEATURE`, `EXPERIMENTAL`, `DISPENSER`, `GILLY7CE-CARPET-ADDON`

### dropEyesOfEnderFromEndPortalFrame

A full end portal frame will drop an eye of ender when right-clicked by a player, turning into an empty end portal frame
in the process. Any connecting end portals will break.

- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `FEATURE`, `EXPERIMENTAL`, `GILLY7CE-CARPET-ADDON`

### movableEmptyEndPortalFrames

Allows empty end portal frames to be moved.
The `drop_as_items_on_explosion` option will allow end portal frames to drop as items when an explosion occurs whilst
being pushed by a piston.

- Type: `MovableBlockOptions`
- Default value: `false`
- Required options: `true`, `false`, `drop_as_items_on_explosion`
- Categories: `FEATURE`, `EXPERIMENTAL`, `GILLY7CE-CARPET-ADDON`

### movableSpawners

Allows spawners to be moved.
This requires the carpet `movableBlockEntities` rule to be enabled.

- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `FEATURE`, `EXPERIMENTAL`, `GILLY7CE-CARPET-ADDON`

### netheriteAxeInstantMineWood

A netherite axe with efficiency V combined with the haste II status effect will instant mine wood and nether wood type
blocks.

- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `SURVIVAL`, `GILLY7CE-CARPET-ADDON`

### netheritePickaxeInstantMineBlueIce

A netherite pickaxe with efficiency V combined with the haste II status effect will instant mine blue ice blocks.

- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `SURVIVAL`, `GILLY7CE-CARPET-ADDON`

### netheritePickaxeInstantMineCobblestone

A netherite pickaxe with efficiency V combined with the haste II status effect will instant mine cobblestone type
blocks.

- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `SURVIVAL`, `GILLY7CE-CARPET-ADDON`

### netheritePickaxeInstantMineDeepslate

A netherite pickaxe with efficiency V combined with the haste II status effect will instant mine deepslate type blocks.

- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `SURVIVAL`, `GILLY7CE-CARPET-ADDON`

### netheritePickaxeInstantMineEndStone

A netherite pickaxe with efficiency V combined with the haste II status effect will instant mine end stone type blocks.

- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `SURVIVAL`, `GILLY7CE-CARPET-ADDON`

### netheritePickaxeInstantMineNetherBricks

A netherite pickaxe with efficiency V combined with the haste II status effect will instant mine nether brick type
blocks.

- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `SURVIVAL`, `GILLY7CE-CARPET-ADDON`

### phantomsObeyHostileMobCap

Phantoms will no longer spawn if the hostile mobcap is full. This is per player.

- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `FEATURE`, `GILLY7CE-CARPET-ADDON`

### spectatorPlayersUsePortals

Spectator players can go through nether portals, end portals and end gateways.

- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `FEATURE`, `EXPERIMENTAL`, `GILLY7CE-CARPET-ADDON`

### xpBubbleColumnInteraction

Bubble columns will push or pull XP orb entities like with other entities and items.

- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `FEATURE`, `GILLY7CE-CARPET-ADDON`

## Contributing

Anyone can contribute to this repository. However, please follow the following guidelines when contributing.

### Raising issues

If you have a question, feature request or bug report please raise it in the issues tab and link it with the
corresponding label.

### Code setup

Follow these [guidelines](https://fabricmc.net/wiki/tutorial:setup) to setup a dev environment to contribute code to
this repository. [Intellij IDEA](https://www.jetbrains.com/idea/download/#section=windows) is the preferred IDE to use,
and the one which I use (Gilly7CE). You do not need to clone the fabric example mod, instead clone this repo. Once
setup, you can build the mod. If building using fabric isn't working, try running `./gradlew idea` in the terminal
whilst in the repo directory. I'd recommend running this each time before debugging the code anyway. Since this mod is
dependent on carpet, it will automatically build the carpet mod `.jar` needed and place it within the mods/ folder of
the running minecraft instance along with this mod.

### Workflow for PRs

To contribute a code change to the repository, please do the following:

- Create a branch with a name that matches the following format: \<type of issue>-\<author name>-\<issue number>
  -\<description>
  - The issue can be omitted until one is created, however the branch should be renamed to include the issue. You can
    do this before creating a PR. You can also create the issue in advance and assign yourself to indicate you're
    working on it.
  - The branch base should be targeting `main`, unless the issue is specific to a particular minecraft version of the
    mod.
- When writing commits, please follow
  these [guidelines](https://initialcommit.com/blog/git-commit-messages-best-practices).
- Once code changes have been made to the branch, push it and create a PR.
- Additional checks before creating a PR:
  - Right-click the project in IntelliJ IDEA and select "Reformat Code". Under "Options" select "Cleanup code" and
    "Optimize imports". Click "Run". The code will now be formatted to the project's rules.
  - Run the tests under the "Test" directory. Ensure they all pass.
  - Run "Minecraft Client" in release and/or debug mode. If Minecraft loads then you know the configuration is okay.
  - Ensure any Mixin classes are declared `abstract`.

If you have any issues doing any of these, please contact the following:

- Gilly7CE
  - Discord: Gilly7CE#7462
  - Email: gilly7ce@gmail.com
