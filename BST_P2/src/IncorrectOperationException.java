
public class IncorrectOperationException extends IllegalArgumentException {

	public IncorrectOperationException(String m){
		super(m);
		System.out.println("\n _________________________________________________________________ \n"
				+ m + " is not an allowed operation. Only insert integer, remove integer,"
						+ "\nprint_tree and inorder_list are allowed. \n "
						+ "_________________________________________________________________");
	}
	
}
