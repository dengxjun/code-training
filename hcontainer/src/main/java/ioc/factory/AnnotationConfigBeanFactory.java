package ioc.factory;

import ioc.annotation.Component;
import ioc.annotation.Controller;
import ioc.annotation.Inject;
import ioc.util.PathUtil;
import ioc.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;

/**
 * @author: 邓小军
 * @since: 2020/1/19 14:17
 *
 */
public class AnnotationConfigBeanFactory extends AbstractBeanFactory {
    private static Logger log = LoggerFactory.getLogger(AnnotationConfigBeanFactory.class);

    @Override
    public void loadBeanDefinitions(String pathName) {

        try {
            Set<Class> classes = new HashSet<>();
            scanPackages(pathName, classes);
            if (classes.size() == 0) return;

            doLoadBeanDefinition(classes);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Set<Class> scanPackages(String pathName, Set<Class> classes){

        try {
            Enumeration<URL> basePathURL = this.getClass().getClassLoader().getResources(pathName);
            while (basePathURL.hasMoreElements()) {
                URL url = basePathURL.nextElement();
                File file = new File(url.toURI());
                if (file.isDirectory()){
                    for (String fileName : file.list()){
                        log.debug("scan packages {}" , fileName);
                        scanPackages(pathName+"/"+fileName, classes);
                    }
                }else {

                    Class cls = this.getClass().getClassLoader().loadClass(PathUtil.convertToFullClasspath(pathName));
                    classes.add(cls);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return classes;
    }

    private void doLoadBeanDefinition(Set<Class> classes){
        for (Class cls : classes){

            if (cls.isInterface() || !containComponentAnnotation(cls)){
                continue;
            }

            BeanDefinition definition = new BeanDefinition();
            definition.setTargetClass(cls);
            classDefinitionMap.put(cls, definition);

            if (cls.getInterfaces() != null){
                definition.setInterfaces(Arrays.asList(cls.getInterfaces()));
                definition.getInterfaces().forEach(item ->{
                    classDefinitionMap.put(item, definition);
                });
            }

            praseComponentAnnotation(definition , cls);

            Field[] fields = cls.getDeclaredFields();
            if (fields.length > 0){
                for (Field field : fields){
                    Inject inject = field.getAnnotation(Inject.class);
                    if (inject != null){
                        definition.setInjectFields(field.getName(), field.getType());
                    }
                }
            }

            beanNameDefinitionMap.put(definition.getBeanName(),definition);
        }
    }

    private void praseComponentAnnotation(BeanDefinition definition, Class cls){

        String beanName = null;

        beanName = getAnnotationValue(cls);

        if (StrUtil.isEmpty(beanName)){
            beanName = StrUtil.toLowCaseFirstChar(cls.getSimpleName());
        }
        definition.setBeanName(beanName);
    }

    private boolean containComponentAnnotation(Class cls){
        Annotation[] annotations = cls.getAnnotations();
        for (Annotation ann : annotations){
            if (Component.class.isAssignableFrom(ann.annotationType())){
                return true;
            }else if (!isExcludeAnnotation(ann)){
                return containComponentAnnotation(ann.annotationType());
            }
        }
        return false;
    }

    private String getAnnotationValue(Class cls){
        Annotation[] annotations = cls.getAnnotations();
        for (Annotation ann : annotations){
            if (Component.class.isAssignableFrom(ann.annotationType())){
                return (String) invokeAnnotationMethod(ann, "value");
            }else if (!isExcludeAnnotation(ann) && containComponentAnnotation(ann.annotationType())){
                return (String) invokeAnnotationMethod(ann, "value");
            }
        }
        return null;
    }

    private Object invokeAnnotationMethod(Annotation ann, String methodName) {
        Object result = null;
        try {
            result = ann.annotationType().getMethod(methodName).invoke(ann);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private boolean isExcludeAnnotation(Annotation ann){
        if (ann.annotationType().getName().equals("java.lang.annotation.Documented")
                || ann.annotationType().getName().equals("java.lang.annotation.Retention")
                || ann.annotationType().getName().equals("java.lang.annotation.Target")){
            return true;
        }
        return false;
    }

}
