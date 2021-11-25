import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1547_공 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		boolean[] cups = new boolean[4]; // 0 : temp;
		
		cups[1] = true;
		
		int M = Integer.parseInt(in.readLine());
		
		for(int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int X = Integer.parseInt(st.nextToken());
			int Y = Integer.parseInt(st.nextToken());
			
			cups[0] = cups[X];
			cups[X] = cups[Y];
			cups[Y] = cups[0];
		}
		
		for(int i = 1; i <= 3; i++) {
			if(cups[i]) {
				System.out.print(i);
				break;
			}
		}
		
		in.close();
	}

}
