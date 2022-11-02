import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * A memory manager package for variable length
 * records, and a spatial data structure to support
 * geographical queryies.
 * 
 * @author {your PID}
 */
public class HeapSort {

    private static final int recordSize = 4;

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
        file = new File(args[0]);

        if (!file.exists()) {
            System.out.println("The input file does not exist " + args[0]);
            return;
        }

        // Sorting
        int sizeOfPool = Integer.parseInt(args[1]);
        BufferPool bufferPool = new BufferPool(file, sizeOfPool, statistics);
        long startOfSort = System.currentTimeMillis();
        int numRecords = (int)file.length() / recordSize;
        MaxHeap heap = new MaxHeap(numRecords, bufferPool);
        heap.heapSort(numRecords);
        bufferPool.flush();
        long endOfSort = System.currentTimeMillis();
        long timeTakenToSort = endOfSort - startOfSort;

        // Statistics File
        File statisticsFile = new File(args[2]);
        FileWriter writer = new FileWriter(statisticsFile, false);
        writer.close();
        writer = new FileWriter(statisticsFile, true);
        writer.write("Name of File: " + args[0]);
        writer.write("\nNumber of Cache Hits: " + statistics.cacheHitCount());
        writer.write("\nNumber of Cache Misses: " + statistics
            .cacheMissCount());
        writer.write("\nNumber of Disk Reads: " + statistics.diskReadCount());
        writer.write("\nNumber of Disk Writes: " + statistics.diskWriteCount());
        writer.write("\nTime taken to Sort: " + timeTakenToSort + "\n");
        writer.close();

        // Random Access File
        @SuppressWarnings("resource")
        RandomAccessFile random = new RandomAccessFile(args[0], "rw");
        int numberOfRecords = (int)file.length() / 4;
        int i = 0;
        boolean t = i < numberOfRecords / 1024;
        while (t) {
            if (i > 0 && i % 8 == 0) {
                System.out.println();
            }

            int index = i * 1024 * 4;

            random.seek(index);
            int recordKey = random.readUnsignedShort();

            random.seek(index + 2);
            int recordValue = random.readUnsignedShort();

            Record rc = new Record(recordKey, recordValue);
            System.out.print(rc.getKey() + " " + rc.getValue() + "    ");
            i++;

        }

        random.close();

    }

}
