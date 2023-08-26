package com.hafthor;

public class Main {
    public static void main(final String[] args) {
        final String input6 = "input6\n" +
                "89. 625 ..." +
                ".5. 8.7 962" +
                "7.2 1.3 ..4" +
                "231 5.. .9." +
                "98. 3.2 4.5" +
                "6.. ... .38" +
                "476 95. 1.." +
                "328 .7. 6.." +
                "..9 23. .4.";
        final String input19 = "input19\n" +
                "365 2.. .7." +
                "8.. 3.6 14." +
                "... .79 .53" +
                "1.. .9. 5.." +
                "456 ... 8.7" +
                "7.. 46. 2.." +
                ".7. 624 ..5" +
                "..2 .13 7.9" +
                "53. ..7 .26";
        final String input25 = "input25\n" +
                "3.5 ... 2.6" +
                "... 15. .7." +
                "7.. 2.. 1.5" +
                ".3. .7. 65." +
                ".97 .45 ..." +
                ".6. ... 987" +
                "..3 4.1 ..." +
                "978 ..6 ..2" +
                "..2 .89 56.";
        final String input30 = "input30\n" +
                "564 ... .7." +
                "2.. .9. 5.." +
                "... 56. 21." +
                "..5 7.. 462" +
                ".8. 4.6 .51" +
                ".26 3.5 ..8" +
                "9.. .53 1.." +
                "142 ..8 ..." +
                "... ..2 987";
        final String input46 = "input46\n" +
                ".98 .3. 5.." +
                ".6. ..7 21." +
                ".3. ..4 87." +
                "8.. ..2 6.." +
                "3.6 9.. ..4" +
                "2.. 65. .8." +
                "6.. .8. .51" +
                "5.. 4.6 ..8" +
                "..7 ... .6.";
        final String input51 = "input51\n" +
                ".13 2.6 8.." +
                "... 135 246" +
                "..4 ... .35" +
                "79. ... .5." +
                "4.. .8. ..1" +
                "... 65. .87" +
                "..1 56. 7.8" +
                ".7. .12 ..." +
                "3.6 7.. 41.";
        final String input66 = "input66\n" +
                "... 4.. 89." +
                "2.1 7.. ..." +
                ".79 ... .1." +
                ".6. 32. 78." +
                "1.. ... 4.6" +
                "798 .5. ..." +
                ".1. .79 ..2" +
                "..4 1.. ..8" +
                ".8. ..6 53.";
        final String input71 = "input71\n" +
                "... ... 78." +
                "9.8 1.. 5.." +
                "6.3 978 ..." +
                ".8. 3.5 ..4" +
                "... 4.6 897" +
                ".2. ... .5." +
                "... .9. .1." +
                "... .3. 645" +
                "231 .6. ...";
        final String input76 = "input76\n" +
                ".6. 879 1.." +
                "89. 2.. 4.." +
                "2.. ... 7.." +
                "9.8 ... .5." +
                "... 63. ..." +
                "..3 .87 ..." +
                ".15 .6. .87" +
                ".2. 79. ..3" +
                "... 3.. 6.4";
        final String input87 = "input87\n" +
                "... ... 5.3" +
                "..5 ... .8." +
                "7.. 1.3 6.." +
                "..4 .78 1.2" +
                ".97 ... ..5" +
                "... 64. 7.." +
                "97. .2. .5." +
                "6.3 .8. .4." +
                "1.. .3. .79";
        final String input92 = "input92\n" +
                "... .87 ..." +
                ".89 .2. .5." +
                "..6 ..3 ..7" +
                "14. ..9 ..." +
                "... ..6 .21" +
                ".53 ... 7.9" +
                "... ... 6.." +
                "5.. 13. ..8" +
                "8.7 ... 3..";
        final String input97 = "input97\n" +
                ".6. ... 2.4" +
                "7.8 41. 3.." +
                "... ... 8.." +
                "1.5 ... .78" +
                "... .35 .4." +
                ".4. ..9 ..1" +
                ".2. 6.. .89" +
                ".54 98. ..." +
                "9.. ..1 ..6";
        final String input102 = "input102\n" +
                "... 8.9 ..." +
                "1.. 5.. 7.." +
                "7.9 ... .56" +
                "... .3. ..." +
                ".65 ... .14" +
                "... .2. 365" +
                "9.. ... 6.." +
                "642 .9. ..." +
                "... .62 .78";
        final String input109 = "input109\n" +
                "..5 ... 78." +
                "12. ... ..6" +
                "9.. ..5 .2." +
                "..6 .1. ..8" +
                ".1. 87. ..." +
                ".7. .4. 3.2" +
                "3.. 7.. 264" +
                ".62 3.. ..." +
                ".98 4.. ..3";
        final String input114 = "input114\n" +
                "79. ... ..1" +
                ".65 ... ..7" +
                "... .98 5.4" +
                ".24 ... 789" +
                ".8. 62. 3.." +
                "51. ... .2." +
                "8.9 .5. 1.." +
                "..6 ..1 .7." +
                "2.1 8.9 .5.";
        final String input120 = "input120\n" +
                "98. ... 4.." +
                ".2. 789 ..." +
                "5.. 4.. ..." +
                "1.2 .64 8.." +
                "4.5 ... ..." +
                "... .31 5.6" +
                ".79 .42 63." +
                "..1 6.3 98." +
                "3.6 .7. .2.";
        final String input126 = "input126\n" +
                "89. .5. ..." +
                "2.. 7.9 ..." +
                "..4 ..3 ..." +
                "..9 6.2 ..3" +
                "... ..1 897" +
                ".15 97. ..." +
                ".5. 2.. 7.." +
                "... 3.. 4.1" +
                ".42 .9. 5..";

        // puzzles from https://sudoku.com/
        final String inputEasy = "inputEasy\n" +
                "7.9 ..2 68." +
                "..2 .5. 7.4" +
                "... ... 2.." +
                "19. ..7 .6." +
                "867 195 .4." +
                "5.4 ... .9." +
                "435 78. .2." +
                "..6 4.. ..1" +
                "98. 5.6 ..3";
        final String inputMedium = "inputMedium\n" +
                "83. 6.9 ..." +
                "6.2 ... 9.." +
                "5.. ..7 ..1" +
                "... ... .69" +
                "39. 2.8 ..." +
                ".5. 4.6 .23" +
                "92. .7. 5.." +
                "... ..5 4.." +
                ".1. 9.. ..7";
        final String inputHard = "inputHard\n" +
                "19. ... ..4" +
                "..5 ..8 .9." +
                ".3. ... 1.." +
                "56. ..4 92." +
                "... ... 415" +
                "... 18. ..." +
                "... 8.. ..." +
                "9.. .3. 67." +
                "25. 6.9 .3."; // unsolved without guessing
        final String inputExpert = "inputExpert\n" +
                "... .6. .29" +
                "7.. .8. ..." +
                "..2 ..1 ..." +
                "437 ... ..." +
                ".1. ... ..5" +
                ".8. ... 6.1" +
                "..8 7.. 3.." +
                "... 3.4 .1." +
                "... ... 4.."; // unsolved without guessing
        final String inputEvil = "inputEvil\n" +
                "8.. ... .4." +
                "3.. 8.. 56." +
                "..2 ..3 ..." +
                "5.. ... ..4" +
                "..7 .6. 95." +
                "... 9.. ..2" +
                "2.. 6.. 83." +
                "... ... ..9" +
                ".1. .7. ..."; // unsolved without guessing
        final String inputGen1 = "inputGen1\n" +
                "... ... ..." +
                "... 1.. ..6" +
                "5.. ... ..." +
                ".5. 3.. 81." +
                "... ... ..." +
                "... .8. ..." +
                "9.8 ..1 .5." +
                "... 6.8 .3." +
                "..7 ..9 ..."; // first generated puzzle

        int unsolvedCount = 0;
        for (final String input : new String[]{
                input6, input19, input25, input30, input46, input51,
                input66, input71, input76, input87, input92, input97,
                input102, input109, input114, input120, input126,
                inputEasy, inputMedium, inputHard, inputExpert, inputEvil,
                inputGen1
        }) {
            final String[] ss = input.split("\n");
            final Puzzle puzzle = new Puzzle(ss[1]);
            puzzle.setName(ss[0]);
            puzzle.solve();
            if (puzzle.isSolved()) {
                puzzle.print();
                System.out.println("*** Solved ***" + puzzle.getStatistics());
            } else {
                System.out.println("*** Couldn't solve ***" + puzzle.getStatistics());
                unsolvedCount++;
            }
        }
        System.out.println("Unsolved: " + unsolvedCount);

        for (int i = 0; i < 10; i++) {
            final Puzzle puzzle = new Puzzle(i);
            puzzle.setName("Generated #" + i);
            puzzle.print();
            System.out.println("Stats:" + puzzle.getStatistics());
        }
    }
}