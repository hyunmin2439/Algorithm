import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_1668_트로피진열 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		int[] height = new int[N];
		
		int leftVisibleNum = 0, maxHeight = 0;
		for(int i = 0; i < N; i++) {
			height[i] = Integer.parseInt(in.readLine());
			
			if(height[i] > maxHeight) {
				maxHeight = height[i];
				leftVisibleNum++;
			}
		}
		
		int rightVisibleNum = 1;
		maxHeight = height[N - 1];
		for(int i = N - 2; i >= 0; i--) {
			if(height[i] > maxHeight) {
				maxHeight = height[i];
				rightVisibleNum++;
			}
		}
		
		System.out.println(leftVisibleNum);
		System.out.println(rightVisibleNum);
		
		in.close();
	}

}
