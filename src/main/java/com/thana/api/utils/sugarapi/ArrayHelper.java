package com.thana.api.utils.sugarapi;

// Utilities from: HTMLChannel
public class ArrayHelper {

    public static <T> T[] skip(T[] array, int index) {
        T[] copy = array.clone();
        System.arraycopy(array, index, copy, 0, array.length - 1);
        return copy;
    }
}
