import java.util.Scanner;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
	
		//int x = scan.nextInt();
		//int y = scan.nextInt();
		//int n = scan.nextInt();
		//Generation.randomGeneration(x,y,n);
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
	
		for(int i = 0; i < City.listCity.size(); i++) {
			System.out.println(City.listCity.get(i).getX());
			System.out.println(City.listCity.get(i).getY());
			System.out.println("\n");
		}
	
		scan.close();
		
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
		
		/*long startTime1 = System.nanoTime();
		ArrayList<State> biteErection = State.AStar();
		long endTime1 = System.nanoTime();
		long duration1 = (endTime1 - startTime1);
		System.out.println(biteErection.get(biteErection.size() - 1).getCA());
		System.out.println(duration1/1000000 + "ms");
		System.out.println(biteErection);*/
		
		System.out.println(State.LocaLBeamSearchAlgo(3));
		
		
	}
	
}