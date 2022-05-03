import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// Memory:78020KB / Time:660ms
public class BJ_1269_대칭차집합 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(in.readLine());
		
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		Set<Integer> set = new HashSet<>();
		
		st= new StringTokenizer(in.readLine());
		for(int i = 0; i < A; i++) {
			set.add(Integer.valueOf(st.nextToken()));
		}
		
		Integer tmp;
		st= new StringTokenizer(in.readLine());
		for(int i = 0; i < B; i++) {
			tmp = Integer.valueOf(st.nextToken());
			if( set.contains(tmp) )
				set.remove(tmp);
			else
				set.add(tmp);
 		}
		
		System.out.print(set.size());
		
		in.close();
	}

}
