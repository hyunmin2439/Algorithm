import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Memory:14,240KB / Time:124ms
public class BJ_2828_사과_담기_게임 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int J = Integer.parseInt(in.readLine());
		
		int cs = 1, ce = M, pos, move = 0, totMove = 0;
		for(int i = 0; i < J; i++) {
			pos = Integer.parseInt(in.readLine());
			
			if(cs <= pos && pos <= ce) continue;
			
			if(pos < cs) {
				move = cs - pos;
				cs -= move;
				ce -= move;
			}
			else {
				move = pos - ce;
				cs += move;
				ce += move;
			}
			
			totMove += move;
		}
		
		System.out.println(totMove);
	}

}
