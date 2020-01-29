package ioc.util;

import java.util.Collection;
import java.util.List;

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

    public static void mergeArrayIntoCollection(Object values, Collection<Object> collection) {
        Object[] arr;
        if (values instanceof Object[]){
            arr = (Object[])values;
        }else {
            arr = new Object[0];
        }

        for (Object obj : arr){
            collection.add(obj);
        }
    }
}
