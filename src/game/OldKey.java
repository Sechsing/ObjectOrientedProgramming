package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Class representing the Old Key.
 */
public class OldKey extends Item {
    private Ability ability;

    /**
     * Constructor for creating an OldKey object.
     *
     * @param ability The ability associated with the Old Key
     */
    public OldKey(Ability ability) {
        // Call the constructor of the Item class with the item name, display character, and stackable flag
        super("Old key", '-', true);
        // Initialize the ability associated with the Old Key
        this.ability = ability;
    }

    /**
     * Get the ability associated with the Old Key.
     *
     * @return The ability granted by the Old Key
     */
    public Ability getAbility() {
        return ability;
    }

    /**
     * Perform an action when the Old Key is used or its tick method is called.
     *
     * This method adds the associated ability to an actor when the Old Key is used.
     * It is typically called when an actor interacts with the Old Key item.
     *
     * @param currentLocation The location where the Old Key is located
     * @param actor           The actor interacting with the Old Key
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        // Add the associated ability to the actor when the Old Key is used.
        actor.addCapability(ability);
    }
}