package game;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * Class representing the Broadsword.
 */
public class Broadsword extends WeaponItem {
    private Skill active;
    private Boolean activeProcess;
    private Ability passive;
    private int turn;

    /**
     * Constructor for creating a Broadsword object.
     */
    public Broadsword() {
        // Call the constructor of the WeaponItem class with the weapon name, display character, damage, and hit chance
        super("Broadsword", '1', 110, "slashes", 80);
        // Initialize the Broadsword's active skill, active process status, turn count, and passive ability
        this.active = new Focus(5, 0.1F, this); // Active skill with a duration and hit rate modifier
        this.activeProcess = false; // Initially, the active process is not active
        this.turn = 0; // Initialize the turn count
        this.passive = Ability.FOCUS; // Initialize the passive ability associated with the Broadsword
    }

    /**
     * Start the active skill process.
     *
     * This method sets the active process status to true, indicating that the active skill is in use.
     */
    public void startActiveProcess() {
        activeProcess = true;
    }

    /**
     * End the active skill process.
     *
     * This method sets the active process status to false, indicating that the active skill is no longer in use.
     */
    public void endActiveProcess() {
        activeProcess = false;
    }

    /**
     * Reset the turn count for tracking the duration of the active skill.
     */
    public void resetTurn() {
        turn = 0;
    }

    /**
     * Perform actions on each tick (turn) of the game.
     *
     * This method is called during each game turn. It handles updating the Broadsword's active
     * skill, passive ability, and turn count.
     *
     * @param currentLocation The location where the Broadsword is located
     * @param actor           The actor wielding the Broadsword
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        // Add the passive ability to the actor if they don't already have it.
        if (!actor.hasCapability(passive)) {
            addCapability(passive);
        }
        // If the active skill process is active and the actor has the passive ability, update the hit rate.
        if (activeProcess && actor.hasCapability(passive)) {
            updateHitRate(90); // Modify the hit rate (e.g., increase it) as needed
        }
        // Increment the turn count if the active skill process is active.
        if (activeProcess) {
            turn += 1;
        }
        // Deactivate the active skill if the turn count exceeds its duration.
        if (turn > active.duration) {
            active.deactivateSkill();
            turn = 0; // Reset the turn count
        }
    }

    /**
     * Get a list of allowable actions for the actor wielding the Broadsword.
     *
     * @param actor The actor wielding the Broadsword
     * @return An ActionList containing allowable actions for the actor
     */
    @Override
    public ActionList allowableActions(Actor actor) {
        ActionList actionList = new ActionList();
        actionList.add(new PerformSkillAction(active)); // Add an action to perform the active skill
        return actionList;
    }

    /**
     * Get a list of allowable actions that the item allows its owner do to other actor.
     *
     * @param actor    The actor wielding the Broadsword
     * @param location The location where the actor intends to perform an action
     * @return An ActionList containing allowable actions for the actor at the specified location
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location) {
        ActionList actionList = new ActionList();
        actionList.add(new AttackAction(actor, location.toString(), this)); // Add an action to perform an attack
        return actionList;
    }
}
