package game;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;

/**
 * Class representing the Refreshing Flask.
 */
public class RefreshingFlask extends Item {
    /***
     * Constructor.
     */
    public RefreshingFlask() {
        super("Refreshing Flask", 'u', true);
    }

    /**
     * Get a list of allowable actions for the actor holding the refreshing flask.
     *
     * @param actor The actor holding the refreshing flask
     * @return An ActionList containing allowable actions for the actor
     */
    @Override
    public ActionList allowableActions(Actor actor) {
        ActionList actionList = new ActionList();
        actionList.add(new ReplenishAction(this,0, 0.2F)); // Add an action to perform the active skill
        return actionList;
    }
}
