public class DVD {

	// Fields:

	private String title;		// Title of this DVD
	private String rating;		// Rating of this DVD
	private int runningTime;	// Running time of this DVD in minutes

	public DVD(String dvdTitle, String dvdRating, int dvdRunningTime) 
	{
		this.title = dvdTitle;
		this.rating = dvdRating;
		this.runningTime = dvdRunningTime;

	
	}
	
	public String getTitle() 
	{
	


		return title;	//returns the title when called
	}
	
	public String getRating() 
	{



		return rating;	// returns rating when called (shocker!)
	}
	
	public int getRunningTime() 
	{



		return runningTime;	// returns runningTime.
	}

	public void setTitle(String newTitle) {


		title = newTitle; //sets the title of the DVD object to the new, updated title


	}

	public void setRating(String newRating) {


		rating = newRating;//sets the rating of the DVD object to the new, updated rating


	}

	public void setRunningTime(int newRunningTime) {


		runningTime = newRunningTime;//sets the rating of the DVD object to an updated running time


	}

	public String toString() {
		//generates a string by taking title, rating, and running time and concatenating them, along with some punctuation. 
		String myStr = (title +", "+ rating+ ", " + runningTime + "\n") ;
		//return the string for processing/use in other places
		return myStr;	
	}
	
	
}
