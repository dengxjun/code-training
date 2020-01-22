package trains.control;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import trains.BaseTest;
import trains.input.builder.*;
import trains.output.FootPrint;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: 邓小军
 * @Date: 2019/12/30 17:11
 * @Description: 组装不同命令测试 && 多线程并发执行不同命令测试
 *
 */
public class TainControlCenterTest extends BaseTest {

    static TainControlCenter tainControlCenter = null;

    @Test
    public void currentProcess(){
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        CommandBuilder[] commandBuilders = new CommandBuilder[]{
                new DefaultCommandBuilder(),
                new ExtCommandBuilder1(),
                new ExtCommandBuilder2(),
                new ExtCommandBuilder3(),
                new ExtCommandBuilder4()
        };

        for (int i = 0; i < 10; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    Random random = new Random();
                    List<FootPrint> footPrints = tainControlCenter.startProcess(commandBuilders[random.nextInt(4)].buildCommander());
                    printOutput(footPrints);
                }
            });
        }

        executorService.shutdown();
        while (true){
            if (executorService.isTerminated()){
                break;
            }else {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Test
    public void startProcess() {
        CommandBuilder commandBuilder = new DefaultCommandBuilder();
        List<FootPrint> footPrints = tainControlCenter.startProcess(commandBuilder.buildCommander());

        printOutput(footPrints);


        Assert.assertEquals(9, footPrints.get(0).getResult());
        Assert.assertEquals(5, footPrints.get(1).getResult());
        Assert.assertEquals(13, footPrints.get(2).getResult());
        Assert.assertEquals(22, footPrints.get(3).getResult());
        Assert.assertEquals(-1, footPrints.get(4).getResult());
        Assert.assertEquals(2, footPrints.get(5).getResult());
        Assert.assertEquals(3, footPrints.get(6).getResult());
        Assert.assertEquals(9, footPrints.get(7).getResult());
        Assert.assertEquals(9, footPrints.get(8).getResult());
        Assert.assertEquals(7, footPrints.get(9).getResult());
    }


    @Test
    public void startProcessForCommandExt1() {
        CommandBuilder commandBuilder = new ExtCommandBuilder1();
        List<FootPrint> footPrints = tainControlCenter.startProcess(commandBuilder.buildCommander());

        printOutput(footPrints);


        Assert.assertEquals(9, footPrints.get(0).getResult());
        Assert.assertEquals(5, footPrints.get(1).getResult());
        Assert.assertEquals(13, footPrints.get(2).getResult());
        Assert.assertEquals(22, footPrints.get(3).getResult());
        Assert.assertEquals(-1, footPrints.get(4).getResult());
    }


    @Test
    public void startProcessForCommandExt2() {
        CommandBuilder commandBuilder = new ExtCommandBuilder2();
        List<FootPrint> footPrints = tainControlCenter.startProcess(commandBuilder.buildCommander());

        printOutput(footPrints);


        Assert.assertEquals(2, footPrints.get(0).getResult());
        Assert.assertEquals(3, footPrints.get(1).getResult());
        Assert.assertEquals(9, footPrints.get(2).getResult());
        Assert.assertEquals(9, footPrints.get(3).getResult());
        Assert.assertEquals(7, footPrints.get(4).getResult());
    }

    @Test
    public void startProcessForCommandExt3() {
        CommandBuilder commandBuilder = new ExtCommandBuilder3();
        List<FootPrint> footPrints = tainControlCenter.startProcess(commandBuilder.buildCommander());

        printOutput(footPrints);


        Assert.assertEquals(9, footPrints.get(0).getResult());
        Assert.assertEquals(9, footPrints.get(1).getResult());
        Assert.assertEquals(7, footPrints.get(2).getResult());
    }


    @Test
    public void startProcessForCommandExt4() {
        CommandBuilder commandBuilder = new ExtCommandBuilder4();
        List<FootPrint> footPrints = tainControlCenter.startProcess(commandBuilder.buildCommander());

        printOutput(footPrints);


        Assert.assertEquals(7, footPrints.get(0).getResult());
    }

    private void printOutput(List<FootPrint> footPrints) {
        footPrints.forEach((item -> {
            System.out.println("output for question: " + footPrints.indexOf(item) + "   result:  " + item.getResult());
            item.getPrints().forEach((print -> {
                System.out.print(print + " | ");
            }));
            System.out.println();
        }));
    }

}