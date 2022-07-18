import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), ":");
		
		int answer = 0, times[] = new int[3], tmps[] = new int[3];
		
		for(int i = 0; i < 3; i++) {
			times[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(i == j) continue;
				for(int k = 0; k < 3; k++) {
					if(i == k || j == k) continue;
					
					if( !(1 <= times[i] && times[i] <= 12) || 
						!(0 <= times[j] && times[j] <= 59) ||
						!(0 <= times[k] && times[k] <= 59) ) continue;
					
					answer++;
				}
			}
		}
		
		System.out.print(answer);
	}
	
}