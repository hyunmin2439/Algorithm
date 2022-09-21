import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Memory:13960KB / Time:116ms
public class BJ_5086_배수와약수 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		
		StringTokenizer st = null;
		int firstNumber = -1;
		int secondNumber = -1;
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			st = new StringTokenizer(in.readLine());
			firstNumber = Integer.parseInt(st.nextToken());
			secondNumber = Integer.parseInt(st.nextToken());
			
			if( firstNumber == 0 && secondNumber == 0 ) break;
			
			if( (firstNumber < secondNumber) && (secondNumber % firstNumber == 0) )
				sb.append("factor").append("\n");
			else if( (firstNumber > secondNumber) && (firstNumber % secondNumber == 0) )
				sb.append("multiple").append("\n");
			else
				sb.append("neither").append("\n");
		}
		
		System.out.println(sb);
		
		in.close();
	}

}
