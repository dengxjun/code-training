package ioc.factory;

/**
 * @author: 邓小军
 * @since: 2020/1/19 14:05
 *
 */

/**
 * The root interface for accessing a Spring bean container.
 * This is the basic client view of a bean container;
 * further interfaces such as {@link AbstractBeanFactory} and
 * {@link BeanFactory}
 * are available for specific purposes.
 *
 * <p>Normally a BeanFactory will load bean definitions stored in a configuration
 * source (such as an XML document), and use the {@code org.springframework.beans}
 * package to configure the beans. However, an implementation could simply return
 * Java objects it creates as necessary directly in Java code. There are no
 * constraints on how the definitions could be stored: LDAP, RDBMS, XML,
 * properties file, etc. Implementations are encouraged to support references
 * amongst beans (Dependency Injection).
 *
 * <p>In contrast to the methods in {@link BeanFactory}, all of the
 * operations in this interface will also check parent factories if this is a
 * {@link BeanFactory}. If a bean is not found in this factory instance,
 * the immediate parent factory will be asked. Beans in this factory instance
 * are supposed to override beans of the same name in any parent factory.
 *
 * <p>Bean factory implementations should support the standard bean lifecycle interfaces
 * as far as possible. The full set of initialization methods and their standard order is:<br>
 * 1. BeanNameAware's {@code setBeanName}<br>
 * 2. BeanClassLoaderAware's {@code setBeanClassLoader}<br>
 * 3. BeanFactoryAware's {@code setBeanFactory}<br>
 * 4. EnvironmentAware's {@code setEnvironment}
 * 5. EmbeddedValueResolverAware's {@code setEmbeddedValueResolver}
 * 6. ResourceLoaderAware's {@code setResourceLoader}
 * (only applicable when running in an application context)<br>
 * 7. ApplicationEventPublisherAware's {@code setApplicationEventPublisher}
 * (only applicable when running in an application context)<br>
 * 8. MessageSourceAware's {@code setMessageSource}
 * (only applicable when running in an application context)<br>
 * 9. ApplicationContextAware's {@code setApplicationContext}
 * (only applicable when running in an application context)<br>
 * 10. ServletContextAware's {@code setServletContext}
 * (only applicable when running in a web application context)<br>
 * 11. {@code postProcessBeforeInitialization} methods of BeanPostProcessors<br>
 * 12. InitializingBean's {@code afterPropertiesSet}<br>
 * 13. a custom init-method definition<br>
 * 14. {@code postProcessAfterInitialization} methods of BeanPostProcessors
 *
 * <p>On shutdown of a bean factory, the following lifecycle methods apply:<br>
 * 1. {@code postProcessBeforeDestruction} methods of DestructionAwareBeanPostProcessors
 * 2. DisposableBean's {@code destroy}<br>
 * 3. a custom destroy-method definition
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @author Chris Beams
 * @since 13 April 2001
 * @date 2020/1/19 14:11
 * @see ioc.context.ApplicationContext#getBeanFactory
 */
public interface BeanFactory {
    /**
     * 返回指定bean的实例，这个实例也许是共享的，或许是单独的
     * Return an instance, which may be shared or independent, of the specified bean.
     * <p>这个方法可以通过占位符获取单例或者原型对象
     * <p>传递的beanName也可以是一个别名
     *  通过beanName获取bean实例
     *  * <p><pre>{@code
     *  演示如何使用该方法
     *  List<Goods> items = new ArrayList<>();
     *  Goods goods = new Goods(1L, BigDecimal.ONE);
     *  Goods goods2 = new Goods(2L, BigDecimal.TEN);
     *  items.add(goods);
     *  items.add(goods2);
     *
     *  Order order1 = new Order();
     *  order.setUserId("1");
     *  order.setItems(items);
     *  OrderService#createOrder(order);
     * }
     * </pre>
     * @param beanName 可以是bean的名称，也可以是别名
     * @date 2020/1/19 14:11
     * @return an instance of the bean
     * @throws RuntimeException if there is no bean definition
     */
    Object getBean(String beanName);

    /**
     * <p>通过类型获取bean实例</p>
     * @param cls
     * @return
     */
    Object getBean(Class cls);
}
