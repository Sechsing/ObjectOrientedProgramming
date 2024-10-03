package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;

/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class Application {

    public static void main(String[] args) {

        // Create a world for the game
        World world = new World(new Display());

        // Create a ground factory to define the terrain elements
        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(),
                new Wall(), new Floor(), new Puddle(), new Void(), new AbandonedVillageGraveyard());

        // Define the map layout for the abandoned village
        List<String> map = Arrays.asList(
                "...........................................................",
                "...#######.................................................",
                "...#__.....................................................",
                "...#..___#.................................................",
                "...###.###................#######..........................",
                "..........................#_____#..........................",
                "........~~................#_____#..........................",
                ".........~~~..............###_###..........................",
                "...~~~~~~~~...............+................................",
                "....~~~~~.................................###..##..........",
                "~~~~~~~...................n...............#___..#..........",
                "~~~~~~....................................#..___#..........",
                "~~~~~~~~~.................................#######..........");

        // Create a game map for the abandoned village using the ground factory
        GameMap abandonedVillage = new GameMap(groundFactory, map);

        // Add the abandoned village game map to the world
        world.addGameMap(abandonedVillage);

        // Create a ground factory to define the terrain elements
        FancyGroundFactory groundFactory2 = new FancyGroundFactory(new Dirt(),
                new Wall(), new Floor(), new Puddle(), new Void(), new BurialGroundGraveyard());

        // Define the map layout for the burial ground
        List<String> map2 = Arrays.asList(
                "...........+++++++........~~~~~~++....~~",
                "...........++++++.........~~~~~~+.....~~",
                "............++++...........~~~~~......++",
                "............+.+......n......~~~.......++",
                "..........++~~~.......................++",
                ".........+++~~~....#######...........+++",
                ".........++++~.....#_____#.........+++++",
                "..........+++......#_____#........++++++",
                "..........+++......###_###.......~~+++++",
                "..........~~.....................~~...++",
                "..........~~~..................++.......",
                "...........~~....~~~~~.........++.......",
                "......~~....++..~~~~~~~~~~~......~......",
                "....+~~~~..++++++++~~~~~~~~~....~~~.....",
                "....+~~~~..++++++++~~~..~~~~~..~~~~~....");

        // Create a game map for the burial ground using the same ground factory
        GameMap burialGround = new GameMap(groundFactory2, map2);

        // Add the burial ground game map to the world
        world.addGameMap(burialGround);

        // Display the game title with a delay for visual effect
        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        // Add a wandering undead actor to the abandoned village
        abandonedVillage.at(23, 10).addActor(new WanderingUndead());

        // Create the player character
        Player player = new Player("The Abstracted One", '@', 150, 200);
        // Add the player to the abandoned village game map
        world.addPlayer(player, abandonedVillage.at(29, 5));

        // Create a broadsword and add it to the abandoned village game map
        Broadsword broadsword = new Broadsword();
        abandonedVillage.at(30, 5).addItem(broadsword);

        // Create an old key with the ability to unlock gates
        OldKey key = new OldKey(Ability.UNLOCKGATE);
        // Add the old key to the abandoned village game map
        abandonedVillage.at(30, 6).addItem(key);

        // Create a gate from the abandoned village to the burial ground
        Gate gateToBurialGround = new Gate(abandonedVillage, burialGround, 20, 1, key);
        // Add the gate to the abandoned village game map
        abandonedVillage.at(28, 6).addItem(gateToBurialGround);

        // Add a hollow soldier actor to the burial ground
        burialGround.at(20, 4).addActor(new HollowSoldier());

        // Create a gate from the burial ground to the abandoned village
        Gate gateToVillage = new Gate(burialGround, abandonedVillage, 29, 5, key);
        // Add the gate to the burial ground game map
        burialGround.at(20, 2).addItem(gateToVillage);

        world.run();
    }
}
