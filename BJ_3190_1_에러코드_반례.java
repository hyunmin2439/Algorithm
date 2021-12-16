package solved.submit;

import java.io.IOException;

public class BJ_3190_1_에러코드_반례 {

	static int R, C, cnt;
	static boolean[][] map;
	
	public static void main(String[] args) throws IOException {

	}
	
	private static void findPipeLine(int y, int x) {

		map[y][x] = true; // 현재 길 사용
		
		// 기저 조건
		if(x >= C - 1) {
			cnt++;
			return;
		}
		
//		세 길중에 한 한길로만 가서 끝낼 경우(아래가 반례가 될 듯)
//		    ->X
//		 ->O->X ...
//		O   ->X 세 방향 탐색을 하고 이 길은 못간다하고 끝내 버린다.
//		      O
		
//		      X
//		    ->X ...
//		O->O->X 
//	     	->O 바로 오른쪽으로 갔으면 갈 수 있는 길이 있다.
		
		// 이 세가지 길중에 한 곳으로만 가야한다.
		if( y - 1 >= 0 && !map[y - 1][x + 1] ) 
			findPipeLine(y - 1, x + 1); // 오른쪽 윗길
		
		else if( !map[y][x + 1] ) 
			findPipeLine(y, x + 1);	// 오른쪽 길
		
		else if( y + 1 < R && !map[y + 1][x + 1] ) 
			findPipeLine(y + 1, x + 1); // 오른쪽 아랫길
		
	}
	
}
