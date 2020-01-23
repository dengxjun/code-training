package letcode.sort;

import java.util.Arrays;

/**
 * @Auther: 邓小军
 * @Date: 2019/11/27 13:41
 * @Description:
 */
public class SortTest {
    public static int[] mergeSort(int[] src){
        sort(src, 0, src.length - 1);
        return src;
    }

    public static void sort(int[] src, int begin, int end){

        if (begin == end) return;

        int mid = begin + ((end -begin)>> 1);

        sort(src, begin, mid);
        sort(src, mid + 1, end);

        merge(src, begin, end, mid);

    }

    private static void merge(int[] src, int begin, int end, int mid){
        int[] tmp = new int[end - begin + 1];

        int l_index = begin;
        int r_index = mid + 1;

        int i = 0;
        while (l_index <= mid && r_index <= end){
            tmp[i++] = src[l_index] < src[r_index] ?  src[l_index++] : src[r_index++];
        }

        while (l_index <= mid){
            tmp[i++] = src[l_index++];
        }

        while (r_index <= end){
            tmp[i++] = src[r_index++];
        }


        for (int j = begin; j <= end; j++) {
            src[j] = tmp[j-begin];
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(mergeSort(new int[]{4,2,3,1,5,66,4,77,999,663,443,112})));
    }

}
