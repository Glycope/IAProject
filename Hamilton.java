import java.util.ArrayList;
import java.util.Random;

public class Hamilton { //pour la partie 2

	
	private ArrayList<City> cycle = new ArrayList<City>();
	
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
		}
	}
	
	public double weight() {
		double poids = 0;
		int s = City.listCity.size();
		for(int i = 0; i < s-1; i++) {
			poids += City.listCity.get(i).distance(City.listCity.get(i+1));
		}
		poids += City.listCity.get(s-1).distance(City.listCity.get(0));
		
		return poids;
	}
	
	public ArrayList<City> getCycle(){
		return this.cycle;
	}
	
}
