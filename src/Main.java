import java.io.IOException;
/**
 * 
 * This class represents the User Interface [Console] for the program
 * more precisely works as an abstract class
 *
 */
public class Main {
	static IPRxml _IPRxml;
	static IPR _IPR;

	
	public static void main(String[] args) throws IOException {
		
		LoadConsole();
		System.out.println("done!");
	}
	/**
	 * Checks user inputs for validation and write
	 * outputs XML file
	 */
	private static void LoadConsole()
	{
		_IPR= new IPR();
		if (ValidateInputs(_IPR.readUserInputs()))
		{
			PerformCalculations();
		}
		else
		{
			LoadConsole();	
		}
		try
		{
			WriteFiles();
		}
		
		catch (IPReXception e)
		{
			System.out.println(e.errorMessage);
		}	
	}
	
	/**
	 * 
	 * @param Validates the User Inputs from a format point of view and also
	 * validates against constraints such as DegreeRoot must be greater than1.
	 * 
	 */
	private static boolean ValidateInputs(boolean isValid)
	{
		if (isValid)
		{
			try
			{
				checkDegreeRoot(_IPR.getDegreeRoot());
				_IPRxml = new IPRxml(_IPR.getXmlFilePath(), _IPR.getPrime(),
						_IPR.getDegreeRoot());
				return true;
			}
			catch (IPReXception e)
			{
				System.out.println(e.errorMessage);
				return false;
			}
		} 
		else
		{
			return false;
		}
	}
	
	/**
	 * Perform Calculations Based on the User Inputs
	 */
	private static void PerformCalculations()
	{
		double root,initial=1,prime;
		int DegreeRoot;
		prime = _IPR.getPrime();
		DegreeRoot = _IPR.getDegreeRoot();
		if(DegreeRoot==2)
		{
			root= getTheRoot(prime,DegreeRoot,initial);
			if(isprime(root, prime))
			WriteToXML("the "+ DegreeRoot +"th root of the prime number: " + prime + " is " + root);
			else
			WriteToXML("the "+ DegreeRoot +"th root of the number: " + prime + " is " + root + ".");	
		}
		else
		{
			root= getTheRoot(prime,2,initial);
			if(isprime(root, prime))
			{
				root= getTheRoot(prime,DegreeRoot,initial);
				WriteToXML("the "+ DegreeRoot +"th root of the prime number: " + prime + " is " + root);
			}
		}	
	}
	
	/**
	 * This function checks for primality utilizing
	 * isPrime Class functions to maximum
	 * 
	 */
	private static boolean isprime(double root, double prime) {
		
		Prime _Prime = new Prime();
		_Prime.setRoot(root);
		
		if(!_Prime.isPrime(prime))
		{
		    WriteToXML("The number " +prime +
		    		" was not found to be prime because it has the factor: "+
		    		_Prime.getFactor());
		    return false;
		    
		    //throw new IPReXception("The number " +prime +
		    		//" was not found to be prime because it has the factor: "+
		    		//_Prime.getFactor());
		    //return false;
		}
		return true;
	}


	/** Get Root Function
	 */
	private static double getTheRoot(double prime, double n, double initial) {
	
		GetRoot _GetRoot = new GetRoot();
		/*if(prime<1000)initial=1;
		else if(prime>10000&&prime<1000000)initial=100;
		else initial=1000;
		_GetRoot.setInitialValue(initial);*/
		initial = _GetRoot.Calculate(prime,n);
		WriteToXML("There were "+_GetRoot.getIterations()+" iterations of Newton's Method for the "+n+"th root of " + prime);
		return initial;
	}
	/**
	 * Before the program terminates, a call is made to this function
	 * to write the Output XML file and the HTML file [using XSLT transformation see IPRxml.xsl]
	 * @throws IPReXception
	 */
	private static void  WriteFiles() throws IPReXception
	{
		if (!(_IPRxml.WriteFile(1))|| !(_IPRxml.WriteFile(2)))
		{
			throw new IPReXception("!!error writing output files [xml, html]!!");
		}
	}
	/**
	 * throughout the duration of the program, call this function
	 * to append elements to the XML document
	 * @param Text represents the text for the of the XML element
	 */
	private static void WriteToXML(String Text)
	{
		_IPRxml.AddOutputNodes(Text);
	}
	/**
	 * checks whether the degree root is greater than 1 or not, if not then
	 * throw an IPReXception with a friendly message
	 * @param DegreeRoot
	 * @throws IPReXception
	 */
	private static void checkDegreeRoot(int DegreeRoot) throws IPReXception
	{
		if (DegreeRoot < 2)
		{
			throw new IPReXception("Integral Degree Root must be greater than 1.");
		}
	}
	
		
}
