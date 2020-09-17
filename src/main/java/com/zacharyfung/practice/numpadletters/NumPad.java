package com.zacharyfung.practice.numpadletters;

import java.util.*;

/**
 * @author ZacharyFung
 * @date 2020/9/16 21:46
 */

public class NumPad {

    /**
     * Array of 'number to letters' sets
     */
    private static final String[][] numPadSet = {{}, {}, {"a", "b", "c"}, {"d", "e", "f"}, {"g", "h", "i"}
            , {"j", "k", "l"}, {"m", "n", "o"}
            , {"p", "q", "r", "s"}, {"t", "u", "v"}, {"w", "x", "y", "z"}};


    /**
     * @param num The input number
     * @return The letters in String array format
     */
    private String[] getLettersByNum(int num) {
        return numPadSet[num];
    }

    public List<String> getCombinations(List<Integer> numbers) {
        //Verify numbers. If one of them is illegal, throws exception
        List<Integer> verifiedNumbers = this.verifyNumbers(numbers);

        Collections.sort(verifiedNumbers);

        //Using HashMap's keys to store results, to avoid duplicate combinations
        HashMap<String, String> combinationsBook = new HashMap<String, String>();

        //To store all the combinations(may contains duplicated combinations)
        List<String> combinations = new ArrayList<String>();

        //Start the action
        this.findAllCombinations(combinations, 0, verifiedNumbers, "");

        //Save combinations into HashMap key-set
        for (String com : combinations) {
            combinationsBook.put(com, "");
        }

        //Write in results
        List<String> results = removeDuplicates(combinationsBook.keySet());

        //Sort Results for better reading
        Collections.sort(results);

        return results;
    }


    /**
     * @param allCombinations Initial variable declares to be Combinations
     * @param inputIndex      The index of User-Input numbers
     * @param onGoing         A String to pass down to next (find_combination) recursion
     */
    private void findAllCombinations(List<String> allCombinations, int inputIndex, List<Integer> numbers, String onGoing) {
        //if the number handled has reach the end
        if (inputIndex == numbers.size()) {
            allCombinations.add(onGoing);
            return;
        }

        //Get all the letters of this number;
        String[] letters = this.getLettersByNum(numbers.get(inputIndex));

        //Get all the rest (increase the index by 1 for each loop)
        for (String letter : letters) {
            onGoing = onGoing + letter;
            findAllCombinations(allCombinations, inputIndex + 1, numbers, onGoing);
            //If loop goes on , remove current suffixed letter (else case: ad,ae -> ad,ade)
            onGoing = onGoing.substring(0, onGoing.length() - 1);
        }
    }

    /**
     * @param numbers User-Input numbers
     * @throws Exception If one of the number is not on the numpad
     *                   Verified by checking the method "this.getLettersByNum($num)"
     */
    private List<Integer> verifyNumbers(List<Integer> numbers) {
        List<Integer> checkedNumbers = new ArrayList<>();
        for (Integer number : numbers) {
            if (number > 99) {
                throw new NumberFormatException();
            }
            if (number < 10) {
                checkedNumbers.add(number);
            } else {
                checkedNumbers.add(number / 10);
                checkedNumbers.add(number % 10);
            }
        }

        for (int i = 0; i < checkedNumbers.size(); i++) {
            if (checkedNumbers.get(i) == 0 || checkedNumbers.get(i) == 1) {
                checkedNumbers.remove(i);
            }
        }
        return checkedNumbers;
    }

    private List<String> removeDuplicates(Set<String> stringSet) {
        List<String> result = new ArrayList<>();
        List<char[]> characterList = new ArrayList<>();
        for (String string : stringSet) {
            char[] arr = string.toCharArray();
            Arrays.sort(arr);
            characterList.add(arr);
        }
        String lastOne = "";
        for (char[] chars : characterList) {
            String current = new String(chars);
            if (lastOne.equals(current)) {
                continue;
            }
            result.add(current);
            lastOne = current;
        }
        return result;
    }
}