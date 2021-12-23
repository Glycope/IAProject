import java.util.ArrayList;
import java.util.Random;

public class Generation {
	public ArrayList<City> generation (int x, int y, int n){  //x max en abscisse, y max en ordonnée, n nombre de villes
		ArrayList<City> lVille = new ArrayList<City>();
		ArrayList<Integer> yUse = new ArrayList<Integer>();
		ArrayList<Integer> xUse = new ArrayList<Integer>();
		
		/* Génère un graphe en selection x,y aléatoires compris entre xmax et ymax
		 * si x et y sont déjà pris (càd un point existe déjà à ces coordonnées), 
		 * on génère deux nouveaux (x,y)
		 * (utilise deux listes de taille xmax et ymax initialisées à 0, puis à 1 en index
		 * y ou x lorsqu'ils sont  générés)
		 * */
		
		for (int i = 0; i < x; i++) {
		    xUse.add(0);
		}
		for (int i = 0; i < y; i++) {
		    yUse.add(0);
		}
		
		Random random = new Random();
		int yAlea, xAlea = 0;
		
		for(int i = 0; i < n; i++) {
			yAlea = random.nextInt(y);
			xAlea = random.nextInt(x);
			City ville = new City(xAlea, yAlea);
			
			while((xUse.get(xAlea) == 1) && (yUse.get(yAlea) == 1)) {
				ville = new City(xAlea, yAlea);
				lVille.add(ville);
			}
		
			xUse.set(xAlea, 1);
			yUse.set(yAlea, 1);
		}
		
		return lVille;
	}
} 