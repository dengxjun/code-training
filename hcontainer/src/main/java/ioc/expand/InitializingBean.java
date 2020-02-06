package ioc.expand;

/**
 * <p> 实现该接口的bean class 在bean初始化完成后会调用主动调用afterPropertiesSet方法，完成对应业务逻辑的处理 </p>
 *
 * @author heyman
 * @date 2020/2/4
 */
public interface InitializingBean {
    /**
     * 调用发生在 init方法调用前
     */
    void afterPropertiesSet();
}
