
import java.nio.file.Files;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.Assert.assertEquals;

public class GraphTest {
	
	City c0, c1, c2, c3, c4;
	Visit v0;
	State s0;

	
	@BeforeAll
	public static void beforeall() {
		
	}
	
	@Before
	public void before() {
		c0 = new City(300,400);
		c1 = new City(700,700);
		c2 = new City(200,700);
		c3 = new City(400,600);
		c4 = new City(350,800);
		v0 = new Visit(c0);
		s0 = new State(v0,null,0);
	}
	
	@After
	public void after() {
		City.listCity = new ArrayList<City>();
	}
	
	
	/*@Test
	public void testGoTo() {
		Visit v1 = v0.goTo(c2);
		Visit v2 = v1.goTo(c1);
		Visit v3 = v2.goTo(c2);
		Visit v4 = v2.goTo(c0);
		Visit v5 = v2.goTo(c3);
		Visit v6 = v5.goTo(c0);
		assertEquals(v0.toString(),"(5.0,5.0) [(5.0,5.0), (3.0,3.0), (5.0,2.0), (4.0,6.0)]");
		assertEquals(v1.toString(),"(5.0,2.0) [(5.0,5.0), (3.0,3.0), (4.0,6.0)]");
		assertEquals(v2.toString(),"(3.0,3.0) [(5.0,5.0), (4.0,6.0)]");
		assertEquals(v3,null);
		assertEquals(v4,null);
		assertEquals(v5.toString(),"(4.0,6.0) [(5.0,5.0)]");
		assertEquals(v6.toString(),"(5.0,5.0) []");
	}
	
	@Test
	public void testExpand() {
		ArrayList<State> succ0 = s0.expand();
		ArrayList<State> succ1 = new ArrayList<State>();
		Visit v1 = v0.goTo(c1);
		State s1 = new State(v1,s0,c0.distance(c1));
		Visit v2 = v0.goTo(c2);
		State s2 = new State(v2,s0,c0.distance(c2));
		Visit v3 = v0.goTo(c3);
		State s3 = new State(v3,s0,c0.distance(c3));
		succ1.add(s1);
		succ1.add(s2);
		succ1.add(s3);
		assertEquals(succ0,succ1);
	}*/
	
	@Test
	public void testHeuristic() {
		double h0 = v0.heuristic();
		//Construction de l'arbre couvrant minimal avec geogebra
		double h1 = c0.distance(c3) + c0.distance(c1) + c1.distance(c2);
		assertEquals((Double) h0, (Double) h1);
	}
	
	@Test
	public void testAStar() {
		ArrayList<State> solution0 = State.AStar();
		System.out.println(solution0);
		ArrayList<State> solution1 = new ArrayList<State>();
		assertEquals(solution0,solution1);
	}
	

	
}
