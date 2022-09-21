import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Memory:14702KB / Time:124ms
public class BJ_1629_곱셈 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		long A = Integer.parseInt(st.nextToken());
		long B = Integer.parseInt(st.nextToken());
		long C = Integer.parseInt(st.nextToken());
		
		long ans = 1;
		
		while(B > 0) {
			if(B % 2 == 1)
				ans = (ans * A) % C;
			
			B >>= 1;
			A = (A * A) % C;
		}
		
		System.out.println(ans);
		
		in.close();
	}

}
