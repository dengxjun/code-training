package bussinessproject.test.support;

import ioc.annotation.Component;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/2/5
 */
@Component("heymanAspect")
public class HeymanAspect {

    public void pointCut(){

    }

    public void before(){
        System.out.println("-----------HeymanAspect before method be called------------");
    }

    public void after(){
        System.out.println("-----------HeymanAspect after method be called------------");
    }

    public static boolean needIntercept(Object bean){
        if (bean.getClass().getTypeName().equals("bussinessproject.dao.Demo1Dao")){
            return true;
        }
        return false;
    }

}
