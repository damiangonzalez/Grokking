package Algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class PairWithTargetSum {

    public static int[] search(int[] arr, int targetSum) {
        boolean done = false;
        int startIndex = 0;
        int endIndex = arr.length - 1;
        while (!done) {
            int startNum = arr[startIndex];
            int endNum = arr[endIndex];
            if (startNum + endNum == targetSum) {
                return new int[]{startIndex, endIndex};
            } else if (startNum + endNum < targetSum) {
                startIndex = startIndex + 1;
            } else {
                endIndex = endIndex - 1;
            }

            if (startIndex > endIndex) {
                done = true;
            }
        }

        return new int[]{-1, -1};
    }
}

class TwoPointersMain {
    public static void main(String[] args) {
        System.out.println(PairWithTargetSum.search(new int[]{1, 2, 3, 4, 6}, 6));
        System.out.println(PairWithTargetSum.search(new int[]{2, 5, 9, 11}, 11));
    }
}
