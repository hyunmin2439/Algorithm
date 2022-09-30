import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        Map<Integer, Integer> benefit = new HashMap<>();
        Map<String, Integer> idx = new HashMap<>();
        int[] answer = new int[enroll.length], parent = new int[enroll.length + 1];
        
        init(benefit, idx, parent, enroll, referral);
        
        // 판매액 누적해서 계산하면 소수 점 아래 절삭되는 부분이 달라져 누적해서 계산하면 값이 다르다.
        
        // 판매 이익 나누기
        for(int i = 0; i < seller.length; i++) {
            sell(benefit, parent, idx.get(seller[i]), amount[i] * 100);
        }
        
        // 판매 이익 담기
        for(int i = 0; i < enroll.length; i++) {
            answer[i] = benefit.get(i + 1);
        }
        
        return answer;
    }
    
    private void init(Map<Integer, Integer> benefit, Map<String, Integer> idx, int[] parent, String[] enroll, String[] referral) {
        parent[0] = 0;
        idx.put("-", 0);
        benefit.put(0, 0);
        for(int i = 0; i < enroll.length; i++) {
            parent[i + 1] = i + 1;
            idx.put(enroll[i], i + 1);
            benefit.put(i + 1, 0);
        }
        
        for(int i = 0; i < referral.length; i++) {
            parent[ idx.get(enroll[i]) ] = idx.get(referral[i]);
        }
    }
    
    private void sell(Map<Integer, Integer> benefit, int[] parent, int x, int price) {
        int div = price / 10;
        price -= div;
        
        if(parent[x] == x) {
            benefit.put(x, benefit.get(x) + price + div);
            return;
        }
        
        benefit.put(x, benefit.get(x) + price);
        
        if(div > 0) // 배분할 이익이 없으면 재귀 돌지 말 것 => 시간 초과
            sell(benefit, parent, parent[x], div);
    }
}