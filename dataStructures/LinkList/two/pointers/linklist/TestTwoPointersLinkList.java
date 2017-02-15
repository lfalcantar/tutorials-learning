package two.pointers.linklist;

import single.linklist.LinkList;

/**
 * Created by Luis Alcantar on 2/12/17.
 */
public class TestTwoPointersLinkList {

    private static final long MEGABYTE = 1024L * 1024L;
    private static final int CONTROL_VARIABLE = 100000;
    private static final double TO_SECONDS = 1000.00;
    private static Runtime runtime;


    public static void main(String[] args) {
        runtime = Runtime.getRuntime();
        TwoPointersLinkList<Integer> list = new TwoPointersLinkList<Integer>();

        System.out.println(list.getClass() + " Test");

        int testNumber = 1;
        long startTime, endTime;


        System.out.println("Test " + testNumber++ + " : Adding " + CONTROL_VARIABLE + " to lists");
        System.out.print("\tOLD-SIZE :" + list.size());
        startTime = System.currentTimeMillis();
        addingToListTest(list);
        endTime = System.currentTimeMillis() - startTime;
        System.out.println(", NEW_SIZE:" + list.size());
        System.out.println("\tResult for adding " + CONTROL_VARIABLE + ", in Seconds: " + endTime / TO_SECONDS);

        memoryUsage();

        System.out.println("Test " + testNumber++ + " : the removal of  " + CONTROL_VARIABLE + " from lists");
        System.out.print("\tOLD-SIZE :" + list.size());
        startTime = System.currentTimeMillis();
        deletingElementsTest(list);
        endTime = System.currentTimeMillis() - startTime;
        System.out.println(", NEW_SIZE:" + list.size());
        System.out.println("\tResult for Removing " + CONTROL_VARIABLE + ", in Seconds: " + endTime / TO_SECONDS);

        memoryUsage();

        System.out.println("Test " + testNumber++ + " : removing all elements using getLast()");
        System.out.print("\tOLD-SIZE :" + list.size());
        startTime = System.currentTimeMillis();
        getAllElements(list);
        endTime = System.currentTimeMillis() - startTime;
        System.out.println(", NEW_SIZE:" + list.size());
        System.out.println("\tResult removing all element using getLast(), in Seconds: " + endTime / TO_SECONDS);

        memoryUsage();


    }

    public static void addingToListTest(TwoPointersLinkList<Integer> list) {
        for (int i = 0; i < CONTROL_VARIABLE; i++) {
            list.add(i);
        }
    }

    public static void deletingElementsTest(TwoPointersLinkList<Integer> list) {
        final int MAX = CONTROL_VARIABLE;
        final int MIN = 0;

        final int limit = CONTROL_VARIABLE / 2;

        int range = (MAX - MIN) + 1;

        for (int i = 0; i < limit; i++) {
            list.delete((int) (Math.random() * range) + MIN);
        }
    }

    public static void getAllElements(TwoPointersLinkList<Integer> list) {
        int limit = list.size();
        for (int i = 0; i < limit; i++) {
            list.getLast();
        }
    }

    public static void memoryUsage() {
        // Run the garbage collector
        runtime.gc();
        // Calculate the used memory
        long memory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("\tUsed memory in bytes: " + memory);
        System.out.println("\tUsed memory in megabytes: " + bytesToMegabytes(memory));
    }

    public static long bytesToMegabytes(long bytes) {
        return bytes / MEGABYTE;
    }
}
