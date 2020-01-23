package letcode;

/**
 * @Auther: 邓小军
 * @Date: 2019/11/23 13:52
 * @Description:
 */
public class 单词规律 {
    public static boolean wordPattern(String pattern, String str) {
        char[] patternArr = pattern.toCharArray();
        String[] strArr = str.split(" ");
        String[] mapArr = new String[133];

        if (patternArr.length != strArr.length){
            return false;
        }

        for(int i=0; i < patternArr.length; i++){
            if (mapArr[patternArr[i]] != null) {
                if (!mapArr[patternArr[i]].equals(strArr[i])){
                    return false;
                }
            }else {
                for(String mapValue : mapArr){
                    if (mapValue != null && mapValue.equals(strArr[i])){
                        return false;
                    }
                }
            }

            mapArr[patternArr[i]] = strArr[i];
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(wordPattern("abba","dog cat cat dog"));
    }
}
