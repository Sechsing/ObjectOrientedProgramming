package game;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

import java.util.Random;

/**
 * Class representing the Graveyard in the Abandoned Village.
 */
public class AbandonedVillageGraveyard extends Graveyard {
    /**
     * Constructor for creating a Graveyard ground object that spawns a wandering undead actor.
     */
    public AbandonedVillageGraveyard(){
        super();
        this.chance = 25;
    }

    /**
     * Spawn an entity on the Graveyard ground.
     *
     * If the random percentage is less than the chance, a Wandering Undead actor is added to the location's map.
     *
     * @param location The location of the Graveyard ground
     */
    @Override
    public void spawn(Location location) {
        Random random = new Random();
        GameMap map = location.map();
        if (random.nextInt(100) < chance && !location.containsAnActor()) {
            map.at(location.x(), location.y()).addActor(new WanderingUndead());
        }
    }
}
