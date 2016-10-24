import java.util.Iterator;
import java.util.StringTokenizer;


public class ReadAndPerform2 {
	
	public static BinarySearchTree<Integer> BST = new BinarySearchTree<Integer>();
	private String operation;
	
	public ReadAndPerform2(String t)
	{
		operation = t;
		perform();	
	}

	private void perform() {
		
		StringTokenizer s = new StringTokenizer(operation," ");
		
		while(s.hasMoreTokens()){
			
			int value = 0;
			String op = s.nextToken();
			
			//this if statement will check for insert and remove operations
			if(s.hasMoreTokens())
			{
				try{
					value = Integer.parseInt(s.nextToken());
					
					if(op.contentEquals("insert")){
						if(value >= -9999 && value <= 9999) BST.insert(value); //dont accept values out of range
					}
					else if(op.contentEquals("remove")) BST.remove(value);
					else throw new IncorrectOperationException(op); //if more than one token, but still incorrect
				
				} catch (NumberFormatException nfe) {
					System.out.println("Values to be inserted or removed should be integers.");
				}
			}
			
			else if(op.contentEquals("print_tree"))	BST.printTree();
			else if(op.contentEquals("inorder_list")) printInOrderList();
			else throw new IncorrectOperationException(op);					
		}		
	}

	private void printInOrderList() {
		Iterator<Integer> toPrint = BST.iterator();
		while(toPrint.hasNext()){
			System.out.print(toPrint.next() + "   ");
		}
		
	}
}
