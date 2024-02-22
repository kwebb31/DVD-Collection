import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DVDGUI  implements DVDUserInterface {
	 
	 private DVDCollection dvdlist;
	 
	 public DVDGUI(DVDCollection dl)
	 {
		 dvdlist = dl;
		 String[] fileNames; 
	 }
	 
	 
	 
	 private static void createWindow() {
		 JFrame frame = new JFrame("GUI");
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 createUI(frame);
		 frame.setSize(560, 200);
		 frame.setLocationRelativeTo(null); // Center on screen
		 frame.setVisible(true); // make visible
		 }
	 private static void createUI(final JFrame frame){
		 JPanel panel = new JPanel();
		 //LayoutManager layout = new FlowLayout(); // comment out: To try alternate layouts
		 LayoutManager layout = new GridLayout(); // uncomment: To try alternate layouts
		 panel.setLayout(layout);
		 JTextField textField = new JTextField(20); // create a textfield
		 JButton okButton = new JButton("Ok");
		 JButton exitButton = new JButton("Exit");
		 JButton cancelButton = new JButton("Cancel");
		 //cancelButton.setEnabled(true);
		 cancelButton.setEnabled(false);
		 JButton submitButton = new JButton("Submit");
		 okButton.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
		     JOptionPane.showMessageDialog(frame, "Ok Button clicked. Cancel Enabled");
		     cancelButton.setEnabled(true);
		   }
		 });
		 }
	 public void processCommands()
	 {
		 //modify DVD and Select a New File to Load added here as option buttons
		 String[] commands = {"Add DVD",
				 	"Modify DVD",
				 	"Remove DVD",
				 	"Get DVDs By Rating",
				 	"Get Total Running Time",
				 	"Select a New File to Load",
				 	"Exit and Save"};
		 
		 int choice;
		 
		 do {
			 choice = JOptionPane.showOptionDialog(null,
					 "Select a command. Current DVDs and Array Information: \n" + dvdlist.toString(), 
					 "DVD Collection", 
					 JOptionPane.YES_NO_CANCEL_OPTION, 
					 JOptionPane.QUESTION_MESSAGE, 
					 null, 
					 commands,
					 commands[commands.length - 1]);
		 //commands are added here, break is required or the commands will all be chosen
			 switch (choice) {
			 	case 0: doAddOrModifyDVD(); break;
			 	case 1: doModifyDVD(); break;
			 	case 2: doRemoveDVD(); break;
			 	case 3: doGetDVDsByRating(); break;
			 	case 4: doGetTotalRunningTime(); break;
			 	case 5: doAddFile(); break;
			 	case 6:	doSave(); break;
			 	default:  // do nothing
			 }
			 
		 } while (choice != commands.length-1);
		 System.exit(0);
	 }

	private void doAddFile(){
	
		//try to take a filename from the user and attempt to call loadData with it
		try {
			String filename = JOptionPane.showInputDialog("Enter the name of an additional file to add");
			dvdlist.loadData(filename);}
		catch(Exception e){
			
			JOptionPane.showMessageDialog(null, "Something went wrong, the file could not be found.");
		}
	}
	private void doModifyDVD() {
	   String input; 
	   String FullDVD = "";
	   String rating = "";
	  //gives it a default image so the IDE won't yell at me :) 
	   ImageIcon image = new ImageIcon(getClass().getResource("GenericDVD.jpg"));
	   
	   String[] options = new String[dvdlist.numdvds];
	  //get the titles of all the dvds to display them in the dropdown
	    for(int i = 0; i < dvdlist.numdvds; i++) {
	    	options[i] = dvdlist.dvdArray[i].getTitle();	
	    }
			 input = (String) JOptionPane.showInputDialog(null, "Please choose a dvd to modify",
			    		//give a drop down list of options to modify, with the first one presented being the first DVD available
			    		"Modify DVD", JOptionPane.QUESTION_MESSAGE, null, options, options[0]
			        		);
			 if(input == null) {
				 return;
			 }
			/*for each item in the array, call to string to make an individual string
			 * try to make the image's filename input.jpg, but if that fails (the image has not been
			  added, for example), just make the image a generic clip art of a DVD.*/
			 for(int j = 0; j < dvdlist.numdvds; j ++) {
			    	
				 if(dvdlist.dvdArray[j].toString().contains(input)) {
					FullDVD = dvdlist.dvdArray[j].toString();
				try {
					image = new ImageIcon(getClass().getResource(input + ".jpg"));}
				catch(Exception e) {
					image = new ImageIcon(getClass().getResource("GenericDVD.jpg"));
				}
			    	
			    }
			 }
			 
			 
			// Request the rating
			 rating = (String) JOptionPane.showInputDialog(null, image, "Enter rating for " + FullDVD , JOptionPane.QUESTION_MESSAGE);
			if (rating == null) {
				// dialog was cancelled
				return;}
	
			// Request the running time
			String time = JOptionPane.showInputDialog(null, image, "Enter running time for " + FullDVD, JOptionPane.QUESTION_MESSAGE);
				if (time == null) {
					return;
				}
		//if both rating and time have values, go ahead and call addorModifyDVD.		
		if(rating.isEmpty() == false && time.isEmpty() == false) {
			dvdlist.addOrModifyDVD(input, rating, time);
			JOptionPane.showMessageDialog(null,  "Modifying: " + input +", " + rating + ","  + time);
		}
		//otherwise, give a nice lil error message and don't try to add it.
		else {
			JOptionPane.showMessageDialog(null, "Something went wrong, " + input + " could not be modified.");
		}
	}


	private void doAddOrModifyDVD() {
	
		// Request the title
		String title = JOptionPane.showInputDialog("Enter title");
		if (title == null) {
			return;		// dialog was cancelled
		}
		title = title.toUpperCase();
		
		
		//run the following code as long as there's something in the Title string (it's not empty). 
		//otherwise, give an error message, because there's no point running through this code. 
		if(title.isEmpty() == false) {
		// Request the rating
			String rating = JOptionPane.showInputDialog("Enter rating for " + title);
			if (rating == null) {
				return;		// dialog was cancelled
			}
			rating = rating.toUpperCase();
		
		// Request the running time
			String time = JOptionPane.showInputDialog("Enter running time for " + title);
			if (time == null){
				return;
			}
			
			if( title.isEmpty() == false && rating.isEmpty() == false && time.isEmpty()== false ) {
                // Add or modify the DVD (assuming the rating and time are valid
                	dvdlist.addOrModifyDVD(title, rating, time);
                
                // Display current collection to the console for debugging
                	JOptionPane.showMessageDialog(null, "Adding: " + title + "," + rating + "," + time + "\n" + dvdlist);}
			else {
				JOptionPane.showMessageDialog(null, "Something went wrong. Please enter a title, rating, and time to create a new entry.");
				}
		}
		else {
			JOptionPane.showMessageDialog(null, "Something went wrong. Failed to add DVD");
		}
		
	}

	private void doRemoveDVD() {

		
		// Request the title
		String title = JOptionPane.showInputDialog("Enter title");
		if (title == null) {
			return;		// dialog was cancelled
		}
		title = title.toUpperCase();
		
                // Remove the matching DVD if found
                dvdlist.removeDVD(title);
                
                // Display current collection to the console for debugging
                
                JOptionPane.showMessageDialog(null, "Current DVDS: \n" +dvdlist);

	}
	
	private void doGetDVDsByRating() {
		
		// Request the rating
		String rating = JOptionPane.showInputDialog("Enter rating");
		if (rating == null) {
			return;		// dialog was cancelled
		}
		rating = rating.toUpperCase();
		
                String results = dvdlist.getDVDsByRating(rating);
                JOptionPane.showMessageDialog(null,"DVDs with rating " + rating + "\n" + results);
                
	}

        private void doGetTotalRunningTime() {
            //tells the user their total runtime after calling getTotalRunningTime()     
                int total = dvdlist.getTotalRunningTime();
                JOptionPane.showMessageDialog(null,"Total Running Time of DVDs: " + total + " minutes.");
        }

	private void doSave() {
		
		dvdlist.save();
		JOptionPane.showMessageDialog(null, "Your information has been saved.");
		
	}
		
}
