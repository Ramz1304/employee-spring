package employee.Service;

public class ApiException extends Exception {

	private static final long serialVersionUID = 1L;
    public ApiException(String string) {
    	super(string);
    }
}