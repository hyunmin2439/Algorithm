import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_3058_짝수를_찾아라 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(in.readLine());
		
		int num, sum, min;
		while(T-- > 0) {
			sum = 0;
			min = Integer.MAX_VALUE;
			
			st = new StringTokenizer(in.readLine());
			for(int i = 0; i < 7; i++) {
				num = Integer.parseInt(st.nextToken());
				
				if(num % 2 == 0) {
					sum += num;
					min = Math.min(min, num);
				}
				
			}
			
			System.out.println(sum + " " + min);
		}
	}
	
}
