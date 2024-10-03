package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Class representing the Graveyard.
 */
public abstract class Graveyard extends Ground{
    protected int chance; //chance for an actor to spawn

    /**
     * Constructor for creating a Graveyard ground object.
     */
    public Graveyard(){
        super('n');
    }

    /**
     * Spawn an entity on the Graveyard ground.
     *
     * If the random percentage is less than the chance, a Wandering Undead actor is added to the location's map.
     *
     * @param location The location of the Graveyard ground
     */
    public void spawn(Location location){}

    /**
     * Perform actions on each turn of the game.
     *
     * This method is called during each game turn and invokes the spawn method to potentially spawn an actor on the Graveyard ground.
     *
     * @param location The location where the Graveyard ground is located
     */
    @Override
    public void tick(Location location){
        spawn(location);
    }

    /**
     * Reject any attempts from any actors to enter the Graveyard ground.
     *
     * This method is called when an actor tries to neter the Graveyard.
     *
     * @param actor The actor that is trying to enter the Graveyard
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }
}