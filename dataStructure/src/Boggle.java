
/*************************************************************************
 *  Compilation:  javac Boggle.java
 *  Execution:    java Boggle N < words.txt
 *  
 *  Creates a random N-by-N board of characters and prints out all
 *  strings that can be formed by moving left, right, up, down,
 *  or diagonally, without repeating any cell.
 *
 *  Assumes words are comprised of lower case letters.
 *
 *  Currently prints words as many times as they appear, not
 *  just once.
 *
 *************************************************************************/

public class Boggle {
    private int N;                    // dimension of board
    private char[][] board;           // the board (a-z)
    private boolean[][] visited;      // for dfs
    private PrefixST st;              // list of words

    // create random N-by-N board
    public Boggle(int N, PrefixST st) {
        this.N  = N;
        this.st = st;
        visited = new boolean[N][N];
        board = new char[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                board[i][j] = (char) (Math.random() * 26 + 'a');
    }

    // show all words, starting from each possible starting place
    public void showWords() {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                dfs("", i, j);
    }

    // run depth first search starting at cell (i, j)
    private void dfs(String prefix, int i, int j) {
        if (i < 0 || j < 0 || i >= N || j >= N) return;

        // can't visited a cell more than once
        if (visited[i][j]) return;

        // key to efficiency of backtracking algorithm
        if (!st.containsPrefix(prefix)) return;

        // not allowed to reuse a letter
        visited[i][j] = true;

        // found a word
        prefix = prefix + board[i][j];
        if (st.contains(prefix)) 
            System.out.println(prefix);

        // consider all neighbors
        for (int ii = -1; ii <= 1; ii++)
            for (int jj = -1; jj <= 1; jj++)
                dfs(prefix, i + ii, j + jj);

        visited[i][j] = false;
    }

    // just the board
    public String toString() {
        String s = "";
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s = s + board[i][j] + " ";
            }
            s = s + "\n";
        }
        return s;
    }



    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);

        // read in the list of words
        PrefixST st = new PrefixST();
        while(!StdIn.isEmpty())
            st.add(StdIn.readString());
        System.err.println("Done reading dictionary");

        Boggle boggle = new Boggle(N, st);
        System.out.println(boggle);
        boggle.showWords();
    }
}