import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_1212_8진수2진수 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] octaFirst = { "", "1", "10", "11", "100", "101", "110", "111" };
		String[] octaAfter = { "000", "001", "010", "011", "100", "101", "110", "111" };
				
		String octa = in.readLine();
		
		if("0".equals(octa)) {
			System.out.print("0");
			return;
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(octaFirst[octa.charAt(0) - '0']);
		
		for(int i = 1; i < octa.length(); i++) {
			sb.append(octaAfter[octa.charAt(i) - '0']);
		}
		
		System.out.print(sb);
	}

}
