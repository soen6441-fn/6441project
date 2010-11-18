/**
 * Custom exception class inherit from Java general class Exception
 */
class IPReXception extends Exception
{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String errorMessage;

 /**
 * Default constructor to initialize the error message
 */

  public IPReXception()
  {
    super();  
    this.errorMessage = "hopefully none? <== unknown error occured if you get this message.";
  }
  

/**
 * if, in the constructor, the error message is explicit then assign it to error
 */

  public IPReXception(String errorMessage)
  {
    super(errorMessage);     // call super class constructor
    this.errorMessage = errorMessage; 
  }
  

/**
 * public function to get error message
 * @return the error message as to what error has occurred or what might be causing it.
 */

  public String getExceptionError()
  {
    return errorMessage;
  }
}
  