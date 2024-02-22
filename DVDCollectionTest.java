import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

import org.junit.Test;


public class DVDCollectionTest {
	private DVDCollection myDVDs = new DVDCollection();
	
	@Test
	public void testGetTotalRunningTime() {
		
		myDVDs.addOrModifyDVD("Meow", "R", "190");
		myDVDs.addOrModifyDVD("Best", "G", "165");
		myDVDs.addOrModifyDVD("UWU", "PG", "170");
		assertEquals(myDVDs.getTotalRunningTime(), 525);
	}
	
	@Test
	public void testAddOrModifyDVD() {
	
		myDVDs.addOrModifyDVD("Meow", "R", "190");
		myDVDs.addOrModifyDVD("Best", "G", "165");
		myDVDs.addOrModifyDVD("UWU", "PG", "170");
		//testing to see if the dvds were added in the correct order title wise
		assertTrue(myDVDs.dvdArray[1].getTitle().equals("MEOW"));
		assertTrue(myDVDs.dvdArray[0].getTitle().equals("BEST"));
		assertTrue(myDVDs.dvdArray[2].getTitle().equals("UWU"));
		//testing to see if the ratings were added correctly
		assertTrue(myDVDs.dvdArray[1].getRating().equals("R"));
		assertTrue(myDVDs.dvdArray[0].getRating().equals("G"));
		assertTrue(myDVDs.dvdArray[2].getRating().equals("PG"));
		//testing to see if the RunningTimes were added correctly
		assertTrue(myDVDs.dvdArray[1].getRunningTime() == 190);
		assertTrue(myDVDs.dvdArray[0].getRunningTime() == 165);
		assertTrue(myDVDs.dvdArray[2].getRunningTime() == 170);
		//now testing to see if modifying a DVD works
		//This should be modifying the first DVD, because the title matches the first DVD. 
		myDVDs.addOrModifyDVD("MEOW", "PG", "225");
		//testing to see if the rating and runtime were updated. 
		assertTrue(myDVDs.dvdArray[1].getRating() == "PG");
		assertTrue(myDVDs.dvdArray[1].getRunningTime() == 225);
	}


	@Test
	public void testToString() {
		//make sure there's something in the to string and it's not empty
		myDVDs.addOrModifyDVD("Meow", "R", "190");
		myDVDs.addOrModifyDVD("Best", "G", "165");
		myDVDs.addOrModifyDVD("UWU", "PG", "170");
		String newString = myDVDs.toString();
		assertFalse(newString.equals(""));
	}
	
	@Test
	public void testGetDVDsByRating() {
		//,ale sire that the function found the one R rated movie 
		myDVDs.addOrModifyDVD("Meow", "R", "190");
		myDVDs.addOrModifyDVD("Best", "G", "165");
		myDVDs.addOrModifyDVD("UWU", "PG", "170");
		assertEquals(myDVDs.getDVDsByRating("R"), myDVDs.dvdArray[1].toString());
	}



	@Test
	public void testRemoveDVD() {
		//make sure that the third dvd got deleted
		myDVDs.addOrModifyDVD("Meow", "R", "190");
		myDVDs.addOrModifyDVD("Best", "G", "165");
		myDVDs.addOrModifyDVD("UWU", "PG", "170");
		myDVDs.removeDVD("Best");
		assertNull(myDVDs.dvdArray[3]);
	}


}
