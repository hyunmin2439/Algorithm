import java.util.List;
import java.util.Collections;
import java.util.LinkedList;

class Solution {
    public String[] solution(String[] files) {
        List<File> list = new LinkedList<>();
        String head, number, tail, answer[];
        int firstIdx, secondIdx;
        File file;
        
        for(int i = 0; i < files.length; i++) {
            firstIdx = 0;
            
            while( !isNumber(files[i].charAt(firstIdx)) ) firstIdx++;
            
            secondIdx = firstIdx;
            
            while( secondIdx < files[i].length() && secondIdx - firstIdx < 5 && isNumber(files[i].charAt(secondIdx)) ) secondIdx++;
            
            head = files[i].substring(0, firstIdx);
            number = files[i].substring(firstIdx, secondIdx);
            tail = files[i].substring(secondIdx, files[i].length());
            
            list.add(new File(head, number, tail));
        }
        
        Collections.sort(list);
        
        answer = new String[list.size()];
        for(int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i).toString();
        }
        
        return answer;
    }
    
    private boolean isNumber(char c) {
        return '0' <= c && c <= '9';
    }
    
    private class File implements Comparable<File> {
        String head, number, tail;
        
        public File(String head, String number, String tail) {
            this.head = head;
            this.number = number;
            this.tail = tail;
        }
        
        @Override
        public int compareTo(File other) {
            int comp = this.head.toLowerCase().compareTo(other.head.toLowerCase());
            
            if(comp != 0)
                return comp;
            
            return Integer.parseInt(this.number) - Integer.parseInt(other.number);
        }
        
        @Override
        public String toString() {
            return head + number + tail;
        }
    }
}