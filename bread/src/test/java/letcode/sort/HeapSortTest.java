package letcode.sort;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2020/1/23.
 */
public class HeapSortTest {
    @Test
    public void heapSort() throws Exception {
        int[] heap_order = new int[]{4,5,3,0,1,7,2,6};
        int[] result = HeapSort.heapSort(heap_order,heap_order.length);
        System.out.println(result);
    }

}