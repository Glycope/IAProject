import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;

public class AStar {

	
	
	/* 
	 * algo_AStar() 
	 * retourne le chemin solution optimal
	 * prend en argument un entier k qui représente le nombre des meilleurs états surlequels se fait la recherche locale
	*/
	public static ArrayList<State> algo_AStar() {
		//liste des états explorés
		Deque<State> explored = new ArrayDeque<State>();
		//liste des voisins des états explorés
		ArrayList<State> frontier = new ArrayList<State>();
		// on ajoute l'état initial dans la liste des états explorés
		State init = new State(new Visit(City.listCity.get(0),City.listCity), null, 0);
		explored.add(init);
		int i = 0;
		int maxFrontier = 0;
		// tant que l'on ne trouve pas l'état final on continue la recherche
		while (!explored.peek().getV().isSolved()) {
			// erreur si on a tout exploré et on n'a pas trouvé la solution
			if (i >= explored.size())
				throw new ArrayIndexOutOfBoundsException("Le programme ne trouve pas de solution");
			// on remplit la listes succ avec les les voisins des états explorés
			frontier.addAll(explored.peek().expand());
			// mise a jour de la taille max de frontier
			maxFrontier = Math.max(maxFrontier, frontier.size());
			// on trouve l'état minimum évalué grâce à h(n) + f(n), le comparateur sera expliqué dans la classe State
			State minState = Collections.min(frontier, new State.StateComparator());
			// ajout de l'etat courant dans le stack des explorés
			explored.push(minState);
			// on retire l'etat courant de la liste frontier
			frontier.remove(minState);
			i++;
			
		}
		
		ArrayList<State> solution = new ArrayList<State>();
		//on remonte les parents de l'état final afin de retrouver le chemin solution
		solution.add(explored.pop());
		while (!solution.get(0).equals(init)) {
			solution.add(0,solution.get(0).getP());
		}
		System.out.println("maxFrontier : " + maxFrontier);
		return solution;
	}
	
}
