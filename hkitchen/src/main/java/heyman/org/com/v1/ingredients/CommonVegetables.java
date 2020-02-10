package heyman.org.com.v1.ingredients;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/2/10
 */
public class CommonVegetables implements HVegetables{
    private String name;

    private String unit;

    private Integer number;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "CommonVegetables{" +
                "name='" + name + '\'' +
                ", unit='" + unit + '\'' +
                ", number=" + number +
                '}';
    }
}
