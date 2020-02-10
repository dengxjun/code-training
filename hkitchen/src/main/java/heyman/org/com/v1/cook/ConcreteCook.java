package heyman.org.com.v1.cook;

import heyman.org.com.v1.Order;
import heyman.org.com.v1.cookmenu.HCookBook;
import heyman.org.com.v1.cookmenu.MenuLsit;
import heyman.org.com.v1.kitchen.HKitchen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/2/10
 */
public class ConcreteCook extends AbstractCook{
    private static final Logger LOGGER = LoggerFactory.getLogger(ConcreteCook.class);

    public ConcreteCook(HKitchen kitchen){
        super(kitchen);
    }

    @Override
    protected void checkHIngredients(HCookBook cookMenu) {
        LOGGER.debug("do check ingredients===");
    }

    @Override
    protected void doCook(CookContext cookContext) {
        LOGGER.debug("start do cook===");
    }


    @Override
    protected void prepareHIngredients(CookContext cookContext, HCookBook cookMenu) {
        LOGGER.debug("start do prepareHIngredients===");
    }

    @Override
    public void startCookMeal(Order order) throws Exception {
        super.startCookMeal(order);
    }
}
