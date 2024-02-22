import java.util.*;

/**
 * 	Program to display and modify a simple DVD collection
 */

public class DVDManager {

	public static void main(String[] args) {
		
		DVDUserInterface dlInterface;
		DVDCollection dl = new DVDCollection();

		//Scanner scan = new Scanner(System.in);

		String filename = "dvddata.txt";			
		dl.loadData(filename);
		
		
		//because gui is the only option, we're removing console options.
		
		//System.out.println("Input interface type: C=Console, G=GUI");
		//String interfaceType = "G";
		// if (interfaceType.equals("G")) {
			dlInterface = new DVDGUI(dl);
			dlInterface.processCommands();
		//} else {
		//	System.out.println("Unrecognized interface type. Program exiting.");
		//	System.exit(0);
		//}
		
	}

}
