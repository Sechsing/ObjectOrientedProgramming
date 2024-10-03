package game;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;

/**
 * Class representing the Healing Vial.
 */
public class HealingVial extends Item {
    /***
     * Constructor.
     */
    public HealingVial() {
        super("Healing Vial", 'a', true);
    }

    /**
     * Get a list of allowable actions for the actor holding the healing vial.
     *
     * @param actor The actor holding the healing vial
     * @return An ActionList containing allowable actions for the actor
     */
    @Override
    public ActionList allowableActions(Actor actor) {
        ActionList actionList = new ActionList();
        actionList.add(new ReplenishAction(this,0.1F, 0)); // Add an action to perform the active skill
        return actionList;
    }
}
