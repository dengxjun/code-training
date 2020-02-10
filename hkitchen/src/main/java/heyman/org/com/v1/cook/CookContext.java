package heyman.org.com.v1.cook;

import heyman.org.com.v1.cookmenu.HCookBook;
import heyman.org.com.v1.kitchen.HKitchen;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/2/10
 */
public class CookContext {

    private HCookBook cookMenu;

    private HKitchen kitchen;

    void setCookMenu(HCookBook cookMenu){
        this.cookMenu = cookMenu;
    }

    public HKitchen getKitchen() {
        return kitchen;
    }

    public void setKitchen(HKitchen kitchen) {
        this.kitchen = kitchen;
    }

    public HCookBook getCookMenu() {
        return cookMenu;
    }
}
