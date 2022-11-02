import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * A memory manager package for variable length
 * records, and a spatial data structure to support
 * geographical queryies.
 * 
 * @author Rahul Menon
 */
public class HeapSort {

    private static final int recordSize = 4;
    private static final int block = 1024;
    private static final int zero = 0;
    private static final int one = 1;
    private static final int two = 2;

    /**
     * This is the entry point of the application
     * 
     * @param args
     *            Command line arguments
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        Statistics statistics = new Statistics();
        File file = null;
        file = new File(args[zero]);

        if (!file.exists()) {
            System.out.println("The input file does not exist " + args[zero]);
            return;
        }

        // Sorting
        int sizeOfPool = Integer.parseInt(args[one]);
        BufferPool bufferPool = new BufferPool(file, sizeOfPool, statistics);
        long startOfSort = System.currentTimeMillis();
        int numRecords = (int)file.length() / recordSize;
        MaxHeap heap = new MaxHeap(numRecords, bufferPool);
        heap.heapSort(numRecords);
        bufferPool.flush();
        long endOfSort = System.currentTimeMillis();
        long timeTakenToSort = endOfSort - startOfSort;

        // Statistics File
        File statisticsFile = new File(args[two]);
        FileWriter writer = new FileWriter(statisticsFile, false);
        writer.close();
        writer = new FileWriter(statisticsFile, true);
        writer.write("Name of File: " + args[zero]);
        writer.write("\nNumber of Cache Hits: " + statistics.cacheHitCount());
        writer.write("\nNumber of Cache Misses: " + statistics
            .cacheMissCount());
        writer.write("\nNumber of Disk Reads: " + statistics.diskReadCount());
        writer.write("\nNumber of Disk Writes: " + statistics.diskWriteCount());
        writer.write("\nTime taken to Sort: " + timeTakenToSort + "\n");
        writer.close();

        // Random Access File
        RandomAccessFile random = new RandomAccessFile(args[zero], "rw");
        int numberOfRecords = (int)file.length() / recordSize;
        int i = 0;
        boolean t = i < numberOfRecords / block;
        while (t) {

            boolean greaterThanZero = i > 0;
            boolean noRemainder = i % 8 == 0;

            if (greaterThanZero && noRemainder) {
                System.out.println();
            }

            int neededI = i * block * 4;

            random.seek(neededI);
            int recordKey = random.readUnsignedShort();

            random.seek(neededI + two);
            int recordValue = random.readUnsignedShort();

            Record rc = new Record(recordKey, recordValue);
            System.out.print(rc.getKey() + " " + rc.getValue() + "    ");
            i++;

        }

        random.close();

    }

}
