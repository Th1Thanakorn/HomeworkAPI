package com.thana.api.utils.sugarapi;

import java.util.Arrays;

public class ArrayHelper {

    /**
     * Skip any arrays by cloning and filtering an array using the index counter.
     * Utilities By: HTML Channel.
     *
     * @param array Input of array.
     * @param index Start counting and collecting index of array.
     * @return Skipped array on the given index.
     */
    public static <T> T[] skip(T[] array, int index) {
        T[] copy = Arrays.copyOf(array, array.length - 1);
        for (int i = 0; i < array.length; i++) {
            if (i >= index) {
                copy[i - index] = array[i];
            }
        }
        return copy;
    }
}
