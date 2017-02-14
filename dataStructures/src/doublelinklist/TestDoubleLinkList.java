package doublelinklist;

import single.linklist.LinkList;

/**
 * Created by NOOB on 2/12/17.
 */
public class TestDoubleLinkList {

    private static final long MEGABYTE = 1024L * 1024L;


    public static void main(String[]args){

        LinkList<Integer> list = new LinkList<Integer>();

        System.out.println(list.getClass() + " Test");

        final int MAX_NUMBER = 50000;
        final double TO_SECONDS = 1000.00;
        int testNumber = 1 ;
        long startTime, endTime;

        System.out.println("\tTest " + testNumber + " : Adding " + MAX_NUMBER + " elements <Integers>");
            startTime = System.currentTimeMillis();
            addingToListTest(MAX_NUMBER,list);
            endTime = System.currentTimeMillis() - startTime;
        System.out.println("\t Result for adding "+ MAX_NUMBER + ", in Seconds: "+ endTime/TO_SECONDS);


        // Get the Java runtime
        Runtime runtime = Runtime.getRuntime();
        // Run the garbage collector
        runtime.gc();
        // Calculate the used memory
        long memory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("\tUsed memory in bytes: " + memory);
        System.out.println("\tUsed memory in megabytes: " + bytesToMegabytes(memory));
    }
    public static void addingToListTest(int MAX_NUMBER,LinkList<Integer> list){
        for (int i = 0; i < MAX_NUMBER;i++){
            list.add(i);
        }
    }

    public static long bytesToMegabytes(long bytes) {
        return bytes / MEGABYTE;
    }
}
