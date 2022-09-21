import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Memory:14016KB / Time:124ms
public class BJ_2455_지능형기차 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int max = 0, sum = 0;
		
		for(int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			sum -= Integer.parseInt(st.nextToken());
			sum += Integer.parseInt(st.nextToken());
			
			max = Math.max(max, sum);
		}
		
		System.out.println(max);
		
		in.close();
	}

}
