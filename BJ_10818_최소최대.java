package solved;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// 		  0123456789012
// 입력 : 20 10 35 30 7
// br.read를 쓰면 안되는 이유
// String 형태이기 때문에 한글자씩 읽어서 각 데이터를 제대로 해석을 하지 못한다.

public class BJ_10818_최소최대 {

	public static void main(String[] args) {
		int N, num, max, min;
		String[] nums;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		try {
			N = Integer.parseInt( br.readLine() );
			nums = br.readLine().split(" ");
			
			// 0번째 값으로 초기화
			num = Integer.parseInt(nums[0]);
			max = num; min = num;
			
			for(int i = 1; i < nums.length; i++) {
				num = Integer.parseInt(nums[i]);
				
				if(num > max) max = num;
				else if(num < min) min = num;
			}
			
			bw.write(min + " " + max);
			bw.flush();
			
		} catch (Exception e) {
			// TODO: handle exception
		} 
		
	}
}
