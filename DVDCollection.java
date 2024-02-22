import java.io.*;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class DVDCollection {

	// Data fields
	
	/** The current number of DVDs in the array */
    protected int numdvds;
	/** The counting variable I'll compare my array against to determine if I need to double its size.*/
	private int count=7;
	/** The array to contain the DVDs */
	protected DVD[] dvdArray;
	
	/** The name of the data file that contains dvd data */
	private String sourceName;
	
	/** Boolean flag to indicate whether the DVD collection was
	    modified since it was last saved. */
	private boolean modified;
	
	/**
	 *  Constructs an empty directory as an array
	 *  with an initial capacity of 7. When we try to
	 *  insert into a full array, we will double the size of
	 *  the array first.
	 */
	public DVDCollection() {
		numdvds = 0;
	    dvdArray = new DVD[7];	

	}
	
	public String toString() {
		// Return a string containing all the DVDs in the
		// order they are stored in the array along with
		// the values for numdvds and the length of the array.
		
		String arrayTotalString = "";
		String arrayItem = "";
		String iterator = "" ;
		//for each item in the array, concatenate "dvdArray[i]: title, rating, time"
		for(int i = 0; i < numdvds; i ++) {
			
			arrayItem = dvdArray[i].toString();
			iterator = Integer.toString(i);
			arrayTotalString = arrayTotalString.concat("dvdArray[" +  iterator + "] = " + arrayItem) ;
			
			
		}
		
		//full final string
		String theString = "numDVDS: " +  numdvds + "\n" + 
		"dvdArray.length: " + dvdArray.length + "\n" + arrayTotalString;
		

		// return the full string for people to print
		return theString;	
	}

	public void addOrModifyDVD(String title, String rating, String runningTime) {
		// NOTE: Be careful. Running time is a string here
		// since the user might enter non-digits when prompted.
		// If the array is full and a new DVD needs to be added,
		// double the size of the array first.
		
		
		/*apparently, the file input was not converting correctly if it was lowercase,
		so I'm just making both title and rating uppercase upon entering the file*/
		title = title.toUpperCase();
		rating = rating.toUpperCase();
		
		
		//execute this code if the number of items in the array is equal to the number of spaces we have in the array. 
		if(numdvds+1 == count) {
			//double the comparison number so we know to double the array if we use more than the next max capacity
			count = count * 2;
			
			// new array of DVD objects that will hold the previous array's information while we double the size of the array
			DVD[] copyArray = new DVD[count];
			for(int i = 0; i < numdvds; i++) {
			
				copyArray[i] = new DVD(dvdArray[i].getTitle(), dvdArray[i].getRating(), dvdArray[i].getRunningTime());
			}
			
			//generates a dvdArray with the count variable instead of the 7 
			dvdArray = new DVD[count];
			//loop that copies the information into the dvdArray from the copyArray
			for(int i = 0; i < numdvds; i++) {
				dvdArray[i] = new DVD(copyArray[i].getTitle(), copyArray[i].getRating(), copyArray[i].getRunningTime());
			}
		}
		
		//point points to the item in the array I'm currently evaluating
		//to see if our item needs to be inserted here. 
		int point = 0;
		
		//type casting so that the running time will be accepted by the DVD class. 
		int runTime = Integer.valueOf(runningTime);
		

		
		//if there aren't any items in the array, put our item in in the first slot. 
		if(numdvds == 0) {
			
			dvdArray[0] = new DVD( title, rating, runTime);
			
			numdvds++;
			
		}
			
			//the current item in the array is the 
		else {
			String current = dvdArray[point].getTitle();
			 
			//Compare the title we're currently looking at in the array to the title we're trying to enter 
			int outcome = current.compareTo(title);
			
		
			 //if the outcome is less than zero, that means that the array item we're looking at is 
			//further in the alphabet than the title we're working with.
			//this loops keeps looking until we've run through the array or we're out of items in the array to look at. 
			while(outcome  < 0 && point < (numdvds - 1)) {
				point++;
				current = dvdArray[point].getTitle(); 
				outcome = current.compareTo(title);
			
			}
			 if(outcome > 0) {
				//working backwards from one index larger than our current array, pull the last item in the array into the slot after it 	
				for(int i = numdvds; i > point; i--) {
					dvdArray[i] = new DVD(dvdArray[i-1].getTitle(), dvdArray[i-1].getRating(), dvdArray[i-1].getRunningTime());
			
					 	
					}
				 	//put the old item into the next array slot and put the new item in the current array slot, and then increment numdvds.
					dvdArray[point + 1] = new DVD(dvdArray[point].getTitle(), dvdArray[point].getRating(), dvdArray[point].getRunningTime());
				
					
				 	dvdArray[point] = new DVD(title, rating, runTime);
				
					numdvds++ ;
					

			}
			 
			 //if outcome = 0, the titles are the same. This means we should update the rating and the runTime currently existing for this title.
			 else if(outcome == 0) {
				
				 dvdArray[point].setRating(rating);
				 dvdArray[point].setRunningTime(runTime);
			 }
			 //if we reached the end of the array and didn't find our item, stick it at the end. 
			 else if(point == numdvds-1) {
			
				 dvdArray[point+1] = new DVD(title, rating, runTime);
				 
				 numdvds++ ;
			 }
	
		}
	
	modified = true;}
	public void removeDVD(String title) {
		
		int point = 0;
		//getting the title of each dvd to see if this is the one we need to remove.
		String current = dvdArray[point].getTitle();
		 
		//Compare the title we're currently looking at in the array to the title we're trying to enter 
		int outcome = current.compareTo(title);
		
		//as long as outcome is negative (meaning that our current array item is 
		//further back in the alphabet than our newest title), keep looking. 
		while(outcome < 0 && point < numdvds) {
		//increment point so that we'll keep moving forward in the array
			point++;
		//set a new current and do the compareTo again to get a new integer result.	
			current = dvdArray[point].getTitle(); 
			outcome = current.compareTo(title);
		}
		
		if(outcome == 0) {
			//move each item in the array back one, overwriting the deleted entry.
			for(int i = point; i < numdvds-1 ; i++) {
				dvdArray[i] = new DVD(dvdArray[i+1].getTitle(), dvdArray[i+1].getRating(), dvdArray[i+1].getRunningTime());
			 
			}
			
			//reset the final item in the array as if it'd never been set to anything; if we didn't do this we could lose  
			dvdArray[numdvds - 1] = new DVD(null, null, 0);
			numdvds--;
			JOptionPane.showMessageDialog(null,"Removing: " + title);
			modified = true;
		}
		//if for some reason we don't ever find the item, tell the user the item wasn't deleted. 
		else {
			JOptionPane.showMessageDialog(null,"Failed to delete item. Please try again.");
		}



	}
	
	public String getDVDsByRating(String rating) {
		String myStr = "";
		int outcome;
		String arrayItem;
		String check;
	//for loop to compare each item in dvdArray against 	
		for(int i = 0; i < numdvds; i++) {
			//takes the current dvdArray object and creates a string of it, then compares it to rating. 
			check = String.valueOf(dvdArray[i].getRating());
			outcome = check.compareTo(rating); 
			if(outcome == 0 ){
			
				arrayItem = dvdArray[i].toString();
				
				myStr = myStr.concat(arrayItem);
			
			}
			
		}
	
		return myStr;
	}

	public int getTotalRunningTime() {
		//takes the runtime from each dvdArray item and adds it to the running total. 
		int runTimeTotal = 0;
		for(int i = 0; i < numdvds; i++) {
			runTimeTotal += dvdArray[i].getRunningTime();
		}
		

		return runTimeTotal;	// the total running time is returned for use here

	}

	
	public void loadData(String filename) {
	//the majority of this segment of code comes from Christopher Smith's 
	//supplementary code library. :)
		
		 
		 File myFile = new File(filename); //open the file
		 JOptionPane.showMessageDialog(null,"Attempting to open " + filename);
		 try {
			 if (myFile.createNewFile()) {
			       JOptionPane.showMessageDialog(null, "File created: " + myFile.getName());}
			 //take the contents and parse them to insert into our array. 
			 else { 	
				 Scanner scanner = new Scanner(myFile);
				while (scanner.hasNextLine()) {
				String data = scanner.nextLine();
				String parse[] = data.split(", ");
				addOrModifyDVD(parse[0], parse[1], parse[2]);
			
		}
			 
			scanner.close(); }
			}catch (Exception e) {
				System.out.println(e);
		}
		}
		

	
	public void save() {
		//because we are using dvddata for the initialization and saving of our program, list it by name. 
		sourceName = "dvddata.txt";
	//the majority of the code in this code block came from Christopher Smith's supplementary code. 
		if(modified == true) {
		
		try {
			// If a NEW file is created, add text to it
			FileWriter myWriter = new FileWriter(sourceName); 
			
			//use the tostring function to generate a string for each DVD object. Write said string to the file.
			for(int i = 0; i < numdvds; i++){
			myWriter.write(dvdArray[i].toString());
		//CLOSE THE WRITER!!!	
			}myWriter.close();
			
			
			
			} catch (IOException e1) {
				e1.printStackTrace();
									 }
	
			}
		else {System.out.println("No new data to save. Thank you for using the DVD collection program.");
			}
	}

}
