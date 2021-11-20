import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1145_적어도대부분의배수 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int arr[] = new int[5];
		int min = Integer.MAX_VALUE;
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int i = 0; i < 5; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			min = Math.min(arr[i], min);
		}
		
		int cnt = 0, num = min;
		while(true) {
			cnt = 0;
			
			for(int i = 0; i < 5; i++) {
				if(num % arr[i] == 0) cnt++;
			}
			
			if(cnt >= 3) {
				System.out.print(num);
				break;
			}
			
			num++;
		}
		
		
		in.close();
	}

}
