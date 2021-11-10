package com.github.setvizan.eightqueens;

/**
 Author: Nino Arisona and Oliver Janka

 !NOTE THIS IS ONLY FOR 8 QUEENS, FOR COMMENTED CODE LOOK INTO NQueens.java!

 Info:
 fast brute-force-ish method to calculate EightQueens
 Though this solution isn't THAT fast and printing an output of the board also takes quite a bit
 according to chess rank and file to coordinate system would be x and y

 Info-Naming convention:
 methods northeast and northwest are named after cardinal directions, namely the diagonal ones, of which the queen can move.

 Help:
 Stack Overflow for diagonal constraints
 Google developer page for basic overview and explanation of the problem
*/
public class EightQueens {

    private int[] queens = new int[8];
    private int counter = 0;

    private int northeast(final int rank, final int file){
        return rank + file;
    }

    private int northwest(final int rank, final int file){
        return rank - file + 8;
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
        if(rank == 8){
            System.out.println(++this.counter);
            printSolution();
        } else {
            for(int file = 0; file < 8; file++){
                if(isSafe(rank, file)){
                    placeQueen(rank, file);
                    loop(rank+1);
                    clearQueen(rank); // this is not really needed since this program overwrites previous value by default, only makes it nicer for debug
                }
            }
        }
    }

    private void printSolution(){
        for(int r = 0; r < queens.length; r++){
            for(int f = 0; f < queens.length; f++){
                System.out.print(queens[r] == f ? "[Q]" : "[ ]");
            }
            System.out.println();
        }
    }

    public static void solve(){
        long t1 = System.nanoTime();
        new EightQueens().loop(0);
        long t2 = System.nanoTime();
        System.out.println(t2-t1);
    }
}
