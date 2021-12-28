import java.util.ArrayList;
import java.util.Collections;

public class hamNeighboor {

	private Hamilton state;
	private ArrayList<Hamilton> lVoisinage = new ArrayList<Hamilton>();
	private static ArrayList<City> list; 
	public hamNeighboor(Hamilton s) {
		this.state = s;
		for(int i = 0; i < s.getCycle().size(); i++) {
			for(int j = i+1; j < s.getCycle().size(); j++) {
				list = new ArrayList<City>(s.getCycle());   //A optimiser niveau memoire
				Collections.swap(list, i, j);	
				Hamilton ham = new Hamilton(s.getfCity());
				ham.setCycle(list);
				this.lVoisinage.add(ham);
				list = null;
			}
		}
	}
	
	public Hamilton getState() {
		return this.state;
	}
	
	public ArrayList<Hamilton> getVois() {
		return this.lVoisinage;
	}
	
	public Hamilton hillClim() {
		hamNeighboor current = this;
		hamNeighboor bestHam = this;
		for(int i = 0; i < this.lVoisinage.size(); i++) {
			if(this.lVoisinage.get(i).weight() <= bestHam.state.weight()) {
				bestHam = null;
				bestHam = new hamNeighboor(this.lVoisinage.get(i));
			}
		}
		if(bestHam == current) {
			return bestHam.state;
		} else {
			current = null;
			//System.out.println("tets");
			return bestHam.hillClim();
		}
	}
}
