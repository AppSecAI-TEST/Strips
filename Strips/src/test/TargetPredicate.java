package test;

import strips.Predicate;

public class TargetPredicate extends SokPredicate {

	public TargetPredicate( String id, String value) {
		super("TargetAt", id, value);
	}

	@Override
	public boolean satisfies(Predicate p) {
		if(!p.getType().equals("ClearAt"))
			return super.satisfies(p);
		else if(value.equals(p.getValue())){
			return true;
		}
		else return false;
	}
}
