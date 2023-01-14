import java.util.*;

class Solution {
    
    private int answer;
    
    public int solution(String begin, String target, String[] words) {
        List<char[]> list = new ArrayList<>();
        char[] word, tgt;
        boolean isExist = false;
        answer = words.length;
        
        if(begin.equals(target)) return 0;
        
        tgt = target.toCharArray();
        
        for(int i = 0; i < words.length; i++) {
            if(target.equals(words[i])) isExist = true;
            
            word = words[i].toCharArray();
            
            for(int j = 0; j < word.length; j++) {
                if(tgt[j] == word[j]) {
                    list.add(word);
                    break;
                }
            }
        }
        
        if(list.isEmpty() || !isExist) return 0;
        
        dfs(list, new boolean[list.size()], begin.toCharArray(), tgt, 0);
        
        return answer;
    }
    
    private void dfs(List<char[]> list, boolean[] visited, char[] curr, char[] target, int cnt) {
        if(answer <= cnt) return;
        
        boolean check = true;
        
        for(int i = 0; i < curr.length; i++) {
            if(curr[i] != target[i]) {
                check = false;
                break;
            }
        }
        
        if(check) {
            answer = Math.min(answer, cnt);
            return;
        }
        
        char[] word;
        int diff;
        for(int i = 0; i < list.size(); i++) {
            if(visited[i]) continue;
            
            word = list.get(i);
            diff = 0;
            
            for(int j = 0; j < word.length; j++) {
                if(curr[j] != word[j]) diff++;
            }
            
            if(diff != 1) continue;
            
            visited[i] = true;
            dfs(list, visited, word, target, cnt + 1);
            visited[i] = false;
        }
    }
}