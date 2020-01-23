package letcode;

/**
 * @Auther: 邓小军
 * @Date: 2019/11/23 10:58
 * @Description:
 */
public class 唯一摩尔斯密码词 {

    public static int uniqueMorseRepresentations(String[] words) {
        String[] dict = new String[]{".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
        String[] afterArr = new String[words.length];
        int repeatCount = 0;
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            char[] chars = word.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (char c :
                    chars) {
                sb.append(dict[c - 97]);
            }

            String afterStr = sb.toString();

            for (String s: afterArr) {
                if (afterStr.equals(s)){
                    repeatCount++;
                    break;
                }
            }
            afterArr[i] = afterStr;
        }

        return words.length - repeatCount;
    }

    public static void main(String[] args) {
//        System.out.println(uniqueMorseRepresentations(new String[]{"gin", "zen", "gig", "msg"}));
        System.out.println(uniqueMorseRepresentations(new String[]{"rwjje","aittjje","auyyn","lqtktn","lmjwn"}));
        char aa = 'b';
        System.out.println(aa+1);
    }
}
