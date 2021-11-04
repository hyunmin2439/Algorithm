package solved;

import java.io.*;
import java.math.BigInteger;

public class BJ_10757_큰수AaddB_1_BigInteger {

	public static void main(String[] args) throws IOException {
		BigInteger b1, b2, b3;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] line = br.readLine().trim().split(" ");
		
		b1 = new BigInteger( line[0].toString() );
		b2 = new BigInteger( line[1].toString() );
		b3 = b1.add(b2);
		
		bw.write(b3.toString());
		br.close();
		bw.close();
	}
}
