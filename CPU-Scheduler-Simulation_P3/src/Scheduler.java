import java.util.LinkedList;
import java.util.Queue;


public class Scheduler {

	private Queue<Job> jobsToAdd = new LinkedList<Job>();
	private Queue<Job> jobs = new LinkedList<Job>();
	private static BinaryHeap<Job> bh = new BinaryHeap<Job>();

	
	public Scheduler(Queue<Job> o) {
		jobs = o;

	}
	
	public void perform(){
		
		bh.insert(jobs.remove());
		Job j = bh.deleteMinimum();
		
		int count = j.getLength();
		int timeStart = j.getArrival();
		int length = count + timeStart;

		int printIDCounter = 0;
		
		for(int i=timeStart; i<length; i++)
		{
			if(printIDCounter != j.getLength())
			{
				System.out.println("Current Job ID: " + j.getID());
				printIDCounter++;
			}
				
			if(!jobs.isEmpty() && jobs.peek().getArrival() == i)
			{
				while(!jobs.isEmpty() && jobs.peek().getArrival() == i)
				{
					Job temp = jobs.remove();
					bh.insert(temp);
					System.out.println("Added job with ID " + temp.getID() + ", length " + temp.getLength() + 
							" and priority " + temp.getPriority());
				}
			}
			else System.out.println("No new job arrived during this time slice");

			

			if(i == length-1 && !bh.isEmpty())
			{
				j = bh.deleteMinimum();
				printIDCounter = 0;
				length = length + j.getLength();
			}
			
			if(i == length-1 && !jobs.isEmpty())
			{
				length = jobs.peek().getArrival() + 1;
			}
			//if(jobs.isEmpty()) System.out.println("Queue is empty.");
		}

		
		//System.out.println(bh.deleteMinimum().getPriority());
		//System.out.println(bh.deleteMinimum().getPriority());
		//System.out.println(bh.deleteMinimum().getPriority());

	}

}
