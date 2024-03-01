import java.util.Scanner;
import java.util.Random;

public class FGTest{
	public static void main(String[]args){
		Scanner kb = new Scanner(System.in);
		
		String algorithmString = "gfgfggff";
		StreamingAlgo algo = new StreamingAlgo(){
			StreamingAlgo f1 = new F();
			StreamingAlgo f2 = new F();
			StreamingAlgo g1 = new G();
			StreamingAlgo g2 = new G();
			StreamingAlgo f3 = new F();
			StreamingAlgo f4 = new F();
			StreamingAlgo g3 = new G();
			StreamingAlgo g4 = new G();
			public int apply(int x){
				return f4.apply(f3.apply(g4.apply(g3.apply(f2.apply(g2.apply(f1.apply(g1.apply(x))))))));
			}
		};
		while(true) {
			int x = kb.nextInt();
			System.out.println("Algo: " + algorithmString + ", Input value: " + x + ", algo: " + algo.apply(x) + ", fusion returns " + fusion(x));
		}
	}
	
	static int previous = 1;
	static boolean hasPrevious = false;
	static int sum = 0;
	private static int fusion(int x){
		int y = 0;
		if (hasPrevious) {
			y = x - previous;
			previous = x;
		}else{
			y = x;
			previous = x;
			hasPrevious = true;
		}
		sum += y;
		return sum;
	}
}

abstract class StreamingAlgo{
	public abstract int apply(int x);
}
class F extends StreamingAlgo{
	private int fPrevious = 1;
	private boolean fHasPrevious = false;
	
	public int apply(int x){
		if (fHasPrevious){
			int result = x - fPrevious;
			fPrevious = x;
			return result;
		}else{
			fPrevious = x;
			fHasPrevious = true;
			return x;
		}
	}
}
class G extends StreamingAlgo{
	private int gSum = 0;
	
	public int apply(int x){
		gSum += x;
		return gSum;
	}
}