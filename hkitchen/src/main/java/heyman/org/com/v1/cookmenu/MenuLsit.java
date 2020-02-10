package heyman.org.com.v1.cookmenu;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/2/10
 */
public enum MenuLsit {
    XiHongShiChaoDan("西红柿炒蛋"),
    XiHongJiDanMian("西红柿鸡蛋面"),
    YouBOLaZiMian("油泼辣子面");

    private String value;

    MenuLsit(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
