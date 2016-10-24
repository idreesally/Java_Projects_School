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

				Scanner inFile = new Scanner(new File(args[0]));
				PrintStream outFile1 = new PrintStream(new FileOutputStream(args[1]));
				PrintStream outFile2 = new PrintStream(new FileOutputStream(args[2]));
				
				int size = 0;
				int data = 0;

				while(inFile.hasNextInt()){
					size++;
					data = inFile.nextInt();
				}

				inFile.close();
			
				PQSort pq = new PQSort(size);
				Scanner inFileRepeat = new Scanner(new File(args[0]));
				
				pq.buildPQArray(inFileRepeat, outFile1);
				pq.deletePQArray(outFile1, outFile2);
						
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}