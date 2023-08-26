package com.hafthor;

import java.util.Arrays;
import java.util.Random;

/**
 * A Sudoku puzzle solver and generator. Represents a puzzle as a 9x9 grid of 9-bit bitmasks, where each bit represents
 * a possible value for that cell. For example, the value 0x1ff (binary 111111111) means that any value is possible,
 * while the value 0x1f7 (binary 111110111) means that all values are possible except 4. The value 0x8 (binary 1000)
 * means that only 4 is possible. The value 0x0 means that no values are possible, and thus the puzzle is unsolvable.
 */
public class Puzzle {
    private static final short ALL_POSSIBLE = 0x1ff;

    private static Random random;

    /**
     * Returns the random number generator used for solving when guessing is required.
     * @return the random number generator used for solving when guessing is required
     */
    public static Random getRandom() {
        return random;
    }

    /**
     * Sets the random number generator used for solving when guessing is required.
     * @param random the random number generator used for solving when guessing is required
     */
    public static void setRandom(Random random) {
        Puzzle.random = random;
    }

    private String name;

    /**
     * Returns the name of the puzzle, if any.
     * @return the name of the puzzle, if any
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the puzzle.
     * @param name the name of the puzzle
     */
    public void setName(String name) {
        this.name = name;
    }

    private short[] puzzle;

    private int passes;

    /**
     * Returns the number of passes of reduces while solving.
     * @return the number of passes of reduces while solving
     */
    public int getPasses() {
        return passes;
    }

    private int reduces;

    /**
     * Returns the number of reduces while solving, i.e. the number of times a known value was removed from other cells.
     * @return the number of reduces while solving, i.e. the number of times a known value was removed from other cells
     */
    public int getReduces() {
        return reduces;
    }

    private int loners;

    /**
     * Returns the number of loners while solving, i.e. the number of times a cell was found to be the only one that
     * could be a particular value.
     * @return the number of loners while solving, i.e. the number of times a cell was found to be the only one that
     * could be a particular value
     */
    public int getLoners() {
        return loners;
    }

    private int guesses;

    /**
     * Returns the number of guesses while solving.
     * @return the number of guesses while solving
     */
    public int getGuesses() {
        return guesses;
    }

    private int backtracks;

    /**
     * Returns the number of backtracks while solving.
     * @return the number of backtracks while solving
     */
    public int getBacktracks() {
        return backtracks;
    }

    private int solutions;

    /**
     * Returns the number of solutions possible for the puzzle.
     * @return the number of solutions possible for the puzzle
     */
    public int getSolutions() {
        return solutions;
    }

    private int rounds;

    /**
     * Returns the number of rounds while generating, i.e. how many puzzles where generated before finding one that
     * could be solved.
     * @return the number of rounds while generating, i.e. how many puzzles where generated before finding one that
     * could be solved
     */
    public int getRounds() {
        return rounds;
    }

    /**
     * Constructs a puzzle from a string representation of it, where each character is a digit from 1 to 9, or a period
     * for an unknown value. Other characters are ignored. A zero-seeded random number generator is used for solving
     * when guessing is required.
     */
    public Puzzle(final String s) {
        random = new Random(0);
        parsePuzzle(s);
    }

    /**
     * Constructs a puzzle from a string representation of it, where each character is a digit from 1 to 9, or a period
     * for an unknown value. Other characters are ignored. The provided random number generator is used for solving
     * when guessing is required.
     */
    public Puzzle(final String s, Random r) {
        random = r;
        parsePuzzle(s);
    }

    /**
     * Constructs a puzzle from a string representation of it, where each character is a digit from 1 to 9, or a period
     * for an unknown value. Other characters are ignored. The provided random number seed is used for solving when
     * guessing is required.
     */
    public Puzzle(final String s, int seed) {
        random = new Random(seed);
        parsePuzzle(s);
    }

    private void parsePuzzle(final String s) {
        final short[] grid = new short[9 * 9];
        int i = 0;
        for (final char cellChar : s.toCharArray())
            if ('1' <= cellChar && cellChar <= '9')
                grid[i++] = (short) (1 << (Integer.parseInt(String.valueOf(cellChar)) - 1));
            else if (cellChar == '.')
                grid[i++] = ALL_POSSIBLE;
        if (i != grid.length)
            throw new IllegalArgumentException();
        this.puzzle = grid;
    }

    /**
     * Generates one of 6,670,903,752,021,072,936,960 possible puzzles using a random number generator seeded with zero.
     * The puzzle is guaranteed to be solvable, and to have only one solution.
     */
    public Puzzle() {
        random = new Random(0);
        generatePuzzle();
    }

    /**
     * Generates one of 6,670,903,752,021,072,936,960 possible puzzles using the provided random number generator.
     * The puzzle is guaranteed to be solvable, and to have only one solution.
     */
    public Puzzle(Random r) {
        random = r;
        generatePuzzle();
    }

    /**
     * Generates one of 6,670,903,752,021,072,936,960 possible puzzles using the provided random number seed.
     * The puzzle is guaranteed to be solvable, and to have only one solution.
     */
    public Puzzle(int seed) {
        random = new Random(seed);
        generatePuzzle();
    }

    private void generatePuzzle() {
        final short[] grid = new short[9 * 9];
        short[] unsolved = null;
        for (int rounds = 0; solutions != 1 || !isSolved(); rounds++) {
            Arrays.fill(grid, ALL_POSSIBLE);
            for (int seeds = 0; seeds < 17; seeds++) {
                final int i = random.nextInt(grid.length);
                if (grid[i] != ALL_POSSIBLE)
                    seeds--; // we already generated this location, try again
                else
                    grid[i] = (short) (1 << random.nextInt(9));
            }
            unsolved = grid.clone();

            puzzle = grid;
            solutions = 0;
            this.rounds = rounds;
            solve(true, true);
        }
        puzzle = unsolved;
        passes = 0;
        loners = 0;
        reduces = 0;
        guesses = 0;
        backtracks = 0;
    }

    /**
     * Solves the puzzle. If the puzzle is not solvable, it will be left in an unsolved state.
     */
    public void solve() {
        solve(false, false);
    }

    /**
     * Solves the puzzle. If the puzzle is not solvable, it will be left in an unsolved state.
     * @param quiet - if true, don't print anything
     * @param randomGuessLocation - if true, guess at a random location instead of the first unsolved location
     */
    public void solve(boolean quiet, boolean randomGuessLocation) {
        while (solvePass()) ;
        if (!isSolved())
            solveByGuessing(quiet, randomGuessLocation);
    }

    private boolean solvePass() {
        if (isSolved() || !isOk())
            return false;
        while (solveExclusionary() != 0) ;
        if (isSolved() || !isOk())
            return false;
        if (solveLoner() == 0)
            return false;
        return true;
    }

    /**
     * Returns true if the puzzle is solved.
     * @return true if the puzzle is solved
     */
    public boolean isSolved() {
        for (final short cell : puzzle)
            if (!isPow2(cell))
                return false;
        return true;
    }

    /**
     * Solves the puzzle by removing known values from other cells.
     * @return the number of cells that were changed
     */
    public int solveExclusionary() {
        passes++;
        int count = 0;
        for (int row = 0; row < 9; row++)
            for (int col = 0; col < 9; col++)
                count += this.solveExclusionary(row, col);
        return count;
    }

    /**
     * Solves the puzzle by finding cells that are the only ones that could be a particular value.
     * @return the number of cells that were changed
     */
    public int solveLoner() {
        for (int i = 0; i < puzzle.length; i++) {
            if (!isPow2(puzzle[i])) {
                int count = this.solveLoner(i / 9, i % 9);
                if (count > 0)
                    return count;
            }
        }
        return 0;
    }

    private int solveExclusionary(final int row, final int col) {
        int count = 0;
        final int i = row * 9 + col;
        if (isPow2(puzzle[i])) {
            // if known, remove as possibility from others in row, column, and sub-grid
            for (int n = 0; n < 9; n++) {
                final short cur = puzzle[i], notCur = (short) ~cur;
                final int ri = row * 9 + n, ic = n * 9 + col, sg = subGrid(gridAt(row, col), n);
                if (i != ri && (cur & puzzle[ri]) != 0) {
                    reduces++;
                    count++;
                    puzzle[ri] &= notCur;
                }
                if (i != ic && (cur & puzzle[ic]) != 0) {
                    reduces++;
                    count++;
                    puzzle[ic] &= notCur;
                }
                if (i != sg && (cur & puzzle[sg]) != 0) {
                    reduces++;
                    count++;
                    puzzle[sg] &= notCur;
                }
            }
        }
        return count;
    }

    private int solveLoner(final int row, final int col) {
        final int i = row * 9 + col;
        if (!isPow2(puzzle[i])) {
            // if unknown, check to see if it is the only one that could be a particular value
            for (short b = 1; b < 0x200; b <<= 1) {
                if ((puzzle[i] & b) != 0) {
                    int rowCouldBe = 0, colCouldBe = 0, subGridCouldBe = 0;
                    for (int n = 0; n < 9; n++) {
                        final int ri = row * 9 + n, ic = n * 9 + col, sgi = subGrid(gridAt(row, col), n);
                        if (i != ri && (puzzle[ri] & b) != 0)
                            rowCouldBe++;
                        if (i != ic && (puzzle[ic] & b) != 0)
                            colCouldBe++;
                        if (i != sgi && (puzzle[sgi] & b) != 0)
                            subGridCouldBe++;
                    }
                    if (rowCouldBe == 0 || colCouldBe == 0 || subGridCouldBe == 0) {
                        loners++;
                        puzzle[i] = b;
                        return 1;
                    }
                }
            }
        }
        return 0;
    }

    /**
     * Solves the puzzle by guessing at a cell, then backtracking if it fails.
     * @param quiet - if true, don't print anything
     * @param randomGuessLocation - if true, guess at a random location instead of the first unsolved location
     */
    public void solveByGuessing(boolean quiet, boolean randomGuessLocation) {
        final short[] gridCopy = Arrays.copyOf(puzzle, puzzle.length);
        final int i = findFirstUnsolved(randomGuessLocation ? random.nextInt(puzzle.length) : 0);
        if (i == -1)
            return;
        short[] lastSolution = null;
        final int startN = randomGuessLocation ? random.nextInt(9) : 0;
        for (int n = 0; n < 9; n++) {
            short b = (short) (1 << ((n + startN) % 9));
            if ((puzzle[i] & b) != 0) {
                if (!quiet) {
                    print();
                    System.out.print("Guessing " + (log2(b) + 1) + " at row " + ((i / 9) + 1) + ", col " + ((i % 9) + 1) + "... ");
                }
                puzzle[i] = b;
                guesses++;
                while (solvePass()) ;
                if (isSolved()) {
                    solutions++;
                    if (!quiet)
                        System.out.println("solved");
                    lastSolution = Arrays.copyOf(puzzle, puzzle.length);
                    // backtrack to see if other solutions exist
                    System.arraycopy(gridCopy, 0, puzzle, 0, puzzle.length);
                    continue;
                }
                if (!isOk()) {
                    // backtrack
                    if (!quiet) {
                        System.out.println("failed");
                        this.print();
                        System.out.println("backtracking");
                    }
                    backtracks++;
                    System.arraycopy(gridCopy, 0, puzzle, 0, puzzle.length);
                    continue;
                }
                solveByGuessing(quiet, randomGuessLocation);
            }
        }
        if (lastSolution != null)
            System.arraycopy(lastSolution, 0, puzzle, 0, puzzle.length);
    }

    /**
     * Returns true if the puzzle is in a valid state, that is, if all cells have at least one possible value.
     * @return true if the puzzle is in a valid state
     */
    public boolean isOk() {
        for (final short cell : puzzle)
            if (cell == 0)
                return false;
        return true;
    }

    private int findFirstUnsolved(final int startingFrom) {
        for (int i = 0; i < puzzle.length; i++)
            if (!isPow2(puzzle[(startingFrom + i) % puzzle.length]))
                return i;
        return -1;
    }

    /**
     * Returns a string representation of the statistics for the puzzle.
     */
    public String getStatistics() {
        return (name != null ? " Name: " + name : "") +
                (passes > 0 ? " Passes: " + passes : "") +
                (reduces > 0 ? " Reduces: " + reduces : "") +
                (loners > 0 ? " Loners: " + loners : "") +
                (guesses > 0 ? " Guesses: " + guesses : "") +
                (backtracks > 0 ? " Backtracks: " + backtracks : "") +
                (solutions > 1 ? " Solutions: " + solutions : "") +
                (rounds > 0 ? " Rounds: " + rounds : "");
    }

    /**
     * Returns a string representation of the puzzle.
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(9 * (9 + 3) - 1);
        int i = 0;
        for (final short cell : puzzle) {
            if (i != 0 && i % 9 == 0)
                sb.append('\n');
            else if (i % 3 == 0)
                sb.append(' ');
            sb.append(isPow2(cell) ? log2(cell) + 1 : '.');
            i++;
        }
        return sb.toString();
    }


    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_BG_DARK_GRAY = "\u001B[100m";
    private static final String ANSI_BG_YELLOW = "\u001B[103m";

    /**
     * Prints the puzzle to standard out.
     */
    public void print() {
        for (int i = 0, row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                // checkerboard grid squares
                int gridNum = gridAt(row, col);
                if (gridNum % 2 == 0)
                    System.out.print(ANSI_BG_DARK_GRAY);
                else
                    System.out.print(ANSI_RESET);

                final short cell = puzzle[i++];
                if (cell == 0)
                    System.out.print(ANSI_RED + ANSI_BG_YELLOW + '!' + ANSI_RESET); // error
                else if (isPow2(cell))
                    System.out.print(log2(cell) + 1); // known
                else
                    System.out.print('.'); // unknown
            }
            System.out.println(ANSI_RESET);
        }
    }

    private static int gridAt(final int row, final int col) {
        return (row / 3) * 3 + (col / 3);
    }

    private static int subGrid(final int gridNum, final int inGridIndex) {
        final int gridRow = gridNum / 3, gridCol = gridNum % 3;
        final int subGridRow = inGridIndex / 3, subGridCol = inGridIndex % 3;
        return (gridRow * 3 + subGridRow) * 9 + gridCol * 3 + subGridCol;
    }

    private static final double LOG2 = Math.log(2);

    private static int log2(final short n) {
        return (int) (Math.log(n) / LOG2);
    }

    private static boolean isPow2(final short n) {
        return n != 0 && (n & (n - 1)) == 0;
    }
}