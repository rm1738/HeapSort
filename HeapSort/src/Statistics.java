public class Statistics {
    
    private int cacheHit;
    private int cacheMiss;
    private int diskRead;
    private int write;

    public Statistics() {

        
        cacheHit = 0;
        cacheMiss = 0;
        diskRead = 0;
        write = 0;

    }


   
    public void cacheHit() {
        cacheHit++;

    }


   
    public void cacheMiss() {
        cacheMiss++;

    }


    public void diskRead() {
        diskRead++;
    }


    
    public void diskWrite() {
        write++;
    }


    
    public int cacheHitCount() {
        return cacheHit;
    }


   
    public int cacheMissCount() {
        return cacheMiss;
    }


    
    public int diskReadCount() {
        return diskRead;
    }


    
    public int diskWriteCount() {
        return write;
    }

}