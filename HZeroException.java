public class HZeroException extends Exception
{
	private String error;
	
	public HZeroException()
	{
		super();
		this.error = "unknown";
	}
	
	public HZeroException(String error)
	{
		super(error);
		this.error = error;
	}
	
	public String getError()
	{
		return error;
	}
}