
public class IncorrectOperationException extends IllegalArgumentException{

		public IncorrectOperationException(String m){
			super(m);
			System.out.println("\n _________________________________________________________________ \n"
					+ m + " is not an allowed operation. Only add, subtract, and multiply is "
							+ "\n allowed. Third line must contain proper operation. \n "
							+ "_________________________________________________________________");
		}
}
