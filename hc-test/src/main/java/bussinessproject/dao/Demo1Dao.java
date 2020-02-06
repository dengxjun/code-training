package bussinessproject.dao;

import ioc.annotation.Repository;

/**
 * @author: 邓小军
 * @since: 2020/1/19 17:59
 *
 */
@Repository
public class Demo1Dao{
    public void save (){
        System.out.println("Demo1Dao saveing....");
    }
}
