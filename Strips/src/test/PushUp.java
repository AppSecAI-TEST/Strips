package test;

import strips.Action;
import strips.Clause;
import strips.Predicate;

public class PushUp extends Action{
	
	public PushUp(String id, String value) {
		super("PushUp", id, value);
		String[] vals = value.split(",");
		int i = Integer.parseInt(vals[0]);
		int j = Integer.parseInt(vals[1]);
		
		Predicate p1 = new SokPredicate("PlayerAt", "",""+(i+1)+","+(j));
		Predicate p2 = new SokPredicate("ClearAt", "",""+(i-1)+","+(j));
		Predicate p4 = new SokPredicate("BoxAt",id,""+i+","+j);
		preconditions = new Clause(p1,p2);
		
		Predicate p3 = new SokPredicate("PlayerAt","?",""+(i)+","+(j));
		Predicate p5 = new SokPredicate("ClearAt","",""+(i+1)+","+j);
		Predicate p6 = new SokPredicate("BoxAt",id,""+(i-1)+","+j);
		
		effects = new Clause(p3,p5,p6);
	}

}
