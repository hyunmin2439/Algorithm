import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int wordIdx = 0, turn = 1, cycle = 1;
        char prev, curr;
        Node header = new Node('h');
        
        header.find(words[wordIdx], 0, true);
        
        while(++wordIdx < words.length) {
            if(++turn > n) {
                turn = 1;
                cycle++;
            }
            
            prev = words[wordIdx - 1].charAt(words[wordIdx - 1].length() - 1);
            curr = words[wordIdx].charAt(0);
                
            if(prev != curr || header.find(words[wordIdx], 0, true))
                return new int[] { turn, cycle };
        }
        
        return new int[] { 0, 0 };
    }
    
    private class Node {
        char c;
        List<Node> next;
        
        public Node(char c) {
            this.c = c;
            this.next = new ArrayList<>();
        }
        
        public boolean find(String str, int idx, boolean isExist) {
            if(str.length() == idx) 
                return isExist;
            
            char curr = str.charAt(idx);
            
            for(int i = 0; i < next.size(); i++)
                if(next.get(i).c == curr)
                    return next.get(i).find(str, idx + 1, true);
            
            Node newNode = new Node(curr);
            next.add(newNode);
            
            return newNode.find(str, idx + 1, false);
        }
    }
}