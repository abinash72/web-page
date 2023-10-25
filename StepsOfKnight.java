import java.util.*;

public class StepsOfKnight {
    public static boolean isValidMove(int[][] board, int row, int col, int N) {
        return (row >= 0 && row < N && col >= 0 && col < N && board[row][col] == 0);
    }
    
    public static int findMinimumSteps(int N, int startX, int startY, int targetX, int targetY) {
        // Create a chessboard with all cells initially marked as 0
        int[][] board = new int[N][N];
        
        // Define the eight possible movements of a knight
        int[][] moves = {
            {-2, -1}, {-1, -2}, {-2, 1}, {-1, 2},
            {2, -1}, {1, -2}, {2, 1}, {1, 2}
        };
        
        
        // Create a queue to store the positions of the knight
        Queue<int[]> queue = new LinkedList<>();
        
        // Enqueue the starting position of the knight
        queue.offer(new int[]{startX, startY});
        
        // Mark the starting position as visited
        board[startX][startY] = 1;
        
        // Perform breadth-first search
        while (!queue.isEmpty()) {
            int[] currentPos = queue.poll();
            int currentX = currentPos[0];
            int currentY = currentPos[1];
            
            // Check if we have reached the target position
            if (currentX == targetX && currentY == targetY) {
                return board[currentX][currentY] - 1;
            }
            
            // Explore the eight possible moves
            for (int[] move : moves) {
                int nextX = currentX + move[0];
                int nextY = currentY + move[1];
                
                if (isValidMove(board, nextX, nextY, N)) {
                    // Mark the next position as visited and enqueue it
                    board[nextX][nextY] = board[currentX][currentY] + 1;
                    queue.offer(new int[]{nextX, nextY});
                }
            }
        }
        
        // If the target position cannot be reached, return -1
        return -1;
    }
    
    public static void main(String[] args) {
        int N = 8; // Size of the chessboard (8x8 in this case)
        int startX = 0; // Starting X coordinate
        int startY = 0; // Starting Y coordinate
        int targetX = 7; // Target X coordinate
        int targetY = 7; // Target Y coordinate
        
        int minSteps = findMinimumSteps(N, startX, startY, targetX, targetY);
        
        if (minSteps == -1) {
            System.out.println("Target position cannot be reached.");
        } else {
            System.out.println("Minimum steps to reach the target: " + minSteps);
        }
    }
}
