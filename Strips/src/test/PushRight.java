package test;

import strips.Action;
import strips.Clause;
import strips.Predicate;

public class PushRight extends Action{

	public PushRight(String id, String value) {
		super("PushRight", id, value);
		String[] vals = value.split(",");
		int i = Integer.parseInt(vals[0]);
		int j = Integer.parseInt(vals[1]);
		
		preconditions = new Clause(null);
		preconditions.addPredicate(new SokPredicate("PlayerAt", "",""+(i)+","+(j-1)));
		preconditions.addPredicate(new SokPredicate("ClearAt", "",""+(i)+","+(j+1)));
		preconditions.addPredicate(new SokPredicate("BoxAt",id,""+(i)+","+(j)));
		
		effects = new Clause(null);
		effects.addPredicate(new SokPredicate("PlayerAt", "", ""+(i)+","+(j)));
		effects.addPredicate(new SokPredicate("BoxAt", id, ""+(i)+","+(j+1)));
		effects.addPredicate(new SokPredicate("ClearAt", "", ""+(i)+","+(j-1)));
	}

}
