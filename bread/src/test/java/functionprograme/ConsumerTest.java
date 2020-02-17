package functionprograme;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.*;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/2/15
 */
public class ConsumerTest {
    @Test
    public void consumerTest() {
        Consumer f = System.out::println;
        Consumer f2 = n -> System.out.println(n + "-F2");

        //执行完F后再执行F2的Accept方法
        f.andThen(f2).accept("test");

        //连续执行F的Accept方法
        f.andThen(f).andThen(f).andThen(f).accept("test1");
    }

    /**
     * Function测试
     */
    @Test
    public void functionTest() {
        Function<Integer, Integer> f = s -> ++s;
        Function<Integer, Integer> g = s -> s * 2;

        /**
         * 下面表示在执行F时，先执行G，并且执行F时使用G的输出当作输入。
         * 相当于以下代码：
         * Integer a = g.apply(1);
         * System.out.println(f.apply(a));
         */
        System.out.println(f.compose(g).apply(1));

        /**
         * 表示执行F的Apply后使用其返回的值当作输入再执行G的Apply；
         * 相当于以下代码
         * Integer a = f.apply(1);
         * System.out.println(g.apply(a));
         */
        System.out.println(f.andThen(g).apply(1));

        /**
         * identity方法会返回一个不进行任何处理的Function，即输出与输入值相等；
         */
        System.out.println(Function.identity().apply("a"));
    }

    /**
     * Predicate测试
     */
    @Test
    public void predicateTest() {
        Predicate<String> p = o -> o.equals("test");
        Predicate<String> g = o -> o.startsWith("t");

        /**
         * negate: 用于对原来的Predicate做取反处理；
         * 如当调用p.test("test")为True时，调用p.negate().test("test")就会是False；
         */
        Assert.assertFalse(p.negate().test("test"));

        /**
         * and: 针对同一输入值，多个Predicate均返回True时返回True，否则返回False；
         */
        Assert.assertTrue(p.and(g).test("test"));

        /**
         * or: 针对同一输入值，多个Predicate只要有一个返回True则返回True，否则返回False
         */
        Assert.assertTrue(p.or(g).test("ta"));
    }

    @Test
    public void streamMap(){
        Stream<String> stream = Stream.of("11","22","33");
        stream.map(n -> n.concat(".txt")).forEach(System.out::println);
    }

    @Test
    public void streamFlatMap(){
        Stream<String> stream = Stream.of("11","22","33");
        stream.flatMap(n -> Stream.of(n.split(""))).forEach(System.out::println);
    }

    @Test
    public void streamFlatMapToDouble(){
        Stream<String> stream = Stream.of("111","22","33");
        stream.flatMapToDouble(n -> DoubleStream.of(Double.valueOf(n))).sorted().limit(2).average().ifPresent(System.out::println);
    }

    @Test
    public void streamflatMapToInt(){
        Stream<String> stream = Stream.of("1","2","3");
        stream.flatMapToInt(n -> IntStream.of(Integer.valueOf(n))).average().ifPresent(System.out::println);
    }

    @Test
    public void streamfilter(){
        Stream<String> stream = Stream.of("test","heyman","bruce");
        stream.filter(n -> n.startsWith("t")
        ).forEach(n -> System.out.println(n));

        Map<String, String> stringMap = new HashMap<String, String>(){{
            put("k1","test");
            put("k2","heyman");
            put("k3","bruce");
        }};

        Stream<Map<String, String>> mapStream = Stream.of(stringMap);
        Stream<Map<String, String>> mapStream1 = mapStream.filter(n -> n.containsKey("k1")).peek(n -> System.out.println(n));
        mapStream1.forEach(n -> System.out.println(n));
        stringMap.forEach( (k,v) -> System.out.println(k +"#"+ v));

        Supplier<String> supplier = () -> "hello";
        UnaryOperator<Integer> unaryOperator  = x -> x+1;
    }
}
