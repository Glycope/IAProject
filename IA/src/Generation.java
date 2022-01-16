import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Generation {
public static void randomGeneration (int x, int y, int n){  //x max en abscisse, y max en ordonnée, n nombre de villes
		
		final ArrayList<City> listVille = new ArrayList<City>();
		
		Random random = new Random();
		int yAlea, xAlea = 0;
		City.listCity = new ArrayList<City>();
		
		for(int i = 0; i < n; i++) { //genere aleatoirement X et Y 
			yAlea = random.nextInt(y);
			xAlea = random.nextInt(x);
			City ville = new City(xAlea, yAlea); //place une ville sur les coordonnées
			
			while(listVille.contains(ville)) { //Recommence si cette ville existe déjà
				yAlea = random.nextInt(y);
				xAlea = random.nextInt(x);
				ville.setX(xAlea);
				ville.setY(yAlea);
			}
			
			listVille.add(ville);
		}
		
	}
	
	public static void myGeneration(int n) {
		for (int i = 0; i < n; i++) {
			try {
				Scanner saisieUtilisateur = new Scanner(System.in);
				System.out.print("x" + i + " :");
				double x = saisieUtilisateur.nextDouble();
				System.out.print("y" + i + " :");
				double y = saisieUtilisateur.nextDouble();
				new City(x,y);
			}
			catch (IllegalArgumentException e) {
				System.out.println("Valeurs décimales svp");
				myGeneration(n);
			}
		}
	}
} 
