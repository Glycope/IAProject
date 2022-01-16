import java.util.Scanner;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Main {

	public class Main {
	
	public static void test(int k, int n, int x, int y) {
		
		double mHam = 0;
		double mHam2 = 0;
		double mHam3 = 0;
		double mAstar = 0;
		double mLocal = 0;
		double mLocal2 = 0;
		double mLocal3 = 0;
		for(int i = 0; i < k; i++) {
			Generation.randomGeneration(x,y,n);
			
			//ASTAR
			long startTime1 = System.nanoTime();
	        ArrayList<State> astarStates = AStar.algo_AStar();
			long endTime1 = System.nanoTime();
	        long duration1 = (endTime1 - startTime1);
	        mAstar += duration1/1000000;
			
	        //Hill climbing
			Hamilton test = new Hamilton(City.listCity.get(0));
			test.generate();
		
			long startTime2 = System.nanoTime();
			Hamilton u = test.go();
			long endTime2 = System.nanoTime();
			long duration2 = (endTime2 - startTime2); 
			double rate = (test.getWeight() - u.getWeight())/test.getWeight();
			double rate2 = (u.getWeight() - astarStates.get(astarStates.size() - 1).getCA())/u.getWeight();
			
			mHam += rate;
			mHam2 += duration2/1000000;
			mHam3 += rate2;
			
			//Local Beam 
			
		}
		mAstar = mAstar/k; //temps d'execution moyen Astar
		
		mHam = mHam/k; //Ecart avec solution initiale HillClim
		mHam2 = mHam2/k; //Temps d'execution hill clim
		mHam3 = mHam3/k; //Ecart avec solution optimale hill clim
		
		System.out.println(mAstar);
		System.out.println(mHam);
		System.out.println(mHam3);
		System.out.println(mHam2);
		
	}

	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		
		int x = scan.nextInt();
		int y = scan.nextInt();
		int n = scan.nextInt();
		int k = scan.nextInt();
		/*
		new City(19,18);
        new City(16,13);
        new City(11,1);
        new City(3,0);
        new City(3,4);
        new City(4,6);
        new City(3,9);
        new City(5,11);
        new City(8,8);
        new City(16,17);
System.out.println("HILL CLIMBING SEARCH");
        Hamilton test = new Hamilton(City.listCity.get(0));
        test.generate();
        
        long startTime2 = System.nanoTime();
        Hamilton u = test.go();
        long endTime2 = System.nanoTime();

        long duration2 = (endTime2 - startTime2); 
        System.out.println(u.getCycle());
        System.out.println(u.getWeight());
        System.out.println(hamNeighboor.ite);
        System.out.println(duration2/1000000 + "ms"); //to ms 

        System.out.println("LOCAL BEAM SEARCH");
        long startTime3 = System.nanoTime();
        ArrayList<State> beamStates = LocalBeamSearch.algo_LocaLBeamSearch(3);
        long endTime3 = System.nanoTime();
        long duration3 = (endTime3 - startTime3);
        System.out.println(beamStates.get(beamStates.size() - 1).getCA());
        System.out.println(duration3/1000000 + "ms");
        System.out.println(beamStates);

        System.out.println("A*");
        long startTime1 = System.nanoTime();
        ArrayList<State> astarStates = AStar.algo_AStar();
        long endTime1 = System.nanoTime();
        long duration1 = (endTime1 - startTime1);
        System.out.println(astarStates.get(astarStates.size() - 1).getCA());
        System.out.println(duration1/1000000 + "ms");
        System.out.println(astarStates);*/
		/*for(int i = 0; i < City.listCity.size(); i++) {
			System.out.println(City.listCity.get(i).getX());
			System.out.println(City.listCity.get(i).getY());
			System.out.println("\n");
		}*/
	
		scan.close();
		
		test(k,n, x, y);
		
		/*
		Hamilton test = new Hamilton(City.listCity.get(0));
		test.generate();
		System.out.println(test.weight());
		System.out.println(test.getCycle() + "\n");
		
		long startTime2 = System.nanoTime();
		Hamilton u = test.go();
		long endTime2 = System.nanoTime();

		long duration2 = (endTime2 - startTime2); 
		System.out.println(u.getCycle());
		System.out.println(u.getWeight());
		System.out.println(hamNeighboor.ite);
		System.out.println(duration2/1000000 + "ms"); //to ms 
		
		
		
		long startTime1 = System.nanoTime();
		ArrayList<State> astarStates = AStar.algo_AStar();
		long endTime1 = System.nanoTime();
		long duration1 = (endTime1 - startTime1);
		System.out.println(astarStates.get(astarStates.size() - 1).getCA());
		System.out.println(duration1/1000000 + "ms");
		System.out.println(astarStates);
		
		
		long startTime3 = System.nanoTime();
		ArrayList<State> beamStates = LocalBeamSearch.algo_LocaLBeamSearch(10);
		long endTime3 = System.nanoTime();
		long duration3 = (endTime3 - startTime3);
		System.out.println(beamStates.get(beamStates.size() - 1).getCA());
		System.out.println(duration3/1000000 + "ms");
		System.out.println(beamStates);
		*/
		
	}
	
}
	
}
