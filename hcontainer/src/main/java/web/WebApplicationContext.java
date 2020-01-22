package web;

import ioc.context.AnnotationApplicationContext;

/**
 * @Auther: 邓小军
 * @Date: 2020/1/20 15:15
 * @Description:
 */
public class WebApplicationContext extends AnnotationApplicationContext {

    public final static String PACKAGE_SCAN = "packageScan";

    public final static String WebApplicationContext = "webApplicationContext";



    public WebApplicationContext(String packageScan) {
        super(packageScan);
    }
}
