package heyman.org.com.v1.kitchen;

import heyman.org.com.v1.ingredients.HIngredients;
import heyman.org.com.v1.kitchen.cooker.HContainer;
import heyman.org.com.v1.kitchen.cooker.HCookingBench;
import heyman.org.com.v1.kitchen.cooker.HKnife;

import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/2/10
 */
public abstract class AbstraceKitchen implements HKitchen{
    /**
     * 锅碗瓢盆
     */
    private List<HContainer> containers;
    /**
     * 灶台
     */
    private List<HCookingBench> cookingBenches;
    /**
     * 刀具
     */
    private List<HKnife> knifes;
    /**
     * 食材（包括调料）
     */
    private List<HIngredients> ingredientses;

    public List<HContainer> getContainers() {
        return containers;
    }

    public void setContainers(List<HContainer> containers) {
        this.containers = containers;
    }

    public List<HCookingBench> getCookingBenches() {
        return cookingBenches;
    }

    public void setCookingBenches(List<HCookingBench> cookingBenches) {
        this.cookingBenches = cookingBenches;
    }

    public List<HIngredients> getIngredientses() {
        return ingredientses;
    }

    public void setIngredientses(List<HIngredients> ingredientses) {
        this.ingredientses = ingredientses;
    }

    public List<HKnife> getKnifes() {
        return knifes;
    }

    public void setKnifes(List<HKnife> knifes) {
        this.knifes = knifes;
    }
}
