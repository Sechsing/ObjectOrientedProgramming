package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Class representing the Gate.
 */
public class Gate extends Item {
    private GameMap origin;
    private GameMap end;
    private int destinationX;
    private int destinationY;
    private Ability ability;

    /**
     * Constructor for creating a Gate object.
     *
     * @param origin         The GameMap where the gate is located
     * @param end            The destination GameMap to which the gate teleports
     * @param destinationX   The X-coordinate on the destination GameMap
     * @param destinationY   The Y-coordinate on the destination GameMap
     * @param key            The OldKey item required to use the gate
     */
    public Gate(GameMap origin, GameMap end, int destinationX, int destinationY, OldKey key) {
        // Call the constructor of the Item class with the item name, display character, and stackable flag
        super("Gate", '=', false);
        // Initialize the gate's attributes, including origin, destination, ability, and destination coordinates
        this.origin = origin;
        this.end = end;
        this.destinationX = destinationX;
        this.destinationY = destinationY;
        this.ability = key.getAbility();
    }

    /**
     * Teleport an actor to the destination when using the gate.
     *
     * This method is called when an actor interacts with the gate. It checks if the actor has the
     * required capability (ability) associated with the gate's key and moves the actor to the destination
     * GameMap if the conditions are met.
     *
     * @param location The location where the gate is located
     */
    public void teleport(Location location) {
        if (location.containsAnActor()) {
            Actor actor = location.getActor();
            // Check if the actor is the player character @ and has the required ability
            if (actor.getDisplayChar() == '@' && actor.hasCapability(ability)) {
                // Move the actor to the destination GameMap at the specified coordinates
                origin.moveActor(actor, end.at(destinationX, destinationY));
            }
        }
    }

    /**
     * Perform an action when the gate's tick method is called.
     *
     * This method is typically called to handle the gate's behavior, such as teleporting actors
     * when they interact with the gate.
     *
     * @param location The location where the gate is located
     */
    public void tick(Location location) {
        // Call the teleport method to perform the teleportation action
        teleport(location);
    }
}
