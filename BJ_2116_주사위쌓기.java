import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Memory: 22,772KB / Time:232ms
public class BJ_2116_주사위쌓기 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		
		int prevUp[] = new int[6], curr[] = new int[6], sum[] = new int[6];
		
		// 0번 주사위 윗면 선택을 위해서 초기화
		for(int i = 0; i < 6; i++) {
			prevUp[i] = i + 1;
		}
		
		StringTokenizer st= null;
		// 0 ~ N - 1 주사위 
		for(int i = 0; i < N; i++) {
			st= new StringTokenizer(in.readLine());
			
			// 현재 주사위 값으로 갱신
			for(int j = 0; j < 6; j++) {
				curr[j] = Integer.parseInt(st.nextToken());
			}
			
			// 이전 주사위 6가지 방향에 따라
			for(int j = 0; j < 6; j++) {
				// 이전 윗면의 값과 현재 윗면 값이 같은지
				for(int k = 0; k < 6; k++) {
					if( prevUp[j] != curr[k] ) continue;
					
					int[] tmp = getUpNumAndSideMaxNum(curr, k);
					prevUp[j] = tmp[0]; // 현재 주사위 윗면 값으로 값 갱신
					sum[j] += tmp[1]; // 사이드 높은 값 더하기
					break;
				}
			}
		}
		
		in.close();
		
		int max = 0;
		for(int i = 0; i < 6; i++) {
			max = Math.max(max, sum[i]);
		}
		
		System.out.print(max);
	}

	private static int[] getUpNumAndSideMaxNum(int[] dice, int downIdx) {
		int upNum = 0, maxNum = 0;
		
		switch(downIdx) {
		case 0:
			upNum = dice[5]; 
			maxNum = getMaxNum(dice, 0, 5);
			break;
		case 1:
			upNum = dice[3]; 
			maxNum = getMaxNum(dice, 1, 3);
			break;
		case 2:
			upNum = dice[4]; 
			maxNum = getMaxNum(dice, 2, 4);
			break;
		case 3:
			upNum = dice[1]; 
			maxNum = getMaxNum(dice, 3, 1);
			break;
		case 4:
			upNum = dice[2]; 
			maxNum = getMaxNum(dice, 4, 2);
			break;
		case 5:
			upNum = dice[0]; 
			maxNum = getMaxNum(dice, 5, 0);
			break;
		}
		
		return new int[] { upNum, maxNum };
	}
	
	private static int getMaxNum(int[] dice, int downIdx, int upIdx) {
		int maxNum = 0;
		
		for(int i = 0; i < 6; i++) {
			if(i == downIdx || i == upIdx) continue;
			
			maxNum = Math.max(maxNum, dice[i]);
		}
		
		return maxNum;
	}
}
