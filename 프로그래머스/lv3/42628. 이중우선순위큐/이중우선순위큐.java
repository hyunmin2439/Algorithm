import java.util.StringTokenizer;
import java.util.Arrays;

class Solution {
    
    private int length = 1;
    
    public int[] solution(String[] operations) {
        String operation;
        int num, len = operations.length;
        DoubleHeap dHeap = new DoubleHeap(len);
        
        for(int i = 0; i < len; i++) {
            StringTokenizer st = new StringTokenizer(operations[i]);
            operation = st.nextToken();
            num = Integer.parseInt(st.nextToken());
            
            switch(operation) {
                case "I": dHeap.add(num); break;
                case "D": dHeap.delete(num); break;
            }
            
            // System.out.println( dHeap.length + " " + Arrays.toString(dHeap.data) );
        }
        
        return new int[] { dHeap.peek(1), dHeap.peek(-1) };
    }
    
    private class DoubleHeap {
        int length, maxLength, data[];
        
        public DoubleHeap(int maxLength) {
            this.maxLength = maxLength;
            this.data = new int[maxLength + 1];
        }
        
        private void add(int num) {
            if(length == maxLength) return;
            
            int idx = ++length;
            data[idx] = num;
            
            // 마지막 노드에는 제일 작은 값 저장
            // 따라서 현재 삽입하는 노드가 마지막 노드 보다 작거나 같으면 비교 불필요
            if(length < 2 || data[idx - 1] >= data[idx]) return;
            
            while(idx > 1 && data[idx - 1] < data[idx]) {
                swap(idx - 1, idx--);
            }
            
            while(idx > 1) {
                if(data[idx / 2] >= data[idx]) break;

                swap(idx, idx / 2);
                idx /= 2;
            }
        }

        private void delete(int num) {
            if(length == 0) return;
            
            if(num == -1) {
                data[length--] = 0;
                return;
            }
                
            int idx = 1,
                cIdx = 0;
            
            data[idx] = data[length];
            data[length--] = 0;
            
            while(idx * 2 <= length) {
                cIdx = idx * 2;
                
                if(cIdx + 1 <= length)
                    cIdx = data[cIdx] > data[cIdx + 1] ? cIdx : cIdx + 1;
                
                if(data[idx] >= data[cIdx]) break;
                
                swap(idx, cIdx);
                
                idx = cIdx;
            }
            
            // 제일 마지막 노드와 비교
            if(data[idx] < data[length])
                swap(idx, length);
        }
        
        private int peek(int num) {
            if(length == 0) return 0;
            
            return num == 1 ? data[1] : data[length];
        }
        
        private void swap(int idx1, int idx2) {
            int tmp = data[idx1];
            data[idx1] = data[idx2];
            data[idx2] = tmp;
        }
    }
}