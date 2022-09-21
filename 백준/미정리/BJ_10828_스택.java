package solved.submit;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * Input
 * n (1 <= n <= 10,000)
 * n String(push | pop | size | empty | top)
 * 
 * Process
 * stack method
 * Invoke stack method matching string
 * push : push()
 * pop : pop() & Output -1 if stack is empty
 * size : size()
 * empty : if( empty() ) sysout(1);
 * 		   else sysout(0);
 * top : peeK() & Output -1 if stack is empty
 * 
 */

public class BJ_10828_스택 {
	static Stack<String> stack;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out) );
		
		stack = new Stack<>();
		
		int n = Integer.parseInt( br.readLine() );
		
		StringBuilder sb = new StringBuilder();
		while(n-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String method = st.nextToken();
			sb.append( stackMethod( st, method ) );
		}
		
		bw.write(sb.toString());
		br.close();
		bw.close();
	}

	private static String stackMethod(StringTokenizer st, String method) {
		String result = "";
		switch(method) {
		case "push": 
			stack.push( st.nextToken() ); 
			break;
		case "pop":
			if( stack.empty() ) result = "-1";
			else result = stack.pop();
			break;
		case "size":
			result = String.valueOf( stack.size() );
			break;
		case "empty": 
			if( stack.empty() ) result = "1";
			else result = "0";
			break;
		case "top": 
			if( stack.empty() ) result = "-1";
			else result = stack.peek();
			break;
		}
		if( !method.equals("push") ) 
			result += "\n";
		
		return result;
	}

}
