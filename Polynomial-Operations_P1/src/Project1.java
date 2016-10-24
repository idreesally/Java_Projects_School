import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Project1 {
	
	static String inFileName = "project1.txt"; //name of file to read
	public static BufferedReader inFile;
	public static String line; //will be equal to each line of the file
	
	public static void main(String[] args){
		try {
			inFile = new BufferedReader(new FileReader(inFileName));
			line=inFile.readLine();
			while(line!=null){ 
				
				String line1 = line; //line1 holds first expression
				
				line = inFile.readLine();
				String line2 = line;  //line2 holds second expression
				
				line = inFile.readLine();				
				String line3 = line;  //line3 holds operation to perform

				//sends expressions and operation to ReadAndPerform class to solve
				ReadAndPerform rap = new ReadAndPerform(line1, line2, line3); 
				line = inFile.readLine();
	
			}//while_end
		} catch (IOException e) {
			System.out.println("____________________________________________________________________________ \n"
					+ "No such file or directory by the name of " + inFileName + " in the Project folder.\n"
							+ "____________________________________________________________________________");
		}//try_catch_end	
	}
}
