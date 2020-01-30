package web.method;

import ioc.annotation.RequestBody;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import web.http.MockHttpServletRequest;
import web.method.resolver.support.RequestBodyMethodParameterResolverTest;
import web.mockdata.RequestData;
import web.mockdata.User;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/1/29
 */
public class ServletInvocableHandlerMethodTest {

    private ServletInvocableHandlerMethod invocableHandlerMethod;
    private HandlerMethod handlerMethod;
    private MockHttpServletRequest request;
    private Method method;
    private Parameter parameter;

    @Before
    public void setup(){
        handlerMethod = new HandlerMethod();
        invocableHandlerMethod = new ServletInvocableHandlerMethod(handlerMethod);
        try {
            method = RequestBodyMethodParameterResolverTest.class.getMethod("mockMethod",User.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        parameter = method.getParameters()[0];
    }

    @Test
    public void invoke() throws Exception {

    }

    @Test
    public void resolveMethodParametersValueForGeniricType() throws Exception {
        Map<String,String> paramMap = new HashMap<String,String>(){{
            put("name","heyman");
            put("age","30");
            put("height","167.8");
        }};
        request = RequestData.getRequestForApplicationFormType(paramMap,"");

        MethodParameter methodParameter1 = new MethodParameter(String.class, "name");
        handlerMethod.addMethodParam(methodParameter1);
        MethodParameter methodParameter2 = new MethodParameter(Integer.class, "age");
        handlerMethod.addMethodParam(methodParameter2);
        Object[] args = invocableHandlerMethod.resolveMethodParametersValue(request, handlerMethod);
        Assert.assertTrue((args[0] instanceof String) && "heyman".equals(args[0]));
        Assert.assertTrue(args[1] instanceof Integer  && args[1].equals(30));
    }

    @Test
    public void resolveMethodParametersValueForEntity() throws Exception {
        Map<String,String> paramMap = new HashMap<String,String>(){{
            put("name","heyman");
            put("age","30");
            put("height","167.8");
        }};
        request = RequestData.getRequestForApplicationFormType(paramMap,"");

        MethodParameter methodParameter1 = new MethodParameter(User.class, "user");
        handlerMethod.addMethodParam(methodParameter1);

        Object[] args = invocableHandlerMethod.resolveMethodParametersValue(request, handlerMethod);
        Assert.assertTrue((args[0] instanceof User) && "heyman".equals(((User)args[0]).getName()));
    }

    @Test
    public void resolveMethodParametersValueForRequestBodyApplicationType() throws Exception {
        String jsonStr = "{\n" +
                "\t\"name\":\"heyman\",\n" +
                "\t\"age\":30\n" +
                "}";

        String xmlStr = "<User>\n" +
                "\t<name>heyman</name>\n" +
                "\t<age>30</age>\n" +
                "</User>";

        MockHttpServletRequest requestJson = RequestData.getRequestForApplicationJsonType(jsonStr,"");

        MethodParameter methodParameter1 = new MethodParameter(User.class, "user",parameter);
        handlerMethod.addMethodParam(methodParameter1);

        Object[] args = invocableHandlerMethod.resolveMethodParametersValue(requestJson, handlerMethod);
        Assert.assertTrue((args[0] instanceof User) && "heyman".equals(((User)args[0]).getName()));
    }

    public void mockMethod(@RequestBody User user){

    }

}