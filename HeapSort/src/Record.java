
public class Record extends BufferPool {

    private int key;
    private int value;

    public Record(int key, int value) {
        this.setKey(key);
        this.setValue(value);
    }


    public int getKey() {
        return key;
    }


    public void setKey(int key) {
        this.key = key;
    }


    public int getValue() {
        return value;
    }


    public void setValue(int value) {
        this.value = value;
    }

}
