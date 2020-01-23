package letcode;

/**
 * @Auther: 邓小军
 * @Date: 2019/11/23 09:20
 * @Description:
 */
public class ZuiDasanjiaoxingmianji {
    //海伦公式 p=(a+b+c)/2  S=√[p(p-a)(p-b)(p-c)]
    public double largestTriangleArea(int[][] points) {
        int length = points.length;

        if (length < 3 || length > 50) return -1;

        int x=0,y =0;
        double largestArea = 0d;
        for (int i = 0; i < length; i++) {
            int[] point1 = points[i];
            // get seconde point
            for (int j = i+1; j < length; j++) {
                int[] point2 =  points[j];

                for (int k = j+1; k < length; k++) {
                    int[] point3 = points[k];
                    //判断是否在同一条直线
                    if ((point1[0] == point2[0] && point2[0] == point3[0])
                            || ((point1[1] == point2[1] && point2[1] == point3[1]))){
                        continue;
                    }

                    /*int high=0,bottomLength=0;
                    //两点分布在Y轴
                    if (point1[0] == 0 && point2[0] == 0){
                        high = point3[0];
                        bottomLength = Math.abs(point1[1] - point2[1]);
                    }else if (point1[0] == 0 && point3[0] == 0){
                        high = point2[0];
                        bottomLength = Math.abs(point1[1] - point3[1]);
                    }else if (point2[0] == 0 && point3[0] == 0){
                        high = point1[0];
                        bottomLength = Math.abs(point2[1] - point3[1]);
                    }

                    //两点分布在X轴
                    if (point1[1] == 0 && point2[1] == 0){
                        high = point3[1];
                        bottomLength = Math.abs(point1[0] - point2[1]);
                    }else if (point1[1] == 0 && point3[1] == 0){
                        high = point2[1];
                        bottomLength = Math.abs(point1[0] - point3[0]);
                    }else if (point2[1] == 0 && point3[1] == 0){
                        high = point1[0];
                        bottomLength = Math.abs(point2[0] - point3[0]);
                    }

                    double tmpArea = bottomLength * high / 2.0;*/

                    //海伦公式 p=(a+b+c)/2  S=√[p(p-a)(p-b)(p-c)]  最终公式为： 1/4）√[(a+b+c)(a+b-c)(a+c-b)(b+c-a)]
                    double a = 0,b=0,c=0;
                    a = Math.sqrt(Math.pow(Math.abs(point1[0] - point2[0]),2) + Math.pow(Math.abs(point1[1] - point2[1]),2));
                    b = Math.sqrt(Math.pow(Math.abs(point1[0] - point3[0]),2) + Math.pow(Math.abs(point1[1] - point3[1]),2));
                    c = Math.sqrt(Math.pow(Math.abs(point3[0] - point2[0]),2) + Math.pow(Math.abs(point3[1] - point2[1]),2));
                    double tmpArea = 0.25 * Math.sqrt((a+b+c) * (a+b-c) * (a+c-b) * (b+c-a));

                    if (tmpArea > largestArea) largestArea = tmpArea;

                }
            }
        }
        return largestArea;
    }

    public static void main(String[] args) {
//        int[][] p = new int[][]{{0,0},{0,1},{1,0},{0,2},{2,0}};
        int[][] p = new int[][]{{4,6},{6,5},{3,1}};
        ZuiDasanjiaoxingmianji obj = new  ZuiDasanjiaoxingmianji();
        System.out.println(obj.largestTriangleArea(p));
    }

}
