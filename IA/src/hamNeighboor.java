import java.util.ArrayList;
import java.util.Collections;

public class hamNeighboor {

	private Hamilton state;
	private ArrayList<Hamilton> lVoisinage = new ArrayList<Hamilton>();
	private static ArrayList<City> list;
	public static int ite = 0;

	public hamNeighboor(Hamilton s) {
		this.state = s;
		for(int i = 0; i < s.getCycle().size(); i++) { 
			for(int j = i+1; j < s.getCycle().size(); j++) {
				ite++;
				
				list = new ArrayList<City>(s.getCycle());   
				Collections.swap(list, i, j);	
				Hamilton ham = new Hamilton(s.getfCity());
				ham.setCycle(list);
				this.lVoisinage.add(ham);
				list = null;
				//this.TwoSwap(i, j);
			}
		}
	}
	
	//fonction useless
	public void TwoSwap(int i, int j) {
		
		Hamilton ham = new Hamilton(this.state.getfCity());
		list = new ArrayList<City>();
		list.addAll(this.state.getCycle().subList(0, i));
		for(int k = i; k <= j; k++) {
			list.add(this.state.getCycle().get(j-(k-i)));
		}
		list.addAll(this.state.getCycle().subList(j+1, this.state.getCycle().size()));
		ham.setCycle(list);
		//System.out.println(list);
		this.lVoisinage.add(ham);
		list = null;

	}
	
	public Hamilton getState() {
		return this.state;
	}
	
	public ArrayList<Hamilton> getVois() {
		return this.lVoisinage;
	}
	
	public Hamilton hillClim() {
		
		hamNeighboor bestHam = this;
		Hamilton best = this.state;
		Boolean found = false;
		for(int i = 0; i < this.lVoisinage.size(); i++) {
			if(this.lVoisinage.get(i).getWeight() < best.getWeight()) {
				found = true;
				best = this.lVoisinage.get(i);	
			}
		}
		
		if(found) {
			bestHam = null;
			bestHam = new hamNeighboor(best);
			//System.out.println("test");
			return bestHam.hillClim();
		}
		
		return bestHam.state;
	}
	
}
