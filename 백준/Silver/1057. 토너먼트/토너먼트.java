import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		int jiminNum = Integer.parseInt(st.nextToken());
		int hansuNum = Integer.parseInt(st.nextToken());
		
		int round = 1;
		
		while(true) {
			if( jiminNum % 2 == 1 && jiminNum + 1 == hansuNum ) break;
			
			if( hansuNum % 2 == 1 && hansuNum + 1 == jiminNum ) break;
			
			if( jiminNum % 2 == 1 ) jiminNum++;
			if( hansuNum % 2 == 1 ) hansuNum++;
			
			jiminNum /= 2;
			hansuNum /= 2;
			
			round++;
		}
		
		System.out.print(round);
		in.close();
	}
}