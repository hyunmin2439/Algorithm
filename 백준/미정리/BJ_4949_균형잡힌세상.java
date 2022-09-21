import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

// Memory:18908KB / Time:268ms
public class BJ_4949_균형잡힌세상 {

	static char[] bracket = { '[', '(', ']', ')' };
	
	static Stack<Character> stack;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		stack = new Stack<>();
		
		String line = null;
		int length = 0;
		
		while(true) {
			line = in.readLine();
			
			if(line.equals(".")) break;
			
			length = line.length();
			
			for(int i = 0; i < length; i++) {
				char c = line.charAt(i);
				
				if( !isCorrectBracket(c) ) {
					// (]와 같이 맞지 않은 괄호로 false가 리턴될 수도 있음. 
					// 하지만 stack은 빈상태로 맞는 괄호라고 판단하는 것을 방지
					stack.push('('); 
					break;
				}
			}
			
			// stack이 비어 있어야 균형을 이룬 문자열
			if( stack.isEmpty() )
				sb.append("yes").append("\n");
			else
				sb.append("no").append("\n");
			
			stack.clear();
		}
		
		System.out.print(sb);
		
		in.close();
	}

	private static boolean isCorrectBracket(char c) {
		for(int j = 0; j < bracket.length; j++) {
			if(c == bracket[j]) {
				if(j < 2) {
					stack.push(c);
					break;
				}
				else if(!stack.isEmpty() && stack.pop() == bracket[j - 2]) {
					break;
				}
				else 
					return false;
			}
		}
		
		return true;
	}

}
