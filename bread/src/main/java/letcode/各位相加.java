package letcode;

/**
 * @Auther: 邓小军
 * @Date: 2019/11/23 14:09
 * @Description: 你可以不使用循环或者递归，且在 O(1) 时间复杂度内解决这个问题吗？
 */
public class 各位相加 {
    public static int addDigits(int num) {
        if (num < 10) return num;

        char[] nArr = String.valueOf(num).toCharArray();
        int sum = 0;
        for(char n : nArr){
            sum += n-48;
        }
        return addDigits(sum);
    }

    public static void main(String[] args) {
        System.out.println(addDigits(38));
    }
}
