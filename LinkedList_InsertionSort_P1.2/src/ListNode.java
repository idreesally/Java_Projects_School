
public class ListNode {
	String data = null;
	int count = 0;
	ListNode next = null;
	
	ListNode(){}

	ListNode(String d)
	   {
	      data = d;
	      count = 1;
	      next = null;
	   }  // constructor
	   
	   public ListNode(String d, int c, ListNode n){
			data = d;
			count = c;
			next = n;
		}
	   
	   String getData(){
		   return data;
	   }
}
