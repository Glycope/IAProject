import java.util.ArrayList;

public class City {
	
	private double x;
	private double y;
	public static ArrayList<City> listCity = new ArrayList<City>();
	
	
	public City(double x, double y) {
		this.x = x;
		this.y = y;
		listCity.add(this);
	}
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public double distance(City v) {
		return Math.sqrt((this.x - v.x)*(this.x - v.x) + (this.y - v.y)*(this.y - v.y));
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof City))
			return false;
		City c = (City) o;
		return (this.x == c.x && this.y == c.y);
	}
	
	@Override
	public Object clone() {
		City c = new City(this.x, this.y);
		return c;
	}
	
	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}
	
}
