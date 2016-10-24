
public class Job implements Comparable<Job> {
	
	private int ID;
	private int priority;
	private int arrival;
	private int length;
	
	public Job(int i,int p,int a, int l){
		ID = i;
		priority = p;
		arrival = a;
		length = l;
	}
	
	public int getID(){ return ID;}
	public int getPriority(){ return priority; }
	public int getArrival(){ return arrival; }
	public int getLength(){ return length; }
	
	public void setID(int i){ ID = i;}
	public void setPriority(int p){ priority = p; }
	public void setArrival(int a){ arrival = a; }
	public void setLength(int l){ length = l; }
	
	@Override
	public int compareTo(Job o) {
		int compareNum = this.getPriority() - o.getPriority();
		return compareNum;
	}

}
