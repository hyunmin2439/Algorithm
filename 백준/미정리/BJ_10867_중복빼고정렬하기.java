package solved.notsubmit;

import java.io.*;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BJ_10867_중복빼고정렬하기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());  
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		Set<Integer> set = new TreeSet<>();
		while(n-- > 0) {
			set.add(Integer.parseInt(st.nextToken()));
		}
		
		StringBuilder sb = new StringBuilder();
		for (Integer i : set) {
			sb.append(i + " ");
		}
		
		bw.write(sb.toString());
		br.close();
		bw.close();
	}

}
