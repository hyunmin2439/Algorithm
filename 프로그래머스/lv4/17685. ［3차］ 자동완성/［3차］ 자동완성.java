class Solution {
    public int solution(String[] words) {
        Node curr, head = new Node('0');
        String word;
        int answer = 0;
        
        for(int i = 0; i < words.length; i++) {
            curr = head;
            word = words[i];
            for(int j = 0; j < word.length(); j++) {
                curr = curr.addNode(word.charAt(j));
            }
            
            curr.isLeafNode = true;
        }
        
        for(int i = 0; i < words.length; i++) {
            curr = head;
            word = words[i];
            
            for(int j = 0; j < word.length(); j++) {
                curr = curr.getNode(word.charAt(j));
                answer++;
                
                if(!curr.isLeafNode && curr.childCnt <= 1) break;
            }
        }
        
        return answer;
    }
    
    class Node {
        char c;
        int childCnt;
        boolean isLeafNode;
        Node[] next;
        
        public Node(char c) {
            this.c = c;
            this.next = new Node[26];
        }
        
        public Node addNode(char c) {
            this.childCnt++;
            
            if(this.next[c - 'a'] == null)
                this.next[c - 'a'] = new Node(c);
            
            return this.next[c - 'a'];
        }
        
        public Node getNode(char c) {
            return this.next[c - 'a'];
        }
    }
}