<img src="./src/main/resources/assets/carpet-addons-not-found/icon.png" align="right" width="128px"/>

# Carpet-Addons-Not-Found

![Curseforge downloads](https://cf.way2muchnoise.eu/full_862771_downloads.svg)
![Modrinth Downloads](https://img.shields.io/modrinth/dt/iIPoKpIW?style=social&logo=modrinth)
![GitHub all releases](https://img.shields.io/github/downloads/Gilly7CE/Carpet-Addons-Not-Found/total?style=social)

![GitHub](https://img.shields.io/github/license/Gilly7CE/Carpet-Addons-Not-Found)

![Curseforge available for](https://cf.way2muchnoise.eu/versions/862771.svg)
![GitHub release (latest by date)](https://img.shields.io/github/v/release/Gilly7CE/Carpet-Addons-Not-Found?label=latest%20release)

![GitHub Workflow Status](https://img.shields.io/github/actions/workflow/status/Gilly7CE/Carpet-Addons-Not-Found/mainbuild.yml?label=production%20build)
![GitHub Workflow Status](https://img.shields.io/github/actions/workflow/status/Gilly7CE/Carpet-Addons-Not-Found/prbuild.yml?label=dev%20build)
![GitHub Workflow Status](https://img.shields.io/github/actions/workflow/status/Gilly7CE/Carpet-Addons-Not-Found/publish.yml?label=publish%20build)

This mod extends the [carpet mod](https://github.com/gnembon/fabric-carpet) and adds useful features to the game which
would be cool to have in the Vanilla game.

This is the work of the [404 Not Found Technical Server](https://discord.gg/eBJbuNcGkH).

The mod was formerly called "Gilly7CE Carpet Addons", which releases 1.1.0 and prior were called. Versions after this
will be "Carpet Addons Not Found".

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
- Categories: `FEATURE`, `CREATIVE`, `CARPET-ADDONS-NOT-FOUND`
- Additional notes:
    - This only works on non-player entities.

### disableMobSpawningInEnd

Disables mobs from spawning in the End.

- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `CREATIVE`, `CARPET-ADDONS-NOT-FOUND`

### disableMobSpawningInNether

Disables mobs from spawning in the Nether.

- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `CREATIVE`, `CARPET-ADDONS-NOT-FOUND`

### disableMobSpawningInOverworld

Disables mobs from spawning in the Overworld.

- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `CREATIVE`, `CARPET-ADDONS-NOT-FOUND`

### disablePhantomSpawningForCreativePlayers

Phantoms will no longer spawn for creative players.

- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `CREATIVE`, `FEATURE`, `CARPET-ADDONS-NOT-FOUND`

### disablePhantomSpawningInMushroomFields

Phantoms will no longer spawn in a mushroom fields biome.

- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `FEATURE`, `CARPET-ADDONS-NOT-FOUND`

### dispensersPlaceEyesOfEnder

Dispensers can place eyes of ender into end portal frames.

- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `FEATURE`, `DISPENSER`, `CARPET-ADDONS-NOT-FOUND`

### dispensersRemoveEyesOfEnder

Dispensers can remove eyes of ender from full end portal frames. Any connecting end portals will break.

- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `FEATURE`, `EXPERIMENTAL`, `DISPENSER`, `CARPET-ADDONS-NOT-FOUND`

### dropEyesOfEnderFromEndPortalFrame

A full end portal frame will drop an eye of ender when right-clicked by a player, turning into an empty end portal frame
in the process. Any connecting end portals will break.

- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `FEATURE`, `EXPERIMENTAL`, `CARPET-ADDONS-NOT-FOUND`

### movableEmptyEndPortalFrames

Allows empty end portal frames to be moved.
The `drop_as_items_on_explosion` option will allow end portal frames to drop as items when an explosion occurs whilst
being pushed by a piston.

- Type: `MovableBlockOptions`
- Default value: `false`
- Required options: `true`, `false`, `drop_as_items_on_explosion`
- Categories: `FEATURE`, `EXPERIMENTAL`, `CARPET-ADDONS-NOT-FOUND`

### movableSpawners

Allows spawners to be moved.
This requires the carpet `movableBlockEntities` rule to be enabled.

- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `FEATURE`, `EXPERIMENTAL`, `CARPET-ADDONS-NOT-FOUND`

### netheriteAxeInstantMineWood

A netherite axe with efficiency V combined with the haste II status effect will instant mine wood and nether wood type
blocks.

- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `SURVIVAL`, `CARPET-ADDONS-NOT-FOUND`

### netheritePickaxeInstantMineBlueIce

A netherite pickaxe with efficiency V combined with the haste II status effect will instant mine blue ice blocks.

- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `SURVIVAL`, `CARPET-ADDONS-NOT-FOUND`

### netheritePickaxeInstantMineCobblestone

A netherite pickaxe with efficiency V combined with the haste II status effect will instant mine cobblestone type
blocks.

- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `SURVIVAL`, `CARPET-ADDONS-NOT-FOUND`

### netheritePickaxeInstantMineDeepslate

A netherite pickaxe with efficiency V combined with the haste II status effect will instant mine deepslate type blocks.

- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `SURVIVAL`, `CARPET-ADDONS-NOT-FOUND`

### netheritePickaxeInstantMineEndStone

A netherite pickaxe with efficiency V combined with the haste II status effect will instant mine end stone type blocks.

- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `SURVIVAL`, `CARPET-ADDONS-NOT-FOUND`

### netheritePickaxeInstantMineNetherBricks

A netherite pickaxe with efficiency V combined with the haste II status effect will instant mine nether brick type
blocks.

- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `SURVIVAL`, `CARPET-ADDONS-NOT-FOUND`

### phantomsObeyHostileMobCap

Phantoms will no longer spawn if the hostile mobcap is full. This is per player.

- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `FEATURE`, `CARPET-ADDONS-NOT-FOUND`

### portalCreativeDelay

Amount of delay ticks to use a nether portal in creative

* Type: `Integer`
* Default value: `1`
* Suggested options: `1`, `40`, `80`, `72000`
* Categories: `CREATIVE`, `CARPET-ADDONS-NOT-FOUND`
* Additional notes:
    * You must choose a value from 1 to 72000

### replaceableFlowers

Placing blocks on flowers will replace them like grass.

- Type: `ReplaceableFlowersOptions`
- Default value: `false`
- Required options: `false`, `one_tall_flowers`, `all_flowers`
- Categories: `FEATURE`, `SURVIVAL`, `CARPET-ADDONS-NOT-FOUND`

### spawnEggsSpawnMobsWithNoAI

A spawn egg will spawn a mob with no AI.

- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `FEATURE`, `CREATIVE`, `CARPET-ADDONS-NOT-FOUND`

### spectatorPlayersUsePortals

Spectator players can go through nether portals, end portals and end gateways.

- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `FEATURE`, `EXPERIMENTAL`, `CARPET-ADDONS-NOT-FOUND`

### xpBubbleColumnInteraction

Bubble columns will push or pull XP orb entities like with other entities and items.

- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `FEATURE`, `CARPET-ADDONS-NOT-FOUND`

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

The workflow will depend on whether you're a contributor with write access or not. If you're not a contributor please
follow this [general guidance](https://akrabat.com/the-beginners-guide-to-contributing-to-a-github-project/), however
please still follow the branching and commit guidance below.

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
