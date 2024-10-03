package game;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Class representing Skill.
 */
public abstract class Skill {
    protected int duration;               // RDuration of the skill
    protected float damageMultiplierBonus; // Bonus to the damage multiplier
    protected int turn;                   // Current turn count for tracking skill duration

    /**
     * Constructor for creating a Skill object.
     *
     * @param duration              The duration (number of turns) for which the skill remains active
     * @param damageMultiplierBonus The bonus to the damage multiplier provided by the skill
     */
    public Skill(int duration, float damageMultiplierBonus) {
        this.duration = duration;
        this.damageMultiplierBonus = damageMultiplierBonus;
    }

    /**
     * Activate the skill for an actor.
     *
     * @param actor The actor on which the skill is activated
     */
    public abstract void activateSkill(Actor actor);

    /**
     * Deactivate the skill.
     *
     */
    public abstract void deactivateSkill();

    /**
     * Get a string representation of the skill.
     *
     * @return A string describing the skill and its effects
     */
    public abstract String toString();
}