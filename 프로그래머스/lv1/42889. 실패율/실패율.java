import java.util.List;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Arrays;

class Solution {
    public int[] solution(int N, int[] stages) {
        int deno = stages.length, cnt[] = new int[N + 2], answer[] = new int[N];
        
        for(int i = 0; i < stages.length; i++)
            cnt[ stages[i] ]++;
        
        List<Stage> list = new LinkedList<>();
        
        for(int i = 1; i <= N; i++) {
            deno -= cnt[i - 1];
            
            // 분모가 0이 되면 DivideZero Error 발생
            if(deno > 0)
                list.add( new Stage(i, (double) cnt[i] / deno) );
            else
                list.add( new Stage(i, 0) ); 
        }
        
        Collections.sort(list);
        
        for(int i = 0; i < N; i++) {
            answer[i] = list.get(i).num;
        }
        
        return answer;
    }
}

class Stage implements Comparable<Stage> {
    int num;
    double fail;
    
    public Stage(int num, double fail) {
        this.num = num;
        this.fail = fail;
    }
    
    @Override
    public int compareTo(Stage other) {
        // 실패율이 같으면 위치를 안바꾼다. 
        // num 순서대로 list에 넣기 때문에 따로 코드 추가 불필요
        
        return Double.compare(other.fail, this.fail);
    }
}