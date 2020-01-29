package web.mockdata;

import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/1/29
 */
public class User {
    private String name;
    private Integer age;
    private List<String> otherNames;

    public User() {
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getOtherNames() {
        return otherNames;
    }

    public void setOtherNames(List<String> otherNames) {
        this.otherNames = otherNames;
    }
}
