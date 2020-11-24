import java.io.File; 
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 * @author adeltouati
 *
 */
public class HashTest {


	private static int Ts(int min, int max) {

		if (max < min) {
			System.out.println("Invalid range.");
			System.exit(0);
		}

		int num1, num2;
		for (int i = min; i < max; i++) {
			num1 = new Integer(i);
			num2 = new Integer(i + 2);

			if (prime(num1) && prime(num2)) {
				return i+2;
			}
		}
		return -1;
	}




	static int size = 0;
	static int debugLevel = 0;
	static double loadF = 0;
	static int arg = 0;

	static int maxP;
	static long curTime = 0;
	static Scanner sc;
	static  int count = 0;
	static  int count1 = 0;
	static  int count2 = 0;
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args){


		try {
			arg = Integer.parseInt(args[0]);
			loadF = Double.parseDouble(args[1]);
			if (args.length== 3) {
				debugLevel =Integer.parseInt(args[2]);
			} else if (args.length == 2) {
				debugLevel=0;
			}
		} catch (IllegalArgumentException e) {
			System.out.println("It should have 2 or 3 command-line arguments, <input type> <load factor> [<debug level>]" + "\n The <input type> should be 1, 2, or 3 depending on whether the data is generated."+ "\n the <load factor> should be a double between 0 and 1."
					+ "\n the [<debug level>] is optional and it should be 0, 1, 2.");
			System.exit(1);
		}


		if ((loadF< 0 || loadF> 1)|| (arg<1 ||arg >3) || (debugLevel < 0||debugLevel>2)) {
			System.out.println("It should have 2 or 3 command-line arguments, <input type> <load factor> [<debug level>]"+ "\n The <input type> should be 1, 2, or 3 depending on whether the data is generated."+ "\n the <load factor> should be a double between 0 and 1."
					+ "\n the [<debug level>] is optional and it should be 0, 1, 2.");
			System.exit(1);
		}

		if (args.length != 3 &&args.length != 2) {
			System.out.println("It should have 2 or 3 command-line arguments, <input type> <loadfactor> [<debug level>]"+ "\n The <input type> should be 1, 2, or 3 depending on whether the data is generated." + "\n the <load factor> should be a double between 0 and 1."
					+ "\n the [<debug level>] is optional and it should be 0, 1, 2.");

			System.exit(1);
		}

		size = Ts(95500, 96001);
		maxP= (int) Math.ceil(size * loadF);
		System.out.println("A good table size is found: "+size);

		Random rand = new Random();
		@SuppressWarnings("rawtypes")
		hashTable doubProb = new hashTable<String>(size, loadF, debugLevel);
		@SuppressWarnings("rawtypes")
		hashTable linProb = new hashTable<String>(size, loadF, debugLevel);

		if (arg == 1) {
			System.out.println("Data source type: random number ");

			for(int i = 0; i<maxP;){
                  count1++;
				Integer inP = rand.nextInt(Integer.MAX_VALUE);

				doubProb.insert(inP, 1);
				if (linProb.insert(inP, 0)==1) {
					i++;
				}
		

			}
			int dup3 = count1-linProb.getlinearElement();

			System.out.println("Using Linear Hasing...");
			System.out.println("Inserted "+ count1 +" elements of which " +dup3+" are duplicates");
			System.out.println("load factor = "+ linProb.getLoadF()+" , The average number of probes is "+((double)(double)linProb.getTotalLinearProb()/(double)maxP)+"\n");

			System.out.println("Using Double Hasing...");
			System.out.println("Inserted "+ count1 +" elements of which " +dup3+" are duplicates");
			System.out.println("load factor = "+ doubProb.getLoadF()+" , The average number of probes is "+((double)(double)doubProb.getTotalDoubleProb()/(double)maxP));
            


		} else if (arg == 2) {
			System.out.println("Data source type: current time ");
            

			for(int i = 0; i<maxP;){
                 count2++;
				curTime = System.currentTimeMillis();	

				doubProb.insert(curTime, 1);
				if (linProb.insert(curTime, 0)==1) {
					i++;
				}
				

			}
			int dup2 = count2-linProb.getlinearElement();
			System.out.println("Using Linear Hasing...");
			System.out.println("Inserted "+ count2 +" elements of which " +dup2+" are duplicates");
			System.out.println("load factor = "+ linProb.getLoadF()+" , The average number of probes is "+((double)linProb.getTotalLinearProb()/(double)maxP)+"\n");

			System.out.println("Using Double Hasing...");
			System.out.println("Inserted "+ count2 +" elements of which " +dup2+" are duplicates");
			System.out.println("load factor = "+ doubProb.getLoadF()+" , The average number of probes is "+((double)doubProb.getTotalDoubleProb()/(double)maxP));

		}
		else if (arg == 3) {
			System.out.println("Data source type: word-list\n");

			try {
				sc = new Scanner(new File("word-list"));
			    
				for(int i = 0; i<maxP;){
			   count++;
					String	str = sc.nextLine();
					doubProb.insert(str, 1);
					if (linProb.insert(str, 0)==1) {
               
						i++;
					}



				}
				sc.close();
			} catch (FileNotFoundException e) {
				System.err.println("File Not Found.");
				System.exit(1);
			} 

           int dup = count-linProb.getlinearElement();
           
			System.out.println("Using Linear Hasing...");
			System.out.println("Inserted "+ count +" elements of which " + dup +" are duplicates");
			System.out.println("load factor = "+ linProb.getLoadF()+" , The average number of probes is "+ (double)linProb.getTotalLinearProb()/maxP +"\n");

			System.out.println("Using Double Hasing...");
			System.out.println("Inserted "+ count +" elements of which " + dup +" are duplicates");
			System.out.println("load factor = "+ doubProb.getLoadF()+" , The average number of probes is "+(double)doubProb.getTotalDoubleProb()/maxP);
           
            
        
		}
		if (debugLevel == 1) {
			linProb.printT("linear-dump");
			System.out.println();
			doubProb.printT("double-dump");
		} 




	}

	public static boolean prime(int n){
		for (int i = 2; i < (n/2); i++){
			if (n%i == 0)
				return false;
		}
		return true;

	}

}
