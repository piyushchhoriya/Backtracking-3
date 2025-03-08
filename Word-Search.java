// In this problem, running a for loop to find the position of first character in the word, and when found, calling a recursive 
// function from there and recursively searching for the next character in 4 directions, if found marking that as visited and moving
// forward. And after the recursive call is completed, backtracking.

// Time Complexity :m*n*3^L  where L is length of word, because we are calling recursive function on 3 directions in worst case
// Space Complexity : 0(1)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

class WordSearch {
    int m, n;
    int[][] dirs;

    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0) {
            return false;
        }
        m = board.length;
        n = board[0].length;
        dirs = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (recurse(board, i, j, 0, word)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean recurse(char[][] board, int row, int col, int index, String word) {
        // base
        if (index == word.length()) {
            return true;
        }
        if (row < 0 || row == m || col < 0 || col == n || board[row][col] == '#') {
            return false;
        }

        // logic
        if (board[row][col] == word.charAt(index)) {
            board[row][col] = '#';
            for (int[] dir : dirs) {
                int nr = row + dir[0];
                int nc = col + dir[1];
                if (recurse(board, nr, nc, index + 1, word)) {
                    return true;
                }
            }
            board[row][col] = word.charAt(index);
        }
        return false;
    }

    public static void main(String[] args) {
        char[][] board = new char[][] { { 'A', 'B', 'R', 'O' }, { 'S', 'F', 'C', 'S' }, { 'L', 'O', 'R', 'E' } };
        String word = "SFCRESOR";
        WordSearch sv = new WordSearch();
        System.out.println(sv.exist(board, word));
    }
}