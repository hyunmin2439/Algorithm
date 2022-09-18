import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;

class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;
        
        List<String> set1 = makeSet( str1.toLowerCase() );
        List<String> set2 = makeSet( str2.toLowerCase() );
        List<String> ins = new ArrayList<>();
        
        //A와 집합 B가 모두 공집합일 경우에는 나눗셈이 정의되지 않으니 따로 J(A, B) = 1
        //A와 집합 B 중 둘 중 하나가 공집합인 경우 0
        if(set1.isEmpty() || set2.isEmpty()) {
            if(set1.isEmpty() && set2.isEmpty())
                answer = 65536;
            
            return answer;
        }
        
        for(int i = 0; i < set1.size(); i++) {
            for(Iterator<String> iterator = set2.iterator(); iterator.hasNext();) {
                String e1 = set1.get(i);
                String e2 = iterator.next();
                
                if( e1.equals(e2) ) {
                    ins.add(e1);
                    iterator.remove();
                    break;
                }
            }
        }
        
        // System.out.println( set1.toString() );
        // System.out.println( set2.toString() );
        // System.out.println( ins.toString() );
        
        answer = (int) ( ( (double) ins.size() / (set1.size() + set2.size()) ) * 65536 );
        
        return answer;
    }
    
    public List<String> makeSet(String str) {
        List<String> set = new LinkedList<>();
        
        int end = str.length() - 1;
        for(int i = 0; i < end; i++) {
            if( !('a' <= str.charAt(i) && str.charAt(i) <= 'z') ) continue;
            
            if( 'a' <= str.charAt(i + 1) && str.charAt(i + 1) <= 'z' )
                set.add(str.substring(i, i + 2));
        }
        
        return set;
    }
}