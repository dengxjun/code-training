package web.http;

import ioc.util.StrUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import web.http.constant.HttpConstant;
import web.mockdata.RequestData;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/1/28
 */
public class HttpServletRequestParserTest {

    private MockHttpServletRequest request;

    @Test
    public void getApplicationFormTypeData() throws Exception {
        Map<String,String> paramMap = new HashMap<String,String>(){{
            put("aa","bb");
            put("ww","cc");
        }};
        request = RequestData.getRequestForApplicationFormType(paramMap,"");
        InputStream ips = HttpServletRequestParser.getBody(request);
        String body = StrUtil.convertToStringFromInputStream(ips);
        Assert.assertEquals("aa=bb&ww=cc",body);
    }

    @Test
    public void getApplicationJsonData() throws Exception {
        String jsonStr = "{\n" +
                "\t\"name\":\"heyman\",\n" +
                "\t\"age\":30,\n" +
                "\t\"othername\":[\"bruce\",\"落花\"]\n" +
                "}";
        request = RequestData.getRequestForApplicationJsonType(jsonStr,"");
        InputStream ips = HttpServletRequestParser.getBody(request);
        String body = StrUtil.convertToStringFromInputStream(ips);
        System.out.println(body);
    }

    @Test
    public void getApplicationXmlData() throws Exception {
        String xmlStr = "<body>\n" +
            "\t<name>heyman</name>\n" +
            "\t<age>30</age>\n" +
            "\t<othername>\n" +
            "\t\t<value>bruce</value>\n" +
            "\t</othername>\n" +
            "</body>";

        request = RequestData.getRequestForApplicationXmlType(xmlStr,"");
        InputStream ips = HttpServletRequestParser.getBody(request);
        String body = StrUtil.convertToStringFromInputStream(ips);
        System.out.println(body);
    }

}