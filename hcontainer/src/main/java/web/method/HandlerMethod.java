package web.method;

import ioc.annotation.RequestMethod;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: 邓小军
 * @since: 2020/1/20 16:10
 *
 */
public class HandlerMethod {

    private Class targetClass;

    private String targetMethodName;

    private List<MethodParameter> params = new ArrayList<>();

    private RequestMethod requestMethod;

    public Class getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class targetClass) {
        this.targetClass = targetClass;
    }

    public String getTargetMethodName() {
        return targetMethodName;
    }

    public void setTargetMethodName(String targetMethodName) {
        this.targetMethodName = targetMethodName;
    }


    public void setParams(Method method) {
        try{
            Class[] parameterTypes = method.getParameterTypes();

            if (parameterTypes.length == 0) return;

            String classname = method.getDeclaringClass().getName();
            String methodname = method.getName();
            ClassPool pool = ClassPool.getDefault();
            pool.insertClassPath(HandlerMethod.class.getClassLoader().getResource("").getPath());

            CtClass cc = pool.get(classname);
            CtMethod cm = cc.getDeclaredMethod(methodname);
            MethodInfo methodInfo = cm.getMethodInfo();
            CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
            LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
            if (attr != null)  {
                int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
                for (int i = 0; i < parameterTypes.length; i++){
                    params.add(new MethodParameter(parameterTypes[i], attr.variableName(i + pos)));
                }
            }
        }catch(Exception e){
            System.out.println("getMethodVariableName fail "+e);
        }

    }

    public List<Class<?>> getMethodParams() {
        List<Class<?>> typeParams = new ArrayList<>(params.size());
        for (MethodParameter pm : params){
            typeParams.add(pm.getType());
        }

        return typeParams;
    }

    public List<String> getMethodParamsField() {
        List<String> typeParams = new ArrayList<>(params.size());
        for (MethodParameter pm : params){
            typeParams.add(pm.getFieldName());
        }

        return typeParams;
    }

    public List<MethodParameter> getParams() {
        return params;
    }

    public RequestMethod getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(RequestMethod requestMethod) {
        this.requestMethod = requestMethod;
    }

}
