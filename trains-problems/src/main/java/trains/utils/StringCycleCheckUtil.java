package trains.utils;

/**
 * @Auther: 邓小军
 * @Date: 2019/12/30 20:00
 * @Description:
 */
public class StringCycleCheckUtil {

    /**
     * 循环字串检测
     * @param str
     * @return
     */
    public static boolean isCycle(String str){
        boolean result = false;
        for (int i = 1; i < (str.length()-1) /2; i++) {
            String s = str.substring(str.length()-i,str.length());
            String t = str.substring(str.length()-2*i,str.length()-i);

            if (s.equals(t)){
                result = true;
                break;
            }
        }
        return result;
    }
}
