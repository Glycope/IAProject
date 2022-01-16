import java.util.ArrayList;

public class AStar {

	
	public static ArrayList<State> algo_AStar() {
		ArrayList<State> explored = new ArrayList<State>();
		ArrayList<State> frontier = new ArrayList<State>();
		explored.add(new State(new Visit(City.listCity.get(0),City.listCity), null, 0));
		int i = 0;
		int maxFrontier = 0;
		while (!explored.get(explored.size() - 1).getV().isSolved()) {
			
			if (i >= explored.size())
				throw new ArrayIndexOutOfBoundsException("Le programme ne trouve pas de solution");
			ArrayList<State> tmp = explored.get(i).expand();
			tmp.removeIf(s -> s.isIn(explored));
			frontier.addAll(tmp);
			maxFrontier = Math.max(maxFrontier, frontier.size());
			
			State minState = frontier.get(0);
			double minf = minState.evaluate();
			for (int j = 0; j < frontier.size(); j++) {
				double f = frontier.get(j).evaluate();
				if (f < minf) {
					minState = frontier.get(j);
					minf = f;
				}
			}
			explored.add(minState);
			frontier.remove(minState);
			i++;
			
		}
		
		ArrayList<State> solution = new ArrayList<State>();
		solution.add(explored.get(explored.size() - 1));
		while (!solution.get(0).equals(explored.get(0))) {
			solution.add(0,solution.get(0).getP());
		}
		System.out.println("maxFrontier : " + maxFrontier);
		return solution;
	}
	
}
