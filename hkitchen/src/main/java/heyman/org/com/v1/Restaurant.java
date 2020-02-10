package heyman.org.com.v1;

import heyman.org.com.v1.cook.AbstractCook;
import heyman.org.com.v1.cook.ConcreteCook;
import heyman.org.com.v1.cook.HCook;
import heyman.org.com.v1.cookmenu.MenuLsit;
import heyman.org.com.v1.kitchen.ConcreteKitchenA;
import heyman.org.com.v1.kitchen.HKitchen;

import java.util.ArrayList;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/2/10
 */
public class Restaurant {

    private HKitchen kitchen;

    private HCook cook;

    public Restaurant(){
        kitchen = new ConcreteKitchenA();
        cook = new ConcreteCook(kitchen);
    }

    void procssOrder(Order order){
        cook.startCookMeal(order);
    }

    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();
        Order order = new Order();
        OrderItem orderItem = new OrderItem();
        order.setItemList(new ArrayList<OrderItem>(){{add(orderItem);}});

        order.setId(100);
        order.setTableNo(2);
        orderItem.setMenuLsit(MenuLsit.XiHongJiDanMian);
        orderItem.setNumber(2);

        restaurant.procssOrder(order);
    }
}
