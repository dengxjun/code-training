package ioc.factory;

import ioc.annotation.Component;
import ioc.annotation.Controller;
import ioc.annotation.Inject;
import ioc.utils.PathUtil;
import ioc.utils.StrUtil;
import org.apache.log4j.Logger;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;

/**
 * @Auther: 邓小军
 * @Date: 2020/1/19 14:17
 * @Description:
 */
public class AnnotationConfigBeanFactory extends AbstractBeanFactory {
    private static Logger log = Logger.getLogger(AnnotationConfigBeanFactory.class);

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
                        log.debug("scan packages {}" + fileName);
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

            if (cls.isInterface()) continue;

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
            praseControllerAnnotation(definition , cls);

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

    public void praseControllerAnnotation(BeanDefinition definition, Class cls){

        String beanName = null;

        Controller clsDeclaredAnnotation = (Controller)cls.getDeclaredAnnotation(Controller.class);

        if (clsDeclaredAnnotation == null){
            return;
        }

        beanName = clsDeclaredAnnotation.value();

        if (StrUtil.isEmpty(beanName)){
            beanName = StrUtil.toLowCaseFirstChar(cls.getSimpleName());
        }
        definition.setBeanName(beanName);

        List lst = typeAnnotationDefinitionMap.get(Controller.class);
        if (lst == null){
            lst = new ArrayList();
        }
        lst.add(definition);

        typeAnnotationDefinitionMap.put(Controller.class, lst);
    }

    private void praseComponentAnnotation(BeanDefinition definition, Class cls){

        String beanName = null;

        Component clsDeclaredAnnotation = (Component)cls.getDeclaredAnnotation(Component.class);

        if (clsDeclaredAnnotation == null){
            return;
        }

        beanName = clsDeclaredAnnotation.value();

        if (StrUtil.isEmpty(beanName)){
            beanName = StrUtil.toLowCaseFirstChar(cls.getSimpleName());
        }
        definition.setBeanName(beanName);
    }


    public List<BeanDefinition> getBeanDefinitions(Class<Controller> controllerClass) {
        return typeAnnotationDefinitionMap.get(controllerClass);
    }
}
