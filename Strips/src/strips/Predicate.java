package strips;

public class Predicate {
	
	protected String type,id,value;
	protected boolean selfSatisfied;
	
	/**
	 * A general predicate following mathematical predicate rules.
	 * @param type name of the predicate
	 * @param id id of the object refferred to
	 * @param value value of the predicate
	 */
	public Predicate(String type, String id, String value) {
		 super();
		 this.type = type;
		 this.id = id;
		 this.value = value;
		 selfSatisfied = false;
	}
	
	public boolean isSatisfied(){
		return false;
	}
	/**
	 * Check if this predicate satisfies the given predicate.
	 * @param p another predicate
	 * @return true or false
	 */
	public boolean satisfies(Predicate p){
		if(selfSatisfied)
			return isSatisfied();
		else return ((type.equals(p.type))&&(id.equals(p.id) || p.id.equals("?"))&&(value.equals(p.value)));
	}
	//TODO: sokoban will extend this class and override this, because it is true for a general predicate.
	public boolean contradicts(Predicate p){
		return (type.equals(p.type) && (id.equals(p.id)) && !(value.equals(p.value)));
	}
	
	@Override
	public int hashCode() {
		return (type+id+value).hashCode();
	}
	//TODO:change presentation if needed 
	@Override
	public String toString() {
		return type+"("+id+","+"("+value+")"+")";
	}
	
	
	public boolean equals(Predicate p) {
		return (type.equals(p.type) && id.equals(p.id) && value.equals(p.value));
	}
	
	public String getType(){
		return type;
	}
	
	public String getValue(){
		return value;
	}
	
	public String getId(){
		return id;
	}
}
