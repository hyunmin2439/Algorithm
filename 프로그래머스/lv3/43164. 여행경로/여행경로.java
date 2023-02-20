import java.util.*;

class Solution {

    private boolean flag;
    
    public String[] solution(String[][] tickets) {
        String[] answer = new String[tickets.length + 1];
        List<LinkedList<String>> edges = new LinkedList<>();
        
        for(int i = 0; i < 17_576; i++) {
            edges.add(new LinkedList<>());
        }
        
        for(int i = 0; i < tickets.length; i++) {
            edges.get(getHash(tickets[i][0])).offer(tickets[i][1]);
        }
        
        for(int i = 0; i < 17_576; i++) {
            if(!edges.get(i).isEmpty())
                Collections.sort(edges.get(i));
        }
        
        answer[0] = "ICN";
        dfs(edges, answer, "ICN", 0);
        
        return answer;
    }
    
    private void dfs(List<LinkedList<String>> edges, String[] answer, String curr, int idx) {
        if(flag) return;
        
        if(idx == answer.length - 1) {
            answer[idx] = curr;
            flag = true;
            return;
        }
        
        String tmp;
        int hash = getHash(curr);
        answer[idx] = curr;
        
        for(int i = 0; i < edges.get(hash).size(); i++) {
            tmp = edges.get(hash).remove(i);
            dfs(edges, answer, tmp, idx + 1);
            edges.get(hash).add(i, tmp);
        }
    } 
    
    private int getHash(String airport) {
        return (airport.charAt(0) - 'A') * 676 + (airport.charAt(1) - 'A') * 26 + airport.charAt(2) - 'A';
    }
}