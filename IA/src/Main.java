import java.util.Scanner;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Main {
	/*
	 * test(int k, int n, int x, int y) affiche la durée moyenne des 3 algorithmes et 
	 * les écarts des solutions des algos de recherche locale par rapport à la solution de A*
	 * k est le nombre de tests
	 * n est le nombre de villes à générer 
	 * x est la coordonée d'abscisse maximale
	 * y est la coordonée d'ordonnée maximale
	 */
	public static void test(int k, int n, int x, int y, int kbeam) {
		double mHam = 0;
		double mHam2 = 0;
		double mHam3 = 0;
		double mAstar = 0;
		double mLocal = 0;
		double mLocal2 = 0;
		for(int i = 0; i < k; i++) {
			Generation.randomGeneration(x,y,n);
			
			//ASTAR
			long startTime1 = System.nanoTime();
			System.out.println("L'algorithme A* : ");
	        ArrayList<State> astarStates = AStar.algo_AStar();
	        if (k < 5){
	        	System.out.println(astarStates);
	        }
			long endTime1 = System.nanoTime();
	        long duration1 = (endTime1 - startTime1);
	        mAstar += duration1/1000000;
			
	        //Hill climbing
			Hamilton test = new Hamilton(City.listCity.get(0));
			test.generate();
			long startTime2 = System.nanoTime();
			System.out.println("L'algorithme HillClimbing : ");
			Hamilton u = test.go();
			if (k < 5){
				System.out.println(u.getCycle());
				System.out.println(u.getWeight());
	        }
			long endTime2 = System.nanoTime();
			long duration2 = (endTime2 - startTime2); 
			double rate = (test.getWeight() - u.getWeight())/test.getWeight();
			double rate2 = (u.getWeight() - astarStates.get(astarStates.size() - 1).getCA())/u.getWeight();
			
			mHam += rate;
			mHam2 += duration2/1000000;
			mHam3 += rate2;
			
			//Local Beam 
			long startTime3 = System.nanoTime();
			System.out.println("L'algorithme LocalBeamSearch : ");
	        ArrayList<State> beamStates = LocalBeamSearch.algo_LocaLBeamSearch(kbeam);
	        long endTime3 = System.nanoTime();
	        if (k < 5){
	        	System.out.println(beamStates);
	        }
	        long duration3 = (endTime3 - startTime3);
			
	        mLocal += duration3/1000000;
			mLocal2 += (beamStates.get(beamStates.size() - 1).getCA() - astarStates.get(astarStates.size() - 1).getCA())/beamStates.get(beamStates.size() - 1).getCA();
			
			
		}
		mAstar = mAstar/k; //temps d'exécution moyen Astar
		mHam = mHam/k; ////Taux d'améliration entre la solution initiale et la solution finale de HillClim
		mHam2 = mHam2/k; //Temps d'exécution HillClim
		mHam3 = mHam3/k; //Taux d'améliration entre la solution de Astar et la solution de HillClim
		mLocal = mLocal/k;//Temps d'exécution LocalBeamSearch
		mLocal2 = mLocal2/k; //Taux d'améliration entre la solution de Astar et la solution de LocalBeamSearch
		
		System.out.println(" Temps d'exécution moyen de A* : " + mAstar);
		System.out.println(" Temps d'exécution moyen de HillClim : " + mHam2);
		System.out.println(" Temps d'exécution moyen de LocalBeamSearch : " + mLocal);
		System.out.println("Taux d'améliration moyen entre la solution initiale et la solution finale de HillClim : " + mHam);
		System.out.println("Taux d'améliration moyen entre la solution de Astar et la solution de HillClim : " + mHam3);
		System.out.println("Taux d'écart moyen entre la solution de Astar et la solution de LocalBeamSearch : " + mLocal2);
		
		
	}

	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		System.out.println(" Entrez la coordonnée d'abscisse maximale ");
		int x = scan.nextInt();
		System.out.println(" Entrez la coordonnée d'ordonnée maximale ");
		int y = scan.nextInt();
		System.out.println(" Entrez le nombre de villes à générer (restez raisonable) ");
		int n = scan.nextInt();
		System.out.println(" Entrez le nombre de tests à faire ");
		int k = scan.nextInt();
		System.out.println(" Entrez le nombre de villes pour l'Algorithme LocalBeamSearch ");
		int kbeam = scan.nextInt();
		scan.close();
		test(k, n, x, y, kbeam);
	}
	
}
	

