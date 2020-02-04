package web.mockdata;

import web.http.MockHttpServletRequest;
import web.http.constant.HttpConstant;

import java.util.Map;

/**
 * <p>Description: 可以复用的httpRequest数据</p>
 *
 * @author heyman
 * @date 2020/1/29
 */
public class RequestData {

    public static MockHttpServletRequest getRequestForApplicationFormType(Map<String, String> paramMap, String uri) throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod(HttpConstant.METHOD_POST);
        request.setContentType(HttpConstant.FORM_CONTENT_TYPE);
        request.setRequestURI(uri);

        for (Map.Entry<String,String> entry : paramMap.entrySet()){
             request.addParameter(entry.getKey(),entry.getValue());
        }
        return request;
    }

    public static MockHttpServletRequest getRequestForApplicationJsonType(String jsonStr, String uri) throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod(HttpConstant.METHOD_POST);
        request.setContentType(HttpConstant.APPLICATION_JSON_TYPE);
        request.setRequestURI(uri);
        request.setContent(jsonStr.getBytes());
        return request;
    }

    public static MockHttpServletRequest getRequestForApplicationXmlType(String xmlStr, String uri) throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod(HttpConstant.METHOD_POST);
        request.setContentType(HttpConstant.APPLICATION_XML_TYPE);
        request.setRequestURI(uri);
        request.setContent(xmlStr.getBytes());
        return request;
    }
}
