package Metrics;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class PerformanceTracker {
    private long comparisons = 0;
    private long swaps = 0;
    private long arrayAccesses = 0;
    private long startTimeNs = 0;
    private long endTimeNs = 0;

    public void startTimer() { startTimeNs = System.nanoTime(); }
    public void stopTimer() { endTimeNs = System.nanoTime(); }

    public void addComparison() { comparisons++; }
    public void addComparisons(long k) { comparisons += k; }
    public void addSwap() { swaps++; }
    public void addSwaps(long k) { swaps += k; }
    public void addArrayAccess() { arrayAccesses++; }
    public void addArrayAccesses(long k) { arrayAccesses += k; }

    public long getComparisons() { return comparisons; }
    public long getSwaps() { return swaps; }
    public long getArrayAccesses() { return arrayAccesses; }
    public long getElapsedMillis() {
        if (endTimeNs == 0) stopTimer();
        return TimeUnit.NANOSECONDS.toMillis(endTimeNs - startTimeNs);
    }

    public String toCSVRow(String label, int n) {
        return String.format("%s,%d,%d,%d,%d,%d\n",
                label,
                n,
                getElapsedMillis(),
                getComparisons(),
                getSwaps(),
                getArrayAccesses());
    }

    public static String csvHeader() {
        return "label,n,time_ms,comparisons,swaps,array_accesses\n";
    }

    public void exportCSV(String filename, String label, int n) throws IOException {
        try (FileWriter fw = new FileWriter(filename, true)) {
            if (new java.io.File(filename).length() == 0) {
                fw.write(csvHeader());
            }
            fw.write(toCSVRow(label, n));
        }
    }

    public void reset() {
        comparisons = swaps = arrayAccesses = 0;
        startTimeNs = endTimeNs = 0;
    }
}
