package strips;

import java.util.List;

/**
 * A general action that has preconditions,effects, and mini actions that was needed to execute this action.
 * @author Or Priesender
 *
 */
public class Action extends Predicate{

	
	protected Clause preconditions,effects;
	List<Action> miniActions;
	

	public Action(String type, String id, String value) {
		super(type, id, value);
	}
	
	public Clause getPreconditions() {
		return preconditions;
	}

	public void setPreconditions(Clause preconditions) {
		this.preconditions = preconditions;
	}

	public Clause getEffects() {
		return effects;
	}

	public void setEffects(Clause effects) {
		this.effects = effects;
	}

	public List<Action> getMiniActions() {
		return miniActions;
	}

	public void setMiniActions(List<Action> miniActions) {
		this.miniActions = miniActions;
	}
	
	
}
