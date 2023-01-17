import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int t = 0, T, N, acc, tmp, flag, end = (1 << 10) - 1;
		
		T = Integer.parseInt(in.readLine());
		
		while(t++ < T) {
			N = Integer.parseInt(in.readLine());
			acc = 0;
			flag = 0;
			
			while(flag < end) {
				acc += N;
				tmp = acc;
				
				while(tmp != 0) {
					flag |= 1 << tmp % 10;
					tmp /= 10;
				}
			}
			
			out.write("#" + t + " " + acc + "\n");
		}
		
		out.flush();
	}
}