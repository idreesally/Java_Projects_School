import java.io.PrintStream;
import java.util.Scanner;

public class PQSort {
	
	int finalHeapSize = 0;
	int currentHeapSize = 0;
	final static int ROOT_INDEX = 1;
	int[] PQAry;
	
	PQSort(int size)
	{
	    finalHeapSize = size+1;
	    PQAry = new int[finalHeapSize];
	    PQAry[0] = 0;
	    
	}

	void buildPQArray(Scanner inFileRepeat, PrintStream outFile1){
	    int data = 0;
	    while(inFileRepeat.hasNextInt()){
	    	data = inFileRepeat.nextInt();
	        insertOneDataItem(data);
	        //CURRENT HEAP SIZE NOW 1, on first iteration after InsertOneDataItem
	        bubbleUp(getCurrentHeapSize());
	        printPQArray(outFile1);
	    }
	    inFileRepeat.close();
	}

	void insertOneDataItem(int data){
	    if(!isPQArrayFull()){
	        currentHeapSize++;
	        PQAry[getCurrentHeapSize()] = data;
	        PQAry[0] = currentHeapSize;

	    }
	}

	void bubbleUp(int kidIndex){
	    int fatherIndex = getFatherIndex(kidIndex);
	    while(PQAry[kidIndex] < PQAry[fatherIndex]){
	        if(kidIndex == 1) break;
	        int temp = PQAry[fatherIndex];
	        PQAry[fatherIndex] = PQAry[kidIndex];
	        PQAry[kidIndex] = temp;
	        kidIndex = fatherIndex;
	        fatherIndex = getFatherIndex(kidIndex);
	    }
	}

	void deletePQArray(PrintStream outFile1, PrintStream outFile2){
	    while(!isPQArrayEmpty()){
	        outFile2.print("| " + deleteRoot() + " ");
	        if(getCurrentHeapSize() > 0) bubbleDown();
	        printPQArray(outFile1);
	    }
	    outFile2.println("|");
	    outFile1.close();
	    outFile2.close();
	}

	int deleteRoot(){
	    int root = PQAry[ROOT_INDEX];
	    PQAry[ROOT_INDEX] = PQAry[getCurrentHeapSize()];
	    currentHeapSize--;
	    PQAry[0] = getCurrentHeapSize();
	    return root;
	}

	void bubbleDown(){
	    int fatherIndex = ROOT_INDEX;
	    int bubbleDownValue = PQAry[fatherIndex];

	    while(hasLeftChild(fatherIndex)){
	        int smallKidIndex = getLeftChildIndex(fatherIndex);
	        if(hasRightChild(fatherIndex) && PQAry[getRightChildIndex(fatherIndex)] < PQAry[smallKidIndex]){
	            smallKidIndex = getRightChildIndex(fatherIndex);
	        }
	        if(PQAry[smallKidIndex] >= bubbleDownValue) break;
	        PQAry[fatherIndex] = PQAry[smallKidIndex];
	        fatherIndex = smallKidIndex;
	    }
	    PQAry[fatherIndex] = bubbleDownValue;
	}
	
	void printPQArray(PrintStream outFile1){
	    for(int i=0; i < Math.min(getCurrentHeapSize() +1, 10) ; i++){
	            outFile1.print(PQAry[i] +  "    ");
	    }
	    outFile1.println();
	}

	boolean isPQArrayFull(){
	    if(getCurrentHeapSize() < getFinalArraySize()) return false;
	    else return true;
	}

	boolean isPQArrayEmpty(){
	    if(getCurrentHeapSize() == 0) return true;
	    else return false;
	}

	int getCurrentHeapSize(){
	    return currentHeapSize;
	}

	int getFinalArraySize(){
	    return finalHeapSize;
	}

	int getFatherIndex(int i){
	    return (i/2);
	}

	int getLeftChildIndex(int i){
	    return (2*i);
	}

	int getRightChildIndex(int i){
	    return (2*i + 1);
	}

	boolean hasLeftChild(int i){
	    if(getLeftChildIndex(i) <= getCurrentHeapSize()) return true;
	    else return false;
	}

	boolean hasRightChild(int i){
	    if(getRightChildIndex(i) <= getCurrentHeapSize()) return true;
	    else return false;
	}
}
