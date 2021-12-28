import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;


public class Hamilton { //pour la partie 2

	private City villeDepart;
	private ArrayList<City> cycle = new ArrayList<City>();
	private ArrayList<Hamilton> lVoisinage = new ArrayList<Hamilton>();
	
	public Hamilton(City v) { //prend un point de départ
		this.villeDepart = v;
		this.generate();
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
		for(int i = 1; i < s-1; i++) {
			poids += this.cycle.get(i).distance(this.cycle.get(i+1));
		}
		poids += this.cycle.get(s-1).distance(this.villeDepart);
		
		return poids;
	}
	
	public ArrayList<City> getCycle(){
		return this.cycle;
	}
	
	public ArrayList<Hamilton> getNeighboor(){
		return this.lVoisinage;
	}
	
	public City getfCity() {
		return this.villeDepart;
	}
	
	public void setCycle(ArrayList<City> l) {
		this.cycle = l;
	}
	
	public Hamilton go() {
		hamNeighboor best = new hamNeighboor(this);
		
		Hamilton u = best.hillClim();
		
		return u;
	}
	
}
