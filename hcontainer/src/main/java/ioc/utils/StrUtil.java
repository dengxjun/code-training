package ioc.utils;

/**
 * @Auther: 邓小军
 * @Date: 2020/1/20 10:52
 * @Description:
 */
public class StrUtil {

    public static String toLowCaseFirstChar(String originStr){
        if (originStr == null || originStr==""){
            return originStr;
        }

        String first = originStr.substring(0,1);
        return first.toLowerCase() + originStr.substring(1);
    }

    public static String toUpperCaseFirstChar(String originStr){
        if (originStr == null || originStr==""){
            return originStr;
        }

        String first = originStr.substring(0,1);
        return first.toUpperCase() + originStr.substring(1);
    }

    public static boolean isEmpty(String str){
        return str == null || str.length()==0;
    }

    public static boolean isNotEmpty(String str){
        return str != null && str.length() > 0;
    }

    public static String getSetterMethodName(String fieldName){
        return "set" + toUpperCaseFirstChar(fieldName);
    }

    public static String catPath(String... path){
        if (path.length == 0) return null;

        StringBuilder sb = new StringBuilder();
        for (String pt : path){
            if (pt.lastIndexOf("/") == (pt.length()-1)){
                pt = pt.substring(0, pt.length()-1);
            }

            if (pt.indexOf("/") !=0){
                sb.append("/");
            }
            sb.append(pt);

        }

        return sb.toString();
    }
}
