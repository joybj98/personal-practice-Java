public class SudokuSolver {
    private static final int GRID_SIZE = 9;      
    
    public static void main(String[] args) {
    
        int[][] board = {
            {7,0,2,0,5,0,6,0,0},
            {0,0,0,0,0,3,0,0,0},
            {1,0,0,0,0,9,5,0,0},
            {8,0,0,0,0,0,0,9,0},
            {0,4,3,0,0,0,7,5,0},
            {0,9,0,0,0,0,0,0,8},
            {0,0,9,7,0,0,0,0,5},
            {0,0,0,2,0,0,0,0,0},
            {0,0,7,0,4,0,2,0,3}
        };
        printBoard(board);
        if(solveBoard(board)){
            System.out.println();
            System.out.println("Solved Sucessfully!");
            System.out.println();
        };
        printBoard(board);
        
    }
    private static void printBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++){
            if (row % 3 ==0 && row != 0){
                System.out.println("-----------");
            }
            for (int col = 0; col < GRID_SIZE; col++){
                if(col % 3 ==0 && col !=0){
                    System.out.print("|");
                }
                System.out.print(board[row][col]);
            }
            System.out.println();
        }
        
    }

    private static boolean isNumberInRow(int[][] board, int number, int row){
        for (int i = 0; i < GRID_SIZE; i++){
            if (board[row][i] == number){
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInCol(int[][] board, int number, int col){
        for (int i = 0; i < GRID_SIZE; i++){
            if (board[i][col] == number){
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInBox(int[][] board, int number, int row, int col){
        int localBoxRow = row - row % 3;
        int localBoxCol = col - col % 3;

        for (int i = localBoxRow; i < localBoxRow + 3 ; i++){
            for (int j = localBoxCol; j < localBoxCol + 3; j++){
                if (board[i][j] == number){
                    return true;
                }
            }
            
        }

        return false;
    }
    private static boolean isValid(int[][]board, int number , int row, int col){

        return !isNumberInBox(board,number,row, col) &&
        !isNumberInRow(board,number,row)&&
        !isNumberInCol(board,number,col);
    }

    private static boolean solveBoard(int[][] board){
        for (int row = 0 ; row < GRID_SIZE ; row++){
            for (int col = 0 ; col < GRID_SIZE ; col++){
                if(board[row][col] == 0){
                    //find a position to solve
                    for (int number = 1; number < GRID_SIZE+1; number ++){
                        if (isValid(board, number, row, col)){
                            
                            board[row][col] = number;
                            // find a valid number and fill it
                            if (solveBoard(board)) {
                                return true;
                            }
                            else {
                                // the rest of the board is impossible to solve
                                // try 
                                board[row][col] = 0;
                                // then go to the next try for a number
                                // (by for loop)
                            }
                        }

                    }
                    
                    // at any position, if all possible states cannot solve the 
                    // board
                    return false;
                }
            }
        }
        // if we finished filling the board,
        return true;
    }
}

