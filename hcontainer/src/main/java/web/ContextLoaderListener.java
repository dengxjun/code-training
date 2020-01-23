package web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author: 邓小军
 * @since: 2020/1/20 15:22
 *
 */
public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext slc = servletContextEvent.getServletContext();
        String packageScan = slc.getInitParameter(WebApplicationContext.PACKAGE_SCAN);

        refeshContext(packageScan, slc);
    }

    private void refeshContext(String packageScan, ServletContext slc) {
        WebApplicationContext applicationContext = new WebApplicationContext(packageScan);
        applicationContext.refresh();
        slc.setAttribute(WebApplicationContext.WebApplicationContext, applicationContext);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
