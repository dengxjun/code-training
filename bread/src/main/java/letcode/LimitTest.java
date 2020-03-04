package letcode;

import java.util.concurrent.TimeUnit;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/3/4
 */
public class LimitTest implements Runnable{

    private SlideWindow slideWindow;
    private LimitLevel timeLeval = null;
    private long startLimitTime;
    private boolean limitFlag = false;

    public LimitTest(SlideWindow slideWindow, LimitLevel timeLeval) {
        this.slideWindow = slideWindow;
        this.timeLeval = timeLeval;
        slideWindow.initWindow(TimeUnit.SECONDS,timeLeval.getTimeLeval());
        slideWindow.start();
    }


    public static void main(String[] args) {
        final SlideWindow slideWindow = new SlideWindow();
        // 5s 请选择LimitLevel.TIME_LEVEL_1
        // 1min 请选择LimitLevel.TIME_LEVEL_2
        // 1h 请选择LimitLevel.TIME_LEVEL_3
        LimitTest limitTest = new LimitTest(slideWindow, LimitLevel.TIME_LEVEL_1);
        new Thread(limitTest).start();
    }


    @Override
    public void run() {
        while (true){
            if (checkLimit("10.12.25.36")){
                System.out.println("sleep..." + timeLeval.getLimitTime() + "current size is:"+ slideWindow.getSize());
                try {
                    Thread.sleep(1000 * timeLeval.getTimeLeval());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean checkLimit(String ipAddress){
        long timeDiff = (System.currentTimeMillis() - startLimitTime) / 1000;
        if (limitFlag == true && timeDiff < timeLeval.getLimitTime()){
            return limitFlag;
        }

        limitFlag = false;

        slideWindow.increase();
        if (slideWindow.getSize() >= timeLeval.getCount()){
            limitFlag = true;
            startLimitTime = System.currentTimeMillis();
        }
        return limitFlag;
    }


    public static class SlideWindow{

        //窗口总数量定义
        int windowNum = 10;

        // 窗口容器
        int[] windows = new int[windowNum];

        //时间窗口滑动的时间间隔
        int timeGap = 0;

        //当前容器容量
        int size = 0;

        //当前窗口
        int head = 0;
        // 末尾数
        int tail = windows[windowNum-1];

        //运行状态 0：未启动 1：运行中
        int status = 0;

        int windowLoop = 0;

        public void initWindow(TimeUnit timeUnit, int timeRange){
            switch (timeUnit){
                case MINUTES:
                    timeGap = 60 * 1000 * timeRange / 100;
                case SECONDS:
                    timeGap = 1000 * timeRange / 100;
            }
        }

        public synchronized void increase(){
            windows[head] = windows[head] + 1;
            size++;
        }

        public synchronized void decrease(){
            if (windows[head] ==0) return;

            windows[head] = windows[head] - 1;
            size--;
        }

        public synchronized void calculateValate(){
            int total = 0;
            for (int i:
                    windows) {
                total += i;
            }

            System.out.println("total is : " + total +" and size is : " + size);
        }

        public synchronized void stepWindow(){

            if (windowLoop == 0 && head < windowNum - 2){
                head++;
                return;
            }

            size = size - windows[tail] > 0 ? size - windows[tail] : 0;
            windows[tail] = 0;

            if (tail == windowNum -1) {
                tail = 0;
            }else {
                tail++;
            }


            if (head == windowNum -1) {
                head = 0;
                windowLoop++;
            }
            else head++;
//        System.out.println("current window index is : " + current);

        }

        public int getSize(){
            return size;
        }

        public void start(){
            status = 1;
            new Thread(new Runnable() {
                public void run() {
                    while (status == 1){
                        stepWindow();
                        try {
                            Thread.sleep(timeGap);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    System.out.println("SlideWindow have stoped...");
                }
            }).start();
        }

        public void stop(){
            status = 0;
        }
    }

    public static enum LimitLevel{
        TIME_LEVEL_1(5,1000,60),
        TIME_LEVEL_2(60,1000,60*10),
        TIME_LEVEL_3(60*60,5000,60*60*24);

        private Integer timeLeval;
        private Integer count;
        private Integer limitTime;

        LimitLevel(Integer limitTime, Integer count, Integer timeLeval) {
            this.limitTime = limitTime;
            this.count = count;
            this.timeLeval = timeLeval;
        }

        public Integer getCount() {
            return count;
        }

        public Integer getLimitTime() {
            return limitTime;
        }

        public Integer getTimeLeval() {
            return timeLeval;
        }
    }
}
