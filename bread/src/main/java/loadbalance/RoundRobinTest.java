package loadbalance;

/**
 * Created by Administrator on 2019/11/30.
 */
public class RoundRobinTest {
    public static int select(int[] weights, int num){
        int weightSum = 0;
        for (int w : weights){
            weightSum += w;
        }

        int mod = num % weightSum;

        for (int i = 0; i < weights.length; i++) {

            for (int j = 0; j < weights.length; j++) {

                if (mod == 0 && weights[j] > 0){
                    return j;
                }else if(weights[j] > 0) {
                    weights[j]--;
                    mod--;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] weightsCopy = new int[]{1,2,3};
        for (int i = 1; i <= 16 ; i++) {
            System.out.println(i + " : " + weightsCopy[select(new int[]{1,2,3}, i)]);
        }
    }
}
