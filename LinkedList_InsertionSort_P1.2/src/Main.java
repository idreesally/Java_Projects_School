import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) throws IOException{

		if(args.length == 0) System.out.println("its zero");
		if(args.length > 0) {
            
			try {
				
				LinkedList CWordListHead = new LinkedList();
				LinkedList textListHead = new LinkedList();

				Scanner inFile1 = new Scanner(new File(args[0]));
				Scanner inFile2 = new Scanner(new File(args[1]));
				PrintStream out1 = new PrintStream(new FileOutputStream(args[2]));
				PrintStream out2 = new PrintStream(new FileOutputStream(args[3]));
				
				while(inFile1.hasNext()){
					String commonWord = inFile1.next();

					ListNode spot = CWordListHead.findSpot(commonWord);
					ListNode newWord = new ListNode(commonWord);
					CWordListHead.listInsert(spot, newWord);
					
					out1.println(CWordListHead.debugPrint() + "\n");
				}
				out1.println("\n\n");
				
				
				int insertionCount = 0;
				while(inFile2.hasNext()){
					String textWord = inFile2.next();
					
					if(!CWordListHead.containsCommonWord(textWord)){
						ListNode spot = textListHead.findSpot(textWord);
						
						if(spot != null){
							ListNode newWord  = new ListNode(textWord);
							textListHead.listInsert(spot, newWord);
							insertionCount++;
						}
					}
					
					if(insertionCount >= 5){
						out1.println(textListHead.debugPrint() + "\n\n");
						insertionCount = 0;
					}					
				}
				
				out2.println(textListHead.printSortedList());
				
				inFile1.close();
				out1.close();
				inFile2.close();
				out2.close();
			
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}	
		}
	}
}