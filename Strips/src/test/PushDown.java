package test;

import strips.Action;
import strips.Clause;
import strips.Predicate;

public class PushDown extends Action{
	
	public PushDown(String id, String value) {
		super("PushDown", id, value);
		String[] vals = value.split(",");
		int i = Integer.parseInt(vals[0]);
		int j = Integer.parseInt(vals[1]);
		
		preconditions = new Clause(null);
		preconditions.addPredicate(new SokPredicate("PlayerAt", "",""+(i-1)+","+(j)));
		preconditions.addPredicate(new SokPredicate("ClearAt", "",""+(i+1)+","+(j)));
		preconditions.addPredicate(new SokPredicate("BoxAt",id,""+(i)+","+(j)));
		
		effects = new Clause(null);
		effects.addPredicate(new SokPredicate("PlayerAt", "", ""+(i)+","+(j)));
		effects.addPredicate(new SokPredicate("BoxAt", id, ""+(i+1)+","+(j)));
		effects.addPredicate(new SokPredicate("ClearAt", "", ""+(i-1)+","+(j)));
		
		
	}

}
