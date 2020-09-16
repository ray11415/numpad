package com.zacharyfung.practice.numpadletters;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
class NumpadlettersApplicationTests {

    NumPad numpad = new NumPad();

    /**
     * Testing numpad with 2 digits random number;
     */
    @Test
    void twoDigitTest() {
        int[] test = {2, 3};
        try {
            printOut(this.numpad.getCombinations(test));
        } catch (IllegalNumberException e) {
            e.printStackTrace();
        }
    }

    /**
     * Testing numpad with n digits random number;
     * Condition: 0<n<99
     */
    @Test
    void nDigitTest() {
        int[] someHugeDigits = new int[99];
        for (int i = 0; i < 99; i++) {
            someHugeDigits[i] = getRandom();
        }
        try {
            printOut(this.numpad.getCombinations(someHugeDigits));
        } catch (IllegalNumberException e) {
            e.printStackTrace();
        }
    }

    @Test()
    void testIllegalNumber() throws IllegalNumberException {
        int[] illegalDigits = {13, 14, 15};
        printOut(this.numpad.getCombinations(illegalDigits));
    }

    private int getRandom() {
        Random random = new Random();
        return random.nextInt(9);
    }

    private void printOut(List<String> strings) {
        for (String s : strings) {
            System.out.println(s);
        }
    }
}
