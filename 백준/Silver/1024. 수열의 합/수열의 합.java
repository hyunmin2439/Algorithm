import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		String res = twoPointer(N, L);
		
		System.out.print(res);
		
		in.close();
	}
	
	private static String twoPointer(int N, int L) {
		int leftNum = N / L, rightNum = N / L, leftSum, rightSum;
		
		while(leftNum >= 0 && L <= 100) {
			leftSum = 0; 
			rightSum = 0;
			
			for(int i = 0; i < L; i++) {
				leftSum += leftNum + i;
				rightSum += rightNum + i;
			}

			if(leftSum == N) return getResult(leftNum, L);
			if(rightSum == N) return getResult(leftNum, L);
			
			if( leftSum < N && rightSum > N ) {
				L++;
				leftNum = N / L - 1;
				rightNum = N / L + 1;
			}
			else {
				leftNum--;
				rightNum++;
			}
		}
		
		return "-1";
	}
	
	private static String getResult(int startNum, int L) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < L; i++) {
			sb.append(startNum + i).append(" ");
		}
		
		return sb.toString();
	}
}