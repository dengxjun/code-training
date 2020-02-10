package heyman.org.com.v1.cookmenu;

import heyman.org.com.v1.ingredients.HIngredients;
import heyman.org.com.v1.procedure.HCookProcedure;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/2/10
 */
public interface HCookBook {

    /**
     * 获取菜单名称
     * @return
     */
    String getCookMenuName();

    /**
     * 获取食材元素
     * @return
     */
    HIngredients[] getIngredients();

    /**
     * 获取配料元素
     * @return
     */
    HIngredients[] getCondiment();

    /**
     * 获取烹饪步骤
     * @return
     */
    HCookProcedure getHCookProcedure();
}
