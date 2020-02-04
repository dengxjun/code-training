package ioc.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: 邓小军
 * @since: 2020/1/20 10:52
 *
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

    public static String convertToStringFromInputStream(InputStream ips) throws IOException {
        byte[] buffer = new byte[1024];
        int byteRead = -1;
        StringBuilder out = new StringBuilder();
        while ((byteRead = ips.read(buffer)) != -1){
            out.append(new String(buffer));
        }
        return out.toString().trim();
    }

    /**
     * 处理request中参数
     * 数据格式： aa=AA&bb=BB&dd=fe&dd=ss
     * @param requestBodyStr
     * @return
     */
    public static Map<String,List> mapRequestBodyString(String requestBodyStr) {
        Map<String,List> stringListMap = new HashMap();
        String[] pvs = requestBodyStr.split("&");
        for (String pv : pvs){
            String[] kvs = pv.split("=");
            if (stringListMap.containsKey(kvs[0])){
                stringListMap.get(kvs[0]).add(kvs[1]);
            }else {
                stringListMap.put(kvs[0],new ArrayList<Object>(){{add(kvs[1]);}});
            }

        }
        return stringListMap;
    }

    public static String trimAllWhitespace(String str) {
        if (isEmpty(str)) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        int index = 0;
        while (sb.length() > index) {
            if (Character.isWhitespace(sb.charAt(index))) {
                sb.deleteCharAt(index);
            }
            else {
                index++;
            }
        }
        return sb.toString();
    }
}
