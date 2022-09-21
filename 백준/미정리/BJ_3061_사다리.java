package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 정렬하는 횟수랑 같음
 * 
 * 21568KB / Time:356ms
 */
public class BJ_3061_사다리 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(in.readLine());
		
		int N, cnt, tmp, arr[];
		while(T-- > 0) {
			cnt = 0;
			N = Integer.parseInt(in.readLine());
			
			arr = new int[N];
			st = new StringTokenizer(in.readLine());
			for(int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 1; j < N - i; j++) {
					if(arr[j - 1] > arr[j]) {
						tmp = arr[j];
						arr[j] = arr[j - 1];
						arr[j - 1] = tmp;
						cnt++;
					}
				}
			}
			
			System.out.println(cnt);
		}
		
		in.close();
	}

}
