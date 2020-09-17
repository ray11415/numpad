package com.zacharyfung.practice.numpadletters;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
class NumpadlettersApplicationTests {

    NumPad numpad = new NumPad();

    /**
     * Question:Stage 2;
     * Testing numpad with 2 digits random number;
     */
    @Test
    void twoDigitTest() {
        List<Integer> test = new ArrayList<>();
        test.add(23);
        test.add(21);
        printOut(this.numpad.getCombinations(test));
    }

    /**
     * Testing numpad with n digits random number;
     * Condition: 0<n<99
     */
    @Test
    void nDigitTest() {
        List<Integer> someHugeDigits = new ArrayList<>();
        for (int i = 0; i < 99; i++) {
            someHugeDigits.set(i, getRandom());
        }
        printOut(this.numpad.getCombinations(someHugeDigits));
    }

    @Test
    void from0To99() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            numbers.set(i, i);
        }
        printOut(this.numpad.getCombinations(numbers));
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
