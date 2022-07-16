import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N, classTable[][];
		boolean[][] isSameClass;
		
		N = Integer.parseInt(in.readLine());
		
		classTable = new int[N][5];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j = 0; j < 5; j++) {
				classTable[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		

		isSameClass = new boolean[N][N];
		checkSameClass(isSameClass, classTable, N); // 같은반 확인
		
		System.out.print( getClassLeader(isSameClass, N) );
	}
	
	private static void checkSameClass(boolean[][] isSameClass, int[][] classTable, int N) {
		for(int i = 0; i < N; i++) {
			for(int j = i + 1; j < N; j++) {
				for(int k = 0; k < 5; k++) {
					if(classTable[i][k] == classTable[j][k]) {
						isSameClass[i][j] = isSameClass[j][i] = true; // i랑 j랑 같은반
						break;
					}
				}
				
			}
		}
	}
	
	private static int getClassLeader(boolean[][] isSameClass, int N) {
		int cnt = 0 , max = 0, idx = 0;
		
		for(int i = 0; i < N; i++) {
			cnt = 0;
			for(int j = 0; j < N; j++) {
				if( isSameClass[i][j] ) cnt++;
			}
			
			if(max < cnt) {
				max = cnt;
				idx = i;
			}
		}
		
		return idx + 1;
	}
}