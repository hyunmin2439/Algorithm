package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2525_오븐시계 {

	public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        
        int hour = Integer.parseInt(st.nextToken());
        int minute = Integer.parseInt(st.nextToken());
        int cookingTime = Integer.parseInt(in.readLine());
        
        int addHour = (minute + cookingTime) / 60;
        minute = (minute + cookingTime) % 60;
        hour = (hour + addHour) % 24;
        
        System.out.println(hour + " " + minute);
    }
}