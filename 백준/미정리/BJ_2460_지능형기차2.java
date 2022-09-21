import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Memory:14144KB / Time:132ms
public class BJ_2460_지능형기차2 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int max = 0, sum = 0;
		
		for(int i = 0; i < 10; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			sum -= Integer.parseInt(st.nextToken());
			sum += Integer.parseInt(st.nextToken());
			
			max = Math.max(max, sum);
		}
		
		System.out.println(max);
		
		in.close();
	}

}
