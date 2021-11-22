import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Memory:14716KB / 148ms
public class BJ_2484_주사위네개 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int maxMoney = 0;
		
		int N = Integer.parseInt(in.readLine());
		
		for(int i = 0; i < N; i++) {
			int[] dice = new int[6 + 1];
			
			StringTokenizer st = new StringTokenizer(in.readLine());
			for(int j = 0; j < 4; j++) {
				dice[Integer.parseInt(st.nextToken())]++;
			}
			
			int money = 0;
			for(int j = 1; j <= 6; j++) {
				
				if(dice[j] == 4) {
					money = 50000 + j * 5000;
					break;
				}
				else if(dice[j] == 3){
					money = 10000 + j * 1000;
					break;
				}
				else if(dice[j] == 2) {
					money = 0;
					for(int k = j + 1; k <= 6; k++) {
						if(dice[k] == 2) {
							money = 2000 + k * 500;
							break;
						}
					}
					money = money > 0 ? money + j * 500 : 1000 + j * 100;
					break;
				}
				else if(dice[j] == 1) {
					money = j * 100;					
				}
			}
			
			maxMoney = Math.max(maxMoney, money);
		}
		
		System.out.print(maxMoney);
		
		in.close();
	}

}
