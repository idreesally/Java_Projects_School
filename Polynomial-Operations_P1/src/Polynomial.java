public class Polynomial {

	protected DoublyLinkedList<Term> expression;
	
	public Polynomial(){
		expression = new DoublyLinkedList<Term>();
	}
	
	public Polynomial(DoublyLinkedList<Term> ex){

		expression = ex;

	}

	public void add(Polynomial p){
		
		this.makeSameLength(p); //make two polynomials the same length to add vertically
		
		for(int i=0; i<expression.size(); i++){
			double tempCo = expression.get(i).getCoefficient() + p.expression.get(i).getCoefficient();
			int tempEx = expression.get(i).getExponent();
			Term tempTerm = new Term(tempCo,tempEx);
			expression.set(i, tempTerm);
		}
	}
	
	public void subtract(Polynomial p){
		
		this.makeSameLength(p); //make two polynomials the same length to subtract vertically
		
		for(int i=0; i<expression.size(); i++){
			double tempCo = expression.get(i).getCoefficient() - p.expression.get(i).getCoefficient();
			int tempEx = expression.get(i).getExponent();
			Term tempTerm = new Term(tempCo,tempEx);
			expression.set(i, tempTerm);
		}
	}
	
	public void multiply(Polynomial p){
		
		this.makeSameLength(p);
		
		DoublyLinkedList<Term> tempList = new DoublyLinkedList<Term>(); 
		
		for(int i=0; i<expression.size(); i++){
			for(int j=0; j<expression.size(); j++){
				double tempCo = expression.get(i).getCoefficient() * p.expression.get(j).getCoefficient();
				int tempEx = expression.get(i).getExponent() + p.expression.get(j).getExponent();
				//create new terms with tempCo and tempEx --> keep appending them to temp list
				Term tempTerm = new Term(tempCo,tempEx);
				tempList.add(tempTerm);
			}
		}
		
		expression = tempList;
		
		//remove terms with 0.0 coefficient
		this.removeEmptyTerms();
		
		//combine like terms
		this.combineLikeTerms();
		
		//sort terms
		this.sortTerms();	
	}
	
	//gets rid of of all terms with coefficient of 0
	private void removeEmptyTerms(){
		for (int i=0; i<expression.size(); i++){
			if(expression.get(i).getCoefficient() == 0.0){
				expression.remove(i);
				i=i-1;
			}
		}
	}
	
	//as it says, combines like terms (with same exponent)
	private void combineLikeTerms(){
		for(int i=0; i<expression.size()-1; i++){
			for(int j=i+1; j<expression.size(); j++){
				if(expression.get(i).getExponent() == expression.get(j).getExponent()){
					double tCo = expression.get(i).getCoefficient() + expression.get(j).getCoefficient();
					expression.get(i).setCoefficient(tCo);
					expression.remove(j);
					j=j-1;
					i=i-1;
				}
			}
		}
	}
	
	//sorts terms in order of descending exponent
	private void sortTerms(){
		Term temp = new Term();
		for(int i=0; i<expression.size(); i++){
			for(int j=i+1; j<expression.size(); j++){
				if(expression.get(i).getExponent() < expression.get(j).getExponent()){
					temp = expression.get(i);
					expression.set(i, expression.get(j));
					expression.set(j, temp);
				}
			}
		}
	}
	
	private int max(int size, int size2) {
		if(size >= size2) return size;
		else return size2;
	}
	
	private void makeSameLength(Polynomial polynomial2) {
		this.addMissingTerms(polynomial2);
		polynomial2.addMissingTerms(this);	
	}
	
	//adds empty terms with corresponding exponent so vertical operation can occur
	// (2x^4 + 3x^0) + (5x^2 + 6x^1)
	//      is the same as
	// 2x^4 + 0x^2 + 0x^1 + 3x^0
	//             +
	// 0x^4 + 5x^2 + 6x^1 + 0x^0
	private void addMissingTerms(Polynomial p) {
		
		if(max(expression.size(),p.expression.size()) == expression.size()){
			for(int i=0; i<expression.size(); i++){
				String s = "x^" + expression.get(i).getExponent();
				if(!p.toString().contains(s)){
					if(expression.get(i).getExponent() > p.expression.get(i).getExponent()){
						Term temp = new Term(0.0,expression.get(i).getExponent());
						p.expression.add(i, temp);
					}
					else if(expression.get(i).getExponent() < p.expression.get(i).getExponent()){
						Term temp = new Term(0.0,expression.get(i).getExponent());
						p.expression.add(i+1, temp);
					}
				}
			}
		}
		
		else if(max(expression.size(),p.expression.size()) == p.expression.size()){
			for(int i=0; i<p.expression.size(); i++){
				String s = "x^" + expression.get(i).getExponent();
				if(!this.toString().contains(s)){
					if(p.expression.get(i).getExponent() > expression.get(i).getExponent()){
						Term temp = new Term(0.0,p.expression.get(i).getExponent());
						expression.add(i, temp);
					}
					else if(p.expression.get(i).getExponent() < expression.get(i).getExponent()){
						Term temp = new Term(0.0,p.expression.get(i).getExponent());
						expression.add(i+1, temp);
					}
				}
			}
		}
	}
	
	public String toString(){
		String returnString = "";
		
		for(int i=0; i<expression.size(); i++){
			
			returnString = returnString + Double.toString(expression.get(i).getCoefficient()) + "x^" + Integer.toString(expression.get(i).getExponent());
			
			if(i != expression.size() - 1){
				returnString = returnString + " + ";
			}
		}
		String t;
		//if(returnString.contains("x^0")){
			//returnString = returnString.replace("x^0", "");
		//}
		//returnString = returnString.replace("x^0", " "); //______ Gives indexOutOfBoundsException for some reason o.o
		return returnString;
		
	}

}
