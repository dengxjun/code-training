package letcode;

/**
 * @Auther: 邓小军
 * @Date: 2019/11/25 18:03
 * @Description:
 */
public class 寻找两个有序数组的中位数 {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] mergerArr = new int[nums1.length + nums2.length];
        int position = 0;

        for (int i=0; i < nums1.length; i++){
            mergerArr[i] = nums1[i];
        }

        int step = 0;
        int limitStep = 0;
        for (int i=0; i < nums2.length; i++){
            int src = nums2[i];
            int tmp=-9999;
            for (int j=step; j < mergerArr.length; j++){
                int tar = mergerArr[j];
                if(tmp != -9999){
                    mergerArr[j] = tmp;
                    tmp = tar;
                    continue;
                }

                if (src < tar){
                    mergerArr[j] = src;
                    limitStep ++;
                    tmp = tar;
                    step = j;
                }else if(src > tar && j >= nums1.length + limitStep){
                    mergerArr[j] = src;
                    step = j + 1;
                    break;
                }
            }
        }


        if (mergerArr.length % 2.0 == 0){
            int index = (mergerArr.length / 2) -1;
            return (mergerArr[index] + mergerArr[index+1])/2.0;
        }else {
            int index = (mergerArr.length - 1) / 2;
            return mergerArr[index];
        }
    }

    public static void main(String[] args) {
        System.out.println(findMedianSortedArrays(new int[]{1,2}, new int[]{-1,3}));
    }
}
