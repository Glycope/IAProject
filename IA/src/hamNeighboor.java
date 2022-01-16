import java.util.ArrayList;
import java.util.Collections;

public class hamNeighboor {

	private Hamilton state; //Le cycle sur lequel on se situe 
	private ArrayList<Hamilton> lVoisinage = new ArrayList<Hamilton>(); //Liste des autres cycles (voisins)
	private static ArrayList<City> list; 

	public hamNeighboor(Hamilton s) {  //Genere l'ensemble des possibilités pour un cycle donnés
		this.state = s;
		for(int i = 0; i < s.getCycle().size(); i++) { 
			for(int j = i+1; j < s.getCycle().size(); j++) {
				
				list = new ArrayList<City>(s.getCycle());     
				Collections.swap(list, i, j);	         //Echange les positions i,j du cycle, ce que correspond à changer deux arretes.
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
			bestHam = new hamNeighboor(best); //Si on trouve, on relance hill climbing sur le nouveau
			//Utilisé un booléen pour ne pas générer des voisins inutiles
			return bestHam.hillClim();
		}
		
		return bestHam.state; //Sinon on return le courant 
	}
	
}
