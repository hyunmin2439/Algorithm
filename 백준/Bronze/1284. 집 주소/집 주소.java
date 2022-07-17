import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		char[] num;
		int len;
		
		while(true) {
			num = in.readLine().toCharArray();
			
			if(num[0] == '0') break;
			
			len = 2;
			len += num.length - 1;
			
			for(int i = 0; i < num.length; i++) {
				if(num[i] == '0')
					len += 4;
				else if(num[i] == '1')
					len += 2;
				else
					len += 3;
			}
			
			sb.append(len).append('\n');
		}
		
		System.out.print(sb);
	}
}