import java.util.ArrayList;
import java.util.Random;

public class Generation {
	public static void generation (int x, int y, int n){  //x max en abscisse, y max en ordonnée, n nombre de villes
		
		final ArrayList<City> listVille = new ArrayList<City>();
		
		Random random = new Random();
		int yAlea, xAlea = 0;
		
		for(int i = 0; i < n; i++) {
			yAlea = random.nextInt(y);
			xAlea = random.nextInt(x);
			City ville = new City(xAlea, yAlea);
			
			while(listVille.contains(ville)) { //peut etre gourmand
				yAlea = random.nextInt(y);
				xAlea = random.nextInt(x);
				ville.setCoord(xAlea, yAlea);
			}
			
			listVille.add(ville);
		}
		
	}
} 
