import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// Memory:122936KB / Time:624ms
public class BJ_15656_N과M7 {

	static int N, M;
	
	static int[] arr, tgt;
	
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		tgt = new int[M];
		
		st = new StringTokenizer(in.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		comb(0);
		
		System.out.println(sb);
		
		in.close();
	}

	private static void comb(int idx) {
		if(idx == M) {
			for(int i = 0; i < M; i++) {
				sb.append(tgt[i]).append(' ');
			}
			sb.append('\n');
			
			return;
		}
		
		for(int i = 0; i < N; i++) {
			tgt[idx] = arr[i];
			
			comb(idx + 1);
		}
	}

}
