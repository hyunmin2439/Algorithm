import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

class Solution {
    public long solution(String expression) {
        String[] numStrArr, operArr, operation = new String[]{ "*", "+" , "-" };;
        int[] priority = new int[]{ 0, 1, 2 };
        long value = 0, answer = Integer.MIN_VALUE;
        Long numArr[];
        
        // split은 속도는 느리지만 정규 표현식 사용 가능
        numStrArr = expression.split("[*+-]");
        operArr = expression.replaceAll("[0-9]", "").split("");
        
        numArr = new Long[numStrArr.length];
        for(int i = 0; i < numStrArr.length; i++)
            numArr[i] = Long.valueOf(numStrArr[i]);
        
        do {
            // List.of는 java 9버전부터 지원
            value = calculator(new LinkedList<>( List.of(numArr) ), 
                               new LinkedList<>( List.of(operArr) ), 
                               new String[]{ operation[priority[0]], 
                                            operation[priority[1]], 
                                            operation[priority[2]] }
                              );
            
            answer = Math.max(Math.abs(value), answer);
        } while( np(priority) );
        
        return answer;
    }
    
    private long calculator(List<Long> numList, List<String> operList, String[] operation) {
        long value = 0;

        // operation 순서대로 연산
        for(int i = 0; i < 3; i++) {
            
            for(int j = 0; j < operList.size(); j++) {
                if( !operation[i].equals(operList.get(j)) ) continue;
                
                value = calcu(numList.remove(j), numList.remove(j), operList.remove(j));
                numList.add(j, value);  
                j--;
            }
        }
        
        return numList.get(0);
    }
    
    private long calcu(Long num1, Long num2, String operation) {
        switch(operation) {
            case "*": return num1 * num2;
            case "+": return num1 + num2;
            default: return num1 - num2; // '-'
        }
    }
    
    private int getOperNum(String operation) {
        switch(operation) {
            case "*": return 0;
            case "+": return 1;
            default: return 2; // '-'
        }
    }
    
    private boolean np(int[] arr) {
        int n = arr.length - 1;
        
        int i = n;
        while(i > 0 && arr[i - 1] >= arr[i]) i--;
        
        if(i == 0) return false;
        
        int j = n;
        while(arr[i - 1] >= arr[j]) j--;
        
        swap(arr, i - 1, j);
        
        int k = n;
        while(i < k)
            swap(arr, i++, k--);
            
        return true;
    }
    
    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}