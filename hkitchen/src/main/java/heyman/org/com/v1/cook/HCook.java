package heyman.org.com.v1.cook;

import heyman.org.com.v1.Order;

import java.io.IOException;

/**
 * <p>厨师类: </p>
 *
 * @author heyman
 * @date 2020/2/10
 */
public interface HCook {
    void startCookMeal(Order order) throws Exception;
}
