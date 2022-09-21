import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 오래 걸림 - 추후 최적화 코드로 다시 짤것
// Memory:303452KB / Time:1320ms
public class BJ_20055_컨베이어_벨트_위의_로봇_1 {

	private static int N, N2, K, numOfZero;
	private static List<Node> conbelt;
	private static Queue<Integer> robotPos;
	
	public static void main(String[] args) throws Exception {
		input();
		
		int step = 0;
		while(numOfZero < K) {
			rotate();
			
			int numOfRobot = robotPos.size();
			for(int i = 1; i <= numOfRobot; i++) {
				moveRobot();				
			}
			
			putRobotOnConbelt();
			
			step++;
		}
		
		System.out.print(step);
	}
	
	// 회전
	private static void rotate() {
		conbelt.add(1, conbelt.remove(N2));
		
		// 로봇 내리기
		conbelt.get(N).isExistRb = false;
		
		int numOfRobot = robotPos.size();
		
		for(int i = 1; i <= numOfRobot; i++) {
			int nextPos = robotPos.poll() + 1;
			
			if(nextPos < N)
				robotPos.offer(nextPos);
		}
	}
	
	// 로봇 이동
	private static void moveRobot() {
		int currPos = robotPos.poll();
		Node nextPos = conbelt.get(currPos + 1);
		
		if(nextPos.isExistRb || nextPos.durabil == 0) {
			robotPos.offer(currPos);
			return;
		}
		
		conbelt.get(currPos).isExistRb = false;
		if(--nextPos.durabil == 0) numOfZero++;
		
		if(currPos + 1 == N) return; // N지점 도착시 로봇 바로 내림
		
		nextPos.isExistRb = true;
		robotPos.offer(currPos + 1);
	}
	
	// 로봇 올리기
	private static void putRobotOnConbelt() {
		Node onePos = conbelt.get(1);
		
		if(onePos.durabil == 0) return;
		
		if(--onePos.durabil == 0) numOfZero++;
		onePos.isExistRb = true;
		robotPos.add(1);
	}
	
	private static void input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		N2 = N * 2;
		
		conbelt = new LinkedList<>();
		conbelt.add(new Node(0, false)); // 0:dummy;
		
		st = new StringTokenizer(in.readLine());
		for(int i = 1; i <= N2; i++) {
			conbelt.add(new Node(Integer.parseInt(st.nextToken()), false));
		}
		
		robotPos = new LinkedList<>();
		
		in.close();
	}
	
	static class Node {
		int durabil;
		boolean isExistRb;
		
		public Node(int durabil, boolean isExistRb) {
			this.durabil = durabil;
			this.isExistRb = isExistRb;
		}

		@Override
		public String toString() {
			return "[durabil=" + durabil + "]";
		}
		
	}
}
