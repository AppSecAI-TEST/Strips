package strips;

import java.util.HashSet;
import java.util.Set;

/**
 * A group of predicates connected with AND between them.
 * @author Or Priesender
 *
 */
public class Clause extends Predicate {

	HashSet<Predicate> predicates;
	
	
	public Clause(Predicate...predicates ) {
		super("And","","");
		if(predicates != null){
			this.predicates = new HashSet<>();
			for(Predicate p : predicates){
				this.predicates.add(p);
			}
			buildValue();
		}
	}
	private void buildValue() {
		StringBuilder sb = new StringBuilder();
		sb.append(type);
		sb.append("(");
		for(Predicate p : predicates)
			sb.append(p.toString() + "&");
		sb.deleteCharAt(sb.length()-1);
		sb.append(")");
		value = sb.toString();
	}
	
	public void update(Clause effect){
		effect.predicates.forEach((Predicate p) -> predicates.removeIf((Predicate pr) -> p.contradicts(pr)));
		predicates.addAll(effect.predicates);
		buildValue();
	}
	
	public void addPredicate(Predicate p){
		if(predicates == null)
			predicates = new HashSet<>();
		this.predicates.add(p);
		buildValue();
	}
	
	/**
	 * An AND clause satisfies a predicate only if every one of the predicates in the clause satisfies it.
	 * @param p predicate
	 * @return true or false
	 */
	public boolean satisfiesPredicate(Predicate p){
		if(p.selfSatisfied)
			return p.isSatisfied();
		for(Predicate pr : predicates){
			if(pr.satisfies(p))
				return true;
		}
		return false;
	}
	
	/**
	 * An AND clause satisfies another AND clause only if all predicates satisfy each other.
	 * @param c another clause
	 * @return true or false
	 */
	public boolean satisfiesClause(Clause c){
		for(Predicate p : c.predicates)
			if(!satisfiesPredicate(p))
				return false;
		return true;
						
	}
	
	public Set<Predicate> getPredicates(){
		return predicates;
	}
}
