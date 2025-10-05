package Cli;

import Algorithms.InsertionSort;
import Metrics.PerformanceTracker;

import java.io.IOException;
import java.util.Random;
import java.util.Arrays;


public class BenchmarkRunner {

    public static void main(String[] args) throws IOException {

        int[] ns = new int[]{100, 1000, 10000, 100000};
        String[] distributions = new String[]{"random", "sorted", "reverse", "nearly-sorted"};
        String outFile = "benchmark_results.csv";


        int trials = 3;
        for (int n : ns) {
            for (String dist : distributions) {
                for (int t = 0; t < trials; t++) {
                    int[] arr = generate(n, dist, t);
                    PerformanceTracker tracker = new PerformanceTracker();
                    InsertionSort.sort(arr, tracker);

                    if (!isSorted(arr)) {
                        System.err.println("Sort failed for n=" + n + " dist=" + dist);
                        System.exit(2);
                    }
                    tracker.exportCSV(outFile, dist, n);
                    System.out.printf("Done: n=%d dist=%s trial=%d time=%dms comps=%d swaps=%d accesses=%d%n",
                            n, dist, t+1, tracker.getElapsedMillis(),
                            tracker.getComparisons(), tracker.getSwaps(), tracker.getArrayAccesses());
                }
            }
        }
        System.out.println("Completed benchmarking. Results appended to " + outFile);
    }

    private static int[] generate(int n, String dist, int seed) {
        Random rnd = new Random(12345 + seed);
        int[] a = new int[n];
        switch (dist) {
            case "random":
                for (int i = 0; i < n; i++) a[i] = rnd.nextInt();
                break;
            case "sorted":
                for (int i = 0; i < n; i++) a[i] = i;
                break;
            case "reverse":
                for (int i = 0; i < n; i++) a[i] = n - i;
                break;
            case "nearly-sorted":
                for (int i = 0; i < n; i++) a[i] = i;
                // make small random swaps (1% of n)
                int swaps = Math.max(1, n / 100);
                for (int s = 0; s < swaps; s++) {
                    int i = rnd.nextInt(n);
                    int j = rnd.nextInt(n);
                    int tmp = a[i]; a[i] = a[j]; a[j] = tmp;
                }
                break;
            default:
                for (int i = 0; i < n; i++) a[i] = rnd.nextInt();
        }
        return a;
    }

    private static boolean isSorted(int[] a) {
        for (int i = 1; i < a.length; i++) if (a[i-1] > a[i]) return false;
        return true;
    }
}
