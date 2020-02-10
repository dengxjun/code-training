package heyman.org.com.v1;

import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/2/10
 */
public class Order {
    private Integer id;

    private Integer tableNo;

    private List<OrderItem> itemList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<OrderItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<OrderItem> itemList) {
        this.itemList = itemList;
    }

    public Integer getTableNo() {
        return tableNo;
    }

    public void setTableNo(Integer tableNo) {
        this.tableNo = tableNo;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", tableNo=" + tableNo +
                ", itemList=" + itemList +
                '}';
    }
}
