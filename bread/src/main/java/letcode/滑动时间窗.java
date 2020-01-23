package letcode;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: 邓小军
 * @Date: 2019/11/28 11:48
 * @Description:
 */
public class 滑动时间窗 {

    public static void main(String[] args) {
        final SlideWindow slideWindow = new SlideWindow();
        slideWindow.initWindow(TimeUnit.SECONDS,10);
        slideWindow.start();


        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            if (i % 2 ==0){
                executorService.submit(new Runnable() {
                    public void run() {
                        int count = 0;
                        while (true){
                            slideWindow.increase();

                            if (count % 1000 == 0){
                                System.out.println("current size : " + slideWindow.getSize() + " window index is : "+ slideWindow.head);
                            }

                            try {
                                Thread.sleep(1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            count++;
                        }
                    }
                });
            }else {
                executorService.submit(new Runnable() {
                    public void run() {
                        while (true){
                            slideWindow.decrease();
                            try {
                                Thread.sleep(1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                });
            }
        }


        while (true){
            slideWindow.calculateValate();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class SlideWindow{

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