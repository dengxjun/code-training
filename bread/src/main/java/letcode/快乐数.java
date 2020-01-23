package letcode;

/**
 * @Auther: 邓小军
 * @Date: 2019/11/23 14:30
 * @Description:
 */
public class 快乐数 {
    public static boolean isHappy(int n) {

        if (n == 1) return true;

        if (n == 89) return false;

        char[] nArr = String.valueOf(n).toCharArray();

        int sum = 0;
        for (char c : nArr){
            sum += Math.pow(c-48,2);
        }

        return isHappy(sum);

    }

    public static void main(String[] args) {
        try {
            System.out.println(isHappy(12));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

