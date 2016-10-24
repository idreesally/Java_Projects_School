
public class Term {

	protected double coefficient;
	protected int exponent;
	
	public Term(){
		coefficient = 0.0;
		exponent = 0;
	}
	
	public Term(double c, int e){
		coefficient = c;
		exponent = e;
	}
	
	public double getCoefficient(){
		return coefficient;
	}
	
	public void setCoefficient(double base){
		coefficient=base;
	}
	
	public int getExponent(){
		return exponent;
	}
	
	public void setExponent(int power){
		exponent=power;
	}
	
}
