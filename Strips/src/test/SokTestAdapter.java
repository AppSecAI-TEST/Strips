package test;

import java.util.HashSet;
import java.util.Set;

import strips.Action;
import strips.Clause;
import strips.Plannable;
import strips.Predicate;



public class SokTestAdapter implements Plannable{
	
	char[][] level = { 
			 {'#','#','#','#' },
			 {'#','#',' ','#' },
			 {'T','p',' ',' ' } 
			,{'#','@',' ','#' } 
			,{'#','T','#','#' }
	};
	
	protected Clause kb = null;

		@Override
	public Clause getKnowledgeBase() {
			if(kb==null){
			 kb = new Clause(null);
			int targets=0,boxes=0;
		for(int i=0;i<level.length;i++){
			for(int j=0;j<level[i].length;j++){
				switch(level[i][j]){
				case '#':kb.addPredicate(new Predicate("WallAt","",""+i+","+j));break;
				case 'T':targets++;kb.addPredicate(new TargetPredicate("t"+targets,""+i+","+j));break;
				case 'p':kb.addPredicate(new Predicate("PlayerAt","",""+i+","+j));break;
				case ' ':kb.addPredicate(new Predicate("ClearAt", "?", ""+i+","+j));break;
				case '@':boxes++;kb.addPredicate(new Predicate("BoxAt","b"+boxes,""+i+","+j));break;
				}
			}
		}
			}
		return kb;
	}

	@Override
	public Clause getGoal() {
		Clause goal = new Clause(null);
		int i=1;
		for(Predicate p : getKnowledgeBase().getPredicates()){
			if(p.getType().startsWith("Tar")){
				goal.addPredicate(new SokPredicate("BoxAt","b"+i,p.getValue()));
				i++;
			}
		}
		return goal;		
	}

	@Override
	public Set<Action> getSatisfyingActions(Predicate p) {
		Set<Action> actions = new HashSet<>();
		String[] vals = p.getValue().split(",");
		int i = Integer.parseInt(vals[0]);
		int j = Integer.parseInt(vals[1]);
		
		if(i+2 < level.length){	
				if(level[i+2][j] == 'p' && level[i+1][j] =='@' && (level[i][j] == 'T' || level[i][j]==' ')){ //up
				actions.add(new PushUp( p.getId(), ""+(i+1)+","+j));
			}
		}
		if(i-2 >= 0){
				if(level[i-2][j] == 'p' && level[i-1][j] =='@' && (level[i][j] == 'T'||level[i][j]==' ')){//down
					actions.add(new PushDown(p.getId(), ""+(i-1)+","+j));
			}
		}
		if(j+2 < level[i].length){
				if(level[i][j+2] == 'p' && level[i][j+1] =='@' && level[i][j] == 'T'){//left
					actions.add(new PushLeft(p.getId(), ""+(i)+","+(j+1)));
				}
		}
		if(j-2>=0){
				if(level[i][j-2] == 'p' && level[i][j-1] =='@' && (level[i][j] == 'T' || level[i][j]==' ')){//right
					actions.add(new PushRight( p.getId(), ""+(i)+","+(j-1)));
				}
		}
		
		return actions;
	}

	@Override
	public Action getSatisfyingAction(Predicate p) {
		Set<Action> possibles = getSatisfyingActions(p);
		int max = 0;
		int curr = 0;
		Action chosen = null;
		for(Action a : possibles){
			curr = getNumberOfSatisfiedConditions(a);
			if(max < curr){
				max = curr;
				chosen = a;
			}
			
		}
		return chosen;
	}
	
	public int getNumberOfSatisfiedConditions(Action a){
		int num=0;
		Clause pre = a.getPreconditions();
		for(Predicate p : getKnowledgeBase().getPredicates()){
			for(Predicate pr : pre.getPredicates())
				if(p.satisfies(pr))
					num++;
		}
		return num;
	}

}
