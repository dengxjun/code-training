package web.method.resolver.support;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import web.http.MockHttpServletRequest;
import web.method.MethodParameter;
import web.mockdata.RequestData;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.*;

import static org.junit.Assert.*;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/1/28
 */
public class RequestParamMethodParametersResolverTest {

    private RequestParamMethodParametersResolver resolver;

    private MockHttpServletRequest request;

    @Before
    public void setup(){
        resolver = new RequestParamMethodParametersResolver();
        request = new MockHttpServletRequest("GET","/admin/test");
    }

    @Test
    public void getMethodParametersValue() throws Exception {
        Map<String,String> paramMap = new HashMap<String,String>(){{
            put("name","heman");
            put("age","30");
            put("height","167.8");
        }};
        request = RequestData.getRequestForApplicationFormType(paramMap,"");
        MethodParameter methodParameter = new MethodParameter(Integer.class, "age");
        Object arg = resolver.getMethodParametersValue(request, methodParameter);
        Assert.assertTrue(arg instanceof Integer);

        MethodParameter methodParameterHeight = new MethodParameter(Double.class, "age");
        Object arg2 = resolver.getMethodParametersValue(request, methodParameterHeight);

        Assert.assertTrue(arg2 instanceof Double);
    }

    @Test
    public void supportParameterType() throws Exception {
        MethodParameter methodParameter = new MethodParameter(Integer.class, "age");
        boolean isSupport = resolver.supportParameterType(methodParameter);
        Assert.assertEquals(true,isSupport);
    }

}