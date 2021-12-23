import java.util.ArrayList;

public class Visit {
	
	private City c;
	private ArrayList<City> nonVisited;
	
	public Visit(City c, ArrayList<City> nonVisited) {
		this.c = c;
		this.nonVisited = nonVisited;
	}
	
	public City getC() {
		return this.c;
	}
	
	public ArrayList<City> getNV() {
		return this.nonVisited;
	}
	
	public Visit goTo(City c) {
		if ((this.nonVisited.size() != 1 && c.equals(City.listCity.get(0))) || !this.nonVisited.contains(c) || this.c.equals(c))
			return null;
		ArrayList<City> nonVisited = new ArrayList<City>(this.nonVisited);
		nonVisited.remove(c);
		return new Visit(c, nonVisited);
	}
	
	public boolean isSolved() {
		return (this.c.equals(City.listCity.get(0)) && this.nonVisited.isEmpty());
	}
	
	@Override
	public Object clone() {
		Visit copy = new Visit((City) this.c.clone(), new ArrayList<City>(this.nonVisited));
		return copy;
	}
	
}
