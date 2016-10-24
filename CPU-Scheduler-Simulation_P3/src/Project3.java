import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Project3 {
	
	//public static DoublyLinkedList<Job> dll = new DoublyLinkedList<Job>();
	
	public static void main(String args[]){
		
		String fileName = "project3.txt";
		String line;
		BufferedReader inFile;
		Queue<Job> myQueue = new LinkedList<Job>();
		
		try {
			
			inFile = new BufferedReader(new FileReader(fileName));
			line=inFile.readLine();
			
			while(line != null){
				StringTokenizer s = new StringTokenizer(line," ");
				
				if(s.countTokens() == 4){
					
					int i = Integer.parseInt(s.nextToken());
					int p = Integer.parseInt(s.nextToken());
					int a = Integer.parseInt(s.nextToken());
					int l = Integer.parseInt(s.nextToken());
					//System.out.println(i + " " + p + " " + a+ " " + l);
					
					
					Job j = new Job(i,p,a,l);
					System.out.println(j.getID() + " " + j.getPriority() + " " + j.getArrival()+ " " + j.getLength());
					myQueue.add(j);
					
								
				}				
				line = inFile.readLine();	
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}//try_catch_end
		
		Scheduler CPUSchedule = new Scheduler(myQueue);
		CPUSchedule.perform();
	}
}
