package letcode.sort;

import java.util.Arrays;

/**
 * @Auther: 邓小军
 * @Date: 2019/11/26 11:32
 * @Description:
 */
public class HeapSort {
    public static int[] heapSort(int[] src,int size){

        for (int i = 0; i < size; i++) {
            int index = i;
            while (src[index] > src[(index-1)/2]){
                int tmp = src[(index-1)/2];
                src[(index-1)/2] = src[index];
                src[index] = tmp;

                index = (index-1)/2;
            }
        }

        swap(src, 0, size-1);

        if (size > 1){
            heapSort(src,--size);
        }

        return src;
    }

    private static void swap(int[] src, int start, int end){
        int tmp = src[start];
        src[start] = src[end];
        src[end] = tmp;
    }

    public static int[] getChild(int[] src, int elem){
        int[] result = new int[2];
        for (int i = 0; i < src.length; i++) {
            if (src[i] == elem){
                int leftChild = src[2*i + 1];
                int rightChild = src[2*i + 2];
                result[0] = leftChild;
                result[1] = rightChild;
            }
        }

        return result;
    }

    public static void main(String[] args) {
//        int[] heap_order = new int[]{7,6,5,4,3,2,1};
        int[] heap_order = new int[]{4,5,3,0,1,7,2,6};
        System.out.println(Arrays.toString(heapSort(heap_order,heap_order.length)));
    }
}
