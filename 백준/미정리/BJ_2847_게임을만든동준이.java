package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_2847_게임을만든동준이 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(in.readLine());
		}
		
		int tmp, cnt = 0;
		for(int i = N - 2; i >= 0; i--) {
			if(arr[i + 1] <= arr[i]) {
				tmp = arr[i + 1] - 1;
				cnt += arr[i] - tmp;
				arr[i] = tmp;
			}
		}
		
		System.out.println(cnt);
		
		in.close();
	}

}
