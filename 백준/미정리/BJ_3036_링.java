import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Memory:15972ms / Time:156ms
public class BJ_3036_링 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int numerator = Integer.parseInt(st.nextToken());
		
		for(int i = 1; i < N; i++) {
			int denominator = Integer.parseInt(st.nextToken());
			
			getReducedFraction(numerator, denominator);
		}
		
		in.close();
	}
	
	private static void getReducedFraction(int numerator, int denominator) {
		int divisor = Math.min(numerator, denominator);
		divisor = divisor <= 32 ? divisor : 32; // 최대 숫자가 1000까지
		
		while( divisor > 1 && !(numerator == 1 || denominator == 1) ) {
			
			if(numerator % divisor == 0 && denominator % divisor == 0) {
				numerator /= divisor;
				denominator /= divisor;
			}
			
			divisor--;
		}
		
		System.out.println(numerator + "/" + denominator);
	}
}
