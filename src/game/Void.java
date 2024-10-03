package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Class representing the Void.
 */
public class Void extends Ground {

    /**
     * Constructor for creating a Void ground object.
     */
    public Void() {
        super('+'); // Initialize the Void ground with the display character '+'
    }

    /**
     * Perform actions on each tick (turn) of the game.
     *
     * This method is called during each game turn and checks if an actor is present on the Void ground.
     * If an actor is present, it may trigger certain effects, such as making the actor unconscious.
     *
     * @param location The location where the Void ground is located
     */
    @Override
    public void tick(Location location) {
        // Check if the location contains an actor
        if (location.containsAnActor()) {
            Actor actor = location.getActor();
            GameMap map = location.map();
            // Make the actor unconscious (or apply other effects) based on game logic
            actor.unconscious(map);
            // Check if the actor stepping on the Void ground is the player character '@'
            if (actor.getDisplayChar() == '@') {
                // Display a message indicating that the player character has died.
                new Display().println(FancyMessage.YOU_DIED);
            }
        }
    }
}