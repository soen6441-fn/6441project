import java.util.*;
//------------------------------------------------------------
/**Public class Contacts to store point of contacts [developers] of the program
 * The reason of existence for this class is to allow the user to send/report 
 * suggestions and bugs and therefore enhance usability and maintainability
 */


public class Contacts {
/**
 * Vector to dynamically populate the list of contacts
 */
private Vector<String> contactsList;


/**
 * Default constructor, initially not implemented at this stage as there was no use to instantiate
 * any variables [ maybe in the future updates ]
 */
public Contacts()
{
	contactsList =new Vector<String>();
}
/**
 * append new contact of the IPR
 * @param name represents the name of contact
 * @param email represents the email address of the contact
 * in the form <name, email>
 * @see #contactsList
 */
public void addContact(String name, String email)
{
	contactsList.add(name + "," + email);
}

/**
 * flush the contacts details [name, email] to the console
 * to be called from printMenu() function in the IPR class
 */
public void printContacts()
{
	for (int index=0; index< contactsList.size(); index++)
	{
		System.out.println(contactsList.get(index));
	}
}

}
