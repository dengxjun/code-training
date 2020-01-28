package ioc.util;

import java.util.Collection;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/1/28
 */
public class CollectionUtils {

    public static boolean isEmpty(Collection collection){
        return collection == null || collection.size() == 0;
    }

    public static boolean isNotEmpty(Collection collection){
        return collection != null && collection.size() > 0;
    }
}
