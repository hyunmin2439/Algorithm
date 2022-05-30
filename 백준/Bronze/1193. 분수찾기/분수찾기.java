import java.io.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int x = Integer.parseInt( br.readLine() );
		int n = 1, res = 0;
		
		while(res < x) {
			res = (1 + n) * n / 2;
			n++;
		}
		n--;
		
		int i, j;
		if( n % 2 == 0 ) {
			i = n - (res - x);
			j = n - i + 1;
		}
		else {
			j = n - (res - x);
			i = n - j + 1;
		}
        
		bw.write(i + "/" + j);
		br.close();
		bw.close();
	}

}