package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_13458_시험감독 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		
		float[] numOfPeople = new float[N];
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int i = 0; i < N; i++) {
			numOfPeople[i] = Float.parseFloat(st.nextToken());
		}
		
		st = new StringTokenizer(in.readLine());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		long tot = N; // 총 감독관 1명
		for(int i = 0; i < N; i++) {
			numOfPeople[i] = numOfPeople[i] - B > 0 ? numOfPeople[i] - B : 0;
			tot += (long)Math.ceil(numOfPeople[i] / C);
		}
		
		System.out.print(tot);
	}
}
