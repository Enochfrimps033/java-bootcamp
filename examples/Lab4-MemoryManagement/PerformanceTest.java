public class PerformanceTest {

    private static class SampleObject {
        private final int value;
        private final byte[] data = new byte[64];

        SampleObject(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        System.out.println("===== Performance Measurement =====");
        MemoryMonitor.printMemoryReport("Start");

        int[] objectCounts = {10, 100, 1_000, 100_000, 1_000_000};

        System.out.println();
        System.out.printf("%-12s %-14s %-18s%n",
                "Objects", "Used Memory", "Execution Time");
        System.out.println("--------------------------------------------------");

        for (int count : objectCounts) {
            runAllocationTest(count);
        }

        System.out.println();
        System.out.println("Additional measurements:");

        measureLoopExecution();
        measureArrayAllocation();
        measureLargeByteArray();
    }

    private static void runAllocationTest(int count) {
        MemoryMonitor.triggerGarbageCollection();

        long memoryBefore = MemoryMonitor.getUsedMemoryBytes();
        long start = System.nanoTime();

        SampleObject[] objects = new SampleObject[count];

        for (int objectNumber = 0; objectNumber < count; objectNumber++) {
            objects[objectNumber] = new SampleObject(objectNumber);
        }

        long elapsedMillis =
                (System.nanoTime() - start) / 1_000_000;

        long memoryAfter = MemoryMonitor.getUsedMemoryBytes();
        long memoryUsed = memoryAfter - memoryBefore;

        System.out.printf("| %d | %d bytes | %d ms |%n",
                count, memoryUsed, elapsedMillis);

        objects = null;
        MemoryMonitor.triggerGarbageCollection();
    }

    private static void measureLoopExecution() {
        long start = System.nanoTime();

        long sum = 0;

        for (int i = 0; i < 10_000_000; i++) {
            sum += i;
        }

        long elapsedMillis =
                (System.nanoTime() - start) / 1_000_000;

        System.out.println("Loop sum: " + sum);
        System.out.println(
                "Loop execution time: " + elapsedMillis + " ms");
    }

    private static void measureArrayAllocation() {
        long start = System.nanoTime();

        int[] numbers = new int[1_000_000];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i;
        }

        long elapsedMillis =
                (System.nanoTime() - start) / 1_000_000;

        System.out.println(
                "Array allocation time: " + elapsedMillis + " ms");
    }

    private static void measureLargeByteArray() {
        MemoryMonitor.printMemoryReport("Before Large byte[]");

        byte[] largeArray = new byte[10 * 1024 * 1024];

        MemoryMonitor.printMemoryReport("After Large byte[]");

        largeArray = null;
        System.gc();

        MemoryMonitor.printMemoryReport("After Releasing Large byte[]");
    }
}