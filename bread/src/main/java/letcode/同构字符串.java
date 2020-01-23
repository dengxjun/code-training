package letcode;

/**
 * @Auther: 邓小军
 * @Date: 2019/11/23 12:37
 * @Description:
 */
public class 同构字符串 {
    public static boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;

        char[] srcArr = s.toCharArray();
        char[] tarArr = t.toCharArray();

        char[] map = new char[133];
        char[] map_target = new char[133];

        boolean result = true;

        for(int i = 0; i < srcArr.length; i++){
            char target = tarArr[i];
            char source = srcArr[i];

            char value = map[source];

            if (value > 0 &&  value != target){
                result = false;
                break;
            }else {
                char v_tar= map_target[target];
                if (v_tar > 0 && v_tar != source){

                    result = false;
                    break;

                }
                map[source] = target;
                map_target[target] = source;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(isIsomorphic("ab","aa"));
        System.out.println(isIsomorphic("aa","ab"));
        System.out.println(isIsomorphic("title","paper"));
        char[] c = new char[2];
        System.out.println(c[0]);
    }
}
