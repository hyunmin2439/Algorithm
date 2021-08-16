package solved;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

// memory : 27832KB  time : 360ms
public class Baekjoon1874 {

	static int N;
	static Stack<Integer> stack = new Stack<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
//		num : stack에 차례차례 담길 숫자
		for (int i = 0, num = 1; i < N; i++) {
			int input = Integer.parseInt(br.readLine());

//			num 숫자까지 stack 담기
//			j가 N보다 커지면 num에는 N보다 큰 숫자가 안들어오기 때문에 동작안함
			while (input >= num) {
				stack.push(num++);
				sb.append("+\n");
			}

//			num이 stack에 top 숫자랑 같지 않을 경우 만들 수 없는 수열
			if (input == stack.pop()) sb.append("-\n");
			else {
				sb.setLength(0);
				sb.append("NO");
				break;
			}
		}
		
		bw.write(sb.toString());
		br.close();
		bw.close();
	}

}
