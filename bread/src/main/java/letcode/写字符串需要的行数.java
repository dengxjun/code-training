package letcode;

/**
 * @Auther: 邓小军
 * @Date: 2019/11/23 11:53
 * @Description:
 */
public class 写字符串需要的行数 {

    public static int[] numberOfLines(int[] widths, String S) {
        char[] strArr = S.toCharArray();
        int currWith = 0,currLine = 1;
        for(int i = 0; i < strArr.length; i++){
            char c = strArr[i];
            int witdh = widths[c-97];

            if (currWith + witdh > 100) {
                currWith = witdh;
                currLine ++;
            }else{
                currWith += witdh;
            }
        }
        return new int[]{currLine,currWith};
    }

    public static void main(String[] args) {
        int[] widths = new int[]{10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10};
        String s = "abcdefghijklmnopqrstuvwxyz";
        System.out.println(numberOfLines(widths,s));
    }
}
