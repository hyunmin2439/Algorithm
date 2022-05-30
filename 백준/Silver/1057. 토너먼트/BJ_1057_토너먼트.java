import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Memory:14276KB / Time:136ms
public class BJ_1057_토너먼트 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		st.nextToken(); // N입력, 사용하지 않음
		
		int jiminNum = Integer.parseInt(st.nextToken());
		int hansuNum = Integer.parseInt(st.nextToken());
		
		int round = 1;
		
		while(true) {
			// 번호가 홀수 + 1 == 짝수이면 경기하는 것.
			if( jiminNum % 2 == 1 && jiminNum + 1 == hansuNum ) break;
			if( hansuNum % 2 == 1 && hansuNum + 1 == jiminNum ) break;
			
			// 홀수면 더해서
			if( jiminNum % 2 == 1 ) jiminNum++;
			if( hansuNum % 2 == 1 ) hansuNum++;
			
			// 반으로 나누기
			jiminNum /= 2;
			hansuNum /= 2;
			
			// 라운드 증가
			round++;
		}
		
		System.out.print(round);
		in.close();
	}

}
