package notSolved;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_20055_컨베이어벨트위의로봇 {

	private static int N, N2, K;
	
	private static List<Node> conBelt;
	private static Queue<Integer> robotPosList;
	
	public static void main(String[] args) throws Exception {
		input();
		
		int res = solve();
		
		System.out.print(res);
	}
	
	private static int solve() {
		int res = 0, queueSize, robotPos, numOfZero = 0;
		Node currConBelt;
		
		// 첫번째 칸에 로봇을 올릴 수 있으면 로봇 올리고 내구도가 0이 되면 numOfZero증가
		if( putRobotToConBelt() ) numOfZero++;
		
		while(numOfZero < K) {
			res++; // 단계 진행
			
			rotateConBelt(); // 컨테이너 벨트 회전
			
			queueSize = robotPosList.size();
			for(int i = 0; i < queueSize; i++) {
				robotPos = robotPosList.poll();
				
				currConBelt = conBelt.get(robotPos + 1);
				
				// 이동하려는 위치에 로봇이 있거나 내구도가 0이면 패스
				if( currConBelt.isExistRobot || currConBelt.durability == 0 ) continue;
				
				// 로봇 옮기고 옮긴 위치 내구도 감소
				conBelt.get(robotPos).isExistRobot = false;
				currConBelt.durability--;

				// 내구도 0되면 개수 증가
				if(currConBelt.durability == 0) numOfZero++;
				
				robotPos++;
				
				// N 위치가 아니면 
				if(robotPos != N) {
					currConBelt.isExistRobot = true;
					robotPosList.offer(robotPos);					
				}
			}
			
			// 로봇 올리기
			if( putRobotToConBelt() ) numOfZero++;
		}
		
		return res;
	}
	
	private static void rotateConBelt() {
		conBelt.add(1, conBelt.remove(N2)); // 컨베이너 벨트 이동
		conBelt.get(N).isExistRobot = false; // 로봇 내리기
		
		// 큐 로봇 위치도 업데이트
		int queueSize = robotPosList.size();
		for(int i = 0; i < queueSize; i++) {
			int robotPos = robotPosList.poll() + 1;
			
			if(robotPos != N) robotPosList.offer(robotPos);
		}
	}
	
	// 로봇 내리기
	private static void takeOffRobot() {
		Node currConBelt = conBelt.get(N);
		
		if( ! currConBelt.isExistRobot ) return;
		
		currConBelt.isExistRobot = false;
		robotPosList.poll();
	}
	
	// 로봇 올리고 내구도가 0면 true, 아니면 false 리턴
	private static boolean putRobotToConBelt() {
		Node currConBelt = conBelt.get(1);
		robotPosList.offer(1);
		
		// 로봇을 올릴 수 없으면 false
		if( currConBelt.isExistRobot || currConBelt.durability == 0) return false;
				
		currConBelt.isExistRobot = true;
		currConBelt.durability--;
		
		if(currConBelt.durability == 0) return true;
		
		return false;
	}
	
	private static void input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		N2 = 2 * N;
		
		conBelt = new LinkedList<>();
		conBelt.add(new Node(0, false)); // dummy;
		
		st = new StringTokenizer(in.readLine());
		for(int i = 1; i <= N2; i++) {
			conBelt.add(new Node(Integer.parseInt(st.nextToken()), false));
		}
		
		robotPosList = new LinkedList<>(); // 로봇 위치 리스트
	}
	
	static class Node {
		int durability;
		boolean isExistRobot;
		
		public Node(int durability, boolean isExistRobot) {
			this.durability = durability;
			this.isExistRobot = isExistRobot;
		}
	}
}
