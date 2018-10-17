package com.github.shipengyan.framework.util.sort;

public class BubbleSort implements SortUtil.Sort {

    public void sort(int[] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = data.length - 1; j > i; j--) {
                if (data[j] < data[j - 1]) {
                    SortUtil.swap(data, j, j - 1);
                }
            }
        }
    }

}
