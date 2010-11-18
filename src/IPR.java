import java.util.*;

/**
 * 
 * @author Team E (Feras AlJumah, Naif AlQahtani)
 * IPR class prints out the Menu with the version of the program and points of contacts
 * Then prompt the user for path for the xml file to be saved
 * and Degree Root integer and Prime.
 */
public class IPR
{
/**
 * integer DegreeRoot must be greater than 1
 */
private int DegreeRoot;
/**
 * Prime number [double]
 */
private double Prime;
/**
 * Path to save the XML file [and HTML file]
 */
private String XmlFilePath;

/**
 * Points of Contacts
 */
private Contacts ContactsLst;
/**
 * Version of the Program, initially it is 1.0.1
 */
private String Version;

//GETTERS
public int getDegreeRoot()
{
	return this.DegreeRoot;
}

public double getPrime()
{
	return this.Prime;
}

public String getXmlFilePath()
{
	return this.XmlFilePath;
}

// SETTERS
/**
 * @see #DegreeRoot
 */
private void setDegreeRoot(int DegreeRoot)
{
	this.DegreeRoot = DegreeRoot;
}

/**
 * 
 * @see #Prime
 */
private void setPrime(double Prime)
{
	this.Prime = Prime;
}

/**
 * registers the path of the output files
 * @param XmlFilePath a desired path for the XML to be saved.
 * @see #XmlFilePath
 */
private void setXmlFilePath(String XmlFilePath)
{
	this.XmlFilePath = XmlFilePath;
}


public IPR() 
{
	ContactsLst = new Contacts();
	// program version
	Version = "1.0.1";
	
	// contacts to report bugs or suggestions
	ContactsLst.addContact("Feras AlJumah", "cre@tivity.info");
	ContactsLst.addContact("Naif AlQahtani", "NaifAlQahtani@hotmail.com");
	
	// Show Version of the IPR and Point of Contacts
	printMenuHeader();
}
// a format version of the menu
private void printMenuHeader()
{
	
	System.out.println("******************************************");
	System.out.println("Version: " + this.Version);
	this.ContactsLst.printContacts();
	System.out.println("******************************************");
	
}

/**
 * Prompts the user for file path, integral degree root, and a prime number
 * then it checks if the inputs are entered as expected in form of a string, integer
 * and double 
 * @return a flag whether the values have been read successfully or not.
 */
public boolean readUserInputs()
{
	 try {
		 	Scanner scanner = new Scanner(System.in);
		 	
		 	System.out.println("Please enter an xml file path.. ");
		 	this.setXmlFilePath(scanner.nextLine());
		 	
		 	System.out.println("Please enter a prime number... ");
		 	this.setPrime(Double.parseDouble(scanner.nextLine()));
		 	
		 	System.out.println("Please enter the root value... ");
		 	this.setDegreeRoot(Integer.parseInt(scanner.nextLine()));
		 	
			return true;
			
      } catch (Exception e) {
    	  
    	  	//System.out.println("IO error reading your inputs!");
    	  	return false;
      }


}

	
}
