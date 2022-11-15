package ru.job4j.puzzle;

public class Win {
    public static boolean vertTruth(int[][] board, int j) {
        boolean rls = true;
        for (int i = 0; i < board.length; i++) {
            if (board[j][i] != 1) {
                rls = false;
                break;
            }
        }
        return rls;
    }

    public static boolean horizTruth(int[][] board, int j) {
        boolean rls = true;
        for (int i = 0; i < board.length; i++) {
            if (board[i][j] != 1) {
                rls = false;
                break;
            }
        }
        return rls;
    }

    public static boolean checkWinPosition(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 1 && vertTruth(board, j) || horizTruth(board, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean check(int[][] board) {
        boolean rsl = checkWinPosition(board);
        return rsl;
    }

}
