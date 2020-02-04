package web.http;

import web.http.constant.HttpConstant;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Map;
import java.util.Set;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/1/26
 */
public class HttpServletRequestParser {


    

    public static InputStream getBody(HttpServletRequest req) throws IOException{
        if (isFormMehtod(req)){
            return getBodyFormatedStringToInputStream(req);
        }else {
            return  req.getInputStream();
        }
    }

    private static InputStream getBodyFormatedStringToInputStream(HttpServletRequest req) throws IOException {
        StringBuilder sb = new StringBuilder();

        Map<String, String[]> parameterMap = req.getParameterMap();
        Set<Map.Entry<String, String[]>> entries = parameterMap.entrySet();
        for (Map.Entry<String, String[]> entry: entries){
            String key = entry.getKey();
            String[] values = entry.getValue();
            for (String v : values){
                if (sb.length() > 0){
                    sb.append("&");
                }
                sb.append(key);
                sb.append("=");
                sb.append(v);
            }
        }
        return  new ByteArrayInputStream(sb.toString().getBytes());
    }

    private static boolean isFormMehtod(HttpServletRequest req){
        String method = req.getMethod();

        String contentType = req.getContentType();

        if (contentType.contains(HttpConstant.FORM_CONTENT_TYPE) && method.equalsIgnoreCase(HttpConstant.METHOD_POST)){
            return true;
        }else {
            return false;
        }
    }
}
