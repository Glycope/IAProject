import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;


public class Hamilton { //pour la partie 2

	private City villeDepart;
	private ArrayList<City> cycle = new ArrayList<City>();
	private ArrayList<Hamilton> lVoisinage = new ArrayList<Hamilton>();
	private double weight = 0;
	
	public Hamilton(City v) { //prend un point de d�part
		this.villeDepart = v;
	}
	
	public void generate() { //genere un cycle hamiltonien initial (random)
		Random random = new Random();
		ArrayList<City> villes = new ArrayList<City>(City.listCity);
		int j = 0;
		int s = City.listCity.size();
		for(int i = 0; i < s-1; i++) {
			int u = villes.size();
			j = random.nextInt(u);
			cycle.add(villes.get(j));
			villes.remove(j);
		} // C'est l'ordre qui d�finit le cycle
		weight = weight();
	}
	
	
	public double weight() { //heuristique?
		double poids = 0;
		int s = this.cycle.size();
		for(int i = 0; i < s-1; i++) {
			poids += this.cycle.get(i).distance(this.cycle.get(i+1));
		}
		poids += this.cycle.get(s-1).distance(this.villeDepart);
		poids += this.cycle.get(0).distance(this.villeDepart);
		
		return poids;
	}
	
	public double getWeight() {
		return this.weight;
	}
	
	public void setCity(City v) {
		this.villeDepart = v;
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
		weight = weight();
	}
	
	public Hamilton go() {
		hamNeighboor best = new hamNeighboor(this);
		
		Hamilton u = best.hillClim();
		
		return u;
	}
	
}
