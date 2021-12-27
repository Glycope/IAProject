import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;


public class Hamilton { //pour la partie 2

	
	private ArrayList<City> cycle = new ArrayList<City>();
	private ArrayList<Hamilton> lVoisinage = new ArrayList<Hamilton>();
	
	public Hamilton(City v) { //prend un point de départ
		this.cycle.add(v);
	}
	
	public void generate() { //genere un cycle hamiltonien initial (random)
		Random random = new Random();
		int j = 0;
		int s = City.listCity.size();
		for(int i = 0; i < s-1; i++) {
			j = random.nextInt(s);
			while(cycle.contains(City.listCity.get(j))) {
				j = random.nextInt(s);
			}
			
			cycle.add(City.listCity.get(j));
		} // C'est l'ordre qui définit le cycle
	}
	
	public double weight() { //heuristique?
		double poids = 0;
		int s = this.cycle.size();
		for(int i = 0; i < s-1; i++) {
			poids += this.cycle.get(i).distance(this.cycle.get(i+1));
		}
		poids += this.cycle.get(s-1).distance(this.cycle.get(0));
		
		return poids;
	}
	
	public ArrayList<City> getCycle(){
		return this.cycle;
	}
	
	public ArrayList<Hamilton> getNeighboor(){
		return this.lVoisinage;
	}
	
	public void genNeighboor() {
		for(int i = 1; i < this.cycle.size(); i++) {
			for(int j = i+1; j < this.cycle.size(); j++) {
				ArrayList<City> list = new ArrayList<City>(this.cycle);
				Collections.swap(list, i, j);	
				Hamilton ham = new Hamilton(this.cycle.get(0));
				ham.cycle = list;
				this.lVoisinage.add(ham);
			}
		}
	}
	
	public Hamilton hillClim() {
		Hamilton bestHam = this;
		for(int i = 0; i < this.lVoisinage.size(); i++) {
			if(this.lVoisinage.get(i).weight() <= bestHam.weight()) {
				bestHam = this.lVoisinage.get(i);
			}
		}
		if(bestHam == this) {
			return bestHam;
		} else {
			System.out.println("test");
			bestHam.genNeighboor();
			return bestHam.hillClim();
		}
	}
}
