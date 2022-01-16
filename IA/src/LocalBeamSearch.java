import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LocalBeamSearch {
	
	/* 
	 * algo_LocalBeamSearch(int k) 
	 * retourne le chemin solution s'il le trouve
	 * prend en argument un entier k qui représente le nombre des meilleurs états surlequels se fait la recherche locale
	*/

	public static ArrayList<State> algo_LocaLBeamSearch(int k) {
		//liste des états explorés
        ArrayList<State> explored = new ArrayList<State>();
        
        // on ajoute l'état initial dans la liste des états explorés
        explored.add(new State(new Visit(City.listCity.get(0),City.listCity), null, 0));
        
        int i = 0;
        boolean Found = false;
        
        // tant que l'on ne trouve pas l'état final on continue la recherche locale
        while (!Found) {
        	
        	//liste des voisins des états explorés
        	ArrayList<State> succ = new ArrayList<State>();
        	
        	// erreur si on a tout exploré et on n'a pas trouvé la solution
            if (i >= explored.size())
                throw new ArrayIndexOutOfBoundsException("Le programme ne trouve pas de solution");
            
            // on remplit la listes succ avec les les voisins des états explorés
            for (int l = i; l < explored.size(); l++) {
            	succ.addAll(explored.get(l).expand());
            }
            
            // on met à jour le i pour ne pas reexplorer les mêmes états plusieurs fois
            i = explored.size();
            
            // on trie les états en foction de leur évaluations 
            Collections.sort(succ, new State.StateComparator());
            
            // on regarde les k premiers états tries
            for (int j = 0; j < Math.min(k,succ.size()); j++) {
                State Node = succ.get(j);
                
                // on ajoute l'état courant dans la liste des explorés
                explored.add(Node);
                
                // on supprime l'état courant dans la liste des voisins
                succ.remove(Node);
                
                // on vérifie si l'état courant est l'état finale, si oui on a trouvé la solution
                if (Node.getV().isSolved()){
                    Found = true;
                }
            }
        }
        
        // on retrouve les parents de l'état final afin de retrouver le chemin de solution
        ArrayList<State> solution = new ArrayList<State>();
        solution.add(explored.get(explored.size() - 1));
        while (!solution.get(0).equals(explored.get(0))) {
            solution.add(0,solution.get(0).getP());
        }
        return solution;
    }
	
}
