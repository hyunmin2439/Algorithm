class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        int[][] matrix = initMatrix(rows, columns);
        
        for(int i = 0; i < queries.length; i++) {
            answer[i] = rotate(matrix, rows, columns, queries[i]);
            // printMatrix(matrix, rows, columns);
        }
        
        return answer;
    }
    
    private int[][] initMatrix(int rows, int columns) {
        int[][] matrix = new int[rows + 1][columns + 1];
        
        int num = 1;
        for(int y = 1; y <= rows; y++) {
            for(int x = 1; x <= columns; x++) {
                matrix[y][x] = num++;
            }
        }
        
        return matrix;
    }
    
    private int rotate(int[][] matrix, int rows, int columns, int[] querie) {
        int yCnt = querie[2] - querie[0], 
            xCnt = querie[3] - querie[1], 
            y = querie[0], x = querie[1], 
            sw = 1, tmp = matrix[y][x],
            min = matrix[y][x];
        
        for(int i = 0; i < 2; i++) {
            
            for(int j = 0; j < yCnt; j++) {
                y += sw;
                matrix[y - sw][x] = matrix[y][x];
                min = Math.min(min, matrix[y][x]);
            }
            
            for(int j = 0; j < xCnt; j++) {
                x += sw;
                matrix[y][x - sw] = matrix[y][x];
                min = Math.min(min, matrix[y][x]);
            }
            
            sw *= -1;
        }
        
        matrix[y][x + 1] = tmp;
        
        return min;
    }
    
    private void printMatrix(int[][] matrix, int rows, int columns) {
        for(int y = 1; y <= rows; y++) {
            for(int x = 1; x <= columns; x++) {
                System.out.printf("%3d", matrix[y][x]);
            }
            System.out.println();
        }
    }
}