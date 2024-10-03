package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

/**
 * Class representing the Player.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class Player extends Actor {
    /**
     * Constructor.
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     * @param stamina   Player's starting number of stamina
     */
    public Player(String name, char displayChar, int hitPoints, int stamina) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        // Initialize the player's stamina attribute with a specified value
        this.addAttribute(BaseActorAttributes.STAMINA, new BaseActorAttribute(200));
    }

    /**
     * Returns a string representation of the Player's current status.
     *
     * @return A string containing the player's name, current health, maximum health, current stamina, and maximum stamina.
     */
    @Override
    public String toString() {
        return name + " (" +
                this.getAttribute(BaseActorAttributes.HEALTH) + "/" +
                this.getAttributeMaximum(BaseActorAttributes.HEALTH) +
                ")" + " (" +
                this.getAttribute(BaseActorAttributes.STAMINA) + "/" +
                this.getAttributeMaximum(BaseActorAttributes.STAMINA) +
                ")";
    }

    /**
     * Defines the player's actions and interactions on their turn.
     *
     * This method is called during the player's turn and handles actions and interactions
     * initiated by the player. It increases the player's stamina and displays a menu for
     * the player to choose actions from.
     *
     * @param actions    List of available actions for the player
     * @param lastAction The last action performed by the player
     * @param map        The current GameMap
     * @param display    The display to show game information
     * @return An Action representing the player's chosen action for the turn
     */

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // Increase the player's stamina by 1% of their maximum stamina each turn.
        this.modifyAttribute(
                BaseActorAttributes.STAMINA,
                ActorAttributeOperations.INCREASE,
                Math.round(this.getAttributeMaximum(BaseActorAttributes.STAMINA)*0.01F));
        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();
        // return/print the console menu
        Menu menu = new Menu(actions);
        return menu.showMenu(this, display);
    }

    /**
     * Defines the player's intrinsic weapon.
     *
     * This method specifies the player's intrinsic weapon, including its damage and hit chance.
     *
     * @return An IntrinsicWeapon object representing the player's intrinsic weapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(15, "punches", 80);
    }
}
