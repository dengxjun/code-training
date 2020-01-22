package ioc.factory;

import ioc.utils.StrUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 邓小军
 * @Date: 2020/1/20 14:58
 * @Description:
 */
public abstract class AbstractBeanFactory implements BeanFactory{

    protected Map<String, BeanDefinition> beanNameDefinitionMap = new HashMap<>();

    protected Map<Class, BeanDefinition> classDefinitionMap = new HashMap<>();

    protected Map<Class, List<BeanDefinition>> typeAnnotationDefinitionMap = new HashMap<>();

    protected Map<String, Object> cachedBeans = new HashMap<>();

    @Override
    public Object getBean(String beanName){
        Object obj = cachedBeans.get(beanName);
        if (obj != null){
            return obj;
        }

        BeanDefinition beanDefinition = beanNameDefinitionMap.get(beanName);
        System.out.println(beanDefinition);
        return getObject(beanDefinition);
    }

    @Override
    public Object getBean(Class cls){

        BeanDefinition beanDefinition = classDefinitionMap.get(cls);

        Object obj = cachedBeans.get(beanDefinition.getBeanName());
        if (obj != null){
            return obj;
        }

        return getObject(beanDefinition);
    }

    public Object getObject(BeanDefinition beanDefinition){

        Object obj = null;
        try {
            obj = beanDefinition.getTargetClass().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        for (String fieldName : beanDefinition.getInjectFields().keySet()){
            Class injectClass = beanDefinition.getInjectFields().get(fieldName);
            Method method = null;
            try {
                method = beanDefinition.getTargetClass().getDeclaredMethod(StrUtil.getSetterMethodName(fieldName),injectClass);
                method.invoke(obj,getBean(injectClass));
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }


        cachedBeans.put(beanDefinition.getBeanName(), obj);
        return obj;
    }
}
