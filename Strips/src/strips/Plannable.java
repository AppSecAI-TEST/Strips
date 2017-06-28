package strips;

import java.util.Set;

/**
 * Defines a plannable problem required behavior for the planner to work properly,
 * @author Or Priesender
 *
 */
public interface Plannable {

	Clause getKnowledgeBase();
	Clause getGoal();
	Set<Action> getSatisfyingActions(Predicate p);
	Action getSatisfyingAction(Predicate p);
}
