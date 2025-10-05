package Algorithms;

import Metrics.PerformanceTracker;
import java.util.Objects;


public class InsertionSort {


    public static void sort(int[] arr, PerformanceTracker tracker) {
        Objects.requireNonNull(arr, "Input array cannot be null");
        if (tracker != null) tracker.startTimer();

        int n = arr.length;
        boolean alreadySorted = true;
        for (int i = 1; i < n; i++) {
            if (tracker != null) { tracker.addArrayAccesses(2); tracker.addComparison(); }
            if (arr[i - 1] > arr[i]) {
                alreadySorted = false;
                break;
            }
        }
        if (alreadySorted) {
            if (tracker != null) tracker.stopTimer();
            return;
        }


        for (int i = 1; i < n; i++) {
            int key = arr[i];
            if (tracker != null) { tracker.addArrayAccess(); }
            int insertPos = binarySearchInsertPos(arr, key, 0, i - 1, tracker);


            for (int j = i - 1; j >= insertPos; j--) {
                if (tracker != null) { tracker.addArrayAccesses(2); tracker.addSwap(); }
                arr[j + 1] = arr[j];
            }
            if (tracker != null) tracker.addArrayAccess();
            arr[insertPos] = key;
        }

        if (tracker != null) tracker.stopTimer();
    }


    private static int binarySearchInsertPos(int[] arr, int key, int l, int r, PerformanceTracker tracker) {
        int low = l, high = r;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (tracker != null) { tracker.addArrayAccesses(1); tracker.addComparison(); }
            if (arr[mid] == key) {
                return mid + 1;
            } else if (arr[mid] < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    public static void sort(int[] arr) {
        sort(arr, null);
    }
}
