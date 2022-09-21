import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_1550_16진수 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
        char[] hexa = in.readLine().toCharArray();
        
        int num = 0, res = 0;
        for(int i = hexa.length - 1; i >= 0; i--) {
            if(hexa[i] >= 65)
                num = (hexa[i] - 'A' + 10);
            else
                num = (hexa[i] - '0');
            
            res += num * Math.pow(16, hexa.length - 1 - i);
        }
		
        System.out.println(res);
        
		in.close();
	}
	
}