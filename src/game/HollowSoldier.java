package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Class representing the Hollow Soldier.
 */
public class HollowSoldier extends Actor {
    private Map<Integer, Behaviour> behaviours = new HashMap<>();
    private int chanceToDropVial; // The chance of dropping a healing vial upon defeat
    private int chanceToDropFlask; // The chance of dropping a refreshing flask upon defeat

    /**
     * Constructor for creating a Hollow Soldier.
     */
    public HollowSoldier() {
        super("Hollow Soldier", '&', 200); // Initialize with name, display character, and health points
        this.chanceToDropVial = 20; // Set the default chance to drop a healing vial to 20%
        this.chanceToDropFlask = 30; // Set the default chance to drop a refreshing flask to 30%
        this.behaviours.put(999, new WanderBehaviour()); // Add wandering behavior
    }

    /**
     * This method is called at each turn to determine the action the Hollow Soldier should perform.
     *
     * @param actions    A collection of possible Actions for this Actor.
     * @param lastAction The Action this Actor took last turn.
     * @param map        The game map containing the Actor.
     * @param display    The I/O object to which messages may be written.
     * @return The valid action that the Hollow Soldier can perform in this iteration or null if no valid action is found.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction(); // Return a DoNothingAction if no other actions are available.
    }

    /**
     * This method defines the allowable actions for the Hollow Soldier when attacked by another actor.
     *
     * @param otherActor The Actor that might be performing the attack.
     * @param direction  A String representing the direction of the other Actor.
     * @param map        The current GameMap.
     * @return An ActionList containing allowable actions for the Hollow Soldier in response to the attack.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction)); // Allow the HollowSoldier to perform an attack if the attacker is hostile.
        }
        return actions;
    }

    /**
     * This method is called when the Hollow Soldier becomes unconscious or defeated.
     *
     * @param actor The Actor responsible for the Hollow Soldier's defeat.
     * @param map   The current GameMap.
     * @return A message describing the outcome of the defeat.
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        Random rand = new Random();
        if (rand.nextInt(100) < chanceToDropVial) {
            map.at(map.locationOf(this).x(), map.locationOf(this).y()).addItem(new HealingVial()); // Drop a healing vial with a chance.
        }
        if (rand.nextInt(100) < chanceToDropFlask) {
            map.at(map.locationOf(this).x(), map.locationOf(this).y()).addItem(new RefreshingFlask()); // Drop a refreshing flask with a chance.
        }
        map.removeActor(this); // Remove the HollowSoldier from the map.
        return this + " met their demise in the hand of " + actor + " and may have dropped healing vial and refreshing flask.";
    }

    /**
     * Defines the Hollow Soldier's intrinsic weapon.
     *
     * This method specifies the Hollow Soldier's intrinsic weapon, including its damage and hit chance.
     *
     * @return An IntrinsicWeapon object representing the Hollow Soldier's intrinsic weapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(50, "punches", 50);
    }
}