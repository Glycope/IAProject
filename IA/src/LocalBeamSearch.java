import java.util.ArrayList;
import java.util.Collections;

public class LocalBeamSearch {

public static ArrayList<State> algo_LocaLBeamSearch(int k) {
        
        ArrayList<State> explored = new ArrayList<State>();
        
        
        ArrayList<Double> Evaluations = new ArrayList<Double>();
        explored.add(new State(new Visit(City.listCity.get(0),City.listCity), null, 0));
        int i = 0;
        boolean Found = false;
        while (!Found) {
        	ArrayList<State> succ = new ArrayList<State>();
        	ArrayList<State> SuccSorted = new ArrayList<State>();
            if (i >= explored.size())
                throw new ArrayIndexOutOfBoundsException("Le programme ne trouve pas de solution");
            for (int l = i; l < explored.size(); l++) {
            	succ.addAll(explored.get(l).expand());
            }
            
            // on regarde les evaluations
            for (int j = 0; j < succ.size(); j++) {
                double f = succ.get(j).evaluate();
                Evaluations.add(f);
            }
            //System.out.println("succ : " + succ);
            //System.out.println("eval : " + Evaluations);
            // on trie les evaluations 
            Collections.sort(Evaluations);
            // on trie les etats courrants
            State currState = succ.get(0);
            for (int k1 = 0; k1 < Evaluations.size(); k1++) {
                for (int j = 0; j < succ.size(); j++) {
                    double f = succ.get(j).evaluate();
                    if (f == Evaluations.get(k1)) {
                        currState = succ.get(j);
                    }
                }
                SuccSorted.add(currState);
            }
            //System.out.println("succSort : " + SuccSorted);
            //System.out.println("evalSorted : " + Evaluations);
            //System.out.println(SuccSorted);
            // on regarde les k premiers etats tries
            for (int j = 0; j < k; j++) {
                State Node = SuccSorted.get(j);
                if (Node.getV().isSolved()){
                    Found = true;
                }
                explored.add(Node);
                succ.remove(Node);
            }
            i = explored.size() - k;
        }
        ArrayList<State> solution = new ArrayList<State>();
        solution.add(explored.get(explored.size() - 1));
        while (!solution.get(0).equals(explored.get(0))) {
            solution.add(0,solution.get(0).getP());
        }
        return solution;
    }
	
}
