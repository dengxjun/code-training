package web.method.resolver.support;

import ioc.annotation.RequestBody;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import web.http.MockHttpServletRequest;
import web.method.HandlerMethod;
import web.method.MethodParameter;
import web.mockdata.RequestData;
import web.mockdata.User;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import static org.junit.Assert.*;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/1/30
 */
public class RequestBodyMethodParameterResolverTest {

    private RequestBodyMethodParameterResolver parameterResolver;

    private Method method;

    private Parameter parameter;

    @Before
    public void setup(){
        parameterResolver = new RequestBodyMethodParameterResolver();
        try {
            method = RequestBodyMethodParameterResolverTest.class.getMethod("mockMethod",User.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        parameter = method.getParameters()[0];
    }

    @Test
    public void getMethodParametersValue() throws Exception {
        String jsonStr = "{\n" +
                "\t\"name\":\"heyman\",\n" +
                "\t\"age\":30\n" +
                "}";

        String xmlStr = "<User>\n" +
                "\t<name>heyman</name>\n" +
                "\t<age>30</age>\n" +
                "</User>";

        MockHttpServletRequest request = RequestData.getRequestForApplicationJsonType(jsonStr,"");

        HandlerMethod handlerMethod = new HandlerMethod();
        MethodParameter methodParameter = new MethodParameter(User.class, "user",parameter);
        handlerMethod.addMethodParam(methodParameter);

        User user = (User) parameterResolver.getMethodParametersValue(request,methodParameter);
        Assert.assertEquals("heyman",user.getName());
        Assert.assertEquals(30,user.getAge().intValue());

        MockHttpServletRequest requestForXML = RequestData.getRequestForApplicationXmlType(xmlStr,"");

        User user2 = (User) parameterResolver.getMethodParametersValue(requestForXML,methodParameter);
        Assert.assertEquals("heyman",user.getName());
        Assert.assertEquals(30,user2.getAge().intValue());
    }

    @Test
    public void supportParameterType() throws Exception {
        MethodParameter methodParameter = new MethodParameter(User.class, "user",parameter);
        Assert.assertTrue(parameterResolver.supportParameterType(methodParameter));
    }

    public void mockMethod(@RequestBody User user){

    }

}