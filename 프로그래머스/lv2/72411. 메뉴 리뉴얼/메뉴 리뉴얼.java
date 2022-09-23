import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Collections;

class Solution {
    public String[] solution(String[] orders, int[] course) {
        Map<String, Integer> menu = getAllMenu(orders, course);
        List<String> keyList = new ArrayList<>(menu.keySet());
        List<List<String>> answerList = new ArrayList<>();
        List<String> answer = new ArrayList<>();
        int[] maxOrder = new int[course.length];
        String key;
        int cnt;
        
        // init answerList
        for(int i = 0; i < course.length; i++) {
            answerList.add(new ArrayList<>());
        }
        
        // keyList를 돌면서 counting
        for(int i = 0; i < keyList.size(); i++) {
            for(int j = 0; j < orders.length; j++) {
                key = keyList.get(i);
                
                if( isContains(orders[j], key) )
                    menu.put(key, menu.get(key) + 1);
            }
        }
        
        for(int i = 0; i < keyList.size(); i++) {
            key = keyList.get(i);
            
            if(menu.get(key) < 2) continue;
            
            for(int j = 0; j < course.length; j++) {
                if(key.length() == course[j]) {
                    cnt = menu.get(key);
                    
                    if(cnt == maxOrder[j]) {
                        answerList.get(j).add(key);
                    }
                    else if(cnt > maxOrder[j]) {
                        maxOrder[j] = cnt;
                        answerList.get(j).clear();
                        answerList.get(j).add(key);
                    }
                }
            }
        }
        
        for(int i = 0; i < answerList.size(); i++) {
            answer.addAll(answerList.get(i));
        }
        
        Collections.sort(answer);
        
        return answer.toArray(new String[0]);
    }
    
    private boolean isContains(String order, String key) {
        for(int i = 0; i < key.length(); i++) {
            if( !order.contains( String.valueOf(key.charAt(i)) ) ) {
                return false;
            }
        }
        
        return true;
    }
    
    private Map<String, Integer> getAllMenu(String[] orders, int[] course) {
        Set<Character> set = new TreeSet<>();
        StringBuilder sb = new StringBuilder();
        Map<String, Integer> map = new LinkedHashMap<>();
        List<Character> list;
        char[] tmp;
        int flag = -1, endFlag = 0;
        
        for(int i = 0; i < orders.length; i++) {
            // 각 주문 정렬하기
            tmp = orders[i].toCharArray();
            Arrays.sort(tmp);
            orders[i] = String.valueOf(tmp);
            
            for(int j = 0; j < orders[i].length(); j++) {
                set.add(orders[i].charAt(j));
            }
        }
        
        list = new ArrayList<>(set);
        endFlag = (int)Math.pow(2, list.size());
        
        for(int i = 0; i < course.length; i++) {
            comb(map, list, "", list.size(), course[i], 0, 0);
        }
        
        return map;
    }
    
    private static void comb(Map<String, Integer> map, List<Character> list, String menu, int n, int r, int start, int idx) {
		if(idx == r) {
            map.put(menu, 0);
			return;
		}
		
		for(int i = start; i < n; i++) {	
			comb(map, list, menu + list.get(i), n, r, i + 1, idx + 1);
		}
	}
}