package com.github.setvizan.eightqueens;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nino Arisona and Oliver Janka
 * fast brute-force-ish method to calculate NQueens
 * Though this solution isn't THAT fast and printing an output of the board also takes quite a bit
 * according to chess rank and file to coordinate system would be x and y
 * 
 * To use this class just call the method {@link #solve(int)}
 * 
 * Info-Naming convention:
 * methods northeast and northwest are named after cardinal directions, namely the diagonal ones, of which the queen can move.
 * 
 * Help:
 * Stack Overflow for diagonal constraints
 * Google developer page for basic overview and explanation of the problem
 *
 * Quick-tip: don't be curious and try inputs over 13... They will take way too long
 */
public class NQueens {

    private long counter;
    private final int N;
    private int[] queens;
    private final List<Solution> solutions;

    private NQueens(int N) {
        this.counter = 0;
        this.queens = new int[N];
        this.N = N;
        this.solutions = new ArrayList<>();
    }

    // checks for pieces on left-to-right diagonal using basics maths
    private int northeast(final int rank, final int file) {
        return rank + file;
    }
    // checks for pieces on right-to-left diagonal using basics maths
    private int northwest(final int rank, final int file) {
        return rank - file;
    }

    // checks if pieces are on diagonals using northwest and northeast methods
    private boolean onDiagonal(final int rank, final int file, final int r, final int f) {
        return northwest(rank, file) == northwest(r, f) || northeast(rank, file) == northeast(r, f);
    }

    // places queen on rank -> this will be overwritten if no solution is found
    private void placeQueen(final int rank, final int file) {
        this.queens[rank] = file;
    }

    // clears queen on rank after no solution is found; NOTE: this isn't really needed but makes debugging easier
    private void clearQueen(final int rank) {
        this.queens[rank] = 0;
    }


    private boolean isSafe(final int rank, final int file) {
        for (int r = 0; r < rank; r++) {
            int f = queens[r];                                  //use file for constraint since rank constraint is done by default in loop recursion
            if (f == file || onDiagonal(rank, file, r, f)) {
                return false;
            }
        }
        return true;
    }

    private void loop(final int rank) {
        if (rank == N) {                            // If rank is equal to N a solution has been found!
            System.out.println("Solutions: " + ++this.counter);
            addSolution();
        } else {
            for (int file = 0; file < N; file++) {
                if (isSafe(rank, file)) {
                    placeQueen(rank, file);
                    loop(rank + 1);                 // starts recursion for next rank
                    clearQueen(rank);               // this is not really needed since this program overwrites previous value by default, only makes it nicer for debug
                }
            }
        }
    }

    /**
     * reads a solution into a 2 dimensional array for rendering
     */
    private void addSolution() {
        boolean[][] queens = new boolean[N][N];
        for (int i = 0; i < queens.length; i++) {
            for (int k = 0; k < queens[i].length; k++) {
                queens[i][k] = this.queens[i] == k;
            }
        }
        solutions.add(new Solution(queens));
    }

    /**
     * This is used to output solution into the console
     * @deprecated
     */
    private void printSolution() {
        for (int r = 0; r < N; r++) {
            for (int f = 0; f < N; f++) {
                System.out.print(queens[r] == f ? "[Q]" : "[ ]");
            }
            System.out.println();
        }
    }

    private List<Solution> getSolutions() {
        return this.solutions;
    }
    
    /**
     * Starts solving the problem
     * This method may take a long time
     */
    public static Solution[] solve(int N) {
        var instance = new NQueens(N);
        instance.loop(0);

        List<Solution> solutions = instance.getSolutions();
        return solutions.toArray(new Solution[solutions.size()]);
    }
}
