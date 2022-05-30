import java.io.*;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out) );
		
		int nof = Integer.parseInt( br.readLine() );
		StringTokenizer st = new StringTokenizer(br.readLine());
		while(st.hasMoreTokens()) {
			int f = Integer.parseInt(st.nextToken());
			min = min < f ? min : f;
			max = max > f ? max : f;
		}
		
		int n;
		if(nof == 1) n = (int)Math.pow(min, 2);
		else {
			n = min * max;
		}
		
		bw.write(String.valueOf(n));
		br.close();
		bw.close();
	}

}
