import java.util.StringTokenizer;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        StringTokenizer st;
        Node[] stack;
        Node head, curr;
        int X, top = -1;
        
        stack = new Node[cmd.length];
        head = new Node(-1);
        initNode(head, n);
        
        curr = head.next;
        while(--k >= 0) curr = curr.next;
        
        for(int i = 0; i < cmd.length; i++) {
            st = new StringTokenizer(cmd[i]);
            
            switch( st.nextToken() ) {
                case "U":
                    X = Integer.parseInt(st.nextToken());
                    while(--X >= 0) curr = curr.prev;
                    break;
                case "D":
                    X = Integer.parseInt(st.nextToken());
                    while(--X >= 0) curr = curr.next;
                    break;
                case "C":
                    curr.deleteNode();
                    stack[++top] = curr;
                    if(curr.next != null)
                        curr = curr.next;
                    else
                        curr = curr.prev;
                    break;
                case "Z":
                    stack[top--].insertNode();
                    break;
            }
        }
        
        return makeAnswer(head.next, n);
    }
    
    private void initNode(Node head, int n) {
        Node curr = head;
        
        for(int i = 0; i < n; i++) {
            curr.addNode( new Node(i) );
            curr = curr.next;
        }
    }
    
    private String makeAnswer(Node curr, int n) {
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < n; i++) {
            if(curr == null || curr.idx != i)
                sb.append("X");
            else {
                sb.append("O");
                curr = curr.next;
            }
        }
        
        return sb.toString();
    }
    
    class Node {
        int idx;
        Node prev, next;
        
        public Node(int idx) {
            this.idx = idx;
            prev = next = null;
        }
        
        public void addNode(Node newNode) {
            this.next = newNode;
            newNode.prev = this;
        }
        
        public void insertNode() {
            this.next = this.prev.next;
            this.prev.next = this;
            if(this.next != null)
                this.next.prev = this;
        }
        
        public void deleteNode() {
            this.prev.next = this.next;
            if(this.next != null)
                this.next.prev = this.prev;
        }
    }
}