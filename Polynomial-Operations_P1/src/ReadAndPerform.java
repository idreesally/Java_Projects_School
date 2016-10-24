import java.util.StringTokenizer;

public class ReadAndPerform {
	
	protected String firstExpression;
	protected String secondExpression;
	protected String operation;
	
	public static DoublyLinkedList<Term> terms1 = new DoublyLinkedList<Term>();
	public static DoublyLinkedList<Term> terms2 = new DoublyLinkedList<Term>();
	
	public static Polynomial polynomial1;
	public static Polynomial polynomial2;
	
	public ReadAndPerform(String lineA, String lineB, String lineC){
		firstExpression = lineA;
		secondExpression = lineB;
		operation = lineC;
		operateAndPrint(); //calls this method to perform operation and print expressions and solution
	}

	private void operateAndPrint() {
		
		terms1 = makeList(firstExpression); //list holds terms of first expression
		polynomial1 = new Polynomial(terms1);//polynomial made using list
		
		terms2 = makeList(secondExpression); //list holds terms of second expression
		polynomial2 = new Polynomial(terms2);//polynomial made using list

		print();
	}
	
	//uses String Tokenizer to create Terms and adds Terms to appropriate list
	private DoublyLinkedList<Term> makeList(String exp) {
		StringTokenizer s = new StringTokenizer(exp," ");
		DoublyLinkedList<Term> temp = new DoublyLinkedList<Term>();
		while(s.hasMoreTokens()){
			double co = 0.0;
			int ex = 0;
			try{
				co = Double.parseDouble(s.nextToken());
				ex= Integer.parseInt(s.nextToken());
				Term t = new Term(co,ex);
				temp.add(t);
			} catch (NumberFormatException nfe){ //catches incorrect input for coefficient or exponent
				
				System.out.println("Your line input should include a coefficient of type DOUBLE followed by an exponent of type INT. "
					+ "\nThis was not the case. \n"
					+ "Program will now TERMINATE.");
				System.exit(0);	
			}	
		}
		return temp;
	}
	
	private void print(){
		if(operation.equalsIgnoreCase("add")){
			System.out.println(polynomial1 + "\n" + "+ \n" + polynomial2 + "\n" + "=");
				polynomial1.add(polynomial2);
				System.out.println(polynomial1 + "\n ______________________________________________ \n");
		}
		else if(operation.equalsIgnoreCase("subtract")){
			System.out.println(polynomial1 + "\n" + "- \n" + polynomial2 + "\n" + "=");
			polynomial1.subtract(polynomial2);
			System.out.println(polynomial1 + "\n ______________________________________________ \n");
		}
		else if(operation.equalsIgnoreCase("multiply")){
			System.out.println(polynomial1 + "\n" + "* \n" + polynomial2 + "\n" + "=");
			polynomial1.multiply(polynomial2);
			System.out.println(polynomial1 + "\n ______________________________________________ \n");
		}
		else{ 
			throw new IncorrectOperationException(operation); //throw exception if operation is not acceptable
		}
		
		//clear lists to be reused 
		terms1.clear();
		terms2.clear();
	}
	
}
