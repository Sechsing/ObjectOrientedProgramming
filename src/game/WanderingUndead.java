package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Class representing the Wandering Undead.
 */
public class WanderingUndead extends Actor {
    private Map<Integer, Behaviour> behaviours = new HashMap<>();
    private int chanceToDropKey; // The chance of dropping a key upon defeat
    private int chanceToDropVial; // The chance of dropping a vial upon defeat

    /**
     * Constructor for creating a Wandering Undead.
     */
    public WanderingUndead() {
        super("Wandering Undead", 't', 100); // Initialize with name, display character, and health points
        this.chanceToDropKey = 25;  // Set the default chance to drop a key to 25%
        this.chanceToDropVial = 20; //Set the default chance to drop a vial to 20%
        this.behaviours.put(999, new WanderBehaviour()); // Add wandering behavior
    }

    /**
     * This method is called at each turn to determine the action the Wandering Undead should perform.
     *
     * @param actions    A collection of possible Actions for this Actor.
     * @param lastAction The Action this Actor took last turn.
     * @param map        The game map containing the Actor.
     * @param display    The I/O object to which messages may be written.
     * @return The valid action that the Wandering Undead can perform in this iteration or null if no valid action is found.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction(); // Return a DoNothingAction if no other actions are available
    }

    /**
     * This method defines the allowable actions for the Wandering Undead when attacked by another actor.
     *
     * @param otherActor The Actor that might be performing the attack.
     * @param direction  A String representing the direction of the other Actor.
     * @param map        The current GameMap.
     * @return An ActionList containing allowable actions for the WanderingUndead in response to the attack.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction)); // Allow the WanderingUndead to perform an attack if the attacker is hostile
        }
        return actions;
    }

    /**
     * This method is called when the Wandering Undead becomes unconscious or defeated.
     *
     * @param actor The Actor responsible for the Wandering Undead's defeat.
     * @param map   The current GameMap.
     * @return A message describing the outcome of the defeat, including whether a key was dropped.
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        Random random = new Random();
        if (random.nextInt(100) < chanceToDropKey) {
            map.at(map.locationOf(this).x(), map.locationOf(this).y()).addItem(new OldKey(Ability.UNLOCKGATE)); // Drop a key with a chance
        }
        if (random.nextInt(100) < chanceToDropVial) {
            map.at(map.locationOf(this).x(), map.locationOf(this).y()).addItem(new HealingVial()); // Drop a key with a chance
        }
        map.removeActor(this); // Remove the Wandering Undead from the map
        return this + " met their demise in the hand of " + actor + " and may have dropped key and vial.";
    }
}