class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        StringBuilder sb = new StringBuilder();
        String binStr = "";
        
        for(int i = 0; i < numbers.length; i++) {
        	binStr = makePerfectBinaryString(numbers[i]);
        	answer[i] = isCorrect(binStr, false, 0, binStr.length()) ? 1 : 0;
        }
        
        return answer;
    }
    
    private String makePerfectBinaryString(long number) {
        StringBuilder sb = new StringBuilder();
        String binStr = Long.toBinaryString(number);
        int cnt = (int)Math.pow(2, Math.ceil( (Math.log(binStr.length() + 1) / Math.log(2)) ) ) - 1 - binStr.length();
        
        while(cnt-- > 0) {
            sb.append('0');
        }
        
        return sb.append(binStr).toString();
    }
    
    private boolean isCorrect(String binStr, boolean isDummyChild, int start, int end) {
        int mid = (start + end) / 2;
        boolean isDummy = binStr.charAt(mid) == '0';
        
        if(!isDummy && isDummyChild) return false;
        
        isDummyChild = isDummyChild || isDummy;
        
        if(start + 1 >= end) return true;
        
        return isCorrect(binStr, isDummyChild, start, mid) && isCorrect(binStr, isDummyChild, mid + 1, end);
    }
}