import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * Divide-and-conquer
 * 
 * 문제에서 제시된 대로 수행하면 되는 문제.
 * 
 * 문제 성향에서 알 수 있듯 분할 정복 알고리즘이 적용되는 문제이다.
 * 
 * 시작, 끝 좌표 계산만 확실하게 한다면 어려울 것 없는 문제.
 * 
 * Memory: 16064KB / Time: 196ms
 */

public class BJ_2630_색종이만들기 {

	static final int WHITE = 0;
	
	static int N, numOfWhitePaper, numOfBluePaper;
	static int[][] paper;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		
		paper = new int[N][N];
		StringTokenizer st = null;
		for(int y = 0; y < N; y++) {
			st = new StringTokenizer(in.readLine());
			for(int x = 0; x < N; x++) {
				paper[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		divide(0, N, 0, N, N);
		
		System.out.print(numOfWhitePaper + "\n" + numOfBluePaper);
		
		in.close();
	}

	private static void divide(int sy, int ey, int sx, int ex, int n) {
		int currColor = paper[sy][sx];
		
//		System.out.println("sy:" + sy + " ey:" + ey + " sx:" + sx + " ex:" + ex + " n:" + n
//				+ " WHITE:" + numOfWhitePaper + " BLUE:" + numOfBluePaper);
		
		// 종이가 더 이상 나눌 수 없거나 모든 색이 같으면
		if( n == 1 || isAllSameColor(sy, ey, sx, ex) ) {
			// 삼항 연산자를 쓰기 위해 currColor에 담은 것 뿐, 의미 없음
			currColor = (currColor == WHITE) ? ++numOfWhitePaper : ++numOfBluePaper;
		}
		else {
			int dNum = n / 2;
			
//			System.out.println("--------------divide--------------");
			divide(sy, ey - dNum, sx, ex - dNum, dNum); // 4사분면
			divide(sy, ey - dNum, sx + dNum, ex, dNum); // 1사분면
			divide(sy + dNum, ey, sx, ex - dNum, dNum); // 2사분면
			divide(sy + dNum, ey, sx + dNum, ex, dNum); // 3사분면
		}
	}
	
	private static boolean isAllSameColor(int sy, int ey, int sx, int ex) {
		int currColor = paper[sy][sx];
		
		for(int y = sy; y < ey; y++) {
			for(int x = sx; x < ex; x++) {
				if(paper[y][x] != currColor) return false;
			}
		}
		
		return true;
	}

}
