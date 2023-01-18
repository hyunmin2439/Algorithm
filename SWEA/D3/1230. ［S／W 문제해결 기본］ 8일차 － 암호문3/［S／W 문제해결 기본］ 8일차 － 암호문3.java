import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder(1500); // 대략 큰 크기로
		StringTokenizer st;
		
		List<String> crypto = new LinkedList<>();
		String N = in.readLine(), inst;
		int t = 1, M, x, y;
		
		while(N != null) {
			st = new StringTokenizer(in.readLine());
			while(st.hasMoreTokens()) {
				crypto.add(st.nextToken());
			}
			
			M = Integer.parseInt(in.readLine());
			st = new StringTokenizer(in.readLine());
			
			while(M-- > 0) {
				inst = st.nextToken();
				x = Integer.parseInt(st.nextToken());
				switch(inst) {
				case "I":
					y = Integer.parseInt(st.nextToken());
					// x 이후에 차례로 들어가야 하기 때문에 x++
					while(y-- > 0) crypto.add(x++, st.nextToken());
					break;
					
				case "D":
					y = Integer.parseInt(st.nextToken());
					while(y-- > 0) crypto.remove(x);
					break;
					
				default: // A
					while(x-- > 0) crypto.add(st.nextToken());
				}
			}
			
			sb.append("#").append(t).append(" ");
			for(int i = 0; i < 10; i++)
				sb.append(crypto.get(i)).append(" ");
			sb.append("\n");
			
			crypto.clear();
			N = in.readLine();
			t++;
		}
		
		out.write(sb.toString());
		out.flush();
	}
}