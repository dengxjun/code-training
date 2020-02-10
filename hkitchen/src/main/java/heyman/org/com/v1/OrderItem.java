package heyman.org.com.v1;

import heyman.org.com.v1.cookmenu.MenuLsit;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/2/10
 */
public class OrderItem {

    private MenuLsit menuLsit;

    private Integer number;

    public MenuLsit getMenuLsit() {
        return menuLsit;
    }

    public void setMenuLsit(MenuLsit menuLsit) {
        this.menuLsit = menuLsit;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "menuLsit=" + menuLsit +
                ", number=" + number +
                '}';
    }
}
