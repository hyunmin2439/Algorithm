import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		int t, n, cnt = 0;
		int[][] output = new int[2][41];
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out) );
		
		output[0][0] = 1; output[1][0] = 0;
		output[0][1] = 0; output[1][1] = 1;
		
		for(int i = 2; i < output[0].length; i++) {
			output[0][i] = output[0][i - 2] + output[0][i - 1]; 
			output[1][i] = output[1][i - 2] + output[1][i - 1]; 
		}
		
		t = Integer.parseInt( br.readLine().trim() );
		while(cnt < t) {
			n = Integer.parseInt( br.readLine().trim() );
			bw.write( output[0][n] + " " + output[1][n] + "\n");
			cnt++;
		}
		
		br.close();
		bw.close();
	}

}
