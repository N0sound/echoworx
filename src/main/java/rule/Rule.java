package rule;

public interface Rule {

	public boolean isValid(String messsage);
	
	public String apply(String message);
}
