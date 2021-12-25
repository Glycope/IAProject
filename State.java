import java.util.ArrayList;

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
	
	public int getId() {
		return id;
	}
	
	public ArrayList<State> expand() {
		ArrayList<State> succ = new ArrayList<State>();
		for (City c : this.v.getNV()) {
			Visit v = (Visit) this.v.clone();
			v = v.goTo(c);
			if (v != null) {
				succ.add(new State(v, this, this.costAction + c.distance(this.v.getC())));
			}
		}
		return succ;
	}
	
	
	public double evaluate() {
		return this.h + this.costAction;
	}
	
	public static ArrayList<State> AStar() {
		ArrayList<State> explored = new ArrayList<State>();
		ArrayList<State> succ = new ArrayList<State>();
		explored.add(new State(new Visit(City.listCity.get(0),City.listCity), null, 0));
		
		int i = 0;
		while (!explored.get(explored.size() - 1).v.isSolved()) {
			if (i >= explored.size())
				throw new ArrayIndexOutOfBoundsException("Le programme ne trouve pas de solution");
			succ.addAll(explored.get(i).expand());
			State minState = explored.get(i);
			double minf = minState.evaluate();
			for (int j = 0; j < succ.size(); j++) {
				double f = succ.get(j).evaluate();
				if (f < minf) {
					minState = explored.get(j);
					minf = f;
				}
			}
			explored.add(minState);
			succ.remove(minState);
			i++;
		}
		
		ArrayList<State> solution = new ArrayList<State>();
		solution.add(explored.get(explored.size() - 1));
		while (!solution.get(0).equals(explored.get(0))) {
			solution.add(0,solution.get(0).parent);
		}
		return solution;
	}
	
}
