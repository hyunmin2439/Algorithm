class Solution {
    public int solution(int[] numbers, int target) {
        int answer = dfs(numbers, target, 0, 0);
        return answer;
    }
    
    private int dfs(int[] numbers, int target, int sum, int idx) {
        if(numbers.length == idx) {     
            if(sum == target)
                return 1;
            else
                return 0;
        }
        
        return dfs(numbers, target, sum + numbers[idx], idx + 1) 
            + dfs(numbers, target, sum - numbers[idx], idx + 1);
    }
}