import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.AfterClass;
//import org.junit.jupiter.api.Test;

public class DVDTest {
	DVD dvd1 = new DVD("Meow", "R" , 190);
	@Test
	public void testDVD() {
	       
	       //testing that the default constructor works 
	        assertEquals(dvd1.getTitle(),"Meow");
	        assertEquals(dvd1.getRating(),"R");
	        assertEquals(dvd1.getRunningTime(), 190);
	}



	@Test
	public void testSetTitle() {
		//seeing if the test updates correctly
		dvd1.setTitle("Creative");
		assertTrue(dvd1.getTitle().equals("Creative"));
	}

	 
	@Test
	public void testSetRating() {
		//testing the Rating 
		dvd1.setRating("G");
		assertEquals(dvd1.getRating(),"G");
	}


	
	@Test
	public void testSetRunningTime() {
		dvd1.setRunningTime(165);
		assertTrue(dvd1.getRunningTime() == 165);
	}

	@Test
	public void testToString() {
	
		
		assertEquals(dvd1.toString(),"Meow, R, 190\n");
	}



}
