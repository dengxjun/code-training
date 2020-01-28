package ioc.util;

import java.util.regex.Pattern;

/**
 * @author: 邓小军
 * @since: 2020/1/19 17:29
 *
 */
public class PathUtil {

    public static String convertToFullClasspath(String filePath){
        if (Pattern.matches("^(\\w+/)+\\w*.class$",filePath)){
            int idx = filePath.indexOf(".class");
            return filePath.substring(0,idx).replace("/",".");
        }else
            return filePath;
    }
}
