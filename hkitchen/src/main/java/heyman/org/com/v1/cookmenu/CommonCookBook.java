package heyman.org.com.v1.cookmenu;

import heyman.org.com.v1.ingredients.CommonCondiment;
import heyman.org.com.v1.ingredients.CommonVegetables;
import heyman.org.com.v1.ingredients.HIngredients;
import heyman.org.com.v1.procedure.HCookProcedure;

import java.util.Arrays;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/2/10
 */
public class CommonCookBook implements HCookBook {

    private String cookMenuName;

    private CommonVegetables[] commonVegetables;

    private CommonCondiment[] commonCondiments;

    private String[] cookProcedure;

    @Override
    public HIngredients[] getIngredients() {
        return commonVegetables;
    }

    @Override
    public HCookProcedure getHCookProcedure() {
        return null;
    }

    @Override
    public String getCookMenuName() {
        return cookMenuName;
    }

    @Override
    public HIngredients[] getCondiment() {
        return commonCondiments;
    }


    public void setCookMenuName(String cookMenuName) {
        this.cookMenuName = cookMenuName;
    }

    public void setCookProcedure(String[] cookProcedure) {
        this.cookProcedure = cookProcedure;
    }

    public void setCommonCondiments(CommonCondiment[] commonCondiments) {
        this.commonCondiments = commonCondiments;
    }

    public void setCommonVegetables(CommonVegetables[] commonVegetables) {
        this.commonVegetables = commonVegetables;
    }

    @Override
    public String toString() {
        return "CommonCookBook{" +
                "commonCondiments=" + Arrays.toString(commonCondiments) +
                ", cookMenuName='" + cookMenuName + '\'' +
                ", commonVegetables=" + Arrays.toString(commonVegetables) +
                ", cookProcedure=" + Arrays.toString(cookProcedure) +
                '}';
    }
}
