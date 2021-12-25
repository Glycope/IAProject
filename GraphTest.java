import java.io.IOException;
import java.nio.file.Files;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GraphTest {
	
	
	@Before
	public void before() throws IOException {
		new City(5,5);
		new City(3,3);
		new City(8,12);
		new City(10,15);
	}
	
	@Test
	public void testGoTo() {
		Visit v1 = new Visit(City.listCity.get(0));
		Visit v2 = v1.goTo(City.listCity.get(2));
		Visit v3 = v2.goTo(City.listCity.get(1));
		Visit v4 = v3.goTo(City.listCity.get(2));
		assertEquals(v1.toString(),"(5.0,5.0) {(5.0,5.0), (3.0,3.0), (8.0,12.0), (10.0,15.0)}");
		assertEquals(v2.toString(),"(8.0,12.0) {(5.0,5.0), (3.0,3.0), (10.0,15.0)}");
		assertEquals(v3.toString(),"(3.0,3.0) {(5.0,5.0), (10.0,15.0)}");
		assertEquals(v4,null);
	}
	
	
	
}
