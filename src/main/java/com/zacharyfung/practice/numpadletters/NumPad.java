package com.zacharyfung.practice.numpadletters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author ZacharyFung
 * @date 2020/9/16 21:46
 */

public class NumPad {

    /**
     * Numbers on Numpad: from 0 to 9
     */
    private static final int MAX_NUM = 9;
    private static final int MIN_NUM = 0;


    /**
     * Size use Double: Case n=99 then max will be 4 to the power of 99 -> exceed 'long' margins
     */
    private static double COMBINATION_SIZE = 1;

    /**
     * User's Input stores here.
     */
    private static int[] INPUT_NUMBERS = {};

    /**
     * Array of 'number to letters' sets
     */
    private static final String[][] numPadSet = {{}, {}, {"a", "b", "c"}, {"d", "e", "f"}, {"g", "h", "i"}
            , {"j", "k", "l"}, {"m", "n", "o"}
            , {"p", "q", "r", "s"}, {"t", "u", "v"}, {"w", "x", "y", "z"}};


    /**
     * @param num The input number
     * @return The letters in String array format
     * @throws Exception if number is not on numpad
     */
    private String[] getLettersByNum(int num) throws IllegalNumberException {
        if (num > MAX_NUM || num < MIN_NUM) {
            throw new IllegalNumberException("Illegal Number on Numpad!");
        }
        return numPadSet[num];
    }

    public List<String> getCombinations(int[] numbers) throws IllegalNumberException {
        //Mark down User-Input numbers
        INPUT_NUMBERS = numbers;

        //Verify numbers. If one of them is illegal, throws exception
        this.verifyNumbers(numbers);

        //Using HashMap's keys to store results, to avoid duplicate combinations
        HashMap<String, String> combinationsBook = new HashMap<String, String>();

        //To store all the combinations(may contains duplicated combinations)
        List<String> combinations = new ArrayList<String>();

        //Calculate total combination size (may contains duplicated combinations)
        for (int number : numbers) {
            if (number == 0 || number == 1) {
                continue;
            }
            COMBINATION_SIZE = COMBINATION_SIZE * this.getLettersByNum(number).length;
        }

        //Start the action
        this.findAllCombinations(combinations, 0, "");

        //Save combinations into HashMap key-set
        for (String com : combinations) {
            combinationsBook.put(com, "");
        }

        //Write in results
        List<String> results = new ArrayList<String>(combinationsBook.keySet());

        //Sort Results for better reading
        Collections.sort(results);

        return results;
    }


    /**
     * @param allCombinations Initial variable declares to be Combinations
     * @param inputIndex      The index of User-Input numbers
     * @param onGoing         A String to pass down to next (find_combination) recursion
     * @throws Exception If one of the number is not on the numpad.
     */
    private void findAllCombinations(List<String> allCombinations, int inputIndex, String onGoing) throws IllegalNumberException {
        //if the number handled has reach the end
        if (inputIndex == INPUT_NUMBERS.length) {
            allCombinations.add(onGoing);
            return;
        }

        //Get all the letters of this number;
        String[] letters = this.getLettersByNum(INPUT_NUMBERS[inputIndex]);

        //Get all the rest (increase the index by 1 for each loop)
        for (String letter : letters) {
            onGoing = onGoing + letter;
            findAllCombinations(allCombinations, inputIndex + 1, onGoing);
            //If loop goes on , remove current suffixed letter (else case: ad,ae -> ad,ade)
            onGoing = onGoing.substring(0, onGoing.length() - 1);
        }
    }

    /**
     * @param numbers User-Input numbers
     * @throws Exception If one of the number is not on the numpad
     *                   Verified by checking the method "this.getLettersByNum($num)"
     */
    private void verifyNumbers(int[] numbers) throws IllegalNumberException {
        for (int num : numbers) {
            this.getLettersByNum(num);
        }
    }

}


