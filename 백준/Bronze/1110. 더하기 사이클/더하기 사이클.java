import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) {
		int N; // 입력
		int tenDit, oneDit, sumDit, resDit;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		try {
			N = Integer.parseInt(br.readLine());
			resDit = N;
			int cnt = 0;
			
			do {
				// 조건1 ~ 2
				tenDit = resDit / 10;
				oneDit = resDit % 10;
				sumDit = tenDit + oneDit;
				
				// 조건 3
				tenDit = oneDit * 10;
				oneDit = sumDit % 10;
				resDit = tenDit + oneDit;
				
				cnt++;
				
			}while(N != resDit);
			
			bw.write( String.valueOf(cnt) );
			bw.flush();
			
			br.close();
			bw.close();
						
		} catch (Exception e) {
			
		}

	}

}