package letcode.sort;

import java.util.Arrays;

/**
 * @Auther: 邓小军
 * @Date: 2019/11/26 10:06
 * @Description:
 */
public class MergeSort {
    public static int[] mergerSort(int[] src){
        sort(src,0, src.length-1);
        return src;
    }

    public static void sort(int[] src, int L, int R){
        if (L == R) return;
        int mid = L + ((R -L) >> 1);
        sort(src, L, mid);
        sort(src, mid + 1,R);
        merger(src,L, R,mid + 1);
    }

    private static void merger(int[] src, int l, int r, int mid) {
        int[] tmp = new int[r - l + 1];

        int pl = l;
        int pr = mid;
        int i = 0;
        while (pl < mid && pr <= r){
            tmp[i++] = src[pl] < src[pr] ? src[pl++] : src[pr++];
        }

        while (pl < mid){
            tmp[i++] = src[pl++];
        }

        while (pr < r){
            tmp[i++] = src[pr++];
        }

        for (int j = l; j <= r; j++) {
            src[j] = tmp[j-l];
        }

    }

    public static void main(String[] args) {
        int[] arr = new int[]{9,3,7,5,2};
        System.out.println(Arrays.toString(mergerSort(arr)));
    }
}

