import java.util.Arrays;

class Solution {
    
    private final int NODE_MAX_DEPTH = 100_001;
    
    public int[][] solution(int[][] nodeinfo) {
        int[][] answer = new int[2][nodeinfo.length];
        Node[] nodes = init(nodeinfo);
        
        makeBtree(nodes, nodeinfo.length);
        
        preOrder(nodes[0], answer[0], 0);
        postOrder(nodes[0], answer[1], 0);
        
        return answer;
    }
    
    private Node[] init(int[][] nodeinfo) {
        Node[] nodes = new Node[nodeinfo.length];

        for(int i = 0; i < nodeinfo.length; i++) {
            nodes[i] = new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1);
        }
        
        Arrays.sort(nodes);
        
        return nodes;
    }
    
    private void makeBtree(Node[] nodes, int N) {
        Node root = nodes[0];
        
        for(int i = 1; i < N; i++) {
            insertNode(root, nodes[i]);
        }
    }
    
    private void insertNode(Node parent, Node child) {
        if(parent.x < child.x) {
            if(parent.right == null) parent.right = child;
            else insertNode(parent.right, child);
        }
        else {
            if(parent.left == null) parent.left = child;
            else insertNode(parent.left, child);
        }
    }
    
    private int preOrder(Node curr, int[] answer, int idx) {
        if(answer.length == idx) return idx;
        
        answer[idx++] = curr.idx;
        
        if(curr.left != null)
            idx = preOrder(curr.left, answer, idx);
        if(curr.right != null)
            idx = preOrder(curr.right, answer, idx);
        
        return idx;
    }
    
    private int postOrder(Node curr, int[] answer, int idx) {
        if(answer.length == idx) return idx;

        if(curr.left != null)
            idx = postOrder(curr.left, answer, idx);
        if(curr.right != null)
            idx = postOrder(curr.right, answer, idx);
        
        answer[idx++] = curr.idx;
        
        return idx;
    }
    
    class Node implements Comparable<Node> {
        Node left, right;
        int y, x, idx;
        
        public Node(int x, int y, int idx) {
            this.x = x;
            this.y = y;
            this.idx = idx;
        }
        
        @Override
        public int compareTo(Node other) {
            if(this.y != other.y)
                return other.y - this.y;
            
            return this.x - other.x;
        }
    }
}