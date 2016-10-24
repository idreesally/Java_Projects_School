import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Project2 {
	
	static String inFileName = "project2.txt"; //name of file to read
	public static BufferedReader inFile;
	static String line; //will be equal to each line of the file

	
	public static void main(String[] args){
		
		try {
			
			inFile = new BufferedReader(new FileReader(inFileName));
			line=inFile.readLine();
			
			while(line != null){
				ReadAndPerform2 rap = new ReadAndPerform2(line); //sends line to new class to process the info
				line = inFile.readLine();	
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}//try_catch_end
	}
}
