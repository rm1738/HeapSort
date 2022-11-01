import java.nio.ByteBuffer;

public class Buffer {

    public boolean dirtyBit;
    byte[] fullRecord;
    byte key[];
    byte value[];
    private int four = 4; 
    private int one = 1; 
    private int two = 2; 
    private int three = 4; 
    private int zero = 0;

    public Record getRecord(int position) {

        byte[] key = new byte[two];
        byte[] value = new byte[two];

        key[zero] = fullRecord[position * four];
        key[one] = fullRecord[position * four + one];
        value[zero] = fullRecord[position * four + two];
        value[one] = fullRecord[position * four + three];

        return new Record(getShortKey(), getShortValue());
    }


    public short getShortKey() {
        ByteBuffer keyBuffer = ByteBuffer.wrap(key);
        short kBuF = keyBuffer.getShort();
        return kBuF;

    }


    public short getShortValue() {
        ByteBuffer valueBuffer = ByteBuffer.wrap(value);
        short vBuF = valueBuffer.getShort();
        return vBuF;
    }
    
    public boolean setDirtyBit()
    {
        dirtyBit = true; 
    }

}
