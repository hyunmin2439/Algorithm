import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static String[] color = { "black", "brown", "red", "orange", "yellow", "green", "blue", "violet", "grey", "white" };
	static long[] value = { 1, 10, 100, 1_000, 10_000, 100_000, 1_000_000, 10_000_000, 100_000_000, 1_000_000_000 };
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		long om[] = new long[3];
		
		for (int i = 0; i < 3; i++) {
			String colorStr = in.readLine();
			for (int j = 0; j < 10; j++) {
				if(colorStr.equals(color[j])) {
					om[i] = j;
					break;
				}
			}
		}
		
		om[2] = value[ (int) om[2] ];
		
		System.out.println( (om[0] * 10 + om[1]) * om[2] );
		
		in.close();
	}
}