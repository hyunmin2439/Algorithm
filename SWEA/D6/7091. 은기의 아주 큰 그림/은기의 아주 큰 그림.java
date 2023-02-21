import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	
	private static final int MAX_LEN = 2000;
	private static final int rPowNum = 31, cPowNum = 37;
	
	private static long[][] ep, tp;
	private static long[] hashLine, rPow, cPow;
	private static int H, W, N, M;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		String line;
		long eHash, tHash;
		int t = 0, T, ans;
		
		rPow = initPow(rPowNum);
		cPow = initPow(cPowNum);
		hashLine = new long[MAX_LEN];
		ep = new long[MAX_LEN][MAX_LEN];
		tp = new long[MAX_LEN][MAX_LEN];
		
		T = Integer.parseInt(in.readLine());
		
		while(t++ < T) {
			st = new StringTokenizer(in.readLine());
			
			ans = 0;
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			for(int r = 0; r < H; r++) {
				line = in.readLine();
				for(int c = 0; c < W; c++) {
					ep[r][c] = line.charAt(c) == 'o' ? 1 : 0;
				}
			}
			
			for(int r = 0; r < N; r++) {
				line = in.readLine();
				for(int c = 0; c < M; c++) {
					tp[r][c] = line.charAt(c) == 'o' ? 1 : 0;
				}
			}
			
			// 은기 해쉬값
			for(int r = 0; r < H; r++) {
				hashLine[r] = getHash(ep[r], rPow, W);
			}
			eHash = getHash(hashLine, cPow, H);
			
			// 선생님 가로 해쉬값 미리 다 구하기
			for(int r = 0; r < N; r++) {
				hashLine[r] = getHash(tp[r], rPow, W);
			}
			
			// 구해진 세로 해시 값으로 같은 그림인지 확인
			tHash = getHash(hashLine, cPow, H);
			if(eHash == tHash) ans++;
			
			// 세로 해시 값 아래로 rolling하며 같은 그림인지 확인
			ans += rollingColumnHash(eHash, tHash);
			
			// 가로 해시 값 rolling 하면서 반복
			for(int c = 0; c < M - W; c++) {
				rollingRowHash(c);
				
				tHash = getHash(hashLine, cPow, H);
				if(eHash == tHash) ans++;
				
				ans += rollingColumnHash(eHash, tHash);
			}
			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		
		out.write(sb.toString());
		out.flush();
	}
	
	private static long[] initPow(long b) {
		long[] pow = new long[MAX_LEN];
		
		for(int i = 0; i < MAX_LEN; i++) {
			pow[i] = pow(b, i);
		}
		
		return pow;
	}
	
	private static long pow(long b, int e) {
		long res = 1;
		
		while(e > 0) {
			if((e & 1) == 1)
				res *= b;
			b *= b;
			e >>= 1;
		}
		
		return res;
	}
	
	private static long getHash(long[] pic, long[] pow, int HW) {
		long hash = 0;
		int e;
		
		for(int rc = 0; rc < HW; rc++) {
			e = HW - rc - 1;
			hash = hash + pic[rc] * pow[e];
		}
		
		return hash;
	}
	
	private static int rollingColumnHash(long eHash, long tHash) {
		int cnt = 0;
		
		for(int r = 0; r < N - H; r++) {
			tHash = tHash - hashLine[r] * cPow[H - 1];
			tHash = tHash * cPowNum + hashLine[H + r];
			if(eHash == tHash) cnt++;
		}
		
		return cnt;
	}
	
	private static void rollingRowHash(int c) {
		for(int r = 0; r < N; r++) {
			hashLine[r] = hashLine[r] - tp[r][c] * rPow[W - 1];
			hashLine[r] = hashLine[r] * rPowNum + tp[r][W + c];
		}
	}
}