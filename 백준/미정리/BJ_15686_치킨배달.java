package myAlgo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// first Solve
public class BJ_15686_치킨배달 {
	
	static int N, M, ckCnt, ckDist;
	static char[][] city;
	static List<Node> ckList;
	static int[] open;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ckList = new ArrayList<>();
		ckDist = Integer.MAX_VALUE;
		
		// input & init
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		city = new char[N][N];
		
		// city input & ckList add
		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < N; x++) {
				city[y][x] = st.nextToken().charAt(0);
				
				// 치킨집 위치, 치킨점 총 개수
				if(city[y][x] == '2') {
					ckCnt++;
					ckList.add(new Node(y, x));
				}
			}
		}
		
		// open init
		open = new int[ckCnt];
		for (int i = ckCnt - 1; i >= ckCnt - M; i--) {
			open[i] = 1;
		}
		
		// nextPermitation
		do {
			int sum = 0;
			// 도시 순회
			for (int y = 0; y < N; y++) {
				for (int x = 0; x < N; x++) {
					if (city[y][x] == '1') // 집이면 
						sum += ckSum(y, x); // 집 점수 계산
				}
			}
			ckDist = Math.min(sum, ckDist); // 최소값인지 확인
		} while (np(open));

		System.out.print(ckDist);
		br.close();
	}

	private static int ckSum(int y, int x) {
		int minDist = Integer.MAX_VALUE;
		
		for (int i = 0; i < ckCnt; i++) {
			if(open[i] == 1) {
				// 치킨집 리스트에서 영업중인 치킨집이면
				Node temp = ckList.get(i);
				// 거리 계산
				int dist = Math.abs(y - temp.y) + Math.abs(x - temp.x);
				// 이 거리가 이 집의 최소 치킨거리인지 확인
				minDist = Math.min(dist, minDist);
			}
		}
			
		return minDist;	
	}

	private static boolean np(int[] open) {
		
		int i = ckCnt - 1;
		while(i > 0 && open[i - 1] >= open[i]) i--;
		
		if(i == 0) return false;
		
		int j = ckCnt - 1;
		while(open[i - 1] >= open[j]) j--;
		
		swap(open, i - 1, j);
		
		int k = ckCnt - 1;
		while(i < k) {
			swap(open, i++, k--);
		}
		
		return true;
	}

	private static void swap(int[] open, int i, int j) {
		int temp = open[i];
		open[i] = open[j];
		open[j] = temp;
	}

	static class Node {
		int y;
		int x;
		
		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}

		// 디버깅용
		@Override
		public String toString() {
			return "Node [y=" + y + ", x=" + x + "]";
		}
		
	}
}
