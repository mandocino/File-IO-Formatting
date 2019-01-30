/**
 * Custom Exception class for invalid files containing blanks. The custom error is sent through the constructor via string parameter.
 * @author Armando Mancino (40078466)
 *
 */
class FileInvalidException extends Exception {
	protected String error;
	public FileInvalidException()
	{
		super("Error: Detected Empty Field!");
	}

	public FileInvalidException(String s) 
	{
		this.error=s;
	}
	public String getMessage() 
	{
		return super.getMessage();
	}
}
