package strips;

import java.util.List;

/**
 * An interface to define a planner behavior.
 * @author Or Priesender
 *
 */
public interface Planner {

	List<Action> plan(Plannable plannable);
}
