import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * @author adeltouati
 *
 * @param <T>
 */
public class hashTable <T> {

	private int size;
	private double loadF;
	private int debugLevel;
	@SuppressWarnings("rawtypes")
	private HashObject[] hashTable;
	private int numElemetDoub;
	private int numElementLin;
	private int doubleDup;
	private int linearDup;
	private int probCountL;
	private int probCountD;
	private int totalProbCountL;
	private int totalProbCountD;
	
	

	public hashTable(int size, double loadF, int debugLevel){

		hashTable = new HashObject[size];
		this.debugLevel=debugLevel;
		this.size=size;
		this.loadF=loadF;

	}

	public void setTsize(int size){
		this.size=size;
	}

	public int getTsize(){
		return size;
	}

	public void setLoadF(double loadF){
		this.loadF=loadF;
	}

	public double getLoadF() {
		return loadF;
	}

	public void setlinearElement(int numElementLin){
		this.numElementLin=numElementLin;
	}

	public int getlinearElement(){
		return numElementLin++;
	}

	public void setdoubleElement(int numElemetDoub){
		this.numElemetDoub=numElemetDoub;
	}

	public int getdoubleElement(){
		return numElemetDoub;
	}
	
	public void setlinearProb(int probCountL){
		this.probCountL=probCountL;
	}

	public int getlinearProb(){
		return probCountL;
	}

	public void setdoubleProb(int probCountD){
		this.probCountD=probCountD;
	}

	public int getdoubleProb(){
		return probCountD;
	}

	
	public void setTotalLinearProb(int totalProbCountL){
		this.totalProbCountL=totalProbCountL;
	}

	public int getTotalLinearProb(){
		return totalProbCountL;
	}

	public void setTotalDoubleProb(int totalProbCountD){
		this.totalProbCountD=totalProbCountD;
	}

	public int getTotalDoubleProb(){
		return totalProbCountD;
	}

	public void setdoubleDup(int probCountD){
		this.probCountD=probCountD;
	}

	public int getdoubleDup(){
		return doubleDup;
	}  

	public void setlinearDup(int linearDup){
		this.linearDup=linearDup;
	}

	public int getlinearDup(){
		return linearDup;
	}
	public int mod(int a, int b) {
		int modlus = a % b;
		
		if (modlus < 0) {
			
			return modlus+b;
		}else {
			return modlus;
		}
	}
	
	public int h1 (int k) {
		return mod(k, size);
	}
	public int h2 (int k) {
		return (1+mod(k, size-2));
	}
	public int hdp (int i,int k) {
		return mod(h1(k)+i*h2(k), size);
	}
	public int hlp (int i,int k) {
		return mod(h1(k)+i, size);
	}
	
	
	

	public int insert(T value, int type){

		int key = value.hashCode();
		int linearKey = value.hashCode();
		int doubleKey = value.hashCode();
	
		if (type == 0){
		    
			int hash= hlp(0, linearKey);
		
			HashObject<T> newObjectLi = new HashObject<T>(key, value);
			probCountL=1;
			while (hashTable[hash]!= null && probCountL<size ) {
				
				probCountL++;
				setlinearProb(probCountL);
			
				
				if (hashTable[hash].equals(newObjectLi)) {
				
					//setlinearDup(newObjectLi.probDupCount());
					hashTable[hash].probDupCount();
					return 0;
				}
				hash= hlp(probCountL-1, linearKey);
			}
			
			
			totalProbCountL += probCountL;
		    setTotalLinearProb(totalProbCountL);
		    
			if (probCountL<size) {
			hashTable[hash] = newObjectLi;
			hashTable[hash].setProbCount(probCountL);
            setlinearElement(getlinearElement()+1);
			}else {
				return 0;
			}
			return 1;
		}
		

		if (type == 1){
			HashObject<T> newObjectDouble = new HashObject<T>(key, value);
		    probCountD=1;
			int hash = hdp(probCountD-1, doubleKey);
   
			while (hashTable[hash]!= null && probCountD<size ) {
				probCountD++;
				setdoubleProb(probCountD);
				
				
				if (hashTable[hash].equals(newObjectDouble)) {
					hashTable[hash].probDupCount();
					return 0;
				}
				
			
			
				hash = hdp(probCountD-1, doubleKey);
			}
			
			
			
			totalProbCountD += probCountD;
			setTotalDoubleProb(totalProbCountD);
		
			if (probCountD<size) {
			hashTable[hash] = newObjectDouble;
			hashTable[hash].setProbCount(probCountD);
			setdoubleElement(getdoubleElement()+1);
			}else {
				return 0;
			}

		}
		
			
		
		return 1;
	}
	

	
	public void printT(String fileName){
		
		File file = new File(fileName);

	    try {
			PrintWriter pw = new PrintWriter(file);
			
			for (int k = 0; k < size; k++) {
				if (hashTable[k] != null){
					pw.println("table["+k+"]: "+hashTable[k].toString()+"    " + hashTable[k].probDup()+"  "+hashTable[k].probCount());
				}
			}
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	  
	}
   }


