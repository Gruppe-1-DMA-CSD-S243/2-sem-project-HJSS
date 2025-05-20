package controller;

/**
 * Thrown to indicate an invalid or illegal time registration.
 * 
 * @author Sebastian Nørlund Nielsen
 */
@SuppressWarnings("serial")
public class IllegalTimeRegistrationException extends Exception{
	
	/**
     * Constructs the exception with a specific message.
     *
     * @param message explanation of the error
     */
	public IllegalTimeRegistrationException( String e ) { 
		super(e); 
	}
}
