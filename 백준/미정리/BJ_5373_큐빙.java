package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 큐브 도면을 그려 직접 회전 시켜보면서 옮겨지는 위치 확인
 * 
 * 2차원 배열로 문자를 담을 시 더 헷갈릴 것 같아 1차원 배열로 선언
 * 
 * 아래와 같은 도면으로 구분
 * 
 * 눈으로 볼 수 있는 print메서드를 만들어 각 회전마다 테스트
 * 
 *     B
 *     123
 *     456
 *     789
 * L   U   R
 * 741 123 369
 * 852 456 258
 * 963 789 147
 *     D
 *     123
 *     456
 *     789
 *     B
 *     123
 *     456
 *     789
 *     
 * Memory: 20,460KB / Time: 244ms 
 */

public class BJ_5373_큐빙 {
	
	private static final int U = 0, D = 1, F = 2, B = 3, L = 4, R = 5;
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(in.readLine());
		
		int n;
		Node[] cube;
		while(T-- > 0) {
			n = Integer.parseInt(in.readLine());
			st = new StringTokenizer(in.readLine());
			
			cube = initCube(); // 큐브 초기화
			
			for(int i = 0; i < n; i++) {
				String inst = st.nextToken();
				
				// 여기에서 각 명령어 마다 회전
				rotate(cube, inst);
				
//				System.out.println("-------------" + inst + "-------------------");
//				printCube(cube);
			}
			
			for(int i = 1; i <= 9; i += 3) {
				
				for(int j = 0; j < 3; j++) {
					System.out.print(cube[U].p[i + j]);
				}
				System.out.println();
				
			}
		}
		
		in.close();
		
		// Test		
//		Node[] cube = initCube();
//		
//		for(char c = '1'; c <= '9'; c++) {
//			cube[R].p[c - '0'] = c;
//		}
//		
//		rotate(cube, "R+");
//		
//		printCube(cube);
	}
	
	private static Node[] initCube() {
		Node[] cube = new Node[6];
		
		cube[U] = new Node('w');
		cube[D] = new Node('y');
		cube[F] = new Node('r');
		cube[B] = new Node('o');
		cube[L] = new Node('g');
		cube[R] = new Node('b');
		
		return cube;
	}
	
	private static void rotate(Node[] cube, String direc) {
		// 면을 바라봤을 때 기준으로 시계, 반시계 주의

		switch(direc) {
		// 윗면
		case "U+":
			posRightRotate(cube[U]);
			upRight(cube); 
			break;
		case "U-": 
			posLeftRotate(cube[U]);
			upLeft(cube); 
			break;
		// 아랫 면
		case "D+":
			posRightRotate(cube[D]);
			downRight(cube);
			break;
		case "D-": 
			posLeftRotate(cube[D]);
			downLeft(cube); 
			break;
		// 앞면
		case "F+": 
			posRightRotate(cube[F]);
			frontRight(cube);
			break;
		case "F-": 
			posLeftRotate(cube[F]);
			frontLeft(cube); 
			break;
		// 뒷면
		case "B+": 
			posRightRotate(cube[B]);
			backRight(cube);
			break;
		case "B-": 
			posLeftRotate(cube[B]);
			backLeft(cube); 
			break;
		// 왼쪽면
		case "L+": 
			posRightRotate(cube[L]);
			leftRight(cube);
			break;
		case "L-": 
			posLeftRotate(cube[L]);
			leftLeft(cube); 
			break;
		// 오른쪽면
		case "R+": 
			posRightRotate(cube[R]);
			rightRight(cube);
			break;
		case "R-": 
			posLeftRotate(cube[R]);
			rightLeft(cube); 
			break;
		}
	}
	
	private static void posLeftRotate(Node cube) {
		char tmp;
		
		tmp = cube.p[1];
		cube.p[1] = cube.p[3];
		cube.p[3] = cube.p[9];
		cube.p[9] = cube.p[7];
		cube.p[7] = tmp;
		
		tmp = cube.p[2];
		cube.p[2] = cube.p[6];
		cube.p[6] = cube.p[8];
		cube.p[8] = cube.p[4];
		cube.p[4] = tmp;
	}
	
	private static void posRightRotate(Node cube) {
		char tmp;
		
		tmp = cube.p[1];
		cube.p[1] = cube.p[7];
		cube.p[7] = cube.p[9];
		cube.p[9] = cube.p[3];
		cube.p[3] = tmp;
		
		tmp = cube.p[2];
		cube.p[2] = cube.p[4];
		cube.p[4] = cube.p[8];
		cube.p[8] = cube.p[6];
		cube.p[6] = tmp;
	}
	
	// direc 0 왼쪽 1 오른쪽
	private static void upRight(Node[] cube) {
		char tmp;

		tmp = cube[B].p[9];
		cube[B].p[9] = cube[L].p[1];
		cube[L].p[1] = cube[F].p[1];
		cube[F].p[1] = cube[R].p[1];
		cube[R].p[1] = tmp;
		
		tmp = cube[B].p[8];
		cube[B].p[8] = cube[L].p[2];
		cube[L].p[2] = cube[F].p[2];
		cube[F].p[2] = cube[R].p[2];
		cube[R].p[2] = tmp;
		
		tmp = cube[B].p[7];
		cube[B].p[7] = cube[L].p[3];
		cube[L].p[3] = cube[F].p[3];
		cube[F].p[3] = cube[R].p[3];
		cube[R].p[3] = tmp;	
	}
	
	private static void upLeft(Node[] cube) {
		char tmp;

		tmp = cube[B].p[9];
		cube[B].p[9] = cube[R].p[1];
		cube[R].p[1] = cube[F].p[1];
		cube[F].p[1] = cube[L].p[1];
		cube[L].p[1] = tmp;
		
		tmp = cube[B].p[8];
		cube[B].p[8] = cube[R].p[2];
		cube[R].p[2] = cube[F].p[2];
		cube[F].p[2] = cube[L].p[2];
		cube[L].p[2] = tmp;
		
		tmp = cube[B].p[7];
		cube[B].p[7] = cube[R].p[3];
		cube[R].p[3] = cube[F].p[3];	
		cube[F].p[3] = cube[L].p[3];
		cube[L].p[3] = tmp;
	}
	
	private static void downLeft(Node[] cube) {
		char tmp;

		tmp = cube[B].p[1];
		cube[B].p[1] = cube[L].p[9];
		cube[L].p[9] = cube[F].p[9];
		cube[F].p[9] = cube[R].p[9];
		cube[R].p[9] = tmp;
		
		tmp = cube[B].p[2];
		cube[B].p[2] = cube[L].p[8];
		cube[L].p[8] = cube[F].p[8];
		cube[F].p[8] = cube[R].p[8];
		cube[R].p[8] = tmp;
		
		tmp = cube[B].p[3];
		cube[B].p[3] = cube[L].p[7];
		cube[L].p[7] = cube[F].p[7];
		cube[F].p[7] = cube[R].p[7];
		cube[R].p[7] = tmp;	
	}

	private static void downRight(Node[] cube) {
		char tmp;

		tmp = cube[B].p[1];
		cube[B].p[1] = cube[R].p[9];
		cube[R].p[9] = cube[F].p[9];
		cube[F].p[9] = cube[L].p[9];
		cube[L].p[9] = tmp;
		
		tmp = cube[B].p[2];
		cube[B].p[2] = cube[R].p[8];
		cube[R].p[8] = cube[F].p[8];
		cube[F].p[8] = cube[L].p[8];
		cube[L].p[8] = tmp;
		
		tmp = cube[B].p[3];
		cube[B].p[3] = cube[R].p[7];
		cube[R].p[7] = cube[F].p[7];	
		cube[F].p[7] = cube[L].p[7];
		cube[L].p[7] = tmp;
	}
	
	private static void frontLeft(Node[] cube) {
		char tmp;
		
		// U7 = R1 = D3 = L9 = U7
		tmp = cube[U].p[7];
		cube[U].p[7] = cube[R].p[1];
		cube[R].p[1] = cube[D].p[3];
		cube[D].p[3] = cube[L].p[9];
		cube[L].p[9] = tmp;
		
		// U8 = R4 = D2 = L6 = U8
		tmp = cube[U].p[8];
		cube[U].p[8] = cube[R].p[4];
		cube[R].p[4] = cube[D].p[2];
		cube[D].p[2] = cube[L].p[6];
		cube[L].p[6] = tmp;
		
		// U9 = R7 = D1 = L3 = U9
		tmp = cube[U].p[9];
		cube[U].p[9] = cube[R].p[7];
		cube[R].p[7] = cube[D].p[1];
		cube[D].p[1] = cube[L].p[3];
		cube[L].p[3] = tmp;	
	}

	private static void frontRight(Node[] cube) {
		char tmp;

		// U7 = L9 = D3 = R1 = U7
		tmp = cube[U].p[7];
		cube[U].p[7] = cube[L].p[9];
		cube[L].p[9] = cube[D].p[3];
		cube[D].p[3] = cube[R].p[1];
		cube[R].p[1] = tmp;
		
		// U8 = L6 = D2 = R4 = U8
		tmp = cube[U].p[8];
		cube[U].p[8] = cube[L].p[6];
		cube[L].p[6] = cube[D].p[2];
		cube[D].p[2] = cube[R].p[4];
		cube[R].p[4] = tmp;
		
		// U9 = L3 = D1 = R7 = U9
		tmp = cube[U].p[9];
		cube[U].p[9] = cube[L].p[3];
		cube[L].p[3] = cube[D].p[1];
		cube[D].p[1] = cube[R].p[7];
		cube[R].p[7] = tmp;	
	}
	
	private static void backLeft(Node[] cube) {
		char tmp;

		// D7 = R9 = U3 = L1 = D7
		tmp = cube[D].p[7];
		cube[D].p[7] = cube[R].p[9];
		cube[R].p[9] = cube[U].p[3];
		cube[U].p[3] = cube[L].p[1];
		cube[L].p[1] = tmp;
		
		// D8 = R6 = U2 = L4 = D8
		tmp = cube[D].p[8];
		cube[D].p[8] = cube[R].p[6];
		cube[R].p[6] = cube[U].p[2];
		cube[U].p[2] = cube[L].p[4];
		cube[L].p[4] = tmp;
		
		// D9 = R3 = U1 = L7 = D9
		tmp = cube[D].p[9];
		cube[D].p[9] = cube[R].p[3];
		cube[R].p[3] = cube[U].p[1];
		cube[U].p[1] = cube[L].p[7];
		cube[L].p[7] = tmp;
	}

	private static void backRight(Node[] cube) {
		char tmp;
		
		// D7 = L1 = U3 = R9 = D7
		tmp = cube[D].p[7];
		cube[D].p[7] = cube[L].p[1];
		cube[L].p[1] = cube[U].p[3];
		cube[U].p[3] = cube[R].p[9];
		cube[R].p[9] = tmp;
		
		// D8 = L4 = U2 = R6 = D8
		tmp = cube[D].p[8];
		cube[D].p[8] = cube[L].p[4];
		cube[L].p[4] = cube[U].p[2];
		cube[U].p[2] = cube[R].p[6];
		cube[R].p[6] = tmp;
		
		// D9 = L7 = U1 = R3 = D9
		tmp = cube[D].p[9];
		cube[D].p[9] = cube[L].p[7];
		cube[L].p[7] = cube[U].p[1];
		cube[U].p[1] = cube[R].p[3];
		cube[R].p[3] = tmp;
	}
	
	private static void leftLeft(Node[] cube) {
		char tmp;

		// U1 = F1 = D1 = B1 = U1
		tmp = cube[U].p[1];
		cube[U].p[1] = cube[F].p[1];
		cube[F].p[1] = cube[D].p[1];
		cube[D].p[1] = cube[B].p[1];
		cube[B].p[1] = tmp;
		
		// U4 = F4 = D4 = B4 = U4
		tmp = cube[U].p[4];
		cube[U].p[4] = cube[F].p[4];
		cube[F].p[4] = cube[D].p[4];
		cube[D].p[4] = cube[B].p[4];
		cube[B].p[4] = tmp;
		
		// U7 = F7 = D7 = B7 = U7
		tmp = cube[U].p[7];
		cube[U].p[7] = cube[F].p[7];
		cube[F].p[7] = cube[D].p[7];
		cube[D].p[7] = cube[B].p[7];
		cube[B].p[7] = tmp;
	}

	private static void leftRight(Node[] cube) {
		char tmp;

		// U1 = B1 = D1 = F1 = U1
		tmp = cube[U].p[1];
		cube[U].p[1] = cube[B].p[1];
		cube[B].p[1] = cube[D].p[1];
		cube[D].p[1] = cube[F].p[1];
		cube[F].p[1] = tmp;
		
		// U4 = B4 = D4 = F4 = U4
		tmp = cube[U].p[4];
		cube[U].p[4] = cube[B].p[4];
		cube[B].p[4] = cube[D].p[4];
		cube[D].p[4] = cube[F].p[4];
		cube[F].p[4] = tmp;
		
		// U7 = B7 = D7 = F7 = U7
		tmp = cube[U].p[7];
		cube[U].p[7] = cube[B].p[7];
		cube[B].p[7] = cube[D].p[7];
		cube[D].p[7] = cube[F].p[7];
		cube[F].p[7] = tmp;
	}
	
	private static void rightLeft(Node[] cube) {
		char tmp;

		// U9 = B9 = D9 = F9 = U9
		tmp = cube[U].p[9];
		cube[U].p[9] = cube[B].p[9];
		cube[B].p[9] = cube[D].p[9];
		cube[D].p[9] = cube[F].p[9];
		cube[F].p[9] = tmp;
		
		// U6 = B6 = D6 = F6 = U6
		tmp = cube[U].p[6];
		cube[U].p[6] = cube[B].p[6];
		cube[B].p[6] = cube[D].p[6];
		cube[D].p[6] = cube[F].p[6];
		cube[F].p[6] = tmp;
		
		// U3 = B3 = D3 = F3 = U3
		tmp = cube[U].p[3];
		cube[U].p[3] = cube[B].p[3];
		cube[B].p[3] = cube[D].p[3];
		cube[D].p[3] = cube[F].p[3];
		cube[F].p[3] = tmp;
	}

	private static void rightRight(Node[] cube) {
		char tmp;

		// U9 = F9 = D9 = B9 = U9
		tmp = cube[U].p[9];
		cube[U].p[9] = cube[F].p[9];
		cube[F].p[9] = cube[D].p[9];
		cube[D].p[9] = cube[B].p[9];
		cube[B].p[9] = tmp;
		
		// U6 = F6 = D6 = B6 = U6
		tmp = cube[U].p[6];
		cube[U].p[6] = cube[F].p[6];
		cube[F].p[6] = cube[D].p[6];
		cube[D].p[6] = cube[B].p[6];
		cube[B].p[6] = tmp;
		
		// U3 = F3 = D3 = B3 = U3
		tmp = cube[U].p[3];
		cube[U].p[3] = cube[F].p[3];
		cube[F].p[3] = cube[D].p[3];
		cube[D].p[3] = cube[B].p[3];
		cube[B].p[3] = tmp;
	}
	
	private static void printCube(Node[] cube) {
		
		// B(뒤)
		for(int i = 1; i <= 9; i += 3) {
			for(int j = 0; j < 4; j++)
				System.out.print(" ");
			
			for(int j = 0; j < 3; j++)
				System.out.print(cube[B].p[i + j]);
			
			System.out.println();
		}
		
		System.out.println();
		
		// L U R
		System.out.print(cube[L].p[7] + "" + cube[L].p[4] + "" +  cube[L].p[1]);
		System.out.print(" ");
		System.out.print(cube[U].p[1] + "" + cube[U].p[2] + "" +  cube[U].p[3]);
		System.out.print(" ");
		System.out.print(cube[R].p[3] + "" + cube[R].p[6] + "" +  cube[R].p[9]);
		System.out.println();
		
		System.out.print(cube[L].p[8] + "" + cube[L].p[5] + "" +  cube[L].p[2]);
		System.out.print(" ");
		System.out.print(cube[U].p[4] + "" + cube[U].p[5] + "" +  cube[U].p[6]);
		System.out.print(" ");
		System.out.print(cube[R].p[2] + "" + cube[R].p[5] + "" +  cube[R].p[8]);
		System.out.println();
		
		System.out.print(cube[L].p[9] + "" + cube[L].p[6] + "" +  cube[L].p[3]);
		System.out.print(" ");
		System.out.print(cube[U].p[7] + "" + cube[U].p[8] + "" +  cube[U].p[9]);
		System.out.print(" ");
		System.out.print(cube[R].p[1] + "" + cube[R].p[4] + "" +  cube[R].p[7]);
		System.out.println();

		System.out.println();
		
		// F(앞)
		for(int i = 1; i <= 9; i += 3) {
			for(int j = 0; j < 4; j++)
				System.out.print(" ");
			
			for(int j = 0; j < 3; j++)
				System.out.print(cube[F].p[i + j]);
			
			System.out.println();
		}
		
		System.out.println();
		
		// 밑(뒤)
		for(int i = 1; i <= 9; i += 3) {
			for(int j = 0; j < 4; j++)
				System.out.print(" ");
			
			for(int j = 0; j < 3; j++)
				System.out.print(cube[D].p[i + j]);
			
			System.out.println();
		}
	}
	
	static class Node {
		char[] p;
		
		public Node(char color) {
			this.p = new char[10];
			Arrays.fill(this.p, color);
		}
	}
}
