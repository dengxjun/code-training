package ioc.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: 邓小军
 * @since: 2020/1/19 18:13
 *
 */
public class BeanDefinition {

    private String beanName;

    private Class targetClass;

    private List<Class> interfaces = new ArrayList<>();

    private Map<String, Class> injectFields = new HashMap<>();

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public List<Class> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(List<Class> interfaces) {
        this.interfaces = interfaces;
    }

    public Map<String, Class> getInjectFields() {
        return injectFields;
    }

    public void setInjectFields(String fieldName, Class cls) {
        this.injectFields.put(fieldName, cls);
    }

    public Class getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class targetClass) {
        this.targetClass = targetClass;
    }

    @Override
    public String toString() {
        return "BeanDefinition{" +
                "beanName='" + beanName + '\'' +
                ", targetClass=" + targetClass +
                ", interfaces=" + interfaces +
                ", injectFields=" + injectFields +
                '}';
    }
}
