import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), ":");
		int N, M, divNum = 3;
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		in.close();
		
		while(N % 2 == 0 && M % 2 == 0) {
			N /= 2;
			M /= 2;
		}
			
		while(N >= divNum || M >= divNum) {
			if(N % divNum == 0 && M % divNum == 0) {
				N /= divNum;
				M /= divNum;
			}
			else
				divNum += 2;
		}
		
		System.out.print(N + ":" + M);
	}

}