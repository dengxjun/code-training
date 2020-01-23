package letcode.sort;

import java.util.Arrays;

/**
 * @Auther: 邓小军
 * @Date: 2019/11/26 17:31
 * @Description:
 */
public class FastSort {
    public static int[] fastSort(int[] src, int left, int right){

        int flagIndex = left;
        boolean flag = true;

        int leftBegin = left;
        int rightBegin = right;

        while (left < right){
            int tar = src[flagIndex];
            if (flag){
                if (src[right] < tar) {
                    int tmp = src[right];
                    src[right] = tar;
                    src[flagIndex] = tmp;
                    flagIndex = right;
                    flag = false;
                }else{
                    right --;
                }
            }else {
                if (src[left] > tar){
                    int tmp = src[left];
                    src[left] = tar;
                    src[flagIndex] = tmp;
                    flagIndex = left;
                    flag = true;
                }else {
                    left++;
                }
            }

        }

        if (leftBegin != left){
            fastSort(src,leftBegin,left-1);
        }

        if (rightBegin != right){
            fastSort(src, right+1, rightBegin);
        }

        return src;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{7,3,6,4,9,1,8,66,11,55,22,88,1000,111};

        System.out.println(Arrays.toString(fastSort(arr, 0, arr.length - 1)));
    }
}
