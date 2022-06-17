import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		String[] arr = new String[N + 1];
		Map<String, Integer> map = new LinkedHashMap<>();
		
		String tmp;
		for(int i = 1; i <= N; i++) {
			tmp = in.readLine();
			arr[i] = tmp;
			map.put(tmp, i);
		}
		
		for(int i = 1; i <= M; i++) {
			tmp = in.readLine();
			
			if(tmp.charAt(0) < 65) {
				sb.append(arr[ Integer.parseInt(tmp) ]).append('\n');
			}
			else {
				sb.append(map.get(tmp)).append('\n');
			}
		}
		
		System.out.print(sb);
		
		in.close();
	}
}