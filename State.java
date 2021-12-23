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
			int imin = 0;
			double min = prim.get(0).distance(lcity.get(1));
			for (int i = 0; i < prim.size(); i++) {
				for (int j = 0; j < lcity.size(); j++) {
					double d = prim.get(i).distance(lcity.get(j));
					if (d < min && (i != 0 || j != 0)) {
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

	
}
