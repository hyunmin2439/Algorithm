import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Set<String> set = new HashSet<>();
		List<String> list = new ArrayList<>();
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N; i++) {
			set.add(in.readLine());
		}
		
		String tmp;
		for(int i = 0; i < M; i++) {
			tmp = in.readLine();
			
			if( set.remove(tmp) )
				list.add(tmp);
		}
		
		in.close();
		
		list.sort( (a,b) -> a.compareTo(b) );
		
		StringBuilder sb = new StringBuilder();
		sb.append(list.size()).append('\n');
		list.forEach( e -> sb.append(e).append('\n') );
		System.out.print(sb);
	}
}