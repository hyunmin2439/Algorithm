import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 비트 시프트 방식의 풀이법
 * 
 * 입력
 * 12시부터 시계 방향으로 입력이 들어오니 문자열을 2진수로 입력 받는다.
 * 
 * 12시부터 순서대로 i(7 6 5 4 3 2 1 0) getNS 메서드의 1 << i == 0 ? : 0 : 1 로직으로 NS 극을 찾는다.
 * 
 * dfs 방식으로 회전할 톱니 목록을 list에 저장 후 한번에 회전
 * 
 * 회전시에도 마찬가지로 비트시프트 방식을 사용하여 회전
 * 
 * 시계 방향으로 회전시에는 제일 마지막 자리(i:0)에 1이 있으면 제일 상단 자리(i:7)로 1이 옮겨져야 함.
 * ex) 0100_0001 -> 1_0100_0001 -> 1010_0000
 * 
 * 
 * 반시계 방향으로 회전시에는 제일 상단 자리(i:7)에 1이 있으면 제일 마지막 자리(i:0)로 1이 옮겨져야 함.
 * ex) 1000_0000 -> 1_0000_0000 -> 0000_0001
 * 
 * 
 * Memory: 14,424KB / Time: 128ms
 */

public class BJ_14891_톱니바퀴 {

	private static int gear[], K, inst[][];
	private static List<Node> rotateList;

	public static void main(String[] args) throws Exception {
		input();
		
		solve(); 
	}
	
	private static void input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		gear = new int[4];
		for (int i = 0; i < 4; i++) {
			gear[i] = Integer.parseInt(in.readLine(), 2); // 2진수로 입력받기
		}

		K = Integer.parseInt(in.readLine());
		inst = new int[K][2];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(in.readLine());

			inst[i][0] = Integer.parseInt(st.nextToken()) - 1; // 0 ~ 3 톱니바퀴
			inst[i][1] = Integer.parseInt(st.nextToken());
		}

		rotateList = new ArrayList<>();

		in.close();
	}

	private static void solve() {
		int num, dir;
		
		// 회전 요청 담기
		for (int i = 0; i < K; i++) {
			rotateList.add(new Node(inst[i][0], inst[i][1]));
			
			num = inst[i][0];
			dir = inst[i][1];

			if (inst[i][0] > 0)
				dfs(num - 1, getNS(num, 1), dir * -1, -1); // 좌측 톱니로
			
			if (inst[i][0] < 3)
				dfs(num + 1, getNS(num, 5), dir * -1, 1); // 우측 톱니로

			// 회전
			for (int j = 0; j < rotateList.size(); j++) {
				rotate(rotateList.get(j));
			}
			
			// 회전 목록 초기화
			rotateList.clear();
		}

		// 점수 계산
		int score = 0;
		for (int i = 0; i < 4; i++) {
			if (getNS(i, 7) == 1)
				score += Math.pow(2, i);
		}

		System.out.print(score);
	}
	
	private static int getNS(int num, int pos) {
		return (gear[num] & 1 << pos) == 0 ? 0 : 1;
	}

	private static void dfs(int num, int prevNS, int dir, int sw) {
		if (!(0 <= num && num <= 3))
			return;

		if ( getNS(num, sw == -1 ? 5 : 1) == prevNS )
			return;

		rotateList.add(new Node(num, dir));

		dfs(num + sw, getNS(num, sw == -1 ? 1 : 5), dir * -1, sw);
	}
	
	private static void rotate(Node rotateInfo) {
		if (rotateInfo.dir == -1) { // 반 시계 방향
			gear[rotateInfo.num] <<= 1; // 1000_0000 -> 1_0000_0000
			if (gear[rotateInfo.num] > 255)
				gear[rotateInfo.num] -= 255; // 1_0000_0000 -> 0000_0001
		} else { // 시계 방향
			if ((gear[rotateInfo.num] & 1) == 1)
				gear[rotateInfo.num] += 256; // 0100_0001 -> 1_0100_0001
			gear[rotateInfo.num] >>= 1; // 1_0100_0001 -> 1010_0000
		}
	}
	
	private static void printGear(int num) {
		for (int i = 7; i >= 0; i--) {
			if ((gear[num] & (1 << i)) == 1 << i)
				System.out.print(1);
			else
				System.out.print(0);
		}
		System.out.println();
	}

	static class Node {
		int num, dir;

		public Node(int num, int dir) {
			this.num = num;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "Node [num=" + num + ", dir=" + dir + "]";
		}
		
	}
}
