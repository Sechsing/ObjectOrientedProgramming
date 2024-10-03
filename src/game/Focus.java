package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;

/**
 * Class representing the Focus Skill.
 */
public class Focus extends Skill {
    private Broadsword broadsword;

    /**
     * Constructor for creating a Focus skill.
     *
     * @param duration              The duration (number of turns) for which the skill remains active
     * @param damageMultiplierBonus The bonus to the weapon's damage multiplier
     * @param broadsword            The Broadsword associated with this skill
     */
    public Focus(int duration, float damageMultiplierBonus, Broadsword broadsword) {
        super(duration, damageMultiplierBonus);
        // Initialize the Broadsword associated with this skill
        this.broadsword = broadsword;
    }

    /**
     * Activate the Focus skill for an actor.
     *
     * This method is called when an actor activates the Focus skill. It starts the active process
     * for the associated Broadsword, resets the turn count, increases the weapon's damage multiplier,
     * and consumes stamina from the actor.
     *
     * @param actor The actor who activates the Focus skill
     */
    public void activateSkill(Actor actor) {
        // Start the active process for the associated Broadsword
        broadsword.startActiveProcess();
        // Reset the turn count for tracking the skill's duration
        broadsword.resetTurn();
        // Increase the Broadsword's damage multiplier based on the provided bonus
        broadsword.increaseDamageMultiplier(damageMultiplierBonus);
        // Modify the actor's stamina attribute to consume stamina
        BaseActorAttributes stamina = BaseActorAttributes.STAMINA;
        actor.modifyAttribute(stamina, ActorAttributeOperations.DECREASE, Math.round(actor.getAttributeMaximum(stamina) * 0.2F));
    }

    /**
     * Deactivate the Focus skill.
     *
     * This method deactivates the Focus skill, resetting the Broadsword's damage multiplier and ending
     * the active process.
     */
    public void deactivateSkill() {
        // Reset the Broadsword's damage multiplier to its original value
        broadsword.updateDamageMultiplier(0.0f);
        // End the active process for the associated Broadsword
        broadsword.endActiveProcess();
    }

    /**
     * Get a string representation of the Focus skill.
     *
     * @return A string describing the Focus skill and its effects.
     */
    public String toString() {
        return "Focus to increase the weapon’s damage multiplier by 10% for 5 turns";
    }
}