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
