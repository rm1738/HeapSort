import java.io.IOException;

public class MaxHeap {

    private BufferPool pool;
    private int currentSize;
    private int maximumSize;
    private int zero = 0;
    private int one = 1;
    private int two = 2;

    public MaxHeap(BufferPool p, int number, int max) throws IOException {
        pool = p;
        currentSize = number;
        maximumSize = max;
        createHeap();

    }


    public void createHeap() {
        int newSize = currentSize / two - one;
        for (int i = newSize; i >= zero; i--) {

            heapify(i);
        }
    }


    private void heapify(int position) {

        boolean wrongP = position < 0;
        boolean wrongSize = position >= currentSize / 2;

        if (wrongP || wrongSize)

        {
            return;

        }

        int greatest = position;
        int left = (two * position) + one;
        int right = (two * position) + two;

        if ((left < currentSize) && (pool.getRecord(left).getKey() > pool.getRecord(position).getKey()) {
            greatest = left;
        }
        
        if (right < currentSize && pool.getRecord(right).getKey() > pool.getRecord(greatest)
            .getKey()) {

            greatest = right;
        }
        
        
        if (greatest != position)
        {
            swap(position,greatest);
            heapify(greatest); 
        }

    }


    private void swap(int a, int b) {

    }


    public Record removeMax() throws IOException {
        if (currentSize == 0) {
            return null;
        }
        swap(0, --currentSize);
        heapify(0);

        return pool.get(currentSize);
    }


    public void heapSort(int heapSize) throws IOException {

        for (int i = 0; i < heapSize - 1; i--) {

            removeMax();
        }
    }
}
