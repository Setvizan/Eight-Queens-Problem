package com.github.setvizan.eightqueens;

/**
 Author: Nino Arisona and Oliver Janka

 Info:
 fast brute-force-ish method to calculate NQueens
 Though this solution isn't THAT fast and printing an output of the board also takes quite a bit
 according to chess rank and file to coordinate system would be x and y

 Info-Naming convention:
 methods northeast and northwest are named after cardinal directions, namely the diagonal ones, of which the queen can move.

 Help:
 Stack Overflow for diagonal constraints
 Google developer page for basic overview and explanation of the problem
 */
public class NQueens {

    private final int N;
    private int[] queens;
    private int counter = 0;

    NQueens(int N){
        this.queens = new int[N];
        this.N = N;
    }

    private int northeast(final int rank, final int file){
        return rank + file;
    }

    private int northwest(final int rank, final int file){
        return rank - file + N;
    }

    private boolean onDiagonal(final int rank, final int file, final int r, final int f){
        return northwest(rank, file) == northwest(r, f) || northeast(rank, file) == northeast(r, f);
    }

    private void placeQueen(final int rank, final int file){
        this.queens[rank] = file;
    }

    private void clearQueen(final int rank){
        this.queens[rank] = 0;
    }

    private boolean isSafe(final int rank, final int file){
        for(int r = 0; r < rank; r++){
            int f = queens[r];
            if(f == file || onDiagonal(rank, file, r, f)){
                return false;
            }
        }
        return true;
    }

    private void loop(final int rank){
        if(rank == N){
            System.out.println("Solution No."+ ++this.counter);
            printSolution();
        } else {
            for(int file = 0; file < N; file++){
                if(isSafe(rank, file)){
                    placeQueen(rank, file);
                    loop(rank+1);
                    clearQueen(rank); // this is not really needed since this program overwrites previous value by default, only makes it nicer for debug
                }
            }
        }
    }

    private void printSolution(){
        for(int r = 0; r < N; r++){
            for(int f = 0; f < N; f++){
                System.out.print(queens[r] == f ? "[Q]" : "[ ]");
            }
            System.out.println();
        }
    }

    public static void solve(int N){
        long t1 = System.nanoTime();
        new NQueens(N).loop(0);
        long t2 = System.nanoTime();
        System.out.println("Time needed: "+(t2-t1));
    }
}
