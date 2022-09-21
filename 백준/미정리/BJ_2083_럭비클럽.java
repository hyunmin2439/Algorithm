package solved;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Memory:14016KB / Time:120ms

public class BJ_2083_럭비클럽 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String name, adult;
		int age, weight;
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			name = st.nextToken();
			age = Integer.parseInt(st.nextToken());
			weight = Integer.parseInt(st.nextToken());
			
			if(name.equals("#") && age == 0 && weight == 0) break;
			
			if(age > 17 || weight >= 80)
				adult = "Senior";
			else
				adult = "Junior";
			
			sb.append(name).append(" ").append(adult).append("\n");
		}
		
		System.out.print(sb);
	}
	
}
