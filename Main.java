import java.util.Scanner;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
	
		int x = scan.nextInt();
		int y = scan.nextInt();
		int n = scan.nextInt();
	
		Generation.randomGeneration(x,y,n);
	
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
		
		long startTime = System.nanoTime();
		Hamilton u = test.go();
		long endTime = System.nanoTime();

		long duration = (endTime - startTime); 
		System.out.println(u.getCycle());
		System.out.println(u.getWeight());
		System.out.println(hamNeighboor.ite);
		System.out.println(duration/1000000 + "ms"); //to ms 
	}
	
}
