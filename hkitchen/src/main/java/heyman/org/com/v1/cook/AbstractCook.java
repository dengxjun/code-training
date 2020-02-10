package heyman.org.com.v1.cook;

import heyman.org.com.v1.Order;
import heyman.org.com.v1.cookmenu.CookBookLoader;
import heyman.org.com.v1.cookmenu.HCookBook;
import heyman.org.com.v1.cookmenu.MenuLsit;
import heyman.org.com.v1.kitchen.HKitchen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/2/10
 */
public abstract class AbstractCook implements HCook {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractCook.class);
    
    private CookContext cookContext;

    private HKitchen kitchen;

    public AbstractCook(){
        cookContext = new CookContext();
    }

    public AbstractCook(HKitchen kitchen){
        this();
        this.kitchen = kitchen;
    }

    @Override
    public void startCookMeal(Order order) throws Exception {
        LOGGER.debug("startCookMeal= Order info {}", order);
        cookContext.setKitchen(kitchen);

        //获取菜谱
        HCookBook cookMenu = getCookMenu(order.getItemList().get(0).getMenuLsit());
        cookContext.setCookMenu(cookMenu);
        //检查食材
        checkHIngredients(cookMenu);
        //准备食材
         prepareHIngredients(cookContext, cookMenu);
        //开始做饭
        doCook(cookContext);
        LOGGER.debug("end startCookMeal context:{}",cookContext);
    }

    protected abstract void prepareHIngredients(CookContext cookContext, HCookBook cookMenu);


    protected HCookBook getCookMenu(MenuLsit menuLsit) throws IOException {
        return CookBookLoader.getInstance().getCookBook(menuLsit);
    }

    protected abstract void checkHIngredients(HCookBook cookMenu);

    protected abstract void doCook(CookContext cookContext);

    public void setKitchen(HKitchen kitchen) {
        this.kitchen = kitchen;
    }
}
