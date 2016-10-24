
public class LinkedList {

	ListNode listHead = new ListNode("Listhead");
	
	public LinkedList(){
		ListNode dummy = new ListNode("dummy");
		listHead.next = dummy;
	}
	
	public ListNode findSpot(String data){
		ListNode spot = listHead.next;
		
		while(spot.next !=null && spot.next.data.compareToIgnoreCase(data) <= 0){
			spot = spot.next;
		}

		if(spot.getData().contentEquals(data)){
			spot.count++;
			return null;
		}
		else{
			return  spot;
		}
	}
	
	public void listInsert(ListNode spot, ListNode newNode){
			newNode.next = spot.next;
			spot.next = newNode;
	}
	
	public String debugPrint(){
		
		ListNode temp = listHead.next;
		String toReturn = listHead.data;
		
		while(temp != null){
		
			toReturn += " --> (" + temp.data + ", ";
			if(temp.next == null){
				toReturn += null + ")";
			}
			else {
				toReturn += temp.next.data +")";
			}
			temp = temp.next;
		}
		
		return toReturn;
	}

	public String printSortedList(){
		ListNode temp = listHead.next;
		String toReturn = "";
		
		while(temp != null){
			toReturn += temp.data + "  " + temp.count + "\r\n";
			temp = temp.next;
		}
		
		return toReturn;
	}
	
	public boolean containsCommonWord(String data) {
		ListNode temp = listHead.next;
		
		while(temp != null){
			if (temp.data.contentEquals(data)) return true;
			temp = temp.next;
		}
		return false;
	}
}
