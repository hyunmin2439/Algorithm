class Solution {
    public int solution(int[] a) {
        if(a.length < 3) return a.length;
        
        // 첫번째 풍선과 마지막 풍선은 마지막에 비교하면 무조건 남기는 것이 가능
        int answer = 2, len = a.length - 1, minVal[][] = new int[len + 1][2];
        
        memoiMinValue(a, minVal, len);
        
        for(int i = 1; i < len; i++) {
            // 현재의 위치 값이 왼쪽 가장 작은값 보다 크고 오른쪽 가장 작은값 보다 크면 남기기 불가능
            // 예제 2번 -16 58 -92
            // 번호가 더 작은 풍선을 터트리는 행위 1회를 어느쪽을 먼저 쓰더라도 불가능
            if(minVal[i][0] < a[i] && minVal[i][1] < a[i]) continue;
            
            answer++;
        }
        
        return answer;
    }
    
    private void memoiMinValue(int[] a, int[][] minVal, int len) {
        // 1 ~ len - 1 위치의 왼쪽, 오른쪽 작은 값들을 기록해둠
        int leftIdx = 1, leftMin = a[0], rightIdx = len - 1, rightMin = a[len];
        
        while(len-- > 1) {
            minVal[leftIdx][0] = leftMin;
            minVal[rightIdx][1] = rightMin;
            
            leftMin = Math.min(a[leftIdx++], leftMin);
            rightMin = Math.min(a[rightIdx--], rightMin);
        }
    }
}