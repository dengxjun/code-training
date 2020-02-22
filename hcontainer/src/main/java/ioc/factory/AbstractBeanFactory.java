package ioc.factory;

import ioc.annotation.Controller;
import ioc.expand.ApplicationContextAware;
import ioc.expand.BeanPostProcessor;
import ioc.expand.InitializingBean;
import ioc.util.CollectionUtils;
import ioc.util.StrUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: 邓小军
 * @since: 2020/1/20 14:58
 *
 */
public abstract class AbstractBeanFactory implements BeanFactory{

    protected Map<String, BeanDefinition> beanNameDefinitionMap = new HashMap<>();

    protected Map<Class, BeanDefinition> classDefinitionMap = new HashMap<>();

    protected Map<Class, List<BeanDefinition>> typeAnnotationDefinitionMap = new HashMap<>();

    protected Map<String, Object> cachedBeans = new HashMap<>();

    abstract void loadBeanDefinitions(String scanPackages);

    @Override
    public Object getBean(String beanName){
        Object obj = cachedBeans.get(beanName);
        if (obj != null){
            return obj;
        }


        BeanDefinition beanDefinition = beanNameDefinitionMap.get(beanName);
        System.out.println(beanDefinition);
        Object targetBean = getObject(beanDefinition);
        return targetBean;
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

    public List<BeanDefinition> getBeanDefinitions(){
        return beanNameDefinitionMap.values().stream().collect(Collectors.toList());
    }

    public List<BeanDefinition> getBeanDefinitions(Class cls){
        return (List<BeanDefinition>) classDefinitionMap.get(cls);
    }

    public BeanDefinition getBeanDefinition(String beanName){
        return beanNameDefinitionMap.get(beanName);
    }

    /**
     * 获取继承或者实现某个类的bean定义
     * @param beanFactoryPostProcessorClass
     * @return
     */
    public List<BeanDefinition> getBeanDefinitionAssignableFrom(Class<?> beanFactoryPostProcessorClass) {
        List<BeanDefinition> resultList = new ArrayList<>();
        List<BeanDefinition> beanDefinitions = this.getBeanDefinitions();
        for (BeanDefinition beanDefinition : beanDefinitions){
            if (beanFactoryPostProcessorClass.isAssignableFrom(beanDefinition.getTargetClass())){
                resultList.add(beanDefinition);
            }
        }
        return resultList;
    }

    private Object initializationBeanInstance(Object targetBean, String beanName){

        applyBeanPostProcessorBeforeInitialization(targetBean, beanName);
        processInitializingBean(targetBean);
        return targetBean = applyBeanPostProcessorAfterInitialization(targetBean, beanName);
    }



    protected void processInitializingBean(Object targetBean){
        if (targetBean instanceof InitializingBean){
            ((InitializingBean) targetBean).afterPropertiesSet();
        }
    }

    protected void applyBeanPostProcessorBeforeInitialization(Object targetBean, String beanName) {
        List<BeanDefinition> beanDefinitions = getBeanDefinitionAssignableFrom(BeanPostProcessor.class);
        if (CollectionUtils.isEmpty(beanDefinitions)){
            return;
        }
        for (BeanDefinition beanDefinition : beanDefinitions){
            BeanPostProcessor beanPostProcessor = (BeanPostProcessor) getBean(beanDefinition.getBeanName());
            beanPostProcessor.postProcessorBeforeInitializtion(targetBean, beanName);
        }
    }

    private Object applyBeanPostProcessorAfterInitialization(Object targetBean, String beanName) {
        List<BeanDefinition> beanDefinitions = getBeanDefinitionAssignableFrom(BeanPostProcessor.class);
        if (CollectionUtils.isEmpty(beanDefinitions)){
            return targetBean;
        }
        for (BeanDefinition beanDefinition : beanDefinitions){
            BeanPostProcessor beanPostProcessor = (BeanPostProcessor) getBean(beanDefinition.getBeanName());
            targetBean = beanPostProcessor.postProcessorAfterInitializtion(targetBean, beanName);
        }
        return targetBean;
    }

    private Object getObject(BeanDefinition beanDefinition){

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
        obj = initializationBeanInstance(obj, beanDefinition.getBeanName());
        return obj;
    }
}
