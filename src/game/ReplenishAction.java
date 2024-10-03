package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Class representing the Action to replenish health and stamina.
 */
public class ReplenishAction extends Action {
    private Item item;        // The item used for replenishment
    private float health;       // The amount of health to replenish
    private float stamina;      // The amount of stamina to replenish

    /**
     * Constructor for creating a ReplenishAction.
     *
     * @param item     The item to be used for replenishment.
     * @param health   The amount of health to replenish.
     * @param stamina  The amount of stamina to replenish.
     */
    public ReplenishAction(Item item, float health, float stamina) {
        this.item = item;
        this.health = health;
        this.stamina = stamina;
    }

    /**
     * Executes the replenishment action by healing the actor's health, increasing their stamina, and removing the item from the inventory.
     *
     * @param actor The actor performing the action.
     * @param map   The game map on which the action is executed.
     * @return A message describing the action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.heal(Math.round(actor.getAttributeMaximum(BaseActorAttributes.HEALTH) * health));  // Heal the actor's health
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, Math.round(actor.getAttributeMaximum(BaseActorAttributes.STAMINA) * stamina));  // Increase actor's stamina
        actor.removeItemFromInventory(item);  // Remove the used item from actor's inventory
        return actor + " uses " + item.toString() + " to replenish health and stamina.";
    }

    /**
     * Provides a description of the replenishment action for use in menus.
     *
     * @param actor The actor performing the action.
     * @return A description of the action for display in menus.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " uses the " + item.toString() + " to replenish " + health * 100 + "% health and " + stamina * 100 + "% stamina.";
    }
}