package uploaded;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon11021 {

	public static void main(String[] args) {
		int T, A, B;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		try {
			T = Integer.parseInt(br.readLine());
			
			for(int i = 1; i <= T; i++) {
				// br.read()를 하면 한 글자씩 읽어옵니다. 
				// 입력 시 공백 부분도 읽어오기 때문에 3개의 br.read()가 필요하고 1개의 데이터는 유실 됩니다.	
				A = br.read() - 48;
				
				br.read();
				
				B = br.read() - 48;
				
				// java의 개행 문자는 carrige return과 줄띄움 \r\n 두글자가 있어서 read() 두개가 필요한 것 같다.
				//br.read();
				//br.read();
				br.readLine();
				
				bw.write("Case #"+ i + ": " + (A + B) + "\n");
			}
			
			bw.flush();
			
		} catch (Exception e) {
			
		}
	}

}
