import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	private static int N, M, arr[];
	
	public static void main(String[] args) throws Exception {
		input();
		
		Arrays.sort(arr);
		
		Set<String> set = new LinkedHashSet<>();
		
		permu(set, "", 0);
		
		StringBuilder sb = new StringBuilder();
		for (Iterator<String> iterator = set.iterator(); iterator.hasNext();) {
			sb.append(iterator.next()).append('\n');
		}
		
		System.out.print(sb);
	}
	
	private static void input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		st = new StringTokenizer(in.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		in.close();
	}
	
	private static void permu(Set<String> set, String sel, int idx) {
		if(idx == M) {
			set.add(sel.substring(0, sel.length() - 1));
			return;
		}
		
		for(int i = 0; i < N; i++) {
			permu(set, sel + arr[i] + " ", idx + 1);
		}
	}

}
