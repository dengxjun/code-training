package trains.output;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 邓小军
 * @since: 2019/12/30 16:36
 *
 */
public class FootPrint {
    private int result;
    private List<String> prints = new ArrayList<>();

    public FootPrint() {
    }

    public FootPrint(int result) {
        this.result = result;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void resultAdd() {
        this.result++;
    }

    public List<String> getPrints() {
        return prints;
    }

    public void addPrints(String print) {
        this.prints.add(print);
    }
}
