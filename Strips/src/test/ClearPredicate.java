package test;

import strips.Predicate;

public class ClearPredicate extends SokPredicate {

	public ClearPredicate( String id, String value) {
		super("ClearAt", id, value);
		
	}
	
	@Override
	public boolean satisfies(Predicate p) {
		if(!p.getType().equals("TargetAt"))
			return super.contradicts(p);
		else if(value.equals(p.getValue())){
			return true;
		}
		else return false;
	}

}
