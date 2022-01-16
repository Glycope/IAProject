import java.util.ArrayList;
import java.util.Collections;

public class State {
	
	private Visit v;
	private double costAction;
	private State parent;
	private double h;
	private static int nbVisit = 0;
	private int id;
	
	public State(Visit v, State parent, double costAction) {
		this.v = v;
		this.costAction = costAction;
		this.parent = parent;
		this.h = v.heuristic();
		this.id = nbVisit++;
	}
	
	public Visit getV() {
		return this.v;
	}
	
	public State getP() {
		return this.parent;
	}
	
	public double getH() {
		return this.h;
	}
	
	public double getCA() {
		return this.costAction;
	}
	
	public int getId() {
		return id;
	}
	
	/*public ArrayList<State> expand() {
		ArrayList<State> succ = new ArrayList<State>();
		for (City c : this.v.getNV()) {
			Visit v = (Visit) this.v.clone();
			v = v.goTo(c);
			if (v != null) {
				succ.add(new State(v, this, this.costAction + c.distance(this.v.getC())));
			}
			
		}
		//System.out.println(succ);
		return succ;
	}*/
	
	public ArrayList<State> expand() {
		ArrayList<State> succ = new ArrayList<State>();
		for (City c : this.v.getNV()) {
			Visit v = (Visit) this.v.clone();
			v.goTo(c);
			if (!(this.v.getNV().size() != 1 && c.equals(City.listCity.get(0))) && this.v.getNV().contains(c) && !this.v.getC().equals(c))
				succ.add(new State(v, this, this.costAction + c.distance(this.v.getC())));
		}
		//System.out.println(succ);
		return succ;
	}
	
	
	public double evaluate() {
		return this.h + this.costAction;
	}
	
	public static ArrayList<State> AStar() {
		ArrayList<State> explored = new ArrayList<State>();
		ArrayList<State> frontier = new ArrayList<State>();
		explored.add(new State(new Visit(City.listCity.get(0),City.listCity), null, 0));
		int i = 0;
		int maxFrontier = 0;
		while (!explored.get(explored.size() - 1).v.isSolved()) {
			
			if (i >= explored.size())
				throw new ArrayIndexOutOfBoundsException("Le programme ne trouve pas de solution");
			ArrayList<State> tmp = explored.get(i).expand();
			tmp.removeIf(s -> s.isIn(explored));
			frontier.addAll(tmp);
			maxFrontier = Math.max(maxFrontier, frontier.size());
			
			State minState = frontier.get(0);
			double minf = minState.evaluate();
			for (int j = 0; j < frontier.size(); j++) {
				double f = frontier.get(j).evaluate();
				if (f < minf) {
					minState = frontier.get(j);
					minf = f;
				}
			}
			explored.add(minState);
			frontier.remove(minState);
			i++;
			
		}
		
		ArrayList<State> solution = new ArrayList<State>();
		solution.add(explored.get(explored.size() - 1));
		while (!solution.get(0).equals(explored.get(0))) {
			solution.add(0,solution.get(0).parent);
		}
		System.out.println("maxFrontier : " + maxFrontier);
		return solution;
	}
	
public static ArrayList<State> LocaLBeamSearchAlgo(int k) {
        
        ArrayList<State> explored = new ArrayList<State>();
        ArrayList<State> succ = new ArrayList<State>();
        ArrayList<State> SuccSorted = new ArrayList<State>();
        ArrayList<Double> Evaluations = new ArrayList<Double>();
        explored.add(new State(new Visit(City.listCity.get(0),City.listCity), null, 0));
        int i = 0;
        boolean Found = false;
        while (!explored.get(explored.size() - 1).v.isSolved() && !Found) {
            if (i >= explored.size())
                throw new ArrayIndexOutOfBoundsException("Le programme ne trouve pas de solution");
            succ.addAll(explored.get(i).expand());
            // on regarde les evaluations
            for (int j = 0; j < succ.size(); j++) {
                double f = succ.get(j).evaluate();
                Evaluations.add(f);
            }
            // on trie les evaluations 
            Collections.sort(Evaluations);
            // on trie les etats courrants
            State currState = succ.get(0);
            for (int k1 = 0; k1 < Evaluations.size(); k1++) {
                for (int j = 0; j < succ.size(); j++) {
                    double f = succ.get(j).evaluate();
                    if (f == Evaluations.get(k1)) {
                        currState = succ.get(j);
                    }
                }
                SuccSorted.add(currState);
            }
            //System.out.println(SuccSorted);
            // on regarde les k premiers etats tries
            for (int j = 0; j < k; j++) {
                State Node = SuccSorted.get(j);
                if (Node.v.isSolved()){
                    Found = true;
                }
                else {
                    explored.add(Node);
                    succ.remove(Node);
                }
            }
            i++;
        }
        
        ArrayList<State> solution = new ArrayList<State>();
        solution.add(explored.get(explored.size() - 1));
        while (!solution.get(0).equals(explored.get(0))) {
            solution.add(0,solution.get(0).parent);
        }
        return solution;
    }
	
	public boolean isSameVisit(State s) {
		if (this.v.equals(s.v)) return true;
		return false;
	}
	
	public boolean isIn(ArrayList<State> list) {
		for (State s : list) {
			if (this.isSameVisit(s)) return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		String s = this.v.toString();
		s += " g(n) = " + this.costAction + ", h(n) = " + this.h + "\n";
		return s;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof State))
			return false;
		boolean res = this.v.equals(((State) o).v) && this.costAction == ((State) o).costAction && this.h == ((State) o).h;
		if (this.parent == null && ((State) o).parent == null)
			return (true && res);
		else if ((this.parent == null && ((State) o).parent != null) || (this.parent != null && ((State) o).parent == null))
			return false;
		return (this.parent.equals(((State) o).parent) && res);
	}
	
}
