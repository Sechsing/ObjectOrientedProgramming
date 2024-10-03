package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Class representing the Action to perform Skill.
 */
public class PerformSkillAction extends Action {
    private Skill skill;

    /**
     * Constructor for creating a PerformSkillAction.
     *
     * @param skill The skill to be performed by the actor
     */
    public PerformSkillAction(Skill skill) {
        this.skill = skill;
    }

    /**
     * Execute the skill action for the actor.
     *
     * This method is called when the actor performs the skill action. It activates
     * the associated skill for the actor and returns a message indicating the skill's use.
     *
     * @param actor The actor performing the skill action
     * @param map   The GameMap where the actor is located
     * @return A message indicating the actor's use of the skill
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // Activate the associated skill for the actor.
        skill.activateSkill(actor);
        // Return a message indicating the actor's use of the skill.
        return actor + " uses " + skill.toString();
    }

    /**
     * Get a description of the skill action for use in the menu.
     *
     * This method returns a description of the skill action to be displayed in the game menu.
     *
     * @param actor The actor performing the skill action
     * @return A menu description of the skill action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " uses the special skill " + skill.toString();
    }
}