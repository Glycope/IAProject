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
		this.h = heuristic(v);
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
	
	public double heuristic(Visit v) {
		ArrayList<City> lcity = new ArrayList<City>(v.getNV());
		ArrayList<City> prim = new ArrayList<City>();
		prim.add(v.getC());
		double somme = 0;
		while(!lcity.isEmpty()) {
			double min;
			int imin = 0;
			boolean firstCity;
			if (lcity.get(0).equals(City.listCity.get(0))) {
				min = prim.get(0).distance(lcity.get(1));
				firstCity = true;
			}
			else {
				min = prim.get(0).distance(lcity.get(0));
				firstCity = false;
			}
			for (int i = 0; i < prim.size(); i++) {
				for (int j = 0; j < lcity.size(); j++) {
					double d = prim.get(i).distance(lcity.get(j));
					if (d < min && !(i == 0 && j == 0 && firstCity)) {
						min = d;
						imin = j;
					}
				}
			}
			prim.add(lcity.get(imin));
			lcity.remove(imin);
			somme += min;
		}
		return somme;
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
				throw new ArrayIndexOutOfBoundsException();
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
		}
		
		ArrayList<State> solution = new ArrayList<State>();
		solution.add(explored.get(explored.size() - 1));
		while (!solution.get(0).equals(explored.get(0))) {
			solution.add(0,solution.get(0).parent);
		}
		return solution;
	}
	
}
