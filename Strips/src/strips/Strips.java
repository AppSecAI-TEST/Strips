package strips;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * General implementation of the classic strips algorithm. 
 * Can use any plannable.
 * @author Or Priesender
 *
 */
public class Strips implements Planner{
	private Plannable plannable;

	/**
	 * Plan a list of actions to get to the provided goal state.
	 */
	@Override
	public List<Action> plan(Plannable plannable) {

		LinkedList<Action> plan = new LinkedList<>();
		Stack<Predicate> stack = new Stack<>();
		this.plannable = plannable;

		stack.push(plannable.getGoal());

		while(!stack.isEmpty()){
			Predicate top = stack.peek();
			if(!(top instanceof Action)){ //top is Clause or Predicate
				if(top instanceof Clause){
					Clause c = (Clause) top;
					if(!plannable.getKnowledgeBase().satisfiesClause(c)){
						//unsatisfied multi predicate
						for(Predicate p : c.predicates) //TODO: predicates are inserted with no regards of order - can use heuristic methods here
							stack.push(p);
					} else stack.pop();
				}
				else{ //top is a single predicate
					if(!plannable.getKnowledgeBase().satisfiesPredicate(top)){ //single unsatisfied predicate
						stack.pop();
						Action action = plannable.getSatisfyingAction(top);//TODO: CHANGE TO ACTIONS AND NOT ACTION
						if(action!=null){
							stack.push(action);
							stack.push(action.preconditions);
						}  else {//action is null - no satisfying actions predicate
							System.out.println("No Solution !");
							return null;
						}
					} else {

						stack.pop(); //idf the KB satisfies the Predicate/Clause then put it out
					}
				}
			} else{ //if top is an Action
				stack.pop();
				Action action = (Action) top;
				plannable.getKnowledgeBase().update(action.effects);
				plan.add(action);
				plan.addAll(action.getMiniActions());


			}
		}
		return plan;
	}

}
